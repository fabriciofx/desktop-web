package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.fake.util.WaitCommand;

public final class MacOsBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return name.toLowerCase().contains("mac") ||
			name.toLowerCase().contains("darwin");
	}

	@Override
	public void open(final URI uri) throws IOException {
		new WaitCommand(
			String.format("open %s",
				uri.toURL().toString()
			)
		).exec();
	}
}
