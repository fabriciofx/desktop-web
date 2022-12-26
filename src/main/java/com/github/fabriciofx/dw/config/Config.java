package com.github.fabriciofx.dw.config;

public interface Config {
    String value(String key);
    void entry(String key, String value);
    boolean exists(String key);
}