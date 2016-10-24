package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.Server;

public final class ServerBrowser implements Browser {
	private final Server server;
	private final Browser browser;

	public ServerBrowser(final Server server, final Browser browser) {
		this.server = server;
		this.browser = browser;
	}
	
	@Override
	public boolean match(final String name) {
		return browser.match(name);
	}

	@Override
	public void open(final URI uri) throws IOException {
		browser.open(uri);
		server.stop();
	}
}
