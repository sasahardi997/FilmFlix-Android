package com.example.filmflix.model;

public class Post {

    private String title;
    private String description;
    private String picture;
    private String movieIs;
    private String key;

    public Post(){

    }

    public Post(String title, String description, String picture, String movieIs) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.movieIs = movieIs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMovieIs() {
        return movieIs;
    }

    public void setMovieIs(String movieIs) {
        this.movieIs = movieIs;
    }
}
