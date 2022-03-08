package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class GameController {
    @Autowired
    GameService gameService;

    @PostConstruct
    public void init() {
        gameService.addGame(new Game("Elden Ring", "PC", 60));
    }
    @GetMapping("/addGame")
    public String addGame(){
        return "NewGame";
    }

    @GetMapping("/addedGame")
    public String newGame(@RequestParam String name, @RequestParam String platform, @RequestParam float price) {
        gameService.addGame(new Game(name, platform, price));
        return "CreatedGame";
    }

    @GetMapping("/allGames")
    public String allGames(Model model) {
        model.addAttribute("games", gameService.getGames());
        return "ShowGames";
    }

    @GetMapping("/game/{id}")
    public String showGame(Model model, @PathVariable int id) {
        model.addAttribute("game", gameService.getGames(id));
        if (gameService.getGames(id) == null) {
            return "error/401";
        }
        return "ShowGame";
    }

    @GetMapping("/deleted/{id}")
    public String deleteGame(@PathVariable int id) {
        gameService.deleteGame(id);
        return "DeletedGame";
    }
    @GetMapping("/update/{id}")
    public String updateGame(Model model, @PathVariable int id){
        model.addAttribute("game", gameService.getGames(id));
        return "updateGame";
    }
    @GetMapping("/updated/{id}")
    public String updated(@RequestParam String name, @RequestParam String platform, @RequestParam float price, @PathVariable int id){
        Game aux=new Game(name, platform, price);
        aux.setId(id);
        gameService.updateGame(id, aux);
        return "updated";
    }
}
