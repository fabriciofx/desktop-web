package com.github.fabriciofx.dw.web;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.github.fabriciofx.dw.Server;
import com.jcabi.log.Logger;

public final class WebServer implements Server {
	private final CountDownLatch cdl;
	private final WebServerProcess process; 

	public WebServer(final CountDownLatch cdl, final WebServerProcess process) {
		this.cdl = cdl;
		this.process = process;
	}

	@Override
	public void start() throws IOException {
		Logger.debug(WebServer.class, "Starting webserver... ");
		process.start();
		cdl.countDown();
		Logger.debug(WebServer.class, "done.");
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(WebServer.class, "Stopping webserver... ");
		process.interrupt();
		Logger.debug(WebServer.class, "done.");
	}	
}
