package it.cambi.codility.util;

public class AbstractTestUtil {

  private String carriageReturn;

  public enum OSType {
    WINDOWS,
    MAC_OS,
    LINUX,
    OTHER
  }

  public AbstractTestUtil() {
    setCarriageReturn(System.getProperty("os.name", "generic").toLowerCase());
  }

  public void setCarriageReturn(String os) {

    OSType osType = OSType.OTHER;

    if ((os.contains("mac")) || (os.contains("darwin"))) {
      osType = OSType.MAC_OS;
    } else if (os.contains("win")) {
      osType = OSType.WINDOWS;
    } else if (os.contains("nux")) {
      osType = OSType.LINUX;
    }

    String unixCarriage = "\n";
    String windowsCarriage = "\r\n";

    if (osType == OSType.WINDOWS) carriageReturn = windowsCarriage;
    else carriageReturn = unixCarriage;
  }

  public String getCarriageReturn() {
    return carriageReturn;
  }
}
