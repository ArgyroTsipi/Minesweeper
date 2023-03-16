package com.medialab.minesweeperb;

public class InvalidDescriptionException extends Throwable { //opws h ekfwnhsh
    String message;
    public String message() {
        return message;
    }
    public InvalidDescriptionException(String s) {
        message = s;
    }

}
