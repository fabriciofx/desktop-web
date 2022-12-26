package com.github.fabriciofx.dw.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.function.Supplier;

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