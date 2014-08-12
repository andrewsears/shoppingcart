/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * TODO Description
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see https://github.com/bkielczewski/example-spring-mvc-jetty/blob/master/src/main/java/eu/kielczewski/example/config/PropertySourcesConfig.java
 */
@Configuration
public class PropertySourcesConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		pspc.setLocations( new ClassPathResource[] {
				//new ClassPathResource("jdbc.properties"),
				//new ClassPathResource("profile-dev.properties"),
			}
		);
		return pspc;
	}

}
