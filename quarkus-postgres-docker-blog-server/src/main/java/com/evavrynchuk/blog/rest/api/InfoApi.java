package com.evavrynchuk.blog.rest.api;

import com.evavrynchuk.blog.config.ConfigProperties;
import java.util.Map;
import java.util.TreeMap;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;

@Path("/info")
public class InfoApi {
	
	static final String PROPERTY_VERSION = "version";
	static final String PROPERTY_BRANCH = "branch";
	static final String PROPERTY_TAG = "tag";
	static final String PROPERTY_COMMIT = "commit";
	static final String PROPERTY_BUILD_TIMESTAMP = "buildTimestamp";

	@Inject
	Config config;

	public InfoApi() {
		super();
	}

	InfoApi(Config config) {
		super();
		this.config = config;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getInfo() {
		
		TreeMap<String, Object> info = new TreeMap<>();

		info.put(PROPERTY_VERSION, config.getValue(ConfigProperties.APP_VERSION, String.class));
		info.put(PROPERTY_BRANCH, config.getValue(ConfigProperties.SCM_BRANCH, String.class));
		info.put(PROPERTY_TAG, config.getOptionalValue(ConfigProperties.SCM_TAG, String.class).orElse(""));
		info.put(PROPERTY_COMMIT, config.getValue(ConfigProperties.SCM_COMMIT, String.class));
		info.put(PROPERTY_BUILD_TIMESTAMP, config.getValue(ConfigProperties.BUILD_TIMESTAMP, String.class));

		return info;
	}
}
