package it.academy.project.projectonspring.exception;


public class ContactException extends NumberFormatException{
    public ContactException() {
    }
    public ContactException(String message){
        super(message);
    }
}
