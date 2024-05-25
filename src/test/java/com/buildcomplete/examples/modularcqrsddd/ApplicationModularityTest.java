package com.buildcomplete.examples.modularcqrsddd;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ApplicationModularityTest {

  private ApplicationModules applicationModules = ApplicationModules.of(Application.class);

  @Test
  void verifyApplicationModularity() {
    applicationModules.verify();
  }

  @Test
  void writeDocumentationSnippets() {
    new Documenter(applicationModules)
        .writeDocumentation();
  }
}
