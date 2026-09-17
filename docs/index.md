---
title: Snippets | Your small reference library
---

<section class="snippets-hero">
  <div>
    <h1>Keep the useful <span>bits.</span></h1>
    <p class="snippets-hero__summary">Snippets is a quiet desktop library for the shortcuts, commands, and small discoveries you want to find again—without turning them into a project.</p>
    <a class="snippets-button" href="#getting-started">Start using Snippets</a>
  </div>
  <div class="snippets-hero__art" aria-label="The Snippets index-card icon">
    <img src="images/snippets-icon.png" alt="Three stacked index cards in ivory, blue, and mint with a coral tab." />
  </div>
</section>

<section class="snippets-intro" aria-labelledby="built-for-small-things">
  <h2 id="built-for-small-things">Built for the small things that save time later.</h2>
  <p>When you solve a problem once, it is worth keeping the answer. Snippets gives each useful detail a title, a few keywords, and just enough context to make it useful next time.</p>
</section>

<section class="snippets-steps" aria-label="How Snippets works">
  <article class="snippets-step snippets-step--mint">
    <span class="snippets-step__number">1</span>
    <h3>Capture it</h3>
    <p>Save a shortcut, command, setup step, or small reminder in a few lines.</p>
  </article>
  <article class="snippets-step snippets-step--blue">
    <span class="snippets-step__number">2</span>
    <h3>Find it</h3>
    <p>Search your title, keywords, and note text using the words you remember.</p>
  </article>
  <article class="snippets-step snippets-step--paper">
    <span class="snippets-step__number">3</span>
    <h3>Use it again</h3>
    <p>Open the matching note, act on it, and get back to the task at hand.</p>
  </article>
</section>

## Search without overthinking it

<section class="snippets-library" aria-label="An example Snippets library">
  <div class="snippets-library__list">
    <p>Your library</p>
    <div class="snippets-library__card snippets-library__card--selected">
      <strong>Reveal files in Finder</strong>
      <span>macOS, finder</span>
    </div>
    <div class="snippets-library__card">
      <strong>Share a screen in Teams</strong>
      <span>teams, meeting</span>
    </div>
    <div class="snippets-library__card">
      <strong>Start a local JavaFX app</strong>
      <span>java, javafx, gradle</span>
    </div>
  </div>
  <div class="snippets-library__detail">
    <span class="snippets-library__tag">macOS · Finder</span>
    <h3>Reveal files in Finder</h3>
    <p>Press <code>Command + Shift + G</code> to open Go to Folder. Paste a path and press Return.</p>
  </div>
</section>

Enter a few search words and Snippets keeps only records containing every word. It looks in the title, keywords, and note, so you do not have to remember where you filed the information.

## Getting started

Snippets is a JavaFX desktop app. To run this prototype from its source code, install JDK 25, then run the following from the project root:

```bash
sdk use java 25.0.3.fx-zulu
./gradlew run
```

The first launch provides a few examples to explore. Create a new snippet with **New snippet**, give it a title and keywords, then select **Save**. Edit a selected entry in place, or remove it using **Delete**.

## Kept on your computer

Snippets saves its library locally in `data/snippets.txt`, relative to the folder from which you run the app. The file stays on your computer and is ignored by Git, so personal notes are not added to the repository accidentally.

<aside class="snippets-note">
  <p><strong>This is an early prototype.</strong> It deliberately focuses on the short loop of saving, searching, and revising notes. Features such as folders, pinned records, Markdown formatting, and import/export can come after that loop feels right.</p>
</aside>
