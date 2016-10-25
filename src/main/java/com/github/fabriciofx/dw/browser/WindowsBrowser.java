package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.fake.util.WaitCommand;

public final class WindowsBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return name.toLowerCase().contains("windows");
	}
	
	@Override
	public void open(final URI uri) throws IOException {
		new WaitCommand(
			String.format("cmd.exe /C start /wait %s",
				uri.toURL().toString()
			)
		).exec();
	}
}
