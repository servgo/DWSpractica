package com.example.dwspractica;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RatingService {
    private Map<Long, Map<Long, Rating>>ratings=new ConcurrentHashMap<>();
    private AtomicLong lastId=new AtomicLong();

    public void addRating(long idGame, Rating rating){
        long id=lastId.incrementAndGet();
        rating.setId(id);
        Map<Long, Rating>aux=this.ratings.get(idGame);
        if (aux == null) {
            aux = new ConcurrentHashMap<>();
        }
        aux.put(id, rating);
        this.ratings.put(idGame, aux);
    }
    public void deleteRating(long idGame, long idRating){
        Map<Long, Rating>aux=this.ratings.get(idGame);
        aux.remove(idRating);
        this.ratings.put(idGame, aux);
    }
    public Collection<Rating> getRatings(long idGame){
        Map<Long, Rating>aux=this.ratings.get(idGame);
        if (aux == null) {
            aux = new ConcurrentHashMap<>();
        }
        return aux.values();
    }

    public void updateRating (long idRating){
        this.ratings.put(idRating,ratings.get(idRating));
    }
    public boolean containsRating(long idGame, long id){
        Map<Long, Rating>aux=this.ratings.get(idGame);
        return aux.containsKey(id);
    }
}
