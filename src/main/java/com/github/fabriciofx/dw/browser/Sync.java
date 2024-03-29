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
package com.github.fabriciofx.dw.browser;

import com.github.fabriciofx.dw.Browser;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

public final class Sync implements Browser {
    private final CountDownLatch cdl;
    private final Browser browser;

    public Sync(final CountDownLatch cdl, final Browser browser) {
        this.cdl = cdl;
        this.browser = browser;
    }

    @Override
    public boolean match(final String name) {
        return this.browser.match(name);
    }

    @Override
    public void open(final URI uri) throws IOException {
        try {
            this.cdl.await();
        } catch (final InterruptedException ex) {
            throw new IOException(ex);
        }
        this.browser.open(uri);
    }
}
