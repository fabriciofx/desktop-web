package com.github.fabriciofx.dw.web;

import java.io.IOException;

import org.takes.http.Exit;
import org.takes.http.FtBasic;

public final class WebServerProcess extends Thread {
	private final int port;

	public WebServerProcess(final int port) {
		this.port = port;
	}
	
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
						return Thread.interrupted();
					}
				}
			);
		} catch (final IOException e) {
			throw new IllegalArgumentException(e);
		}		
	}
}
