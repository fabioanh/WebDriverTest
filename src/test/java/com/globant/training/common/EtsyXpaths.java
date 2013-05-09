package com.globant.training.common;

public enum EtsyXpaths {

	SEARCH_FIELD_XPATH("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/INPUT[@id=\"search-query\"]"), 
	SEARCH_BUTTON_XPATH("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/BUTTON[@id=\"search_submit\"]");

	private String value;

	private EtsyXpaths(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
