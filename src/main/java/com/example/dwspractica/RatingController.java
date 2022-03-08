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
    @RequestMapping("Game/{id}/Ratings")
    public String showRating(Model model, @PathVariable int id) {
        model.addAttribute("ratings", ratingService.getRatings(id));
        if (gameService.getGames(id) == null) {
            return "error/401";
        } else {
            return "ShowRatings";
        }
    }

    //We'll be redirected to "NewRating" related to a game and it's id
    @GetMapping("/Game/{id}/Ratings/CreateRating")
    public String ratingCreation(Model model, @PathVariable int id) {
        if (gameService.getGames(id) == null) {
            return "error/401";
        }
        model.addAttribute("id", id);
        model.addAttribute("game", gameService.getGames(id));
        return "NewRating";
    }

    //We are able to create ratings, adding a title, comments and a rating with stars
    @GetMapping("/NewRating/{id}")
    public String newRating(@RequestParam String title, @RequestParam String comment, @RequestParam int stars, @PathVariable int id) {
        Rating aux = new Rating(stars, title, comment);
        aux.setId(id);
        ratingService.addRating(id, aux);
        return "CreatedRating";
    }

    @GetMapping("/deleteRating/{id}")
    public String deleteRating(Model model, @PathVariable int id) {
        model.addAttribute("id", id);
        ratingService.deleteRating(id);
        return "DeletedRating";
    }


}
