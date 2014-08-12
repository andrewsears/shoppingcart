/*
 * Andrew Sears
 * Copyright 2014
 * Created on Jul 15, 2014
 */

package com.andrewsears.example.shoppingcart.util;

import java.math.BigDecimal;

import com.andrewsears.example.shoppingcart.model.Product;
import com.andrewsears.example.shoppingcart.model.ShoppingCart;
import com.andrewsears.example.shoppingcart.model.ShoppingCartItem;


/**
 * A collection of helper methods for the Shopping Cart.
 * 
 * @author Andrew Sears <a href="mailto=andrew@andrewsears.com">andrew@andrewsears.com</a>
 */
public final class ShoppingCartUtilities {

	/**
	 * A helper method that will compute the sub-total of all the items in the shopping cart.
	 * @param shoppingCart The shopping cart
	 * @return The sub-total
	 */
	public static BigDecimal computeSubtotal(ShoppingCart shoppingCart) {
		BigDecimal subTotal = BigDecimal.ZERO;
		
		// Loop through each shopping cart item and add price*quantity to the total
		for ( ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems() ) {
			subTotal = subTotal.add( ShoppingCartUtilities.computeShoppingCartItemSubtotal(shoppingCartItem) );
		}
		
		return subTotal;
	}
	
	/**
	 * A helper method that will computer the sub-total of one specific shopping cart item.
	 * @param shoppingCartItem The shopping cart item
	 * @return The sub-total
	 */
	public static BigDecimal computeShoppingCartItemSubtotal(ShoppingCartItem shoppingCartItem) {
		return shoppingCartItem.getProduct().getPrice().multiply( BigDecimal.valueOf(shoppingCartItem.getQuantity()) );
	}
	
	/**
	 * A helper method that will see if a {@link ShoppingCart} already had a {@link Product}.
	 * @param shoppingCart The shopping cart
	 * @param productId The product
	 * @return If the product is already in the shopping cart
	 */
	public static boolean existsProductInShoppingCart(ShoppingCart shoppingCart, Long productId) {
		// If there's not list, it doesn't exist
		if (shoppingCart == null || shoppingCart.getShoppingCartItems() == null)
			return false;
		
		// Loop through each 
		for ( ShoppingCartItem shoppingCartItem : shoppingCart.getShoppingCartItems() )
			if ( shoppingCartItem.getProduct() != null && shoppingCartItem.getProduct().getId().equals(productId) )
				return true;
			
		// When all else fails, it doesn't exist
		return false;
	}

}
