package com.github.fabriciofx.dw.browser;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public final class DesktopBrowser implements Browser {
	@Override
	public boolean match(final String name) {
		boolean supported = false;
		if (Desktop.isDesktopSupported()) {
			final Desktop desktop = Desktop.getDesktop();
			supported = desktop.isSupported(Desktop.Action.BROWSE);
		}
		return supported;
	}	
	
	@Override
	public void open(final URI uri) throws IOException {
		try {
			final Desktop desktop = Desktop.getDesktop();
			desktop.browse(uri);
		} catch(final Exception e) {
			throw new IOException(e);
		}
	}
}
