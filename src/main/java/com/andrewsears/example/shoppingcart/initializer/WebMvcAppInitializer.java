/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.initializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.andrewsears.example.shoppingcart.ApplicationConstants;

/**
 * Initializer that loads the Spring MVC.
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see https://github.com/bkielczewski/example-spring-mvc-initializer/blob/master/src/main/java/eu/kielczewski/example/initializer/AppInitializer.java
 */
public class WebMvcAppInitializer implements WebApplicationInitializer {

	private final static String URL_MAPPING = "/";
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = this.getContext();
		servletContext.addListener( new ContextLoaderListener(context) );
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet( "DispatcherServlet", new DispatcherServlet(context) );
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(URL_MAPPING);
	}
	
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(ApplicationConstants.APPLICATION_SETUP_CONFIG_PACKAGE);
		return context;
	}

}
