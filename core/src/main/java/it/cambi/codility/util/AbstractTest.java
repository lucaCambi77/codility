package it.cambi.codility.util;

public class AbstractTest {

  private static final String CARRIAGE_RETURN;

  public enum OSType {
    WINDOWS,
    MAC_OS,
    LINUX,
    OTHER
  }

  static {
    String unixCarriage = "\n";
    String windowsCarriage = "\r\n";
    if (getOperatingSystemType() == OSType.WINDOWS) {
      CARRIAGE_RETURN = windowsCarriage;
    } else {
      CARRIAGE_RETURN = unixCarriage;
    }
  }

  public static String getCarriageReturn() {
    return CARRIAGE_RETURN;
  }

  public static OSType getOperatingSystemType() {

    String os = System.getProperty("os.name", "generic").toLowerCase();
    if ((os.contains("mac")) || (os.contains("darwin"))) {
      return OSType.MAC_OS;
    } else if (os.contains("win")) {
      return OSType.WINDOWS;
    } else if (os.contains("nux")) {
      return OSType.LINUX;
    } else {
      return OSType.OTHER;
    }
  }
}
