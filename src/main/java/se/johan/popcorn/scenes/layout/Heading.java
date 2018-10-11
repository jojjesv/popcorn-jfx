/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.johan.popcorn.scenes.layout;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/**
 *
 * @author johansvensson
 */
public class Heading extends Label {
    private static Font defaultFont;
    
    static {
        defaultFont = new Font("Arial", 24.0);
    }
    
    public Heading() {
        this(null);
    }

    public Heading(String text) {
        super(text);
        this.setDerivedStyle();
    }
    
    private void setDerivedStyle() {
        this.setFont(defaultFont);
    }
}
