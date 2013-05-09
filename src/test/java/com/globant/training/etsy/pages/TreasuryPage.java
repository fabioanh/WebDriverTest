package com.globant.training.etsy.pages;

import org.openqa.selenium.WebDriver;

import com.globant.training.common.EtsyTitles;

public class TreasuryPage extends CommonPage {

	public TreasuryPage(WebDriver driver) {
		super(driver, EtsyTitles.TREASURY_TITLE.getValue());
	}

}
