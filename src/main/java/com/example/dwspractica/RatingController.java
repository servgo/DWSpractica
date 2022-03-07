package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RatingController {

    @Autowired
    RatingService ratingService;
    @Autowired
    GameService gameService;

    //We can see a game's ratings. If it doesn't exist, we'll see an error
    @RequestMapping("Game/{id}/Ratings")
    public String showRating(Model model, @PathVariable int id) {
        model.addAttribute("id", id);
        if (gameService.getGames(id) == null) {
            return "error/401";
        }
        if (gameService.getGames(id) == null) {
            model.addAttribute("empty", true);
        } else {
            model.addAttribute("empty", false);
        }
        model.addAttribute("ratings", ratingService.getRatings(id));
        return "ShowRatings";
    }

    //We'll be redirected to "NewRating" related to a game and it's id
    @RequestMapping("/Game/{id}/Ratings/CreateRating")
    public String ratingCreation(Model model, @PathVariable int id) {
        if (gameService.getGames(id) == null) {
            return "error/401";
        }
        model.addAttribute("id", id);
        model.addAttribute("game", gameService.getGames(id));
        return "NewRating";
    }

    //We are able to create ratings, adding a title, comments and a rating with stars
    @RequestMapping("/NewRating")
    public String newRating(Model model, @RequestParam String title, @RequestParam String comment, @RequestParam int stars, @RequestParam int id) {

        Rating aux = new Rating(stars, title, comment);
        ratingService.addRating(id, aux);
        return "CreatedRating.html";
    }

}
