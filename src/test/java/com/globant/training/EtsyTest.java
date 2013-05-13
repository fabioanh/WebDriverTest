package com.globant.training;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.globant.training.common.EtsyValues;
import com.globant.training.etsy.pages.CartPage;
import com.globant.training.etsy.pages.HomePage;
import com.globant.training.etsy.pages.ItemResultPage;
import com.globant.training.etsy.pages.ResultsPage;
import com.globant.training.etsy.pages.TreasuryResultsPage;

public class EtsyTest {

	private WebDriver driver;

	private void commonInit() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "en");
		driver = new FirefoxDriver(profile);
	}

	@BeforeMethod(groups = { "suite1", "suite3" })
	public void init() {
		commonInit();
		driver.get(EtsyValues.ETSY_URL.getValue());
	}

	@AfterMethod(groups = { "suite1", "suite3" })
	public void after() {
		driver.close();
	}

	/**
	 * Test intended to search items around in the site
	 */
	@Test(groups = "suite1")
	public void browseItems() {

		HomePage homePage = new HomePage(driver);

		TreasuryResultsPage treasuryResultsPage = homePage.goToTreasury()
				.treasurySearch("bag");
		assertNotEquals(treasuryResultsPage.getNumResults().getText().trim()
				.substring(0, 1), "0", "Results expected for given key");
		treasuryResultsPage.goToResult(1);

	}

	/**
	 * Test the addition of items to the shopping cart
	 */
	@Test(groups = "suite2")
	public void addItemToCart() {
		commonInit();
		driver.get(EtsyValues.ETSY_CART_URL.getValue());
		CartPage cartPage = new CartPage(driver);

		assertTrue(cartPage.isEmptyCart(), "Cart should be empty");

		ResultsPage resultsPage = cartPage
				.commonSearch("custom hats full color top snapback");
		PageFactory.initElements(driver, resultsPage);
		ItemResultPage itemResult = resultsPage.goToResult(0);
		itemResult = itemResult.selectDropDown(1);
		cartPage = itemResult.addItemToCart();

		assertTrue(!cartPage.isEmptyCart(), "Cart should contain an item");
	}

	/**
	 * Test removing an item from the shopping cart
	 */
	@Test(groups = "suite2", dependsOnMethods = "addItemToCart")
	public void removeItemFromCart() {

		driver.get(EtsyValues.ETSY_CART_URL.getValue());
		CartPage cartPage = new CartPage(driver);
		assertTrue(!cartPage.isEmptyCart(), "Cart should contain an item");
		cartPage = cartPage.clearCart();
		assertTrue(cartPage.isEmptyCart(), "Cart should be empty");
		driver.close();
	}

	@Test(groups = "suite3")
	public void advancedHatSearch() {

		ResultsPage resultsPage = new HomePage(driver).commonSearch("hat");
		resultsPage.goToLinkName(EtsyValues.VINTAGE.getValue());

		assertTrue(!resultsPage.isEmptyResults(),
				"There should be elements for the vintage search");
	}

	@Test(groups = "suite3")
	public void advancedRingSearch() {
		ResultsPage resultsPage = new HomePage(driver).commonSearch("ring");
		resultsPage.goToLinkName(EtsyValues.JEWELRY.getValue());

		assertTrue(!resultsPage.isEmptyResults(),
				"There should be elements for the jewelry search");
	}
}
