package com.Shell.Jersey.API;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.Shell.vo.Command;


@Path("/list")
public interface ListAPI {

	//@Path("/Pending")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addCommand(Command command);
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Command getAllCommand(@QueryParam("uid")int uid);

	
}
