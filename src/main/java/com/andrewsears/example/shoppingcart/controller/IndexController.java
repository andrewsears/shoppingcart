/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.InitBinder;
//XXX import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.andrewsears.example.shoppingcart.formatter.ShoppingCartFormatter;
import com.andrewsears.example.shoppingcart.model.Product;
import com.andrewsears.example.shoppingcart.model.ShoppingCart;
import com.andrewsears.example.shoppingcart.model.ShoppingCartCommand;
import com.andrewsears.example.shoppingcart.model.ShoppingCartItem;
import com.andrewsears.example.shoppingcart.model.ShoppingCartItemCommand;
import com.andrewsears.example.shoppingcart.service.ProductService;
import com.andrewsears.example.shoppingcart.service.ShoppingCartService;
import com.andrewsears.example.shoppingcart.util.ShoppingCartUtilities;

/**
 * TODO Description
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 * @see https://github.com/bkielczewski/example-spring-mvc-initializer/blob/master/src/main/java/eu/kielczewski/example/controller/IndexController.java
 */
@RestController
public class IndexController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	//@Autowired
	//private ShoppingCartService shoppingCartService;
	
//	@Value("${message}")
//	private String message;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("id");
	}

	@RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
	public ModelAndView getIndex() {
		LOGGER.debug("Calling index page");
		ModelAndView model = new ModelAndView();
//		model.addObject("title", "Spring Security Hello World");
//		model.addObject("message", this.message);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = {"/product"}, method = RequestMethod.GET)
	public ModelAndView getProductList() {
		LOGGER.debug("Calling products listing page");
		ModelAndView model = new ModelAndView("product-list");
		model.addObject( "products", this.productService.getAllProducts() );
		return model;
	}
	
	
	@RequestMapping(value = {"/product/{id}"}, method = RequestMethod.GET)
	//@ModelAttribute("shoppingCartItem")
	public ModelAndView getProduct(@PathVariable("id") Long productId) {
		LOGGER.debug("Calling product page by product ID: {}", productId);
		ModelAndView model = new ModelAndView("product");
		
		// Get the product
		Product product = this.productService.getProduct(productId);
		model.addObject( "product", product );
		
		// Create a shell ShoppingCartItem
		
		//ShoppingCartItemCommand sci = new ShoppingCartItemCommand(product, 1);
		//model.addObject("shoppingCartItemCommand", sci);
		ShoppingCartItem sci = new ShoppingCartItem(product, 1);
		model.addObject("shoppingCartItem", sci);
		
		return model;
	}
	/*
	@RequestMapping(value = {"/product/{id}"}, method = RequestMethod.GET)
	@ModelAttribute("shoppingCartItem")
	public ShoppingCartItem getProduct(@PathVariable("id") Long productId) {
		LOGGER.debug("Calling product page by product ID: {}", productId);
		Product product = this.productService.getProduct(productId);
		ShoppingCart sci = new ShoppingCartItemCommand(product, 1);
		return sci;
	}
	*/
	
	/*
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
	@ModelAttribute("shoppingCart")
	public ShoppingCart getShoppingCart() {
		LOGGER.debug("Calling shopping cart page");
		return this.getCurrentShoppingCart();
	}
	*/
	@RequestMapping(value = "/shoppingcart", method = RequestMethod.GET)
	@ModelAttribute("shoppingCartCommand")
	public ShoppingCartCommand getShoppingCart() {
		LOGGER.debug("Calling shopping cart page");
		return new ShoppingCartCommand( this.getCurrentShoppingCart() );
	}
	
	
	/*
	@RequestMapping(value = "/shoppingcart/update", method = RequestMethod.POST)
	//@ModelAttribute("shoppingCart")
	public ModelAndView updateShoppingCart(@ModelAttribute("shoppingCart") @Valid ShoppingCart shoppingCart, BindingResult bindingResult) {
		LOGGER.debug("Updating shopping cart");
		
		LOGGER.debug("SC: " + shoppingCart);
		LOGGER.debug("SC ID: " + shoppingCart.getId());
		
		
		// TODO REMOVE
		for (ShoppingCartItem sci : shoppingCart.getShoppingCartItems() )
			LOGGER.debug("\t- {} - quant: {}", sci.getProduct().getName(), sci.getQuantity());
		
		this.shoppingCartService.updateShoppingCart(shoppingCart);
		
		// Redirect back to the shopping cart part
		ModelAndView modelv = new ModelAndView("redirect:/shoppingcart");
		return modelv;
	}
	*/

	@RequestMapping(value = "/shoppingcart/update", method = RequestMethod.POST)
	public ModelAndView updateShoppingCart(@ModelAttribute("shoppingCartCommand") @Valid ShoppingCartCommand shoppingCartCommand, BindingResult bindingResult) {
		LOGGER.debug("Updating shopping cart");
		
		// XXX Hack to get the shopping cart; the object should have been binded completely.
		ShoppingCart shoppingCart = this.shoppingCartService.getShoppingCart( shoppingCartCommand.getShoppingCartId() );
		
		// Loop through each one and adjust quantities
		// XXX Again, shouldn't need to be here if object binding works
		for ( ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems() ) {
			for ( ShoppingCartItem commandSCI : shoppingCartCommand.getShoppingCartItems() ) {
				if ( shoppingCartItem.getId().longValue() == commandSCI.getId().longValue() ) {
					if ( commandSCI.getQuantity() <= 0 )
						shoppingCart.getShoppingCartItems().remove( shoppingCartItem );
					else
						shoppingCartItem.setQuantity( commandSCI.getQuantity() );
				}
			}
		}
		
		this.shoppingCartService.updateShoppingCart(shoppingCart);
		
		// Redirect back to the shopping cart part
		ModelAndView modelv = new ModelAndView("redirect:/shoppingcart");
		return modelv;
	}

	
	// TODO Cheating now; sticking on converting the Product, so using the Command option
	@RequestMapping(value = "/shoppingcart/add", method = RequestMethod.POST)
	//public ModelAndView addShoppingCartItem(@ModelAttribute("shoppingCartItem") @Valid ShoppingCartItem shoppingCartItem, @PathVariable("productId") Long productId, ModelMap model, BindingResult bindingResult) {
	public ModelAndView addShoppingCartItem(@ModelAttribute("shoppingCartItem") @Valid ShoppingCartItem shoppingCartItem, BindingResult bindingResult, Model model) {
	//public ModelAndView addShoppingCartItem(@ModelAttribute("shoppingCartItemCommand") ShoppingCartItemCommand shoppingCartItemCommand) {
		
		// TODO Weird hack that shouldn't be needed with proper binding and formatter.
		shoppingCartItem.setProduct( this.productService.getProduct( shoppingCartItem.getProduct().getId() ) );
		
		LOGGER.debug("Calling shopping cart item add for product SKU: {}", shoppingCartItem.getProduct().getSku());
		
		if (bindingResult.hasErrors()) {
			for ( ObjectError error : bindingResult.getAllErrors())
			LOGGER.error( error.getDefaultMessage() );
		}
		
		/*
		// XXX These 3 lines are the cheat
		LOGGER.debug("Calling shopping cart item of product (ID {}) add with quantity: {}", shoppingCartItemCommand.getProductId(), shoppingCartItemCommand.getQuantity());
		Product product = this.productService.getProduct( shoppingCartItemCommand.getProductId() );
		ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, shoppingCartItemCommand.getQuantity());
		*/
		
		ShoppingCart shoppingCart = this.getCurrentShoppingCart();
		
		ShoppingCartItem newShoppingCartItem = shoppingCartItem;
		if ( ShoppingCartUtilities.existsProductInShoppingCart(shoppingCart, shoppingCartItem.getProduct().getId()) ) {
			// If there is already the product in the shopping cart, up the quantity
			for ( ShoppingCartItem existingShoppingCartItem : shoppingCart.getShoppingCartItems() )
				if ( existingShoppingCartItem.getProduct().getId().equals( shoppingCartItem.getProduct().getId() ) ) {
					newShoppingCartItem = existingShoppingCartItem;
					newShoppingCartItem.setQuantity( newShoppingCartItem.getQuantity() + shoppingCartItem.getQuantity() );
					break;
				}
			this.shoppingCartService.updateShoppingCart(shoppingCart);
		} else {
			// If this is a new product to the shopping cart
			shoppingCart.getShoppingCartItems().add(newShoppingCartItem);
			this.shoppingCartService.updateShoppingCart(shoppingCart);
		}
		
		// Redirect back to the shopping cart part
		ModelAndView modelv = new ModelAndView("redirect:/shoppingcart");
		return modelv;
	}
	
	
	/*
	 * Local Methods
	 */
	
	/**
	 * Since there is only going to be one shopper in this example, either the shopping
	 * cart exists in the DB (1) or one needs to be created (0).
	 * TODO When a session is valid, make this method utilize the current shopping cart.
	 * @return The shopping cart
	 */
	private ShoppingCart getCurrentShoppingCart() {
		List<ShoppingCart> shoppingCarts = this.shoppingCartService.getAllShoppingCarts();
		if (shoppingCarts.size() > 0) {
			LOGGER.debug("Calling shopping cart getter with existing shopping cart");
			return shoppingCarts.get(0);
		}
		
		LOGGER.debug("Calling shopping cart getter with new shopping cart");
		return this.shoppingCartService.createShoppingCart( new ShoppingCart() );
	}
	
}
