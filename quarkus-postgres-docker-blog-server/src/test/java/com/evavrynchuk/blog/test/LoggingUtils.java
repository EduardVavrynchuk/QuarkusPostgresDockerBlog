package com.evavrynchuk.blog.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public final class LoggingUtils {

	public static void initializeLogging() {
		try {
			try (InputStream is = LoggingUtils.class.getResourceAsStream("/test-logging.properties")) {
				LogManager.getLogManager().readConfiguration(is);
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private LoggingUtils() {
		super();
	}
}
