package se.johan.popcorn.scenes.main;

import java.io.IOException;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.movie.add.AddMovieScene;
import se.johan.popcorn.utils.AlertUtils;

/**
 * Main GUI.
 *
 * @author Johan Svensson
 */
public class MainScene extends Scene {

    private MainService service = new MainService();
    private MainLayout layout = new MainLayout();
    private MovieSearchQuery.Scope searchQueryScope = MovieSearchQuery.Scope.MOVIES;

    public MainScene() {
        //  Root cannot be null
        super(new Pane());
        layout.makeLayout(this);

        fetchMovies();
        setupListeners();
    }

    void setupListeners() {
        layout.addMovie.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                AddMovieScene.show();
            }
        });

        layout.searchScopeInput.getItems().forEach(new Consumer<MenuItem>() {
            @Override
            public void accept(final MenuItem t) {
                t.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        layout.searchScopeInput.setText(t.getText());
                        onSearchQueryScopeChanged(
                                (MovieSearchQuery.Scope) t.getUserData()
                        );
                    }
                });
            }
        });

        layout.searchInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    //  Perform search
                    fetchMovies(layout.searchInput.getText());
                }
            }
        });
    }

    private void onSearchQueryScopeChanged(MovieSearchQuery.Scope scope) {
        searchQueryScope = scope;
    }

    private void fetchMovies() {
        fetchMovies(null);
    }

    /**
     * Asynchronously fetches and parses movie previews and displays them.
     */
    private void fetchMovies(String search) {
        if (search == null) {
            search = "";
        }

        final String SEARCH = search;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MoviePreview[] result;
                    if (SEARCH.length() > 0) {
                        //  Perform search
                        result = service.fetchMoviePreviews(SEARCH, searchQueryScope);
                    } else {
                        result = service.fetchMoviePreviews();
                    }
                    
                    layout.movies.showMovies(result);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    onFetchMoviesFailed();
                }
            }
        });
    }

    private void onFetchMoviesFailed() {
        Optional<ButtonType> retryResult = AlertUtils.showGenericErrorAllowRetry();

        if (retryResult.get() == ButtonType.OK) {
            //  Retry
            fetchMovies();
        }
    }
}
