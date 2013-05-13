package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.globant.training.common.EtsyXpaths;

public abstract class CommonPage {

	protected final WebDriver driver;
	protected final String title;
	protected WebDriverWait wait;
	private By searchFieldBy = By.xpath(EtsyXpaths.SEARCH_FIELD_XPATH
			.getValue());
	private By searchButtonBy = By.xpath(EtsyXpaths.SEARCH_BUTTON_XPATH
			.getValue());

	public CommonPage(WebDriver driver, String title) {
		this.driver = driver;
		this.title = title;
		wait = new WebDriverWait(driver, 10);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkCorrectPage();
	}

	protected void checkCorrectPage() {
		if (!driver.getTitle().trim().equals(title)) {
			throw new IllegalStateException("This is not the right page - "
					+ driver.getCurrentUrl() + " - Title: "
					+ driver.getTitle().trim() + " --- Should be: " + title);
		}
	}

	/**
	 * Makes a basic search by the entered key
	 * 
	 * @param key
	 * @return
	 */
	public ResultsPage commonSearch(String key) {
		WebElement searchButton = wait.until(ExpectedConditions
				.elementToBeClickable(searchButtonBy));
		this.driver.findElement(searchFieldBy).sendKeys(key);
		searchButton.submit();
		return new ResultsPage(driver, key);
	}
}
