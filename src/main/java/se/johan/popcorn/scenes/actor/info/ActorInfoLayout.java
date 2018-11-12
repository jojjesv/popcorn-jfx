/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.actor.info;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;
import se.johan.popcorn.scenes.layout.LayoutMaker;
import se.johan.popcorn.scenes.movie.MoviePreviewGrid;

/**
 *
 * @author johansvensson
 */
public class ActorInfoLayout implements LayoutMaker {
    VBox root;
    VBox personalInfoContainer;
    MoviePreviewGrid movies;
    ImageView picture;
    Label name;
    
    private Actor actor;
    private MoviePreview[] starredMovies;

    public ActorInfoLayout(Actor actor, MoviePreview[] movies) {
        this.actor = actor;
        this.starredMovies = movies;
    }

    public Actor getActor() {
        return actor;
    }

    public MoviePreview[] getStarredMovies() {
        return starredMovies;
    }
    
    @Override
    public void makeLayout(Scene scene) {
        root = new VBox();
        root.setPadding(new Insets(24));
        
        scene.setRoot(root);
        
        ObservableList<Node> children = root.getChildren();
        
        String pictureUri = actor.getImageUri();
        if (pictureUri == null) {
            pictureUri = "https://us.123rf.com/450wm/tuktukdesign/tuktukdesign1606/tuktukdesign160600105/59070189-user-icon-man-profile-businessman-avatar-person-icon-in-vector-illustration.jpg?ver=6";
        }
        picture = new ImageView(new Image(pictureUri));
        picture.setPreserveRatio(true);
        picture.setFitHeight(120);
        
        name = new Label(actor.getName());
        
        personalInfoContainer = new VBox();
        children.add(personalInfoContainer);
        
        personalInfoContainer.getChildren().addAll(picture, name);
        
        movies = new MoviePreviewGrid();
        children.add(movies);
        movies.showMovies(starredMovies);
    }
}
