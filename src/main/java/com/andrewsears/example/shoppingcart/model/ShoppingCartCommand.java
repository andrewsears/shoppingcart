package com.andrewsears.example.shoppingcart.model;

import java.math.BigDecimal;
import java.util.List;

import com.andrewsears.example.shoppingcart.util.ShoppingCartUtilities;

/**
 * Temporary command for updating a shopping cart.
 * TODO REMOVE
 */

public class ShoppingCartCommand {
	private ShoppingCart shoppingCart;
	private Long shoppingCartId;
	private List<ShoppingCartItem> shoppingCartItems;
//	private BigDecimal subtotal;
	
	public ShoppingCartCommand() {
		this.shoppingCart = null;
		this.shoppingCartId = null;
		this.shoppingCartItems = null;
//		this.subtotal = BigDecimal.ZERO;
	}
	
	public ShoppingCartCommand(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
		this.shoppingCartId = shoppingCart.getId();
		this.shoppingCartItems = shoppingCart.getShoppingCartItems();
//		this.subtotal = ShoppingCartUtilities.computeSubTotal(this.shoppingCart);
	}

	public ShoppingCart getShoppingCart() {	return this.shoppingCart;	}
	public void setShoppingCart(ShoppingCart shoppingCart) {	this.shoppingCart = shoppingCart;	}
	
	public Long getShoppingCartId() {	return this.shoppingCartId;	}
	public void setShoppingCartId(Long shoppingCartId) {	this.shoppingCartId = shoppingCartId;	}
	
	public List<ShoppingCartItem> getShoppingCartItems() {	return this.shoppingCartItems;	}
	public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {	this.shoppingCartItems = shoppingCartItems;	}
	
//	public BigDecimal getSubtotal() {	return this.subtotal;	}
//	public void setSubtotal(BigDecimal subtotal) {	this.subtotal = subtotal;	}
	
}
