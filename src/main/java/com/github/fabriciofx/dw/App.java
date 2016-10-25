package com.github.fabriciofx.dw;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.github.fabriciofx.dw.browser.Browser;
import com.github.fabriciofx.dw.browser.Browsers;
import com.github.fabriciofx.dw.web.WebServer;
import com.jcabi.manifests.Manifests;

public final class App {
	public static void main(String[] args) throws InterruptedException {
		try {
			final int port = Integer.parseInt(
				Manifests.read("DesktopWeb-Port")
			);
			final Server server = new WebServer(port);
			server.start();
			final Browser browser = new Browsers(server).browser();
			browser.open(
				new URI(
					String.format(
						"http://%s:%d",
						Manifests.read("DesktopWeb-Host"),
						port
					)
				)
			);
		} catch (final IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
