/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.movie.add;

import java.util.List;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import se.johan.popcorn.movie.MovieFormData;
import se.johan.popcorn.scenes.layout.DefaultRootGridPane;
import se.johan.popcorn.scenes.layout.Heading;
import se.johan.popcorn.scenes.layout.LabeledTextField;
import se.johan.popcorn.scenes.layout.LayoutMaker;
import se.johan.popcorn.utils.Utils;

/**
 *
 * @author johansvensson
 */
public class AddMovieLayout implements LayoutMaker {

    VBox root;
    GridPane formRoot;
    LabeledTextField imdbId;
    Button imdbFetch;
    
    Label header;
    
    HBox imdbIdContainer;
    LabeledTextField title;
    LabeledTextField ageRating;
    LabeledTextField categories;
    LabeledTextField runtime;
    LabeledTextField plot;
    LabeledTextField year;
    LabeledTextField score;
    Button submit;

    @Override
    public void makeLayout(Scene scene) {
        root = new VBox();
        List<Node> children = root.getChildren();
        
        scene.setRoot(root);
        
        formRoot = new DefaultRootGridPane();
        List<Node> formChildren = formRoot.getChildren();
        children.add(formRoot);
        
        header = new Heading("Add Movie");
        formChildren.add(header);
        GridPane.setConstraints(header, 0, 0);

        imdbIdContainer = new HBox();
        formChildren.add(imdbIdContainer);
        GridPane.setConstraints(imdbIdContainer, 0, 1);
        
        imdbId = new LabeledTextField("IMdb ID");
        imdbIdContainer.getChildren().add(imdbId);
        
        imdbFetch = new Button("Get movie");
        imdbIdContainer.getChildren().add(imdbFetch);

        title = new LabeledTextField("Title");
        formChildren.add(title);
        GridPane.setConstraints(title, 1, 1);

        ageRating = new LabeledTextField("Age rating");
        formChildren.add(ageRating);
        GridPane.setConstraints(ageRating, 0, 2);

        categories = new LabeledTextField("Categories");
        formChildren.add(categories);
        GridPane.setConstraints(categories, 1, 2);

        runtime = new LabeledTextField("Runtime");
        formChildren.add(runtime);
        GridPane.setConstraints(runtime, 0, 3);

        plot = new LabeledTextField("Plot");
        formChildren.add(plot);
        GridPane.setConstraints(plot, 1, 3);

        year = new LabeledTextField("Year");
        formChildren.add(year);
        GridPane.setConstraints(year, 0, 4);

        score = new LabeledTextField("Score");
        formChildren.add(score);
        GridPane.setConstraints(score, 1, 4);
        
        submit = new Button("Add movie");
        formChildren.add(submit);
        GridPane.setConstraints(submit, 1, 5, 2, 1, HPos.RIGHT, VPos.BOTTOM);
    }

    /**
     * @return Form data from input.
     */
    MovieFormData toFormData() {
        MovieFormData data = new MovieFormData();

        data.setTitle(title.getInputText().trim());
        data.setAgeRating(ageRating.getInputText().trim());
        data.setPlot(plot.getInputText().trim());
        data.setRuntime(Integer.parseInt(year.getInputText().trim()));
        data.setYear(Integer.parseInt(year.getInputText().trim()));

        data.setCategories(Utils.map(
                categories.getInputText().split(","),
                    new Utils.MapCallback<String>() {
                        @Override
                        public String map(String original) {
                            return original.trim();
                        }
                    }
                )
        );
        
        return data;
    }
}
