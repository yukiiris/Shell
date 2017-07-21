package com.Shell.Jersey.API;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.Shell.vo.Command;

@Path("/command")
public interface CommandAPI {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addCommand(Command command);
	
	@PUT 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean ajustCommand(Command command);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Command> getAll(@QueryParam("uid")int uid);
}
