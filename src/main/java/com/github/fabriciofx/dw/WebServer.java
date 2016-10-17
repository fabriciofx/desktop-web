package com.github.fabriciofx.dw;

import java.io.IOException;

import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

public final class WebServer implements Server {
	private final int port;

	public WebServer(final int port) {
		this.port = port;
	}

	@Override
	public void start() throws IOException {
		new FtBasic(
			new TkFork(
				new FkRegex("/", "hello, world!")
			),
			port
		).start(Exit.NEVER);
	}

	@Override
	public void stop() throws IOException {
	}
}
