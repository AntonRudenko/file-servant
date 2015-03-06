package ru.teamlabs.fileservant.dto;

/**
 * @author Anton Rudenko.
 */
public class ExceptionDto {

    public long errorCode;
    public String errorMessage;


    public ExceptionDto() {
    }

    public ExceptionDto(String errorMessage, long errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
