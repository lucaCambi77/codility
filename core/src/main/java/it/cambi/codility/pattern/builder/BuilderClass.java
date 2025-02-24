package it.cambi.codility.pattern.builder;

public record BuilderClass(String name, int id) {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String name;
    private int id;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder id(int id) {
      this.id = id;
      return this;
    }

    public BuilderClass build() {
      return new BuilderClass(this.name, this.id);
    }
  }
}
