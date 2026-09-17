---
title: Snippets | Your small reference library
---

<div class="snippets-shell">
  <header class="snippets-nav">
    <a class="snippets-brand" href="#top">
      <img src="images/snippets-icon.png" alt="" />
      <span>Snippets</span>
    </a>
    <nav aria-label="Page sections">
      <a href="#how-it-works">How it works</a>
      <a href="#download">Download</a>
      <a href="https://github.com/damithc/snippets">Source</a>
    </nav>
  </header>

  <main id="top">
    <section class="snippets-showcase" aria-labelledby="snippets-title">
      <figure class="snippets-product-shot">
        <img src="images/snippets-window.png" alt="The Snippets desktop app showing a searchable library on the left and an editable note on the right." />
        <figcaption>The desktop app, with example snippets.</figcaption>
      </figure>
      <div class="snippets-showcase__copy">
        <p class="snippets-version">Version 0.1.0</p>
        <h1 id="snippets-title">A place for tiny answers.</h1>
        <p>Keep the shortcut, command, or setup detail that you will need one day but cannot quite remember. Snippets gives it a title, a few keywords, and a home you can search.</p>
        <a class="snippets-download-button" href="https://github.com/damithc/snippets/releases/download/v0.1.0/snippets.jar">Download Snippets.jar</a>
        <p class="snippets-requirement">Requires Java 25 or later.</p>
      </div>
    </section>

    <section class="snippets-facts" aria-label="What Snippets is for">
      <p><strong>Short by design.</strong> Capture a useful detail in a few lines.</p>
      <p><strong>Searchable everywhere.</strong> Find words in the title, keywords, or note.</p>
      <p><strong>Stored locally.</strong> Your library remains on your computer.</p>
    </section>

    <section class="snippets-workflow" id="how-it-works" aria-labelledby="workflow-title">
      <div class="snippets-workflow__intro">
        <p class="snippets-section-label">A small loop</p>
        <h2 id="workflow-title">Save the answer while it is still fresh.</h2>
      </div>
      <ol>
        <li>
          <span>01</span>
          <div>
            <h3>Capture the useful bit</h3>
            <p>Write down the exact step, shortcut, or command that solved the problem. Add the words that future you might remember.</p>
          </div>
        </li>
        <li>
          <span>02</span>
          <div>
            <h3>Search when you need it</h3>
            <p>Enter a few words. Snippets narrows the library to records containing every one of them, whether they occur in the title, keywords, or note.</p>
          </div>
        </li>
        <li>
          <span>03</span>
          <div>
            <h3>Refine it over time</h3>
            <p>Edit a note when you learn a better way, or delete it once it stops being useful. The library stays focused on the things you actually use.</p>
          </div>
        </li>
      </ol>
    </section>

    <section class="snippets-download" id="download" aria-labelledby="download-title">
      <div>
        <p class="snippets-section-label">Ready when you are</p>
        <h2 id="download-title">Download, then run.</h2>
        <p>Snippets is packaged as one downloadable JAR. You do not need Gradle, IntelliJ IDEA, or the source code to use it.</p>
        <a class="snippets-download-button snippets-download-button--light" href="https://github.com/damithc/snippets/releases/download/v0.1.0/snippets.jar">Get Snippets.jar</a>
        <p class="snippets-release-link"><a href="https://github.com/damithc/snippets/releases/tag/v0.1.0">View the v0.1.0 release notes</a></p>
      </div>
      <div class="snippets-command-card">
        <p>1. Download <code>snippets.jar</code></p>
        <p>2. Open a terminal in its folder</p>
        <p>3. Start the app</p>
        <pre><code>java -jar snippets.jar</code></pre>
      </div>
    </section>

    <section class="snippets-local" aria-labelledby="local-title">
      <img src="images/snippets-icon.png" alt="" />
      <div>
        <h2 id="local-title">Your notes stay close.</h2>
        <p>Snippets saves your library in <code>data/snippets.txt</code>, relative to the folder from which you run the JAR. The file is local and ignored by Git, so your personal notes are not added to the project repository.</p>
      </div>
    </section>
  </main>

  <footer>
    <p>Snippets is an early desktop prototype for remembering the useful little things.</p>
    <a href="https://github.com/damithc/snippets">View the source on GitHub</a>
  </footer>
</div>
