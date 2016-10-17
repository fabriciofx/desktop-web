package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

public final class LinuxBrowser implements Browser {
	private final static String[] BROWSERS = {
		"xdg-open", "chromium", "google-chrome", "firefox", "mozilla-firefox",
		"mozilla", "konqueror", "netscape", "opera", "midori" };

	@Override
	public boolean match(final String name) {
		return name.toLowerCase().contains("linux");
	}

	@Override
	public void open(final URI uri) throws IOException {
		final Runtime rt = Runtime.getRuntime();
		for (final String b : BROWSERS) {
			rt.exec(
				new String[] { 
					b,
					uri.toURL().toString()
				}
			);
		}
	}
}
