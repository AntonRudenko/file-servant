package ru.teamlabs.fileservant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.teamlabs.fileservant.dto.ExceptionDto;

/**
 * @author Anton Rudenko.
 */
@ControllerAdvice
@RestController
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public ExceptionDto handleException(Exception e) {
        return new ExceptionDto(e.getMessage(), 1);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileException.class)
    public void handFileException(FileException e) {

    }

}
