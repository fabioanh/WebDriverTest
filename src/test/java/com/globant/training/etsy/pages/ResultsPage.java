package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyXpaths;

public class ResultsPage extends CommonPage {

	public ResultsPage(WebDriver driver, String key) {
		super(driver, key + " " + EtsyTitles.RESULTS_TITLE);
	}

	public WebElement getNumResults() {
		return this.driver.findElement(By.xpath(EtsyXpaths.NUM_RESULTS_TREASURY_XPATH
				.getValue()));
	}

}
