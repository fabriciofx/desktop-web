package com.github.fabriciofx.dw.fake;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.fabriciofx.dw.Server;
import com.jcabi.log.Logger;

public final class ToscoServer implements Server {
	private final ServerSocket socket;

	public ToscoServer(final int port) throws IOException {
		this(new ServerSocket(port));
	}

	public ToscoServer(final ServerSocket socket) {
		this.socket = socket;
	}

	@Override
	public void start() throws IOException {
		Logger.debug(ToscoServer.class, "Starting toscoserver... ");
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							final Socket client = socket.accept();
							System.out.println("Client: " +
								client.getInetAddress().getHostAddress());
							response(client);
							client.close();
						}
					} catch (final IOException e) {
						throw new IllegalArgumentException(e);
					}
				}				
			}
		).start();
		Logger.debug(ToscoServer.class, "done.");
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(ToscoServer.class, "Stopping toscoserver... ");
		socket.close();
		Logger.debug(ToscoServer.class, "done.");
	}
	
	private static void response(Socket client) throws IOException {
		final PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		out.println("HTTP/1.0 200");
		out.println("Content-type: text/html");
		out.println("Server-name: ToscoServer");
		final String response = "<head><title>WebApp</title></head>" +
				"<body><h1>Welcome to ToscoServer!</h1><body>";
		out.println("Content-length: " + response.length());
		out.println("");
		out.println(response);
		out.flush();
		out.close();
	}
}
