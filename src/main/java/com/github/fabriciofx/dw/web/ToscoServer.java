/*
 * The MIT License (MIT)
 *
 * Copyright (C) 2016-2022 Fabrício Barros Cabral
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.fabriciofx.dw.web;

import com.github.fabriciofx.dw.Server;
import com.jcabi.log.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
            () -> {
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
        ).start();
		Logger.debug(ToscoServer.class, "done.");
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(ToscoServer.class, "Stopping toscoserver... ");
        this.socket.close();
		Logger.debug(ToscoServer.class, "done.");
	}

    @Override
    public void close() throws IOException {
        this.stop();
    }

    private static void response(final Socket client) throws IOException {
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
