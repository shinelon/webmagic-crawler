/**
 *GuidePageProcessor.java 
 *
 * 2018年11月27日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.shinelon.credit.crawler.zrfan.model.Activity;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

/**
 * GuidePageProcessor.java 指南页面抓取 https://www.zrfan.com/category/zhinan/
 * 
 * @author syq
 *
 */
public class GuidePageProcessor implements PageProcessor {

	private static final Site SITE = Site.me().setTimeOut(10 * 1000).setRetryTimes(1).setSleepTime(1000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	private static final String LIST_URL = "https://www.zrfan.com/category/zhinan/";
	private static final String TARGET_URL ="https://www\\.zrfan\\.com/\\d*\\.html";

	private List<String> nodeNames = Arrays.asList("fieldset", "h2", "blockquote");
	/**
	 * 指定抓取的哪些日期的页面，格式为yyyy-MM-dd
	 */
	private List<String> dateListStr;

	public GuidePageProcessor(List<String> dateListStr) {
		super();
		this.dateListStr = dateListStr;
	}

	@Override
	public void process(Page page) {
		if (page.getUrl().regex(LIST_URL).match()) {
			Selectable links = page.getHtml().xpath("//h2[@class=\"entry-title\"]");
			List<Request> formatRequest = formatRequest(links.all());
			Pattern pattern = Pattern.compile(TARGET_URL);
			formatRequest.forEach(e -> {
				Matcher matcher = pattern.matcher(e.getUrl());
				if (matcher.matches()) {
					page.addTargetRequest(e);
				}
			});
		} else {
			List<Activity> activityList = extractActivityListFromPage(page);
			page.putField("activityListJson", JSON.toJSONString(activityList));
		}
		ZrfanUtils.randomSleep(8);
	}

	/**
	 * 
	 * 获取页面活动
	 * 
	 * @param page
	 * @return
	 */
	private List<Activity> extractActivityListFromPage(Page page) {
		Selectable singleContentStr = page.getHtml().xpath("//div[@class=\"single-content\"]");
		Document doc = Jsoup.parse(singleContentStr.get());
		Element div = doc.select("div").first();
		List<Node> childNodes = div.childNodes();
		List<Activity> result = new ArrayList<>(childNodes.size());
		String keyword = "";
		for (Node node : childNodes) {
			Document nodeDoc = Jsoup.parse(node.outerHtml());
			if ("fieldset".equals(findNodeName(nodeDoc))) {
				formatFieldset(nodeDoc, result);
			}
			if ("h2".equals(findNodeName(nodeDoc))) {
				keyword = nodeDoc.select("h2").text();
			}
			if ("blockquote".equals(findNodeName(nodeDoc))) {
				formatBlockquote(nodeDoc, keyword, result);
			}
		}
		return result;
	}

	private List<Request> formatRequest(List<String> urls) {
		if (CollectionUtils.isEmpty(urls)) {
			return Collections.emptyList();
		}
		return urls.stream().map(e -> Jsoup.parse(e))
				.filter(e -> dateListStr.contains(StringUtils.split(e.text(), "日")[0]))
				.map(e -> new Request("https:" + e.select("a").attr("href"))).collect(Collectors.toList());
	}

	public String findNodeName(Document nodeDoc) {
		for (String name : nodeNames) {
			Elements node = nodeDoc.select(name);
			if (node.size() > 0) {
				return name;
			}
		}
		return "";
	}

	private void formatBlockquote(Document nodeDoc, String keyword, List<Activity> result) {
		Elements blockquotes = nodeDoc.select("blockquote");
		for (Element element : blockquotes) {
			Activity activity = new Activity();
			String title = element.select("span").text();
			String content = element.select("a").text();
			String url = element.select("a").attr("href");
			activity.setTitle(title);
			activity.setContent(content);
			activity.setKeywords(ZrfanUtils.removeNbsp(keyword));
			activity.setUrl(ZrfanUtils.totalFormatUrl(url));
			result.add(activity);
		}
	}

	private void formatFieldset(Document nodeDoc, List<Activity> result) {
		Elements fieldsets = nodeDoc.select("fieldset");
		for (Element element : fieldsets) {
			String keyword = element.select("legend").text();
			Elements ps = element.select("p");
			for (Element p : ps) {
				Activity activity = new Activity();
				
				activity.setKeywords(ZrfanUtils.removeNbsp(keyword));
				activity.setTitle(p.text());
				Elements hasA = p.select("a");
				if (hasA.size() > 0) {
					activity.setTitle(p.text());
					activity.setUrl(ZrfanUtils.removeRedirectUrl(p.select("a").attr("href")));
				}
				result.add(activity);
			}
		}
	}

	@Override
	public Site getSite() {
		return SITE;
	}

}
