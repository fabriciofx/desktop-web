package com.github.fabriciofx.dw.fake.util;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import com.github.fabriciofx.dw.browser.Browser;

public final class SyncBrowser implements Browser {
	private final Browser browser;
	private final CountDownLatch cdl;
	
	public SyncBrowser(final Browser browser, final CountDownLatch cdl) {
		this.browser = browser;
		this.cdl = cdl;
	}

	@Override
	public boolean match(final String name) {
		return true;
	}

	@Override
	public void open(final URI uri) throws IOException {
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					try {
						cdl.await();
						browser.open(uri);
					} catch (final IOException | InterruptedException e) {
						e.printStackTrace();
					}
				}					
			}
		).start();
	}
}
