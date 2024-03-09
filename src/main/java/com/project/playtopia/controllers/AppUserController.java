package com.project.playtopia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppUserController {

    @GetMapping("/login")
    String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    String registerPage(){
        return "register";
    }
}
