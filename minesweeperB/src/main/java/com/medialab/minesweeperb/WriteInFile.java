package com.medialab.minesweeperb;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// write in file: https://stackoverflow.com/questions/24565539/in-need-of-javafx-help-writing-to-a-txt-file
// get current directory: https://stackoverflow.com/questions/4871051/how-to-get-the-current-working-directory-in-java
public class WriteInFile {
    static void writeFile(String scenarioID, int numBombs, int superBomb, int mode, int timeInSeconds){

        String currentDirectory = System.getProperty("user.dir");
        String fileName = currentDirectory + "/medialab/" + scenarioID + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(mode));
            writer.newLine();
            writer.write(String.valueOf(numBombs));
            writer.newLine();
            writer.write(String.valueOf(timeInSeconds));
            writer.newLine();
            writer.write(String.valueOf(superBomb));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }

