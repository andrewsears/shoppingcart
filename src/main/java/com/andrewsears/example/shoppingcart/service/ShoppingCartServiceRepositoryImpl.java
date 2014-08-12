/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 31, 2014
 */

package com.andrewsears.example.shoppingcart.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrewsears.example.shoppingcart.model.ShoppingCart;
import com.andrewsears.example.shoppingcart.repository.ShoppingCartRepository;

/**
 * An implementation of the {@link ProductService} interface using the JPA instance.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 * @see http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/
 */
@Service
public class ShoppingCartServiceRepositoryImpl implements ShoppingCartService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceRepositoryImpl.class);
	
	//@Resource
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCartServiceRepositoryImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ShoppingCartService#getAllShoppingCarts()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ShoppingCart> getAllShoppingCarts() {
		LOGGER.debug("Getting all shopping carts");
		return (List<ShoppingCart>) this.shoppingCartRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ShoppingCartService#getShoppingCart(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public ShoppingCart getShoppingCart(Long shoppingCartId) {
		LOGGER.debug("Getting shopping cart by id {}", shoppingCartId);
		return this.shoppingCartRepository.findOne(shoppingCartId);
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ShoppingCartService#createShoppingCart(com.andrewsears.example.shoppingcart.model.ShoppingCart)
	 */
	@Override
	public ShoppingCart createShoppingCart(ShoppingCart shoppingCart) {
		LOGGER.debug("Creating a new shopping cart");
		return this.shoppingCartRepository.save(shoppingCart);
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ShoppingCartService#updateShoppingCart(com.andrewsears.example.shoppingcart.model.ShoppingCart)
	 */
	@Override
	public boolean updateShoppingCart(ShoppingCart shoppingCart) {
		LOGGER.debug("Update an existing shopping cart with id {}", shoppingCart.getId());
		ShoppingCart returnedShoppingCart = this.shoppingCartRepository.save(shoppingCart);
		return ( returnedShoppingCart.getId().equals( shoppingCart.getId() ) );
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ShoppingCartService#deleteShoppingCart(com.andrewsears.example.shoppingcart.model.ShoppingCart)
	 */
	@Override
	public boolean deleteShoppingCart(ShoppingCart shoppingCart) {
		LOGGER.debug("Delete an existing shopping cart with id {}", shoppingCart.getId());
		this.shoppingCartRepository.delete(shoppingCart);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ShoppingCartService#deleteShoppingCart(java.lang.Long)
	 */
	@Override
	public boolean deleteShoppingCart(Long shoppingCartId) {
		LOGGER.debug("Delete an existing shopping cart with id {}", shoppingCartId);
		this.shoppingCartRepository.delete(shoppingCartId);
		return true;
	}
	
}
