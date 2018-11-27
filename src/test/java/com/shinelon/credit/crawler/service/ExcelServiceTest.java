/**
 *ExcelServiceTest.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.shinelon.credit.crawler.zrfan.model.Activity;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;

/**
 * ExcelServiceTest.java
 *
 * @author syq
 *
 */
public class ExcelServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(ExcelServiceTest.class);

	@Test
	public void csvTest() {
		File file = FileUtil.file("classpath:json.txt");
		String readString = FileUtil.readString(file, "GBK");
		List<Activity> list = JSON.parseArray(readString, Activity.class);
		logger.info("parseArray:{}", list);
		CsvWriter writer = CsvUtil.getWriter("d:/aaa.csv", Charset.forName("GBK"));
		writer.write(csvList(list));
		writer.close();
	}

	private List<String[]> csvList(List<Activity> list) {
		List<String[]> ret = new ArrayList<>(list.size() + 1);
		ret.add(new String[] { "银行名称", "关键词", "标题", "内容","URL" });
		ret.addAll(
				list.stream().map(e -> new String[] { e.getBankName(), e.getKeywords(), e.getTitle(), e.getContent() })
						.collect(Collectors.toList()));
		return ret;

	}

}
