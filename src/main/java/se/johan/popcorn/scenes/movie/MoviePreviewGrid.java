/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.movie.info.MovieInfoScene;

/**
 * A grid for displaying clickable movie previews.
 * @author johansvensson
 */
public class MoviePreviewGrid extends GridPane {
    public int columnCount = 3;
    
    /**
     * Replaces the contents of the movies list with views which represents a
     * movie array.
     *
     * @param movies Movies to show.
     */
    public void showMovies(MoviePreview[] movies) {
        ObservableList<Node> children = getChildren();
        children.clear();

        for (int i = 0, n = movies.length; i < n; i++) {
            MoviePreviewView view = new MoviePreviewView(movies[i]);

            this.add(view, i % columnCount,
                    Math.floorDiv(i, columnCount));

            final MoviePreview target = movies[i];
            view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    showMovieInfoScene(target);
                }
            });
        }
    }

    /**
     * Shows a new movie info scene for a specific movie.
     */
    private void showMovieInfoScene(MoviePreview targetMovie) {
        try {
            MovieInfoScene.show(targetMovie.getMovieId());
        } catch (Exception e) {

        }
    }
}
