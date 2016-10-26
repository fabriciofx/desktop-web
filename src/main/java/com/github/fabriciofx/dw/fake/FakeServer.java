package com.github.fabriciofx.dw.fake;

import java.io.IOException;

import com.github.fabriciofx.dw.Server;
import com.jcabi.log.Logger;

public final class FakeServer implements Server {
	@Override
	public void start() throws IOException {
		Logger.debug(FakeServer.class, "Starting fakeserver... done.");		
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(FakeServer.class, "Stopping fakeserver... done.");		
	}
}
