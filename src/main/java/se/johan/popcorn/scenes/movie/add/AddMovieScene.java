/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.add;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MovieFormData;
import se.johan.popcorn.scenes.movie.info.MovieInfoScene;
import se.johan.popcorn.utils.AlertUtils;
import se.johan.popcorn.utils.Utils;

/**
 * Scene with a form for adding a movie.
 *
 * @author johansvensson
 */
public class AddMovieScene extends Scene {

    /**
     * Shows a new stage with a new add movie scene as the default scene.
     */
    public static void show() {
        Stage stage = new Stage();
        stage.setTitle("Add movie - Popcorn");

        AddMovieScene scene = new AddMovieScene();
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    private final AddMovieLayout layout = new AddMovieLayout();

    public AddMovieScene() {
        super(new Pane());
        layout.makeLayout(this);

        this.setupListeners();
    }

    private void setupListeners() {
        layout.imdbFetch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fetchImdbMovie();
            }
        });
        layout.submit.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                submit();
            }
        });
    }

    /**
     * Initializes the submit process.
     */
    private void submit() {
        try {
            MovieFormData formData = layout.toFormData();
            AddMovieService.AddMovieResult result =
                    AddMovieService.getInstance().submit(formData);
            this.onSubmit(result);
        } catch (Exception e) {
            e.printStackTrace();
            onSubmitError(e);
        }
    }

    /**
     * Fetches movie data from IMdb using the inputted imdb movie ID.
     */
    private void fetchImdbMovie() {
        String input = layout.imdbId.getInputText().trim();

        try {
            Movie movie = AddMovieService.getInstance().fetchImdbMovie(input);
            populateFields(movie);
        } catch (Exception ex) {
            ex.printStackTrace();
            onFetchImdbMovieFailed(ex);
        }
    }

    private void onFetchImdbMovieFailed(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            //  Invalid IMdb movie ID
            AlertUtils.showError(
                    "The IMdb movie ID you entered appears incorrect.",
                    "Invalid Movie ID"
            );
        } else {
            AlertUtils.showGenericError();
        }
    }

    /**
     * Uses a movie to populate input fields.
     */
    private void populateFields(Movie movie) {
        layout.plot.setInputText(movie.getPlot());
        layout.title.setInputText(movie.getTitle());
        layout.categories.setInputText(
                Utils.join(Utils.map(movie.getCategories(), new Utils.MapCallback<String>() {
                    @Override
                    public String map(String original) {
                        return original.toLowerCase().trim();
                    }
                }))
        );
        layout.score.setInputText(String.valueOf(movie.getScore()));
        layout.runtime.setInputText(String.valueOf(movie.getRuntime()));
        layout.year.setInputText(String.valueOf(movie.getRuntime()));
        layout.ageRating.setInputText(String.valueOf(movie.getAgeRating()));
    }

    private void onSubmitError(Exception e) {
        if (e instanceof IllegalArgumentException) {
            AlertUtils.showError(e.getMessage(), "Validation Error");
        } else {
            AlertUtils.showGenericError();
        }
    }

    /**
     * Called after submitting.
     */
    private void onSubmit(AddMovieService.AddMovieResult result) {
        try {
            MovieInfoScene.show(result.getMovieId());
        } catch (Exception ex) {
            ex.printStackTrace();
            AlertUtils.showError(
                    "The movie was added but can't be opened right now. Try again later.",
                    "Failed to open movie"
            );
        }
    }
}
