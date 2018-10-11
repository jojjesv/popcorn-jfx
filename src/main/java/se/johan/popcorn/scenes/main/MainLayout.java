/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import se.johan.popcorn.scenes.movie.MoviePreviewView;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.layout.Heading;
import se.johan.popcorn.scenes.layout.LayoutMaker;
import se.johan.popcorn.scenes.movie.MoviePreviewGrid;
import se.johan.popcorn.scenes.movie.info.MovieInfoScene;

/**
 * Sets the layout for the main
 *
 * @author johansvensson
 */
class MainLayout implements LayoutMaker {

    public FlowPane root;
    public Label moviesLabel;
    public MoviePreviewGrid movies;

    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        root.setOrientation(Orientation.VERTICAL);
        root.setPadding(new Insets(24, 24, 24, 24));
        ObservableList<Node> children = root.getChildren();

        scene.setRoot(root);

        moviesLabel = new Heading("Movies");
        children.add(moviesLabel);

        movies = new MoviePreviewGrid();
        movies.setPrefSize(480, 320);
        children.add(movies);

        moviesLabel = new Heading("Movies");
        children.add(moviesLabel);
    }
}
