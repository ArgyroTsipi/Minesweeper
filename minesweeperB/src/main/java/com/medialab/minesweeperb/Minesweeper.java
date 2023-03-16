package com.medialab.minesweeperb;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import com.medialab.minesweeperb.CreateHandler.*;

// switching scenes tutorial: https://www.youtube.com/watch?v=7LxWQIDOzyE&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=4
// minesweeper game tutorial: https://www.youtube.com/watch?v=JwcyxuKko_M
// count down timer: https://asgteach.com/2011/10/javafx-animation-and-binding-simple-countdown-timer-2/
// alert boxes: https://www.youtube.com/watch?v=SpL3EToqaXA&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=5
public class Minesweeper {

    static final int TILE_SIZE = 40;
    private static final int W = 600;
    private static final int H = 600;
    static int X_TILES; // not final
    //W/TILE_SIZE;
    static int Y_TILES; // not final
    static Tile[][] grid; // = new Tile[X_TILES][Y_TILES];
    private static Scene scene1, scene2, scene3, scene4;
    private  static int remainingTimeInSeconds, timeInSeconds;
    private  static Timeline timeline;
    private static Label timerLabel;
    static Label markedLabel;
    private static Label totalNumBombsLabel;
    static int markedBombs = 0;
    private static Timer timer;
    private static Label countDownText;
    private static KeyFrame frame;
    public static int mode;
    private static int numBombs;
    private static int superBomb;
    private static int superBombx, superBomby;

    public static void startGame(int numBombs, int superBomb, int mode, int timeInSeconds) throws FileNotFoundException {
        remainingTimeInSeconds = timeInSeconds;
        markedBombs = 0;
        BorderPane borderPane = new BorderPane();
       // GridPane gridPane = new GridPane();
        if(mode == 1){ //easy
         //   borderPane.setPrefSize(800,800);
        }
        else if(mode == 2){ //normal
          //  borderPane.setPrefSize(W, H);
        }
        Minesweeper.remainingTimeInSeconds = timeInSeconds;
        Minesweeper.numBombs = numBombs;
        Minesweeper.mode = mode;
        Minesweeper.superBomb = superBomb;
        borderPane.setTop(Main.menubar);
        borderPane.setCenter(Minesweeper.createContent(numBombs, mode, superBomb, timeInSeconds));
        //borderPane.setBackground(Main.borderPane.getBackground());
    //    borderPane.getChildren().addAll(Main.application, Main.topLabels, Minesweeper.createContent(numBombs, mode, superBomb, timeInSeconds));
      //  borderPane.getChildren().add(Minesweeper.createContent(numBombs, mode, superBomb));
      // borderPane.setCenter(Minesweeper.createContent(numBombs, mode, superBomb, timeInSeconds));
        // borderPane.setBottom(Main.application);
      // borderPane.setTop(Main.topLabels);

// changing scenes
        Scene newScene =  new Scene(borderPane, 900, 900);
        File f = new File("menubar.css");
        newScene.getStylesheets().clear();
        newScene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        //GUI.getStage().setScene(newScene);
        //Stage stage = ((Node)event.getSource()).getScene().getWindow();
        // or alternatively, just:
        // Stage stage = button.getScene().getStage();
        //stage.setScene(newScene);
        Main.stage.setScene(newScene);
        Main.stage.show();
    }

