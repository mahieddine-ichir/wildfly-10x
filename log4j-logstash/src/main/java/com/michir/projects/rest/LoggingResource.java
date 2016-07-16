package com.michir.projects.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;

@Path("/log")
public class LoggingResource {

	@Inject
	private Logger logger;
	
	@GET
	@Path("/")
	public Response ok() {
		logger.info("ok() resource called ... using CDI");
		return Response.ok().build();
	}
	
	@GET
	@Path("/error")
	public Response error() {
		logger.info("error() resource called ... using CDI");
		Object any = null;
		try {
			if (any == null) {
				throw new NullPointerException("Object is null");
			}
			return Response.ok().build();
		} catch (Exception e) {
			logger.error("could not satisfy query", e);
			return Response.serverError().build();
		}
	}
}
