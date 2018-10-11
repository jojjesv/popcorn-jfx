package se.johan.popcorn.scenes.main;

import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.johan.popcorn.movie.MoviePreview;

/**
 * Main GUI.
 *
 * @author Johan Svensson
 */
public class MainScene extends Scene {

    private MainLayout layout = new MainLayout();

    public MainScene() {
        //  Root cannot be null
        super(new Pane());
        layout.makeLayout(this);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                fetchMovies();
            }
        }, 500);
    }

    /**
     * Fetcbes and parses movie previews and displays them.
     */
    void fetchMovies() {
        final MoviePreview[] result = new MoviePreview[]{
            new MoviePreview("Guardians of the Galaxy Vol. 2", "https://via.placeholder.com/350x150"),
            new MoviePreview("Guardians of the Galaxy Vol. 12", "https://via.placeholder.com/350x150"),
            new MoviePreview("Guardians of the Galaxy Vol. 10", "https://via.placeholder.com/350x150")
        };
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                layout.showMovies(result);
            }
        });

    }
}
