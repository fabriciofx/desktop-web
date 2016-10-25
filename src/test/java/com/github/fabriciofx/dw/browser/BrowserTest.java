package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.github.fabriciofx.dw.fake.FakeBrowser;

public final class BrowserTest {
	@Test
	public void open() throws IOException, URISyntaxException {
		final Browser browser = new Browsers(new FakeBrowser()).browser();
		browser.open(new URI("https://www.google.com"));
	}
}
