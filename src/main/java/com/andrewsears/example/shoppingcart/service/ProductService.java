/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 29, 2014
 */

package com.andrewsears.example.shoppingcart.service;

import java.util.List;

import com.andrewsears.example.shoppingcart.model.Product;

/**
 * An interface for all service calls for the Product.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 */
public interface ProductService {

	/**
	 * Returns the one {@link Product} based on the id.
	 * @param productId The id
	 * @return The product
	 */
	public Product getProduct(Long productId);
	
	/**
	 * Returns the list of all the {@link Product}s.
	 * @return The list of all products
	 */
	public List<Product> getAllProducts();

}
