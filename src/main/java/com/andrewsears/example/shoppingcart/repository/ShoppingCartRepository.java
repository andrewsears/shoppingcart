/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 29, 2014
 */

package com.andrewsears.example.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.andrewsears.example.shoppingcart.model.ShoppingCart;

/**
 * An extension of the {@link CrudRepository} interface for the {@link ShoppingCart}.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 */
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
