package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.fake.util.WaitCommand;

public final class Win32Browser implements Browser {
	@Override
	public boolean match(final String name) {
		return !isWin64bit();
	}

	@Override
	public void open(final URI uri) throws IOException {
		new WaitCommand(
			String.format(
				"rundll32 url.dll,FileProtocolHandler %s",
				uri.toURL().toString()
			)
		).exec();
	}
	
	private boolean isWin64bit() {
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
