package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/myProfile")
    public String myProfile(Model model, HttpServletRequest request){
        model.addAttribute("nombre", request.getUserPrincipal().getName());
        return "Profile";
    }
}
