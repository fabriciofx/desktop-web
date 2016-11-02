package com.github.fabriciofx.dw.sync;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.github.fabriciofx.dw.Server;
import com.github.fabriciofx.dw.browser.Browser;

public final class Sync {
	private final Server server;
	private final Browser browser;
	private final CountDownLatch cdl;
	
	public Sync(final Server server, final Browser browser) {
		this.server = server;
		this.browser = browser;
		this.cdl = new CountDownLatch(1);
	}
	
	public Server server() throws IOException {
		return new SyncServer(server, cdl);
	}
	
	public Browser browser() throws IOException {
		return new SyncBrowser(browser, cdl);
	}
}
