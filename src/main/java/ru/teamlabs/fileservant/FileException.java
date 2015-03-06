package ru.teamlabs.fileservant;

/**
 * @author Anton Rudenko.
 */
public class FileException extends RuntimeException {

    public FileException() {
    }

    public FileException(String message) {
        super(message);
    }
}
