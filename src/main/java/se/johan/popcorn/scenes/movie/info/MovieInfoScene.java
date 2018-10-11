/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;

/**
 *
 * @author johansvensson
 */
public class MovieInfoScene extends Scene {

    /**
     * Fetches a movie with a specific ID then shows it.
     *
     * @param movie Movie to associate with the scene.
     */
    public static void show(int movieId) throws IOException {
        show(new Movie(movieId, "Jurassic Path", 2018, movieId, "PG", new Actor[]{
            new Actor(1, "Max Payne", "https://ctvalleybrewing.com/wp-content/uploads/2017/04/avatar-placeholder.png"),
            new Actor(2, "Bruce Willis", "https://ctvalleybrewing.com/wp-content/uploads/2017/04/avatar-placeholder.png"),
            new Actor(3, "John McCain", "https://ctvalleybrewing.com/wp-content/uploads/2017/04/avatar-placeholder.png"),
            new Actor(4, "Owe Boll", "https://ctvalleybrewing.com/wp-content/uploads/2017/04/avatar-placeholder.png")
        }));
    }

    /**
     * Shows a new stage with a new movie info scene as the default scene.
     *
     * @param movie Movie to associate with the scene.
     */
    public static void show(Movie movie) {
        Stage stage = new Stage();
        stage.setTitle(movie.getTitle());
        
        MovieInfoScene scene = new MovieInfoScene(movie);
        stage.setScene(scene);
        
        stage.show();
    }

    private MovieInfoLayout layout;

    public MovieInfoScene(Movie movie) {
        super(new Pane());

        layout = new MovieInfoLayout(movie);
        layout.makeLayout(this);
    }
}
