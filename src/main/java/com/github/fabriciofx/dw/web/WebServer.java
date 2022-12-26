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
import java.util.concurrent.CountDownLatch;

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
		this.process.start();
        this.cdl.countDown();
		Logger.debug(WebServer.class, "done.");
	}

	@Override
	public void stop() throws IOException {
		Logger.debug(WebServer.class, "Stopping webserver... ");
        this.process.interrupt();
		Logger.debug(WebServer.class, "done.");
	}

    @Override
    public void close() throws IOException {
        this.stop();
    }
}
