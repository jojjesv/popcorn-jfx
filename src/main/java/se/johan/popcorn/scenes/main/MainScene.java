package se.johan.popcorn.scenes.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import se.johan.popcorn.movie.Movie;

/**
 * Main GUI.
 * @author Johan Svensson
 */
public class MainScene extends Scene {
    private MainLayout layout = new MainLayout();

    public MainScene() {
        super(null);
        layout.makeLayout(this);
    }
}
