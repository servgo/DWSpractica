package com.example.dwspractica;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private float precio;
    private List<Game> games = new ArrayList<>();


    public float getPrecio(){
        float sum = 0;
        for(Game game: games){
            sum+=game.getPrice();
        }
        return sum;
    }

    public void addGame (Game game){
        games.add(game);
    }

    public void deleteGame (Game game){
        games.remove(game);
    }

    public void clearCart (){
        this.games.removeAll();
    }



}
