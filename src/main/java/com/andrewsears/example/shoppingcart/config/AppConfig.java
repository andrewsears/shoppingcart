/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.andrewsears.example.shoppingcart.ApplicationConstants;

/**
 * TODO Description
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see https://github.com/bkielczewski/example-spring-mvc-initializer/blob/master/src/main/java/eu/kielczewski/example/config/AppConfig.java
 */
@Configuration
@ComponentScan(basePackages = ApplicationConstants.APPLICATION_SETUP_BASE_PACKAGE)
public class AppConfig {
	
	/*
	// JSTL
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	*/
	
	/*
	// Velocity
	@Bean
	public UrlBasedViewResolver viewResolver() {
		VelocityLayoutViewResolver viewResolver = new VelocityLayoutViewResolver();
		viewResolver.setViewClass(VelocityLayoutView.class);
		viewResolver.setPrefix("/WEB-INF/view/velocity/");
		viewResolver.setSuffix(".vm");
		return viewResolver;
	}
	*/
	
	// Thymeleaf
	// @see http://www.thymeleaf.org/doc/thymeleafspring.html
	
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/view/thymeleaf/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");	// TODO is there a constant within TL library for HTML5?
		templateResolver.setCacheable(false);
		return templateResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		return templateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setViewClass(ThymeleafView.class);
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		//viewResolver.setViewNames( new String[] {"*.html", "*.xhtml"} );
		viewResolver.setViewNames( new String[] {"*"} );
		viewResolver.setCache(false);
		return viewResolver;
	}
	
}
