package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyXpaths;

public class TreasuryResultsPage extends CommonPage {

	public TreasuryResultsPage(WebDriver driver, String key) {
		super(driver, key + " " + EtsyTitles.TREASURY_RESULTS_TITLE.getValue());
	}

	public WebElement getNumResults() {
		return this.driver.findElement(By
				.xpath(EtsyXpaths.NUM_RESULTS_TREASURY_XPATH.getValue()));
	}

	public void goToResult(int resultIndex) {
		wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[2]/UL/LI["
								+ resultIndex + "]/DIV[2]/DIV[2]/H3/A")))
				.click();
	}

}
