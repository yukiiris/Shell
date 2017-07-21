package com.Shell.Jersey.API;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.Shell.vo.Group;

@Path("/group")
public interface GroupAPI {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addGroup(Group group);
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean deleteGroup(Group group);
	
	@Path("/change")
	@POST
	@Consumes()
	@Produces(MediaType.APPLICATION_JSON)
	public boolean changeGroup(Group group);
}