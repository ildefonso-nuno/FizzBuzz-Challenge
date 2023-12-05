package de.otto.beluga.codingsession.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StartLargerThanEndException extends RuntimeException{
    public StartLargerThanEndException() {
        super("Start number cannot be larger than end number");
    }
}
