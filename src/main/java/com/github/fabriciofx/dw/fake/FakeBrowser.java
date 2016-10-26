package com.github.fabriciofx.dw.fake;

import java.io.IOException;
import java.net.URI;

import com.github.fabriciofx.dw.browser.Browser;
import com.jcabi.log.Logger;

public final class FakeBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		return true;
	}

	@Override
	public void open(final URI uri) throws IOException {
		Logger.debug(FakeBrowser.class,
			"Opening %s... ",
			uri.toURL().toString()
		);
	}
}
