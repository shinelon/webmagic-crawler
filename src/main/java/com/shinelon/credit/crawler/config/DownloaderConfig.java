package com.shinelon.credit.crawler.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shinelon.credit.crawler.chrome.ChromeDriverDownloader;

import us.codecraft.webmagic.downloader.HttpClientDownloader;

/***
 * 
 * DownloaderConfig.java
 *
 * @author syq
 *
 */
@Configuration
public class DownloaderConfig {

	@Value("${chrome.driver.path}")
	private String chromeDriverPath;

	@Bean(name = "spiderHttpClientDownloader")
	public HttpClientDownloader config() {
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		httpClientDownloader.setThread(8);
		// httpClientDownloader.setProxyProvider(proxyProvider);
		return httpClientDownloader;
	}

	@Bean(name = "spiderChromeDriverDownloader")
	public ChromeDriverDownloader configSelenium() {
		if (StringUtils.isBlank(chromeDriverPath)) {
			throw new RuntimeException("chrome.driver.path is blank ,please check application.properties ！");
		}
		ChromeDriverDownloader downloader = new ChromeDriverDownloader(chromeDriverPath);
		downloader.setThread(8);
		return downloader;
	}
}
