/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import se.johan.popcorn.movie.MoviePreview;

/**
 * Represents a single movie preview.
 *
 * @author Johan Svensson
 */
public class MoviePreviewView extends VBox {

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

        setAlignment(Pos.CENTER);
        
        MoviePreview data = this.originalData;

        title = new Label(data.getTitle());
        title.setPadding(new Insets(8, 0, 0, 0));
        title.setAlignment(Pos.CENTER);
        title.setPrefWidth(80);

        String imageUri = data.getImageUri();
        thumbnail = new ImageView(imageUri != null ? imageUri : ("http://www.citypages.com/img/movie-placeholder.gif"));

        int vwidth = 36;
        int vheight = 48;
        Image img = thumbnail.getImage();
        thumbnail.setFitHeight(80);
        thumbnail.setPreserveRatio(true);

        ObservableList<Node> children = getChildren();
        children.add(thumbnail);
        children.add(title);
    }
}
