package se.johan.popcorn.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Utilities for displaying alerts.
 *
 * @author johansvensson
 */
public abstract class AlertUtils {

    public static void showGenericError() {
        show(AlertType.ERROR, "The action could not be completed. \n"
                + "Ensure your computer's Internet connectivity and try again later.",
                "Action Failed"
        );
    }
    
    /**
     * Shows a generic error message and allows the user to retry.
     * @return 
     */
    public static Optional<ButtonType> showGenericErrorAllowRetry() {
        return prompt("The action could not be completed. \n"
                + "Ensure your computer's Internet connectivity. Do you want to try again?",
                "Action Failed"
        );
    }

    public static void showError(String message, String title) {
        show(AlertType.ERROR, message, title);
    }

    public static void show(AlertType type, String message, String title) {
        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.show();
    }

    /**
     * Shows a confirmation dialog and returns the user's input.
     */
    public static Optional<ButtonType> prompt(String message, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);

        return alert.showAndWait();
    }
}
