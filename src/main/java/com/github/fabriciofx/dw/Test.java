package com.github.fabriciofx.dw;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test {
	private final Logger logger = LogManager.getLogger(Test.class);

	public Test(String serialPortName) {
		logger.debug("debug! {}", serialPortName);
		logger.info("info! {}", serialPortName);
		logger.warn("warn! {}", serialPortName);
		
		logger.error("error! {}", serialPortName);
		logger.fatal("fatal! {}", serialPortName);
	}

	public static void main(String args[]) {
		new Test("1001");
	}
}