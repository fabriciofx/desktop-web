package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.fake.util.WaitCommand;
import com.github.fabriciofx.dw.fake.util.Windows;

public final class Win32Browser implements Browser {
	@Override
	public boolean match(final String name) {
		return !new Windows().is64bit();
	}

	@Override
	public void open(final URI uri) throws IOException {
		new WaitCommand(
			String.format(
				"rundll32 url.dll,FileProtocolHandler %s",
				uri.toURL().toString()
			)
		).exec();
	}
}
