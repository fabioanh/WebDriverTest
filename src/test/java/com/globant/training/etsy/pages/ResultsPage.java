package com.globant.training.etsy.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyXpaths;

public class ResultsPage extends CommonPage {

	@FindBy(className="listing-thumb")
	private List<WebElement> resultItems;

	public ResultsPage(WebDriver driver, String key) {
		super(driver, key + " " + EtsyTitles.RESULTS_TITLE.getValue());
	}

	public WebElement getNumResults() {
		return this.driver.findElement(By
				.xpath(EtsyXpaths.NUM_RESULTS_TREASURY_XPATH.getValue()));
	}

	private List<WebElement> getResultItems() {
		resultItems = driver.findElements(By.className("listing-thumb"));
		return resultItems;
	}

	public ItemResultPage goToResult(int index) {
		this.getResultItems().get(index).click();
		return new ItemResultPage(driver);
	}

	public ResultsPage goToLinkName(String linkText) {
		wait.until(
				ExpectedConditions.elementToBeClickable(By.linkText(linkText)))
				.click();
		return this;
	}

	public boolean isEmptyResults() {
		return getResultItems().isEmpty();
	}

}
