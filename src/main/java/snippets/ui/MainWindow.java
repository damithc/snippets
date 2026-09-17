package snippets.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import snippets.model.Snippet;
import snippets.storage.SnippetLibrary;

/**
 * Controls search, selection, and editing in Snippets' main window.
 */
public class MainWindow {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<Snippet> snippetList;

    @FXML
    private TextField titleField;

    @FXML
    private TextField tagsField;

    @FXML
    private TextArea contentField;

    @FXML
    private Label resultCountLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Button deleteButton;

    private final ObservableList<Snippet> snippets = FXCollections.observableArrayList();
    private final FilteredList<Snippet> filteredSnippets = new FilteredList<>(snippets);
    private SnippetLibrary library;
    private Snippet selectedSnippet;

    /** Configures search, result cards, and selection handling after FXML injection. */
    @FXML
    public void initialize() {
        snippetList.setItems(filteredSnippets);
        snippetList.setCellFactory(list -> new SnippetCell());
        snippetList.getSelectionModel().selectedItemProperty().addListener((observable, oldSnippet, newSnippet) -> {
            if (newSnippet != null) {
                showSnippet(newSnippet);
            }
        });
        searchField.textProperty().addListener((observable, oldQuery, newQuery) -> filterSnippets(newQuery));
    }

    /**
     * Connects this window to an opened library and selects the first result.
     *
     * @param library local snippet library
     */
    public void setLibrary(SnippetLibrary library) {
        this.library = library;
        refreshSnippets();
        if (!filteredSnippets.isEmpty()) {
            snippetList.getSelectionModel().selectFirst();
        }
    }

    /** Starts a blank snippet without changing the stored library. */
    @FXML
    private void handleNewSnippet() {
        selectedSnippet = null;
        snippetList.getSelectionModel().clearSelection();
        titleField.clear();
        tagsField.clear();
        contentField.clear();
        deleteButton.setDisable(true);
        statusLabel.setText("New snippet — add a title and save when ready.");
        titleField.requestFocus();
    }

    /** Saves either a new snippet or the currently selected one. */
    @FXML
    private void handleSave() {
        if (titleField.getText().isBlank()) {
            statusLabel.setText("A title is required before saving.");
            titleField.requestFocus();
            return;
        }

        try {
            Snippet savedSnippet = selectedSnippet == null
                    ? library.create(titleField.getText(), tagsField.getText(), contentField.getText())
                    : library.update(selectedSnippet, titleField.getText(), tagsField.getText(),
                    contentField.getText());
            refreshSnippets();
            snippetList.getSelectionModel().select(savedSnippet);
            selectedSnippet = savedSnippet;
            statusLabel.setText("Saved locally.");
        } catch (IOException exception) {
            showStorageFailure(exception);
        }
    }

    /** Deletes the selected snippet after a deliberate confirmation. */
    @FXML
    private void handleDelete() {
        if (selectedSnippet == null) {
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete \"" + selectedSnippet.title() + "\"? This cannot be undone.", ButtonType.CANCEL, ButtonType.OK);
        confirmation.setHeaderText("Delete snippet");
        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return;
        }

        try {
            library.delete(selectedSnippet);
            handleNewSnippet();
            refreshSnippets();
            if (!filteredSnippets.isEmpty()) {
                snippetList.getSelectionModel().selectFirst();
            }
            statusLabel.setText("Deleted locally.");
        } catch (IOException exception) {
            showStorageFailure(exception);
        }
    }

    /** Clears the search query and returns the full library. */
    @FXML
    private void handleClearSearch() {
        searchField.clear();
        searchField.requestFocus();
    }

    /** Filters the results using every word in the user's query. */
    private void filterSnippets(String query) {
        filteredSnippets.setPredicate(snippet -> snippet.matches(query));
        updateResultCount();
    }

    /** Repopulates the visual list from the library's durable state. */
    private void refreshSnippets() {
        snippets.setAll(library.getAll());
        filterSnippets(searchField.getText());
    }

    /** Moves the selected snippet's fields into the editor. */
    private void showSnippet(Snippet snippet) {
        selectedSnippet = snippet;
        titleField.setText(snippet.title());
        tagsField.setText(snippet.tags());
        contentField.setText(snippet.content());
        deleteButton.setDisable(false);
        statusLabel.setText("Editing saved snippet.");
    }

    /** Keeps the result count useful during a narrow search. */
    private void updateResultCount() {
        int count = filteredSnippets.size();
        resultCountLabel.setText(count + (count == 1 ? " snippet" : " snippets"));
    }

    /** Presents a clear, non-destructive error if local persistence fails. */
    private void showStorageFailure(IOException exception) {
        statusLabel.setText("Could not save the local library.");
        Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
        alert.setHeaderText("Local library unavailable");
        alert.showAndWait();
    }
}
