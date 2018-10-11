/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import se.johan.popcorn.movie.Movie;

/**
 *
 * @author johansvensson
 */
public class MovieInfoScene extends Scene {
    private MovieInfoLayout layout;

    public MovieInfoScene(Movie movie) {
        super(new Pane());
        
        layout = new MovieInfoLayout(movie);
        layout.makeLayout(this);
    }
}
