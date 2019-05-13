package com.ebay.test;

import org.testng.annotations.Test;

import com.ebay.Pages.EbaySettingPage;
import com.ebay.Pages.eBayPage;

public class PurchaseTest {

	@Test(groups = { "Test" })
	public void purchaseNewProduct() {
		String userName = System.getProperty("userName");
		String password = System.getProperty("password");
		String searchProductText = System.getProperty("ItemName");
		String regionName = System.getProperty("countryName");
		String productName = System.getProperty("productName");

		EbaySettingPage setting = new EbaySettingPage();
		setting.changeAppRegion(regionName);

		eBayPage page = new eBayPage();
		page.login(userName, password);
		page.searchProduct(searchProductText);
		page.selectProduct(productName);
		page.verifyProductDetails(productName);
		page.addToCart();
	}

}
