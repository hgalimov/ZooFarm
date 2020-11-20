package ru.uennar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @GetMapping("/user")
    public String userPage(HttpServletRequest request){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        System.out.println(name + surname);
        return "user/user";
    }
    @GetMapping("/login")
    public String login(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "surname", required = false) String surname){
        System.out.println(name + surname);
        return "user/login";
    }
}
