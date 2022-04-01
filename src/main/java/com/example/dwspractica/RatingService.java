package com.example.dwspractica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    GameRepository gameRepository;

    private Map<Long, Map<Long, Rating>> ratings = new ConcurrentHashMap<>();
    private AtomicLong lastId = new AtomicLong();

    public void addRating(long idGame, Rating rating) {
        if (gameRepository.existsById(idGame)){
            rating.setGame(gameRepository.getById(idGame));
            ratingRepository.save(rating);
        }
    }

    public void deleteRating(long idGame, long idRating) {
        if (gameRepository.existsById(idGame)){
            if (ratingRepository.existsById(idRating)){
                ratingRepository.deleteById(idRating);
            }
        }
    }

    public Collection<Rating> getRatings(long idGame) {
        if (gameRepository.existsById(idGame)){
            return ratingRepository.findBygame(gameRepository.getById(idGame));
        }else{
            return null;
        }
    }

    public void updateRating(long idRating) {
        this.ratings.put(idRating, ratings.get(idRating));
    }

    public boolean containsRating(long idGame, long id) {
        if(gameRepository.existsById(idGame)){
            return ratingRepository.existsById(id);
        }else{
            return false;
        }
    }
}
