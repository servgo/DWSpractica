package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ShoppingCartRESTController {

    @Autowired
    GameService gameService;
    @Autowired
    ShoppingCart shoppingCart;


    @GetMapping("/shoppingCart")
    public ResponseEntity<Collection<Game>>showAll(){
        return new ResponseEntity<>(shoppingCart.getCart(), HttpStatus.OK);
    }
    @PostMapping("/addToCart/{idGame}")
    public ResponseEntity<Game>addToCart(@PathVariable int idGame){
        if (!gameService.containsGame(idGame)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            shoppingCart.addGame(gameService.getGames(idGame));
            return new ResponseEntity<>(gameService.getGames(idGame), HttpStatus.OK);
        }
    }
    @DeleteMapping("/remove/{idGame}")
    public ResponseEntity<Game>delete(@PathVariable int idGame){
        if (!shoppingCart.containsGame(idGame)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            shoppingCart.deleteGame(idGame);
            return new ResponseEntity<>(gameService.getGames(idGame), HttpStatus.OK);
        }
    }
    @DeleteMapping ("/removeAllCart")
    public ResponseEntity<Game>removeAll(){
        shoppingCart.clearCart();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
