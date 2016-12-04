package com.github.fabriciofx.dw.web;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.takes.http.Exit;
import org.takes.http.FtBasic;

import com.github.fabriciofx.dw.Server;
import com.jcabi.log.Logger;

public final class WebServer implements Server {
	private final CountDownLatch cdl;
	private final Thread thread; 

	public WebServer(final CountDownLatch cdl, final int port) {
		this.cdl = cdl;
		this.thread = new Thread(
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
									return Thread.interrupted();
								}
							}
						);
					} catch (final IOException e) {
						throw new IllegalArgumentException(e);
					}
				}
			}
		);
	}

	@Override
	public void start() throws IOException {
		Logger.debug(WebServer.class, "Starting webserver... ");
		thread.start();
		cdl.countDown();
		Logger.debug(WebServer.class, "done.");
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(WebServer.class, "Stopping webserver... ");
		thread.interrupt();
		Logger.debug(WebServer.class, "done.");
	}	
}
