package snippets.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import snippets.model.Snippet;

/**
 * Owns the snippet collection and persists each change to the local library.
 */
public class SnippetLibrary {
    private final SnippetStorage storage;
    private final List<Snippet> snippets;

    /**
     * Opens the library at the given path, adding examples on the first launch.
     *
     * @param dataFile location of the persistent data file
     * @throws IOException if the library cannot be opened
     */
    public SnippetLibrary(Path dataFile) throws IOException {
        boolean isFirstLaunch = Files.notExists(dataFile);
        storage = new SnippetStorage(dataFile);
        snippets = storage.load();
        if (isFirstLaunch) {
            snippets.addAll(createExamples());
            storage.save(snippets);
        }
    }

    /**
     * Returns the library sorted from newest to oldest.
     *
     * @return a copy of the current library
     */
    public List<Snippet> getAll() {
        return snippets.stream()
                .sorted(Comparator.comparing(Snippet::createdAt).reversed())
                .toList();
    }

    /**
     * Adds a snippet and saves the library.
     *
     * @param title title for the new snippet
     * @param tags optional comma-separated keywords
     * @param content information to retain
     * @return the stored snippet
     * @throws IOException if the updated library cannot be saved
     */
    public Snippet create(String title, String tags, String content) throws IOException {
        Snippet snippet = new Snippet(UUID.randomUUID().toString(), title.trim(), tags.trim(), content.trim(),
                LocalDateTime.now());
        snippets.add(snippet);
        storage.save(snippets);
        return snippet;
    }

    /**
     * Updates an existing snippet and saves the library.
     *
     * @param original snippet being edited
     * @param title replacement title
     * @param tags replacement keywords
     * @param content replacement information
     * @return the updated snippet
     * @throws IOException if the updated library cannot be saved
     */
    public Snippet update(Snippet original, String title, String tags, String content) throws IOException {
        Snippet updated = new Snippet(original.id(), title.trim(), tags.trim(), content.trim(), original.createdAt());
        replace(original, updated);
        storage.save(snippets);
        return updated;
    }

    /**
     * Deletes an existing snippet and saves the library.
     *
     * @param snippet snippet to delete
     * @throws IOException if the updated library cannot be saved
     */
    public void delete(Snippet snippet) throws IOException {
        snippets.removeIf(candidate -> candidate.id().equals(snippet.id()));
        storage.save(snippets);
    }

    /** Replaces one snippet by identifier after preserving its place in the collection. */
    private void replace(Snippet original, Snippet updated) {
        for (int index = 0; index < snippets.size(); index++) {
            if (snippets.get(index).id().equals(original.id())) {
                snippets.set(index, updated);
                return;
            }
        }
        throw new IllegalArgumentException("The snippet no longer exists.");
    }

    /** Provides enough useful content for first-time evaluation of the interface. */
    private List<Snippet> createExamples() {
        LocalDateTime now = LocalDateTime.now();
        List<Snippet> examples = new ArrayList<>();
        examples.add(new Snippet(UUID.randomUUID().toString(), "Open a browser tab in Codex", "codex, shortcut",
                "Use Command + T to open a new browser tab in the current Codex task.", now));
        examples.add(new Snippet(UUID.randomUUID().toString(), "Reveal files in Finder", "macos, finder",
                "In Finder, press Command + Shift + G to open Go to Folder. Paste a path and press Return.",
                now.minusMinutes(1)));
        examples.add(new Snippet(UUID.randomUUID().toString(), "Start a local JavaFX app", "java, javafx, gradle",
                "Select JDK 25, then run ./gradlew run from the project root.", now.minusMinutes(2)));
        return examples;
    }
}
