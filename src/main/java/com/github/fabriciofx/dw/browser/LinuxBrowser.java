package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.util.WaitCommand;

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
		for (final String b : BROWSERS) {
			try {
				new WaitCommand(
					String.format("%s %s",
						b,
						uri.toURL().toString()
					)
				).exec();
				break;
			} catch(final IOException e) {
				continue;
			}
		}
	}
}
