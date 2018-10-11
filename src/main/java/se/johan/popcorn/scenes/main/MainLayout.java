/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.layout.Heading;
import se.johan.popcorn.scenes.layout.LayoutMaker;

/**
 * Sets the layout for the main 
 * @author johansvensson
 */
class MainLayout implements LayoutMaker {
    public AnchorPane root;
    public Label moviesLabel;
    public GridPane movies;
    
    @Override
    public void makeLayout(Scene scene) {
        root = new AnchorPane();
        ObservableList<Node> children = root.getChildren();
        
        scene.setRoot(root);
        
        moviesLabel = new Heading("Movies");
        children.add(moviesLabel);
        
        movies = new GridPane();
        children.add(movies);
        
        moviesLabel = new Heading("Movies");
        children.add(moviesLabel);
    }
    
    /**
     * Replaces the contents of the movies list with views which
     * represents a movie array.
     * @param movies Movies to show.
     */
    public void showMovies(MoviePreview[] movies){
        ObservableList<Node> children = this.movies.getChildren();
        MoviePreviewView[] newChildren = new MoviePreviewView[movies.length];
        for (int i = 0, n = movies.length; i < n; i++) {
            newChildren[i] = new MoviePreviewView(movies[i]);
        }
        
        children.setAll(newChildren);
    }
}
