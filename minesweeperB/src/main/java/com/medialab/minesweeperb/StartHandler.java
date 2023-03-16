package com.medialab.minesweeperb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.FileNotFoundException;

import static com.medialab.minesweeperb.Main.*;

public class StartHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent t) {
                try {
                        Minesweeper.startGame(numBombs, superBomb, mode, timeInSeconds);
                } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                }

        }
}
