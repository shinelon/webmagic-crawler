/**
 *ZhinanTargetHtmlTest.java 
 *
 * 2018年11月27日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.html;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.shinelon.credit.crawler.zrfan.model.Activity;
import com.shinelon.credit.crawler.zrfan.utils.ZrfanUtils;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * ZhinanTargetHtmlTest.java
 *
 * @author syq
 *
 */
public class ZhinanTargetHtmlTest {
	
	private static final Logger logger = LoggerFactory.getLogger(ZhinanTargetHtmlTest.class);
	
	private String removePrefix="//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=";
	
	private List<String> nodeNames=Arrays.asList("fieldset","h2","blockquote");
	
	@Test
	public void formatActivity() {
		Html listHtml = new Html(zhinanHtmlStr);
		Selectable singleContentStr = listHtml.xpath("//div[@class=\"single-content\"]");
//		logger.info("single-content:{}",singleContentStr.get());
		Document doc = Jsoup.parse(singleContentStr.get());
		Element div = doc.select("div").first();

		List<Node> childNodes = div.childNodes();
		List<Activity> result = new ArrayList<>(childNodes.size());

		String keyword = "";
		for (Node node : childNodes) {
//			logger.info("node:{}",node);
			Document nodeDoc = Jsoup.parse(node.outerHtml());
//			logger.info("nodeDoc:{}",nodeDoc);
			if ("fieldset".equals(findNodeName(nodeDoc))) {
//				logger.info("fieldset...");
				formatFieldset(nodeDoc, result);
			}
//			if ("h2".equals(findNodeName(nodeDoc))) {
////				logger.info("h2...{}",nodeDoc.select("h2").text());
//				keyword = nodeDoc.select("h2").text();
//			}
//			if ("blockquote".equals(findNodeName(nodeDoc))) {
////				logger.info("blockquote...");
//				formatBlockquote(nodeDoc, keyword, result);
//			}
		}
		logger.info("result:{}", JSON.toJSONString(result));
	}
	
	public String findNodeName(Document nodeDoc) {
		for (String name : nodeNames) {
			Elements node = nodeDoc.select(name);
			if(node.size()>0) {
				return name;
			}
		}
		return "";
	}
	
	private void formatBlockquote(Document nodeDoc, String keyword, List<Activity> result) {
		Elements blockquotes = nodeDoc.select("blockquote");
//		logger.info("blockquote:{}", blockquotes);
		for (Element element : blockquotes) {
			Activity activity = new Activity();
			String title=element.select("span").text();
			String content=element.select("a").text();
			String url=element.select("a").attr("href");
			activity.setTitle(title);
			activity.setContent(content);
			activity.setKeywords(keyword);
			activity.setUrl(ZrfanUtils.totalFormatUrl(url));
			result.add(activity);
		}
	}
	
