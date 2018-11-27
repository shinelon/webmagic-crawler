/**
 *TargetHtmlTest.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.html;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * TargetHtmlTest.java
 *
 * @author syq
 *
 */
public class TargetHtmlTest {
	private static final Logger logger = LoggerFactory.getLogger(TargetHtmlTest.class);
	
	private Html listHtml= new Html(TARGET_HTML_STR); 
	
	@Test
	public void findContentTest() {
		String content = listHtml.xpath("//div[@class=\"single-content\"]").get();
		logger.info("content:{}",content);
		Document doc = Jsoup.parse(content);
		String text = doc.select("div").text();
		logger.info("ticontenttle text:{}",text);
	}
	
	@Test
	public void findTitleTest() {
		String title = listHtml.xpath("//h1[@class=\"entry-title\"]").get();
		logger.info("title:{}",title);
		Document doc = Jsoup.parse(title);
		String text = doc.select("h1").text();
		logger.info("title text:{}",text);
	}
	
	@Test
	public void findKeywordsTest() {
		Selectable keywords = listHtml.xpath("/html/head/meta[7]");
		logger.info("keywords:{}",keywords.get());
		Document doc = Jsoup.parse(keywords.get());
		String content = doc.select("meta").attr("content");
		logger.info("keywords content:{}",content);
	}
	
