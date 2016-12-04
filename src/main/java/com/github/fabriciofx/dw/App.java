package com.github.fabriciofx.dw;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import com.github.fabriciofx.dw.browser.Browser;
import com.github.fabriciofx.dw.browser.Browsers;
import com.github.fabriciofx.dw.util.Config;
import com.github.fabriciofx.dw.util.ConfigFile;
import com.github.fabriciofx.dw.web.WebServer;
import com.github.fabriciofx.dw.web.WebServerProcess;
import com.jcabi.log.Logger;


public final class App {
	public static void main(String[] args) {
		try {
			final Config config = new ConfigFile("desktop-web.properties");
			final String host = config.read("desktop-web.host");
			final int port = Integer.parseInt(config.read("desktop-web.port"));
			// TODO: remove temporal coupling between server and browser
			final CountDownLatch cdl = new CountDownLatch(1);
			final Server server = new WebServer(cdl,
				new WebServerProcess(port)
			);
			final Browser browser = new Browsers(cdl).browser();
			server.start();
			browser.open(
				new URI(
					String.format(
						"http://%s:%d",
						host,
						port
					)
				)
			);
			server.stop();
		} catch (final IOException | URISyntaxException e) {
			Logger.error(App.class, e.getMessage());
		}
	}
}
