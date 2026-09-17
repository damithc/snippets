package snippets.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class SnippetTest {
    @Test
    void matches_keywordsAcrossTitleTagsAndContent_returnsTrue() {
        Snippet snippet = new Snippet("id", "Share screen", "teams, meeting",
                "Choose the Share button, then select a window.", LocalDateTime.now());

        assertTrue(snippet.matches("share teams"));
        assertTrue(snippet.matches("SELECT window"));
        assertTrue(snippet.matches(""));
    }

    @Test
    void matches_missingKeyword_returnsFalse() {
        Snippet snippet = new Snippet("id", "Share screen", "teams, meeting",
                "Choose the Share button, then select a window.", LocalDateTime.now());

        assertFalse(snippet.matches("share zoom"));
    }
}
