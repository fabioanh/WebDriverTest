package com.globant.training.etsy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.globant.training.common.EtsyTitles;

public class ItemResultPage extends CommonPage {

	public ItemResultPage(WebDriver driver) {
		super(driver, EtsyTitles.HOME_TITLE.getValue());
	}

	@Override
	protected void checkCorrectPage() {
		// Do nothing
	}

	public ItemResultPage selectSize(int index) {
		Select sizeSelect = new Select(driver.findElement(By
				.className("variation")));
		sizeSelect.selectByIndex(index);
		return this;
	}

	public CartPage addItemToCart() {
		
		return new CartPage(this.driver);
	}
	
	public boolean hasSize(){
		
	}

}
