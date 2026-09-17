package snippets.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import snippets.model.Snippet;

/**
 * Reads and writes the small local text file that holds a snippet library.
 */
public class SnippetStorage {
    private static final String FIELD_SEPARATOR = "\t";

    private final Path filePath;

    /**
     * Creates storage at the supplied application-data path.
     *
     * @param filePath location of the local library file
     */
    public SnippetStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads every stored snippet, or an empty list before the file is created.
     *
     * @return the snippets in storage order
     * @throws IOException if the library cannot be read
     */
    public List<Snippet> load() throws IOException {
        if (Files.notExists(filePath)) {
            return new ArrayList<>();
        }

        List<Snippet> snippets = new ArrayList<>();
        for (String line : Files.readAllLines(filePath, StandardCharsets.UTF_8)) {
            if (!line.isBlank()) {
                snippets.add(decode(line));
            }
        }
        return snippets;
    }

    /**
     * Replaces the stored library with the supplied snippets.
     *
     * @param snippets snippets to write
     * @throws IOException if the library cannot be written
     */
    public void save(List<Snippet> snippets) throws IOException {
        Path parent = filePath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        List<String> lines = snippets.stream().map(this::encode).toList();
        Path temporaryFile = filePath.resolveSibling(filePath.getFileName() + ".tmp");
        Files.write(temporaryFile, lines, StandardCharsets.UTF_8);
        moveIntoPlace(temporaryFile);
    }

    /** Moves a complete replacement file into place without risking a partial library file. */
    private void moveIntoPlace(Path temporaryFile) throws IOException {
        try {
            Files.move(temporaryFile, filePath, StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException exception) {
            Files.move(temporaryFile, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /** Encodes fields to one tab-separated, UTF-8-safe line. */
    private String encode(Snippet snippet) {
        return String.join(FIELD_SEPARATOR,
                encodeField(snippet.id()),
                encodeField(snippet.title()),
                encodeField(snippet.tags()),
                encodeField(snippet.content()),
                encodeField(snippet.createdAt().toString()));
    }

    /** Decodes one storage line and rejects files that are not in Snippets' format. */
    private Snippet decode(String line) throws IOException {
        String[] fields = line.split(FIELD_SEPARATOR, -1);
        if (fields.length != 5) {
            throw new IOException("The snippet library contains an invalid record.");
        }
        try {
            return new Snippet(decodeField(fields[0]), decodeField(fields[1]), decodeField(fields[2]),
                    decodeField(fields[3]), LocalDateTime.parse(decodeField(fields[4])));
        } catch (IllegalArgumentException exception) {
            throw new IOException("The snippet library contains an invalid record.", exception);
        }
    }

    /** Base64 avoids ambiguity from tabs and line breaks in user-entered text. */
    private String encodeField(String field) {
        return Base64.getUrlEncoder().encodeToString(field.getBytes(StandardCharsets.UTF_8));
    }

    /** Restores one Base64-encoded text field. */
    private String decodeField(String field) {
        return new String(Base64.getUrlDecoder().decode(field), StandardCharsets.UTF_8);
    }
}
