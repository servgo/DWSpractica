package com.example.dwspractica;

import org.springframework.stereotype.Service;

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
    }
}
