/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.actor.info;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.movie.MoviePreview;

/**
 * Scene which displays info about a specific actor.
 * @author johansvensson
 */
public class ActorInfoScene extends Scene {
    /**
     * Fetches actor's movies.
     *
     * @param actorId ID of actor to fetch.
     */
    public static void show(Actor actor) throws IOException {
        show(actor, ActorInfoService.getInstance().fetchMoviePreviews(
                actor.getId()
        ));
    }

    /**
     * Shows a new stage with a new actor info scene.
     */
    public static void show(Actor actor, MoviePreview[] movies) {
        Stage stage = new Stage();
        stage.setTitle(String.format("Movies starring %s", actor.getName()));
        
        ActorInfoScene scene = new ActorInfoScene(actor, movies);
        stage.setScene(scene);
        
        stage.show();
    }
    private ActorInfoLayout layout;
    
    public ActorInfoScene(Actor actor, MoviePreview[] movies) {
        super(new Pane());
        
        layout = new ActorInfoLayout(actor, movies);
        layout.makeLayout(this);
    }
}
