package com.ebay.Pages;

import static org.testng.Assert.assertTrue;

import org.testng.Reporter;

public class EbaySettingPage extends Base_Page {

	private static String hanburgerLoc = System.getProperty("HAMBURGER_MENU_LOC");
	private static String signInLoc = System.getProperty("SIGN_IN_LABEL_LOC");
	private static String settingTabLoc = System.getProperty("SETTING_TAB_LOC");
	private static String settingPageHeaderLoc = System.getProperty("SETTING_PAGE_HEADER_LOC");
	private static String settingOptionLoc = System.getProperty("SETTING_OPTION_LOC");
	private static String autoDetectSwitchLoc = System.getProperty("AUTODETECT_SWITCH_LOC");
	private static String countryNameLoc = System.getProperty("COUNTRY_REGION_NAME_LOC");
	private static String searchtextField = System.getProperty("SEARCH_FIELD_LOC");
	private static String toSelectCountryLoc = System.getProperty("SELECT_COUNTRY_LOC");
	private static String navigateUpLoc = System.getProperty("NAVIGATE_UP_BUTTON_LOC");
	private static String filterCountryLoc = System.getProperty("FILTER_COUNTRY_LOC");

	public void openHamburgerMenu() {
		if (verifyIsDisplayed(hanburgerLoc)) {
			click(hanburgerLoc);
		} else {
			Reporter.log("Hamburger menu is not visible");
		}
	}

	public void verifySignInMenu() {
		if (verifyIsDisplayed(signInLoc)) {
			Reporter.log("Sign In menu is visible");
		} else {
			Reporter.log("Sign In menu is not visible");
		}
	}

	public void login(String userName, String password) {
		assertTrue(verifyIsDisplayed(signInLoc), "Sign in option is not visible");

	}

	public void openSettingMenu() {
		if (verifyIsDisplayed(settingTabLoc)) {
			click(settingTabLoc);
		}
		if (verifyIsDisplayed(settingPageHeaderLoc)) {
			System.out.println("Setting page opens up after clicking on setting option");
			Reporter.log("Setting page opens up after clicking on setting option");
		} else {
			System.out.println("Setting page is not geting open after clicking on setting option");
			Reporter.log("Setting page is not geting open after clicking on setting option");
		}
	}

	public void selectSettingOption(String settingOptions) {
		if (verifyIsDisplayed(getParametrisedLocator(settingOptionLoc, settingOptions))) {
			click(getParametrisedLocator(settingOptionLoc, settingOptions));
		} else {
			System.out.println("Setting Option " + settingOptions + " is not present in page");
			Reporter.log("Setting Option " + settingOptions + " is not present in page");
		}

	}

	public void enableDisableAutoDetect(boolean autoDetectOnOff) {
		String autoDetectFlagText = getLocatorText(autoDetectSwitchLoc);
		if (autoDetectOnOff && autoDetectFlagText.equals("OFF")) {
			click(autoDetectSwitchLoc);
		} else if (autoDetectOnOff && autoDetectFlagText.equals("ON")) {
			Reporter.log("Auto detect is already Activated");
		} else if (!autoDetectOnOff && autoDetectFlagText.equals("ON")) {
			click(autoDetectSwitchLoc);
		} else if (!autoDetectOnOff && autoDetectFlagText.equals("OFF")) {
			Reporter.log("Auto detect is already Deactivated");
		}
	}

	public void selectDiffCountry(String regionName) {
		if (verifyIsDisplayed(countryNameLoc)) {
			click(countryNameLoc);
			verifyIsDisplayed(filterCountryLoc);
			sendkeys(filterCountryLoc, regionName);
			verifyIsDisplayed(getParametrisedLocator(toSelectCountryLoc, regionName));
			click(getParametrisedLocator(toSelectCountryLoc, regionName));

		} else {
			Reporter.log("Setting Option is not present in page");
		}
	}

	public void navigateUp() {
		if (verifyIsDisplayed(navigateUpLoc)) {
			click(navigateUpLoc);
		}
	}

	public void changeAppRegion(String regionName) {
		openHamburgerMenu();
		verifySignInMenu();
		scrollDown();
		openSettingMenu();
		selectSettingOption("Country/region");
		enableDisableAutoDetect(false);
		selectDiffCountry(regionName);
		navigateUp();
		navigateUp();
	}

}
