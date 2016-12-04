package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.http.Exit;
import org.takes.http.FtBasic;

import com.github.fabriciofx.dw.Server;
import com.jcabi.log.Logger;

public final class WebServer implements Server {
	private final int port;
	private volatile boolean exit; 

	public WebServer(final int port) {
		this.port = port;
		this.exit = false;
	}

	@Override
	public void start() throws IOException {
		Logger.debug(WebServer.class, "Starting webserver... ");
		final Thread thread = new Thread(
			new Runnable() {
				@Override
				public void run() {
					try {
						new FtBasic(
							new TkRoutes(),
							port
						).start(
							new Exit() {
								@Override
								public boolean ready() {
									return exit;
								}
							}
						);
					} catch (final IOException e) {
						throw new IllegalArgumentException(e);
					}
				}
			}
		);
		thread.start();
		Logger.debug(WebServer.class, "done.");
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(WebServer.class, "Stopping webserver... ");
		this.exit = true;
		Logger.debug(WebServer.class, "done.");
	}	
}
