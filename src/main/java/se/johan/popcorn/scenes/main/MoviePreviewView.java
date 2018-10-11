/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.main;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import se.johan.popcorn.movie.MoviePreview;

/**
 * Represents a single movie preview.
 * @author Johan Svensson
 */
public class MoviePreviewView extends FlowPane {
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
        this.setPadding(new Insets(8, 8, 8, 8));
        
        this.setOrientation(Orientation.VERTICAL);
        setColumnHalignment(HPos.CENTER);
        
        MoviePreview data = this.originalData;
        
        title = new Label(data.getTitle());
        title.setPrefWidth(80);
        title.setPadding(new Insets(8, 0, 0, 0));
        title.setAlignment(Pos.CENTER);
        
        thumbnail = new ImageView(data.getImageUri());
        
        int vwidth = 36;
        int vheight = 48;
        Image img = thumbnail.getImage();
        thumbnail.setViewport(new Rectangle2D(
                (img.getWidth() - vwidth) / 2,
                (img.getHeight() - vheight) / 2,
                vwidth, vheight
        ));
    
        ObservableList<Node> children = getChildren();
        children.add(thumbnail);
        children.add(title);
    }
}
