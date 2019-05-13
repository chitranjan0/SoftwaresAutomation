package com.ebay.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	/**
	 * Load locators
	 * 
	 * @param driverName
	 * @return
	 */
	public static Properties loadLocatorProperties(String driverName) {
		Properties props = new Properties();
		String locatorsDirPath = System.getProperty("user.dir") + "/src/main/java/com/ebay/resource/" + driverName
				+ "_locators";
		try {
			props.load(new FileReader(new File(locatorsDirPath + "/locator.properties")));
			for (Object propertyKey : props.keySet()) {
				System.setProperty(propertyKey.toString(), props.get(propertyKey).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * Load Test Data
	 * 
	 * @return
	 */
	public static Properties loadtestDataProperties() {
		Properties props = new Properties();
		String locatorsDirPath = System.getProperty("user.dir") + "/src/main/java/com/ebay/resource";
		try {
			props.load(new FileReader(new File(locatorsDirPath + "/testData.properties")));
			for (Object propertyKey : props.keySet()) {
				System.setProperty(propertyKey.toString(), props.get(propertyKey).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * Load platform capabilities
	 * 
	 * @param platform
	 * @return
	 */
	public static Properties getCapabilities(String platform) {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/ebay/resource/capabilities/" + platform + ".properties");
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return prop;
	}

}
