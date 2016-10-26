package com.github.fabriciofx.dw.fake.util;

import java.io.IOException;

import com.jcabi.log.Logger;

public final class WaitCommand {
	private final String command;
	
	public WaitCommand(final String command) {
		this.command = command;
	}
	
	public void exec() throws IOException {
		Logger.debug(WaitCommand.class, "Executing command %s", command);
		final Process process = Runtime.getRuntime().exec(command);
		try {
			process.waitFor();
		} catch (final InterruptedException e) {
			throw new IOException(e);
		}		
	}
}
