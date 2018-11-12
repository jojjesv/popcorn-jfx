/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.layout;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

/**
 * A text input field with a label.
 *
 * @author johansvensson
 */
public class LabeledTextField extends HBox {
    public static final int labelWidth = 80;
    
    private Label label;
    private TextField input;
    
    public LabeledTextField(String lbl) {
        this.initComponents();
        
        label.setText(lbl);
    }

    /**
     * Initializes the components and adds them to the children list.
     */
    private void initComponents() {
        List<Node> children = getChildren();
        
        label = new Label();
        label.setTextAlignment(TextAlignment.RIGHT);
        label.setAlignment(Pos.CENTER_RIGHT);
        label.setPrefWidth(labelWidth);
        input = new TextField();
        label.setPadding(new Insets(0, 6, 0, 0));
        
        children.add(label);
        children.add(input);
    }
    
    public String getInputText() {
        return input.getText();
    }
    
    public void setInputText(String text) {
        input.setText(text);
    }

    /**
     * @return The underlying text field.
     */
    public TextField getTextField() {
        return input;
    }
}
