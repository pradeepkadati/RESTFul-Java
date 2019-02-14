package com.rest.services;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class LoggingFilter implements ContainerRequestFilter,ContainerResponseFilter {
	
	/**
	 * This method is for response filter
	 */
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getHeaders().add("powered-by", "Jersey");
		System.out.println(responseContext.getHeaders());
		
	}
	
	/**
	 * This method is for request filter
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println(requestContext.getHeaders());
		
	}

}
