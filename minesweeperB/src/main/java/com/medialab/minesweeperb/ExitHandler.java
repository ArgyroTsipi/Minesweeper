package com.medialab.minesweeperb;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
// added javadoc based on the following tutorial: https://www.jetbrains.com/help/idea/working-with-code-documentation.html
public class ExitHandler implements EventHandler<ActionEvent> {

    /** Closes the window, it's a handler for when the exit option is pressed.
     *
     * @param t
     * ActionEvent
     *
     */

    public void handle(ActionEvent t) {
        System.exit(0);
    }
}
