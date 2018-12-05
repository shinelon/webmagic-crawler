/**
 *ZrfanUtilsTest.java 
 *
 * 2018年12月5日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

/**
 * ZrfanUtilsTest.java
 *
 * @author syq
 *
 */
public class ZrfanUtilsTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ZrfanUtilsTest.class);
	
	public static final DateTimeFormatter DEFALUT_DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter DEFALUT_DTF2 = DateTimeFormatter.ofPattern("yyyy-M-d");
	
	@Test
	public void dateStrTest() {
		LocalDate now = LocalDate.now();
		logger.info(now.format(DEFALUT_DTF));
		logger.info(now.format(DEFALUT_DTF2));
	}
	@Test
	public void getCompatibleDateListTest() {
		String dateStr="2018-12-05";
		logger.info("{}",ZrfanUtils.getCompatibleDateList(dateStr));
	}
	
}
