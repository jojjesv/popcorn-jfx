package se.johan.popcorn.scenes.layout;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

/**
 * A grid pane with default settings.
 *
 * @author johansvensson
 */
public class DefaultRootGridPane extends GridPane {
    public DefaultRootGridPane() {
        super();
        
        init();
    }
    
    protected void init() {
        setPadding(new Insets(24));
        setHgap(16);
        setVgap(8);
    }
}
