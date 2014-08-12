/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.config;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.andrewsears.example.shoppingcart.formatter.ProductFormatter;
import com.andrewsears.example.shoppingcart.formatter.ShoppingCartFormatter;

/**
 * TODO Description
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see https://github.com/bkielczewski/example-spring-mvc-initializer/blob/master/src/main/java/eu/kielczewski/example/config/WebMvcConfig.java
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);
	
	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	// https://github.com/bkielczewski/example-spring-mvc-jetty/blob/master/src/main/java/eu/kielczewski/example/config/WebMvcConfig.java
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("images/**").addResourceLocations("images/");
		// XXX Does this works: registry.addResourceHandler("WEB-INF/**").addResourceLocations("classpath:webapp/WEB-INF/");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		
		// Lazy initialized objects
		// http://zeroturnaround.com/rebellabs/your-next-java-web-app-less-xml-no-long-restarts-fewer-hassles-part-2/
		OpenEntityManagerInViewInterceptor interceptor = new OpenEntityManagerInViewInterceptor();
		interceptor.setEntityManagerFactory( this.entityManagerFactory.getObject() );
		registry.addWebRequestInterceptor( interceptor );
	}
	
	/*
	 * TODO Figure out how to use generic within ConversionService instead of the formatters below 
	@Bean
	@Autowired
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DomainClassConverter domainClassConverter(ConversionService conversionService) {
		LOGGER.debug("Configuring DomainClassConverter with ConversionService");
		return new DomainClassConverter(conversionService);
	}
	*/
	
	/*
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter( new ProductFormatter() );
	}
	*/
	
	@SuppressWarnings("rawtypes")
	@Bean
	public FormattingConversionServiceFactoryBean conversionService() {
		FormattingConversionServiceFactoryBean conversionService = new FormattingConversionServiceFactoryBean();
		Set<Formatter> formatters = new HashSet<Formatter>();
		formatters.add( new ProductFormatter() );
		formatters.add( new ShoppingCartFormatter() );
		conversionService.setFormatters(formatters);
		return conversionService;
	}
	
}
