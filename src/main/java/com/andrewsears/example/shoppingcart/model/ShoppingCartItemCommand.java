package com.andrewsears.example.shoppingcart.model;

/**
 * Temporary command for adding a shopping cart item.
 * TODO REMOVE
 */

public class ShoppingCartItemCommand {
	private Long productId;
	private Integer quantity;
	
	public ShoppingCartItemCommand() {
		this.productId = null;
		this.quantity = 0;
	}
	
	public ShoppingCartItemCommand(Product product, Integer quantity) {
		this.productId = product.getId();
		this.quantity = quantity;
	}

	public Long getProductId() {	return this.productId;	}
	public void setProductId(Long productId) {	this.productId = productId;	}
	public Integer getQuantity() {	return this.quantity;	}
	public void setQuantity(Integer quantity) {	this.quantity = quantity;	}
}
