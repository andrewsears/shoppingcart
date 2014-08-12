/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * POJO - Product
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 */
@Entity
public class Product extends /*AbstractPersistable<Long>*/ AbstractPersistentObject {

	private static final long serialVersionUID = 6776218952730232237L;
	
	private String name;
	private String description;
	private String sku;
	private BigDecimal price;
	
	/**
	 * Empty Constructor
	 */
	public Product() {
		super();
		this.name = null;
		this.description = null;
		this.sku = null;
		this.price = BigDecimal.ZERO;
	}
	
	/**
	 * Full Constructor
	 * @param name The name
	 * @param description The description
	 * @param sku The SKU
	 * @param price The price
	 */
	public Product(String name, String description, String sku, BigDecimal price) {
		super();
		this.name = name;
		this.description = description;
		this.sku = sku;
		this.price = price;
	}

	
	
	/*
	 * Getters and Setters
	 */

	/**
	 * Returns the name.
	 * @return The name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the description.
	 * @return The description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Sets the description.
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the sku.
	 * @return The sku
	 */
	public String getSku() {
		return this.sku;
	}

	/**
	 * Sets the sku.
	 * @param sku The sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * Returns the price.
	 * @return The price
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * Sets the price.
	 * @param price The price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
