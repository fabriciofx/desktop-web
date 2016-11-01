package com.github.fabriciofx.dw.fake.util;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import com.github.fabriciofx.dw.Server;

public final class SyncServer implements Server {
	private final Server server;
	private final CountDownLatch cdl;
	
	public SyncServer(final Server server, final CountDownLatch cdl) {
		this.server = server;
		this.cdl = cdl;
	}
	
	@Override
	public void start() throws IOException {
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					try {
						server.start();
						cdl.countDown();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}					
			}
		).start();
	}

	@Override
	public void stop() throws IOException {
		server.stop();
	}
}
