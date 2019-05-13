package com.ebay.Pages;

import static org.testng.Assert.assertEquals;

import org.testng.Reporter;

public class eBayPage extends Base_Page {

	private static String searchtextField = System.getProperty("SEARCH_FIELD_LOC");
	private static String searchTextBox = System.getProperty("SEARCH_TEXT_BOX");
	private static String productLoc = System.getProperty("SEARCHED_PRODUCT");
	private static String addToCartLoc = System.getProperty("ADD_TO_CART_LOC");
	private static String selectedProduct = System.getProperty("SELECTED_PRODUCT");

	public void login(String userName, String password) {

	}

	/**
	 * Search Product to buy with the product name
	 * 
	 * @param searchProductText
	 */
	public void searchProduct(String searchProductText) {
		if (verifyIsDisplayed(searchTextBox)) {
			click(searchTextBox);
		} else {
			Reporter.log("Search text box is not visible");
		}
		if (verifyIsDisplayed(searchtextField)) {
			sendkeys(searchtextField, searchProductText);
			pressEnter();
		} else {
			Reporter.log("Search text field is not visible");
		}
	}

	/**
	 * Select Product from the product list
	 * 
	 * @param productName
	 */
	public void selectProduct(String productName) {
		searchAndScroll(getParametrisedLocator(productLoc, productName));
		click(getParametrisedLocator(productLoc, productName));
	}

	/**
	 * verify selected product details
	 * 
	 * @param productName
	 */
	public void verifyProductDetails(String productName) {
		verifyIsDisplayed(selectedProduct);
		assertEquals(getLocatorText(selectedProduct), productName);
	}

	/**
	 * Click on Add to cart
	 */
	public void addToCart() {
		searchAndScroll(addToCartLoc);
		if (verifyIsDisplayed(addToCartLoc)) {
			click(addToCartLoc);
		} else {
			Reporter.log("Add to cart option is not visible");
		}
	}

}
