package com.example.dwspractica;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.isUserInRole("USER"));
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
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
    @GetMapping("/register")
    public String register(){
        return "Register";
    }
    @GetMapping("/registered")
    public String registered(@PathVariable String username, @PathVariable String password){
        return "Registered";
    }
}
