package it.academy.project.projectonspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetController {
    @GetMapping
    public String get(){
        return "Hello ";
    }
}
