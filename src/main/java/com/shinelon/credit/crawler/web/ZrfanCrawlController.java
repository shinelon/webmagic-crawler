/**
 *ZrfanCrawlController.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinelon.credit.crawler.service.ZrfanCrawlService;
import com.shinelon.credit.crawler.utils.JsonResultUtil;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

import net.minidev.json.JSONUtil;

/**
 * ZrfanCrawlController.java
 *
 * @author syq
 *
 */
@Controller
@RequestMapping("/crawl")
public class ZrfanCrawlController {

	private static final Logger logger = LoggerFactory.getLogger(ZrfanCrawlController.class);

	@Autowired
	private ZrfanCrawlService zrfanCrawlService;

	@RequestMapping("/bank")
	@ResponseBody
	public String crawlBank(Integer pageSum) {
		
		if (pageSum == null || pageSum < 1) {
			pageSum = 1;
		}
		zrfanCrawlService.crawlBank(pageSum);
		return JsonResultUtil.getSuccessJson().toJSONString();
	}
	
	@RequestMapping("/guide")
	@ResponseBody
	public String crawlGuide(String dateStr) {
		if(StringUtils.isBlank(dateStr)) {
			dateStr=ZrfanUtils.getTodayStr();
		}
		if(!ZrfanUtils.checkDateStyle(dateStr)) {
			return JsonResultUtil.getFailureJson("日期不合法，标准日期格式为yyyy-MM-dd",null).toJSONString();
		}
		zrfanCrawlService.crawlGuide(dateStr);
		return JsonResultUtil.getSuccessJson().toJSONString();
	}
	

}