	@Test
	public void findBankNameTest() {
		String keywords = "上海银行信用卡优惠,上海银行信用卡活动";
		String[] split = StringUtils.split(keywords, ",");
		int indexOf = StringUtils.indexOf(split[0], "信用卡");
		String substring = StringUtils.substring(split[0], 0,indexOf);
		logger.info("findBankName:{}",substring);
	}
	
	
	private static final String TARGET_HTML_STR="\r\n" + 
			"<!DOCTYPE html>\r\n" + 
			"<html lang=\"zh-CN\">\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"UTF-8\">\r\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">\r\n" + 
			"<meta http-equiv=\"Cache-Control\" content=\"no-transform\" />\r\n" + 
			"<meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\r\n" + 
			"<meta name=\"baidu_union_verify\" content=\"840d7e94142d30e03eb831b802362791\">\r\n" + 
			"<title>上海银行信用卡移动支付每周享好礼 | 羊毛优惠</title>\r\n" + 
			"<meta name=\"description\" content=\"活动时间：\" />\r\n" + 
			"<meta name=\"keywords\" content=\"上海银行信用卡优惠,上海银行信用卡活动\" />\r\n" + 
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
			"<link rel=\"canonical\" href=\"//www.zrfan.com/2460.html\" />\r\n" + 
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
			"<body class=\"post-template-default single single-post postid-15842 single-format-standard\">\r\n" + 
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
			"<li id=\"menu-item-12372\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-12372\"><a href=\"//www.zrfan.com/category/zhinan/\"><i class=\"fa-book fa\"></i><span class=\"font-text\">每日刷卡指南</span></a></li>\r\n" + 
			"<li id=\"menu-item-7733\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category current-post-ancestor menu-item-has-children menu-item-7733\"><a href=\"//www.zrfan.com/category/bank/\"><i class=\"fa-credit-card-alt fa\"></i><span class=\"font-text\">银行卡</span></a>\r\n" + 
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
			"		<li id=\"menu-item-7929\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category current-post-ancestor current-menu-parent current-post-parent menu-item-7929\"><a href=\"//www.zrfan.com/category/bank/shanghai/\">上海银行优惠活动</a></li>\r\n" + 
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
			"		<a class=\"crumbs\" href=\"//www.zrfan.com/\"><i class=\"fa fa-home\"></i>首页</a><i class=\"fa fa-angle-right\"></i><a href=\"//www.zrfan.com/category/bank/\">银行优惠活动</a><i class=\"fa fa-angle-right\"></i><a href=\"//www.zrfan.com/category/bank/shanghai/\" rel=\"category tag\">上海银行优惠活动</a><i class=\"fa fa-angle-right\"></i>正文	</nav>\r\n" + 
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
			"				<article id=\"post-15842\" class=\"post-15842 post type-post status-publish format-standard hentry category-shanghai tag-255 tag-256 rrs\">\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"									<h1 class=\"entry-title\">上海银行信用卡移动支付每周享好礼</h1>						</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"single-content\">\r\n" + 
			"				\r\n" + 
			"				\r\n" + 
			"				<p><a href=\"//www.zrfan.com/cdn/2018/11/20181125125857202.jpg\" class=\"fancybox\" data-fancybox-group=\"button\"><img class=\"aligncenter size-full wp-image-15843\" src=\"//www.zrfan.com/cdn/2018/11/20181125125857202.jpg\" alt=\"上海银行信用卡移动支付每周享好礼\" alt=\"\" width=\"500\" height=\"800\" /></a></p>\r\n" + 
			"<h2>活动时间：</h2>\r\n" + 
			"<p>2018年11月26日——2018年12月30日</p>\r\n" + 
			"<h2>活动内容：</h2>\r\n" + 
			"<p>11/26-12/2、12/3-12/9、12/10-12/16、12/17-12/23、12/24-12/30期间每周满3笔移动支付不限金额，享抽奖1次，每周最高享3次抽奖机会！</p>\r\n" + 
			"<h2>抽奖礼品：</h2>\r\n" + 
			"<p>康宁百丽餐具双耳饭碗三件套、K.S.菲奥纳真空焖烧罐、乐扣乐扣热水壶、福临门东町越光米2kg、欧丽薇兰特级初榨橄榄油500ml、哈根达斯单球冰淇淋权益、优酷土豆黄金会员权益（1个月）、腾讯视频VIP权益（1个月）、9积分、99积分、999积分、9999积分。</p>\r\n" + 
			"<p>注：</p>\r\n" + 
			"<p>1. <span style=\"color: #ff0000;\">每周每户最高享3次抽奖机会</span>，需至手机银行-首页—热门活动-达标抽奖参与；每周抽奖礼品动态调整，详见手机银行抽奖页面。</p>\r\n" + 
			"<p>2. 移动支付类型：支付宝快捷支付、微信快捷支付、云闪付支付；其中云闪付支付仅含二维码支付和手机闪付；其中二维码支付包括：上海银行手机银行二维码、云闪付APP二维码;手机闪付包括：Apple Pay、华为Pay、小米Pay。</p>\r\n" + 
			"<h2>活动细则：</h2>\r\n" + 
			"<p>1. 本活动适用于账户及卡片状态正常的上海银行个人信用卡持卡人（各类单标境外卡、单位信用卡除外），主附卡交易金额合并计算，附属卡交易归入主卡统计。</p>\r\n" + 
			"<p>2. 本活动限移动支付类型消费：支付宝快捷支付、微信快捷支付、云闪付支付。其中云闪付支付仅含二维码支付和手机闪付；其中二维码支付包括：上海银行手机银行二维码、云闪付APP二维码;手机闪付包括：Apple Pay、华为Pay、小米Pay。</p>\r\n" + 
			"<p>3. 交易统计时间、交易金额以上海银行信用卡系统记录的交易时间为准，因时差、商户及收单行结算交易延迟等原因造成的交易不符合活动条件，卡中心不承担任何责任。</p>\r\n" + 
			"<p>4. 若发生全额退货导致交易金额不符活动要求，该笔原始交易将不予以统计。</p>\r\n" + 
			"<p>5. 每周活动结束后，我行将于次周以短信通知达标客户，客户需在指定时间内至手机银行参与抽奖，其中手机银行绑定的手机号必须与办理信用卡预留手机号一致。逾期未领取视为自动放弃抽奖资格。如领奖过程有任何问题，详询在线客服。</p>\r\n" + 
			"<p>1） 积分奖励于客户领取后45日内优先导入客户（主卡人）名下任意一张计上海银行积分的有效信用卡；积分有效期至2019年12月31日。如客户名下仅有吉祥航空联名信用卡、联通联名信用卡、彩贝联名信用卡、上海银行唯品花联名信用卡、京东小白信用卡、美团点评美食联名信用卡，积分奖励将转为任意一张联名卡的积分。联名卡积分转化以具体联名卡转换规则为准。</p>\r\n" + 
			"<p>2） 实物礼品奖励（康宁百丽餐具双耳饭碗三件套、K.S.菲奥纳真空焖烧罐、乐扣乐扣热水壶、福临门东町越光米2kg、欧丽薇兰特级初榨橄榄油500ml）于客户领取后45日内，由指定供应商配送至客户领奖时登记的地址（未登记地址的按客户账单地址配送）。如因客户的信息登记错误导致礼品未收到，由客户自行承担。</p>\r\n" + 
			"<p>3） 电子权益（优酷视频、腾讯视频、哈根达斯单球）于客户领取后45日内发送电子码至达标客户的信用卡预留手机号，若因客户手机号码问题导致未收到发码短信、因客户本人未妥善保存电子码导致券码泄露或过期未使用等情况，责任由客户自行承担，我行将不予补发，请在有效期内使用。</p>\r\n" + 
			"<p>6. 上海银行非本次礼品的提供商，如有任何礼品质量或与礼品有关的问题，我行将协助持卡人与商家协商解决。奖品以客户实际抽取为准，不得调换。所赠积分及实物礼品不可兑换现金或同等价值的其他礼品。</p>\r\n" + 
			"<p>7. 客户若未收到奖励或对奖励有异议，可于2019年3月31日前致电上海银行客服热线95594反馈，逾期将视为放弃反馈权利，我行将不再受理。</p>\r\n" + 
			"<p>8. 客户如有以下任一情况：所持信用卡被停用或管制、自行注销信用卡、拒不偿还上海银行信用卡欠款、在信用卡使用中存在欺诈或舞弊行为及违反本活动办法及其他相关规定的，卡中心有权取消其参加本活动的资格并无须另行通知持卡人。</p>\r\n" + 
			"<p>9. 法律许可范围内，卡中心有权修订本活动条款及细则（包括但不限于参加资格、消费时间及奖励方式等）、暂停或取消本活动，并经相关途径（如我行官方网站、短信、微信、各分支行网点等）公告后生效。</p>\r\n" + 
			"<p>10. 其他未尽事宜，仍受《上海银行信用卡章程》、《上海银行信用卡领用合约》及其他相关文件约束。</p>\r\n" + 
			"<p>11. 本行员工不得参加本次抽奖活动。</p>\r\n" + 
			"			</div>\r\n" + 
			"\r\n" + 
			"						\r\n" + 
			"									\r\n" + 
			"										<span class=\"content-more\"><a href=\"http://www.bosc.cn/zh/xyk/xyk_ywdt/78848.shtml\" target=\"_blank\" rel=\"nofollow\">直达链接</a></span>\r\n" + 
			"			\r\n" + 
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
			"	         <a href=\"javascript:;\" data-action=\"ding\" data-id=\"15842\" title=\"点赞\" class=\"favorite\"><i class=\"fa fa-thumbs-up\"></i>赞 <i class=\"count\">\r\n" + 
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
			"				<ul class=\"single-meta\"><li class=\"print\"><a href=\"javascript:printme()\" target=\"_self\" title=\"打印\"><i class=\"fa fa-print\"></i></a></li><li class=\"comment\"><a href=\"//www.zrfan.com/2460.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></li><li class=\"views\"><i class=\"fa fa-eye\"></i> 95</li><li class=\"r-hide\"><a href=\"javascript:pr()\" title=\"侧边栏\"><i class=\"fa fa-caret-left\"></i> <i class=\"fa fa-caret-right\"></i></a></li></ul><ul id=\"fontsize\"><li>A+</li></ul><div class=\"single-cat-tag\"><div class=\"single-cat\">所属分类：<a href=\"//www.zrfan.com/category/bank/shanghai/\" rel=\"category tag\">上海银行优惠活动</a></div></div>			</footer><!-- .entry-footer -->\r\n" + 
			"\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"	</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"<div class=\"single-tag\"><ul class=\"wow fadeInUp\" data-wow-delay=\"0.3s\"><li><a href=\"//www.zrfan.com/tag/%e4%b8%8a%e6%b5%b7%e9%93%b6%e8%a1%8c%e4%bf%a1%e7%94%a8%e5%8d%a1%e4%bc%98%e6%83%a0/\" rel=\"tag\">上海银行信用卡优惠</a></li><li><a href=\"//www.zrfan.com/tag/%e4%b8%8a%e6%b5%b7%e9%93%b6%e8%a1%8c%e4%bf%a1%e7%94%a8%e5%8d%a1%e6%b4%bb%e5%8a%a8/\" rel=\"tag\">上海银行信用卡活动</a></li></ul></div>\r\n" + 
			"									<div class=\"authorbio wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"	<img src=\"https://secure.gravatar.com/avatar/4022dc143931b70a6085968c674159d5?s=128&d=mm\" alt=\"avatar\" class=\"avatar avatar-128\" height=\"128\" width=\"128\">\r\n" + 
			"	<ul class=\"spostinfo\">\r\n" + 
			"		<li>\r\n" + 
			"								<li><strong>版权声明：</strong>本页面文章，于15小时前，由 <b>匿名网友</b> 整理投稿，共 1715 字。</li>\r\n" + 
			"		<li class=\"reprinted\"><strong>转载请注明：</strong><a href=\"//www.zrfan.com/2460.html\" rel=\"bookmark\" title=\"本文固定链接 https://www.zrfan.com/2460.html\">上海银行信用卡移动支付每周享好礼 | 羊毛优惠，并标明原文出处</a></li>\r\n" + 
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
			"				<a href=\"//www.zrfan.com/2453.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/11/20181122124003807.jpg&w=280&h=210&a=&zc=1\" alt=\"上海银行信用卡美团外卖下午茶随机立减最高99元\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2453.html\">上海银行信用卡美团外卖下午茶随机立减最高99元</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2452.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/11/20181122123701317.jpg&w=280&h=210&a=&zc=1\" alt=\"上海银行信用卡猫眼电影满50-25元（周五）\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2452.html\">上海银行信用卡猫眼电影满50-25元（周五）</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2430.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/11/20181116123955937.jpg&w=280&h=210&a=&zc=1\" alt=\"上海银行信用卡周六、周日至满记甜品、面包新语等品牌满50-20元\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2430.html\">上海银行信用卡周六、周日至满记甜品、面包新语等品牌满50-20元</a></div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"		\r\n" + 
			"	<div class=\"r4\">\r\n" + 
			"		<div class=\"related-site\">\r\n" + 
			"			<figure class=\"related-site-img\">\r\n" + 
			"				<a href=\"//www.zrfan.com/2403.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/11/20181108125936428.jpg&w=280&h=210&a=&zc=1\" alt=\"上海银行信用卡双十一尽情刷\" /></a>			 </figure>\r\n" + 
			"			<div class=\"related-title\"><a href=\"//www.zrfan.com/2403.html\">上海银行信用卡双十一尽情刷</a></div>\r\n" + 
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
			"				<li><a href=\"//www.zrfan.com/1848.html\" rel=\"bookmark\">上海银行信用卡网点享1元充10元话费</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/907.html\" rel=\"bookmark\">上海银行信用卡周五格瓦拉观影买一赠一、立减5元</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/880.html\" rel=\"bookmark\">上海银行信用卡五月日日刷，最高可得50w积分，还有1积分哈根达斯</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/2033.html\" rel=\"bookmark\">上海银行信用卡周三星巴克买一赠一</a></li>\r\n" + 
			"				<li><a href=\"//www.zrfan.com/1202.html\" rel=\"bookmark\">上海银行信用卡周周刷9月</a></li>\r\n" + 
			"					</ul>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside>		<aside id=\"recent-posts-2\" class=\"widget widget_recent_entries wow fadeInUp\" data-wow-delay=\"0.3s\">		<h3 class=\"widget-title\"><span class=\"s-icon\"></span>最新优惠</h3>		<ul>\r\n" + 
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
			"					<li>\r\n" + 
			"				<a href=\"//www.zrfan.com/2457.html\">工商银行信用卡微信百大商户随机减</a>\r\n" + 
			"						</li>\r\n" + 
			"				</ul>\r\n" + 
			"		<div class=\"clear\"></div></aside>			</div>\r\n" + 
			"	<div class=\"clear\"></div>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"				<nav class=\"nav-single wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
			"					<a href=\"//www.zrfan.com/2453.html\" rel=\"prev\"><span class=\"meta-nav\"><span class=\"post-nav\"><i class=\"fa fa-angle-left\"></i> 上一篇</span><br/>上海银行信用卡美团外卖下午茶随机立减最高99元</span></a><span class='meta-nav'><span class='post-nav'>没有了<br/></span>已是最新文章</span>					<div class=\"clear\"></div>\r\n" + 
			"				</nav>\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"	<nav class=\"navigation post-navigation\" role=\"navigation\">\r\n" + 
			"		<h2 class=\"screen-reader-text\">文章导航</h2>\r\n" + 
			"		<div class=\"nav-links\"><div class=\"nav-previous\"><a href=\"//www.zrfan.com/2459.html\" rel=\"prev\"><span class=\"meta-nav-r\" aria-hidden=\"true\"><i class=\"fa fa-angle-left\"></i></span></a></div><div class=\"nav-next\"><a href=\"//www.zrfan.com/2461.html\" rel=\"next\"><span class=\"meta-nav-l\" aria-hidden=\"true\"><i class=\"fa fa-angle-right\"></i></span></a></div></div>\r\n" + 
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
			"			<h3 id=\"reply-title\" class=\"comment-reply-title\">发表评论<small><a rel=\"nofollow\" id=\"cancel-comment-reply-link\" href=\"/2460.html#respond\" style=\"display:none;\">取消回复</a></small></h3>\r\n" + 
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
			"						<input type='hidden' name='comment_post_ID' value='15842' id='comment_post_ID' />\r\n" + 
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
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 5,943</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;0</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/1669.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/04/20180406105915422.jpg&w=280&h=210&a=&zc=1\" alt=\"x丝三白之白麒麟，境外活动狂薅积分里程\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/1669.html\" rel=\"bookmark\">x丝三白之白麒麟，境外活动狂薅积分里程</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 3,939</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;5</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/603.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2017/02/20170203122937559.jpg&w=280&h=210&a=&zc=1\" alt=\"我是如何天天免费喝星巴克的？\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/603.html\" rel=\"bookmark\">我是如何天天免费喝星巴克的？</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 13,808</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;5</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/595.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/18.jpg\" alt=\"信用卡还款优惠券、白条还款优惠券汇总\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/595.html\" rel=\"bookmark\">信用卡还款优惠券、白条还款优惠券汇总</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 11,728</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;1</i>\r\n" + 
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
			"			<span class=\"about-cn\">浏览 3936574</span>\r\n" + 
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
			"							<input type=\"hidden\" name=\"redirect_to\" value=\"/2460.html\" />\r\n" + 
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
			"														<input type=\"hidden\" name=\"redirect_to\" value=\"/2460.html?reset=true\" />\r\n" + 
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
			"var viewsCacheL10n = {\"admin_ajax_url\":\"https:\\/\\/www.zrfan.com\\/wp-admin\\/admin-ajax.php\",\"post_id\":\"15842\"};\r\n" + 
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
			"<!-- Dynamic page generated in 0.516 seconds. -->\r\n" + 
			"<!-- Cached page generated by WP-Super-Cache on 2018-11-26 12:14:40 -->\r\n" + 
			"\r\n" + 
			"<!-- super cache -->";
}
