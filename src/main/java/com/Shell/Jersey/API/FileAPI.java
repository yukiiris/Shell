package com.Shell.Jersey.API;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.Shell.vo.File;

@Path("/file")
public interface FileAPI {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<File> getAllFile();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean changeMode(@QueryParam("authority")String authority, File file);
	
	@Path("/group")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean changeGroup(File file, @QueryParam("gid")int gid);
}
