package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyXpaths;

public class TreasuryPage extends CommonPage {

	public TreasuryPage(WebDriver driver) {
		super(driver, EtsyTitles.TREASURY_TITLE.getValue());
	}

	public TreasuryResultsPage treasurySearch(String key) {
		WebElement treasurySearchButton = getSearchButtonTreasury();
		getSearchFieldTreasury().sendKeys(key);
		treasurySearchButton.click();
		return new TreasuryResultsPage(driver, key);
	}

	public WebElement getSearchButtonTreasury() {
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(EtsyXpaths.SEARCH_BUTTON_TREASURY_XPATH.getValue())));
	}

	public WebElement getSearchFieldTreasury() {
		return wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(EtsyXpaths.SEARCH_FIELD_TREASURY_XPATH.getValue())));
	}

}
