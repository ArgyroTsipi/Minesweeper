package com.medialab.minesweeperb;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

// border pane: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/layout/BorderPane.html
// menu item: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/MenuItem.html
// menu: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Menu.html
// menu tutorial: https://www.youtube.com/watch?v=JBJ9MIEfU3k&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=21
// handle menu clicks: https://www.youtube.com/watch?v=AP4e6Lxncp4&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=22
// handle user events tutorial: https://www.youtube.com/watch?v=S_JN7zO12H4&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=3
// css tutorial: https://www.youtube.com/watch?v=UD_SJ07mQlM&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG&index=25
// communicating between windows: https://www.youtube.com/watch?v=HFAsMWkiLvg
public class Main extends Application {
    private Label timerLabel, markedLabel, numBombsLabel;
    private MenuItem create, load, start, exit, rounds, solution;
    static Stage stage;
    static int mode ;
    static int numBombs ;
    static int timeInSeconds ;
    static int superBomb;
    static BorderPane borderPane;
    static MenuBar menubar;
    static Menu application, details;
    static HBox topLabels;

    @Override
    public void start(Stage stage) throws IOException {
        Main.stage = stage;

        //application menu (create, load, start, exit)
        menubar = new MenuBar();
        application = new Menu("Application");
        application.setStyle("-fx-text-fill-color: Lavender;");

        //details menu (rounds, solution)
        details = new Menu("Details");
        details.setStyle("-fx-text-fill-color: Lavender;");

        borderPane = new BorderPane();
        //for application menu
        create = new MenuItem("Create");
        create.setStyle("-fx-background-color: RosyBrown;");
        create.setOnAction(new CreateHandler());

        load = new MenuItem("Load");
        load.setStyle("-fx-background-color: RosyBrown;");
        load.setOnAction(new LoadHandler());

        start = new MenuItem("Start");
        start.setStyle("-fx-background-color: RosyBrown;");
        start.setOnAction(new StartHandler());

        exit = new MenuItem("Exit");
        exit.setStyle("-fx-background-color: RosyBrown;");
        exit.setOnAction(new ExitHandler());

        //add the menu items to the menu
        application.getItems().addAll(create,load,start,exit);

        //for details menu
        rounds = new MenuItem("Rounds");
        rounds.setStyle("-fx-background-color: RosyBrown;");
        rounds.setOnAction(new RoundsHandler());

        solution = new MenuItem("Solution");
        solution.setStyle("-fx-background-color: RosyBrown;");
        solution.setOnAction(new SolutionHandler());

        // add the menu items to the menu
        details.getItems().addAll(rounds,solution);

        // add the menus to the menu bar
        menubar.getMenus().addAll(application, details);
        menubar.setStyle("-fx-background-color: RosyBrown");//FFFAF0

        borderPane.setTop(menubar);
        borderPane.setStyle("-fx-background-color: #FFF0F5");//FFF0F5

        stage.setTitle("Minesweeper Game");
        Text text = new Text("INSTRUCTIONS:\nClick on the Application Menu on the top left.\nClick Create, write in the text fields in the new window that popped up. \nAfter that, click Load on the Application Menu and choose a file. \nFinally, click Start on the Appplication Menu.  ");
        text.setStyle("-fx-text-fill-color: RosyBrown;");
        borderPane.setCenter(text);
        Scene scene = new Scene(borderPane, 500, 500);

        // include the css file
        File f = new File("menubar.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}