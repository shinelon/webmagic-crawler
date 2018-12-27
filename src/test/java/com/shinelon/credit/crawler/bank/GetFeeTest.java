/**
 *GetFeeTest.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * GetFeeTest.java
 *
 * @author syq
 *
 */
public class GetFeeTest {
	
	private static final Logger logger = LoggerFactory.getLogger(GetFeeTest.class);
	
	@Test
	public void getFee() {
		Html detailHtml= new Html(html); 
		String payRuleHtml = detailHtml.xpath("//[@data-id=\"pay-rule\"]").get();
//		logger.info("payRuleHtml:{}",payRuleHtml);
		Document doc = Jsoup.parse(payRuleHtml);
		Elements select = doc.select("dd");
		for (Element element : select) {
			String lable = element.attr("data-label");
			if(StringUtils.contains(lable, "年费")) {
				logger.info("{}",lable);
				logger.info("{}",element.text());
			}
		}
		
	}
	
	
	
	private String html="<!doctype html>\r\n" + 
			"<html class=\"no-js\">\r\n" + 
			" <head>\r\n" + 
			"  <title>中银长城国际卡申请办理_中银长城国际卡额度/还款/分期/积分/年费-挖财信用卡之窗</title>\r\n" + 
			"  <meta charSet=\"utf-8\">\r\n" + 
			"  <meta http-equiv=\"x-ua-compatible\" content=\"ie=edge\">\r\n" + 
			"  <meta name=\"description\" content=\"中国银行信用卡中心发行的中银长城国际卡,挖财信用卡之窗提供中银长城国际卡申请办理服务,在线办卡,就是这么便捷。同时还提供中银长城国际卡额度、还款、分期、积分、年费等信息介绍,中银长城国际卡在线申请就到挖财信用卡之窗。\">\r\n" + 
			"  <meta name=\"keywords\" content=\"中银长城国际卡申请办理,中银长城国际卡额度,中银长城国际卡还款,中银长城国际卡分期,中银长城国际卡积分,中银长城国际卡年费\">\r\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no\">\r\n" + 
			"  <meta name=\"format-detection\" content=\"telephone=no\">\r\n" + 
			"  <link rel=\"apple-touch-icon\" href=\"/apple-touch-icon.png\">\r\n" + 
			"  <link rel=\"shortcut icon\" href=\"/favicon.ico\">\r\n" + 
			"  <style id=\"base-style\">*,:after,:before{box-sizing:border-box;-webkit-tap-highlight-color:rgba(0,0,0,0)}html{background:#f6f7f9}body{margin:0}button,html,input,select,textarea{font-family:Microsoft Yahei,arial,tahoma,\\\\5B8B\\4F53,sans-serif}table{border-collapse:collapse;border-spacing:0}h1,h2,h3,h4,h5,h6{margin-top:0;margin-bottom:0;font-weight:400}ol,ul{list-style:none;padding-left:0}dl,ol,p,ul{margin-top:0;margin-bottom:0;-webkit-margin-before:0;-webkit-margin-after:0}dd{margin-left:0}input{margin:0}button,input,textarea{padding:0}a{text-decoration:none}img{border:0;vertical-align:top}</style>\r\n" + 
			"  <style id=\"css\">._3B5A7{background-color:#f1f2f2!important}._3B5A7 [role=main] ._3evq1{margin:-30px -30px 0}._3B5A7 [role=main] ._3evq1 [role=wac-anchor-scroll-body]{padding-top:20px!important}._3B5A7 [role=main] ._3evq1 .-DgEM{float:right;padding-right:60px;padding-top:9px}._3B5A7 [role=main] ._3evq1 [role=wac-anchor-scroll-body]{padding:40px 30px}._3B5A7 [role=main] ._3evq1 [role=wac-anchor-scroll-header-container-fixed] [role=wac-anchor-scroll-header]{width:1180px}._3B5A7 [role=main] ._3OHns{display:inline-block;background:#ff4a5c;color:#fff;height:34px;line-height:34px;width:121px;text-align:center;font-size:16px;border-radius:5px}._3B5A7 [role=main] ._3OHns:hover{background:#ff172e}._3B5A7 [role=main] .je4xG{margin-top:25px}._3B5A7 [role=main] .je4xG:first-child{margin-top:0}._3B5A7 [role=main] .je4xG>p{font-size:18px;line-height:25px;color:#1e1e1e;margin-bottom:16px;padding-top:20px}._3B5A7 [role=main] .je4xG>dl{border:1px solid #e5e5e5;padding:10px 30px 10px 50px;border-radius:2px}._3B5A7 [role=main] .je4xG>dl>dd{font-size:14px;color:#333;line-height:32px;position:relative}._3B5A7 [role=main] .je4xG>dl>dd[data-label]:before{content:attr(data-label) \": \";color:#666}._3B5A7 [role=main] .je4xG>dl>dd:after{content:\"\";position:absolute;width:0;height:0;border:3px solid;border-radius:100%;left:-20px;top:13px}._3B5A7 [role=aside]{padding:0!important;background:transparent!important}._3B5A7 [role=aside] ._3Za31{margin-bottom:20px;background:#fff}._3B5A7 [role=aside] ._3Za31 [role=panel-header]{padding:10px 20px 0}._3B5A7 [role=aside] ._3Za31 [role=panel-header]>span{padding-bottom:10px;font-size:18px}._3B5A7 [role=aside] ._3Za31 [role=panel-body]{padding:24px 20px}._3B5A7 [role=aside] ._3Za31 [role=panel-body] [role=media-content]{margin-top:10px}._3B5A7 [role=aside] ._3Za31 [role=panel-body] [role=media-content] h2{font-size:14px!important}._3B5A7 [role=aside] ._3Za31 [role=panel-body] ._2Ly7M{margin-bottom:8px;max-height:56px;overflow:hidden}._3B5A7 [role=aside] ._3Za31 [role=panel-body] ._2Ly7M a{font-size:14px;line-height:28px;color:#1e1e1e}._3B5A7 [role=aside] ._3Za31 [role=panel-body] ._2Ly7M a:hover{color:#ff4a5c}html{position:relative}._2UqKe,html{min-height:100%}._2UqKe{background-color:#fff;min-width:1280px}._2ukIt{max-width:1180px;margin:20px auto 30px}._3mAOP{background-color:#fff}.XdKrw{display:block;width:100%;height:120px;border-bottom:1px solid #e5e5e5;background:#fff}.XdKrw ._3LBES{width:1180px;margin:0 auto;position:relative;height:84px}.XdKrw ._3LBES ._1iWxh{left:0;width:160px;height:46px;background:url(//s1.wacdn.com/s/node-creditcard-home/444c2ea5.png);background-size:contain;background-repeat:no-repeat}.XdKrw ._3LBES ._1iWxh,.XdKrw ._3LBES ._1pCrO{position:absolute;top:50%;-webkit-transform:translateY(-50%);-ms-transform:translateY(-50%);transform:translateY(-50%)}.XdKrw ._3LBES ._1pCrO{right:-25px}.XdKrw ._3LBES ._1pCrO ._Z2QE{font-size:18px;line-height:25px;height:25px;padding:0 25px}.XdKrw ._3LBES ._1pCrO ._Z2QE>a{text-decoration:none;color:#1e1e1e;position:relative}.XdKrw ._3LBES ._1pCrO ._Z2QE>a._3AU-M,.XdKrw ._3LBES ._1pCrO ._Z2QE>a:hover{color:#ff4a5c;transition:all .4s}.XdKrw ._3LBES ._1pCrO ._Z2QE>a._20hY1:before{content:\"HOT\";position:absolute;color:#fff;font-size:12px;width:36px;height:16.8px;line-height:16.8px;border-radius:8.4px;background:#ff4a5c;-webkit-transform:scale(.83333333);-ms-transform:scale(.83333333);transform:scale(.83333333);top:-16px;right:-24px;text-align:center}.XdKrw ._3LBES ._1pCrO ._Z2QE>a._20hY1:after{content:\"\";position:absolute;top:-3px;right:-6px;border-style:solid;border-width:6px 8px 0 1px;border-color:#ff4a5c transparent transparent}._1gT4P [role=dialog-content]{padding:30px 0}.XlgPX{width:1180px;margin:0 auto;font-size:12px;padding:8px 0 4px}.XlgPX ._34NQZ{border-left:1px solid #e5d5e5;padding:0 20px;height:24px;line-height:24px;position:relative}.XlgPX ._34NQZ:hover{color:#ff4a5c;cursor:pointer}.XlgPX ._34NQZ:hover ._3_QbV{visibility:visible;opacity:1;-webkit-transform:translateY(0);-ms-transform:translateY(0);transform:translateY(0)}.XlgPX ._34NQZ._2TXKX,.XlgPX ._34NQZ:first-child{border-left:0}.XlgPX ._34NQZ:first-child{padding-left:0}.XlgPX ._34NQZ._2XnhD{padding-right:0}.XlgPX ._34NQZ *{vertical-align:middle}.XlgPX ._34NQZ>a{text-decoration:none;color:#4a4a4a}.XlgPX ._34NQZ .D27H5{margin-left:5px}.XlgPX ._34NQZ ._3_QbV{visibility:hidden;opacity:0;position:absolute;left:50%;top:30px;width:146px;margin-left:-73px;box-sizing:border-box;padding:15px;box-shadow:0 0 4px rgba(0,0,0,.3);z-index:999;background:#fff;-webkit-transform:translateY(-5px);-ms-transform:translateY(-5px);transform:translateY(-5px);transition:all .4s}.XlgPX ._34NQZ ._3_QbV img{display:block;width:117px;height:117px}.XlgPX ._34NQZ ._3_QbV:before{content:\"\";position:absolute;left:50%;top:-6px;margin-left:-7px;width:10px;height:10px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-top:1px solid rgba(0,0,0,.15);border-left:1px solid rgba(0,0,0,.15);z-index:-1;background:#fff}.XlgPX ._34NQZ ._3_QbV:after{content:attr(data-desc);display:block;text-align:center;font-size:13px;color:#333;line-height:18px;margin-top:10px}._1A7nK:after{content:\" \";display:block;height:0;clear:both;visibility:hidden;font-size:0}._3MMJs{margin-left:-8px;margin-right:-8px}._3MMJs>._3Xc_W{padding-left:8px;padding-right:8px}._3p-y5>[role=wac-col]{float:left}._1A7nK [role=wac-col-right]{float:right}._1AU5x,._1kpUw,._1ql2Q,._1u5LP,._1UYZx,._1vuPt,._1zt6Q,._2Amnx,._2cAzg,._2j-v_,._2Jsh1,._2KvbV,._2ohtL,._2PDxa,._2rckO,._2SPub,._2U-l7,._2ydXZ,._2zRWg,._3_7oi,._3AoI9,._3Caax,._3dEqM,._3HJyK,._3IcB1,._3JKVX,._3mjdO,._3Nii0,._3NQ5q,._3qrgz,._3u_Tj,._3UU05,._6bBiW,._6Tn5z,._15RYL,._20tL0,._23xbl,._28Lg7,.gkwqs,.GoGH-,.IeWUy,.Iwpw5,.j-K2Y,.JGGLF,.O6Ims,.o60cI,.Oq1tJ,.XAt_w{position:relative;min-height:1px;vertical-align:top}._1kpUw,._2ohtL,._2PDxa,._2U-l7,._3AoI9,._3Caax,._3Nii0,._3UU05,._6bBiW,.O6Ims,.o60cI,.XAt_w{float:left}._6bBiW{width:100%}._2U-l7{width:91.66666667%}._3Nii0{width:83.33333333%}._2ohtL{width:75%}._2PDxa{width:66.66666667%}._3Caax{width:58.33333333%}.XAt_w{width:50%}._3UU05{width:41.66666667%}.O6Ims{width:33.33333333%}.o60cI{width:25%}._3AoI9{width:16.66666667%}._1kpUw{width:8.33333333%}@media (min-width:768px){._1AU5x,._1ql2Q,._1u5LP,._1UYZx,._1zt6Q,._2Amnx,._2Jsh1,._2SPub,._2ydXZ,._3IcB1,._3u_Tj,._6Tn5z{float:left}._1u5LP{width:100%}._1UYZx{width:91.66666667%}._2Amnx{width:83.33333333%}._2SPub{width:75%}._2Jsh1{width:66.66666667%}._6Tn5z{width:58.33333333%}._3u_Tj{width:50%}._1zt6Q{width:41.66666667%}._2ydXZ{width:33.33333333%}._1AU5x{width:25%}._3IcB1{width:16.66666667%}._1ql2Q{width:8.33333333%}}@media (min-width:992px){._1vuPt,._2cAzg,._2j-v_,._2KvbV,._2zRWg,._3_7oi,._3HJyK,._3JKVX,._15RYL,.GoGH-,.IeWUy,.j-K2Y{float:left}._3_7oi{width:100%}._2KvbV{width:91.66666667%}.IeWUy{width:83.33333333%}._15RYL{width:75%}._2zRWg{width:66.66666667%}._3JKVX{width:58.33333333%}.j-K2Y{width:50%}.GoGH-{width:41.66666667%}._2cAzg{width:33.33333333%}._3HJyK{width:25%}._1vuPt{width:16.66666667%}._2j-v_{width:8.33333333%}}@media (min-width:1200px){._2rckO,._3dEqM,._3mjdO,._3NQ5q,._3qrgz,._20tL0,._23xbl,._28Lg7,.gkwqs,.Iwpw5,.JGGLF,.Oq1tJ{float:left}._3qrgz{width:100%}._3mjdO{width:91.66666667%}._20tL0{width:83.33333333%}._28Lg7{width:75%}.JGGLF{width:66.66666667%}._2rckO{width:58.33333333%}._3NQ5q{width:50%}._23xbl{width:41.66666667%}.Iwpw5{width:33.33333333%}.Oq1tJ{width:25%}._3dEqM{width:16.66666667%}.gkwqs{width:8.33333333%}}@font-face{font-family:wacFont;src:url(//s1.wacdn.com/s/node-creditcard-home/01621cf5.eot);src:url(//s1.wacdn.com/s/node-creditcard-home/01621cf5.eot) format(\"embedded-opentype\"),url(//s1.wacdn.com/s/node-creditcard-home/060e4e9e.woff2) format(\"woff2\"),url(//s1.wacdn.com/s/node-creditcard-home/20a65e5f.woff) format(\"woff\"),url(//s1.wacdn.com/s/node-creditcard-home/a7fbf571.ttf) format(\"truetype\"),url(//s1.wacdn.com/s/node-creditcard-home/dea8a5a2.svg) format(\"svg\");font-weight:400;font-style:normal}._2OyZl{font-family:wacFont;font-weight:400;font-style:normal;-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale}._1WG2Y{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E600;\")}._1WG2Y:before{content:\"\\E600\"}._3_B9f{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E601;\")}._3_B9f:before{content:\"\\E601\"}._30MAO{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E602;\")}._30MAO:before{content:\"\\E602\"}._1n6TP{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E603;\")}._1n6TP:before{content:\"\\E603\"}._2XKxs{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E604;\")}._2XKxs:before{content:\"\\E604\"}.vkBHc{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E605;\")}.vkBHc:before{content:\"\\E605\"}._1JHaZ{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E606;\")}._1JHaZ:before{content:\"\\E606\"}._66rIn{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E607;\")}._66rIn:before{content:\"\\E607\"}._3zEmV{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E608;\")}._3zEmV:before{content:\"\\E608\"}._2imrH{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E609;\")}._2imrH:before{content:\"\\E609\"}._171fX{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E60A;\")}._171fX:before{content:\"\\E60A\"}._39fzV{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E60B;\")}._39fzV:before{content:\"\\E60B\"}.ZVUjb{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E60C;\")}.ZVUjb:before{content:\"\\E60C\"}._3Vev7{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E60D;\")}._3Vev7:before{content:\"\\E60D\"}._2YQdN{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E60E;\")}._2YQdN:before{content:\"\\E60E\"}._3fGr6{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E60F;\")}._3fGr6:before{content:\"\\E60F\"}._1pHe-{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E610;\")}._1pHe-:before{content:\"\\E610\"}.VxD84{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E611;\")}.VxD84:before{content:\"\\E611\"}.z51mr{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E612;\")}.z51mr:before{content:\"\\E612\"}._3MBEW{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E613;\")}._3MBEW:before{content:\"\\E613\"}._2aScH{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E614;\")}._2aScH:before{content:\"\\E614\"}._3XtEv{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E615;\")}._3XtEv:before{content:\"\\E615\"}._2ZsX6{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E616;\")}._2ZsX6:before{content:\"\\E616\"}._2aNWr{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E617;\")}._2aNWr:before{content:\"\\E617\"}._3fjFR{*zoom:expression(this.runtimeStyle[\"zoom\"] = \"1\",this.innerHTML += \"&#xe\\E618;\")}._3fjFR:before{content:\"\\E618\"}._3z-CG{background-image:url(\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAMAAABg3Am1AAAABGdBTUEAALGPC/xhBQAAAAFzUkdCAK7OHOkAAABFUExURUxpcdvb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb29vb27KuLzUAAAAWdFJOUwAVWj8FMU3ujsy/+iF92JyzZgvicaaPt6wpAAAB80lEQVRIx5VWWYKFIAxTZN9V5P5HHRFB6vqGL5eUlpAGuu5uDEbOjFlvKO5+GFTGOqyZvuATj2DY4QMv43mgN3xvY/xXxHX+yGb/vPRT/dE6RPppegwgEL7QL4Y8gJPPDWgT2LfZ+9CfV+D7NzyL23ZiUfFm+4E1oXTQ05V6ts2nVcGPaQeRE5UqELOs3/QmuRZPWUvAHA5mafqwrRA1+E4HuOcHCVte3gS4/Qc2IEmep1Apaq4oj2I12JediZBfSFkD0+36wjXHkp95YWk89R4oi5QlxEysAAXtpIsmIFU+xyMFj+qqHxCxdkZJqXQiwN0IoRFASlFr9OsvT766kHTHS1jl8KBi1RDljuh7QaPVNcaK4VUPiam7gjBzbWfxDs/vESHOq+70EdCkWyOGm16J6aNr5NHyrM4W5HZQ6ZX0X7OrwoD2l0ZCW9GDaiMkgQXtcsnkzLmPKIhQBkMfFEcsLzsjgIwFwnUB6RWXYFW1f+4u2TeuI6sHceBNohSmLB9WkZhqgRtgLKkayQTuFscDOblaJg5Fq9/OFQ/7LB1kb2ZILWyz7lHJ2S3cjb28wDk0vQ/HH72Cp8qz7VPOvVXnE44/Tz7cnJ+p058HuuLl+z0AWvx7PTtBC9Ag/eFiQrjIaZhEP91kun4wixTCm4c7yR+jE09Chn/mZQAAAABJRU5ErkJggg==\");background-position:50% 50%;background-repeat:no-repeat;background-size:50px;background-color:rgba(0,0,0,.05)}.MfWU_{margin-bottom:10px}.MfWU_ [role=wac-crumb-content]{color:#666}.MfWU_ ._2Xp4-{font-size:14px;color:#333;line-height:20px;transition:all .2s}.MfWU_ ._2Xp4-:hover{color:#ff4a5c;text-shadow:0 0 15px rgba(51,51,51,.3)}.vqfId{color:#333;font-size:14px;line-height:20px;height:20px}.vqfId .l8yD6{margin:0 .3em}.vqfId a{color:#333;text-decoration:none}.vqfId a:hover{color:#555}._2cWaK{width:100%;background:#fff;border:0;box-shadow:0 0 5px rgba(0,0,0,.1);transition:all .4s;margin-bottom:20px}.Td8e3{position:relative}.Td8e3:hover{box-shadow:-4px 4px 15px rgba(0,0,0,.1)}.Td8e3 ._2WxY8{position:absolute;left:0;top:0;right:0;bottom:0;z-index:1}._2cWaK ._2NI3c{padding:60px 127px 40px;position:relative}._2cWaK ._2NI3c ._1jD7W>img{display:block;width:250px;height:158px}._2cWaK ._2NI3c ._1jD7W ._2L3Z0{margin-top:15px;text-align:center;font-size:12px;color:#1e1e1e}._2cWaK ._2NI3c ._1jD7W ._2L3Z0 *{vertical-align:middle}._2cWaK ._2NI3c ._1jD7W ._2L3Z0 [role=logo]{display:inline-block;width:18px;height:18px;background-size:contain}._2cWaK ._2NI3c ._1jD7W ._2L3Z0 [role=contract],._2cWaK ._2NI3c ._1jD7W ._2L3Z0 [role=name]{margin-left:10px;line-height:17px}._2cWaK ._2NI3c ._bjly{padding:0 40px;max-width:493px}._2cWaK ._2NI3c ._bjly [role=title]{font-size:24px;line-height:33px;color:#1e1e1e;letter-spacing:.59px;height:33px;margin-bottom:8px;font-weight:600;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;word-break:normal}._2cWaK ._2NI3c ._bjly [role=enroll-num]{color:#7a7a7a;font-size:12px;line-height:17px;margin-bottom:20px}._2cWaK ._2NI3c ._bjly [role=desc-list]>dd{font-size:14px;color:#4a4a4a;line-height:30px}._2cWaK ._2NI3c ._2T9NX{position:absolute;top:50%;right:100px;-webkit-transform:translateY(-50%);-ms-transform:translateY(-50%);transform:translateY(-50%);z-index:2;text-align:center}._2cWaK ._2NI3c ._2T9NX>p{display:inline-block;padding-left:21px;background:url(//s1.wacdn.com/s/node-creditcard-home/e421abb6.png) no-repeat 0}._2cWaK ._2NI3c ._2T9NX ._1OaEj{display:block;color:#fff;background:#ff4a5c;transition:all .4s;border-radius:4px;font-size:18px;line-height:25px;padding:10px 63px;border:0!important}._2cWaK ._2NI3c ._2T9NX ._1OaEj:hover{background:#ff172e}._2cWaK ._2NI3c ._2T9NX p{font-size:14px;color:#1e1e1e;line-height:20px;margin-top:16px;text-align:center}._2cWaK ._2NI3c ._2T9NX p *{vertical-align:middle}._2cWaK ._2NI3c ._2T9NX p i{margin-right:.5em}._2cWaK ._2ZJLI{background:#f8f8f8;padding:16px 100px}._2cWaK ._1cTmO{display:inline-block;border-right:1px solid #eaeaea;text-align:center;font-size:16px;line-height:22px}._2cWaK ._1cTmO:last-child{border-right:0}._2cWaK ._1cTmO [role=title]{color:#1e1e1e}._2cWaK ._1cTmO [role=value]{color:#b8b8b8;height:22px;width:8em;margin:auto;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;word-break:normal}._2Y37D{font-size:0}._2Y37D [role=aside],._2Y37D [role=main]{display:inline-block;font-size:12px;background:#fff;padding:30px;vertical-align:top}._2Y37D [role=main]{width:820px;margin-right:20px}._2Y37D [role=aside]{width:340px}._2Y37D.TdEeQ [role=main]{width:920px}._2Y37D.TdEeQ [role=aside]{width:240px}.je1qy{font-size:0;border-bottom:1px solid #ededed;background:#fff;width:100%;margin:0 auto}.je1qy [role=wac-anchor-scroll-header-item]{display:inline-block;white-space:nowrap;word-break:normal;overflow:hidden;text-overflow:ellipsis;text-align:center;font-size:16px;line-height:22px;color:#1e1e1e;padding:14px 0;margin-bottom:-1px;border-bottom:1px solid transparent;transition:all .4s;max-width:130px;cursor:pointer}.je1qy [role=wac-anchor-scroll-header-item]:not([role=wac-anchor-scroll-header-item-sticky]).phT4A{color:#ff4a5c;border-bottom-color:#ff4a5c}.je1qy [role=wac-anchor-scroll-header-item]:not([role=wac-anchor-scroll-header-item-sticky]):hover{color:#ff4a5c}._3wjY9{width:100%;background:#fff}._1yic3._37F7T{font-size:0;background:#fff}._1yic3._37F7T ._1pf5q,._1yic3._37F7T ._3wjY9{display:inline-block;box-sizing:border-box;vertical-align:top}._1yic3._37F7T ._1pf5q{width:200px}._1yic3._37F7T ._1pf5q [role=wac-anchor-scroll-header]{border-bottom:0}._1yic3._37F7T ._1pf5q [role=wac-anchor-scroll-header-item]{border:0;display:block;max-width:unset;margin-bottom:0;padding:20px 0}._1yic3._37F7T ._1pf5q [role=wac-anchor-scroll-header-item].phT4A{color:#ff4a5c;background:#f6f6f6;opacity:.7}._1yic3._37F7T ._1pf5q [role=wac-anchor-scroll-header-item]:hover{color:#ff4a5c}._1yic3._37F7T ._3wjY9{width:calc(100% - 200px);border-left:1px solid hsla(0,0%,59%,.1);font-size:12px}._1yic3._37F7T._34X6N ._3wjY9{width:100%}._8Qd_I{border:0!important}._8Qd_I [role=panel-header]{padding:0;border-bottom:1px solid #ededed;font-size:20px;line-height:28px;color:#333}._8Qd_I [role=panel-header]>span{display:inline-block;padding-bottom:6px;border-bottom:1px solid #ff4a5c;margin-bottom:-1px}._8Qd_I [role=panel-body]{padding:30px 0}._2gYwC{border:1px solid #ececec;border-radius:2px}._3esbn{padding:20px;border-top-left-radius:2px;border-top-right-radius:2px}._3esbn h1,._3esbn h2,._3esbn h3,._3esbn h4,._3esbn h5,._3esbn h6{margin:0}._2Ii3b{padding:20px;border-top-left-radius:2px;border-top-right-radius:2px}.Cusl2{display:block;background:#fff;font-size:0}.Cusl2 ._2klcC{color:#7a7a7a;border-color:#f4f4f4;background:#f4f4f4}.Cusl2 ._2RXjs,.Cusl2 .hGTXa{font-size:14px;line-height:20px;color:#7a7a7a}.Cusl2 ._2RXjs i{margin-right:.1em}.Cusl2 ._2eZtn,.Cusl2 ._3WVaW{display:inline-block;box-sizing:border-box;vertical-align:top;overflow:hidden}.Cusl2 ._2eZtn img{width:100%;height:100%;transition:all .8s}.Cusl2 ._2eZtn img._3jIWg:hover{-webkit-transform:scale(1.1);-ms-transform:scale(1.1);transform:scale(1.1)}.Cusl2 ._3WVaW ._1QV3M{color:#333;font-weight:600;overflow:hidden}.Cusl2 ._3WVaW ._16EQg{overflow:hidden}.Cusl2 ._3WVaW._1CYRG{width:100%!important}.Cusl2 ._3QggH{float:right}.Cusl2 ._3jljD:after{content:\" \";display:block;height:0;font-size:0;clear:both;overflow:hidden;visibility:hidden}.Cusl2 ._3jljD *{vertical-align:middle}.Cusl2._3-y93 ._2eZtn{width:120px;height:76px;overflow:hidden;margin-right:20px}.Cusl2._3-y93 ._3WVaW{width:calc(100% - 140px);height:76px;padding:0}.Cusl2._3-y93 ._3WVaW ._1QV3M{font-size:14px;line-height:21px;font-weight:400;height:42px;text-overflow:ellipsis;overflow:hidden;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical;margin-bottom:16px}.Cusl2._3-y93 ._3WVaW ._16EQg{font-size:12px;color:#a0a0a0;line-height:17px}.Cusl2._2XwoS ._2eZtn{width:206px;height:130px;overflow:hidden;margin-right:30px}.Cusl2._2XwoS ._3WVaW{width:calc(100% - 236px);height:130px;padding:0}.Cusl2._2XwoS ._3WVaW ._1QV3M{font-size:18px;line-height:26px;margin-bottom:8px}.Cusl2._2XwoS ._3WVaW ._16EQg{color:#7a7a7a;font-size:14px;line-height:26px;max-height:52px;margin-bottom:12px}.Cusl2._2XwoS .aOUB0{margin-left:20px}.Cusl2._3bf9Z ._2eZtn{width:640px;height:315px}.Cusl2._3bf9Z .aOUB0{margin-left:40px}.Cusl2._3bf9Z ._3to12{margin-left:5px}.Cusl2._3bf9Z ._3WVaW{padding:30px 40px;width:calc(100% - 640px);height:315px;position:relative}.Cusl2._3bf9Z ._3WVaW ._3jljD{position:absolute;left:40px;bottom:30px}.Cusl2._3bf9Z ._3WVaW ._2h7wy{margin-bottom:14px}.Cusl2._3bf9Z ._3WVaW ._1QV3M{font-size:24px;line-height:33px;max-height:66px;margin-bottom:18px;-webkit-line-clamp:2}.Cusl2._3bf9Z ._3WVaW ._1QV3M,.Cusl2._3bf9Z ._3WVaW ._16EQg{overflow:hidden;text-overflow:ellipsis;display:-webkit-box;-webkit-box-orient:vertical}.Cusl2._3bf9Z ._3WVaW ._16EQg{color:#7a7a7a;font-size:16px;line-height:28px;max-height:84px;margin-bottom:20px;-webkit-line-clamp:3}.Cusl2._1luLa{display:inline-block;width:100%}.Cusl2._1luLa ._2eZtn,.Cusl2._1luLa ._3WVaW{display:block;width:100%}.Cusl2._1luLa ._2eZtn img{min-height:120px}.Cusl2._1luLa ._3WVaW{padding:0;margin-top:18px}.Cusl2._1luLa ._3WVaW ._1QV3M{font-size:16px;line-height:22px;height:44px;color:#1e1e1e;margin-bottom:8px;font-weight:400;overflow:hidden}.Cusl2._1luLa ._3WVaW ._3jljD{height:32px;line-height:32px;overflow:hidden}.Cusl2._1luLa .aOUB0{margin-left:8px}._1cX32{display:block;width:100%;border-top:1px solid #e5e5e5;background:#fff}.WJ75o{padding:25px;width:100%}.WJ75o ._2VPby{width:1180px;margin:0 auto}.WJ75o ._2VPby ._36KkU{width:480px;border:1px solid #e5e5e5;border-top:0;border-bottom:0;font-size:16px;color:#333;padding:0 54px}.WJ75o ._2VPby ._36KkU:first-child,.WJ75o ._2VPby ._36KkU:last-child{border:0;width:350px}.WJ75o ._2VPby ._36KkU ._3oMNY{text-align:center;margin-bottom:27px}.WJ75o ._2VPby ._36KkU ._2NJWI{text-align:center}.WJ75o ._2VPby ._36KkU ._2NJWI img{display:inline-block;width:104px;height:104px;background:url(//s1.wacdn.com/s/node-creditcard-home/00ea1316.png)}.WJ75o ._2VPby ._36KkU ._2NJWI p{font-size:12px;color:#ff4a5c;line-height:17px;margin-top:8px}.WJ75o ._2VPby ._36KkU ._2L8vM{font-size:13px;line-height:23px;margin-bottom:20px}.WJ75o .TjcQk{font-size:0;margin-top:-13px}.WJ75o .TjcQk>dd{display:inline-block;width:50%;margin-top:13px}.WJ75o .TjcQk>dd ._3n7mF{display:block;font-size:14px;line-height:20px;color:#333}.WJ75o .TjcQk>dd ._3n7mF:hover{color:#ff4a5c}.WJ75o ._200B1:before{content:attr(label) \":\";margin-right:1em;font-size:14px}.WJ75o ._200B1 ._3WZA0{display:inline-block;width:30px;height:30px;border-radius:50%;margin-right:8px;text-align:center;line-height:30px;cursor:pointer;position:relative}.WJ75o ._200B1 ._3WZA0 ._1sJXJ{visibility:hidden;opacity:0;position:absolute;font-size:12px;left:50%;top:40px;width:100px;margin-left:-50px;box-sizing:border-box;padding:10px;box-shadow:0 0 4px rgba(0,0,0,.3);z-index:999;background:#fff;-webkit-transform:translateY(-5px);-ms-transform:translateY(-5px);transform:translateY(-5px);transition:all .4s}.WJ75o ._200B1 ._3WZA0 ._1sJXJ img{display:block;width:80px;height:80px}.WJ75o ._200B1 ._3WZA0 ._1sJXJ:before{content:\"\";position:absolute;left:50%;top:-6px;margin-left:-7px;width:10px;height:10px;-webkit-transform:rotate(45deg);-ms-transform:rotate(45deg);transform:rotate(45deg);border-top:1px solid rgba(0,0,0,.15);border-left:1px solid rgba(0,0,0,.15);z-index:-1;background:#fff}.WJ75o ._200B1 ._3WZA0:hover ._1sJXJ{visibility:visible;opacity:1;-webkit-transform:translateY(0);-ms-transform:translateY(0);transform:translateY(0)}.WJ75o ._200B1 ._3i19K{background:#ff4a5c}.WJ75o ._200B1 ._2NEgD{background:#62b900}.WJ75o ._200B1 ._3WZA0 i{color:#fff}._1gcZh{background:#3d3d3d;padding:20px 0 30px;text-align:center}._1gcZh ._3OFpq ._1PQqo{display:inline-block;padding:0 40px;border-right:1px solid #787878}._1gcZh ._3OFpq ._1PQqo:last-child{border-right:0}._1gcZh ._3OFpq ._1PQqo a{display:inline-block;font-size:14px;height:20px;line-height:20px;color:#7a7a7a}._1gcZh ._3OFpq ._1PQqo a:hover{color:#9c9c9c;transition:all .2s}._1gcZh ._1LM7e{margin-top:8px;font-size:14px;height:20px;line-height:20px;color:#7a7a7a}._1gcZh ._1LM7e::-moz-selection{color:#fff;background:#ccc}._1gcZh ._1LM7e::selection{color:#fff;background:#ccc}</style>\r\n" + 
			"  <script>var _hmt = _hmt || [];\r\n" + 
			"            (function() {\r\n" + 
			"              var hm = document.createElement(\"script\");\r\n" + 
			"              hm.src = \"https://hm.baidu.com/hm.js?26d22c8ea8da0cb3a88191d492cc0b22\";\r\n" + 
			"              var s = document.getElementsByTagName(\"script\")[0];\r\n" + 
			"              s.parentNode.insertBefore(hm, s);\r\n" + 
			"            })();</script>\r\n" + 
			"  <script>\r\n" + 
			"          (function bdstatic () {\r\n" + 
			"              var bp = document.createElement('script');\r\n" + 
			"              var curProtocol = window.location.protocol.split(':')[0];\r\n" + 
			"              if (curProtocol === 'https') {\r\n" + 
			"                bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';\r\n" + 
			"              }\r\n" + 
			"                else {\r\n" + 
			"                bp.src = 'https://push.zhanzhang.baidu.com/push.js';\r\n" + 
			"              }\r\n" + 
			"              var s = document.getElementsByTagName(\"script\")[0];\r\n" + 
			"              s.parentNode.insertBefore(bp, s);\r\n" + 
			"            })();</script>\r\n" + 
			" </head>\r\n" + 
			" <body>\r\n" + 
			"  <div id=\"app\">\r\n" + 
			"   <div class=\"_2UqKe _3B5A7\" data-reactroot=\"\">\r\n" + 
			"    <div class=\"XdKrw\">\r\n" + 
			"     <div class=\"_1A7nK _3p-y5 XlgPX\">\r\n" + 
			"      <div role=\"wac-col\" class=\"_34NQZ\">\r\n" + 
			"       <a href=\"javascript:;\" data-stat=\"\">开启信用生活</a>\r\n" + 
			"      </div>\r\n" + 
			"      <div role=\"wac-col\" class=\"_34NQZ\">\r\n" + 
			"       <a href=\"javascript:;\" data-stat=\"\">收藏本站</a>\r\n" + 
			"      </div>\r\n" + 
			"      <div role=\"wac-col\" class=\"_34NQZ\">\r\n" + 
			"       <a href=\"//bbs.creditcard.com.cn/forum-15-1.html\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">给卡窗一些建议</a>\r\n" + 
			"      </div>\r\n" + 
			"      <div role=\"wac-col-right\" class=\"_34NQZ _2XnhD\">\r\n" + 
			"       <i class=\"_2OyZl _3_B9f\"></i>\r\n" + 
			"       <span class=\"D27H5\">扫码贷款</span>\r\n" + 
			"       <div class=\"_3_QbV\" data-desc=\"扫二维码立即贷款\">\r\n" + 
			"        <img src=\"https://s1.wacdn.com/wis/502/122b234efe0283e4_280x280.png\" alt=\"扫码贷款\" class=\"_3z-CG\">\r\n" + 
			"       </div>\r\n" + 
			"      </div>\r\n" + 
			"      <div role=\"wac-col-right\" class=\"_34NQZ\">\r\n" + 
			"       <i class=\"_2OyZl z51mr\"></i>\r\n" + 
			"       <span class=\"D27H5\">微信</span>\r\n" + 
			"       <div class=\"_3_QbV\" data-desc=\"卡管家微信公众号\">\r\n" + 
			"        <img src=\"https://s1.wacdn.com/wis/495/16ea067da832c3a8_280x280.png\" alt=\"微信\" class=\"_3z-CG\">\r\n" + 
			"       </div>\r\n" + 
			"      </div>\r\n" + 
			"      <div role=\"wac-col-right\" class=\"_34NQZ _2TXKX\">\r\n" + 
			"       <i class=\"_2OyZl ZVUjb\"></i>\r\n" + 
			"       <span class=\"D27H5\">APP</span>\r\n" + 
			"       <div class=\"_3_QbV\" data-desc=\"挖财信用卡管家APP\">\r\n" + 
			"        <img src=\"https://s1.wacdn.com/wis/495/7036ac9900adaeaa_280x280.png\" alt=\"APP\" class=\"_3z-CG\">\r\n" + 
			"       </div>\r\n" + 
			"      </div>\r\n" + 
			"     </div>\r\n" + 
			"     <div class=\"_3LBES\">\r\n" + 
			"      <a href=\"/\" class=\"_1iWxh\"></a>\r\n" + 
			"      <div class=\"_1A7nK _3p-y5 _1pCrO\">\r\n" + 
			"       <div role=\"wac-col-right\" class=\"_Z2QE\">\r\n" + 
			"        <a href=\"//bbs.creditcard.com.cn\" class=\"_20hY1\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">论坛</a>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"wac-col-right\" class=\"_Z2QE\">\r\n" + 
			"        <a href=\"/post/\" class=\"\" data-stat=\"\">攻略</a>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"wac-col-right\" class=\"_Z2QE\">\r\n" + 
			"        <a href=\"//www.wacai.com\" class=\"\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">理财</a>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"wac-col-right\" class=\"_Z2QE\">\r\n" + 
			"        <a href=\"/creditcard/\" class=\"_3AU-M _20hY1\" data-stat=\"\">信用卡</a>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"wac-col-right\" class=\"_Z2QE\">\r\n" + 
			"        <a href=\"/\" class=\"\" data-stat=\"\">首页</a>\r\n" + 
			"       </div>\r\n" + 
			"      </div>\r\n" + 
			"     </div>\r\n" + 
			"    </div>\r\n" + 
			"    <div class=\"_2ukIt\">\r\n" + 
			"     <div class=\"vqfId MfWU_\">\r\n" + 
			"      <span><span role=\"wac-crumb-content\"><a href=\"/\" class=\"_2Xp4-\" data-stat=\"\">首页</a></span><span class=\"l8yD6\">/</span></span>\r\n" + 
			"      <span><span role=\"wac-crumb-content\"><a href=\"/creditcard/\" class=\"_2Xp4-\" data-stat=\"\">信用卡</a></span><span class=\"l8yD6\">/</span></span>\r\n" + 
			"      <span><span role=\"wac-crumb-content\">中银长城国际卡</span></span>\r\n" + 
			"     </div>\r\n" + 
			"     <div>\r\n" + 
			"      <div class=\"_2cWaK\">\r\n" + 
			"       <div class=\"_1A7nK _3p-y5 _2NI3c\">\r\n" + 
			"        <div role=\"wac-col\" class=\"_1jD7W\">\r\n" + 
			"         <img src=\"https://creditcard-imgs.wacdn.com/uploads_1e6ae56652b5e352acca3e1a31e3c260.JPG\" alt=\"中国银行\" class=\"_3z-CG\">\r\n" + 
			"         <div class=\"_2L3Z0\">\r\n" + 
			"          <i role=\"logo\" style=\"background-image:url(/uploads/center/1601121127324163.png)\"></i>\r\n" + 
			"          <span role=\"name\">中国银行</span>\r\n" + 
			"          <span role=\"contract\"></span>\r\n" + 
			"         </div>\r\n" + 
			"        </div>\r\n" + 
			"        <div role=\"wac-col\" class=\"_bjly\">\r\n" + 
			"         <h1 role=\"title\">中银长城国际卡</h1>\r\n" + 
			"         <p role=\"enroll-num\">10001\r\n" + 
			"          <!-- -->位卡友拥有此卡</p>\r\n" + 
			"         <dl role=\"desc-list\">\r\n" + 
			"          <dd>\r\n" + 
			"           ★ 刷卡购票赠最高达400万元航空保险\r\n" + 
			"          </dd>\r\n" + 
			"          <dd>\r\n" + 
			"           ★ 24小时全球紧急支援服务\r\n" + 
			"          </dd>\r\n" + 
			"          <dd>\r\n" + 
			"           ★ 每年生日当月赠送1000积分\r\n" + 
			"          </dd>\r\n" + 
			"         </dl>\r\n" + 
			"        </div>\r\n" + 
			"        <div role=\"wac-col\" class=\"_2T9NX\">\r\n" + 
			"         <a href=\"\" class=\"_1OaEj\" data-stat=\"\" rel=\"nofollow\">免费申请</a>\r\n" + 
			"         <p>中国银行\r\n" + 
			"          <!-- -->官方申请通道</p>\r\n" + 
			"        </div>\r\n" + 
			"       </div>\r\n" + 
			"       <dl class=\"_2ZJLI\">\r\n" + 
			"        <dd class=\"_1cTmO\" style=\"width:20%\">\r\n" + 
			"         <p role=\"title\">卡币种</p>\r\n" + 
			"         <p role=\"value\">美元,港币</p>\r\n" + 
			"        </dd>\r\n" + 
			"        <dd class=\"_1cTmO\" style=\"width:20%\">\r\n" + 
			"         <p role=\"title\">卡组织</p>\r\n" + 
			"         <p role=\"value\">VISA</p>\r\n" + 
			"        </dd>\r\n" + 
			"        <dd class=\"_1cTmO\" style=\"width:20%\">\r\n" + 
			"         <p role=\"title\">卡等级</p>\r\n" + 
			"         <p role=\"value\">金卡</p>\r\n" + 
			"        </dd>\r\n" + 
			"        <dd class=\"_1cTmO\" style=\"width:20%\">\r\n" + 
			"         <p role=\"title\">取现额度</p>\r\n" + 
			"         <p role=\"value\">30%</p>\r\n" + 
			"        </dd>\r\n" + 
			"        <dd class=\"_1cTmO\" style=\"width:20%\">\r\n" + 
			"         <p role=\"title\">免息期</p>\r\n" + 
			"         <p role=\"value\">20-50天</p>\r\n" + 
			"        </dd>\r\n" + 
			"       </dl>\r\n" + 
			"      </div>\r\n" + 
			"      <div class=\"_2Y37D TdEeQ\">\r\n" + 
			"       <div role=\"main\">\r\n" + 
			"        <div role=\"wac-anchor-scroll\" class=\"_1yic3 _3evq1\" style=\"padding-top:0px\">\r\n" + 
			"         <div role=\"wac-anchor-scroll-header-container\" class=\"_1pf5q\">\r\n" + 
			"          <dl role=\"wac-anchor-scroll-header\" class=\"je1qy\">\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"phT4A\" id=\"baseInfo\" style=\"width:14.285714285714285%\">\r\n" + 
			"            基本信息\r\n" + 
			"           </dd>\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"\" id=\"features\" style=\"width:14.285714285714285%\">\r\n" + 
			"            卡片权益\r\n" + 
			"           </dd>\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"\" id=\"pay-rule\" style=\"width:14.285714285714285%\">\r\n" + 
			"            卡片费用\r\n" + 
			"           </dd>\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"\" id=\"credit-earned\" style=\"width:14.285714285714285%\">\r\n" + 
			"            积分规则\r\n" + 
			"           </dd>\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"\" id=\"instalment-rule\" style=\"width:14.285714285714285%\">\r\n" + 
			"            分期规则\r\n" + 
			"           </dd>\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"\" id=\"loss-report\" style=\"width:14.285714285714285%\">\r\n" + 
			"            申办挂失\r\n" + 
			"           </dd>\r\n" + 
			"           <dd role=\"wac-anchor-scroll-header-item\" class=\"-DgEM\" style=\"width:14.285714285714285%\"></dd>\r\n" + 
			"          </dl>\r\n" + 
			"         </div>\r\n" + 
			"         <div role=\"wac-anchor-scroll-body\" class=\"_3wjY9\">\r\n" + 
			"          <div role=\"wac-anchor-scroll-body-item\" data-id=\"baseInfo\" class=\"je4xG\">\r\n" + 
			"           <p>中银长城国际卡-基本信息</p>\r\n" + 
			"           <dl>\r\n" + 
			"            <dd data-label=\"发行银行\">\r\n" + 
			"             中国银行\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"卡片种类\">\r\n" + 
			"             标准卡\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"卡片外观\">\r\n" + 
			"             常规卡\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"卡片等级\">\r\n" + 
			"             金卡\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"品牌标识\">\r\n" + 
			"             VISA\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"货币种类\">\r\n" + 
			"             美元,港币\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"卡片介质\">\r\n" + 
			"             磁条,芯片\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"交易验证方式\">\r\n" + 
			"             密码+签名,只凭签名\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"卡片特点\">\r\n" + 
			"             <p>中国银行发行的威士标识单币种长城国际信用卡。</p>\r\n" + 
			"             <p>1、刷卡购票赠最高达400万元航空保险；2、24小时全球紧急支援服务；3、每年生日当月赠送1000积分。</p>\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"最低还款比例\">\r\n" + 
			"             最低应还所欠金额的10%\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"网上支付限额\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"           </dl>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"wac-anchor-scroll-body-item\" data-id=\"features\" class=\"je4xG\">\r\n" + 
			"           <p>中银长城国际卡-卡片权益</p>\r\n" + 
			"           <dl>\r\n" + 
			"            <dd data-label=\"机场贵宾\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"增值保险\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"高尔夫球场\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"医疗服务\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"道路救援\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"海外救援\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"联名优惠\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"           </dl>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"wac-anchor-scroll-body-item\" data-id=\"pay-rule\" class=\"je4xG\">\r\n" + 
			"           <p>中银长城国际卡-卡片费用</p>\r\n" + 
			"           <dl>\r\n" + 
			"            <dd data-label=\"免年费政策\">\r\n" + 
			"             首年免年费，累计消费或取现5次（含）以上免次年年费\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"主、副卡年费\">\r\n" + 
			"             最高人民币360元/卡\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"循环信贷利率\">\r\n" + 
			"             日息0.05%\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"滞纳金费率\">\r\n" + 
			"             按照最低还款额未还部分的5%收取，最低为人民币10元\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"超限费\">\r\n" + 
			"             暂时免收\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"境内取现费用\">\r\n" + 
			"             柜台取现，本行本地免费；本行异地按照取现金额的1%，最低人民币10元/笔，最高人民币50元/笔；自助终端取现，本行本地免费；本行异地人民币10元/笔、跨行本地人民币4元/笔、跨行异地人民币12元/笔。\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"境外取现费用\">\r\n" + 
			"             柜台取现，本行本地免费；本行异地按照取现金额的1%，最低人民币10元/笔，最高人民币50元/笔；自助终端取现，境外本行或国际组织会员银行，交易金额的3%，最低3.5美元/笔；境外本行或国际组织会员银行，交易金额的3%，最低3.5美元/笔。境外银联：人民币15元/笔\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"溢缴款取现费用\">\r\n" + 
			"             领回金额的0.5%，最低人民币5元/笔\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"挂失手续费\">\r\n" + 
			"             人民币40元/卡\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"补卡工本费\">\r\n" + 
			"             暂时免收\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"境外应急补卡手续费\">\r\n" + 
			"             无此项服务\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"补寄对账单手续费\">\r\n" + 
			"             补制1年以内（含）的对账单免费；补制1年以上的对账单，最高人民币5元/月/卡\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"调阅签购单续费\">\r\n" + 
			"             暂时免收\r\n" + 
			"            </dd>\r\n" + 
			"           </dl>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"wac-anchor-scroll-body-item\" data-id=\"credit-earned\" class=\"je4xG\">\r\n" + 
			"           <p>中银长城国际卡-积分规则</p>\r\n" + 
			"           <dl>\r\n" + 
			"            <dd data-label=\"积分比率\">\r\n" + 
			"             每消费或取现1元人民币累计1分；消费1美元累计14分\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"积分有效期\">\r\n" + 
			"             普通信用卡积分以一年为累积期，累积的积分两年有效，每年6月30日将到期积分清零 \r\n" + 
			"            </dd>\r\n" + 
			"           </dl>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"wac-anchor-scroll-body-item\" data-id=\"instalment-rule\" class=\"je4xG\">\r\n" + 
			"           <p>中银长城国际卡-分期规则</p>\r\n" + 
			"           <dl>\r\n" + 
			"            <dd data-label=\"分期申请方式\">\r\n" + 
			"             电话邮购、网上商城分期在指定的购物网站分期付款购买商品；单笔分期在店面通过指定POS机以分期付款方式购买商品或服务；账单分期可通过网上银行申请。 \r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"常规分期类型\">\r\n" + 
			"             电话邮购分期,单笔交易分期,账单分期\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"特色分期业务\">\r\n" + 
			"             无\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"分期起分金额\">\r\n" + 
			"             消费分期为单笔消费金额为人民币600元或以上；账单分期为申请金额最低为人民币1,000元，最高不超过当期账单新增刷卡消费总额的90%\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"分期期数费率\">\r\n" + 
			"             3期：1.95%；6期：3.60%；9期：5.40%；12期：7.20%；18期：11.70%；24期及以上：15%\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"手续费扣除方式\">\r\n" + 
			"             在首期一次性收取\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"提前还款规定\">\r\n" + 
			"             分期手续费经收取不予退还 \r\n" + 
			"            </dd>\r\n" + 
			"           </dl>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"wac-anchor-scroll-body-item\" data-id=\"loss-report\" class=\"je4xG\">\r\n" + 
			"           <p>中银长城国际卡-申办挂失</p>\r\n" + 
			"           <dl>\r\n" + 
			"            <dd data-label=\"申请方式\">\r\n" + 
			"             网上申请、网点申请、电话预约\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"申请区域\">\r\n" + 
			"             全国\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"申请条件\">\r\n" + 
			"             凡年满18周岁，具有完全民事行为能力、有合法稳定收入的中国公民和在中国境内有居留权等条件的外国人及港澳台同胞均可在本地申请中银信用卡。\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"提交材料\">\r\n" + 
			"             1、身份证明文件：接受18-65周岁人士的主卡申请以及年满16周岁人士的附属卡申请；如您是境内居民：需提供居民身份证或临时居民身份证复印件（正反两面，有效期内），或军官证复印件；如您是外籍人士：需提供（1）有效护照（2）《外国人居留证》复印件 或 在有效护照签证页上贴附的“外国人居留许可”复印件 或《外国人永久居留证》复印件；如您是港澳台人士：需提供《港澳居民来往内地通行证》或 《港澳同胞回乡证》或《台湾居民来往大陆通行证》 复印件。2、固定居住地址证明（可选择下列各项中的任一项）户口卡（簿）复印件；水、电、气最近三期缴费单；固定电话最近三期缴费单；单位开具的集体宿舍证明；房屋租赁合同（协议）；自有房产证明；小区物业管理费缴费单据；街道开具的住址证明；最近二期的信用卡月结单或最近三个月的个人所得税完税证明；能证明其有固定居住地址的其他证明材料。3、财力证明文件（可选择下列各项中的任一项）政府机构、企业开具的最近三个月的正式工资单或收入证明（需加盖公司章或部门章）；银行代发工资的存折/账单复印件（需显示最近三个月薪水入账信息）；最近三个月的个人所得税完税证明复印件；社会保险扣缴凭证复印件；自有房产证明复印件；银行定期、活期存款单 /存折复印件；基金、国债、企业债券购买凭证复印件（需显示购买人姓名、账号和账户余额）。 \r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"挂失方式\">\r\n" + 
			"             客服电话、网上银行\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"挂失保障\">\r\n" + 
			"             只有少数指定信用卡享受挂失前失卡保护功能\r\n" + 
			"            </dd>\r\n" + 
			"            <dd data-label=\"挂失电话\">\r\n" + 
			"             拨打中国银行信用卡24小时客服电话：400-66-95566 办理卡片挂失手续\r\n" + 
			"            </dd>\r\n" + 
			"           </dl>\r\n" + 
			"          </div>\r\n" + 
			"         </div>\r\n" + 
			"        </div>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"aside\">\r\n" + 
			"        <div class=\"_2gYwC _8Qd_I _3Za31\">\r\n" + 
			"         <div role=\"panel-header\" class=\"_3esbn\">\r\n" + 
			"          <span>相关活动</span>\r\n" + 
			"         </div>\r\n" + 
			"         <div role=\"panel-body\" class=\"_2Ii3b\">\r\n" + 
			"          <div role=\"media-panel\" class=\"Cusl2 _1luLa\">\r\n" + 
			"           <div role=\"media-cover\" class=\"_2eZtn\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53450.html\" target=\"_self\"><img src=\"https://s1.wacdn.com/wis/4/7c03cced27626f00_135x85.jpg\" alt=\"新年逛DFS刷中国银行Visa卡 满额即享最高10%优惠！\" class=\"_3z-CG _3jIWg\" style=\"width:100%;height:100%;display:block\"></a>\r\n" + 
			"           </div>\r\n" + 
			"           <div role=\"media-content\" class=\"_3WVaW\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53450.html\"><h2 class=\"_1QV3M\">新年逛DFS刷中国银行Visa卡 满额即享最高10%优惠！</h2><p class=\"_16EQg\"></p></a>\r\n" + 
			"            <div class=\"_3jljD\">\r\n" + 
			"             <span class=\"\"></span>\r\n" + 
			"            </div>\r\n" + 
			"           </div>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"media-panel\" class=\"Cusl2 _1luLa\">\r\n" + 
			"           <div role=\"media-cover\" class=\"_2eZtn\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53448.html\" target=\"_self\"><img src=\"https://s1.wacdn.com/wis/4/d14ad149f6a8a82d_135x85.jpg\" alt=\"VISA新年优惠季，Agoda全球酒店预订每周一88折、平日折上9折\" class=\"_3z-CG _3jIWg\" style=\"width:100%;height:100%;display:block\"></a>\r\n" + 
			"           </div>\r\n" + 
			"           <div role=\"media-content\" class=\"_3WVaW\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53448.html\"><h2 class=\"_1QV3M\">VISA新年优惠季，Agoda全球酒店预订每周一88折、平日折上9折</h2><p class=\"_16EQg\"></p></a>\r\n" + 
			"            <div class=\"_3jljD\">\r\n" + 
			"             <span class=\"\"></span>\r\n" + 
			"            </div>\r\n" + 
			"           </div>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"media-panel\" class=\"Cusl2 _1luLa\">\r\n" + 
			"           <div role=\"media-cover\" class=\"_2eZtn\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53447.html\" target=\"_self\"><img src=\"https://s1.wacdn.com/wis/4/a35b7cb19e0b28a0_135x85.jpg\" alt=\"刷中国银行银联信用卡 满800返100\" class=\"_3z-CG _3jIWg\" style=\"width:100%;height:100%;display:block\"></a>\r\n" + 
			"           </div>\r\n" + 
			"           <div role=\"media-content\" class=\"_3WVaW\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53447.html\"><h2 class=\"_1QV3M\">刷中国银行银联信用卡 满800返100</h2><p class=\"_16EQg\"></p></a>\r\n" + 
			"            <div class=\"_3jljD\">\r\n" + 
			"             <span class=\"\"></span>\r\n" + 
			"            </div>\r\n" + 
			"           </div>\r\n" + 
			"          </div>\r\n" + 
			"          <div role=\"media-panel\" class=\"Cusl2 _1luLa\">\r\n" + 
			"           <div role=\"media-cover\" class=\"_2eZtn\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53446.html\" target=\"_self\"><img src=\"https://s1.wacdn.com/wis/4/d6eff28418a8e200_135x85.jpg\" alt=\"“中国银行首届购车节”办理汽车分期部分车型享0费率及京东红包\" class=\"_3z-CG _3jIWg\" style=\"width:100%;height:100%;display:block\"></a>\r\n" + 
			"           </div>\r\n" + 
			"           <div role=\"media-content\" class=\"_3WVaW\">\r\n" + 
			"            <a href=\"/xinyongka104/yangmaoyouhui110/53446.html\"><h2 class=\"_1QV3M\">“中国银行首届购车节”办理汽车分期部分车型享0费率及京东红包</h2><p class=\"_16EQg\"></p></a>\r\n" + 
			"            <div class=\"_3jljD\">\r\n" + 
			"             <span class=\"\"></span>\r\n" + 
			"            </div>\r\n" + 
			"           </div>\r\n" + 
			"          </div>\r\n" + 
			"         </div>\r\n" + 
			"        </div>\r\n" + 
			"        <div class=\"_2gYwC _8Qd_I _3Za31\">\r\n" + 
			"         <div role=\"panel-header\" class=\"_3esbn\">\r\n" + 
			"          <span>相关攻略</span>\r\n" + 
			"         </div>\r\n" + 
			"         <div role=\"panel-body\" class=\"_2Ii3b\">\r\n" + 
			"          <dl>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/gaoduanka112/58938.html\" data-stat=\"\">中国银行白金信用卡有什么好处?申请条件是什么？</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/53512.html\" data-stat=\"\">中国银行信用卡圆分期购车梦</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/53170.html\" data-stat=\"\">中国银行信用卡海淘最高返现18%</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/52777.html\" data-stat=\"\">双十一苏宁易购携手中国银行信用卡惊喜不断</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/52496.html\" data-stat=\"\">揭密中国银行发行国内第一张信用卡，挑战支付理念</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/tieyangkagonglue107/52465.html\" data-stat=\"\">带着中国银行信用卡淘遍全球</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/52401.html\" data-stat=\"\">中国银行信用卡黑色星期五海淘最高返现18%</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/52113.html\" data-stat=\"\">善用中国银行卡，出国留学更省心</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/51905.html\" data-stat=\"\">中国银行阿狸信用卡首刷送好礼啦</a>\r\n" + 
			"           </dd>\r\n" + 
			"           <dd class=\"_2Ly7M\">\r\n" + 
			"            <a href=\"/xinyongka104/xinyongkabaike109/51855.html\" data-stat=\"\">中国银行信用卡发卡30周年优惠大集合</a>\r\n" + 
			"           </dd>\r\n" + 
			"          </dl>\r\n" + 
			"         </div>\r\n" + 
			"        </div>\r\n" + 
			"       </div>\r\n" + 
			"      </div>\r\n" + 
			"     </div>\r\n" + 
			"    </div>\r\n" + 
			"    <div class=\"_1cX32\">\r\n" + 
			"     <div class=\"WJ75o\">\r\n" + 
			"      <div class=\"_1A7nK _3p-y5 _2VPby\">\r\n" + 
			"       <div role=\"wac-col\" class=\"_36KkU\">\r\n" + 
			"        <p class=\"_3oMNY\">友情链接</p>\r\n" + 
			"        <dl class=\"TjcQk\">\r\n" + 
			"         <dd>\r\n" + 
			"          <a href=\"http://www.wacai.com\" class=\"_3n7mF\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">挖财</a>\r\n" + 
			"         </dd>\r\n" + 
			"         <dd>\r\n" + 
			"          <a href=\"http://www.shebaogjj.com/\" class=\"_3n7mF\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">社保公积金查询网</a>\r\n" + 
			"         </dd>\r\n" + 
			"         <dd>\r\n" + 
			"          <a href=\"https://www.wacai.com/index/app.action?cmd=jimi\" class=\"_3n7mF\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">基米</a>\r\n" + 
			"         </dd>\r\n" + 
			"         <dd>\r\n" + 
			"          <a href=\"http://www.wacai.com/index/app.action?cmd=credit\" class=\"_3n7mF\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">挖财信用卡管家</a>\r\n" + 
			"         </dd>\r\n" + 
			"         <dd>\r\n" + 
			"          <a href=\"http://bbs.wacai.com/index/\" class=\"_3n7mF\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">钱堂社区</a>\r\n" + 
			"         </dd>\r\n" + 
			"         <dd>\r\n" + 
			"          <a href=\"http://www.wacai.com/index/app.action?cmd=finance\" class=\"_3n7mF\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">挖财记账理财</a>\r\n" + 
			"         </dd>\r\n" + 
			"        </dl>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"wac-col\" class=\"_36KkU\">\r\n" + 
			"        <p class=\"_3oMNY\">关于信用卡之窗</p>\r\n" + 
			"        <p class=\"_2L8vM\">信用卡之窗是国内较早建立的信用卡行业专业门户网站，专注于通过互联网为银行等金融机构提供信用卡、小额贷款、个人理财等业务及产品的网络营销服务。</p>\r\n" + 
			"        <dl class=\"_200B1\" label=\"关注我们\">\r\n" + 
			"         <dd class=\"_3WZA0 _2NEgD\">\r\n" + 
			"          <i class=\"_2OyZl _1WG2Y\"></i>\r\n" + 
			"          <div class=\"_1sJXJ\">\r\n" + 
			"           <img src=\"https://s1.wacdn.com/wis/495/16ea067da832c3a8_280x280.png\" alt=\"微信公众号\" class=\"_3z-CG\">\r\n" + 
			"          </div>\r\n" + 
			"         </dd>\r\n" + 
			"        </dl>\r\n" + 
			"       </div>\r\n" + 
			"       <div role=\"wac-col\" class=\"_36KkU\">\r\n" + 
			"        <p class=\"_3oMNY\">扫码下载 挖财信用卡APP</p>\r\n" + 
			"        <div class=\"_2NJWI\">\r\n" + 
			"         <img src=\"https://s1.wacdn.com/wis/495/7036ac9900adaeaa_280x280.png\" alt=\"挖财信用卡APP\" class=\"_3z-CG\">\r\n" + 
			"         <p>管卡就送5~100元还款金</p>\r\n" + 
			"        </div>\r\n" + 
			"       </div>\r\n" + 
			"      </div>\r\n" + 
			"     </div>\r\n" + 
			"     <div class=\"_1gcZh\">\r\n" + 
			"      <ul class=\"_3OFpq\">\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/sitemap/\" data-stat=\"\">网站导航</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/aboutus/service-term/\" data-stat=\"\">服务条款</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/aboutus/private-declear/\" data-stat=\"\">隐私声明</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/aboutus/cooperation/\" data-stat=\"\">业务合作</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/aboutus/contact-us/\" data-stat=\"\">联系我们</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/aboutus/\" data-stat=\"\">关于我们</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"//bbs.creditcard.com.cn/forum-15-1.html\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">投诉建议</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/gongju/debxhk.html\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">理财工具</a></li>\r\n" + 
			"       <li class=\"_1PQqo\"><a href=\"/wangdian/\" data-stat=\"\" target=\"_blank\" rel=\"noopener\">银行网点查询页</a></li>\r\n" + 
			"      </ul>\r\n" + 
			"      <p class=\"_1LM7e\">Copyright @ 2018 creditcard.com.cn Inc.All Rights Reserved.沪公网安备31011502003657号&nbsp;&nbsp;沪ICP备09094398号</p>\r\n" + 
			"     </div>\r\n" + 
			"    </div>\r\n" + 
			"   </div>\r\n" + 
			"  </div>\r\n" + 
			"  <script>window.App={\"state\":{\"activities\":[{\"id\":53450,\"title\":\"新年逛DFS刷中国银行Visa卡 满额即享最高10%优惠！\",\"litpic\":\"https:\\u002F\\u002Fs1.wacdn.com\\u002Fwis\\u002F4\\u002F7c03cced27626f00_135x85.jpg\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"yangmaoyouhui110\"},{\"id\":53448,\"title\":\"VISA新年优惠季，Agoda全球酒店预订每周一88折、平日折上9折\",\"litpic\":\"https:\\u002F\\u002Fs1.wacdn.com\\u002Fwis\\u002F4\\u002Fd14ad149f6a8a82d_135x85.jpg\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"yangmaoyouhui110\"},{\"id\":53447,\"title\":\"刷中国银行银联信用卡 满800返100\",\"litpic\":\"https:\\u002F\\u002Fs1.wacdn.com\\u002Fwis\\u002F4\\u002Fa35b7cb19e0b28a0_135x85.jpg\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"yangmaoyouhui110\"},{\"id\":53446,\"title\":\"“中国银行首届购车节”办理汽车分期部分车型享0费率及京东红包\",\"litpic\":\"https:\\u002F\\u002Fs1.wacdn.com\\u002Fwis\\u002F4\\u002Fd6eff28418a8e200_135x85.jpg\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"yangmaoyouhui110\"}],\"posts\":[{\"id\":58938,\"title\":\"中国银行白金信用卡有什么好处?申请条件是什么？\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"gaoduanka112\"},{\"id\":53512,\"title\":\"中国银行信用卡圆分期购车梦\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":53170,\"title\":\"中国银行信用卡海淘最高返现18%\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":52777,\"title\":\"双十一苏宁易购携手中国银行信用卡惊喜不断\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":52496,\"title\":\"揭密中国银行发行国内第一张信用卡，挑战支付理念\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":52465,\"title\":\"带着中国银行信用卡淘遍全球\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"tieyangkagonglue107\"},{\"id\":52401,\"title\":\"中国银行信用卡黑色星期五海淘最高返现18%\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":52113,\"title\":\"善用中国银行卡，出国留学更省心\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":51905,\"title\":\"中国银行阿狸信用卡首刷送好礼啦\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"},{\"id\":51855,\"title\":\"中国银行信用卡发卡30周年优惠大集合\",\"mainCategory\":\"xinyongka104\",\"subCategory\":\"xinyongkabaike109\"}],\"cardBaseInfo\":{\"id\":22343,\"title\":\"中银长城国际卡\",\"description\":[\"★ 刷卡购票赠最高达400万元航空保险\",\"★ 24小时全球紧急支援服务\",\"★ 每年生日当月赠送1000积分\"],\"coverPic\":\"https:\\u002F\\u002Fcreditcard-imgs.wacdn.com\\u002Fuploads_1e6ae56652b5e352acca3e1a31e3c260.JPG\",\"enrollNum\":10001,\"bankName\":\"中国银行\",\"bankIcon\":\"\\u002Fuploads\\u002Fcenter\\u002F1601121127324163.png\",\"bankContact\":\"\",\"applyUrl\":\"\",\"features\":[{\"title\":\"卡币种\",\"value\":\"美元,港币\"},{\"title\":\"卡组织\",\"value\":\"VISA\"},{\"title\":\"卡等级\",\"value\":\"金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}]},\"content\":[{\"id\":\"baseInfo\",\"title\":\"基本信息\",\"list\":[{\"title\":\"发行银行\",\"value\":\"中国银行\"},{\"title\":\"卡片种类\",\"value\":\"标准卡\"},{\"title\":\"卡片外观\",\"value\":\"常规卡\"},{\"title\":\"卡片等级\",\"value\":\"金卡\"},{\"title\":\"品牌标识\",\"value\":\"VISA\"},{\"title\":\"货币种类\",\"value\":\"美元,港币\"},{\"title\":\"卡片介质\",\"value\":\"磁条,芯片\"},{\"title\":\"交易验证方式\",\"value\":\"密码+签名,只凭签名\"},{\"title\":\"卡片特点\",\"value\":[\"中国银行发行的威士标识单币种长城国际信用卡。\",\"1、刷卡购票赠最高达400万元航空保险；2、24小时全球紧急支援服务；3、每年生日当月赠送1000积分。\"]},{\"title\":\"最低还款比例\",\"value\":\"最低应还所欠金额的10%\"},{\"title\":\"网上支付限额\",\"value\":\"无\"}]},{\"id\":\"features\",\"title\":\"卡片权益\",\"list\":[{\"title\":\"机场贵宾\",\"value\":\"无\"},{\"title\":\"增值保险\",\"value\":\"无\"},{\"title\":\"高尔夫球场\",\"value\":\"无\"},{\"title\":\"医疗服务\",\"value\":\"无\"},{\"title\":\"道路救援\",\"value\":\"无\"},{\"title\":\"海外救援\",\"value\":\"无\"},{\"title\":\"联名优惠\",\"value\":\"无\"}]},{\"id\":\"pay-rule\",\"title\":\"卡片费用\",\"list\":[{\"title\":\"免年费政策\",\"value\":\"首年免年费，累计消费或取现5次（含）以上免次年年费\"},{\"title\":\"主、副卡年费\",\"value\":\"最高人民币360元\\u002F卡\"},{\"title\":\"循环信贷利率\",\"value\":\"日息0.05%\"},{\"title\":\"滞纳金费率\",\"value\":\"按照最低还款额未还部分的5%收取，最低为人民币10元\"},{\"title\":\"超限费\",\"value\":\"暂时免收\"},{\"title\":\"境内取现费用\",\"value\":\"柜台取现，本行本地免费；本行异地按照取现金额的1%，最低人民币10元\\u002F笔，最高人民币50元\\u002F笔；自助终端取现，本行本地免费；本行异地人民币10元\\u002F笔、跨行本地人民币4元\\u002F笔、跨行异地人民币12元\\u002F笔。\"},{\"title\":\"境外取现费用\",\"value\":\"柜台取现，本行本地免费；本行异地按照取现金额的1%，最低人民币10元\\u002F笔，最高人民币50元\\u002F笔；自助终端取现，境外本行或国际组织会员银行，交易金额的3%，最低3.5美元\\u002F笔；境外本行或国际组织会员银行，交易金额的3%，最低3.5美元\\u002F笔。境外银联：人民币15元\\u002F笔\"},{\"title\":\"溢缴款取现费用\",\"value\":\"领回金额的0.5%，最低人民币5元\\u002F笔\"},{\"title\":\"挂失手续费\",\"value\":\"人民币40元\\u002F卡\"},{\"title\":\"补卡工本费\",\"value\":\"暂时免收\"},{\"title\":\"境外应急补卡手续费\",\"value\":\"无此项服务\"},{\"title\":\"补寄对账单手续费\",\"value\":\"补制1年以内（含）的对账单免费；补制1年以上的对账单，最高人民币5元\\u002F月\\u002F卡\"},{\"title\":\"调阅签购单续费\",\"value\":\"暂时免收\"}]},{\"id\":\"credit-earned\",\"title\":\"积分规则\",\"list\":[{\"title\":\"积分比率\",\"value\":\"每消费或取现1元人民币累计1分；消费1美元累计14分\"},{\"title\":\"积分有效期\",\"value\":\"普通信用卡积分以一年为累积期，累积的积分两年有效，每年6月30日将到期积分清零 \"}]},{\"id\":\"instalment-rule\",\"title\":\"分期规则\",\"list\":[{\"title\":\"分期申请方式\",\"value\":\"电话邮购、网上商城分期在指定的购物网站分期付款购买商品；单笔分期在店面通过指定POS机以分期付款方式购买商品或服务；账单分期可通过网上银行申请。 \"},{\"title\":\"常规分期类型\",\"value\":\"电话邮购分期,单笔交易分期,账单分期\"},{\"title\":\"特色分期业务\",\"value\":\"无\"},{\"title\":\"分期起分金额\",\"value\":\"消费分期为单笔消费金额为人民币600元或以上；账单分期为申请金额最低为人民币1,000元，最高不超过当期账单新增刷卡消费总额的90%\"},{\"title\":\"分期期数费率\",\"value\":\"3期：1.95%；6期：3.60%；9期：5.40%；12期：7.20%；18期：11.70%；24期及以上：15%\"},{\"title\":\"手续费扣除方式\",\"value\":\"在首期一次性收取\"},{\"title\":\"提前还款规定\",\"value\":\"分期手续费经收取不予退还 \"}]},{\"id\":\"loss-report\",\"title\":\"申办挂失\",\"list\":[{\"title\":\"申请方式\",\"value\":\"网上申请、网点申请、电话预约\"},{\"title\":\"申请区域\",\"value\":\"全���\"},{\"title\":\"申请条件\",\"value\":\"凡年满18周岁，具有完全民事行为能力、有合法稳定收入的中国公民和在中国境内有居留权等条件的外国人及港澳台同胞均可在本地申请中银信用卡。\"},{\"title\":\"提交材料\",\"value\":\"1、身份证明文件：接受18-65周岁人士的主卡申请以及年满16周岁人士的附属卡申请；如您是境内居民：需提供居民身份证或临时居民身份证复印件（正反两面，有效期内），或军官证复印件；如您是外籍人士：需提供（1）有效护照（2）《外国人居留证》复印件 或 在有效护照签证页上贴附的“外国人居留许可”复印件 或《外国人永久居留证》复印件；如您是港澳台人士：需提供《港澳居民来往内地通行证》或 《港澳同胞回乡证》或《台湾居民来往大陆通行证》 复印件。2、固定居住地址证明（可选择下列各项中的任一项）户口卡（簿）复印件；水、电、气最近三期缴费单；固定电话最近三期缴费单；单位开具的集体宿舍证明；房屋租赁合同（协议）；自有房产证明；小区物业管理费缴费单据；街道开具的住址证明；最近二期的信用卡月结单或最近三个月的个人所得税完税证明；能证明其有固定居住地址的其他证明材料。3、财力证明文件（可选择下列各项中的任一项）政府机构、企业开具的最近三个月的正式工资单或收入证明（需加盖公司章或部门章）；银行代发工资的存折\\u002F账单复印件（需显示最近三个月薪水入账信息）；最近三个月的个人所得税完税证明复印件；社会保险扣缴凭证复印件；自有房产证明复印件；银行定期、活期存款单 \\u002F存折复印件；基金、国债、企业债券购买凭证复印件（需显示购买人姓名、账号和账户余额）。\\r\\n\"},{\"title\":\"挂失方式\",\"value\":\"客服电话、网上银行\"},{\"title\":\"挂失保障\",\"value\":\"只有少数指定信用卡享受挂失前失卡保护功能\"},{\"title\":\"挂失电话\",\"value\":\"拨打中国银行信用卡24小时客服电话：400-66-95566 办理卡片挂失手续\"}]}]},\"appKey\":\"H0Zs7OZ5J8ydGw8JeS2O\",\"extraData\":{\"friendLinks\":[{\"id\":88,\"jump\":\"http:\\u002F\\u002Fwww.wacai.com\",\"title\":\"挖财\"},{\"id\":89,\"jump\":\"http:\\u002F\\u002Fwww.shebaogjj.com\\u002F\",\"title\":\"社保公积金查询网\"},{\"id\":90,\"jump\":\"https:\\u002F\\u002Fwww.wacai.com\\u002Findex\\u002Fapp.action?cmd=jimi\",\"title\":\"基米\"},{\"id\":91,\"jump\":\"http:\\u002F\\u002Fwww.wacai.com\\u002Findex\\u002Fapp.action?cmd=credit\",\"title\":\"挖财信用卡管家\"},{\"id\":92,\"jump\":\"http:\\u002F\\u002Fbbs.wacai.com\\u002Findex\\u002F\",\"title\":\"钱堂社区\"},{\"id\":93,\"jump\":\"http:\\u002F\\u002Fwww.wacai.com\\u002Findex\\u002Fapp.action?cmd=finance\",\"title\":\"挖财记账理财\"}]}}</script>\r\n" + 
			"  <script src=\"//s1.wacdn.com/s/node-creditcard-home/vendor.883e7474d9ac055f486f.js\"></script>\r\n" + 
			"  <script src=\"//s1.wacdn.com/s/node-creditcard-home/cardDetail.4c6d3deefdfcbfa0442a.js\"></script>\r\n" + 
			"  <script src=\"//dyn.wacdn.com/creditcard-home-node/baidumap/api.js?key=&amp;v=1.1&amp;services=true\"></script>\r\n" + 
			" </body>\r\n" + 
			"</html>";
}
