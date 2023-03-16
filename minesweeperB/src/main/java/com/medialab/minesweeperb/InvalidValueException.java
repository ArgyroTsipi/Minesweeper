package com.medialab.minesweeperb;

public class InvalidValueException extends Throwable { //opws h ekfwnhsh
    String message;
    public String message() {
        return message;
    }
    public InvalidValueException(String s) {
        message = s;
    }
}
