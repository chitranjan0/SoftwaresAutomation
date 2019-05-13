package com.ebay.Pages;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ebay.core.Driver;
import com.ebay.core.Locators;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Base_Page {
	final static int WAIT = 30;

	public WebDriver getDriver() {
		return Driver.getDriver();
	}

	/**
	 * Verify locators are displaying or not
	 * 
	 * @param locator
	 * @return
	 */
	public boolean verifyIsDisplayed(String locator) {
		boolean flag = false;
		try {
			new WebDriverWait(getDriver(), WAIT).until(ExpectedConditions.visibilityOf(Locators.findElement(locator)));
			flag = true;
		} catch (Exception e) {

		}
		return flag;
	}

	/**
	 * Return Text of given locator
	 * 
	 * @param locator
	 * @return
	 */
	public String getLocatorText(String locator) {
		String text = "";
		text = Locators.findElement(locator).getText();
		return text;
	}

	/**
	 * Click on given locator
	 * 
	 * @param locator
	 */
	public void click(String locator) {
		Locators.findElement(locator).click();
	}

	/**
	 * Enter text in the given text field locator
	 * 
	 * @param locator
	 * @param value
	 */
	public void sendkeys(String locator, String value) {
		Locators.findElement(locator).clear();
		Locators.findElement(locator).sendKeys(value);
	}

	/**
	 * Get Formated Parameterized locator
	 * 
	 * @param locator
	 * @param parameter
	 * @return
	 */
	public String getParametrisedLocator(String locator, Object parameter) {
		locator = String.format(locator, parameter);
		return locator;
	}

	/**
	 * Scroll Down till the element is not visible
	 * 
	 * @param locator
	 */
	public void searchAndScroll(String locator) {
		int scroll = 0;
		int maxCount = 5;
		while (!verifyIsDisplayed(locator) && scroll <= maxCount) {
			scrollDown();
			scroll++;
		}
	}

	public void scrollDown() {

		int margin = 100;
		Dimension size = getDriver().manage().window().getSize();
		Point location = new Point(0, 0);

		int fromXOffSet = location.getX() + size.width / 2;
		int fromYOffSet = location.getY() + size.height / 2 - margin;

		int toXOffSet = location.getX() + size.width / 2;
		int toYOffSet = location.getY() + margin;

		PointOption pressOptions = PointOption.point(fromXOffSet, fromYOffSet);
		PointOption moveToOptions = PointOption.point(toXOffSet, toYOffSet);
		WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(1000));

		new TouchAction((PerformsTouchActions) getDriver()).press(pressOptions).waitAction(waitOptions)
				.moveTo(moveToOptions).release().perform();

	}

	/**
	 * Scroll Down
	 */
	public void pressEnter() {
		AndroidDriver<WebElement> driver = ((AndroidDriver) getDriver());
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}

}
