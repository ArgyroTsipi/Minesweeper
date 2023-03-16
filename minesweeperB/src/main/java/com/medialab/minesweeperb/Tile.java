package com.medialab.minesweeperb;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.medialab.minesweeperb.Minesweeper.*;

// tiles minesweeper tutorial: https://www.youtube.com/watch?v=JwcyxuKko_M

 class Tile extends StackPane {
    int x;
    int y;
    boolean hasBomb;
    private boolean isOpen = false;
    private int bombs = 0;
    private Rectangle border = new Rectangle(TILE_SIZE-2, TILE_SIZE-2);
    Text text = new Text();
    public Tile(int x, int y, boolean hasBomb){
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
        border.setFill(Color.LAVENDERBLUSH);
        border.setStroke(Color.LIGHTGRAY);
        // text.setFill(Color.WHITE);
        text.setFont(Font.font(18));
        text.setText(hasBomb ? "X" : "");
        text.setVisible(false);
        getChildren().addAll(border, text);

        setTranslateX(x*TILE_SIZE);
        setTranslateY(y*TILE_SIZE);

        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                open();
            }
            else if (event.getButton() == MouseButton.SECONDARY)
            {
                text.setFill(Color.PINK);
                text.setText("*&*");
                text.setVisible(true);
                markedBombs++;
                markedLabel.setText("Marked bombs: " + markedBombs);
            }
        });
    }
    public void open(){
        if(isOpen)
            return;

        if(hasBomb){
            text.setVisible(true);

            System.out.println("GAME OVER");
            Alert alertover = new Alert(Alert.AlertType.INFORMATION);
            alertover.setHeaderText("GAME OVER!");
            alertover.show();
            //scene1.setRoot(createContent(1));
            //scene4.setRoot(createContent(2));
            // we should clear the root before doing that
            return;
        }

//at the start (one way or another) we know how many mines there are.
// We could have a variable, say mines, that keeps track of the number of mines in the game.
// So, if the user uncovers a mine or several, then subtract the amount from the mines variable.
// If mines == 0 then the game is won.


        isOpen = true;
        text.setVisible(true);
        border.setFill(null);

        if(text.getText().isEmpty()){
            getNeighbors(this).forEach(Tile::open);
        }
    }
     void OpenALL() {
         if (this.isOpen) return;
         this.isOpen = true;
         text.setVisible(true);
     }
}

