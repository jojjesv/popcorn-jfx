/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.layout.Heading;
import se.johan.popcorn.scenes.layout.LayoutMaker;
import se.johan.popcorn.scenes.movieinfo.MovieInfoScene;

/**
 * Sets the layout for the main 
 * @author johansvensson
 */
class MainLayout implements LayoutMaker {
    public FlowPane root;
    public Label moviesLabel;
    public GridPane movies;
    public int movieColumnCount = 3;
        
    
    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        root.setOrientation(Orientation.VERTICAL);
        root.setPadding(new Insets(24, 24, 24, 24));
        ObservableList<Node> children = root.getChildren();
        
        scene.setRoot(root);
        
        moviesLabel = new Heading("Movies");
        children.add(moviesLabel);
        
        movies = new GridPane();
        movies.setPrefSize(480, 320);
        children.add(movies);
        
        moviesLabel = new Heading("Movies");
        children.add(moviesLabel);
    }
    
    /**
     * Shows a new movie info scene for a specific movie.
     */
    private void showMovieInfoScene(MoviePreview targetMovie) {
        MovieInfoScene scene = new MovieInfoScene();
        
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * Replaces the contents of the movies list with views which
     * represents a movie array.
     * @param movies Movies to show.
     */
    public void showMovies(MoviePreview[] movies){
        ObservableList<Node> children = this.movies.getChildren();
        children.clear();
        MoviePreviewView[] newChildren = new MoviePreviewView[movies.length];
        
        for (int i = 0, n = movies.length; i < n; i++) {
            MoviePreviewView view = new MoviePreviewView(movies[i]);
            
            this.movies.add(view, i % movieColumnCount,
                    Math.floorDiv(i, movieColumnCount));
        
            final MoviePreview target = movies[i];
            view.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    showMovieInfoScene(target);
                }
            });
        }
    }
}
