package com.takeandtrade.capstone.controllers;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenicationController {
    @GetMapping("/login")
    public String showLoginForm() {

        return "login";
    }
}

