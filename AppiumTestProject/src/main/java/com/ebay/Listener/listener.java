package com.ebay.Listener;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.InvokedMethod;

import com.ebay.core.Driver;

public class listener implements ITestListener {

	public void onTestStart(ITestResult result) {
		IInvokedMethod method = new InvokedMethod(result.getTestClass(), result.getMethod(), System.currentTimeMillis(),
				result);
		String platform = method.getTestMethod().getXmlTest().getLocalParameters().get("platform");
		try {
			WebDriver driver = Driver.createInstance(platform);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Driver.setWebDriver(driver);
		} catch (Exception e) {
			result.setStatus(ITestResult.FAILURE);
			throw new RuntimeException(e);
		}

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
