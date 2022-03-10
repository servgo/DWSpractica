package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class RatingRESTController {

    @Autowired
    GameService gameService;
    @Autowired
    RatingService ratingService;

    @GetMapping("/getRatings/{idGame}")
    public ResponseEntity<Collection<Rating>> showRatings(@PathVariable int idGame) {
        if (!gameService.containsGame(idGame)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(ratingService.getRatings(idGame), HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteRating/{idGame}/{id}")
    public ResponseEntity<Rating> deleteRating(@PathVariable int idGame, @PathVariable int id) {
        if (!gameService.containsGame(idGame)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if (!ratingService.containsRating(idGame, id)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                ratingService.deleteRating(idGame, id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
    }

    @PostMapping("/addRating/{idGame}")
    public ResponseEntity<Rating> addRating(@PathVariable int idGame, @RequestBody Rating rating) {
        if (!gameService.containsGame(idGame)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            ratingService.addRating(idGame, rating);
            return new ResponseEntity<>(rating, HttpStatus.OK);
        }
    }
}
