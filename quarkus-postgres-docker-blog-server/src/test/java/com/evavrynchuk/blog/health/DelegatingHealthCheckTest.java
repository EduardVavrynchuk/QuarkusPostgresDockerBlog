package com.evavrynchuk.blog.health;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DelegatingHealthCheckTest {

	private class TestHealthCheck extends DelegatingHealthCheck {

		@Override
		protected HealthCheck createDelegate(Config config) {
			return delegate;
		}
		
	}
	
	private HealthCheck delegate;
	private DelegatingHealthCheck healthCheck;
	private HealthCheckResponse response;
	
	@BeforeEach
	public void before() {
		delegate = Mockito.mock(HealthCheck.class);
		healthCheck = new TestHealthCheck();
		response = Mockito.mock(HealthCheckResponse.class);
	}
	
	@Test
	public void testInit() {
		
		healthCheck.init();

		Assertions.assertSame(delegate, healthCheck.getDelegate());		
	}
	
	@Test
	public void testCall() {
		
		healthCheck.setDelegate(delegate);
		
		Mockito
			.when(delegate.call())
			.thenReturn(response);
		
		Assertions.assertSame(response, healthCheck.call());
	}
}
