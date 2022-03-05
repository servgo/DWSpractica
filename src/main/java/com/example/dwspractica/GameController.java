package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class GameController {
    @Autowired
    GameService gameService;
    @PostConstruct
    public void init(){

    }
    @GetMapping("NewGame")
    public String newGame(Model model, @RequestParam String name, @RequestParam String platform, @RequestParam float price){
        gameService.addGame(new Game(name, platform, price));
        return "CreatedGame";
    }

}
