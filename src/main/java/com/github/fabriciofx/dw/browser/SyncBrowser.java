package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

public final class SyncBrowser implements Browser {
	private final CountDownLatch cdl;
	private final Browser browser;

	public SyncBrowser(final CountDownLatch cdl, final Browser browser) {
		this.cdl = cdl;
		this.browser = browser;
	}

	@Override
	public boolean match(final String name) {
		return browser.match(name);
	}

	@Override
	public void open(final URI uri) throws IOException {
		try {
			cdl.await();
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}
		browser.open(uri);
	}
}
