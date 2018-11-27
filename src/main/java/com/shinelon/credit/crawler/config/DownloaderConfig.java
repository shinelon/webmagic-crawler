package com.shinelon.credit.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean(name = "spiderHttpClientDownloader")
    public HttpClientDownloader config() {
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setThread(8);
        // httpClientDownloader.setProxyProvider(proxyProvider);
        return httpClientDownloader;
    }
    
    @Bean(name = "spiderSeleniumDownloader")
    public SeleniumDownloader configSelenium() {
    	SeleniumDownloader downloader=new SeleniumDownloader("D:\\eclipse-workspace2018-photon\\chromedriver_win32\\chromedriver.exe");
    	downloader.setThread(1);
    	downloader.setSleepTime(1000);
    	return downloader;
    }
}
