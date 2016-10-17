package com.github.fabriciofx.dw;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.github.fabriciofx.dw.browser.Browser;
import com.github.fabriciofx.dw.browser.Browsers;

public final class App {
	public static void main(String[] args) {
		try {
			Browser browser = new Browsers().browser();
			browser.open(new URI("http://localhost:8080"));
			Server server = new WebServer(8080);
			server.start();
		} catch (final IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
