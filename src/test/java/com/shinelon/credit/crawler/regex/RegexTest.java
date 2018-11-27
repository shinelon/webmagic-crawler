/**
 *RegexTest.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.selector.RegexSelector;

/**
 * RegexTest.java
 *
 * @author syq
 *
 */
public class RegexTest {

	private static final Logger logger = LoggerFactory.getLogger(RegexTest.class);
	private  String listUrl1 = "https://www.zrfan.com/category/bank/";
	private  String listUrl2="https://www.zrfan.com/category/bank/page/2/";
	private  String targetUrl="https://www.zrfan.com/2460.html";
	private  String listUrlPattern = "https://www\\.zrfan\\.com/category/bank/(page/\\d/)?";
	private  String targetUrlPattern = "https://www\\.zrfan\\.com/\\d*\\.html";
	
	private String guideUrl="https://www.zrfan.com/category/zhinan/";
	private String guideUrlPattern="https://www.zrfan.com/category/zhinan/";
	
	@Test
	public void guideUrlTest() {
		Pattern pattern = Pattern.compile(guideUrlPattern);
		logger.info("pattern:{}", pattern);
		Matcher matcher1 = pattern.matcher(guideUrl);
		logger.info("ret1:{}", matcher1.matches());
		Assert.assertEquals(true, matcher1.matches());
	}
	
	
	@Test
	public void listUrlTest() {
		Pattern pattern = Pattern.compile(listUrlPattern);
		logger.info("pattern:{}", pattern);
		Matcher matcher1 = pattern.matcher(listUrl1);
		logger.info("ret1:{}", matcher1.matches());
		Assert.assertEquals(true, matcher1.matches());
		Matcher matcher2 = pattern.matcher(listUrl2);
		logger.info("ret2:{}", matcher2.matches());
		Assert.assertEquals(true, matcher2.matches());
	}
	
	@Test
	public void targetUrlTest() {
		Pattern pattern = Pattern.compile(targetUrlPattern);
		logger.info("pattern:{}", pattern);
		Matcher matcher = pattern.matcher(targetUrl);
		logger.info("ret:{}", matcher.matches());
		Assert.assertEquals(true, matcher.matches());
	}
	
	@Test
	public void listUrlRegexSelectorTest() {
		RegexSelector rs = new RegexSelector(listUrlPattern);
		String select = rs.select(listUrl1);
		logger.info("ret:{}",select);

	}
}
