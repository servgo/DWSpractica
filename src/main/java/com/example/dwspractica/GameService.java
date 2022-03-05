package com.example.dwspractica;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GameService {
    private Map<Long, Game>games=new ConcurrentHashMap<>();
    private AtomicLong lastId=new AtomicLong();

    public void addGame(Game game){
        long id=lastId.incrementAndGet();
        game.setId(id);
        this.games.put(id, game);
    }
    public void deleteGame(long n){
        this.games.remove(n);
    }
    public Game getGames(long n){
        return this.games.get(n);
    }
    public Collection<Game> getGames(){
        return this.games.values();
    }
    public void updateGame(long id, Game game){
        this.games.put(id, game);
    }
}
