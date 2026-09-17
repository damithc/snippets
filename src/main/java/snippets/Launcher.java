package snippets;

import javafx.application.Application;

/**
 * Starts the application without making JavaFX launcher-class detection part
 * of the user's run configuration.
 */
public class Launcher {
    /**
     * Launches the Snippets JavaFX application.
     *
     * @param args command-line arguments passed to JavaFX
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
