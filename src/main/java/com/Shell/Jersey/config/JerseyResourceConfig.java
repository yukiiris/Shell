package com.Shell.Jersey.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.Shell.Jersey.Impl.UserImpl;
import com.Shell.filter.JWTSecurityFilter;

public class JerseyResourceConfig extends ResourceConfig{

	public JerseyResourceConfig()
	{
		register(JWTSecurityFilter.class);
		packages("com.Shell.Jersey.Impl");
		register(UserImpl.class);
		register(LoggingFilter.class);
		register(JacksonJsonProvider.class);
	}
}
