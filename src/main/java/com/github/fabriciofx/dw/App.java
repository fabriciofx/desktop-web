package com.github.fabriciofx.dw;

import java.io.IOException;

public final class App {
	public static void main(String[] args) {
		try {
			Browser b = new Browser();
			b.open("http://localhost:8080");
			Server s = new WebServer(8080);
			s.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
