package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

public final class WindowsBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return name.toLowerCase().contains("windows");
	}
	
	@Override
	public void open(final URI uri) throws IOException {
		Runtime.getRuntime().exec(
			String.format("cmd.exe /C start %s",
				uri.toURL().toString()
			)
		);
	}
}
