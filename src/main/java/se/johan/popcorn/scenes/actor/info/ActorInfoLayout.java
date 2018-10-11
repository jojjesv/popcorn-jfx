/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.actor.info;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.scenes.layout.LayoutMaker;

/**
 *
 * @author johansvensson
 */
public class ActorInfoLayout implements LayoutMaker {
    public FlowPane root;
    
    private Actor actor;
    private Movie[] movies;

    public ActorInfoLayout(Actor actor, Movie[] movies) {
        this.actor = actor;
        this.movies = movies;
    }

    public Actor getActor() {
        return actor;
    }

    public Movie[] getMovies() {
        return movies;
    }
    
    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        
        scene.setRoot(root);
    }
}
