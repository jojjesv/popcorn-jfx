/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.info;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.johan.popcorn.movie.Actor;
import se.johan.popcorn.movie.Movie;
import se.johan.popcorn.scenes.actor.ActorPreviewView;
import se.johan.popcorn.scenes.layout.LayoutMaker;

/**
 * Manages the movie info scene layout.
 * @author johansvensson
 */
class MovieInfoLayout implements LayoutMaker {
    public FlowPane root;
    public ImageView poster;
    
    public int actorsColumnCount = 4;
    public GridPane actors;
    
    private Movie movie;

    public MovieInfoLayout(Movie movie) {
        this.movie = movie;
    }
    
    public Movie getMovie() {
        return movie;
    }
    
    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        scene.setRoot(root);
        
        ObservableList<Node> children = root.getChildren();
        
        poster = new ImageView();
        children.add(poster);
        
        actors = new GridPane();
        children.add(actors);
        
        this.addActorsAsChildren();
    }
    
    /**
     * Makes children out of actors and adds them.
     */
    private void addActorsAsChildren(){
        int i = 0;
        for (Actor actor : movie.getActors()){
            ActorPreviewView view = new ActorPreviewView(actor);
            
            actors.add(view, i % actorsColumnCount,
                    Math.floorDiv(i, actorsColumnCount));
            
            final Actor target = actor;
            view.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    
                }
            });
            i++;
        }
    }
}
