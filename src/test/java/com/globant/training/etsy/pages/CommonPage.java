package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.globant.training.common.EtsyXpaths;

public abstract class CommonPage {

	protected final WebDriver driver;
	protected final String title;
	private By searchField = By.xpath(EtsyXpaths.SEARCH_FIELD_XPATH.getValue());
	private By searchButton = By.xpath(EtsyXpaths.SEARCH_BUTTON_XPATH
			.getValue());

	public CommonPage(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;
		checkCorrectPage();
	}

	public void checkCorrectPage() {
		if (!driver.getTitle().trim().equals(title)) {
			throw new IllegalStateException("This is not the right page");
		}
	}

	/**
	 * Makes a basic search by the entered key
	 * 
	 * @param key
	 * @return
	 */
	public ResultsPage commonSearch(String key) {
		this.driver.findElement(searchField).sendKeys(key);
		this.driver.findElement(searchButton).submit();
		return new ResultsPage(driver, key);
	}
}
