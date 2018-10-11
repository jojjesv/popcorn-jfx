/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.actor.info;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.layout.LayoutMaker;
import se.johan.popcorn.scenes.movie.MoviePreviewGrid;

/**
 *
 * @author johansvensson
 */
public class ActorInfoLayout implements LayoutMaker {
    public FlowPane root;
    public MoviePreviewGrid movies;
    
    private Actor actor;
    private MoviePreview[] starredMovies;

    public ActorInfoLayout(Actor actor, MoviePreview[] movies) {
        this.actor = actor;
        this.starredMovies = movies;
    }

    public Actor getActor() {
        return actor;
    }

    public Movie[] getMovies() {
        return starredMovies;
    }
    
    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        root.setPadding(new Insets(24));
        root.setOrientation(Orientation.VERTICAL);
        
        scene.setRoot(root);
        
        ObservableList<Node> children = root.getChildren();
        
        movies = new MoviePreviewGrid();
        children.add(movies);
        movies.showMovies(starredMovies);
    }
}
