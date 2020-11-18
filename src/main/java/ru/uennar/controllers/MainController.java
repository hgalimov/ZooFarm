package ru.uennar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/start")
    public String start(){
        return "main/start";
    }

    @GetMapping("/end")
    public String end(){
        return "main/end";
    }
}
