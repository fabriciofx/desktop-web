package com.github.fabriciofx.dw.config;

import org.junit.Assert;
import org.junit.Test;

public class ConfigFileTest {
    @Test
    public void hostAndPortValues() {
        final Config config = new ConfigFile("desktop-web.properties");
        Assert.assertEquals("localhost", config.value("desktop-web.host"));
        Assert.assertEquals("8080", config.value("desktop-web.port"));
    }
}
