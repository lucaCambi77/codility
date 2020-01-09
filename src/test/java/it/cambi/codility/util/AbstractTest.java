package it.cambi.codility.util;

public class AbstractTest {

	private static String unixCarriage = "\n";
	private static String windowsCarriage = "\r\n";
	private static String carriageReturn;

	public enum OSType {
		Windows, MacOS, Linux, Other
	};

	static {

		switch (getOperatingSystemType()) {
		case Windows:

			carriageReturn = windowsCarriage;
			break;

		case Linux:

			carriageReturn = unixCarriage;
			break;

		default:
			break;
		}

	}

	protected static OSType detectedOS;

	public static String getCarriageReturn() {
		return carriageReturn;
	}

	public static OSType getOperatingSystemType() {
		
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

		return detectedOS;
	}
}
