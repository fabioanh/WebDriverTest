package com.globant.training.common;

public enum EtsyXpaths {

	FIRST_ITEM_GALLERY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[2]/UL/LI[1]/DIV[4]/A[1]/IMG"),
	NUM_RESULTS_TREASURY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[1]/H1"),
	SEARCH_FIELD_XPATH("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/INPUT[@id=\"search-query\"]"),
	SEARCH_FIELD_TREASURY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[@id=\"listings-header\"]/FORM/SPAN/SPAN/INPUT"),
	SEARCH_BUTTON_XPATH("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/BUTTON[@id=\"search_submit\"]"),
	SEARCH_BUTTON_TREASURY_XPATH("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[@id=\"listings-header\"]/FORM/INPUT[@name=\"search_query\"]");

	private String value;

	private EtsyXpaths(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
