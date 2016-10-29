package com.github.fabriciofx.dw;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.github.fabriciofx.dw.browser.Browser;
import com.github.fabriciofx.dw.browser.Browsers;
import com.github.fabriciofx.dw.util.Config;
import com.github.fabriciofx.dw.util.ConfigFile;
import com.github.fabriciofx.dw.web.WebServer;

public final class App {
	public static void main(String[] args) {
		try {
			final Config config = new ConfigFile("desktop-web.properties");
			final String host = config.read("desktop-web.host");
			final int port = Integer.parseInt(config.read("desktop-web.port"));
			final Server server = new WebServer(port);
			server.start();
			final Browser browser = new Browsers(server).browser();
			browser.open(
				new URI(
					String.format(
						"http://%s:%d",
						host,
						port
					)
				)
			);
		} catch (final IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
