/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import se.johan.popcorn.movie.MoviePreview;

/**
 * Represents a single movie preview.
 * @author Johan Svensson
 */
public class MoviePreviewView extends Pane {
    private ImageView thumbnail;
    private Label title;
    
    private MoviePreview originalData;

    public MoviePreview getOriginalData() {
        return originalData;
    }

    public MoviePreviewView(MoviePreview data) {
        this.originalData = data;
        
        this.initControls();
    }
    
    private void initControls() {
        MoviePreview data = this.originalData;
        
        title = new Label(data.getTitle());
        thumbnail = new ImageView(data.getImageUri());
    
        ObservableList<Node> children = getChildren();
        children.add(title);
        children.add(thumbnail);
    }
}
