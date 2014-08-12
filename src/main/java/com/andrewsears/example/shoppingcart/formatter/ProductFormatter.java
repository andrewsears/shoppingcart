/*
 * Andrew Sears
 * Copyright 2014
 * Created on Aug 2, 2014
 */

package com.andrewsears.example.shoppingcart.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.andrewsears.example.shoppingcart.model.Product;
import com.andrewsears.example.shoppingcart.service.ProductService;

/**
 * An implementation of {@link Formatter} for the {@link Product} object.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 * @see http://www.thymeleaf.org/doc/thymeleafspring.html#handling-the-command-object
 */
public class ProductFormatter implements Formatter<Product> {
	
	@Autowired
	private ProductService productService;
	
	public ProductFormatter() {
		super();
	}
	
	@Override
	public Product parse(final String text, final Locale locale) throws ParseException {
		final Long id = Long.valueOf(text);
		return this.productService.getProduct(id);
	}
	
	@Override
	public String print(final Product object, final Locale locale) {
		return ( (object != null) ? object.getId().toString() : "" );
	}

}
