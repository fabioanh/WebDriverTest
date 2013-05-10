package com.globant.training.common;

public enum EtsyValues {

	ETSY_CART_URL("https://www.etsy.com/cart"),
	ETSY_URL("http://www.etsy.com/"),
	TREASURY("Treasury");

	private String value;

	private EtsyValues(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
