package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class ShoppingCart {

    @Autowired
    GameService gameService;

    private float price = 0;
    private Map<Long, Game> games = new ConcurrentHashMap<>();


    public float getPrecio() {
        return this.price;
    }

    public void addGame(Game game) {
        games.put(game.getId(), game);
        this.price += game.getPrice();
    }

    public void deleteGame(long id) {
        games.remove(id);
        Game aux = gameService.getGames(id);
        this.price -= aux.getPrice();
    }

    public void clearCart() {
        this.games.clear();
        this.price = 0;
    }

    public boolean esVacia() {
        return this.games.isEmpty();
    }

    public Collection<Game> getCart() {
        return this.games.values();
    }
}
