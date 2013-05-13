package com.globant.training.common;

public enum EtsyXpaths {

	ADD_TO_CART_XPATH("//input[@value='Add to Cart']"),
	CART_EMPTY_IMAGE_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/DIV[@id=\"newempty\"]/DIV"),
	CLEAR_CART_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/FORM[1]/H2/SPAN[2]/A"),
	FIRST_ITEM_GALLERY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[2]/UL/LI[1]/DIV[4]/A[1]/IMG"),
	NUM_RESULTS_TREASURY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[1]/H1"),
	SEARCH_FIELD_XPATH("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/INPUT[@id=\"search-query\"]"),
	SEARCH_FIELD_TREASURY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[@id=\"listings-header\"]/FORM/INPUT[@name=\"search_query\"]"),
	SEARCH_BUTTON_XPATH("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/BUTTON[@id=\"search_submit\"]"),
	SEARCH_BUTTON_TREASURY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[@id=\"listings-header\"]/FORM/SPAN/SPAN/INPUT");

	private String value;

	private EtsyXpaths(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
