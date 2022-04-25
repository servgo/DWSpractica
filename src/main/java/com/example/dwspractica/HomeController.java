package com.example.dwspractica;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "Login";
    }
    @GetMapping("/loginerror")
    public String loginerror(){
        return "LoginError";
    }
    @GetMapping("/logout")
    public String logout(){
        return "Logout";
    }
}
