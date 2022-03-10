package com.example.dwspractica;

public class Rating {
    private long id = -1;
    private int stars;
    private String title;
    private String comment;

    public Rating(int stars, String title, String comment) {
        this.stars = stars;
        this.title = title;
        this.comment = comment;
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
}
