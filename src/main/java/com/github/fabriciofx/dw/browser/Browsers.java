package com.github.fabriciofx.dw.browser;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public final class Browsers {
	private final List<Browser> browsers;

	public Browsers(final CountDownLatch cdl) {
		this(
			new SyncBrowser(cdl, new WindowsBrowser()),
			new SyncBrowser(cdl, new LinuxBrowser()),
			new SyncBrowser(cdl, new MacOsBrowser()),
			new SyncBrowser(cdl, new Win32Browser())
		);
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
