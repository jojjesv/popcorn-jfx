/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.movie;

/**
 *
 * @author johansvensson
 */
public class Movie {
    private int id;
    private String title;
    private int year;
    private double score;
    private String ageRating;
    private Actor[] actors;

    public Movie(int id, String title, int year, double score, String ageRating,
            Actor[] actors) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.score = score;
        this.ageRating = ageRating;
        this.actors = actors;
    }

    public Actor[] getActors() {
        return actors;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public double getScore() {
        return score;
    }

    public String getAgeRating() {
        return ageRating;
    }
}
