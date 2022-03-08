package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@SessionScope
public class ShoppingCart {

    @Autowired
    GameService gameService;

    private float price = 0;
    private Map<Long, Game> games = new ConcurrentHashMap<>();


    public float getPrecio() {
        return games.values().stream().map(Game::getPrice).reduce(Float::sum).orElse((float) 0);
    }

    public void addGame(Game game) {
        games.put(game.getId(), game);
        this.price += game.getPrice();
    }

    public void deleteGame(long id) {
        Game aux = gameService.getGames(id);
        games.remove(id);
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

    public void updateCart(Game game) {
        long id = game.getId();
        games.put(id, game);
    }
    public boolean containsGame(long id){
        return this.games.containsKey(id);
    }
}
