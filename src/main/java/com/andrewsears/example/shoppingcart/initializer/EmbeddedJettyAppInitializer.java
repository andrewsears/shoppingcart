/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.initializer;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.andrewsears.example.shoppingcart.ApplicationConstants;

/**
 * Initializer that loads the embedded Jetty server.
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see https://github.com/bkielczewski/example-spring-mvc-jetty/blob/master/src/main/java/eu/kielczewski/example/initializer/EmbeddedJetty.java
 */
public class EmbeddedJettyAppInitializer {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmbeddedJettyAppInitializer.class);
	
	private final static String CONTEXT_PATH = "/";
	private final static String URL_MAPPING = "/";
	
	public static void main(String[] args) throws Exception {
		new EmbeddedJettyAppInitializer().startJetty( getPortFromArgs(args) );
	}
	
	private static int getPortFromArgs(String[] args) {
		if (args.length > 0) {
			try {
				return Integer.valueOf(args[0]);
			} catch (NumberFormatException ignore) {
				// DO NOTHING; it will return a default anyway
			}
		}
		LOGGER.debug("No server port configured, falling back to {}", ApplicationConstants.APPLICATION_SETUP_DEFAULT_PORT);
		return ApplicationConstants.APPLICATION_SETUP_DEFAULT_PORT;
	}
	
	
	private void startJetty(int port) throws Exception {
		LOGGER.debug("Starting server at port {}", port);
		Server server = new Server(port);
		server.setHandler( getServletContextHandler(getContext()) );
		server.start();
		LOGGER.info("Server started at port {}", port);
		server.join();
	}
	
	private static ServletContextHandler getServletContextHandler(WebApplicationContext context) throws IOException {
		WebAppContext contextHandler = new WebAppContext();
		contextHandler.setErrorHandler(null);
		contextHandler.setContextPath(CONTEXT_PATH);
		contextHandler.addServlet( new ServletHolder(new DispatcherServlet(context)), URL_MAPPING );
		contextHandler.addEventListener(new ContextLoaderListener(context));
		contextHandler.setResourceBase( new ClassPathResource("webapp").getURI().toString() );	// TODO put this hard-coded string somewhere else
		return contextHandler;
	}
	
	// TODO is this the same as WebMvcAppInitializer?
	private static WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(ApplicationConstants.APPLICATION_SETUP_CONFIG_PACKAGE);
		return context;
	}

}
