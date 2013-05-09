package com.globant.training.etsy.pages;

import org.openqa.selenium.WebDriver;

import com.globant.training.common.EtsyTitles;

public class ResultsPage extends CommonPage {

	public ResultsPage(WebDriver driver, String key) {
		super(driver, key + " " + EtsyTitles.CART_TITLE);
	}

}
