/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 29, 2014
 */

package com.andrewsears.example.shoppingcart.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andrewsears.example.shoppingcart.model.Product;
import com.andrewsears.example.shoppingcart.repository.ProductRepository;

/**
 * An implementation of the {@link ProductService} interface using the JPA instance.
 * 
 * @author Andrew Sears <a href="mailto=andrew.sears@nyu.edu">andrew.sears@nyu.edu</a>
 * @see http://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/
 */
@Service
public class ProductServiceRepositoryImpl implements ProductService {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ProductServiceRepositoryImpl.class);
	
	//@Resource
	@Autowired
	private ProductRepository productRepository;
	
	public ProductServiceRepositoryImpl() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ProductService#getProduct(Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Product getProduct(Long productId) {
		LOGGER.debug("Getting product by id {}", productId);
		return this.productRepository.findOne(productId);
	}

	/* (non-Javadoc)
	 * @see com.andrewsears.example.shoppingcart.service.ProductService#getAllProducts()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		LOGGER.debug("Getting all products");
		return (List<Product>) this.productRepository.findAll();
	}

}
