/**
 *ZhinanListHtmlTest.java 
 *
 * 2018年11月27日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.html;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * ZhinanListHtmlTest.java
 *
 * @author syq
 *
 */
public class ZhinanListHtmlTest {
	private static final Logger logger = LoggerFactory.getLogger(TargetHtmlTest.class);
	
	private static final String TARGET_URL_PATTERN = "www\\.zrfan\\.com/\\d*\\.html";
	@Test
	public void findTargetUrlTest() {
		Html listHtml= new Html(htmlStr);
		//<h2 class="entry-title"><a href="//www.zrfan.com/2462.html" rel="bookmark">2018-11-27日周二刷卡指南</a></h2>
		Selectable links = listHtml.xpath("//h2[@class=\"entry-title\"]").regex(TARGET_URL_PATTERN);
		logger.info("links:{}",getUrl(links.all()));
		
	}
	
	private List<String> getUrl(List<String> htmls) {
//		htmls.forEach(e->{
//			Document doc = Jsoup.parse(e);
//			String text = doc.text();
//			String dateStr = StringUtils.split(text, "日")[0];
//			logger.info("dateStr:{}",dateStr);
//			if("2018-11-27".equals(dateStr)) {
//				logger.info("dateStr:{}",doc.select("a").attr("href"));
//			}
//		});
		List<String> collect = htmls.stream().map(e -> Jsoup.parse(e))
				.filter(e -> StringUtils.split(e.text(), "日")[0].equals("2018-11-27"))
				.map(e -> "https:" + e.select("a").attr("href")).collect(Collectors.toList());
		return collect;
	}
	
	
	
