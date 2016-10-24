package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.http.Exit;
import org.takes.http.FtBasic;

import com.github.fabriciofx.dw.Server;

public final class WebServer implements Server {
	private final int port;
	private volatile boolean exit; 

	public WebServer(final int port) {
		this.port = port;
		this.exit = false;
	}

	@Override
	public void start() throws IOException {
		final Thread thread = new Thread(
			new Runnable() {
				@Override
				public void run() {
					try {
						new FtBasic(
							new TkMain(),
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
	}

	@Override
	public void stop() throws IOException {
		this.exit = true;
	}	
}
