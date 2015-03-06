package ru.teamlabs.fileservant;

/**
 * @author Anton Rudenko.
 */
public class WrongParamsException extends RuntimeException {

    public WrongParamsException() {
    }

    public WrongParamsException(String message) {
        super(message);
    }
}
