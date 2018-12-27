/**
 *CreditCardServiceImpl.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shinelon.credit.crawler.bank.bean.BankBean;
import com.shinelon.credit.crawler.bank.bean.CardBean;
import com.shinelon.credit.crawler.bank.service.CreditCardService;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;
import com.shinelon.credit.crawler.bank.bean.BankAndCardBean;

import cn.hutool.http.HttpUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * CreditCardServiceImpl.java
 *https://www.creditcard.com.cn/creditcard
 * @author syq
 *
 */
public class CreditCardServiceImpl implements CreditCardService {
	
	private static final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);
	
	private static final Site SITE = Site.me().setTimeOut(10 * 1000).setRetryTimes(1).setSleepTime(1000).setUserAgent(
			"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
	
	private static final String MAIN_LIST_URL="https://www.creditcard.com.cn/api/creditcard/filter";
	
	private static final String DETAIL_TEMPLATE="https://www.creditcard.com.cn/creditcard/detail/%s.html";
	
	@Override
	public List<BankAndCardBean> getCardMainInfo(List<BankBean> list) {
		List<BankAndCardBean> ret =new ArrayList<>(list.size());
		for (BankBean bankBean : list) {
			List<CardBean> loopFormatBankInfo = loopFormatBankInfo(bankBean.getCode());
			BankAndCardBean totalRetBean = new BankAndCardBean();
			totalRetBean.setBankInfo(bankBean);
			totalRetBean.setCardList(loopFormatBankInfo);
			ret.add(totalRetBean);
		}
		return ret;
	}
	
	
	private Page detailRequest(String cardId) {
		HttpClientDownloader downloader =new HttpClientDownloader();
		Request request = new Request();
		request.setUrl(String.format(DETAIL_TEMPLATE, cardId));
		return downloader.download(request, SITE.toTask());

		
	}
	
	private List<String> getFees(Page page){
//		logger.info("page:{}",page.getHtml());
		List<String> ret =new ArrayList<>();
		String payRuleHtml = page.getHtml().xpath("//[@data-id=\"pay-rule\"]").get();
		Document doc = Jsoup.parse(payRuleHtml);
		Elements select = doc.select("dd");
		for (Element element : select) {
			String lable = element.attr("data-label");
			if(StringUtils.contains(lable, "年费")) {
//				logger.info("{}",lable);
//				logger.info("{}",element.text());
				ret.add(lable+":"+element.text());
			}
		}
		return ret;
	}
	
	
	
	private List<CardBean> loopFormatBankInfo(String bankCode){
		List<CardBean> ret = new ArrayList<>(50);
		Integer page = 1;
		Integer rows = 8;
		while (true) {
			List<CardBean> requestBankInfo = requestBankInfo(MAIN_LIST_URL, bankCode, page, rows);
			page ++;
			if(CollectionUtils.isEmpty(requestBankInfo)) {
				break;
			}
			ZrfanUtils.randomSleep(4);
			formatFees(requestBankInfo);
			ret.addAll(requestBankInfo);
		}
		return ret;
	}
	
	private void formatFees(List<CardBean> cards) {
		for (CardBean cardBean : cards) {
			Page page = detailRequest(cardBean.getId());
			List<String> fees = getFees(page);
			cardBean.setFees(fees);
			ZrfanUtils.randomSleep(4);
		}
	}
	
	private List<CardBean> requestBankInfo(String url,String bankCode,Integer page,Integer rows) {
		String formatRequestJson = formatRequestJson(bankCode, page, rows);
		logger.info("requestJson:{}",formatRequestJson);
		String postRet = HttpUtil.post(url, formatRequestJson);
		logger.info("postRet:{}",postRet);
		JSONObject retObj = JSON.parseObject(postRet);
		Integer retCode = retObj.getInteger("code");
		if(!Integer.valueOf(0).equals(retCode)) {
			return Collections.emptyList();
		}
		JSONArray jsonArray = retObj.getJSONObject("data").getJSONArray("cardList");
		return JSON.parseArray(jsonArray.toJSONString(), CardBean.class);
	}
	
	private String formatRequestJson(String bankCode,Integer page,Integer rows) {
		JSONObject bodyJson = new JSONObject();
		JSONObject bankObj= new JSONObject();
		bankObj.put("bank", bankCode);
		bodyJson.put("condition", bankObj);
		bodyJson.put("page",page);
		bodyJson.put("rows", rows);
		return bodyJson.toJSONString();
	}
	
	
}
