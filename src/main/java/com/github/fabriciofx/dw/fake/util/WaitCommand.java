package com.github.fabriciofx.dw.fake.util;

import java.io.IOException;

public final class WaitCommand {
	private final String command;
	
	public WaitCommand(final String command) {
		this.command = command;
	}
	
	public void exec() throws IOException {
		final Process process = Runtime.getRuntime().exec(command);
		try {
			process.waitFor();
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}		
	}
}
