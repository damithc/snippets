package snippets.ui;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import snippets.model.Snippet;

/**
 * Renders a compact, scannable snippet result in the library list.
 */
public class SnippetCell extends ListCell<Snippet> {
    /**
     * Updates a result cell when JavaFX reuses it for a different snippet.
     *
     * @param snippet snippet to display
     * @param empty whether the cell has no item
     */
    @Override
    protected void updateItem(Snippet snippet, boolean empty) {
        super.updateItem(snippet, empty);
        if (empty || snippet == null) {
            setText(null);
            setGraphic(null);
            return;
        }

        Label title = new Label(snippet.title());
        title.getStyleClass().add("snippet-card-title");
        title.setWrapText(true);

        Label tags = new Label(snippet.tags().isBlank() ? "UNTAGGED" : snippet.tags().toUpperCase());
        tags.getStyleClass().add("snippet-card-tags");

        Label preview = new Label(createPreview(snippet.content()));
        preview.getStyleClass().add("snippet-card-preview");
        preview.setWrapText(true);

        VBox content = new VBox(5, title, tags, preview);
        content.getStyleClass().add("snippet-card");
        setText(null);
        setGraphic(content);
    }

    /** Keeps a result card compact even when the stored snippet is long. */
    private String createPreview(String content) {
        String singleLineContent = content.replaceAll("\\s+", " ").trim();
        return singleLineContent.length() <= 88 ? singleLineContent : singleLineContent.substring(0, 85) + "...";
    }
}
