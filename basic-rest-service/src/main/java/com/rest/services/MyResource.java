package com.rest.services;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.oes.domain.model.Customer;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
//@Singleton   
public class MyResource {
	// by default every jersey resource will create new instance until 
    // unless we specify the resource as singleton
    private int count;
	
   
	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	count = count +1;
        return "Got it! no of times this method is called -> "+count;
    }
    
  
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/customer")
    public Customer getCustomer() {
        Customer cust = new Customer();
        cust.setId(1);
        cust.setFirstName("pradeep");
    	return cust;
    }
}
