package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public final class BrowserTest {
	@Test
	public void open() throws IOException, URISyntaxException {
		final Browser browser = new Browsers().browser();
		browser.open(new URI("https://www.google.com"));
	}
}
