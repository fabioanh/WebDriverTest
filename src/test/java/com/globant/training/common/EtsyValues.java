package com.globant.training.common;

public enum EtsyValues {

	ADD_TO_CART("Add to Cart"),
	ETSY_CART_URL("https://www.etsy.com/cart"),
	ETSY_URL("http://www.etsy.com/"),
	JEWELRY("Jewelry"),
	TREASURY("Treasury"), 
	VINTAGE("Vintage");

	private String value;

	private EtsyValues(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
