/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.movie;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.List;

/**
 *
 * @author johansvensson
 */
public class Movie {
    private int id;
    private String title;
    private String plot;
    private int year;
    private int runtime;
    private double score;
    private String ageRating;
    private List<Actor> actors;
    private String pictureUri;
    private String possessor;
    private List<String> categories;

    public String getPossessor() {
        return possessor;
    }

    public void setPossessor(String possessor) {
        this.possessor = possessor;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setPictureUri(String pictureUri) {
        this.pictureUri = pictureUri;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }    
    
    public int getRuntime() {
        return runtime;
    }
    
    public List<String> getCategories() {
        return categories;
    }

    public String getPictureUri() {
        return pictureUri;
    }

    public List<Actor> getActors() {
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

    public String getPlot() {
        return plot;
    }
    
    
}
