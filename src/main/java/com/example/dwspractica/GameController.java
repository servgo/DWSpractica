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
    @Autowired
    ShoppingCart shoppingCart;
    @Autowired
    RatingService ratingService;

    @PostConstruct
    public void init() {
        gameService.addGame(new Game("Elden Ring", "PC", 60));
        gameService.addGame(new Game("Mario Kart 8", "Switch", 55));
        gameService.addGame(new Game("The Last Of Us 2", "PlayStation", 70));
        ratingService.addRating(1, new Rating(4, "Juegazo", "Muy bueno"));
        ratingService.addRating(1, new Rating(5, "El mejor juego que he jugado", "Tremendo"));
        ratingService.addRating(2, new Rating(2, "Muy infantil", "Aburrido"));
        ratingService.addRating(2, new Rating(5, "Gran juego", "Muy divertido"));
        ratingService.addRating(3, new Rating(5, "Increible", "Fabulosa historia"));
        ratingService.addRating(3, new Rating(5, "Maravilloso", "10/10"));
    }

    @GetMapping("/addGame")
    public String addGame() {
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

    @GetMapping("/game/{idGame}")
    public String showGame(Model model, @PathVariable int idGame) {
        model.addAttribute("game", gameService.getGames(idGame));
        if (gameService.getGames(idGame) == null) {
            return "error/401";
        }
        return "ShowGame";
    }

    @GetMapping("/deleted/{idGame}")
    public String deleteGame(@PathVariable int idGame) {
        shoppingCart.deleteGame(idGame);
        gameService.deleteGame(idGame);
        return "DeletedGame";
    }

    @GetMapping("/update/{idGame}")
    public String updateGame(Model model, @PathVariable int idGame) {
        model.addAttribute("game", gameService.getGames(idGame));
        return "updateGame";
    }

    @GetMapping("/updated/{idGame}")
    public String updated(@RequestParam String name, @RequestParam String platform, @RequestParam float price, @PathVariable int idGame) {
        Game aux = new Game(name, platform, price);
        aux.setId(idGame);
        gameService.updateGame(idGame, aux);
        shoppingCart.updateCart(aux);
        return "updated";
    }
}