        public static Parent createContent(int numBombs, int mode, int superBomb, int timeInSeconds) throws FileNotFoundException {
            Pane root = new Pane();

            if(mode == 1){ //easy
                root.setPrefSize(700,700);
                //timeInSeconds = 120 +(int)(Math.random()*((240-120)+1));
                //int numBombs = 9 + (int)(Math.random()* ((11-9) +1));
                X_TILES = 9;
                Y_TILES =  9;
            }
            else if(mode == 2){ //normal
                root.setPrefSize(W, H);
                //int numBombs = 35 + (int) (Math.random() * ((45 - 35) + 1));
                //timeInSeconds = 240 + (int) (Math.random() * ((360 - 240) + 1));
                X_TILES = 16;
                Y_TILES = 16;
            }
// ----------------------------------------------------------------------------------
// count down timer
            remainingTimeInSeconds = timeInSeconds;
            timerLabel = new Label("Time left: " + remainingTimeInSeconds);
            totalNumBombsLabel = new Label("Total number of bombs: " + numBombs);
            markedLabel = new Label("Marked bombs :" + markedBombs);
            VBox vbox = new VBox(timerLabel, totalNumBombsLabel, markedLabel, root); //timerLabel
            vbox.setAlignment(Pos.CENTER);

            timeline = new Timeline();
            frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    remainingTimeInSeconds--;
                    timerLabel.setText("Time left: " + remainingTimeInSeconds + " seconds");
                    timerLabel.setVisible(true);
                    if (remainingTimeInSeconds <= 0) {
                        timeline.stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("Time's up! Game ended!");
                        alert.show();
                    }
                }
            });
            timeline.getKeyFrames().add(frame);
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
// ----------------------------------------------------------------------------------
            Random random = new Random();
            boolean[][] mapBombs = new boolean[X_TILES][Y_TILES];
            List<int[]> bombList = new ArrayList<>();

            List<String> bombPositions = new ArrayList<>();
            for (int i = 0; i < numBombs; i++) {
                int row, col;
                do {
                    row = random.nextInt(Y_TILES);
                    col = random.nextInt(X_TILES);
                } while (mapBombs[row][col] == true);

                mapBombs[col][row] = true;
                bombList.add(new int[]{row, col});
            }

            int superbombIndex = random.nextInt(bombList.size());

            for (int i = 0; i < numBombs; i++) {
                int[] bomb_list_elem = bombList.get(i);
                String isSuperbomb;
                if (i == superbombIndex && mode == 2 && superBomb == 1) {
                    isSuperbomb = "1";
                    superBombx = bomb_list_elem[1];
                    superBomby = bomb_list_elem[0];
                }
                else
                    isSuperbomb = "0";
                bombPositions.add(bomb_list_elem[0] + "," + bomb_list_elem[1] + "," + isSuperbomb);
            }
// ----------------------------------------------------------------------------------
// write the position of the bombs in a txt file
            try {
                Files.write(Paths.get("medialab/mines.txt"), bombPositions);
            } catch (IOException e) {
                e.printStackTrace();
            }
// ----------------------------------------------------------------------------------

            grid = new Tile[X_TILES][Y_TILES];
            for(int y=0; y<Y_TILES; y++){
                for(int x=0; x<X_TILES; x++){
                    Tile tile = new Tile(x, y, mapBombs[x][y] ); //Math.random() < 0.2

                    grid[x][y] = tile;
                    root.getChildren().add(tile);
                }
            }
            for(int y=0; y<Y_TILES; y++){
                for(int x=0; x<X_TILES; x++){
                    Tile tile = grid[x][y];
                    if(tile.hasBomb)
                        continue;
                    //set bombs
                    long bombs = getNeighbors(tile).stream().filter(t -> t.hasBomb).count();

                    if(bombs > 0)
                        // we've obtained a list of neighbors of that tile that we're going through,
                        // we've obtained a stream of elements,
                        // then we've filtered the elements so we're filtering neighbors
                        // if this neighbor has a bomb then lay it in the stream,
                        // if not then discard it
                        tile.text.setText(String.valueOf(bombs));
                }
            }

// ----------------------------------------------------------------------------------
//        VBox vbox = new VBox(timerLabel, root); //timerLabel
//        vbox.setAlignment(Pos.CENTER);
            return vbox;
        }
        static List<Tile> getNeighbors(Tile tile){
            List<Tile> neighbors = new ArrayList<>();

            //ttt
            //txt
            //ttt

            int[] points = new int[] {
                    -1,-1,
                    -1,0,
                    -1,1,
                    0,-1,
                    0,1,
                    1,-1,
                    1,0,
                    1,1
            };
            for(int i=0; i< points.length; i++){
                int dx = points[i];
                int dy = points[++i];

                int newX = tile.x + dx;
                int newY = tile.y + dy;

                if(newX >= 0 && newX < X_TILES && newY >= 0 && newY <Y_TILES){
                    neighbors.add(grid[newX][newY]);
                }
            }
            return neighbors;
        }
    }






