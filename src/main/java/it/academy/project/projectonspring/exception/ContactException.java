package it.academy.project.projectonspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.LENGTH_REQUIRED)
public class ContactException extends NumberFormatException{
    public ContactException() {
    }
}
