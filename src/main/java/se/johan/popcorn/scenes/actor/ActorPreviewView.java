/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.actor;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import se.johan.popcorn.movie.Actor;

/**
 * Previews an actor.
 * @author johansvensson
 */
public class ActorPreviewView extends FlowPane {
    private ImageView thumbnail;
    private Label name;
    private Actor data;

    public ActorPreviewView(Actor data) {
        this.data = data;
        this.initControls();
    }

    private void initControls() {
        setOrientation(Orientation.VERTICAL);
        setColumnHalignment(HPos.CENTER);
        
        ObservableList<Node> children = getChildren();
        
        thumbnail = new ImageView(data.getImageUri());
        children.add(thumbnail);
        
        name = new Label(data.getName());
        name.setPadding(new Insets(8, 0, 0, 0));
        children.add(name);
    }
    
    public Actor getData() {
        return data;
    }
}
