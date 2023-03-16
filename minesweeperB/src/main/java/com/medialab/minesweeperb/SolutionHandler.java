package com.medialab.minesweeperb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import static com.medialab.minesweeperb.Minesweeper.*;

public class SolutionHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        for (int i = 0; i < X_TILES; i++) {
            for (int j = 0; j < Y_TILES; j++) {
                try {
                    grid[i][j].OpenALL();
                } catch (NullPointerException e) {
                    return;
                }
            }
        }
    }
}
