package com.example.dwspractica;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ShoppingCart {
    private float price=0;
    private List<Game> games= new ArrayList<>();

    public ShoppingCart() {
        this.price = 0;
    }

    public float getPrecio(){
        return this.price;
    }

    public void addGame (Game game){
        games.add(game);
        this.price+=game.getPrice();
    }

    public void deleteGame (Game game){
        games.remove(game);
        this.price-=game.getPrice();
    }

    public void clearCart (){
        this.games.clear();
        this.price=0;
    }

    public boolean esVacia (){
        return this.games.isEmpty();
    }

    public Collection<Game> getCart(){
        return this.games;
    }
}
