package com.globant.training.etsy.pages;

import org.openqa.selenium.WebDriver;

import com.globant.training.common.EtsyTitles;

public class CartPage extends CommonPage {

	public CartPage(WebDriver driver) {
		super(driver, EtsyTitles.CART_TITLE.getValue());
	}

}
