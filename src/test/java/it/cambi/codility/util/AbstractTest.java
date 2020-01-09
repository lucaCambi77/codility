package it.cambi.codility.util;

public class AbstractTest {

	private static String unixCarriage = "\n";
	private static String windowsCarriage = "\r\n";

	public enum OSType {
		Windows, MacOS, Linux, Other
	};

	// cached result of OS detection
	protected static OSType detectedOS;

	public static String getCarriageReturn() {

		switch (getOperatingSystemType()) {
		case Windows:

			return windowsCarriage;

		case Linux:

			return unixCarriage;

		default:
			break;
		}

		return null;
	}

	public static OSType getOperatingSystemType() {
		if (detectedOS == null) {
			String OS = System.getProperty("os.name", "generic").toLowerCase();
			if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
				detectedOS = OSType.MacOS;
			} else if (OS.indexOf("win") >= 0) {
				detectedOS = OSType.Windows;
			} else if (OS.indexOf("nux") >= 0) {
				detectedOS = OSType.Linux;
			} else {
				detectedOS = OSType.Other;
			}
		}
		return detectedOS;
	}
}
