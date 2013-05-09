package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyValues;

public class HomePage extends CommonPage {

	public HomePage(WebDriver driver) {
		super(driver, EtsyTitles.HOME_TITLE.getValue());
	}

	public TreasuryPage goToTreasury() {
		driver.findElement(By.linkText(EtsyValues.TREASURY.getValue())).click();
		return new TreasuryPage(driver);
	}

}
