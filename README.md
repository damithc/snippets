# Snippets

![Snippets icon](docs/images/snippets-icon.png)

Snippets is a small desktop library for information you want to find again:
shortcuts, commands, setup notes, and other useful fragments.

This first prototype has a focused workflow:

1. Search titles, tags, and contents using a few keywords.
2. Select a result to read or edit it.
3. Create, save, or delete a snippet.

The app stores its local library in `data/snippets.txt`. That folder is ignored
by Git, so your personal notes are not added to the repository accidentally.
The index-card icon is used in the app header and as the desktop window icon.

## Set up in IntelliJ

Open this folder as a Gradle project, accept the Gradle defaults, then configure
the project SDK to **JDK 25**. Run `snippets.Launcher` or the Gradle `run` task.

## Run from the terminal

```bash
sdk use java 25.0.3.fx-zulu
./gradlew run
```

## Verify the project

```bash
sdk use java 25.0.3.fx-zulu
./gradlew check
```

`check` runs the automated tests and Checkstyle checks. The build follows the
same Java 25, Gradle, JavaFX/FXML, JUnit 5, and Checkstyle stack as Damien.