	private void formatFieldset(Document nodeDoc, List<Activity> result) {
		Elements fieldsets = nodeDoc.select("fieldset");
//		logger.info("fieldsets:{}", fieldsets);
		for (Element element : fieldsets) {
//			logger.info("keywords:{}",element.select("legend").text());;
			String keyword = element.select("legend").text();
			Elements ps = element.select("p");
			for (Element p : ps) {
				Activity activity = new Activity();
				activity.setKeywords(keyword);
				activity.setTitle(p.text());
				Elements hasA = p.select("a");
				if (hasA.size() > 0) {
					activity.setTitle(p.text());
					activity.setUrl(ZrfanUtils.removeRedirectUrl(p.select("a").attr("href")));
//			    	logger.info("title1:{}",p.text());
//			    	logger.info("url1:{}",StringUtils.remove(p.select("a").attr("href"), remove_prefix));
				}
				result.add(activity);
			}
		}
	}
	
	
	@Test
	public void findFieldsets() {
		Html listHtml= new Html(zhinanHtmlStr);
		//Selectable singleContentStr = listHtml.xpath("//div[@class=\"single-content\"]");
//		logger.info("single-content:{}",singleContentStr.get());
		Document doc = Jsoup.parse(zhinanHtmlStr);
		Elements fieldsets = doc.select("fieldset");
		logger.info("fieldsets:{}",fieldsets);
		for (Element element : fieldsets) {
			logger.info("keywords:{}",element.select("legend").text());;
			Elements ps = element.select("p");
			for (Element p : ps) {
				Elements hasA = p.select("a");
			    if(hasA.size()>0) {
			    	logger.info("title1:{}",p.text());
			    	logger.info("url1:{}",StringUtils.remove(p.select("a").attr("href"), removePrefix));
			    }else {
			    	logger.info("title2:{}",p.text());
			    }
			}
		}
		
		
	}
	
	
	private String zhinanHtmlStr="\r\n" + 
			"<!DOCTYPE html>\r\n" + 
			"<html lang=\"zh-CN\">\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"UTF-8\">\r\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">\r\n" + 
			"<meta http-equiv=\"Cache-Control\" content=\"no-transform\" />\r\n" + 
			"<meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\r\n" + 
			"<meta name=\"baidu_union_verify\" content=\"840d7e94142d30e03eb831b802362791\">\r\n" + 
			"<title>2018-11-27日周二刷卡指南 | 羊毛优惠</title>\r\n" + 
			"<meta name=\"description\" content=\" 提醒漂亮妈妈卡6积分抢兑\" />\r\n" + 
			"<meta name=\"keywords\" content=\"每日刷卡指南\" />\r\n" + 
			"<link rel=\"shortcut icon\" href=\"https://static.zrfan.com/wp-content/themes/begin/img/favicon.ico\">\r\n" + 
			"<link rel=\"apple-touch-icon\" sizes=\"114x114\" href=\"https://static.zrfan.com/wp-content/themes/begin/img/favicon.png\" />\r\n" + 
			"<link rel=\"profile\" href=\"http://gmpg.org/xfn/11\">\r\n" + 
			"<link rel=\"pingback\" href=\"//www.zrfan.com/xmlrpc.php\">\r\n" + 
			"<!--[if lt IE 9]>\r\n" + 
			"<script src=\"https://static.zrfan.com/wp-content/themes/begin/js/html5.js\"></script>\r\n" + 
			"<script src=\"https://static.zrfan.com/wp-content/themes/begin/js/css3-mediaqueries.js\"></script>\r\n" + 
			"<![endif]-->\r\n" + 
			"<link rel='stylesheet' id='dwqa-style-css'  href='https://static.zrfan.com/wp-content/plugins/dw-question-answer/templates/assets/css/style.css?ver=180720161352' type='text/css' media='all' />\r\n" + 
			"<link rel='stylesheet' id='dwqa-rtl-css'  href='https://static.zrfan.com/wp-content/plugins/dw-question-answer/templates/assets/css/rtl.css?ver=180720161352' type='text/css' media='all' />\r\n" + 
			"<link rel='stylesheet' id='begin-style-css'  href='https://static.zrfan.com/wp-content/themes/begin/style.css?ver=4.7.11' type='text/css' media='all' />\r\n" + 
			"<link rel='stylesheet' id='fonts-css'  href='https://static.zrfan.com/wp-content/themes/begin/css/fonts.css?ver=2017.02.04' type='text/css' media='all' />\r\n" + 
			"<link rel='stylesheet' id='dw-css'  href='https://static.zrfan.com/wp-content/themes/begin/css/dw.css?ver=2017.02.04' type='text/css' media='all' />\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/jquery.min.js?ver=1.10.1'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/slides.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/jquery.qrcode.min.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/wow.js?ver=0.1.9'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/sticky.js?ver=1.6.0'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/jquery-ias.js?ver=2.2.1'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/jquery.lazyload.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/tipso.js?ver=1.0.1'></script>\r\n" + 
			"<script type='text/javascript'>\r\n" + 
			"/* <![CDATA[ */\r\n" + 
			"var wpl_ajax_url = \"https:\\/\\/www.zrfan.com\\/wp-admin\\/admin-ajax.php\";\r\n" + 
			"/* ]]> */\r\n" + 
			"</script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/script.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/flexisel.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/fancybox.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/comments-ajax-qt.js?ver=2017.02.04'></script>\r\n" + 
			"<link rel=\"canonical\" href=\"//www.zrfan.com/2462.html\" />\r\n" + 
			"<style>.single-content img{margin: 0 auto;}\r\n" + 
			"@media screen and (max-width: 550px) {\r\n" + 
			"    .add-info {\r\n" + 
			"        display: none;\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"#site-nav .down-menu li {\r\n" + 
			"    font-weight: bold;\r\n" + 
			"    font-size: 15px;\r\n" + 
			"}\r\n" + 
			"@media screen and (max-width: 550px) {\r\n" + 
			"    .add-info {\r\n" + 
			"        display: none;\r\n" + 
			"    }\r\n" + 
			"}\r\n" + 
			"\r\n" + 
			".stamp {width: 330px;height: 140px;padding: 0 10px;position: relative;overflow: hidden;margin:5px;}\r\n" + 
			".stamp:before {content: '';position: absolute;top:0;bottom:0;left:10px;right:10px;z-index: -1;}\r\n" + 
			".stamp:after {content: '';position: absolute;left: 10px;top: 10px;right: 10px;bottom: 10px;box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.5);z-index: -2;}\r\n" + 
			".stamp .par{float: left;padding: 16px 15px;width: 220px;border-right:2px dashed rgba(255,255,255,.3);text-align: left;}\r\n" + 
			".stamp .par p{color:#fff;}\r\n" + 
			".stamp .par span{font-size: 50px;color:#fff;margin-right: 5px;}\r\n" + 
			".stamp .par .sign{font-size: 34px;}\r\n" + 
			".stamp .par sub{position: relative;top:-5px;color:rgba(255,255,255,.8);}\r\n" + 
			".stamp .copy{display: inline-block;padding:21px 14px;width:80px;vertical-align: text-bottom;font-size: 30px;color:rgb(255,255,255);}\r\n" + 
			".stamp .copy p{font-size: 16px;margin-top: 15px;}\r\n" + 
			"\r\n" + 
			".stamp01{background: #F39B00;background: radial-gradient(rgba(0, 0, 0, 0) 0, rgba(0, 0, 0, 0) 5px, #F39B00 5px);background-size: 15px 15px;background-position: 9px 3px;float:left}\r\n" + 
			".stamp01:before{background-color:#F39B00;}\r\n" + 
			".stamp01 .copy a{background-color:#e07c00;color:#fff;font-size: 16px;text-decoration:none;padding:25px 10px;border-radius:35px;display: block;}\r\n" + 
			"\r\n" + 
			".container{\r\n" + 
			"            width: 100%;\r\n" + 
			"            height: 350px;\r\n" + 
			"            border:3px black solid;\r\n" + 
			"        }</style><script async src=\"//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\r\n" + 
			"<script>\r\n" + 
			"  (adsbygoogle = window.adsbygoogle || []).push({\r\n" + 
			"    google_ad_client: \"ca-pub-2207325541606966\",\r\n" + 
			"    enable_page_level_ads: true\r\n" + 
			"  });\r\n" + 
			"</script></head>\r\n" + 
			"<body class=\"post-template-default single single-post postid-15850 single-format-standard\">\r\n" + 
			"<div id=\"page\" class=\"hfeed site\">\r\n" + 
			"	<header id=\"masthead\" class=\"site-header\">\r\n" + 
			"\r\n" + 
			"	<nav id=\"top-header\">\r\n" + 
			"		<div class=\"top-nav\">\r\n" + 
			"							<div id=\"user-profile\">\r\n" + 
			"					<div class=\"user-login\">欢迎光临！</div>\r\n" + 
			"			\r\n" + 
			"						<div class=\"nav-set\">\r\n" + 
			"			 	<div class=\"nav-login\">\r\n" + 
			"			 					<a href=\"#login\" class=\"flatbtn\" id=\"login-main\" ><i class=\"fa fa-sign-in\"></i>登录</a>\r\n" + 
			"								</div>\r\n" + 
			"			</div>\r\n" + 
			"					<div class=\"clear\"></div>\r\n" + 
			"</div>			\r\n" + 
			"			<div class=\"menu-%e9%a1%b6%e9%83%a8-container\"><ul id=\"menu-%e9%a1%b6%e9%83%a8\" class=\"top-menu\"><li id=\"menu-item-8069\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-8069\"><a href=\"//www.zrfan.com/dada\">活动问答</a></li>\r\n" + 
			"<li id=\"menu-item-7908\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7908\"><a href=\"//www.zrfan.com/blog\">最新优惠</a></li>\r\n" + 
			"<li id=\"menu-item-7845\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7845\"><a href=\"//www.zrfan.com/phone\">充值优惠</a></li>\r\n" + 
			"<li id=\"menu-item-8159\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-8159\"><a href=\"//www.zrfan.com/qian\">还款优惠</a></li>\r\n" + 
			"</ul></div>		</div>\r\n" + 
			"	</nav><!-- #top-header -->\r\n" + 
			"\r\n" + 
			"	<div id=\"menu-box\">\r\n" + 
			"		<div id=\"top-menu\">\r\n" + 
			"			<span class=\"nav-search\"><i class=\"fa fa-search\"></i></span>\r\n" + 
			"												<span class=\"mobile-login\"><a href=\"#login\" id=\"login-mobile\" ><i class=\"fa fa-user\"></i></a></span>\r\n" + 
			"													<div class=\"logo-sites\">\r\n" + 
			"																		<p class=\"site-title\">\r\n" + 
			"															<a href=\"//www.zrfan.com/\"><img src=\"//www.zrfan.com/cdn/2017/02/20170207101722231.png\" title=\"羊毛优惠\" alt=\"羊毛优惠\" rel=\"home\" /><span class=\"site-name\">羊毛优惠</span></a>\r\n" + 
			"													</p>\r\n" + 
			"													</div><!-- .logo-site -->\r\n" + 
			"\r\n" + 
			"			<div id=\"site-nav-wrap\">\r\n" + 
			"				<div id=\"sidr-close\"><a href=\"#sidr-close\" class=\"toggle-sidr-close\">×</a></div>\r\n" + 
			"				<nav id=\"site-nav\" class=\"main-nav\">\r\n" + 
			"															<a href=\"#sidr-main\" id=\"navigation-toggle\" class=\"bars\"><i class=\"fa fa-bars\"></i></a>\r\n" + 
			"														<div class=\"menu-%e4%b8%bb%e8%8f%9c%e5%8d%95-container\"><ul id=\"menu-%e4%b8%bb%e8%8f%9c%e5%8d%95\" class=\"down-menu nav-menu\"><li id=\"menu-item-7729\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7729\"><a href=\"//www.zrfan.com/category/jingxuan/\"><i class=\"fa-star fa\"></i><span class=\"font-text\">精选</span></a></li>\r\n" + 
			"<li id=\"menu-item-12372\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category current-post-ancestor current-menu-parent current-post-parent menu-item-12372\"><a href=\"//www.zrfan.com/category/zhinan/\"><i class=\"fa-book fa\"></i><span class=\"font-text\">每日刷卡指南</span></a></li>\r\n" + 
			"<li id=\"menu-item-7733\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-7733\"><a href=\"//www.zrfan.com/category/bank/\"><i class=\"fa-credit-card-alt fa\"></i><span class=\"font-text\">银行卡</span></a>\r\n" + 
			"<ul class=\"sub-menu\">\r\n" + 
			"	<li id=\"menu-item-7753\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-7753\"><a href=\"//www.zrfan.com/category/guoyou/\">四大国有银行</a>\r\n" + 
			"	<ul class=\"sub-menu\">\r\n" + 
			"		<li id=\"menu-item-7743\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7743\"><a href=\"//www.zrfan.com/category/bank/gongshang/\">工商银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7736\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7736\"><a href=\"//www.zrfan.com/category/bank/zhonghang/\">中国银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7740\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7740\"><a href=\"//www.zrfan.com/category/bank/nongye/\">农业银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7746\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7746\"><a href=\"//www.zrfan.com/category/bank/jianshe/\">建设银行优惠活动</a></li>\r\n" + 
			"	</ul>\r\n" + 
			"</li>\r\n" + 
			"	<li id=\"menu-item-7752\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-7752\"><a href=\"//www.zrfan.com/category/gufen/\">全国股份制银行</a>\r\n" + 
			"	<ul class=\"sub-menu\">\r\n" + 
			"		<li id=\"menu-item-7735\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7735\"><a href=\"//www.zrfan.com/category/bank/youchu/\">中国邮政储蓄银行</a></li>\r\n" + 
			"		<li id=\"menu-item-7747\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7747\"><a href=\"//www.zrfan.com/category/bank/zhaoshang/\">招商银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7734\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7734\"><a href=\"//www.zrfan.com/category/bank/zhongxin/\">中信银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7737\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7737\"><a href=\"//www.zrfan.com/category/bank/jiaotong/\">交通银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7738\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7738\"><a href=\"//www.zrfan.com/category/bank/guangda/\">光大银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7745\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7745\"><a href=\"//www.zrfan.com/category/bank/guangfa/\">广发银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7739\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7739\"><a href=\"//www.zrfan.com/category/bank/xingye/\">兴业银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7748\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7748\"><a href=\"//www.zrfan.com/category/bank/minsheng/\">民生银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7749\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7749\"><a href=\"//www.zrfan.com/category/bank/pufa/\">浦发银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7742\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7742\"><a href=\"//www.zrfan.com/category/bank/hauxia/\">华夏银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7744\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7744\"><a href=\"//www.zrfan.com/category/bank/pingan/\">平安银行优惠活动</a></li>\r\n" + 
			"	</ul>\r\n" + 
			"</li>\r\n" + 
			"	<li id=\"menu-item-7754\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-7754\"><a href=\"//www.zrfan.com/category/difang/\">地方性银行</a>\r\n" + 
			"	<ul class=\"sub-menu\">\r\n" + 
			"		<li id=\"menu-item-8367\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-8367\"><a href=\"//www.zrfan.com/category/bank/zheshang/\">浙商银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7741\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7741\"><a href=\"//www.zrfan.com/category/bank/beijing/\">北京银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-7929\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7929\"><a href=\"//www.zrfan.com/category/bank/shanghai/\">上海银行优惠活动</a></li>\r\n" + 
			"	</ul>\r\n" + 
			"</li>\r\n" + 
			"	<li id=\"menu-item-7755\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-7755\"><a href=\"//www.zrfan.com/category/waizi/\">外资银行</a>\r\n" + 
			"	<ul class=\"sub-menu\">\r\n" + 
			"		<li id=\"menu-item-7750\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7750\"><a href=\"//www.zrfan.com/category/bank/zhada/\">渣打银行优惠活动</a></li>\r\n" + 
			"		<li id=\"menu-item-8366\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-8366\"><a href=\"//www.zrfan.com/category/bank/%e6%b1%87%e4%b8%b0/\">汇丰银行优惠活动</a></li>\r\n" + 
			"	</ul>\r\n" + 
			"</li>\r\n" + 
			"	<li id=\"menu-item-7732\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7732\"><a href=\"//www.zrfan.com/category/huafei/yinlian/\">银联</a></li>\r\n" + 
			"</ul>\r\n" + 
			"</li>\r\n" + 
			"<li id=\"menu-item-7727\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7727\"><a href=\"//www.zrfan.com/category/lichai/\"><i class=\"fa-money fa\"></i><span class=\"font-text\">理财</span></a></li>\r\n" + 
			"<li id=\"menu-item-7731\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7731\"><a href=\"//www.zrfan.com/category/huafei/\"><i class=\"fa-phone-square fa\"></i><span class=\"font-text\">话费</span></a></li>\r\n" + 
			"<li id=\"menu-item-7730\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7730\"><a href=\"//www.zrfan.com/category/wangluozhifu/\"><i class=\"fa-internet-explorer fa\"></i><span class=\"font-text\">网付</span></a></li>\r\n" + 
			"<li id=\"menu-item-12683\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-has-children menu-item-12683\"><a href=\"//www.zrfan.com/category/youhuiquan/\"><i class=\"fa-youtube-play fa\"></i><span class=\"font-text\">优惠券</span></a>\r\n" + 
			"<ul class=\"sub-menu\">\r\n" + 
			"	<li id=\"menu-item-12684\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-12684\"><a href=\"//www.zrfan.com/jdquan\">京东优惠券</a></li>\r\n" + 
			"</ul>\r\n" + 
			"</li>\r\n" + 
			"<li id=\"menu-item-7724\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7724\"><a href=\"//www.zrfan.com/category/yongkazhinan/\"><i class=\"fa-graduation-cap fa\"></i><span class=\"font-text\">用卡指南</span></a></li>\r\n" + 
			"</ul></div>				</nav><!-- #site-nav -->\r\n" + 
			"			</div><!-- #site-nav-wrap -->\r\n" + 
			"			<div class=\"clear\"></div>\r\n" + 
			"		</div><!-- #top-menu -->\r\n" + 
			"	</div><!-- #menu-box -->\r\n" + 
			"</header><!-- #masthead -->\r\n" + 
			"\r\n" + 
			"<div id=\"search-main\">\r\n" + 
			"	<div class=\"searchbar\">\r\n" + 
			"	<form method=\"get\" id=\"searchform\" action=\"//www.zrfan.com/\">\r\n" + 
			"		<span>\r\n" + 
			"			<input type=\"text\" value=\"\" name=\"s\" id=\"s\" placeholder=\"输入搜索内容\" required />\r\n" + 
			"			<button type=\"submit\" id=\"searchsubmit\">搜索</button>\r\n" + 
			"		</span>\r\n" + 
			"\r\n" + 
			"			</form>\r\n" + 
			"</div>		<div class=\"clear\"></div>\r\n" + 
			"</div>		<nav class=\"breadcrumb\">\r\n" + 
			"		<a class=\"crumbs\" href=\"//www.zrfan.com/\"><i class=\"fa fa-home\"></i>首页</a><i class=\"fa fa-angle-right\"></i><a href=\"//www.zrfan.com/category/zhinan/\" rel=\"category tag\">每日刷卡指南</a><i class=\"fa fa-angle-right\"></i>正文	</nav>\r\n" + 
			"			<div class=\"header-sub\">\r\n" + 
			"					<div class=\"ad-pc ad-site\"><script async src=\"//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\r\n" + 
			"<!-- 评论 -->\r\n" + 
			"<ins class=\"adsbygoogle\"\r\n" + 
			"     style=\"display:block\"\r\n" + 
			"     data-ad-client=\"ca-pub-2207325541606966\"\r\n" + 
			"     data-ad-slot=\"9555081399\"\r\n" + 
			"     data-ad-format=\"auto\"></ins>\r\n" + 
			"<script>\r\n" + 
			"(adsbygoogle = window.adsbygoogle || []).push({});\r\n" + 
			"</script></div>				<div class=\"clear\"></div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		<div id=\"content\" class=\"site-content\">\r\n" + 
			"	<div id=\"primary\" class=\"content-area\">\r\n" + 
			"		<main id=\"main\" class=\"site-main\" role=\"main\">\r\n" + 
			"\r\n" + 
			"			\r\n" + 
			"				<article id=\"post-15850\" class=\"post-15850 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\">\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"									<h1 class=\"entry-title\">2018-11-27日周二刷卡指南</h1>						</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"single-content\">\r\n" + 
			"				\r\n" + 
			"				\r\n" + 
			"				<fieldset>\r\n" + 
			"<legend> 提醒</legend>\r\n" + 
			"<p><strong>漂亮妈妈卡6积分抢兑</strong></p>\r\n" + 
			"<p>每日9点起，翼支付APP-中间“领取50”-邀请6位好友助力可得10-50翼支付金</p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s?__biz=MjM5MDcxNzI3NA==&amp;mid=2247489278&amp;idx=2&amp;sn=3a9a2b327dfc001f6abae8e2b15373d8&amp;scene=21#wechat_redirect\"  target=\"_blank\" rel=\"noopener noreferrer\">中行9点起每个整点抢50元商城券（11月30日截止）</a></p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=http://t.cn/EzoQous\" >http://t.cn/EzoQous</a> <span style=\"color: #ff0000;\">每天领京东支付京东闪付5-1红包，新户5-5</span></p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://u.jd.com/27VQtf\" >https://u.jd.com/27VQtf</a> 抽京东支付券，可扫码或红闪</p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=http://t.cn/Ew6CzBa\" >http://t.cn/Ew6CzBa</a> 苏宁新用户可领30元无门槛券，一身份证可实名两个苏宁号（不同手机号）</fieldset>\r\n" + 
			"<h2>特别活动</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2189.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">6积分被子：</span></strong></span>9月1日-10月31日，漂亮升级妈妈卡或小玩卡消费5笔，且累计满5000元，可11月27日<strong>10点起</strong>，登录掌上银行APP-精品秒杀-乐刷金秋6积分，6积分兑换水星家纺塔图羽丝绒被（3000个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://www.sc.com/cn/credit-cards/offers.html?pid=\" ><span style=\"color: #800000;\"><strong>渣打银行x<span style=\"color: #ff6600;\">网购</span></strong></span>：发送注册代码FX16到“渣打中国”官方微信，注册成功且当月计积分消费满8000元人民币，周二支付宝/银联在线返现5%，臻程卡每月最高返现200元，真逸卡每月最高返现100元</a>（<span style=\"color: #333333;\">续期至12月31日截止</span>）</p></blockquote>\r\n" + 
			"<h2><strong>0点开始活动</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2040.html\"><span style=\"color: #800000;\"><strong>银联x<span style=\"color: #ff6600;\">各种pay激励金：</span></strong></span>指定地区活动商户，使用对应银联手机闪付（<span style=\"color: #ff0000;\"><strong>周二Apple Pay</strong></span>），可享受：消费满5元，返2元激励金；消费满100元，有机会返62元激励金（每卡每日5次，每设备每日5次，每商户每日1次）（19年3月31日截止，个别地区个别商户不参加）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2438.html\"><span style=\"color: #800000;\"><strong>银联云闪付x<span style=\"color: #ff6600;\">中粮我买网：</span></strong></span>中粮我买网通过银联云闪付或各种PAY支付，满99-15元（共6w个名额，每日1次，12月31日截止，建议使用云闪付APP，付款前能看到优惠字样）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2437.html\"><span style=\"color: #800000;\"><strong>银联云闪付x<span style=\"color: #ff6600;\">孩子王：</span></strong></span>孩子王APP使用云闪付APP支付或使用Apple Pay、Huawei Pay、Samsung Pay、Mi Pay支付，满100-20元（限量，每日1次，每月2次，12月19日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>翼支付X</strong><span style=\"color: #ff6600;\"><strong>电商随机减：</strong></span></span>翼支付APP，进入京东、唯品会、网易严选、、本来生活、小米商城、淘票票、猫眼等满39元随机立减，最高39元（每日3500个名额，仅1次，11月30日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2446.html\"><span style=\"color: #800000;\"><strong>工商银行x<span style=\"color: #ff6600;\">猫眼观影：</span></strong></span>每日<strong>0点起</strong>，使用工银信用卡在“猫眼电影”APP选择工商银行e支付支付方式完成在线付款可享60-20元（每日4000个名额，每户每日1次，12月10日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2457.html\"><span style=\"color: #800000;\"><strong>工商银行x<span style=\"color: #ff6600;\">微信随机减：</span></strong></span>工商银行信用卡绑定微信支付，在京东、亚马逊、苏宁易购、美团、大众点评、滴滴等平台支付，满10元随机立减1-999元（可购买京东电子卡，自定义10元，有空的朋友可以卡金额）（每日133088个名额，11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2440.html\"><span style=\"color: #800000;\"><strong>工商银行x<span style=\"color: #ff6600;\">京东沃尔玛：</span></strong></span>每日<strong>0点起</strong>，京东沃尔玛、山姆全球购、山姆会员商店、沃尔玛全球购、ASDA店铺满200-50元（500个名额），满88-18元（1500个名额），每日10点还可抢188元支付神券（每户1次，11月30日截止</a>）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2365.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>美团20-5元：</strong></span>每日<strong>0点起</strong>，农行信用卡在美团点评旗下APP（包括美团APP、大众点评APP或美团外卖APP）支付，满20-5元（每日6000个名额，每户5次）；首笔立减10元（每日2000个名额）（12月10日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">借记卡唯品会：</span></strong></span><a href=\"//www.zrfan.com/2431.html\">每日<strong>0点起</strong>，唯品会APP，选择银联手机闪付（Apple Pay）进行支付，消费满100-30元；如选择农行Ⅱ、Ⅲ类电子账户绑定ApplePay支付，消费满100-50元（共32000个名额，每周1次）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2363.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">京东288-10元：</span></strong></span>每日<strong>0点起</strong>，京东购买实物使用农行卡支付，满288-10元（每日1000个名额，每天1次，12月10日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2405.html\"><span style=\"color: #800000;\"><strong>北京银行x<span style=\"color: #ff6600;\">京彩京东299-50元：</span></strong></span>每日在北京银行信用卡APP“掌上精彩”-京彩京东下单，满299-50元（每日180个名额，每户每月1次，12月13日截止）首次绑定京东支付可获8元立减券（180个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2397.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">美团外卖20-5元：</span></strong></span>每日<strong>0点起</strong>，美团外卖使用光大信用卡支付，满20-5元（每日5000个名额，每户每周一次，19年3月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1983.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">去哪儿满减：</span></strong></span>每日<strong>0点起</strong>，去哪儿旅行APP或去哪儿网使用光大信用卡购买酒店、机票、旅游全品类可享满300-30元优惠；满600-60元优惠；满1000-100元优惠（每户每月1次，每日名额随机）</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2114.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">中青旅：</span></strong></span>每日<strong>0点起</strong>，中青旅遨游网PC电脑端、手机WAP端和“遨游旅行”APP购买指定旅游产品，并使用银联渠道支付，满300-100元（每日200个名额，每户1次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1986.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>永乐票务：</strong></span>每日<strong>0点起</strong>，永乐官方网站通过光大银行快捷支付全额支付订单购买指定票品（北京故宫博物院，永乐票务 网站或APP销售的电影门票，乐通卡除外），享满300-100元（每日400个名额，每户每月2次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1984.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>苏宁小店：</strong></span>周一至周五（开门即可使用），苏宁小店消费，使用苏宁金融付款码、苏宁小店付款码或苏宁易购钱包付款码选择光大信用卡支付，享满10-9元优惠（每日4000个名额，每户每周3次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://jinrong.suning.com/pczfpfxiaodian.html?from=singlemessage&amp;isappinstalled=0\" ><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">借记卡苏宁小店：</span></strong></span>每日（开门即可使用），苏宁小店消费，使用苏宁扫码付选择浦发借记卡支付，享满15-10元优惠（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2277.html\"><span style=\"color: #800000;\"><strong>建行龙支付x<span style=\"color: #ff6600;\">中影观影：</span></strong></span>中影电影手机APP平台，使用建行手机银行龙支付钱包、快贷付或龙支付绑定的带“惠”字标识的建行卡支付，满30-15元活动优惠（12月15日截止，每户3次）</a></p></blockquote>\r\n" + 
			"<h2>6点开始活动</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2095.html\"><span style=\"color: #800000;\"><strong>建设银行x<span style=\"color: #ff6600;\">12306：</span></strong></span>每日12306使用建行龙支付付款（有惠字提醒），首单满50-10元，2-5次满50随机立减0.5-20元（12月15日截止，12306在6点开始订票）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s/4TDTo6cC8-rPVqZGF2bZDQ\" ><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">银联云闪付x</span>12306：</strong></span>在12306选择中国银联付款并调用“云闪付APP”付款，随机享最高62元立减优惠（19年1月7日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>借记卡真功夫</strong></span>：<a href=\"//www.zrfan.com/2431.html\">每日<strong>6点起</strong>，真功夫全国指定门店使用银联二维码（限云闪付APP、农行掌上银行APP）进行支付，消费满30-10元；如选择农行Ⅱ、Ⅲ类电子账户支付，消费满30元-15元（1600个名额，每日1次）</a></p></blockquote>\r\n" + 
			"<h2>7点开始活动</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2354.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">华夏银行x</span>商超随机减：</strong></span>每日<strong>7点起</strong>，欧尚、永旺、家乐福、卜蜂莲花、物美、见福便利、人人乐超市、世纪联华、联华华联、快客、苏宁小店、步步高、屈臣氏、广东7-11、娇兰佳人，使用银联二维码绑定华夏信用卡支付满2元随机立减0,1-999元（总金额1亿，19年3月31日截止）</a></p></blockquote>\r\n" + 
			"<h2><strong>8点开始活动</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2097.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">快客：</span></strong></span>周二<strong>8点起</strong>，快客上海、苏州、杭州、南京、宁波、北京、大连地区指定门店，浦发62信用卡或借记卡使用银联二维码支付，满30元随机立减5-15元（每户每月信用卡借记卡各一次，每日各3900个名额）</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2342.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>淘宝天猫：</strong></span>每日<strong>8点起</strong>，淘宝天猫购物使用兴业信用卡支付满188-18元（每日5k名额，每月1次，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2302.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>支付宝首绑：</strong></span>每日<strong>8点</strong>起，首次使用兴业信用卡绑定支付宝快捷支付，淘宝天猫或淘票票消费满20-10元（50w个名额，12月31日截止，双十一双十二例外）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1650.html\"><span style=\"color: #800000;\"><strong>华夏银行x<span style=\"color: #ff6600;\">汉堡王</span></strong></span>：周二周六<strong>8点起</strong>，使用华夏银行信用卡的云闪付功能，在商户指定机具上成功验证并支付1元后（打印小票视为验证成功），可获得汉堡王指定早餐套餐（每日600个名额）（12月31日截止）</a>（<span style=\"color: #ff0000;\">线下</span>）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1993.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">邮储银行x</span>华莱士：</strong></span>每日<strong>8点起</strong>，邮储银行手机银行或云闪付APP绑定邮储银行借记卡和信用卡在华莱士活动门店通过二维码被扫支付，即可享受“华莱士全品类产品满20元立减10元”优惠（共计40万个，借记卡每日名额1500个）（12月31日截止）</a>（<span style=\"color: #ff0000;\">线下</span>）</p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>借记卡华莱士：</strong></span><a href=\"//www.zrfan.com/2431.html\">每日<strong>8点起</strong>，华莱士指定活动门店使用银联二维码（限云闪付APP、农行掌上银行APP）进行支付，消费满20-10元（1880个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2418.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">工商银行x</span>工银e生活随机减：</strong></span>每日<strong>8点12点起</strong>，工银e生活购买手机充值、加油、外卖、游戏充值、视频会员、机票、火车票、酒店，满10元随机立减2-10元（每日1单，限量，12月31日截止）</a></p></blockquote>\r\n" + 
			"<h2><strong>9点开始活动</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2320.html\"><span style=\"color: #800000;\"><strong>民生银行x<span style=\"color: #ff6600;\">永辉：</span></strong></span>每日9<strong>点</strong>-11点，民生银行62银联卡（信用卡或借记卡）用户在全国永辉活动门店消费，通过民生手机银行APP、民生直销银行APP、云闪付APP二维码付款，满10元随机立减6.2-62元（每日2000个名额，11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2417.html\"><span style=\"color: #800000;\"><strong>银联云闪付x<span style=\"color: #ff6600;\">缴费20-5元：</span></strong></span>每日<strong>9点</strong>（8000个名额）<strong>12点</strong>（28000个名额）起，使用云闪付APP，通过云闪付或手机Pay进行水、电、燃气、暖气缴费，满20-5元（勾选活动）（每月每户3次，每缴费账户1次，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>Apple Payx<span style=\"color: #ff6600;\">拼多多10-5元：</span></strong></span>每日<strong>9点起</strong>，拼多多使用Apple Pay绑定62银联卡支付满10-5元（每天1次，认设备，可以选择10元话费，如果不出Apple Pay，可以下单后返回，再进行支付，付款后显示优惠，可卡余额，11月28日截止）（<span style=\"color: #ff6600;\">名额下降非常快，需要卡点了</span>）</p></blockquote>\r\n" + 
			"<h2><strong>10点开始活动</strong></h2>\r\n" + 
			"<blockquote><p><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">中国银行x</span>京东269-39元</strong></span>：每日<strong>10点起</strong>，京东购买实物，首次绑定中行信用卡使用京东支付，满269-69元（1500个名额）；老用户满269-39元（5500个名额，每户1次）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2388.html\"><span style=\"color: #800000;\"><strong>中国银行x<span style=\"color: #ff6600;\">99积分：</span></strong></span>周二<strong>10点起</strong>，手机银行APP、“缤纷生活”APP、“中国银行信用卡”官方微信-优惠券栏目，以99积分兑换餐饮类/商超类/生活服务类/视频会员类电子券一张</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1414.html\"><span style=\"color: #800000;\"><strong>中信银行x<span style=\"color: #ff6600;\">9分享兑</span></strong></span>：每日<strong>10点起</strong>，动卡空间-权益-9分权益抢星巴克等好礼（每种类每月最高兑换2次）</a>(10点20分可以尝试捡漏）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2281.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">中信银行x</span>屈臣氏：</strong></span>每日<strong>10点起</strong>，屈臣氏全国指定门店使用动卡空间或银联云闪付绑定62中信信用卡，使用银联二维码支付满50-25元（每日2500个名额，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">淘票票</span></strong></span>：每日<strong>10点起</strong>，淘票票（APP、支付宝、手机淘宝三个入口）使用浦发银行信用卡支付，满11-10元（每户每月1次）（每日1000个名额）（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://www.benlai.com/pfcobcard.jsp\" ><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">本来生活</span></strong></span>：周二<strong>10点起</strong>，浦发银行本来生活联名卡（借记卡和信用卡）支付本来生活满80-50元</a>（1500个名额）（11月30日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2145.html\"><span style=\"color: #800000;\"><strong>工商银行x<span style=\"color: #ff6600;\">易果生鲜</span></strong></span><span style=\"color: #ff6600;\">：</span>每日<strong>10点起</strong>，使用中国工商银行信用卡在易果生鲜APP选择中国工商银行e支付受理通道完成在线付款，单笔消费满50元随机立减最高20元（每日1次，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2298.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">工商银行x</span>充话费：</strong></span>每日<strong>10点起</strong>，工商银行APP - 惠生活 - 话费优惠，充值话费享50-5元优惠（每天500个名额）周四充值订单尾号为99可免单（50个名额）（19年3月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://u.jd.com/vWoEo4\" ><span style=\"color: #800000;\"><strong>工商银行x<span style=\"color: #ff6600;\">京东沃尔玛：</span></strong></span>每日10点抢沃尔玛188元支付神券（每户1次，11月30日截止） https://u.jd.com/vWoEo4</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1970.html\"><strong><span style=\"color: #800000;\">北京银行x</span><span style=\"color: #ff6600;\">汉堡王：</span></strong>周二<strong>10</strong>点起，指定链接使用银联在线支付可25元购买50元汉堡王代金券（12月31日截止）（月初需消费3笔188元）</a></p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=http://gdecard.jiahuaming.com/bob/bob6/#/\" >http://gdecard.jiahuaming.com/bob/bob6/#/</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2201.html\"><span style=\"color: #800000;\"><strong>华夏银行x<span style=\"color: #ff6600;\">借记卡加油：</span></strong></span>每日<strong>10点起</strong>（周五例外）京东商城加油卡充值使用华夏借记卡支付满488-50元（100个名额），每周五20起华夏借记卡京东绑卡用户限时1元抢购200元加油充值（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2258.html\"><span style=\"color: #800000;\"><strong>华夏银行<span style=\"color: #ff6600;\">x12306：</span></strong></span>每日<strong>10点起</strong>，12306购票使用银联通道绑定华夏银行信用卡支付，满88-30元（每日500个名额，每户每月2次，11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2362.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">邮储银行x</span>美团立减5元：</strong></span>每日<strong>10点起</strong>，美团外卖消费立减5元（每日6000个名额，每户每周1次）；新户立减5元（10w个名额，可叠加）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://creditcard.cmbc.com.cn/active/web/wonderful/active/cdjPC/index.html\" ><span style=\"color: #ff0000;\"><strong><span style=\"color: #800000;\">民生银行x</span><span style=\"color: #ff6600;\">餐饮达标抢返现：</span></strong></span>11月1日-30日，餐饮类消费满2笔，且单笔满199元可288元抢轻柔被（每日600个）；满2笔199元且总金额满5000元，可388元抢锅具四件套（每日600个）；总金额满3w元，可88积分抢超额部分1%返现（3w以上部分，11月达标，上限200元）（每日200个）或2%返现（3w以上部分，10月11月均达标，上限300元）（每日100个）（11月3日-12月2日每日<span style=\"color: #ff0000;\"><strong>10点起</strong></span>开抢）（<span style=\"color: #ff6600;\">鸡肋要命，刚需上</span>）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>借记卡屈臣氏：</strong></span><a href=\"//www.zrfan.com/2431.html\">周二周日<strong>10点起</strong>，屈臣氏指定门店使用银联二维码（限云闪付APP、农行掌上银行APP）进行支付，消费满100-20元（6600个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2189.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">6积分被子：</span></strong></span>9月1日-10月31日，漂亮升级妈妈卡或小玩卡消费5笔，且累计满5000元，可11月27日<strong>10点起</strong>，登录掌上银行APP-精品秒杀-乐刷金秋6积分，6积分兑换水星家纺塔图羽丝绒被（3000个名额）</a></p></blockquote>\r\n" + 
			"<h2>11点开始活动</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s/vjnCf1muXvM_YpBbvSXnBQ\" ><strong><span style=\"color: #800000;\">浦发银行x</span><span style=\"color: #ff6600;\">左庭右院</span></strong>：周二<strong>11点起</strong>，<span style=\"color: #000000;\">微广场—全能积分—礼券银行或登录浦大喜奔APP—礼券银行</span>，即可以50元购买100元左庭右院代金券（活动期间仅可3份）（还有其它折扣礼券）</a></p></blockquote>\r\n" + 
			"<h2>12点开始活动</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2264.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">京东满减</span></strong></span>：周一周二周四周五<strong>12点起</strong>，京东商城持光大信用卡通过京东支付消费，单笔满100-30元（每月1次）（每日3000个名额）（白金卡也可以哦！）</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2417.html\"><span style=\"color: #800000;\"><strong>银联云闪付x<span style=\"color: #ff6600;\">缴费20-5元：</span></strong></span>每日<strong>9点</strong>（8000个名额）<strong>12点</strong>（28000个名额）起，使用云闪付APP，通过云闪付或手机Pay进行水、电、燃气、暖气缴费，满20-5元（勾选活动）（每月每户3次，每缴费账户1次，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2418.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">工商银行x</span>工银e生活随机减：</strong></span>每日<strong>8点12点起</strong>，工银e生活购买手机充值、加油、外卖、游戏充值、视频会员、机票、火车票、酒店，满10元随机立减2-10元（每日1单，限量，12月31日截止）</a></p></blockquote>\r\n" + 
			"<h2>15点开始活动</h2>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>招商银行x<span style=\"color: #ff6600;\">9积分饭票：</span></strong></span>工作日<strong>15点起</strong>，掌上生活APP-饭票-下午3点（精选-下拉，9分抢购），可9积分抢兑太平洋咖啡、哈根达斯单球等美食</p></blockquote>\r\n" + 
			"<h2>其它优惠活动</h2>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>翼支付x</strong><span style=\"color: #ff6600;\"><strong>全家：</strong></span></span><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s?__biz=MzI1NjEwMjM4Mg==&amp;mid=2660364873&amp;idx=2&amp;sn=da40fa57ce9c988d4d9ae90ac44e3b7b&amp;scene=21#wechat_redirect\"  target=\"_blank\" rel=\"noopener noreferrer\">全国电信翼支付二星及以上绑卡用户在全家指定门店使用翼支付消费满20-10元（每户1次，12月12日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2313.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>便利蜂：</strong></span>周一至周五便利蜂便利店、智能货柜通过便利蜂APP并选择兴业银行信用卡支付渠道，满18-8元（每户每日1次，每月2次，每月500个名额，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2383.html\"><span style=\"color: #800000;\"><strong>银联闪付x<span style=\"color: #ff6600;\">随机减：</span></strong></span>慧择网、国通石油、佰付美、本来生活、多点使用Apple Pay在活动商户付款，满5元享随机立减，最高99元，单卡单用户在所有活动商户每天最多可享受5次优惠（19年4月30日截止，云闪付APP查看剩余名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2333.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">建设银行x</span>龙支付星巴克：</strong></span>周二周四周六建行APP-龙支付-付款码，选择建行钱包、已绑定龙支付的62开头建行借记卡或信用卡支付指定星巴克门店，享满60-20元优惠（12月8日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2056.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">建行龙支付x</span>太平洋咖啡：</strong></span>指定太平洋咖啡门店使用建行龙支付买单（限建行钱包支付，建行借记卡/信用卡绑卡支付），满28-18.1元（每日每户1次，共2次，每日名额限量）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<fieldset>\r\n" + 
			"<legend>小活动</legend>\r\n" + 
			"<p><span style=\"color: #800000;\"><strong>建</strong></span><span style=\"color: #800000;\"><strong>设银行</strong></span>pc端网银，10点起，完成转账、缴费或信用卡还款等操作，可抽30元5折话费券等（每月1次）</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><span style=\"color: #800000;\"><strong> 建</strong><strong>设银行</strong></span>APP-首页-CCB寻宝记，完任务积攒成长值可抽油卡等奖励（12月31日截止）</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><span style=\"color: #800000;\"><strong>招商银行</strong></span>掌上生活APP-生活-红包-完成今日任务，领取各种奖励</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><span style=\"color: #800000;\"><strong>招商银行</strong></span>招商掌上生活APP - 我的 - 无卡支付 - 无卡支付连连看，续期</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=http://mp.weixin.qq.com/s/PC9JGfkYa3j1BXEUZV_5gg\" ><span style=\"color: #800000;\"><strong>中国银行</strong></span>手机银行，进行10元以上动账交易，可抽小米自拍杆、小米体温计等好礼</a>（11月30日截止）</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=http://mp.weixin.qq.com/s/SqlBQSBQvof3St75f3xcjw\" ><span style=\"color: #800000;\"><strong>中国银行</strong></span>手机银行，进行1元以上非关联账户转账汇款或贵金属交易，抽金条等好礼（每月1次）（12月31日截止）</a></p>\r\n" + 
			"<hr />\r\n" + 
			"<p><span style=\"color: #800000;\"><strong>中国银行</strong></span>缤纷生活APP签到每天20分，周一至周日满7天额外奖励800分，信用卡官方微信签到每天5分，周一至周日满7天额外奖励100分，连续签到3天可抽奖</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><span style=\"color: #800000;\"><strong>工商银行</strong></span>融e购抽商城代金券（需实名登陆）</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><strong><span style=\"color: #800000;\">微信支付宝</span></strong>线下扫码得奖励（微信并非全民可享）（周末微信被扫有随机免单）</p>\r\n" + 
			"<hr />\r\n" + 
			"<p><span style=\"color: #800000;\"><strong>交通银行</strong></span>信用卡买单吧—每日福利-可抽刷卡金等奖励，如果面额合适，可以充值话费</fieldset>\r\n" + 
			"<h2>当日必做</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2375.html\"><span style=\"color: #800000;\"><strong>中国银行x<span style=\"color: #ff6600;\">50笔pay送5w积分：</span></strong></span>中行信用卡通过手机闪付（Apple Pay等）进行交易，满10元即可获赠1000信用卡交易积分（每卡50笔，共150w笔）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2353.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">花旗银行x</span>快捷600刷卡金：</strong></span>花旗信用卡持卡人累积消费满6000元（含）以上并通过“花旗银行信用卡”微信号“我的优惠”- “我要参加活动”领取刷卡金礼包，即可享双11期间网络消费的一定比例的刷卡金返还，具体返还比例以领取的礼包页面显示为准（最高600元，11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s/RK52wTXBoE_gqfuLjruyRA\" ><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">广发银行x</span>10元抽奖：</strong></span>广发卡消费满10元，可在发现精彩APP-精彩活动-玩转信用卡-喜刷刷抽奖，普通用户当天3笔以上的消费享2倍奖励，四星五星用户均享2倍奖励，（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2266.html\"><span style=\"color: #800000;\"><strong>华夏银行x<span style=\"color: #ff6600;\">精英挑战赛：</span></strong></span>华夏信用卡（小白卡等卡例外，建议使用正常非联名、有积分卡）新增计积分交易排名1-5000名，可获1800元京东卡，5001-2w名可获1000元猫超卡，20001-4w名可获400元话费，40001-65000名可获爱奇艺年卡，65001-10w名可获100元kfc券（需要88积分兑换，12月31日截止）（微信交易不累计）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2371.html\"><span style=\"color: #800000;\"><strong>华夏银行x<span style=\"color: #ff6600;\">88抽奖：</span></strong></span>关注华夏银行信用卡官方微信，单笔消费满88元可通过微信推送抽取积分或流量（每日1次）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2460.html\"><span style=\"color: #800000;\"><strong>上海银行x<span style=\"color: #ff6600;\">移动消费抽奖：</span></strong></span>每周满3笔移动支付不限金额（支付宝微信云闪付），可抽康宁百丽餐具双耳饭碗三件套、K.S.菲奥纳真空焖烧罐、乐扣乐扣热水壶、福临门东町越光米2kg、欧丽薇兰特级初榨橄榄油500ml等奖励（每周三次）（12月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2248.html\"><span style=\"color: #800000;\"><strong>北京银行x<span style=\"color: #ff6600;\">周周刷：</span></strong></span>每周消费5笔188元，满3周可得乳胶枕或蓝牙耳机（不限量）；满5周可得无线键盘或电动牙刷（不限量）；满8周可得小狗吸尘器或海尔迷你洗衣机（消费排名3000名）；满12周可得kindle或空气炸锅（消费排名1500名）（12月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2212.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">平安银行x</span>188抽红包：</strong></span>平安口袋银行绑定平安卡，单笔消费满188元可抽刷卡金、积分、鲜花、电影券等（每月30个，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2273.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">周周赢抽奖：</span></strong></span>每周四<strong>10点起</strong>，上周消费5000-2w元，可登陆农行掌银APP-信用卡-精品秒杀-每周一品，领取幸运号码，尾号为6或8可6积分抢兑精美礼品（1000个名额），<strong>11点起</strong>，消费2w元，可6积分抢兑精美礼品（1000个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2010.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">喜刷刷（特邀）</span></strong></span><span style=\"color: #ff6600;\">：</span>收到农行邀约短信的客户，使用农行信用卡当月任意金额消费满3笔，即可于次月以6积分抢兑电吹风、电炖锅等好礼（每月2000个名额）</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2009.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>乐刷刷：</strong></span>每自然月农行消费达标（5k以下、5k-1w，1w-2w，2w以上四档），可于指定时间登录掌银APP活动领取幸运券码，尾号为1、3、5、7、9数字之一的客户有机会以6积分兑换相应礼品;尾号为其他数字的客户有机会获得2000积分奖励</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2326.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>集卡卡：</strong></span>周周赢（至少消费5k以上）、乐刷刷（至少消费2k以上）消费达标且每期按时登录掌银抽奖可集卡一次，根据卡片数量可每月抢兑洗碗机、kindle等好礼（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2291.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">刷卡抽奖：</span></strong></span>单笔消费每满66元，登录掌银APP—信用卡—积分换好礼——消费满额笔笔抽，可6积分抽华为手机、88积分等好礼（19年4月10日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s?__biz=MjM5OTA5ODE0MA==&amp;mid=2987062536&amp;idx=3&amp;sn=6817d570d706238942d52d6b05f61900&amp;scene=21#wechat_redirect\" ><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">666积分抽奖：</span></strong></span>掌银APP—信用卡—玩转优惠—乐玩积分—小积分抽大奖。使用666积分可抽破壁机、华为手环、888积分等</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2331.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">银联闪付：</span></strong></span>使用pay或银联二维码消费3笔且总额大于500元，可次月第三个周五10点起浦大喜奔App—精品—小浦支付圈—快捷支付—置顶活动banner，抽8-88元刷卡金，总额满3000元抽8-188元刷卡金（2.5w个，每月1次），首绑还可领8元刷卡金（15w个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1990.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">邮储银行x</span>逢10有奖：</strong></span>邮储银行信用卡在支付宝、微信、银联手机闪付活动渠道消费，单笔金额满10元即可获得悦享币1枚。每客户每日三种渠道最多各获得1枚，每自然月最多共可获得30枚（12月31日截止）（活动如果后期没变动，兑换比例不错）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2348.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>节节高：</strong></span>短信或微信报名后（需要选择单人赛还是组团），参加单人赛，单周刷兴业信用卡指定消费满5笔188元或单周成功办理单笔满1000元的消费分期视为达标1周，累计达标2周及以上且消费总金额达标，可优惠价分期12期抢兑礼品；参加团队赛，团队满10w元可优惠价分期12期抢兑礼品</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2341.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>网购5w积分：</strong></span>客户当月支付宝累计交易满6000元，可于19年1月22日-1月31日登陆“好兴动”APP“我的-我的活动”栏位选相应月份，抢5万积分（每月各2w，航空卡，白金卡不参加）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2187.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>6积分活动升级：</strong></span>累计刷卡消费满6笔，且单笔交易金额满99元，次月（或特别约定时间段）持可使用积分消费的信用卡至线下哈根达斯、汉堡王、歌帝梵门店，享首次6000信用卡积分、第二次6积分兑换指定商品权益（统计时间并非自然月，而是当月26日-次月25日）（12月25日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/693.html\"><span style=\"color: #800000;\"><strong>交通银行x<span style=\"color: #ff6600;\">最红5积分</span>：</strong></span>交通银行信用卡买单吧APP-我的-我的活动-最红5积分，上月消费满8笔（同一商户单周算1笔），可获1次5积分兑换好礼权益，每周五<strong>9点起</strong>更新礼品名额（上月刷卡下月领）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1941.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>消费达标赢京东支付券：</strong></span>每个周期（月中至月底，16号到月底），</a></p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/1941.html\">消费天数不少于9天且当天累计消费金额不少于99元，可获满60减30元的京东商城满减权益一次（每周期8000个名额，时间排名）；</a></p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/1941.html\">金、普、钛金卡客户累计消费金额不少于15,000元，可获赠价值满100减50元的京东商城满减权益一次（每周期5000个名额，金额排名）；</a></p>\r\n" + 
			"<p><a href=\"//www.zrfan.com/1941.html\">白金及以上卡客户累计消费金额不少于40,000元，即有机会获赠价值满150减100元的京东商城满减权益一次（每周期8000个名额，金额排名）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>银联云闪付x<span style=\"color: #ff6600;\">签到红包</span></strong></span>：云闪付APP-首页-首页-右侧浮动窗，签到领红包转账红包继续</p></blockquote>\r\n" + 
			"<p style=\"text-align: center;\"><span style=\"font-size: 14pt;\"><strong><span style=\"color: #800000;\">以下是常规活动</span></strong></span></p>\r\n" + 
			"<h2><strong>工商银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1548.html\"><span style=\"color: #800000;\"><strong>工商银行</strong><strong>x<span style=\"color: #ff6600;\">快捷支付积分：</span></strong></span>工商银行信用卡使用微信支付（包括微信及QQ）、京东支付，消费1元可得1积分（延期至12月31日）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2318.html\"><span style=\"color: #800000;\"><strong>工商银行x<span style=\"color: #ff6600;\">云闪付激励金：</span></strong></span>云闪付APP中成功绑定工行银联信用卡（62开头）后，通过付款码或扫一扫单笔消费10元以上随机获得5-20元激励金（需要云闪付领取，100w个名额，每户1次）</a></p></blockquote>\r\n" + 
			"<h2><strong>农业银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2372.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>妈妈卡小玩卡6积分：</strong></span>农行银联漂亮升级妈妈信用卡或小玩信用卡消费5笔且满8000元，可19年2月20日登陆掌银6积分抽康宁两件套（8000个名额，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2373.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">尊然白狂刷（特邀）：</span></strong></span>收到邀约短信的尊然白金信用卡用户消费满30w，且有一笔支付宝微信快捷消费，消费排名前1k名可获大疆无人机，其余1月23日抽650部华为手机，保底5w积分（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2364.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>11倍12倍积分：</strong></span>农行掌银APP-信用卡-精品秒杀，领券报名成功，双十一双十二，通过支付宝、微信、京东、苏宁、美团快截支付消费分别赠送11倍、12倍积分（上限10w积分，10w个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2312.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">必胜客：</span></strong></span>农行掌银APP-信用卡-乐享周六专区，选择“必胜客100元购买150元代金券”，可100元购买150元必胜客代金券，代金券可在有效期内每周四、周五、周六于必胜客活动门店使用（每户每月1次，每月5w张，12月29日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2291.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">刷卡抽奖：</span></strong></span>单笔消费每满66元，登录掌银APP—信用卡—积分换好礼——消费满额笔笔抽，可6积分抽华为手机、88积分等好礼（19年4月10日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s?__biz=MjM5OTA5ODE0MA==&amp;mid=2987062536&amp;idx=3&amp;sn=6817d570d706238942d52d6b05f61900&amp;scene=21#wechat_redirect\" ><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">666积分抽奖：</span></strong></span>掌银APP—信用卡—玩转优惠—乐玩积分—小积分抽大奖。使用666积分可抽破壁机、华为手环、888积分等</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2273.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">周周赢抽奖：</span></strong></span>每周四<strong>10点起</strong>，上周消费5000-2w元，可登陆农行掌银APP-信用卡-精品秒杀-每周一品，领取幸运号码，尾号为6或8可6积分抢兑精美礼品（1000个名额），<strong>11点起</strong>，消费2w元，可6积分抢兑精美礼品（1000个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2274.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">6积分礼品（特邀）：</span></strong></span>收到邀约短信的客户，消费达标即有机会以6积分赢取Blueair空气净化器（ETC用户消费5000以上，车险用户购车险5000以上，80份）、WMF高端锅具三件套（ETC用户消费1000以上，车险用户2000以上，1800份）、600积分等好礼（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2270.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>微信积分：</strong></span>掌上银行APP-信用卡-精品秒杀专区-“微信支付积分权益”栏目-勾选信用卡-点击领取，领券当月及活动后续月份，通过勾选的信用卡进行微信支付消费，公务卡每消费人民币1元计1.5个积分，其余卡种（不含商务卡、准贷记卡）每消费人民币1元计1个积分（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2215.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>微信10倍积分：</strong></span>农行掌银APP-信用卡-精品秒杀-“微信支付十倍积分”栏目领券并首次绑定微信支付，指定时间使用微信消费享10倍积分，最高10w积分</a>（19年2月28日）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2216.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">信用币微信积分：</span></strong></span>农行信用币微信支付享双倍积分（每月最高额外赠1w积分，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2326.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>集卡卡：</strong></span>周周赢（至少消费5k以上）、乐刷刷（至少消费2k以上）消费达标且每期按时登录掌银抽奖可集卡一次，根据卡片数量可每月抢兑洗碗机、kindle等好礼（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2010.html\"><span style=\"color: #800000;\"><strong>农业银行x<span style=\"color: #ff6600;\">喜刷刷（特邀）</span></strong></span><span style=\"color: #ff6600;\">：</span>收到农行邀约短信的客户，使用农行信用卡当月任意金额消费满3笔，即可于次月以6积分抢兑电吹风、电炖锅等好礼（每月2000个名额）</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2009.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">农业银行x</span>乐刷刷：</strong></span>每自然月农行消费达标（5k以下、5k-1w，1w-2w，2w以上四档），可于指定时间登录掌银APP活动领取幸运券码，尾号为1、3、5、7、9数字之一的客户有机会以6积分兑换相应礼品;尾号为其他数字的客户有机会获得2000积分奖励</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<h2><strong>中国银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2375.html\"><span style=\"color: #800000;\"><strong>中国银行x<span style=\"color: #ff6600;\">50笔pay送5w积分：</span></strong></span>中行信用卡通过手机闪付（Apple Pay等）进行交易，满10元即可获赠1000信用卡交易积分（每卡50笔，共150w笔）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2241.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">中国银行x</span>兑积分奖积分：</strong></span>登录“缤纷生活”APP，成功完成一次交易积分的兑换，最高获赠5000交易积分奖励（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2075.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">中国银行x</span>充值随机减：</strong></span>使用中国银行手机银行充值话费满50元随机减，最高减50元（每户每月1次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2269.html\"><span style=\"color: #800000;\"><strong>中国银行x<span style=\"color: #ff6600;\">银联二维码20倍积分：</span></strong></span>中行信用卡持卡人登录“手机银行”APP，用信用卡进行银联二维码支付可获赠20倍积分，每人每月上限50000积分（消费2500元即可，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2160.html\"><span style=\"color: #800000;\"><strong>中国银行x<span style=\"color: #ff6600;\">快捷5倍积分：</span></strong></span>中国银行信用卡官方微信-热卡精彩-活动报名，微信支付、京东支付、唯品会、支付宝绑定中国银行信用卡，即享5倍积分加速权益（每月每户10w积分，12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1692.html\"><span style=\"color: #800000;\"><strong>中国银行x<span style=\"color: #ff6600;\">星巴克</span></strong></span>：周一至周日，15000积分可兑换一大杯星巴克（每日不限量），周六周日享兑一赠一（每户每周一次）（活动续期至19年4月14日）</a></p></blockquote>\r\n" + 
			"<h2><strong>建设银行</strong></h2>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>建设银行x<span style=\"color: #ff6600;\">龙支付最高8倍积分：</span></strong></span>建行1-2星级客户龙支付有效消费每1元加送1倍积分;3-4星级客户加送4倍积分;5星级以上客户每1元加送8倍积分（最多100w积分，12月31日截止）（建行手机银行-左上角三条杠-头像，可查看星级，该活动积分奖励到账比较快，大家可以看看商户是否有效）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2154.html\"><span style=\"color: #800000;\"><strong>建设银行x<span style=\"color: #ff6600;\">各种pay返现：</span></strong></span>各种pay绑定建行借记卡消费满10元，比上月笔数多3笔，即可获得30元返现（如果上月是3笔，本月需要6笔，以此类推，每月8w名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2195.html\"><span style=\"color: #800000;\"><strong>龙支付x<span style=\"color: #ff6600;\">顺丰：</span></strong></span>使用龙支付扫描顺丰POS的收款二维码完成支付，享满12-6元优惠（每天5000个名额，每天1次，最多3次）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2178.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">建设银行x</span>支付宝微信有积分：</strong></span>龙卡信用卡使用支付宝、微信消费有积分（并非长期积分政策调整，而是活动，活动周期最高可获10w分，即消费10w元）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2020.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">建设银行x</span>龙支付网易严选：</strong></span>网易严选APP，选择网易支付-建行龙支付（含建行钱包支付,以及龙支付绑定的建行借记卡和建行信用卡）支付货款，每人首笔享满立减优惠（满100减30元），第二笔至第六笔满100随机立减9至99元（每户每日1次，每日1w个名额，可购买严选卡）（12月10日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=http://www.ccb.com/cn/html1/branch/js/subject/17/0824lzf/page_yb.html\" ><span style=\"color: #800000;\"><strong>建设银行x<span style=\"color: #ff6600;\">龙支付友宝：</span></strong></span>友宝自助售货机使用建行龙支付支付订单，即可享受单笔订单1折特惠，最高可享5元优惠（每日1次，最多优惠10元）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<h2><strong>中信银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1534.html\"><span style=\"color: #800000;\"><strong>中信银行x<span style=\"color: #ff6600;\">微信2倍积分</span></strong>：</span>中信信用卡有效计积分信用卡客户，微信消费享2倍积分，每月每户最多1w积分，i白魔力卡等不计积分卡片不参与，网络联名卡按照原积分规则（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1414.html\"><span style=\"color: #800000;\"><strong>中信银行x<span style=\"color: #ff6600;\">九积分</span></strong></span>：中信九积分，套路大致相同，但是非交年费的白金卡无法线下兑换，只能线上兑换，每日<span style=\"color: #ff0000;\"><strong>10点</strong></span>动卡空间-权益-9分权益，抢兑星巴克等好礼（线上18w份每月，线下22w份）</a></p></blockquote>\r\n" + 
			"<h2><strong>光大银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2423.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">唯品花卡送积分：</span></strong></span>使用光大唯品花联名信用卡通过线上支付满100元（含）以上，即有机会获赠5万积分（11月5000个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2398.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>美团首绑：</strong></span>美团系APP首绑光大信用卡消费，立减10元（每日3750个名）（19年3月31日截止，可尝试话费）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2390.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>视频卡5w积分：</strong></span>光大视频联名卡每周刷单卡可计积分交易满1999元，可获5万积分（每周3000个名额）；四周全部达标，且11月当月交易额达到9999元或以上的客户，可额外获得15万积分（2000个名额）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2335.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>新户活动：</strong></span>“光大银行信用卡”微信公众号→在线申请→账户绑定，完成绑定即有机会获得5000积分（24w个名额）；“光大银行信用卡”微信公众号→在线申请→手机APP下载功能，首次注册阳光惠生活APP即有机会获得信用卡商城满99元减50元满减权益一张（6w个名额）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2315.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>缴费充值红包：</strong></span>光大专业版手机银行、个人网银完成任意一笔缴费或手机充值交易，即有机会获取1.8元-88元不等话费红包（每户3次，11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2306.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>驴妈妈：</strong></span>光大驴悦亲子旅游联名信用卡客户在驴妈妈旅游APP购买秋季热门旅游产品享单笔满700立减300元优惠，11月30日限前3600名客户</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2205.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">汽车权益包：</span></strong></span>阳光惠生活-首页-会生活，惠养车，可以优惠价购买车点点汽车服务权益包</a>（12月31日截止）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2169.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">月末3倍积分：</span></strong></span>编辑短信“报名”并发送至95595报名月末计积分消费按3倍积分计算，上限10w积分（按照消费排名前5w名）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2150.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>视频类：</strong></span>视频类联名卡客户当月单卡累计可计积分交易满6888元，获赠天猫精灵M1智能音箱。每月限前3500个名额，共计14000个名额，每户1次</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2151.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">出行类：</span></strong></span>出行类联名卡客户当月单卡累计可计积分交易满6888元，获赠欧舒丹旅行套装一套。每月限前4500个名额，共计18000个名额，每户1次</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2152.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>购物类：</strong></span>购物类联名卡客户当月单卡累计可计积分交易满6888元，获赠阿芙香薰套装一套。每月限前3000个名额，共计12000个名额，每户1次（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2144.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>信用卡商城满减：</strong></span>光大银行信用卡商城满49-20元，满99-50元，满299-100元，满1999-200元（每档8000个名额，每户每档1次）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2070.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>途牛：</strong></span>白金及以上用户购买途牛旅游产品满3000-300元（每月500个名额），金普钛金用户满500-40元（每月2000个名额）（每月1次）；途牛APP首页—更多—银行特惠—光大银行，可一键领取所有优惠券（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2051.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>10元机场引领：</strong></span>阳光惠生活APP，首页-本地活动-优-生活，可10元购买200元的金色世纪机场引领服务（每月8000个名额）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2007.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">滴滴接送机：</span></strong></span>阳光惠生活APP-首页-出行权益-滴滴权益，可90元购买价值260元的滴滴专车机场接送权益（每日100名额，每户每月1次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1974.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>苏宁首绑：</strong></span>光大银行信用卡首次绑定苏宁支付，满100-20元（50w个名额）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1971.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>凯撒旅游：</strong></span>阳光惠生活”APP首页-本地生活-点击“优•生活”，光大凯撒旅游联名白金信用卡客户可享1元抢凯撒“满600元减300元权益”（每月300个），光大信用卡客户可享1元抢凯撒“满300元减100元权益”（每月3000个）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1732.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>龙腾</strong></span>：“阳光惠生活”APP，首页“出行权益”—“心享阳光行”—“龙腾权益”，10元购50元的龙腾出行机场/高铁饭票权益（每日150名）；150元购买价值395元机场/高铁快速出行权益（每日50名）；99元购买价值210元极客尊享权益（每日50名）（每户每月各1次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1731.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">悦途</span></strong></span>：“阳光惠生活”APP，首页“心享阳光行”—“悦途贵宾厅权益”10元即有机会购买一次悦途高铁贵宾厅标准权益（每月6500名）（每户每月1次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1730.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">光大银行x</span>财付通消费有积分</strong></span>：光大信用卡通过财付通渠道（QQ支付、微信支付、PC端财付通快捷支付）消费可获得一倍积分（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1450.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">10元大片</span></strong></span>：指定电影院享10元看大片（活动时间基本为周末）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1451.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">十元美食</span></strong></span>：指定餐厅享10元吃美食（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1452.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">10元靓车</span></strong></span>：指定车行享10元洗靓车（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1453.html\"><span style=\"color: #800000;\"><strong>光大银行x<span style=\"color: #ff6600;\">10元high歌</span></strong></span>：指定KTV享10元唱high歌（12月31日截止）</a></p></blockquote>\r\n" + 
			"<h2>浦发银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2420.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">惠花：</span></strong></span>指定商户刷卡或使用浦大喜奔付款，享3%返现（每日1w名额，每户上限500元）；交易享3倍积分，28日享5倍积分；个别商户可买最低5折优惠券（集中在餐饮类）；个别商户可享受满100-12元（每户每月5次）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2238.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">首绑自动还款瓜分5亿积分：</span></strong></span>首次绑定借记卡自动还信用卡，且首期账单还1000元可得1w积分（10w个名额）；连续三期自动还款1000元以上，可瓜分5亿积分（5w个名额）（12月21日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2207.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">唯品会首绑：</span></strong></span>浦大喜奔APP首页-小浦支付券-快捷支付-唯品会，唯品会新户首绑浦发卡得200-20元优惠券，唯品会老户首绑浦发卡得300-20优惠券（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">KPL联名卡竞猜：</span></strong></span>9月至12月，浦发kpl联名卡用户，浦大喜奔APP-搜索“电竞平台”-瓜分20亿积分，参与积分竞猜，可瓜分竞猜奖池（每期额外有积分奖励，总奖励20亿，单户最高500w）</p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1994.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">消费达标领会员：</span></strong></span>上月累计消费5000元，每月<strong>9日11点</strong>起（8月9日，9月9日……），浦发银行信用卡官方微信-微广告-全能积分-小浦视听-上方广告“免费领超级会员”（每月1w个名额）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1975.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">浦发银行x</span>快捷双倍积分：</strong></span>浦发信用卡绑定支付宝、微信，各种pay，银联二维码支付，享双倍积分（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s/oFPeIjg4_Rhxfi0CtI2_6A\" ><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">钻石生日特权：</span></strong></span>钻石客户生日当月，浦发信用卡梦想家俱乐部-生日特权（浦发信用卡官方微信回复“梦想家”），可优惠积分兑换京东卡加油卡等</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2146.html\"><span style=\"color: #800000;\"><strong>浦发银行x<span style=\"color: #ff6600;\">红包一刷到底：</span></strong></span>浦发红包活动更新，新增大额消费权益、8包自定奖励、一刷到底挑战赛</a></p></blockquote>\r\n" + 
			"<h2><strong>兴业银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2348.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>节节高：</strong></span>短信或微信报名后（需要选择单人赛还是组团），参加单人赛，单周刷兴业信用卡指定消费满5笔188元或单周成功办理单笔满1000元的消费分期视为达标1周，累计达标2周及以上且消费总金额达标，可优惠价分期12期抢兑礼品；参加团队赛，团队满10w元可优惠价分期12期抢兑礼品</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2341.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>网购5w积分：</strong></span>客户当月支付宝累计交易满6000元，可于19年1月22日-1月31日登陆“好兴动”APP“我的-我的活动”栏位选相应月份，抢5万积分（每月各2w，航空卡，白金卡不参加）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2328.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>白金卡运动馆：</strong></span>兴业银行兴享白金信用卡使用该卡通过云闪付“全民运动”运动平台预定专区支付，次笔起可享每笔消费金额立减，每笔立减上限20元（每户10次，2000个名额）兴享白金或缴费白金手百立减50元（1w个名额）（12月21日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2305.html\"><span style=\"color: #800000;\"><strong>兴业银行x<span style=\"color: #ff6600;\">每日福利：</span></strong></span>好兴动APP-查询账单，自动弹出抽积分（一般6积分）；查询积分，自动弹出抽积分（一般6积分）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2204.html\"><span style=\"color: #800000;\"><strong>兴业银行x<span style=\"color: #ff6600;\">分期领大米</span>：</strong></span>兴业蒙牛卡、饿了么卡或古韵中国风卡，编辑“CCAZ”发送至95561报名后，消费6笔99元且完成1笔不低于500元且分期期数在12期及以上的消费、账单、现金分期交易即可获得<span style=\"text-indent: 2em;\">水清清稻花香大米（5斤装）（5w个名额）</span></a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2156.html\"><span style=\"color: #800000;\"><strong>兴业银行x<span style=\"color: #ff6600;\">洗车：</span></strong></span>兴业持卡人可购买28元单次汽车清洗服务，99元5次汽车清洗服务，188元10次汽车清洗服务（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2187.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">兴业银行x</span>6积分活动升级：</strong></span>累计刷卡消费满6笔，且单笔交易金额满99元，次月（或特别约定时间段）持可使用积分消费的信用卡至线下哈根达斯、汉堡王、歌帝梵门店，享首次6000信用卡积分、第二次6积分兑换指定商品权益（统计时间并非自然月，而是当月26日-次月25日）（12月25日截止）</a></p></blockquote>\r\n" + 
			"<h2><strong>交通银行</strong></h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2276.html\"><span style=\"color: #800000;\"><strong>交通银行x<span style=\"color: #ff6600;\">云闪付首绑：</span></strong></span>云闪付APP首次绑定交行银行信用卡消费满20返10元刷卡金（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>交通银行x<span style=\"color: #ff6600;\">生活大作战：</span></strong></span>在买单吧电影、缴费、餐饮、商城、还款场景完成交易，可在买单吧APP-我的-我的活动-生活大作战抽最高100元红包（每月1次）</p></blockquote>\r\n" + 
			"<h2>招商银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2404.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">招商银行x</span>顺丰：</strong></span>使用招商银行APP支付顺丰运费，随机立减5-30元（每户每月1次，12月31日截止，限量）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2013.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">招商银行x</span>1分钱友宝：</strong></span>使用招商银行app扫一扫支付友宝，首单立减3元，第二、三单立减1元（每月1次，三次优惠需在首单之后三个月内使用）</a></p></blockquote>\r\n" + 
			"<h2>北京银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2355.html\"><span style=\"color: #800000;\"><strong>北京银行x<span style=\"color: #ff6600;\">二维码10元话费：</span></strong></span>京彩生活APP中单月完成三笔银联二维码支付，即可获赠10元话费（11月2w个名额，12月截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2248.html\"><span style=\"color: #800000;\"><strong>北京银行x<span style=\"color: #ff6600;\">周周刷：</span></strong></span>每周消费5笔188元，满3周可得乳胶枕或蓝牙耳机（不限量）；满5周可得无线键盘或电动牙刷（不限量）；满8周可得小狗吸尘器或海尔迷你洗衣机（消费排名3000名）；满12周可得kindle或空气炸锅（消费排名1500名）（12月30日截止）</a></p></blockquote>\r\n" + 
			"<h2>上海银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2356.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">上海银行x</span>首绑积分：</strong></span>首次绑定云闪付（二维码及手机闪付）、支付宝快捷支付、微信快捷支付且消费任意金额，各享5000积分奖励（11月30日截止）</a></p></blockquote>\r\n" + 
			"<h2>民生银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2352.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">民生银行x</span>快捷5倍积分：</strong></span>11月1日-12月31日，民生信用卡首次使用支付宝、财付通或全民APP支付，满11元可返11元还款金；11月16日起每日10点领（11月每日8k个，12月每日4k个）；11月1日起，民生全民生活报名后，支付宝、微信消费享5倍积分或千万积分大抽奖</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2177.html\"><span style=\"color: #800000;\"><strong>民生银行x<span style=\"color: #ff6600;\">周末许愿：</span></strong></span>全民生活APP-首页上方“全民周末”，完任务得许愿币，移动付（支付宝微信付款，可多次获得）、扫码付款（仅首次可获得），信用卡还款三项相对容易，周六9点起可用许愿币许愿（建议周三之前完成任务，周六9点即可许愿，金币必中奖，银币随机）（12月22日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1699.html\"><span style=\"color: #800000;\"><strong>民生银行x<span style=\"color: #ff6600;\">衣二三</span></strong></span>：民生Visa金卡及以上级别持卡人享“衣二三”包月会员首月半价超值礼遇（原价每月人民币499元，Visa高端卡持卡人享首月人民币249元）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<h2>广发银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1767.html\"><span style=\"color: #800000;\"><strong>广发银行x<span style=\"color: #ff6600;\">境外返现：</span></strong></span>发现精彩APP-首页下拉“环球悦购平台”-上方轮动广告“境外消费6%”，报名后，广发外币国际信用卡和给力信用卡境外消费满15美元返6%，每户每月上限300美元（19年3月31日截止）（<span style=\"color: #ff0000;\"><strong>广发修改规则，变成“名额有限”“每户最多有机会享返现2次”</strong></span>）</a></p></blockquote>\r\n" + 
			"<h2>华夏银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2371.html\"><span style=\"color: #800000;\"><strong>华夏银行x<span style=\"color: #ff6600;\">88抽奖：</span></strong></span>关注华夏银行信用卡官方微信，单笔消费满88元可通过微信推送抽取积分或流量（每日1次）（11月30日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2266.html\"><span style=\"color: #800000;\"><strong>华夏银行x<span style=\"color: #ff6600;\">精英挑战赛：</span></strong></span>华夏信用卡（小白卡等卡例外，建议使用正常非联名、有积分卡）新增计积分交易排名1-5000名，可获1800元京东卡，5001-2w名可获1000元猫超卡，20001-4w名可获400元话费，40001-65000名可获爱奇艺年卡，65001-10w名可获100元kfc券（需要88积分兑换，12月31日截止）（微信交易不累计）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2334.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">华夏银行x</span>签到领积分：</strong></span>华夏信用卡APP“华彩生活”-精品-签到送积分进行签到可获随机积分，每月随机一次5888积分（19年3月31日截止）</a></p></blockquote>\r\n" + 
			"<h2>平安银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2212.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">平安银行x</span>188抽红包：</strong></span>平安口袋银行绑定平安卡，单笔消费满188元可抽刷卡金、积分、鲜花、电影券等（每月30个，12月31日截止）</a></p></blockquote>\r\n" + 
			"<h2>邮储银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/wp-content/themes/begin/inc/go.php?url=https://mp.weixin.qq.com/s/h2-7nksDWHEMV6xzlw_S4A\" ><span style=\"color: #800000;\"><strong>邮储银行x<span style=\"color: #ff6600;\">猫超卡：</span></strong></span>11月消费8888元，12月1日“邮储银行信用卡”官方微信-鼎生活-我的权益，登录后点击“领取抢兑”（2400个名额）</a></p></blockquote>\r\n" + 
			"<h2>汇丰银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/1962.html\"><span style=\"color: #800000;\"><strong>汇丰银行x<span style=\"color: #ff6600;\">亚马逊海外购：</span></strong></span>汇丰中国银联人民币信用卡持卡人在亚马逊中国海外购单笔消费满200元，下一自然月月底前即可获赠价值50元亚马逊中国电子礼品卡1张（每卡一次，每月1500个名额）（12月31日截止）</a></p></blockquote>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2351.html\"><span style=\"color: #800000;\"><strong>汇丰银行x<span style=\"color: #ff6600;\">最高返800刷卡金：</span></strong></span>11月1日-30日，8月31日前获批的汇丰银联信用卡用户，报名后，单月完成9笔199元以上线下可计积分消费（支付宝/微信支付/京东支付快捷支付的方式除外），则11月1日-15日发生的支付宝/微信/京东支付消费可获得对应返现，最高返现800元</a>（需要大额消费）</p></blockquote>\r\n" + 
			"<h2>花旗银行</h2>\r\n" + 
			"<blockquote><p><a href=\"//www.zrfan.com/2353.html\"><span style=\"color: #ff6600;\"><strong><span style=\"color: #800000;\">花旗银行x</span>快捷600刷卡金：</strong></span>花旗信用卡持卡人累积消费满6000元（含）以上并通过“花旗银行信用卡”微信号“我的优惠”- “我要参加活动”领取刷卡金礼包，即可享双11期间网络消费的一定比例的刷卡金返还，具体返还比例以领取的礼包页面显示为准（最高600元，11月30日截止）</a></p></blockquote>\r\n" + 
			"<h2>其它</h2>\r\n" + 
			"<blockquote><p><span style=\"color: #800000;\"><strong>银联云闪付x<span style=\"color: #ff6600;\">签到红包</span></strong></span>：云闪付APP-首页-首页-右侧浮动窗，签到领红包转账红包继续</p></blockquote>\r\n" + 
			"<p><strong>PS：限于石榴能力精力有限，如果您发现还有活动石榴未曾收录，欢迎评论留言！</strong></p>\r\n" + 
			"<p><span style=\"color: #ff0000;\"><strong>——致力于为各位的薅羊毛事业提供信息保障！</strong></span></p>\r\n" + 
			"			</div>\r\n" + 
			"\r\n" + 
			"						\r\n" + 
			"									\r\n" + 
			"						\r\n" + 
			"									<div class=\"s-weixin\">\r\n" + 
			"	<ul class=\"weimg1\">\r\n" + 
			"		<li>\r\n" + 
			"			<strong>QQ群</strong>\r\n" + 
			"		</li>\r\n" + 
			"		<li></li>\r\n" + 
			"		<li>\r\n" + 
			"			<img src=\"//www.zrfan.com/cdn/2017/12/20171222113807593.png\" alt=\"weinxin\" />\r\n" + 
			"		</li>\r\n" + 
			"	</ul>\r\n" + 
			"	<ul class=\"weimg2\">\r\n" + 
			"		<li>\r\n" + 
			"			<strong>微信公众号</strong>\r\n" + 
			"		</li>\r\n" + 
			"		<li></li>\r\n" + 
			"		<li>\r\n" + 
			"			<img src=\"//www.zrfan.com/cdn/2017/02/20170220133517262.jpg\" alt=\"weinxin\" />\r\n" + 
			"		</li>\r\n" + 
			"	</ul>\r\n" + 
			"	<div class=\"clear\"></div>\r\n" + 
			"</div>\r\n" + 
			"				\r\n" + 
			"									<div class=\"clear\"></div>\r\n" + 
			"<div id=\"social\">\r\n" + 
			"	<div class=\"social-main\">\r\n" + 
			"		<span class=\"like\">\r\n" + 
			"	         <a href=\"javascript:;\" data-action=\"ding\" data-id=\"15850\" title=\"点赞\" class=\"favorite\"><i class=\"fa fa-thumbs-up\"></i>赞 <i class=\"count\">\r\n" + 
			"	            0</i>\r\n" + 
			"	        </a>\r\n" + 
			"		</span>\r\n" + 
			"		<div class=\"shang-p\">\r\n" + 
			"			<div class=\"shang-empty\"><span></span></div>\r\n" + 
			"							<span class=\"tipso_style\" id=\"tip-p\" data-tipso='\r\n" + 
			"					<span id=\"shang\">\r\n" + 
			"						<span class=\"shang-main\">\r\n" + 
			"							<h4><i class=\"fa fa-heart\" aria-hidden=\"true\"></i> 您可以选择一种方式赞助本站</h4>															<span class=\"shang-img\">\r\n" + 
			"									<img src=\"//www.zrfan.com/cdn/2017/02/20170206133025703.jpg\" alt=\"alipay\"/>\r\n" + 
			"									<h4>支付宝扫一扫赞助</h4>								</span>\r\n" + 
			"							\r\n" + 
			"															<span class=\"shang-img\">\r\n" + 
			"									<img src=\"//www.zrfan.com/cdn/2017/02/20170206133040156.png\" alt=\"weixin\"/>\r\n" + 
			"									<h4>微信钱包扫描赞助</h4>								</span>\r\n" + 
			"														<span class=\"clear\"></span>\r\n" + 
			"						</span>\r\n" + 
			"					</span>'>\r\n" + 
			"					<span class=\"shang-s\"><a title=\"赞助本站\">赏</a></span>\r\n" + 
			"				</span>\r\n" + 
			"					</div>\r\n" + 
			"		<div class=\"share-sd\">\r\n" + 
			"			<span class=\"share-s\"><a href=\"javascript:void(0)\" id=\"share-s\" title=\"分享\"><i class=\"fa fa-share-alt\"></i>分享</a></span>\r\n" + 
			"			<div id=\"share\">\r\n" + 
			"	<ul class=\"bdsharebuttonbox\">\r\n" + 
			"		<li><a title=\"更多\" class=\"bds_more fa fa-plus-square\" data-cmd=\"more\" onclick=\"return false;\" href=\"#\"></a></li>\r\n" + 
			"		<li><a title=\"分享到QQ空间\" class=\"fa fa-qq\" data-cmd=\"qzone\" onclick=\"return false;\" href=\"#\"></a></li>\r\n" + 
			"		<li><a title=\"分享到新浪微博\" class=\"fa fa-weibo\" data-cmd=\"tsina\" onclick=\"return false;\" href=\"#\"></a></li>\r\n" + 
			"		<li><a title=\"分享到腾讯微博\" class=\"fa fa-pinterest-square\" data-cmd=\"tqq\" onclick=\"return false;\" href=\"#\"></a></li>\r\n" + 
			"		<li><a title=\"分享到人人网\" class=\"fa fa-renren\" data-cmd=\"renren\" onclick=\"return false;\" href=\"#\"></a></li>\r\n" + 
			"		<li><a title=\"分享到微信\" class=\"fa fa-weixin\" data-cmd=\"weixin\" onclick=\"return false;\" href=\"#\"></a></li>\r\n" + 
			"	</ul>\r\n" + 
			"</div>		</div>\r\n" + 
			"		<div class=\"clear\"></div>\r\n" + 
			"	</div>\r\n" + 
			"</div>				\r\n" + 
			"							<div class=\"ad-pc ad-site\"><script async src=\"//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\r\n" + 
			"<!-- 优惠文章页 -->\r\n" + 
			"<ins class=\"adsbygoogle\"\r\n" + 
			"     style=\"display:block\"\r\n" + 
			"     data-ad-client=\"ca-pub-2207325541606966\"\r\n" + 
			"     data-ad-slot=\"4457451393\"\r\n" + 
			"     data-ad-format=\"auto\"></ins>\r\n" + 
			"<script>\r\n" + 
			"(adsbygoogle = window.adsbygoogle || []).push({});\r\n" + 
			"</script></div>\r\n" + 
			"	\r\n" + 
			"			<footer class=\"single-footer\">\r\n" + 
			"				<ul class=\"single-meta\"><li class=\"print\"><a href=\"javascript:printme()\" target=\"_self\" title=\"打印\"><i class=\"fa fa-print\"></i></a></li><li class=\"comment\"><a href=\"//www.zrfan.com/2462.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></li><li class=\"views\"><i class=\"fa fa-eye\"></i> 384</li><li class=\"r-hide\"><a href=\"javascript:pr()\" title=\"侧边栏\"><i class=\"fa fa-caret-left\"></i> <i class=\"fa fa-caret-right\"></i></a></li></ul><ul id=\"fontsize\"><li>A+</li></ul><div class=\"single-cat-tag\"><div class=\"single-cat\">所属分类：<a href=\"//www.zrfan.com/category/zhinan/\" rel=\"category tag\">每日刷卡指南</a></div></div>			</footer><!-- .entry-footer -->\r\n" + 
			"\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"	</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"<div class=\"single-tag\"><ul class=\"wow fadeInUp\" data-wow-delay=\"0.3s\"><li><a href=\"//www.zrfan.com/tag/%e6%af%8f%e6%97%a5%e5%88%b7%e5%8d%a1%e6%8c%87%e5%8d%97/\" rel=\"tag\">每日刷卡指南</a></li></ul></div>\r\n" + 
			"									<div class=\"authorbio wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"	<img src=\"https://secure.gravatar.com/avatar/4022dc143931b70a6085968c674159d5?s=128&d=mm\" alt=\"avatar\" class=\"avatar avatar-128\" height=\"128\" width=\"128\">\r\n" + 
			"	<ul class=\"spostinfo\">\r\n" + 
			"		<li>\r\n" + 
			"								<li><strong>版权声明：</strong>本页面文章，于29分钟前，由 <b>匿名网友</b> 整理投稿，共 16893 字。</li>\r\n" + 
			"		<li class=\"reprinted\"><strong>转载请注明：</strong><a href=\"//www.zrfan.com/2462.html\" rel=\"bookmark\" title=\"本文固定链接 https://www.zrfan.com/2462.html\">2018-11-27日周二刷卡指南 | 羊毛优惠，并标明原文出处</a></li>\r\n" + 
			"			</ul>\r\n" + 
			"	<div class=\"clear\"></div>\r\n" + 
			"</div>\r\n" + 
			"				\r\n" + 
			"				\r\n" + 
			"									<div id=\"related-img\" class=\"wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2461.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/15.jpg\" alt=\"2018-11-26日周一刷卡指南\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2461.html\">2018-11-26日周一刷卡指南</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2458.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/10.jpg\" alt=\"2018-11-25日周日刷卡指南\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2458.html\">2018-11-25日周日刷卡指南</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2456.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/9.jpg\" alt=\"2018-11-24日周六刷卡指南\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2456.html\">2018-11-24日周六刷卡指南</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2455.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/3.jpg\" alt=\"2018-11-23日周五刷卡指南|广发分享日新增项目\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2455.html\">2018-11-23日周五刷卡指南|广发分享日新增项目</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"			<div class=\"clear\"></div>\r\n" + 
			"</div>				\r\n" + 
			"				<div id=\"single-widget\">\r\n" + 
			"	<div class=\"wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"		<aside id=\"random_post-3\" class=\"widget widget_random_post wow fadeInUp\" data-wow-delay=\"0.3s\"><h3 class=\"widget-title\"><span class=\"s-icon\"></span>猜您喜欢</h3>\r\n" + 
			"<div id=\"random_post_widget\">\r\n" + 
			"	<ul>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/2455.html\" rel=\"bookmark\">2018-11-23日周五刷卡指南|广发分享日新增项目</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/2458.html\" rel=\"bookmark\">2018-11-25日周日刷卡指南</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/2462.html\" rel=\"bookmark\">2018-11-27日周二刷卡指南</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/2451.html\" rel=\"bookmark\">2018-11-22日周四刷卡指南</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/773.html\" rel=\"bookmark\">我是如何在屈臣氏买买买的！|屈臣氏优惠活动大全</a></li>\r\n" + 
			"					</ul>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside>		<aside id=\"recent-posts-2\" class=\"widget widget_recent_entries wow fadeInUp\" data-wow-delay=\"0.3s\">		<h3 class=\"widget-title\"><span class=\"s-icon\"></span>最新优惠</h3>		<ul>\r\n" + 
			"					<li>\r\n" + 
			"				<a href=\"//www.zrfan.com/2462.html\">2018-11-27日周二刷卡指南</a>\r\n" + 
			"						</li>\r\n" + 
			"					<li>\r\n" + 
			"				<a href=\"//www.zrfan.com/2461.html\">2018-11-26日周一刷卡指南</a>\r\n" + 
			"						</li>\r\n" + 
			"					<li>\r\n" + 
			"				<a href=\"//www.zrfan.com/2460.html\">上海银行信用卡移动支付每周享好礼</a>\r\n" + 
			"						</li>\r\n" + 
			"					<li>\r\n" + 
			"				<a href=\"//www.zrfan.com/2459.html\">平安银行信用卡拼搏吧我的团</a>\r\n" + 
			"						</li>\r\n" + 
			"					<li>\r\n" + 
			"				<a href=\"//www.zrfan.com/2458.html\">2018-11-25日周日刷卡指南</a>\r\n" + 
			"						</li>\r\n" + 
			"				</ul>\r\n" + 
			"		<div class=\"clear\"></div></aside>			</div>\r\n" + 
			"	<div class=\"clear\"></div>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"				<nav class=\"nav-single wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"					<a href=\"//www.zrfan.com/2461.html\" rel=\"prev\"><span class=\"meta-nav\"><span class=\"post-nav\"><i class=\"fa fa-angle-left\"></i> 上一篇</span><br/>2018-11-26日周一刷卡指南</span></a><span class='meta-nav'><span class='post-nav'>没有了<br/></span>已是最新文章</span>					<div class=\"clear\"></div>\r\n" + 
			"				</nav>\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"	<nav class=\"navigation post-navigation\" role=\"navigation\">\r\n" + 
			"		<h2 class=\"screen-reader-text\">文章导航</h2>\r\n" + 
			"		<div class=\"nav-links\"><div class=\"nav-previous\"><a href=\"//www.zrfan.com/2461.html\" rel=\"prev\"><span class=\"meta-nav-r\" aria-hidden=\"true\"><i class=\"fa fa-angle-left\"></i></span></a></div></div>\r\n" + 
			"	</nav>\r\n" + 
			"									\r\n" + 
			"<!-- 引用 -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"<div class=\"scroll-comments\"></div>\r\n" + 
			"\r\n" + 
			"<div id=\"comments\" class=\"comments-area\">\r\n" + 
			"\r\n" + 
			"	\r\n" + 
			"		<div id=\"respond\" class=\"comment-respond wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<h3 id=\"reply-title\" class=\"comment-reply-title\">发表评论<small><a rel=\"nofollow\" id=\"cancel-comment-reply-link\" href=\"/2462.html#respond\" style=\"display:none;\">取消回复</a></small></h3>\r\n" + 
			"\r\n" + 
			"			\r\n" + 
			"				<form action=\"//www.zrfan.com/wp-comments-post.php\" method=\"post\" id=\"commentform\">\r\n" + 
			"					\r\n" + 
			"			        <p class=\"emoji-box\"><script type=\"text/javascript\">\r\n" + 
			"/* <![CDATA[ */\r\n" + 
			"    function grin(tag) {\r\n" + 
			"    	var myField;\r\n" + 
			"    	tag = ' ' + tag + ' ';\r\n" + 
			"        if (document.getElementById('comment') && document.getElementById('comment').type == 'textarea') {\r\n" + 
			"    		myField = document.getElementById('comment');\r\n" + 
			"    	} else {\r\n" + 
			"    		return false;\r\n" + 
			"    	}\r\n" + 
			"    	if (document.selection) {\r\n" + 
			"    		myField.focus();\r\n" + 
			"    		sel = document.selection.createRange();\r\n" + 
			"    		sel.text = tag;\r\n" + 
			"    		myField.focus();\r\n" + 
			"    	}\r\n" + 
			"    	else if (myField.selectionStart || myField.selectionStart == '0') {\r\n" + 
			"    		var startPos = myField.selectionStart;\r\n" + 
			"    		var endPos = myField.selectionEnd;\r\n" + 
			"    		var cursorPos = endPos;\r\n" + 
			"    		myField.value = myField.value.substring(0, startPos)\r\n" + 
			"    					  + tag\r\n" + 
			"    					  + myField.value.substring(endPos, myField.value.length);\r\n" + 
			"    		cursorPos += tag.length;\r\n" + 
			"    		myField.focus();\r\n" + 
			"    		myField.selectionStart = cursorPos;\r\n" + 
			"    		myField.selectionEnd = cursorPos;\r\n" + 
			"    	}\r\n" + 
			"    	else {\r\n" + 
			"    		myField.value += tag;\r\n" + 
			"    		myField.focus();\r\n" + 
			"    	}\r\n" + 
			"    }\r\n" + 
			"/* ]]> */\r\n" + 
			"</script>\r\n" + 
			"\r\n" + 
			"<a href=\"javascript:grin(':?:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_question.gif\" alt=\":?:\" title=\"疑问\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':razz:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_razz.gif\" alt=\":razz:\" title=\"调皮\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':sad:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_sad.gif\" alt=\":sad:\" title=\"难过\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':evil:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_evil.gif\" alt=\":evil:\" title=\"抠鼻\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':!:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_exclaim.gif\" alt=\":!:\" title=\"吓\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':smile:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_smile.gif\" alt=\":smile:\" title=\"微笑\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':oops:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_redface.gif\" alt=\":oops:\" title=\"憨笑\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':grin:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_biggrin.gif\" alt=\":grin:\" title=\"坏笑\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':eek:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_surprised.gif\" alt=\":eek:\" title=\"惊讶\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':shock:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_eek.gif\" alt=\":shock:\" title=\"发呆\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':???:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_confused.gif\" alt=\":???:\" title=\"撇嘴\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':cool:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_cool.gif\" alt=\":cool:\" title=\"大兵\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':lol:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_lol.gif\" alt=\":lol:\" title=\"偷笑\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':mad:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_mad.gif\" alt=\":mad:\" title=\"咒骂\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':twisted:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_twisted.gif\" alt=\":twisted:\" title=\"发怒\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':roll:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_rolleyes.gif\" alt=\":roll:\" title=\"白眼\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':wink:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_wink.gif\" alt=\":wink:\" title=\"鼓掌\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':idea:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_idea.gif\" alt=\":idea:\" title=\"酷\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':arrow:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_arrow.gif\" alt=\":arrow:\" title=\"擦汗\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':neutral:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_neutral.gif\" alt=\":neutral:\" title=\"亲亲\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':cry:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_cry.gif\" alt=\":cry:\" title=\"大哭\" /></a>\r\n" + 
			"<a href=\"javascript:grin(':mrgreen:')\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/smilies/icon_mrgreen.gif\" alt=\":mrgreen:\" title=\"呲牙\" /></a>\r\n" + 
			"<br /></p>\r\n" + 
			"					<p class=\"comment-form-comment\"><textarea id=\"comment\" name=\"comment\" rows=\"4\" tabindex=\"1\"></textarea></p>\r\n" + 
			"\r\n" + 
			"					<p class=\"comment-tool\">\r\n" + 
			"											<a class=\"tool-img\" href='javascript:embedImage();' title=\"插入图片\"><i class=\"icon-img\"></i><i class=\"fa fa-picture-o\"></i></a>\r\n" + 
			"											<a class=\"emoji\" href=\"\" title=\"插入表情\"><i class=\"fa fa-meh-o\"></i></a>\r\n" + 
			"					</p>\r\n" + 
			"\r\n" + 
			"					\r\n" + 
			"					<div id=\"comment-author-info\">\r\n" + 
			"						<p class=\"comment-form-author\">\r\n" + 
			"							<label for=\"author\">昵称<span class=\"required\">*</span></label>\r\n" + 
			"							<input type=\"text\" name=\"author\" id=\"author\" class=\"commenttext\" value=\"\" tabindex=\"2\" />\r\n" + 
			"						</p>\r\n" + 
			"						<p class=\"comment-form-email\">\r\n" + 
			"							<label for=\"email\">邮箱<span class=\"required\">*</span></label>\r\n" + 
			"							<input type=\"text\" name=\"email\" id=\"email\" class=\"commenttext\" value=\"\" tabindex=\"3\" />\r\n" + 
			"						</p>\r\n" + 
			"						<p class=\"comment-form-url\">\r\n" + 
			"							<label for=\"url\">网址</label>\r\n" + 
			"							<input type=\"text\" name=\"url\" id=\"url\" class=\"commenttext\" value=\"\" tabindex=\"4\" />\r\n" + 
			"						</p>\r\n" + 
			"					</div>\r\n" + 
			"					\r\n" + 
			"					<div class=\"qaptcha\"></div>\r\n" + 
			"\r\n" + 
			"					<div class=\"clear\"></div>\r\n" + 
			"					<p class=\"form-submit\">\r\n" + 
			"						<input id=\"submit\" name=\"submit\" type=\"submit\" tabindex=\"5\" value=\"提交评论\"/>\r\n" + 
			"						<input type='hidden' name='comment_post_ID' value='15850' id='comment_post_ID' />\r\n" + 
			"<input type='hidden' name='comment_parent' id='comment_parent' value='0' />\r\n" + 
			"					</p>\r\n" + 
			"\r\n" + 
			"					<span class=\"mail-notify\">\r\n" + 
			"													<input type=\"checkbox\" name=\"comment_mail_notify\" id=\"comment_mail_notify\" class=\"notify\" value=\"comment_mail_notify\" />\r\n" + 
			"												<label for=\"comment_mail_notify\"><span>有回复时邮件通知我</span></label>\r\n" + 
			"					</span>\r\n" + 
			"				</form>\r\n" + 
			"\r\n" + 
			"	 				</div>\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"</div>\r\n" + 
			"<!-- #comments -->				\r\n" + 
			"			\r\n" + 
			"		</main><!-- .site-main -->\r\n" + 
			"	</div><!-- .content-area -->\r\n" + 
			"\r\n" + 
			"<div id=\"sidebar\" class=\"widget-area all-sidebar\">\r\n" + 
			"\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"			<aside id=\"hot_commend-3\" class=\"widget widget_hot_commend wow fadeInUp\" data-wow-delay=\"0.3s\"><h3 class=\"widget-title\"><i class=\"fa fa-bars\"></i>优惠推荐</h3>\r\n" + 
			"<div id=\"hot\" class=\"hot_commend\">\r\n" + 
			"	<ul>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/1698.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/04/20180416031209859.jpg&w=280&h=210&a=&zc=1\" alt=\"喜讯！年轻人的第一张交行白金卡-优逸白金信用卡\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/1698.html\" rel=\"bookmark\">喜讯！年轻人的第一张交行白金卡-优逸白金信用卡</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 5,956</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;0</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/1669.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/04/20180406105915422.jpg&w=280&h=210&a=&zc=1\" alt=\"x丝三白之白麒麟，境外活动狂薅积分里程\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/1669.html\" rel=\"bookmark\">x丝三白之白麒麟，境外活动狂薅积分里程</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 3,952</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;5</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/603.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2017/02/20170203122937559.jpg&w=280&h=210&a=&zc=1\" alt=\"我是如何天天免费喝星巴克的？\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/603.html\" rel=\"bookmark\">我是如何天天免费喝星巴克的？</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 13,818</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;5</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/595.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/2.jpg\" alt=\"信用卡还款优惠券、白条还款优惠券汇总\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/595.html\" rel=\"bookmark\">信用卡还款优惠券、白条还款优惠券汇总</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 11,729</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;1</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/414.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2016/12/20161226171759465.jpg&w=280&h=210&a=&zc=1\" alt=\"羊毛优惠使用指南\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/414.html\" rel=\"bookmark\">羊毛优惠使用指南</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 9,447</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;4</i>\r\n" + 
			"			</li>\r\n" + 
			"					</ul>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside><aside id=\"about-3\" class=\"widget widget_about wow fadeInUp\" data-wow-delay=\"0.3s\"><h3 class=\"widget-title\"><i class=\"fa fa-bars\"></i>关于本站</h3>\r\n" + 
			"<div id=\"feed_widget\">\r\n" + 
			"	<div class=\"feed-about\">\r\n" + 
			"		<div class=\"about-main\">\r\n" + 
			"			<div class=\"about-img\">\r\n" + 
			"				<img src=\"https://img.zrfan.com/cdn/2017/02/weixin.jpg\" alt=\"QR Code\"/>\r\n" + 
			"			</div>\r\n" + 
			"			<div class=\"about-name\">羊毛优惠</div>\r\n" + 
			"			<div class=\"about-the\">纯人工为您找寻各种银行信用卡优惠活动信息！每天薅羊毛，幸福多一点！</div>\r\n" + 
			"		</div>\r\n" + 
			"		<div class=\"clear\"></div>\r\n" + 
			"		<ul>\r\n" + 
			"			<li class=\"weixin\">\r\n" + 
			"				<span class=\"tipso_style\" id=\"tip-w\" data-tipso='<span class=\"weixin-qr\"><img src=\"https://img.zrfan.com/cdn/2017/02/weixin.jpg\" alt=\" weixin\"/></span>'><a title=\"微信\"><i class=\"fa fa-weixin\"></i></a></span>\r\n" + 
			"			</li>\r\n" + 
			"			<li class=\"tqq\"><a target=blank rel=\"external nofollow\" href=http://wpa.qq.com/msgrd?V=3&uin=2969451814&Site=QQ&Menu=yes title=\"QQ在线\"><i class=\"fa fa-qq\"></i></a></li>\r\n" + 
			"			<li class=\"tsina\"><a title=\"\" href=\"输入链接地址\" target=\"_blank\" rel=\"external nofollow\"><i class=\"fa fa-weibo\"></i></a></li>\r\n" + 
			"			<li class=\"feed\"><a title=\"\" href=\"//www.zrfan.com/feed/\" target=\"_blank\" rel=\"external nofollow\"><i class=\"fa fa-rss\"></i></a></li>\r\n" + 
			"		</ul>\r\n" + 
			"		<div class=\"about-inf\">\r\n" + 
			"			<span class=\"about-pn\">文章 1703</span>\r\n" + 
			"			<span class=\"about-cn\">浏览 3939176</span>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside><aside id=\"advert-3\" class=\"widget widget_advert wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"<div id=\"advert_widget\">\r\n" + 
			"	<script async src=\"//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js\"></script>\r\n" + 
			"<!-- 全站右侧 -->\r\n" + 
			"<ins class=\"adsbygoogle\"\r\n" + 
			"     style=\"display:inline-block;width:300px;height:250px\"\r\n" + 
			"     data-ad-client=\"ca-pub-2207325541606966\"\r\n" + 
			"     data-ad-slot=\"7410917797\"></ins>\r\n" + 
			"<script>\r\n" + 
			"(adsbygoogle = window.adsbygoogle || []).push({});\r\n" + 
			"</script></div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside>	\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div>	</div><!-- .site-content -->\r\n" + 
			"\r\n" + 
			"	<div class=\"clear\"></div>\r\n" + 
			"				\r\n" + 
			"	<div id=\"footer-widget-box\" class=\"site-footer wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"	<div class=\"footer-widget\">\r\n" + 
			"		<aside id=\"nav_menu-2\" class=\"widget widget_nav_menu wow fadeInUp\" data-wow-delay=\"0.3s\"><h3 class=\"widget-title\"><span class=\"s-icon\"></span>更多精彩</h3><div class=\"menu-%e5%ba%95%e9%83%a8-container\"><ul id=\"menu-%e5%ba%95%e9%83%a8\" class=\"menu\"><li id=\"menu-item-7798\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7798\"><a href=\"//www.zrfan.com/new\">最新刷卡指南</a></li>\r\n" + 
			"<li id=\"menu-item-7803\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7803\"><a href=\"//www.zrfan.com/random-articles\">随机文章</a></li>\r\n" + 
			"<li id=\"menu-item-7797\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7797\"><a href=\"//www.zrfan.com/archive\">文章归档</a></li>\r\n" + 
			"<li id=\"menu-item-7802\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7802\"><a href=\"//www.zrfan.com/site-collection\">银行大全</a></li>\r\n" + 
			"<li id=\"menu-item-7800\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7800\"><a href=\"//www.zrfan.com/hot-tag\">热门标签</a></li>\r\n" + 
			"<li id=\"menu-item-7794\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7794\"><a href=\"//www.zrfan.com/about\">关于本站</a></li>\r\n" + 
			"<li id=\"menu-item-7801\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7801\"><a href=\"//www.zrfan.com/contact\">联系方式</a></li>\r\n" + 
			"<li id=\"menu-item-7806\" class=\"menu-item menu-item-type-post_type menu-item-object-page menu-item-7806\"><a href=\"//www.zrfan.com/suiji\">随便看看</a></li>\r\n" + 
			"</ul></div><div class=\"clear\"></div></aside>		<div class=\"clear\"></div>\r\n" + 
			"	</div>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"	<footer id=\"colophon\" class=\"site-footer wow fadeInUp\" data-wow-delay=\"0.3s\" role=\"contentinfo\">\r\n" + 
			"		<div class=\"site-info\">\r\n" + 
			"			Copyright ©  羊毛优惠  保留所有权利 豫ICP备14007442号-2 本站部分内容收集于互联网，如果有侵权内容、不妥之处，请联系我们删除。敬请谅解!			<span class=\"add-info\">\r\n" + 
			"								<script src=\"https://s4.cnzz.com/z_stat.php?id=1260529009&web_id=1260529009\" language=\"JavaScript\"></script>\r\n" + 
			"<script type=\"text/javascript\" src=\"//res.zgboke.com/wp-content/themes/begin/diy/wp-dialog/Dialog.js?skin=default\"></script>			</span>\r\n" + 
			"		</div><!-- .site-info -->\r\n" + 
			"	</footer><!-- .site-footer -->\r\n" + 
			"<div id=\"login\">\r\n" + 
			"	\r\n" + 
			"	<div id=\"login-tab\" class=\"login-tab-product\">\r\n" + 
			"	    <h2 class=\"login-tab-hd\">\r\n" + 
			"			<span class=\"login-tab-hd-con\"><a href=\"javascript:\">登录</a></span>\r\n" + 
			"									<span class=\"login-tab-hd-con\"><a href=\"javascript:\">找回密码</a></span>\r\n" + 
			"	    </h2>\r\n" + 
			"	\r\n" + 
			"		<div class=\"login-tab-bd login-dom-display\">\r\n" + 
			"			<div class=\"login-tab-bd-con login-current\">\r\n" + 
			"				<div id=\"tab1_login\" class=\"tab_content_login\">\r\n" + 
			"					<form method=\"post\" action=\"//www.zrfan.com/wp-login.php\" class=\"wp-user-form\">\r\n" + 
			"						<div class=\"username\">\r\n" + 
			"							<label for=\"user_login\">用户名</label>\r\n" + 
			"							<input type=\"text\" name=\"log\" value=\"\" size=\"20\" id=\"user_login\" tabindex=\"11\" />\r\n" + 
			"						</div>\r\n" + 
			"						<div class=\"password\">\r\n" + 
			"							<label for=\"user_pass\">密码</label>\r\n" + 
			"							<input type=\"password\" name=\"pwd\" value=\"\" size=\"20\" id=\"user_pass\" tabindex=\"12\" />\r\n" + 
			"						</div>\r\n" + 
			"						<div class=\"login_fields\">\r\n" + 
			"							<div class=\"rememberme\">\r\n" + 
			"								<label for=\"rememberme\">\r\n" + 
			"									<input type=\"checkbox\" name=\"rememberme\" value=\"forever\" checked=\"checked\" id=\"rememberme\" tabindex=\"13\" />记住我的登录信息								</label>\r\n" + 
			"							</div>\r\n" + 
			"							<input type=\"submit\" name=\"user-submit\" value=\"登录\" tabindex=\"14\" class=\"user-submit\" />\r\n" + 
			"							<input type=\"hidden\" name=\"redirect_to\" value=\"/2462.html\" />\r\n" + 
			"							<input type=\"hidden\" name=\"user-cookie\" value=\"1\" />\r\n" + 
			"							<div class=\"login-form\"></div>\r\n" + 
			"						</div>\r\n" + 
			"					</form>\r\n" + 
			"				</div>\r\n" + 
			"			</div>\r\n" + 
			"\r\n" + 
			"						\r\n" + 
			"			<div class=\"login-tab-bd-con\">\r\n" + 
			"				<div id=\"tab3_login\" class=\"tab_content_login\">\r\n" + 
			"					<p class=\"message\">输入用户名或电子邮箱地址，您会收到一封新密码链接的电子邮件。</p>\r\n" + 
			"					<form method=\"post\" action=\"//www.zrfan.com/wp-login.php?action=lostpassword\" class=\"wp-user-form\">\r\n" + 
			"						<div class=\"username\">\r\n" + 
			"							<label for=\"user_login\" class=\"hide\">用户名或电子邮件地址</label>\r\n" + 
			"							<input type=\"text\" name=\"user_login\" value=\"\" size=\"20\" id=\"user_login\" tabindex=\"1001\" />\r\n" + 
			"						</div>\r\n" + 
			"						<div class=\"login_fields\">\r\n" + 
			"							<div class=\"login-form\"></div>\r\n" + 
			"							<input type=\"submit\" name=\"user-submit\" value=\"获取新密码\" class=\"user-submit\" tabindex=\"1002\" />\r\n" + 
			"														<input type=\"hidden\" name=\"redirect_to\" value=\"/2462.html?reset=true\" />\r\n" + 
			"							<input type=\"hidden\" name=\"user-cookie\" value=\"1\" />\r\n" + 
			"						</div>\r\n" + 
			"					</form>\r\n" + 
			"				</div>\r\n" + 
			"			</div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"	</div>\r\n" + 
			"<script>window._bd_share_config={\"common\":{\"bdSnsKey\":{},\"bdText\":\"\",\"bdMini\":\"2\",\"bdMiniList\":false,\"bdPic\":\"\",\"bdStyle\":\"1\",\"bdSize\":\"16\"},\"share\":{\"bdSize\":16}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='//www.zrfan.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>\r\n" + 
			"\r\n" + 
			"<ul id=\"scroll\">\r\n" + 
			"	<li class=\"log log-no\"><a class=\"log-button\" title=\"文章目录\"><i class=\"fa fa-bars\"></i></a><div class=\"log-prompt\"><div class=\"log-arrow\">文章目录</div></div></li>\r\n" + 
			"	<li><a class=\"scroll-h\" title=\"返回顶部\"><i class=\"fa fa-angle-up\"></i></a></li>\r\n" + 
			"	<li><a class=\"scroll-c\" title=\"评论\"><i class=\"fa fa-comment-o\"></i></a></li>	<li><a class=\"scroll-b\" title=\"转到底部\"><i class=\"fa fa-angle-down\"></i></a></li>\r\n" + 
			"	<li class=\"gb2-site\"><a id=\"gb2big5\"><span>繁</span></a></li>	<li class=\"qqonline\">\r\n" + 
			"	<div class=\"online\"><a href=\"javascript:void(0)\" ><i class=\"fa fa-qq\"></i></a></div>\r\n" + 
			"					<div class=\"qqonline-box\">\r\n" + 
			"				<div class=\"qqonline-main\">\r\n" + 
			"							<div class=\"nline-wiexin\">\r\n" + 
			"					<h4>微信</h4>\r\n" + 
			"					<img title=\"微信\" alt=\"微信\" src=\"//www.zrfan.com/cdn/2017/02/weixin.jpg\"/>\r\n" + 
			"				</div>\r\n" + 
			"						<div class=\"nline-qq\"><a target=\"_blank\" rel=\"external nofollow\" href=\"http://wpa.qq.com/msgrd?v=3&uin=2969451814&site=qq&menu=yes\"><i class=\"fa fa-qq\"></i>在线咨询</a></div>\r\n" + 
			"		</div>\r\n" + 
			"		<span class=\"qq-arrow\"></span>\r\n" + 
			"	</div>\r\n" + 
			"</li>			<li class=\"qr-site\"><a href=\"javascript:void(0)\" class=\"qr\" title=\"本页二维码\"><i class=\"fa fa-qrcode\"></i><span class=\"qr-img\"><span id=\"output\"><img class=\"alignnone\" src=\"//www.zrfan.com/cdn/2017/02/C_130.png\" alt=\"icon\"/></span><span class=\"arrow arrow-z\"><i class=\"fa fa-caret-right\"></i></span><span class=\"arrow arrow-y\"><i class=\"fa fa-caret-right\"></i></span></span></a></li>\r\n" + 
			"		<script type=\"text/javascript\">$(document).ready(function(){if(!+[1,]){present=\"table\";} else {present=\"canvas\";}$('#output').qrcode({render:present,text:window.location.href,width:\"150\",height:\"150\"});});</script>\r\n" + 
			"	</ul>\r\n" + 
			"\r\n" + 
			"</div><!-- .site -->\r\n" + 
			"\r\n" + 
			"<script type=\"text/javascript\" src=\"https://static.zrfan.com/wp-content/themes/begin/js/jquery-ui.min.js\"></script>\r\n" + 
			"<script type=\"text/javascript\" src=\"https://static.zrfan.com/wp-content/themes/begin/js/qaptcha.jquery.js\"></script>\r\n" + 
			"<script type=\"text/javascript\">var QaptchaJqueryPage=\"//www.zrfan.com/wp-content/themes/begin/inc/function/qaptcha.jquery.php\"</script>\r\n" + 
			"<script type=\"text/javascript\">$(document).ready(function(){$('.qaptcha').QapTcha();});</script>\r\n" + 
			"<script type='text/javascript'>\r\n" + 
			"/* <![CDATA[ */\r\n" + 
			"var viewsCacheL10n = {\"admin_ajax_url\":\"https:\\/\\/www.zrfan.com\\/wp-admin\\/admin-ajax.php\",\"post_id\":\"15850\"};\r\n" + 
			"/* ]]> */\r\n" + 
			"</script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/plugins/wp-postviews/postviews-cache.js?ver=1.68'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/superfish.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/gb2big5.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/carousel.min.js?ver=2017.02.04'></script>\r\n" + 
			"<script type=\"text/javascript\">var ias=$.ias({container:\"#comments\",item:\".comment-list\",pagination:\".scroll-links\",next:\".scroll-links .nav-previous a\",});ias.extension(new IASTriggerExtension({text:'<i class=\"fa fa-chevron-circle-down\"></i>更多',offset: 0,}));ias.extension(new IASSpinnerExtension());ias.extension(new IASNoneLeftExtension({text:'已是最后',}));ias.on('rendered',function(items){$(\"img\").lazyload({effect: \"fadeIn\",failure_limit: 10});});</script>\r\n" + 
			"\r\n" + 
			"</body>\r\n" + 
			"</html>\r\n" + 
			"<!-- Dynamic page generated in 0.642 seconds. -->\r\n" + 
			"<!-- Cached page generated by WP-Super-Cache on 2018-11-26 22:07:24 -->\r\n" + 
			"\r\n" + 
			"<!-- super cache -->";
}
