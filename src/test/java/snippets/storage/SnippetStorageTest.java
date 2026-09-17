package snippets.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import snippets.model.Snippet;

class SnippetStorageTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void saveAndLoad_unicodeAndLineBreaks_retainsFields() throws IOException {
        Snippet snippet = new Snippet("abc", "Finder folder shortcut", "macOS, finder",
                "Press Command + Shift + G.\nPaste the folder path.", LocalDateTime.of(2026, 9, 17, 10, 30));
        SnippetStorage storage = new SnippetStorage(temporaryDirectory.resolve("library.txt"));

        storage.save(List.of(snippet));

        assertEquals(List.of(snippet), storage.load());
    }
}
