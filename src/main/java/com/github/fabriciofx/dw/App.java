/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2016-2022 Fabrício Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.dw;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import com.github.fabriciofx.dw.browser.Browser;
import com.github.fabriciofx.dw.browser.Browsers;
import com.github.fabriciofx.dw.config.Config;
import com.github.fabriciofx.dw.config.ConfigFile;
import com.github.fabriciofx.dw.web.WebServer;
import com.github.fabriciofx.dw.web.WebServerProcess;
import com.jcabi.log.Logger;


public final class App {
	public static void main(String[] args) {
		try {
			final Config config = new ConfigFile("desktop-web.properties");
			final String host = config.value("desktop-web.host");
			final int port = Integer.parseInt(config.value("desktop-web.port"));
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