	private String htmlStr="\r\n" + 
			"<!DOCTYPE html>\r\n" + 
			"<html lang=\"zh-CN\">\r\n" + 
			"<head>\r\n" + 
			"<meta charset=\"UTF-8\">\r\n" + 
			"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no\">\r\n" + 
			"<meta http-equiv=\"Cache-Control\" content=\"no-transform\" />\r\n" + 
			"<meta http-equiv=\"Cache-Control\" content=\"no-siteapp\" />\r\n" + 
			"<meta name=\"baidu_union_verify\" content=\"840d7e94142d30e03eb831b802362791\">\r\n" + 
			"<title>每日刷卡指南 | 羊毛优惠</title>\r\n" + 
			"<meta name=\"description\" content=\"\" />\r\n" + 
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
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/script.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/flexisel.js?ver=2017.02.04'></script>\r\n" + 
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
			"<body class=\"archive category category-zhinan category-106\">\r\n" + 
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
			"																			<h1 class=\"site-title\">\r\n" + 
			"															<a href=\"//www.zrfan.com/\"><img src=\"//www.zrfan.com/cdn/2017/02/20170207101722231.png\" title=\"羊毛优惠\" alt=\"羊毛优惠\" rel=\"home\" /><span class=\"site-name\">羊毛优惠</span></a>\r\n" + 
			"													</h1>\r\n" + 
			"														</div><!-- .logo-site -->\r\n" + 
			"\r\n" + 
			"			<div id=\"site-nav-wrap\">\r\n" + 
			"				<div id=\"sidr-close\"><a href=\"#sidr-close\" class=\"toggle-sidr-close\">×</a></div>\r\n" + 
			"				<nav id=\"site-nav\" class=\"main-nav\">\r\n" + 
			"															<a href=\"#sidr-main\" id=\"navigation-toggle\" class=\"bars\"><i class=\"fa fa-bars\"></i></a>\r\n" + 
			"														<div class=\"menu-%e4%b8%bb%e8%8f%9c%e5%8d%95-container\"><ul id=\"menu-%e4%b8%bb%e8%8f%9c%e5%8d%95\" class=\"down-menu nav-menu\"><li id=\"menu-item-7729\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category menu-item-7729\"><a href=\"//www.zrfan.com/category/jingxuan/\"><i class=\"fa-star fa\"></i><span class=\"font-text\">精选</span></a></li>\r\n" + 
			"<li id=\"menu-item-12372\" class=\"menu-item menu-item-type-taxonomy menu-item-object-category current-menu-item menu-item-12372\"><a href=\"//www.zrfan.com/category/zhinan/\"><i class=\"fa-book fa\"></i><span class=\"font-text\">每日刷卡指南</span></a></li>\r\n" + 
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
			"		<a class=\"crumbs\" href=\"//www.zrfan.com/\"><i class=\"fa fa-home\"></i>首页</a><i class=\"fa fa-angle-right\"></i><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a><i class=\"fa fa-angle-right\"></i>文章 	</nav>\r\n" + 
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
			"												\r\n" + 
			"		<div id=\"content\" class=\"site-content\">\r\n" + 
			"	<section id=\"primary\" class=\"content-area\">\r\n" + 
			"		<main id=\"main\" class=\"site-main\" role=\"main\">\r\n" + 
			"\r\n" + 
			"			\r\n" + 
			"							<article id=\"post-15850\" class=\"wow fadeInUp post-15850 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2462.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/6.jpg\" alt=\"2018-11-27日周二刷卡指南\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2462.html\" rel=\"bookmark\">2018-11-27日周二刷卡指南</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒漂亮妈妈卡6积分抢兑 每日9点起，翼支付APP-中间“领取50”-邀请6位好友助力可得10-50翼支付金 中行9点起每个整点抢50元商城券（11月30日截止） http://t.cn/EzoQous 每天领京东支付京东闪付5-1红包，新户5-5 ht...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"							<span class=\"new-icon\">NEW</span>						<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">12小时前&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 1,910</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2462.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2462.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-15846\" class=\"wow fadeInUp post-15846 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2461.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/8.jpg\" alt=\"2018-11-26日周一刷卡指南\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2461.html\" rel=\"bookmark\">2018-11-26日周一刷卡指南</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				提醒25号浦发流量里程酒店银行过期一批，记得兑换 每日9点起，翼支付APP-中间“领取50”-邀请6位好友助力可得10-50翼支付金 中行9点起每个整点抢50元商城券（11月30日截止） http://t.cn/EzoQous 每天领京东支付京东闪付5-1...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"							<span class=\"new-icon\">NEW</span>						<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">11月25日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 2,725</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2461.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2461.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-15829\" class=\"wow fadeInUp post-15829 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2458.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/19.jpg\" alt=\"2018-11-25日周日刷卡指南\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2458.html\" rel=\"bookmark\">2018-11-25日周日刷卡指南</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒平安我的团活动页面撤销，应为12月开始 每日9点起，翼支付APP-中间“领取50”-邀请6位好友助力可得10-50翼支付金 中行9点起每个整点抢50元商城券（11月30日截止） http://t.cn/EzoQous 每天领京东支付京东闪付5-1红包...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"							<span class=\"new-icon\">NEW</span>						<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">11月24日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 2,380</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2458.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2458.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-15822\" class=\"wow fadeInUp post-15822 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2456.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/2.jpg\" alt=\"2018-11-24日周六刷卡指南\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2456.html\" rel=\"bookmark\">2018-11-24日周六刷卡指南</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒兴业好兴动周六连续签到最高领6666积分，11月24日兴业系统升级，可能无法消费，一切消费类活动暂停，app微信可能无法使用，可以尝试是否可以签到 0点微店pay满减、9点拼多多apple pay满减可能总名额已经用完，卡余额的朋友可以尝试 中行9点...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">11月23日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 2,596</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2456.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2456.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-15817\" class=\"wow fadeInUp post-15817 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2455.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/2.jpg\" alt=\"2018-11-23日周五刷卡指南|广发分享日新增项目\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2455.html\" rel=\"bookmark\">2018-11-23日周五刷卡指南|广发分享日新增项目</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒广发分享日（新增饿了么、周黑鸭、亚马逊、唯品会、携程等）；光大10元观影、线下口碑5折、10元饭票、网易考拉五折（黑五加码）；交行红五（7点30分10点14点有美食券） 微信搜索“万事达卡”小程序，注册参加活动后通过万事达渠道消费，有可能获得免单 微...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">11月22日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 3,956</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2455.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2455.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-15805\" class=\"wow fadeInUp post-15805 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2451.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/13.jpg\" alt=\"2018-11-22日周四刷卡指南\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2451.html\" rel=\"bookmark\">2018-11-22日周四刷卡指南</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 重点工行苏宁满减；银联网易考拉；农行周周赢；华夏本来生活、小白卡满减 明天8点左右关注浦发信用卡官方微信 中行9点起每个整点抢50元商城券（11月30日截止） http://t.cn/EzoQous 每天领京东支付京东闪付5-1红包，新户5-5 http...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">11月21日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 3,043</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2451.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2451.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-15789\" class=\"wow fadeInUp post-15789 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/2441.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/15.jpg\" alt=\"2018-11-21日周三刷卡指南\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/2441.html\" rel=\"bookmark\">2018-11-21日周三刷卡指南</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒光大小白卡京东减，网易考拉卡满减，光大10元看大片；光大卡月末消费3倍积分；工行京东沃尔玛；农行乐刷刷 微信app-我的-钱包-银行卡，点击交行信用卡，随机2-5元立减金 中信银行信用卡官方微信，回复：11.11.。。可以查看双十一消费达标，是否抽中...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">11月20日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 3,036</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/2441.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/2441.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-13608\" class=\"wow fadeInUp post-13608 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/1894.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/2.jpg\" alt=\"2018-6-12日周二刷卡指南|京东神券日\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/1894.html\" rel=\"bookmark\">2018-6-12日周二刷卡指南|京东神券日</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒京东神券日，光大抢支付券，工行融e购抢券，中行京东8倍积分报名，中行快捷支付6倍积分，中行美食券 PS：石榴推荐办理平安卡，成功石榴发200红包+银行开卡礼，推荐办理交行卡，成功石榴发120红包+银行开卡礼，推荐办理招商卡，成功石榴发70红包+银行开...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">06月11日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 5,986</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/1894.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/1894.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-11000\" class=\"wow fadeInUp post-11000 post type-post status-publish format-standard hentry category-zhinan tag-127 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/1286.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/3.jpg\" alt=\"2017-10-28周六刷卡指南|光大满减\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/1286.html\" rel=\"bookmark\">2017-10-28周六刷卡指南|光大满减</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				 提醒兴业5折，建行龙卡星期六屈臣氏、万宁、真功夫等优惠，以及5元15元观影，之前农行抢到全家券的，可以周五周六使用，工行5折优惠，光大淘宝天猫满减，明天看看浦发是否放1元早餐10元午餐 天猫双十一现金红包 https://s.click.taobao.c...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">10月27日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 2,742</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/1286.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/1286.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"							<article id=\"post-8638\" class=\"wow fadeInUp post-8638 post type-post status-publish format-standard hentry category-zhinan tag-171 rrs\" data-wow-delay=\"0.3s\">\r\n" + 
			"			<figure class=\"thumbnail\">\r\n" + 
			"			<a href=\"//www.zrfan.com/773.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/1.jpg\" alt=\"我是如何在屈臣氏买买买的！|屈臣氏优惠活动大全\" /></a>			<span class=\"cat\"><a href=\"//www.zrfan.com/category/zhinan/\">每日刷卡指南</a></span>\r\n" + 
			"		</figure>\r\n" + 
			"		<header class=\"entry-header\">\r\n" + 
			"					<h2 class=\"entry-title\"><a href=\"//www.zrfan.com/773.html\" rel=\"bookmark\">我是如何在屈臣氏买买买的！|屈臣氏优惠活动大全</a></h2>			</header><!-- .entry-header -->\r\n" + 
			"\r\n" + 
			"	<div class=\"entry-content\">\r\n" + 
			"					<div class=\"archive-content\">\r\n" + 
			"				想必很多朋友和石榴一样，都经常去屈臣氏购买美妆个护产品，除了屈臣氏官方或店面活动，我们还可以组合信用卡支付活动，获取最大化优惠。 周二：光大满120-50元（刷卡） 2017年3月7日至2017年5月31日期间的每周二（上午10点至营业结束），光大银行信用...			</div>\r\n" + 
			"			<span class=\"title-l\"></span>\r\n" + 
			"													<span class=\"entry-meta\">\r\n" + 
			"				<span class=\"date\">03月28日&nbsp;&nbsp;</span><span class=\"views\"><i class=\"fa fa-eye\"></i> 1,196</span><span class=\"comment\">&nbsp;&nbsp;<a href=\"//www.zrfan.com/773.html#respond\" rel=\"external nofollow\"><i class=\"fa fa-comment-o\"></i> 发表评论</a></span>			</span>\r\n" + 
			"				<div class=\"clear\"></div>\r\n" + 
			"	</div><!-- .entry-content -->\r\n" + 
			"\r\n" + 
			"					<span class=\"entry-more\"><a href=\"//www.zrfan.com/773.html\" rel=\"bookmark\">阅读全文</a></span>\r\n" + 
			"			</article><!-- #post -->\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"				\r\n" + 
			"			\r\n" + 
			"			\r\n" + 
			"		</main><!-- .site-main -->\r\n" + 
			"\r\n" + 
			"				<nav id=\"nav-below\">\r\n" + 
			"			<div class=\"nav-next\"></div>\r\n" + 
			"			<div class=\"nav-previous\"><a href=\"//www.zrfan.com/category/zhinan/page/2/\" ></a></div>\r\n" + 
			"		</nav>\r\n" + 
			"	\r\n" + 
			"	<nav class=\"navigation pagination\" role=\"navigation\">\r\n" + 
			"		<h2 class=\"screen-reader-text\">文章导航</h2>\r\n" + 
			"		<div class=\"nav-links\"><span class='page-numbers current'><span class=\"screen-reader-text\">第 </span>1<span class=\"screen-reader-text\"> 页</span></span>\r\n" + 
			"<a class='page-numbers' href='//www.zrfan.com/category/zhinan/page/2/'><span class=\"screen-reader-text\">第 </span>2<span class=\"screen-reader-text\"> 页</span></a>\r\n" + 
			"<a class=\"next page-numbers\" href=\"//www.zrfan.com/category/zhinan/page/2/\"><i class=\"fa fa-angle-right\"></i></a></div>\r\n" + 
			"	</nav>\r\n" + 
			"	</section><!-- .content-area -->\r\n" + 
			"\r\n" + 
			"<div id=\"sidebar\" class=\"widget-area all-sidebar\">\r\n" + 
			"\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"	\r\n" + 
			"			<aside id=\"hot_commend-4\" class=\"widget widget_hot_commend wow fadeInUp\" data-wow-delay=\"0.3s\"><h3 class=\"widget-title\"><i class=\"fa fa-bars\"></i>优惠推荐</h3>\r\n" + 
			"<div id=\"hot\" class=\"hot_commend\">\r\n" + 
			"	<ul>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/1698.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/04/20180416031209859.jpg&w=280&h=210&a=&zc=1\" alt=\"喜讯！年轻人的第一张交行白金卡-优逸白金信用卡\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/1698.html\" rel=\"bookmark\">喜讯！年轻人的第一张交行白金卡-优逸白金信用卡</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 5,960</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;0</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/1669.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2018/04/20180406105915422.jpg&w=280&h=210&a=&zc=1\" alt=\"x丝三白之白麒麟，境外活动狂薅积分里程\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/1669.html\" rel=\"bookmark\">x丝三白之白麒麟，境外活动狂薅积分里程</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 3,954</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;5</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/603.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2017/02/20170203122937559.jpg&w=280&h=210&a=&zc=1\" alt=\"我是如何天天免费喝星巴克的？\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/603.html\" rel=\"bookmark\">我是如何天天免费喝星巴克的？</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 13,823</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;5</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/595.html\"><img src=\"https://static.zrfan.com/wp-content/themes/begin/img/random/11.jpg\" alt=\"信用卡还款优惠券、白条还款优惠券汇总\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/595.html\" rel=\"bookmark\">信用卡还款优惠券、白条还款优惠券汇总</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 11,729</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;1</i>\r\n" + 
			"			</li>\r\n" + 
			"					<li>\r\n" + 
			"				<span class=\"thumbnail\">\r\n" + 
			"					<a href=\"//www.zrfan.com/414.html\"><img src=\"//www.zrfan.com/wp-content/themes/begin/timthumb.php?src=https://www.zrfan.com/cdn/2016/12/20161226171759465.jpg&w=280&h=210&a=&zc=1\" alt=\"羊毛优惠使用指南\" /></a>				</span>\r\n" + 
			"				<span class=\"hot-title\"><a href=\"//www.zrfan.com/414.html\" rel=\"bookmark\">羊毛优惠使用指南</a></span>\r\n" + 
			"				<span class=\"views\"><i class=\"fa fa-eye\"></i> 9,449</span>				<i class=\"fa fa-thumbs-o-up\">&nbsp;4</i>\r\n" + 
			"			</li>\r\n" + 
			"					</ul>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside><aside id=\"about-4\" class=\"widget widget_about wow fadeInUp\" data-wow-delay=\"0.3s\"><h3 class=\"widget-title\"><i class=\"fa fa-bars\"></i>关于本站</h3>\r\n" + 
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
			"			<span class=\"about-cn\">浏览 3941866</span>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"</div>\r\n" + 
			"\r\n" + 
			"<div class=\"clear\"></div></aside><aside id=\"advert-4\" class=\"widget widget_advert wow fadeInUp\" data-wow-delay=\"0.3s\">\r\n" + 
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
			"<div class=\"clear\"></div></aside>	</div>\r\n" + 
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
			"							<input type=\"hidden\" name=\"redirect_to\" value=\"/category/zhinan/\" />\r\n" + 
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
			"														<input type=\"hidden\" name=\"redirect_to\" value=\"/category/zhinan/?reset=true\" />\r\n" + 
			"							<input type=\"hidden\" name=\"user-cookie\" value=\"1\" />\r\n" + 
			"						</div>\r\n" + 
			"					</form>\r\n" + 
			"				</div>\r\n" + 
			"			</div>\r\n" + 
			"		</div>\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"	</div>\r\n" + 
			"\r\n" + 
			"<ul id=\"scroll\">\r\n" + 
			"	<li class=\"log log-no\"><a class=\"log-button\" title=\"文章目录\"><i class=\"fa fa-bars\"></i></a><div class=\"log-prompt\"><div class=\"log-arrow\">文章目录</div></div></li>\r\n" + 
			"	<li><a class=\"scroll-h\" title=\"返回顶部\"><i class=\"fa fa-angle-up\"></i></a></li>\r\n" + 
			"		<li><a class=\"scroll-b\" title=\"转到底部\"><i class=\"fa fa-angle-down\"></i></a></li>\r\n" + 
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
			"<script type=\"text/javascript\">\r\n" + 
			"    document.onkeydown = chang_page;function chang_page(e) {\r\n" + 
			"        var e = e || event,\r\n" + 
			"        keycode = e.which || e.keyCode;\r\n" + 
			"        if (keycode == 37) location = '//www.zrfan.com/category/zhinan/';\r\n" + 
			"        if (keycode == 39) location = '//www.zrfan.com/category/zhinan/page/2/';\r\n" + 
			"    }\r\n" + 
			"</script>\r\n" + 
			"\r\n" + 
			"</div><!-- .site -->\r\n" + 
			"\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/superfish.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/gb2big5.js?ver=2017.02.04'></script>\r\n" + 
			"<script type='text/javascript' src='https://static.zrfan.com/wp-content/themes/begin/js/carousel.min.js?ver=2017.02.04'></script>\r\n" + 
			"<script type=\"text/javascript\">var ias=$.ias({container:\"#main\",item:\"article\",pagination:\"#nav-below\",next:\"#nav-below .nav-previous a\",});ias.extension(new IASTriggerExtension({text:'<i class=\"fa fa-chevron-circle-down\"></i>更多',offset:3,}));ias.extension(new IASSpinnerExtension());ias.extension(new IASNoneLeftExtension({text:'已是最后',}));ias.on('rendered',function(items){$(\"img\").lazyload({effect: \"fadeIn\",failure_limit: 70});})</script>\r\n" + 
			"\r\n" + 
			"</body>\r\n" + 
			"</html>";
	
}
