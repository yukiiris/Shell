package com.Shell.Jersey.config;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.Shell.Jersey.Impl.ListImpl;

public class JerseyResourceConfig extends ResourceConfig{

	public JerseyResourceConfig()
	{
		packages("com.Shell.Jersey.Impl");
		register(ListImpl.class);
		register(LoggingFilter.class);
		register(JacksonJsonProvider.class);
	}
}
