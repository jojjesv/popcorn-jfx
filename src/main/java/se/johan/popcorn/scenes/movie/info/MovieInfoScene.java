/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import java.io.IOException;
import java.util.Optional;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.scenes.actor.info.ActorInfoScene;
import se.johan.popcorn.utils.AlertUtils;
import se.johan.popcorn.utils.Utils;

/**
 *
 * @author johansvensson
 */
public class MovieInfoScene extends Scene {

    /**
     * Fetches a movie with a specific ID then shows it.
     */
    public static void show(int id) throws IOException {
        show(MovieInfoService.getInstance().fetchMovie(id));
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

    private EditMode editMode;
    private Movie movie;
    private MovieInfoLayout layout;

    public MovieInfoScene(Movie movie) {
        super(new Pane());

        this.movie = movie;

        editMode = new EditMode();
        layout = new MovieInfoLayout(movie);
        layout.makeLayout(this);
        
        setupListeners();
    }

    /**
     * Sets up listeners for the layout's controls.
     */
    private void setupListeners() {
        layout.editModeEnable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //  Save snapshot
                editMode.setOriginal(movie);
                layout.setEditModeEnabled(true);
            }
        });
        layout.editModeSave.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                saveChanges();
            }
        });
        layout.editModeCancel.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                layout.setEditModeEnabled(false);
            }
        });
        layout.delete.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                promptDelete();
            }
        });
    }

    /**
     * Saves the changes made while in edit mode
     */
    private void saveChanges() {
        Movie derivedMovie;
        
        try {
            derivedMovie = getDerivedMovie();
        } catch (Exception ex){
            ex.printStackTrace();
            onInputValidationFailed();
            return;
        }
        
        try {
            MovieInfoService.getInstance().saveChanges(
                    derivedMovie, editMode
            );
            layout.setEditModeEnabled(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            onSaveChangesFailed();
        }
    }
    
    /**
     * Invoked if the input validation failed while trying to save the modified instance.
     */
    private void onInputValidationFailed() {
        layout.revert(movie);
        AlertUtils.showError("It appears you've entered incorrect values. Check all fields and try again.", "Validation failed");
            layout.setEditModeEnabled(false);
    }
    
    /**
     * @return A new movie with the values from the input fields. Inherits some values from the original, such as ID.
     */
    private Movie getDerivedMovie() {
        Movie original = movie;
        Movie m = new Movie();
    
        //  Inherited
        m.setId(original.getId());
        m.setActors(original.getActors());
        m.setPictureUri(original.getPictureUri());
        m.setYear(original.getYear());
        
        String possessor = layout.possessor.getInputText();
        if ("".equals(possessor)) {
            possessor = null;
        }
        m.setPossessor(possessor); 
        m.setCategories(Utils.splitTrim(
                layout.categories.getInputText(),
                ","
        ));
        m.setRuntime(Integer.parseInt(layout.runtime.getInputText()));
        m.setScore(Double.parseDouble(layout.score.getInputText()));
        m.setYear(Integer.parseInt(layout.year.getInputText()));
        m.setPlot(layout.plot.getInputText());
        
        return m;
    }

    private void onSaveChangesFailed() {
        AlertUtils.showGenericError();
    }

    /**
     * Prompts the user to confirm deleting the current movie.
     */
    private void promptDelete() {
        Alert prompt = new Alert(AlertType.CONFIRMATION);
        prompt.setTitle("Delete Movie");
        prompt.setContentText(String.format(
                "Are you sure you want to delete %s?", movie.getTitle()
        ));
        Optional<ButtonType> result = prompt.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                MovieInfoService.getInstance().deleteMovie(movie.getId());
            } catch (Exception ex) {
                ex.printStackTrace();
                onDeleteFailed();
            }
        }
    }

    /**
     * Invoked if the deleting the current movie failed.
     */
    private void onDeleteFailed() {
        AlertUtils.showGenericError();
    }

    /**
     * Shows the actor page for a specific actor, typically shown when clicked
     * on an actor preview.
     *
     * @param a Actor to show.
     */
    public void showActor(Actor a) {
        try {
            ActorInfoScene.show(a);
        } catch (IOException ex) {
            ex.printStackTrace();
            onShowActorError(ex);
        }
    }

    private void onShowActorError(Exception e) {
        AlertUtils.showGenericError();
    }
}
