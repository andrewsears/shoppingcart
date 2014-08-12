/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * POJO - Shopping Cart
 * It contains a list of {@link ShoppingCartItem}s.
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 */
@Entity
public class ShoppingCart extends AbstractPersistentObject {

	private static final long serialVersionUID = 7184733636024370377L;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="shoppingcart_id", referencedColumnName="id")
	private List<ShoppingCartItem> shoppingCartItems;
	
	/**
	 * Empty Constructor
	 */
	public ShoppingCart() {
		super();
		this.shoppingCartItems = new ArrayList<ShoppingCartItem>();
	}
	
	/**
	 * Full Constructor
	 * @param shoppingCartItems The list of shopping cart items
	 */
	public ShoppingCart(List<ShoppingCartItem> shoppingCartItems) {
		super();
		this.shoppingCartItems = shoppingCartItems;
	}

	
	
	/*
	 * Getters and Setters
	 */

	/**
	 * Returns the shoppingCartItems.
	 * @return The shoppingCartItems
	 */
	public List<ShoppingCartItem> getShoppingCartItems() {
		return this.shoppingCartItems;
	}

	/**
	 * Sets the shoppingCartItems.
	 * @param shoppingCartItems The shoppingCartItems
	 */
	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
		this.shoppingCartItems = shoppingCartItems;
	}
	
}
