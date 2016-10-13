package com.github.fabriciofx.dw;

import java.io.IOException;

public interface Server {
	void start() throws IOException;

	void stop() throws IOException;
}
