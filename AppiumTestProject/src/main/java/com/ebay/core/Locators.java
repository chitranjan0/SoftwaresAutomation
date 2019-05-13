package com.ebay.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Locators {
	final static int WAIT = 30;

	public static WebDriver getDriver() {
		return Driver.getDriver();
	}

	/**
	 * Find locators with diff startegies (id, xpath, etc) we can add more
	 * strategies
	 * 
	 * @param locator
	 * @return
	 */
	public static WebElement findElement(String locator) {
		String locatorStringArray[] = locator.split("=", 2);
		String findBy = locatorStringArray[0];
		String locatorValue = locatorStringArray[1];
		if (findBy.equalsIgnoreCase("id")) {
			new WebDriverWait(getDriver(), WAIT)
					.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			return getDriver().findElement(By.id(locatorValue));
		} else if (findBy.equalsIgnoreCase("content-desc")) {
			new WebDriverWait(getDriver(), WAIT).until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(String.format("//*[@content-desc='%s']", locatorValue))));
			return getDriver().findElement(By.xpath(String.format("//*[@content-desc='%s']", locatorValue)));
		} else if (findBy.equalsIgnoreCase("xpath")) {
			new WebDriverWait(getDriver(), WAIT)
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
			return getDriver().findElement(By.xpath(locatorValue));
		} else {
			new WebDriverWait(getDriver(), WAIT).until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(String.format("//*[@name='%s' or @value='%s']", locatorValue, locatorValue))));
			return getDriver()
					.findElement(By.xpath(String.format("//*[@name='%s' or @value='%s']", locatorValue, locatorValue)));
		}
	}

}
