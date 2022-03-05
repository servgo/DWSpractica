package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GameRESTController {
    @Autowired
    GameService gameService;
    @PostMapping("/addGame")
    public ResponseEntity<Game>create(@RequestBody Game game){
        gameService.addGame(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }
    @GetMapping("/games")
    public ResponseEntity<Collection<Game>>showAll(){
        return new ResponseEntity<>(gameService.getGames(), HttpStatus.OK);
    }
    @GetMapping("/game/{id}")
    public ResponseEntity<Game>showGame(@PathVariable int id){
        Game aux=gameService.getGames(id);
        if (aux==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(aux, HttpStatus.OK);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Game>delete(@PathVariable int id){
        Game aux=gameService.getGames(id);
        if (aux==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            gameService.deleteGame(id);
            return new ResponseEntity<>(aux, HttpStatus.OK);
        }
    }
}
