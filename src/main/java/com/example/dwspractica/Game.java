package com.example.dwspractica;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
public class Game {
    @Id
    @GeneratedValue
    private long idGame;

    private String name;
    private String platform;
    private float price;

    public static int GamesId=0;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Rating>ratings;

    public Game(String name, String platform, float price) {
        this.name = name;
        this.platform = platform;
        this.price = price;
        Game.GamesId++;
        this.idGame=Game.GamesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getId() {
        return idGame;
    }

    public void setId(long id) {
        this.idGame = id;
    }
}
