/**
 *HelloWord.java 
 *
 * 2018年11月27日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * HelloWord.java
 *
 * @author syq
 *
 */
public class HelloWord {
	private static final Logger logger = LoggerFactory.getLogger(HelloWord.class);

	@Test
	public void test() {
		System.getProperties().setProperty("webdriver.chrome.driver",
				"D:\\eclipse-workspace2018-photon\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		WebDriver webDriver = new ChromeDriver(chromeOptions);
		webDriver.get("http://huaban.com/");
		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		logger.info("webDriver:{}",
				webDriver.findElement(By.xpath("//*[@id=\"page\"]/div[1]/div[3]/div[1]")).getText());
		logger.info("webElement:{}",
				webElement.findElement(By.xpath("//*[@id=\"page\"]/div[1]/div[3]/div[1]")).getText());
		webDriver.close();
	}
}
