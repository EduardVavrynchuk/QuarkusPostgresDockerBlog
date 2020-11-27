package com.evavrynchuk.blog.health;

import com.evavrynchuk.blog.config.ConfigProperties;
import javax.inject.Singleton;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Liveness;

@Singleton
@Liveness
public class SystemLoadHealthCheck extends DelegatingHealthCheck {

	@Override
	protected HealthCheck createDelegate(Config config) {
		int percentage = config.getValue(ConfigProperties.HEALTH_CPU_MAX_PERCENTAGE, int.class);
		return new io.smallrye.health.checks.SystemLoadHealthCheck(percentage);
	}	
}
