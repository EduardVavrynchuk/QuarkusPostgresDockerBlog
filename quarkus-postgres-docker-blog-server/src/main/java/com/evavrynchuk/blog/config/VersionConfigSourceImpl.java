package com.evavrynchuk.blog.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.eclipse.microprofile.config.spi.ConfigSource;

public class VersionConfigSourceImpl implements ConfigSource {
	
	static final String LOCATION = "/META-INF/git.properties";
	
	private static Properties loadProperties() {
		
		Properties properties = new Properties();

		try {
			try (InputStream inputStream = VersionConfigSourceImpl.class.getResourceAsStream(LOCATION)) {
				if (inputStream == null) {
					throw new IllegalStateException("not found: " + LOCATION);
				}
				properties.load(inputStream);
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return properties;
	}
	
	private final Properties properties;

	public VersionConfigSourceImpl() {
		this(loadProperties());
	}

	VersionConfigSourceImpl(Properties properties) {
		super();
		this.properties = properties;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, String> getProperties() {
		return (Map)properties;
	}

	@Override
	public String getValue(String propertyName) {
		return properties.getProperty(propertyName);
	}

	@Override
	public String getName() {
		return LOCATION;
	}

	@Override
	public int getOrdinal() {
		return Integer.MAX_VALUE;
	}
}
