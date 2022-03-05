package com.example.dwspractica;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RatingService {
    private Map<Long, Rating>ratings=new ConcurrentHashMap<>();
    private AtomicLong lastId=new AtomicLong();

    public void addRating(long GameId,Rating rating){
        long id=lastId.incrementAndGet();
        rating.setId(id);
        this.ratings.put(id, rating);
    }
    public void deleteRating(long n){
        this.ratings.remove(n);
    }
    public Rating getRatings(long n){
        return this.ratings.get(n);
    }
    public Collection<Rating>getRatings(){
        return this.ratings.values();
    }
    public void updateRating(long id, Rating rating){
        this.ratings.put(id, rating);
    }
}
