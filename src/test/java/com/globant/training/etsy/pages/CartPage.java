package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.globant.training.common.EtsyTitles;
import com.globant.training.common.EtsyXpaths;

public class CartPage extends CommonPage {

	public CartPage(WebDriver driver) {
		super(driver, EtsyTitles.CART_TITLE.getValue());
	}

	public boolean isEmptyCart() {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath(EtsyXpaths.CART_EMPTY_IMAGE_XPATH.getValue())));
			return false;
		} catch (NoSuchElementException e) {
			return true;
		}
	}

}
