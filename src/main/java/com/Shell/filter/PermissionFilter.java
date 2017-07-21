package com.Shell.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

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
		
		try {
			group = Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey())).parseClaimsJws(jsw).getBody().getAudience();
			name = Jwts.parser().setSigningKey(Token.receiveSecret(DAOFactory.getIKeyDAOInstance().getKey())).parseClaimsJws(jsw).getBody().getSubject();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		if (group.equals("root"))
		{
			return ;
		}
		else if (path.contains("group"))
		{
			//TODO
		}
		
		else if (method.equals("POST"))
		{
			if (path.contains("user"))
			{
				if (path.contains("new"))
				{
					return;
				}
				else
				{
					//TODO
				}
			}
			
		}
	}

}
