package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;
    @Autowired
    GameService gameService;


    //We can see a game's ratings. If it doesn't exist, we'll see an error
    @GetMapping("game/{idGame}/ratings")
    public String showRating(Model model, @PathVariable int idGame) {
        if (gameService.containsGame(idGame)) {
            model.addAttribute("name", gameService.getGames(idGame).getName());
            model.addAttribute("ratings", ratingService.getRatings(idGame));
            return "ShowRatings";
        } else {
            return "error/404";
        }
    }

    //We'll be redirected to "NewRating" related to a game and it's id
    @GetMapping("/game/{idGame}/ratings/createRating")
    public String ratingCreation(Model model, @PathVariable int idGame) {
        if (gameService.containsGame(idGame)) {
            model.addAttribute("id", idGame);
            model.addAttribute("game", gameService.getGames(idGame));
            return "NewRating";
        }else{
            return "error/404";
        }
    }

    //We are able to create ratings, adding a title, comments and a rating with stars
    @PostMapping("/game/{idGame}/newRating")
    public String newRating(@RequestParam String title, @RequestParam String comment, @RequestParam int stars, @PathVariable int idGame) {
        Rating aux = new Rating(stars, title, comment);
        ratingService.addRating(idGame, aux);
        return "CreatedRating";
    }

    @GetMapping("/game/{idGame}/deleteRating/{id}")
    public String deleteRating(Model model, @PathVariable int idGame, @PathVariable int id) {
        if (gameService.containsGame(idGame)){
            if (ratingService.containsRating(idGame, id)){
                ratingService.deleteRating(idGame, id);
                return "DeletedRating";
            }else{
                return "error/404";
            }
        }else{
            return "error/404";
        }
    }
}
