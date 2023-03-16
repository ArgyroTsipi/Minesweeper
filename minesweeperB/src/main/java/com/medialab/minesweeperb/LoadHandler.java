package com.medialab.minesweeperb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.medialab.minesweeperb.Main.stage;
import static com.medialab.minesweeperb.Main.mode;
import static com.medialab.minesweeperb.Main.timeInSeconds;
import static com.medialab.minesweeperb.Main.superBomb;
import static com.medialab.minesweeperb.Main.numBombs;

// read from a txt file: https://stackoverflow.com/questions/37769481/javafx-gui-that-opens-a-text-file-how-to-read-whats-in-text-file-and-edit-save
// get current directory: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
public class LoadHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent t) {
            // choose file from medialab folder
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open .txt File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            String currentDirectory = System.getProperty("user.dir"); // open the current diorectory
            fileChooser.setInitialDirectory(new File(currentDirectory + "/medialab/"));

            File file = fileChooser.showOpenDialog(stage); //user chooses file & selects one

            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = reader.readLine();
                    mode = Integer.parseInt(line);
                    line = reader.readLine();
                    numBombs = Integer.parseInt(line);
                    line = reader.readLine();
                    timeInSeconds = Integer.parseInt(line);
                    line = reader.readLine();
                    superBomb = Integer.parseInt(line);

                    if (mode == 1) { // check if the values are valid
                        if (numBombs < 9 || numBombs > 11)
                            throw new InvalidValueException("Write a valid number of bombs: 9 <= bombs <= 11.");
                        if (timeInSeconds < 120 || timeInSeconds > 180)
                            throw new InvalidValueException("Write a valid time value: 120 <= time <=  180 seconds.");
                        if (superBomb != 0)
                            throw new InvalidValueException("Write a valid superbomb value: 0");
                    } else if (mode == 2) {
                        if (numBombs < 35 || numBombs > 45)
                            throw new InvalidValueException("Write a valid number of bombs: 35 <= bombs <= 45.");
                        if (timeInSeconds < 240 || timeInSeconds > 360)
                            throw new InvalidValueException("Write a valid time value: 240 <= time <=  360 seconds.");
                        if (superBomb != 0 && superBomb != 1)
                            throw new InvalidValueException("Write a valid superbomb value: 0 or 1.");
                    } else {
                        throw new InvalidValueException("Mode is either Easy (value:1) or Normal (value:2)");
                    }

                } catch (IOException e) {
                } catch (NumberFormatException e) {
                    try {
                        throw new InvalidDescriptionException("Invalid file");
                    } catch (InvalidDescriptionException ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid");
                        alert.setHeaderText(null);
                        alert.setContentText(ex.message());
                        alert.showAndWait();
                    }
                } catch (InvalidValueException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning");
                    alert.setContentText(e.message());
                    alert.showAndWait();
                }
            }
            }
}
