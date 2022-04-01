package com.example.dwspractica;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private long id;

    private int stars;
    private String title;
    private String comment;

    @ManyToOne
    //@JoinColumn(name = "id_game")
    private Game game;

    public static int RatingsId=0;

    public Rating(int stars, String title, String comment) {
        this.stars = stars;
        this.title = title;
        this.comment = comment;
        Rating.RatingsId++;
        this.id=Rating.RatingsId;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
