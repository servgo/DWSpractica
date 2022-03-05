package com.example.dwspractica;

import java.time.LocalDateTime;

public class Game {
    private long id = -1;
    private String name;
    private String platform;
    private float price;

    public Game(String name, String platform, float price) {
        this.name = name;
        this.platform = platform;
        this.price = price;
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
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
