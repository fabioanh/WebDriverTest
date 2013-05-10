package com.globant.training;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.common.EtsyValues;
import com.globant.training.etsy.pages.CartPage;
import com.globant.training.etsy.pages.HomePage;
import com.globant.training.etsy.pages.ResultsPage;
import com.globant.training.etsy.pages.TreasuryResultsPage;

public class EtsyTest {

	private WebDriver driver;

	@BeforeMethod(groups = { "suite1", "suite3" })
	public void init() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "en");
		driver = new FirefoxDriver();
		driver.get(EtsyValues.ETSY_URL.getValue());
	}

	@AfterMethod(groups = { "suite1", "suite3" })
	public void after() {
		// driver.close();
	}

	/**
	 * Test intended to search items around in the site
	 */
	@Test(groups = "suite1")
	public void browseItems() {

		HomePage homePage = new HomePage(driver);

		TreasuryResultsPage treasuryResultsPage = homePage.goToTreasury()
				.treasurySearch("bag");
		System.out.println("Assert expected results not 0");
		assertNotEquals(treasuryResultsPage.getNumResults().getText().trim()
				.substring(0, 1), "0", "Results expected for given key");
		System.out.println("go to result");

		treasuryResultsPage.goToResult(1);

	}

	/**
	 * Test the addition of items to the shopping cart
	 */
	@Test(groups = "suite2")
	public void addItemToCart() {
		driver = new FirefoxDriver();

		CartPage cartPage = new CartPage(driver);

		if (!cartPage.isEmptyCart()) {
			fail("Cart should be empty");
		}

		ResultsPage resultsPage = cartPage.commonSearch("hat");

		resultsPage.getResultItems().get(0).click();

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

		driver.get(EtsyValues.ETSY_CART_URL.getValue());

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
