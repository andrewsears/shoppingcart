/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * POJO - Shopping Cart Item
 * It contains a {@link Product}, as well as how many.
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 */
@Entity
public class ShoppingCartItem extends AbstractPersistentObject {

	private static final long serialVersionUID = 8787110468068650161L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="product_id", referencedColumnName="id")
	private Product product;
	
	private int quantity;
	
	/**
	 * Empty Constructor
	 */
	public ShoppingCartItem() {
		super();
		this.product = null;
		this.quantity = 0;
	}
	
	/**
	 * Full Constructor
	 */
	public ShoppingCartItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	
	
	/*
	 * Getters and Setters
	 */

	/**
	 * Returns the product.
	 * @return The product
	 */
	public Product getProduct() {
		return this.product;
	}

	/**
	 * Sets the product.
	 * @param product The product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Returns the quantity.
	 * @return The quantity
	 */
	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * Sets the quantity.
	 * @param quantity The quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
