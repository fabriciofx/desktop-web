package com.github.fabriciofx.dw.fake;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.browser.Browser;

public final class FakeBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return true;
	}

	@Override
	public void open(final URI uri) throws IOException {
		System.out.println(
			String.format(
				"Opening %s... ",
				uri.toURL().toString()
			)
		);
	}
}
