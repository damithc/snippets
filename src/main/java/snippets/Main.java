package snippets;

import java.io.IOException;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import snippets.storage.SnippetLibrary;
import snippets.ui.MainWindow;

/**
 * Creates the application window and connects it to the local snippet library.
 */
public class Main extends Application {
    /** Location of the user's local snippet library, relative to the app folder. */
    private static final Path DATA_FILE = Path.of("data", "snippets.txt");

    /**
     * Builds and shows the main Snippets window.
     *
     * @param stage the primary JavaFX stage
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane root = loader.load();
            MainWindow controller = loader.getController();
            controller.setLibrary(new SnippetLibrary(DATA_FILE));

            stage.setTitle("Snippets");
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/snippets-icon.png")));
            stage.setMinWidth(850);
            stage.setMinHeight(600);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not load the Snippets window.", exception);
        }
    }
}
