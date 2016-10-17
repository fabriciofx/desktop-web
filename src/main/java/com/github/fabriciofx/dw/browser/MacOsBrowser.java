package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

public final class MacOsBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return name.toLowerCase().contains("mac")
				|| name.toLowerCase().contains("darwin");
	}

	@Override
	public void open(final URI uri) throws IOException {
		Runtime.getRuntime().exec(
			String.format("open %s",
				uri.toURL().toString()
			)
		);
	}
}
