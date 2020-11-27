package com.evavrynchuk.blog.health;

import com.evavrynchuk.blog.config.ConfigProperties;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.health.HealthCheck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SystemLoadHealthCheckTest {

	private static final int MAX_PERCENT = 69;
	
	private Config config;
	private SystemLoadHealthCheck healthCheck;
	
	@BeforeEach
	public void before() {
		
		healthCheck = new SystemLoadHealthCheck();
		config = Mockito.mock(Config.class);
		
		Mockito
			.when(config.getValue(ConfigProperties.HEALTH_CPU_MAX_PERCENTAGE, int.class))
			.thenReturn(MAX_PERCENT);
	}
	
	@Test
	public void testCreateDelegate() {
		
		HealthCheck delegate = healthCheck.createDelegate(config);
		
		Assertions.assertNotNull(delegate);
		Assertions.assertSame(io.smallrye.health.checks.SystemLoadHealthCheck.class, delegate.getClass());
	}
}
