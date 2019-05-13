package com.ebay.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Driver {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public static WebDriver getDriver() {
		return webDriver.get();
	}

	public static void setWebDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	/**
	 * Launch Application
	 * 
	 * @param platform
	 * @return
	 */
	public static WebDriver createInstance(String platform) {
		Properties prop = PropertyUtil.getCapabilities(platform);
		String driverName = prop.getProperty("driver.name");
		PropertyUtil.loadLocatorProperties(driverName);
		PropertyUtil.loadtestDataProperties();

		WebDriver driver = null;
		if (driverName.equalsIgnoreCase("android")) {
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

			desiredCapabilities.setCapability("app", prop.getProperty("app"));
			desiredCapabilities.setCapability("deviceName", prop.getProperty("deviceName"));
			desiredCapabilities.setCapability("platformName", prop.getProperty("platformName"));
			desiredCapabilities.setCapability("automationName", prop.getProperty("automationName"));
			desiredCapabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));

			try {
				driver = new AndroidDriver<WebElement>(new URL(prop.getProperty("appium.server")), desiredCapabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return driver;
	}
}
