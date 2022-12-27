package org.cadium.base.exceptions;

public class UnknownAccessException extends Exception {

    public UnknownAccessException() {
        super("This exception occurs as soon as unsafe methods are called by invalid operations.");
    }

}
