package org.kadati.rest.messenger.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectparams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InjectParamResource {

	@GET
	@Path("/annotations")
	public String getInjectedParams(@MatrixParam("param") String param, 
									@CookieParam("age") int age,
									@HeaderParam("sid") String sid) {
		
		return "cookie->"+age +" matrix param ->" +param + " header ->"+sid;
	}
	
	@GET
	@Path("/context")
	public String getContextParams(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		
		return "uriInfo->"+uriInfo.getPath() +" headers ->" +headers.getRequestHeaders().toString() ;
	}
}
