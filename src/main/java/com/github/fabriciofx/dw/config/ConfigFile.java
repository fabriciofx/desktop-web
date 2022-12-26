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
package com.github.fabriciofx.dw.config;

import com.github.fabriciofx.dw.Config;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigFile implements Config {
    private final Properties properties;

    public ConfigFile(final String filename) {
        this(ConfigFile.class.getClassLoader().getResourceAsStream(filename));
    }

    public ConfigFile(final InputStream stream) {
        this.properties = new Properties();
        try {
            this.properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String value(final String key) {
        return this.properties.getProperty(key);
    }

    @Override
    public void entry(final String key, final String value) {
        this.properties.setProperty(key, value);
    }

    @Override
    public boolean exists(final String key) {
        return this.properties.containsKey(key);
    }
}