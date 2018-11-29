/**
 *WebDriverPooledObjectFactory.java 
 *
 * 2018年11月29日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.chrome;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.WebDriver;

/**
 * WebDriverPooledObjectFactory.java
 *
 * @author syq
 * @param <WebDriver>
 *
 */

public class WebDriverPooledObjectFactory implements PooledObjectFactory<WebDriver> {

	public WebDriverPooledObjectFactory(String chromeDriverPath) {
		System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
	}

	public WebDriverPooledObjectFactory() {
	}

	@Override
	public PooledObject<WebDriver> makeObject() throws Exception {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		WebDriver webDriver = new ChromeDriver(chromeOptions);
		return new DefaultPooledObject<WebDriver>(webDriver);
	}

	@Override
	public void destroyObject(PooledObject<WebDriver> p) throws Exception {
		WebDriver webDriver = p.getObject();
		webDriver.quit();

	}

	@Override
	public boolean validateObject(PooledObject<WebDriver> p) {
		WebDriver webDriver = p.getObject();
		if (webDriver == null) {
			return false;
		}
		return true;
	}

	@Override
	public void activateObject(PooledObject<WebDriver> p) throws Exception {
		WebDriver webDriver = p.getObject();
		WebDriver.Options manage = webDriver.manage();
		manage.deleteAllCookies();
	}

	@Override
	public void passivateObject(PooledObject<WebDriver> p) throws Exception {
		WebDriver webDriver = p.getObject();
		WebDriver.Options manage = webDriver.manage();
		manage.deleteAllCookies();
	}

}
