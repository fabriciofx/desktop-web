package com.github.fabriciofx.dw.fake.util;

public final class Windows {
	public boolean is64bit() {
		boolean is64bit = false;
		final String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("windows")) {
			is64bit = System.getenv("ProgramFiles(x86)") != null;
		} else {
			is64bit = System.getProperty("os.arch").indexOf("64") != -1;
		}
		return is64bit;
	}
}
