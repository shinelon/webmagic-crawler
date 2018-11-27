/**
 *ZrfanUtils.java 
 *
 * 2018年11月27日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZrfanUtils.java
 *
 * @author syq
 *
 */
public class ZrfanUtils {

	private static final Logger logger = LoggerFactory.getLogger(ZrfanUtils.class);

	private static final String REMOVE_PREFIX = "//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=";
	private static final String TARGET_URL = "www\\.zrfan\\.com/\\d*\\.html";
	private static final Pattern TARGET_URL_PATTERN = Pattern.compile(TARGET_URL);

	public static final DateTimeFormatter DEFALUT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/***
	 * sleep for a while
	 * 
	 * @param maxSeconds
	 */
	public static void randomSleep(int maxSeconds) {
		int nextInt = RandomUtils.nextInt(1, maxSeconds);
		try {
			logger.info("sleep {}s ...", nextInt);
			Thread.sleep(nextInt * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException("randomSleep exceptions");
		}
	}

	/***
	 * 去除302跳转地址
	 * 
	 * @param url
	 * @return
	 */
	public static String removeRedirectUrl(String url) {
		return StringUtils.remove(url, REMOVE_PREFIX);
	}

	/***
	 * 拼接https:
	 * 
	 * @param url
	 * @return
	 */
	public static String formatUrl(String url) {
		Matcher matcher = TARGET_URL_PATTERN.matcher(url);
		if (matcher.find()) {
			url = "https:" + url;
		}
		return url;
	}

	/***
	 * 去除302跳转地址 并且 拼接https:
	 * 
	 * @param url
	 * @return
	 */
	public static String totalFormatUrl(String url) {
		String removeRedirectUrl = removeRedirectUrl(url);
		return formatUrl(removeRedirectUrl);
	}

	/***
	 * 格式化今天
	 * 
	 * @return
	 */
	public static String getTodayStr() {
		LocalDate now = LocalDate.now();
		return now.format(DEFALUT_DTF);
	}

	/***
	 * 格式化日期
	 * 
	 * @param ld
	 * @return
	 */
	public static String dateToStr(LocalDate ld) {
		return ld.format(DEFALUT_DTF);
	}
	

}
