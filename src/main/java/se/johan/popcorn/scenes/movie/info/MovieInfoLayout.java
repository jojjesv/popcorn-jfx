/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.scenes.actor.ActorPreviewView;
import se.johan.popcorn.scenes.layout.DefaultRootGridPane;
import se.johan.popcorn.scenes.layout.Heading;
import se.johan.popcorn.scenes.layout.LabeledTextField;
import se.johan.popcorn.scenes.layout.LayoutMaker;
import se.johan.popcorn.utils.Utils;

/**
 * Manages the movie info scene layout.
 *
 * @author johansvensson
 */
class MovieInfoLayout implements LayoutMaker {

    VBox root;

    /**
     * Contains optional controls for the current movie.
     */
    HBox options;
    AnchorPane hero;
    Label title;
    int actorsColumnCount = 4;
    GridPane actors;

    Button editModeEnable;
    Button editModeCancel;
    Button editModeSave;
    Button delete;

    GridPane infoContainer;

    LabeledTextField plot;
    LabeledTextField runtime;
    LabeledTextField year;
    LabeledTextField score;
    LabeledTextField ageRating;
    LabeledTextField categories;
    LabeledTextField possessor;

    private Movie movie;
    private MovieInfoScene scene;

    public MovieInfoLayout(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public void makeLayout(Scene scene) {
        this.scene = (MovieInfoScene) scene;

        root = new VBox();
        scene.setRoot(root);

        ObservableList<Node> children = root.getChildren();

        options = new HBox();
        ObservableList<Node> optionsChildren = options.getChildren();
        children.add(options);

        hero = new AnchorPane();
        hero.setPrefHeight(140);
        hero.prefWidthProperty().bind(root.prefWidthProperty());

        hero.setMinHeight(Region.USE_PREF_SIZE);
        hero.setMaxHeight(Region.USE_PREF_SIZE);

        String pictureUri = movie.getPictureUri();
        hero.setBackground(new Background(
                new BackgroundImage(
                        new Image(pictureUri != null ? pictureUri : "http://www.citypages.com/img/movie-placeholder.gif"),
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.CENTER,
                        new BackgroundSize(100, 100, true, true, false, true)
                )
        ));
        children.add(hero);

        title = new Heading(movie.getTitle());
        title.setStyle("-fx-text-fill: #fafafa;");

        AnchorPane.setLeftAnchor(title, 24.0);
        AnchorPane.setBottomAnchor(title, 16.0);
        hero.getChildren().add(title);

        actors = new GridPane();
        children.add(actors);

        this.addActorsAsChildren();

        editModeEnable = new Button("Edit");
        optionsChildren.add(editModeEnable);

        editModeCancel = new Button("Cancel");
        optionsChildren.add(editModeCancel);

        editModeSave = new Button("Save");
        optionsChildren.add(editModeSave);
        
        delete = new Button("Delete");
        optionsChildren.add(delete);

        infoContainer = new DefaultRootGridPane();
        List<Node> infoContainerChildren = infoContainer.getChildren();
        children.add(infoContainer);

        plot = new LabeledTextField("Plot");
        infoContainerChildren.add(plot);
        GridPane.setConstraints(plot, 0, 0);

        score = new LabeledTextField("Score / 10");
        infoContainerChildren.add(score);
        GridPane.setConstraints(plot, 1, 0);

        ageRating = new LabeledTextField("Age rating");
        infoContainerChildren.add(ageRating);
        GridPane.setConstraints(ageRating, 0, 1);

        categories = new LabeledTextField("Genres");
        infoContainerChildren.add(categories);
        GridPane.setConstraints(categories, 1, 1);

        possessor = new LabeledTextField("Availability");
        infoContainerChildren.add(possessor);
        GridPane.setConstraints(possessor, 0, 2);
        
        runtime = new LabeledTextField("Runtime");
        infoContainerChildren.add(runtime);
        GridPane.setConstraints(runtime, 1, 2);
        
        year = new LabeledTextField("Year");
        infoContainerChildren.add(year);
        GridPane.setConstraints(year, 0, 3);
        
        //  Populate fields
        revert(movie);
        setEditModeEnabled(false);
    }

    /**
     * Makes children out of actors and adds them.
     */
    private void addActorsAsChildren() {
        int i = 0;
        for (Actor actor : movie.getActors()) {
            ActorPreviewView view = new ActorPreviewView(actor);

            actors.add(view, i % actorsColumnCount,
                    Math.floorDiv(i, actorsColumnCount));

            final Actor target = actor;
            view.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    scene.showActor(target);
                }
            });
            i++;
        }
    }

    /**
     * Alters the view state to enable or disable edit mode.
     */
    public void setEditModeEnabled(boolean enabled) {
        editModeCancel.setVisible(enabled);
        editModeSave.setVisible(enabled);
        editModeEnable.setVisible(!enabled);

        LabeledTextField[] editableFields = new LabeledTextField[]{
            plot, runtime, year, score, ageRating, categories
        };

        for (LabeledTextField field : editableFields) {
            field.getTextField().setEditable(enabled);
        }

        String possessorString = movie.getPossessor();
        possessor.setInputText(
                !enabled ? (possessorString != null ? possessorString : "In stock")
                        : possessorString
        );
    }

    /**
     * Reverts the info fields to match another Movie instance.
     */
    public void revert(Movie to) {
        plot.setInputText(to.getPlot());
        ageRating.setInputText(to.getAgeRating());
        score.setInputText(String.valueOf(to.getScore()));
        runtime.setInputText(String.valueOf(to.getRuntime()));
        categories.setInputText(Utils.join(to.getCategories()));
        year.setInputText(String.valueOf(to.getYear()));

        //  Keep up to date with the movie
        this.movie = to;
    }
}
