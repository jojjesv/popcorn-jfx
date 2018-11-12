/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import java.util.List;
import java.util.function.Consumer;
import se.johan.popcorn.scenes.movie.MoviePreviewView;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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

    Button addMovie;

    FlowPane root;
    Label moviesLabel;
    MoviePreviewGrid movies;

    HBox header;
    HBox searchContainer;

    TextField searchInput;
    MenuButton searchScopeInput;

    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        root.setOrientation(Orientation.VERTICAL);
        root.setPadding(new Insets(24, 24, 24, 24));
        ObservableList<Node> children = root.getChildren();

        searchInput = new TextField();

        searchContainer = new HBox();
        searchContainer.setAlignment(Pos.CENTER_RIGHT);
        searchContainer.getChildren().add(searchInput);

        header = new HBox(16);

        children.add(header);
        List<Node> headerChildren = header.getChildren();

        moviesLabel = new Heading("Movies");
        moviesLabel.setAlignment(Pos.CENTER_LEFT);
        headerChildren.add(moviesLabel);

        addMovie = new Button("Add movie...");
        addMovie.setAlignment(Pos.CENTER_RIGHT);

        headerChildren.add(searchContainer);
        headerChildren.add(addMovie);

        MenuItem[] scopeMenuItems = new MenuItem[]{
            new MenuItem("by name"),
            new MenuItem("by cast"),
            new MenuItem("by genre")
        };
        for (int i = 0, n = scopeMenuItems.length; i < n; i++) {
            scopeMenuItems[i].setUserData(MovieSearchQuery.Scope.values()[i]);
        }
        
        searchScopeInput = new MenuButton();
        searchScopeInput.getItems().addAll(scopeMenuItems);
        searchScopeInput.setText(searchScopeInput.getItems().get(0).getText());
        searchContainer.getChildren().add(searchScopeInput);

        scene.setRoot(root);

        movies = new MoviePreviewGrid();
        movies.setPrefSize(480, 320);
        children.add(movies);
    }
}
