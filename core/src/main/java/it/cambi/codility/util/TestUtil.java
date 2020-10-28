package it.cambi.codility.util;

public class TestUtil {

  private String os = System.getProperty("os.name", "generic").toLowerCase();

  public String getCarriageReturn() {
    if (os.contains("win")) return "\r\n";
    else return "\n";
  }
}
