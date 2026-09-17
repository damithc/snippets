# Test plan

## Automated tests

- `SnippetTest` checks keyword matching across title, tags, and content.
- `SnippetStorageTest` checks that saved snippets load back with their fields
  intact.

## Manual GUI checks

1. Start the app. Confirm that example snippets are visible and the first one
   opens in the editor.
2. Enter words from a title, tag, and body in the search field. Confirm that
   matching results remain and unrelated results disappear.
3. Create a snippet, save it, select another result, then select the new one.
   Confirm that the entered title, tags, and content are retained.
4. Restart the app. Confirm that the saved snippet remains available.
5. Delete a selected snippet and confirm the deletion in the dialog. Restart
   the app and confirm that it does not reappear.
6. Confirm that the index-card icon appears beside the Snippets heading and as
   the desktop window icon.
