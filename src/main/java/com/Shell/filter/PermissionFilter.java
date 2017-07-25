package com.Shell.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ContainerRequest;

import com.Shell.dao.factory.DAOFactory;
import com.Shell.utils.Token;

import io.jsonwebtoken.Jwts;

public class PermissionFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException 
	{
		
		String path = ((ContainerRequest) requestContext).getPath(true);
		String method = ((ContainerRequest) requestContext).getMethod();
		String jsw = ((ContainerRequest) requestContext).getHeaderString("x-auth-token");
		String group = "";
		String name = "";
		
		if (path.equals("/user") && method.equals("POST"))
		{
			return;
		}
		try {
			group = Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey())).parseClaimsJws(jsw).getBody().getAudience();
			name = Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey())).parseClaimsJws(jsw).getBody().getSubject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(group);
		if (group.equals("root"))
		{
			return;
		}
		if (path.contains("user/new") || path.contains("user") && method.equals("DELETE") || path.contains("user") && method.equals("PUT") || path.contains("group"))
		{
			requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity("permission denied").build());
		}
	}

}
