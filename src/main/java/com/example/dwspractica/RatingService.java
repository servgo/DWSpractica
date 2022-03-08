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
    private Map<Long, List<Rating>>ratings=new ConcurrentHashMap<>();
    private AtomicLong lastId=new AtomicLong();

    public void addRating(long idGame, Rating rating){
        long id=lastId.incrementAndGet();
        rating.setId(id);
        List<Rating>aux= this.ratings.get(idGame);
        if (aux == null) {
            aux = new ArrayList<>();
        }
        aux.add(rating);
        this.ratings.put(id, aux);
    }
    public void deleteRating(long id){
        ratings.remove(id);
    }
    public Collection<Rating> getRatings(long idGame){
        List<Rating>aux=this.ratings.get(idGame);
        if (aux == null) {
            aux = new ArrayList<>();
        }
        return aux;
    }

}
