/**
 *ChromeDriverDownloader.java 
 * 
 * 2018年11月29日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.chrome;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;

/**
 * ChromeDriverDownloader.java
 *
 * @author syq
 *
 */
public class ChromeDriverDownloader implements Downloader, Closeable {
	private static final Logger logger = LoggerFactory.getLogger(ChromeDriverDownloader.class);
	private volatile GenericObjectPool<WebDriver> webDriverPool;

	private int sleepTime = 0;
	private int poolSize = 1;

	public ChromeDriverDownloader(String chromeDriverPath) {
		System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
	}

	private void checkInit() {
		if (webDriverPool == null) {
			synchronized (this) {
				if (webDriverPool == null) {
					GenericObjectPoolConfig<WebDriver> genericObjectPoolConfig = new GenericObjectPoolConfig<WebDriver>();
					genericObjectPoolConfig.setMaxTotal(poolSize);
					webDriverPool = new GenericObjectPool<>(new WebDriverPooledObjectFactory(),
							genericObjectPoolConfig);
				}
			}
		}

	}

	@Override
	public void close() throws IOException {
		webDriverPool.clear();
	}

	@Override
	public Page download(Request request, Task task) {
		checkInit();
		logger.info("downloading page " + request.getUrl());
		WebDriver webDriver;
		try {
			webDriver = webDriverPool.borrowObject();
		} catch (Exception e1) {
			throw new RuntimeException(e1.getMessage(), e1);
		}
		WebDriver.Options manage = webDriver.manage();
		Site site = task.getSite();
		if (site.getCookies() != null) {
			for (Map.Entry<String, String> cookieEntry : site.getCookies().entrySet()) {
				Cookie cookie = new Cookie(cookieEntry.getKey(), cookieEntry.getValue());
				manage.addCookie(cookie);
			}
		}
		webDriver.get(request.getUrl());
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		WebElement webElement = webDriver.findElement(By.xpath("/html"));
		String content = webElement.getAttribute("outerHTML");
		Page page = new Page();
		page.setRawText(content);
//		page.setHtml(new Html(content, request.getUrl()));
		page.setUrl(new PlainText(request.getUrl()));
		page.setRequest(request);
		webDriverPool.returnObject(webDriver);
		return page;
	}

	@Override
	public void setThread(int threadNum) {
		this.poolSize = threadNum;

	}

	public ChromeDriverDownloader setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}
}
