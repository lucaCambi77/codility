package it.cambi.codility.pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.cambi.codility.pattern.builder.BuilderClass;

public class BuilderTest {

  @Test
  void shouldBuildClassFromBuilder() {
    BuilderClass builderClass = BuilderClass.builder().id(1).name("name").build();
    assertEquals(new BuilderClass("name", 1), builderClass);
  }
}
