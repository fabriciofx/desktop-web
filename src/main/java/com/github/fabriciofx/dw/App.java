package com.github.fabriciofx.dw;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.github.fabriciofx.dw.browser.Browser;
import com.github.fabriciofx.dw.browser.Browsers;
import com.github.fabriciofx.dw.web.WebServer;

public final class App {
	public static void main(String[] args) throws InterruptedException {
		try {
			final Server server = new WebServer(8080);
			server.start();
			final Browser browser = new Browsers(server).browser();
			browser.open(new URI("http://localhost:8080"));
		} catch (final IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
