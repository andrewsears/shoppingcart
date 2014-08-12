/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 29, 2014
 */

package com.andrewsears.example.shoppingcart.service;

import java.util.List;

import com.andrewsears.example.shoppingcart.model.ShoppingCart;

/**
 * An interface for all service calls for the Shopping Cart.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 */
public interface ShoppingCartService {

	/**
	 * Returns the list of all the saved {@link ShoppingCart}s.
	 * @return The list of all shopping carts
	 */
	public List<ShoppingCart> getAllShoppingCarts();
	
	/**
	 * Returns the one {@link ShoppingCart} based on the id.
	 * @param shoppingCartId The id
	 * @return The shopping cart
	 */
	public ShoppingCart getShoppingCart(Long shoppingCartId);
	
	/**
	 * Saves a new {@link ShoppingCart}, and returns the object with the
	 * id field set.
	 * @param shoppingCart The shopping cart
	 * @return The updated shopping cart
	 */
	public ShoppingCart createShoppingCart(ShoppingCart shoppingCart);
	
	/**
	 * Updates an existing {@link ShoppingCart}, and returns if the object
	 * was updated/saved.
	 * @param shoppingCart The shopping cart
	 * @return If the transaction completed successfully
	 */
	public boolean updateShoppingCart(ShoppingCart shoppingCart);
	
	/**
	 * Removed an existing {@link ShoppingCart}, and returns if the object
	 * was deleted.
	 * @param shoppingCart The shopping cart
	 * @return If the transaction completed successfully
	 */
	public boolean deleteShoppingCart(ShoppingCart shoppingCart);
	
	/**
	 * Removed an existing {@link ShoppingCart} based on the id, and returns
	 * if the object was deleted.
	 * @param shoppingCartId The id
	 * @return If the transaction completed successfully
	 */
	public boolean deleteShoppingCart(Long shoppingCartId);
	
}
