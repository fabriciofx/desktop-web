package com.github.fabriciofx.dw.fake;

import java.io.IOException;

import com.github.fabriciofx.dw.Server;

public final class FakeServer implements Server {
	@Override
	public void start() throws IOException {
		System.out.println("Starting fakeserver... done.");		
	}

	@Override
	public void stop() throws IOException {
		System.out.println("Stopping fakeserver... done.");		
	}
}
