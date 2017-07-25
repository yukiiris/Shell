package com.Shell.Jersey.API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.Shell.vo.Result;

@Path("result")
public interface ResultAPI {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getResultById(@QueryParam("cid") int cid);
	
	@Path("/all")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Result> getAllResult(@QueryParam("uid") int uid);
}
