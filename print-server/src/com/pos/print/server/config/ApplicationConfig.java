package com.pos.print.server.config;

import org.glassfish.jersey.server.ResourceConfig;


public class ApplicationConfig extends ResourceConfig{

	public ApplicationConfig() {
		// TODO Auto-generated constructor stub
		register(new MyBinder());
		packages(true,"com.pos.print.server.rest");
	}

}
