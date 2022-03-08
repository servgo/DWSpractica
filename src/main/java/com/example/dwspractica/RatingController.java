package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;
    @Autowired
    GameService gameService;

    //We can see a game's ratings. If it doesn't exist, we'll see an error
    @GetMapping("game/{idGame}/ratings")
    public String showRating(Model model, @PathVariable int idGame) {
        model.addAttribute("name", gameService.getGames(idGame).getName());
        model.addAttribute("ratings", ratingService.getRatings(idGame));
        if (gameService.getGames(idGame) == null) {
            return "error/401";
        } else {
            return "ShowRatings";
        }
    }

    //We'll be redirected to "NewRating" related to a game and it's id
    @GetMapping("/game/{idGame}/ratings/createRating")
    public String ratingCreation(Model model, @PathVariable int idGame) {
        if (gameService.getGames(idGame) == null) {
            return "error/401";
        }
        model.addAttribute("id", idGame);
        model.addAttribute("game", gameService.getGames(idGame));
        return "NewRating";
    }

    //We are able to create ratings, adding a title, comments and a rating with stars
    @GetMapping("/game/{idGame}/newRating")
    public String newRating(@RequestParam String title, @RequestParam String comment, @RequestParam int stars, @PathVariable int idGame) {
        Rating aux = new Rating(stars, title, comment);
        ratingService.addRating(idGame, aux);
        return "CreatedRating";
    }

    @GetMapping("/game/{idGame}/deleteRating/{id}")
    public String deleteRating(Model model, @PathVariable int idGame, @PathVariable int id) {
        ratingService.deleteRating(idGame, id);
        return "DeletedRating";
    }

    @GetMapping("/updateRating/{idRating}")
    public String updateGame(Model model, @PathVariable int idRating){
        model.addAttribute("game", gameService.getGames(idRating));
        return "updateRating";
    }
}
