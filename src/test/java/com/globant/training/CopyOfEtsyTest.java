package com.globant.training;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class CopyOfEtsyTest {

	private static final String ETSY_URL = "http://www.etsy.com/";
	private static final String CART_URL = "https://www.etsy.com/cart";
	private static final String TREASURY_LINK_TEXT = "Treasury";

	private WebDriver driver;

	@BeforeMethod(groups = { "suite1", "suite3" })
	public void init() {
		driver = new FirefoxDriver();
		driver.get(ETSY_URL);
	}

	/**
	 * Test intended to search items around in the site
	 */
	@Test(groups = "suite1-1")
	public void browseItems() {

		WebElement treasuryLink = driver.findElement(By
				.linkText(TREASURY_LINK_TEXT));
		treasuryLink.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Find the button to do the search inside treasury objects
		WebElement searchButton = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[@id=\"listings-header\"]/FORM/SPAN/SPAN/INPUT")));

		// Find the text input field to do the search
		WebElement searchField = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[@id=\"listings-header\"]/FORM/INPUT[@name=\"search_query\"]"));
		// Search key
		searchField.sendKeys("bag");

		searchButton.click();

		WebElement numResults = driver.findElement(By
				.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[1]/H1"));

		assertNotEquals(numResults.getText().trim().substring(0, 1), "0",
				"Results expected for given key");

		WebElement gallery = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[2]/DIV[2]/UL/LI[1]/DIV[4]/A[1]/IMG")));
		// Click should be done without errors
		gallery.click();

	}

	/**
	 * Test the addition of items to the shopping cart
	 */
	@Test(groups = "suite2")
	public void addItemToCart() {
		driver = new FirefoxDriver();

		driver.get(CART_URL);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/DIV[@id=\"newempty\"]/DIV")));
		} catch (NoSuchElementException e) {
			fail("Cart should be empty");
		}

		// Find the button to do the search inside treasury objects
		WebElement searchButton = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/BUTTON[@id=\"search_submit\"]")));

		// Find the text input field to do the search
		WebElement searchField = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/INPUT[@id=\"search-query\"]"));
		// Search key
		searchField.sendKeys("hat");

		searchButton.click();

		WebElement itemToBuy = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"primary\"]/UL/LI[1]/A")));

		itemToBuy.click();

		WebElement addToCartButton = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[2]/DIV[1]/DIV[2]/DIV[3]/DIV[1]/FORM/SPAN/SPAN/INPUT")));

		addToCartButton.click();

		WebElement itemInCartMessage = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/DIV[@id=\"checkout-header\"]/H1"));

		assertEquals(itemInCartMessage.getText().trim().substring(0, 1), "1",
				"1 item expected in cart");
	}

	/**
	 * Test removing an item from the shopping cart
	 */
	@Test(groups = "suite2", dependsOnMethods = "addItemToCart")
	public void removeItemFromCart() {

		driver.get(CART_URL);

		WebElement itemInCartMessage = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/DIV[@id=\"checkout-header\"]/H1"));

		assertEquals(itemInCartMessage.getText().trim().substring(0, 1), "1",
				"1 item expected in cart");

		WebElement clearButton = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/FORM[1]/H2/SPAN[2]/A"));

		clearButton.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By
					.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"primary\"]/DIV[@id=\"checkout\"]/DIV[@id=\"newempty\"]/DIV")));
		} catch (NoSuchElementException e) {
			fail("Cart should be empty");
		}
	}

	@Test(groups = "suite3")
	public void advancedHatSearch() {

		WebElement vintageLink = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"secondary\"]/DIV[1]/UL[@id=\"category-list\"]/LI[7]/A"));
		vintageLink.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Find the button to do the search inside treasury objects
		WebElement searchButton = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/BUTTON[@id=\"search_submit\"]")));

		// Find the text input field to do the search
		WebElement searchField = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/INPUT[@id=\"search-query\"]"));
		// Search key
		searchField.sendKeys("hat");

		searchButton.click();

		WebElement allItemsLink = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"secondary\"]/DIV[@id=\"search-filters\"]/UL[1]/LI[1]/A")));

		allItemsLink.click();

		vintageLink = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"secondary\"]/DIV[@id=\"search-filters\"]/UL[1]/LI[3]/A")));

		vintageLink.click();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"primary\"]/UL/LI[1]/A/IMG")));
		} catch (NoSuchElementException e) {
			fail("There should be elements for the vintage search");
		}
	}

	@Test(groups = "suite3")
	public void advancedRingSearch() {
		WebElement jewelryLink = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV[@id=\"secondary\"]/DIV[1]/UL[@id=\"category-list\"]/LI[3]/A"));
		jewelryLink.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);

		// Find the button to do the search inside treasury objects
		WebElement searchButton = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/BUTTON[@id=\"search_submit\"]")));

		// Find the text input field to do the search
		WebElement searchField = driver
				.findElement(By
						.xpath("/HTML/BODY/DIV[@id=\"header\"]/DIV[@id=\"navigation-group\"]/FORM[@id=\"search-bar\"]/DIV/INPUT[@id=\"search-query\"]"));
		// Search key
		searchField.sendKeys("ring");

		searchButton.click();

		WebElement allItemsLink = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"secondary\"]/DIV[@id=\"search-filters\"]/UL[1]/LI[1]/A")));

		allItemsLink.click();

		jewelryLink = wait
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"secondary\"]/DIV[@id=\"search-filters\"]/UL[2]/LI[2]/A")));
		jewelryLink.click();

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By
					.xpath("/HTML/BODY/DIV[@id=\"content\"]/DIV/DIV/DIV/DIV[@id=\"primary\"]/UL/LI[1]/A/IMG")));
		} catch (NoSuchElementException e) {
			fail("There should be elements for the jewlery search");
		}
	}
}
