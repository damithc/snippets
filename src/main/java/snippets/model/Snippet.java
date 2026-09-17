package snippets.model;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

/**
 * One searchable piece of information kept in the user's local library.
 */
public record Snippet(String id, String title, String tags, String content, LocalDateTime createdAt) {
    /**
     * Validates the durable fields that make up a snippet.
     */
    public Snippet {
        Objects.requireNonNull(id);
        Objects.requireNonNull(title);
        Objects.requireNonNull(tags);
        Objects.requireNonNull(content);
        Objects.requireNonNull(createdAt);
    }

    /**
     * Checks whether every word in a query occurs somewhere searchable.
     *
     * @param query words entered by the user
     * @return whether this snippet should be shown for the query
     */
    public boolean matches(String query) {
        String searchableText = (title + " " + tags + " " + content).toLowerCase(Locale.ROOT);
        return query.toLowerCase(Locale.ROOT)
                .trim()
                .isBlank() || query.toLowerCase(Locale.ROOT)
                        .trim()
                        .lines()
                        .flatMap(line -> java.util.Arrays.stream(line.split("\\s+")))
                        .allMatch(searchableText::contains);
    }
}
