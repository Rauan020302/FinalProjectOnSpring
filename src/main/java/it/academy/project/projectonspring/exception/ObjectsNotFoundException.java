package it.academy.project.projectonspring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ObjectsNotFoundException extends Exception {
    public ObjectsNotFoundException(){
    }

    public ObjectsNotFoundException(String text){
        super(text);
    }

    public ObjectsNotFoundException(String text, Long id){
        super(text + id);
    }



}
