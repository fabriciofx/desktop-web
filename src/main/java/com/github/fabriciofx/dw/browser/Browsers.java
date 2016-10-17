package com.github.fabriciofx.dw.browser;

import java.util.Arrays;
import java.util.List;

public final class Browsers {
	private final List<Browser> browsers;

	public Browsers() {
		this(new DesktopBrowser(), new WindowsBrowser(), new LinuxBrowser(),
				new MacOsBrowser(), new Win32Browser());
	}

	public Browsers(final Browser... browser) {
		this(Arrays.asList(browser));
	}

	public Browsers(final List<Browser> browser) {
		this.browsers = browser;
	}

	public Browser browser() {
		final String name = System.getProperty("os.name", "linux");
		for (final Browser b : browsers) {
			if (b.match(name)) {
				return b;
			}
		}
		throw new IllegalArgumentException("invalid operating system");
	}
}
