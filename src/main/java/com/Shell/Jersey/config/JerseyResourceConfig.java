package com.Shell.Jersey.config;


import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.Shell.Jersey.Impl.CommandImpl;
import com.Shell.Jersey.Impl.FileImpl;
import com.Shell.Jersey.Impl.GroupImpl;
import com.Shell.Jersey.Impl.ResultImpl;
import com.Shell.Jersey.Impl.UserImpl;
import com.Shell.filter.JWTSecurityFilter;
import com.Shell.filter.PermissionFilter;

public class JerseyResourceConfig extends ResourceConfig{

	public JerseyResourceConfig()
	{
		register(JWTSecurityFilter.class);
		packages("com.Shell.Jersey.Impl");
		register(CommandImpl.class);
		register(GroupImpl.class);
		register(UserImpl.class);
		register(LoggingFilter.class);
		register(JacksonJsonProvider.class);
		register(ResultImpl.class);
		register(FileImpl.class);
		register(PermissionFilter.class);
	}
}
