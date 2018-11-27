/**
 *ZrfanPageProcessor.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.shinelon.credit.crawler.zrfan.model.Activity;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * BankPageProcessor.java
 *    抓取银行页面 https://www.zrfan.com/category/bank/
 * @author syq
 *
 */
public class BankPageProcessor implements PageProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(BankPageProcessor.class);
	
	private static final String LIST_URL = "https://www\\.zrfan\\.com/category/bank/(page/\\\\d/)?";
	private static final String TARGET_URL = "www\\.zrfan\\.com/\\d*\\.html";

	private static final Site SITE = Site.me().setTimeOut(10 * 1000).setRetryTimes(1).setSleepTime(1000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public Site getSite() {
		return SITE;
	}

	@Override
	public void process(Page page) {
		if (page.getUrl().regex(LIST_URL).match()) {
			List<String> urls=page.getHtml().xpath("//h2[@class=\"entry-title\"]").links().regex(TARGET_URL).all();
			List<Request> formatRequest = formatRequest(urls);
			formatRequest.forEach(e->{
				page.addTargetRequest(e);
			});
		} else {
			Activity activity = extractActivityFromPage(page);
			page.putField("activity", activity);
		}
		ZrfanUtils.randomSleep(8);
	}
	
	
	private List<Request> formatRequest(List<String> urls){
		if(CollectionUtils.isEmpty(urls)) {
			return Collections.emptyList();
		}
		return urls.stream().map(e->new Request("https://"+e)).collect(Collectors.toList());
	}
	
	
	private Activity extractActivityFromPage(Page page) {
		Activity activity = new Activity();
		String keywords=findKeywords(page);
		activity.setKeywords(keywords);
		activity.setBankName(findBankName(keywords));
		activity.setTitle(findTitle(page));
		activity.setContent(findContent(page));
		activity.setUrl(page.getUrl().get());
		return activity;
	}

	private String findContent(Page page) {
		String content = page.getHtml().xpath("//div[@class=\"single-content\"]").get();
		Document doc = Jsoup.parse(content);
		return doc.select("div").text();
	}

	private String findTitle(Page page) {
		String title = page.getHtml().xpath("//h1[@class=\"entry-title\"]").get();
		Document doc = Jsoup.parse(title);
		return doc.select("h1").text();
	}

	private String findKeywords(Page page) {
		Selectable keywords = page.getHtml().xpath("/html/head/meta[7]");
		Document doc = Jsoup.parse(keywords.get());
		return doc.select("meta").attr("content");
	}

	private String findBankName(String keywords) {
		String[] split = StringUtils.split(keywords, ",");
		String bankName=splitSubString(split, "信用卡");;
		if(StringUtils.isBlank(bankName)) {
			bankName=splitSubString(split, "借记卡");
		}
		return bankName;
	}
	
	private String splitSubString(String[] split,String seqStr) {
		String str="";
		for (String keyword : split) {
			if(StringUtils.contains(keyword, seqStr)) {
				int indexOf = StringUtils.indexOf(keyword, seqStr);
				str= StringUtils.substring(keyword, 0, indexOf);
				break;
			}
		}
		return str;
	}
	
}
