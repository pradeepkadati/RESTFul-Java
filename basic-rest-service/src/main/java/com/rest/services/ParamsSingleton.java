package com.rest.services;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("{pparam}/params")
/* @Singleton when we have Path,Query and other params at class 
   level we should have the resource at request scope only. if  resource is singleton then
   we should have path , query or other params at method level only */
public class ParamsSingleton {

	@PathParam("pparam")
	private String param;
	@QueryParam("qParam")
	private String qParam;

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String displayParams() {
		
		return "path param is -> " +param +" Query Param is -> "+ qParam;
	}
}
