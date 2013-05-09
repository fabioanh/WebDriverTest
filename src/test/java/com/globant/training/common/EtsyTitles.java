package com.globant.training.common;

public enum EtsyTitles {

	CART_TITLE("Etsy - Shopping Cart"), 
	HOME_TITLE("Etsy - Your place to buy and sell all things handmade, vintage, and supplies"),
	RESULTS_TITLE("on Etsy, a global handmade and vintage marketplace."),
	TREASURY_TITLE("Etsy - Treasury");

	private String value;

	private EtsyTitles(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
