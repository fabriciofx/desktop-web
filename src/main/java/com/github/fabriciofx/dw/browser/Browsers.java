package com.github.fabriciofx.dw.browser;

import java.util.Arrays;
import java.util.List;

import com.github.fabriciofx.dw.Server;

public final class Browsers {
	private final List<Browser> browsers;

	public Browsers(final Server server) {
		this(
			/*new ServerBrowser(server, new DesktopBrowser()),*/
			new ServerBrowser(server, new WindowsBrowser()),
			new ServerBrowser(server, new LinuxBrowser()),
			new ServerBrowser(server, new MacOsBrowser()),
			new ServerBrowser(server, new Win32Browser())
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
