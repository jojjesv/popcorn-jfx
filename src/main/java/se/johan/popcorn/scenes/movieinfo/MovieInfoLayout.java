/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movieinfo;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import se.johan.popcorn.scenes.layout.LayoutMaker;

/**
 * Manages the movie info scene layout.
 * @author johansvensson
 */
class MovieInfoLayout implements LayoutMaker {
    private FlowPane root;
    public ImageView poster;
    
    @Override
    public void makeLayout(Scene scene) {
        root = new FlowPane();
        scene.setRoot(root);
        
        poster = new ImageView();
        
        ObservableList<Node> children = root.getChildren();
    }
}
