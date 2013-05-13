package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyXpaths;

public class ItemResultPage extends CommonPage {

	public ItemResultPage(WebDriver driver) {
		super(driver, EtsyTitles.HOME_TITLE.getValue());
	}

	@Override
	protected void checkCorrectPage() {
		// Do nothing
	}

	public ItemResultPage selectDropDown(int index) {
		if (hasDropDownOptions()) {
			for (WebElement we : driver.findElements(By.className("variation"))) {
				Select select = new Select(we);
				select.selectByIndex(index);
			}
		}
		return this;
	}

	public CartPage addItemToCart() {
		wait.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath(EtsyXpaths.ADD_TO_CART_XPATH.getValue())))
				.click();
		return new CartPage(this.driver);
	}

	public boolean hasDropDownOptions() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.className("variation")));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}
	}
}
