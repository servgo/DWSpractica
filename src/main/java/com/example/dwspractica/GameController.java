package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class GameController {
    @Autowired
    GameService gameService;
    @PostConstruct
    public void init(){
        gameService.addGame(new Game("Elden Ring", "PC", 60));
    }
    @GetMapping("/addedGame")
    public String newGame(@RequestParam String name, @RequestParam String platform, @RequestParam float price){
        gameService.addGame(new Game(name, platform, price));
        return "CreatedGame";
    }
    @GetMapping("/allGames")
    public String allGames(Model model){
        model.addAttribute("games", gameService.getGames());
        return "ShowGames";
    }
    @GetMapping("/game/{id}")
    public String showGame(Model model, @PathVariable int id){
        model.addAttribute("game", gameService.getGames(id));
        return "ShowGame";
    }
    @GetMapping("/deleted/{id}")
    public String deleteGame(@PathVariable int id){
        gameService.deleteGame(id);
        return "DeletedGame";
    }

}
