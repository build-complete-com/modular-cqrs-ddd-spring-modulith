package com.buildcomplete.examples.modularcqrsddd;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@AnalyzeClasses(packages = "com.buildcomplete.examples.modularcqrsddd")
class ApplicationModularityTest {

  private ApplicationModules applicationModules = ApplicationModules.of(Application.class);

  @ArchTest
  ArchRule hexagonalArchitecture = JMoleculesArchitectureRules.ensureHexagonal();

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
