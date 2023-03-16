package com.medialab.minesweeperb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.Objects;
import java.util.Optional;

// tutorial for input text: https://www.youtube.com/watch?v=cwJK_WpseKQ
// tutorial for check boxes: https://www.youtube.com/watch?v=JWxnoe6APUY
// tutorial for radio: https://www.youtube.com/watch?v=VB_YnRteVAM&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=24
public class CreateHandler implements EventHandler<ActionEvent> {

    public void handle(ActionEvent t) {
        // Create the dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Create Game");

        // ScenarioID as a textfield
        TextField scenarioID = new TextField();
        scenarioID.setStyle("-fx-background-color: #FFFAF0; -fx-border-color: #FF69B4;");

        // Easy or Hard mode as radio buttons
        RadioButton easyButton = new RadioButton("Easy Mode");
       // easyButton.setStyle("-fx-border-color: #FF69B4;");
        RadioButton hardButton = new RadioButton("Normal Mode");
       // hardButton.setStyle(" -fx-border-color: #FF69B4;");
        ToggleGroup difficultyGroup = new ToggleGroup();
        easyButton.setToggleGroup(difficultyGroup);
        hardButton.setToggleGroup(difficultyGroup);
        // Number of bombs as textfield
        TextField numBombs = new TextField();
        numBombs.setStyle("-fx-background-color:  #FFFAF0; -fx-border-color: #FF69B4"); //-fx-text-fill: #FFF0F5"
        // Superbomb as a checkbox
        CheckBox superBomb = new CheckBox("Superbomb");
       // superBomb.setStyle(" -fx-border-color: #FF69B4;");
        // time is seconds as a textfield
        TextField timeInSeconds = new TextField();
        timeInSeconds.setStyle("-fx-background-color:  #FFFAF0; -fx-border-color: #FF69B4;");


        VBox content = new VBox(10);
        content.getChildren().addAll(
                new Label("Scenario ID:"), scenarioID,
                new Label("Number of Bombs:"), numBombs,
                new Label("Time Limit in Seconds:"), timeInSeconds,
                new Label("Has SuperBomb:"), superBomb,
                new Label("Game Mode:"), easyButton, hardButton
        );
        dialog.getDialogPane().setContent(content);
        content.setStyle("-fx-background-color:  #FFF0F5");
        // Add buttons to the dialog
        ButtonType createButtonType = new ButtonType("Create", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(createButtonType);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(cancelButtonType);

        // Show the dialog and wait for the user's response
        Optional<ButtonType> result = dialog.showAndWait();

        // Process the user's response
        if (result.isPresent() && result.get() == createButtonType) {
            // User clicked the Create button, get the form values
            String scenarioIDnew = scenarioID.getText();
             int numBombsnew = Integer.parseInt(numBombs.getText());
             int timenew = Integer.parseInt(timeInSeconds.getText());
             int superbombnew = superBomb.isSelected() ? 1 : 0;
            RadioButton selectedButton = (RadioButton) difficultyGroup.getSelectedToggle();
             int mode = Objects.equals(selectedButton.getText(), "Easy Mode") ? 1 : 2;

            WriteInFile.writeFile(scenarioIDnew, numBombsnew, superbombnew, mode, timenew);
        }
    }


}
