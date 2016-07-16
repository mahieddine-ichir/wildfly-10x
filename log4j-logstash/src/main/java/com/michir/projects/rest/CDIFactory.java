package com.michir.projects.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CDIFactory {

	@Produces
	@Dependent
	public Logger log(InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass());
	}
	
}
