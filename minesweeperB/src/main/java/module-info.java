module com.medialab.minesweeperb {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.medialab.minesweeperb to javafx.fxml;
    exports com.medialab.minesweeperb;
}