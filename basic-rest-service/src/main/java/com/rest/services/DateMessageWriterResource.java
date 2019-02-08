package com.rest.services;

import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dateWriter")
public class DateMessageWriterResource {

	
	@GET
	@Produces(value= { MediaType.TEXT_PLAIN,"text/date"})
	public Date getDate() {
		
		return Calendar.getInstance().getTime();
	}
	
}
