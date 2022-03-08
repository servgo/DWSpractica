package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RequestMapping("/api")

@RestController
public class ShoppingCartRESTController {

    @Autowired
    GameService gameService;
    @Autowired
    ShoppingCart shoppingCart;


    @GetMapping("/shoppingCart")
    public ResponseEntity<Collection<Game>>showAll(){
        return new ResponseEntity<>(shoppingCart.getCart(), HttpStatus.OK);
    }

    @PostMapping("/remove/{idGame}")
    public ResponseEntity<Game>delete(@PathVariable int idGame){
        Game aux=gameService.getGames(idGame);
        if (aux==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            gameService.deleteGame(idGame);
            return new ResponseEntity<>(aux, HttpStatus.OK);
        }
    }
    @ResponseStatus (HttpStatus.OK)
    @PostMapping ("/removeAll")
    public void deleteAll(){
        shoppingCart.clearCart();
    }

}
