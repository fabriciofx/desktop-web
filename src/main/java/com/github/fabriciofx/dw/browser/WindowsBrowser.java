package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

public final class WindowsBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return name.toLowerCase().contains("windows");
	}
	
	@Override
	public void open(final URI uri) throws IOException {
		final Process process = Runtime.getRuntime().exec(
			String.format("cmd.exe /C start /wait %s",
				uri.toURL().toString()
			)
		);
		try {
			process.waitFor();
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}		
	}
}
