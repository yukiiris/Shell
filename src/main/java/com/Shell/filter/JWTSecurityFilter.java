package com.Shell.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Path;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ContainerRequest;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.utils.Token;

import io.jsonwebtoken.Jwts;

@Provider
@Priority(Priorities.AUTHENTICATION)//优先级最高
//实现该拦截器借口
//@Provider可以自动注册
public class JWTSecurityFilter implements ContainerRequestFilter{
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException 
	{
		String Path = ((ContainerRequest)containerRequestContext).getPath(true);
		String method = ((ContainerRequest)containerRequestContext).getMethod();

		if (Path.equals("/user") && method.equals("POST"))
		{
			return;
		}
		String jsw = ((ContainerRequest) containerRequestContext).getHeaderString("x-auth-token");
		try
		{
			String name = Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey())).parseClaimsJws(jsw).getBody().getSubject();
			String group = Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey())).parseClaimsJws(jsw).getBody().getAudience();
			if (DAOFactory.getIUserDAOInstance().findUserByName(name))
			{
				System.out.println(group);
				if (group.equals("root"))
				{
					return;
				}
				else
				{
					
				}
			}
			else
			{
				containerRequestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("User must log in first").build());
			}
		}
		catch (Exception e)
		{
			containerRequestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("User must log in first").build());
			e.printStackTrace();
		}
	}
	
}
