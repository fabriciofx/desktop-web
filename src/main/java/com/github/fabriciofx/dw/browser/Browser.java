package com.github.fabriciofx.dw.browser;

import java.io.IOException;
import java.net.URI;

public interface Browser {
	boolean match(String name);
	
	void open(URI uri) throws IOException;
}
