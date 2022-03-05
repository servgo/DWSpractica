package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    GameService gameService;
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/addGame")
    public String addGame(){
        return "NewGame";
    }
    @GetMapping("/showGames")
    public String showGames(Model model){
        model.addAttribute("games", gameService.getGames());
        return "ShowGames";
    }
    @GetMapping("/shoppingCart")
    public String shoppingCart(){
        return "ShoppingCart";
    }
}
