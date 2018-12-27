/**
 *BankMain.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shinelon.credit.crawler.bank.bean.BankAndCardBean;
import com.shinelon.credit.crawler.bank.bean.BankBean;
import com.shinelon.credit.crawler.bank.bean.CardBean;
import com.shinelon.credit.crawler.bank.bean.CsvBean;
import com.shinelon.credit.crawler.bank.constant.XykzcConstant;
import com.shinelon.credit.crawler.bank.service.CreditCardService;
import com.shinelon.credit.crawler.bank.service.impl.CreditCardServiceImpl;

import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriter;

/**
 * BankMain.java
 *
 * @author syq
 *
 */
public class BankMain {
	
	private static final Logger logger = LoggerFactory.getLogger(BankMain.class);
	
	private CreditCardService creditCardService= new CreditCardServiceImpl();
	
	
	@Test
	public void doMain() {
		JSONArray jsonArray = JSON.parseObject(bankCodeString).getJSONArray("list");
		List<BankBean> bankList = JSON.parseArray(jsonArray.toJSONString(), BankBean.class);
		logger.info("bankList:{}",bankList.size());
		long start = System.currentTimeMillis();
		List<BankAndCardBean> cardMainInfo = creditCardService.getCardMainInfo(bankList);
		List<CsvBean> formatCsvBean = formatCsvBean(cardMainInfo);
		saveCsv(formatCsvBean);
		long end = System.currentTimeMillis();
		logger.info("times:{}",end-start);
	}
	
	@Test
	public void saveCsvTest() {
		List<BankAndCardBean> list = JSON.parseArray(bankCardJson, BankAndCardBean.class);
		List<CsvBean> formatCsvBean = formatCsvBean(list);
		saveCsv(formatCsvBean);
	}
	
	private List<CsvBean> formatCsvBean(List<BankAndCardBean> list){
		if(CollectionUtils.isEmpty(list)) {
			return Collections.emptyList();
		}
		List<CsvBean> ret = new ArrayList<>(list.size()*10);
		for (BankAndCardBean bcBean : list) {
			List<CsvBean> fromatCsvBean = fromatCsvBean(bcBean);
			ret.addAll(fromatCsvBean);
		}
		return ret;
	}
	
	private List<CsvBean> fromatCsvBean(BankAndCardBean bcBean){
		List<CardBean> cardList = bcBean.getCardList();
		BankBean bankInfo = bcBean.getBankInfo();
		List<CsvBean> ret = new ArrayList<>(cardList.size());
		for (CardBean cardBean : cardList) {
			CsvBean csvBean = new CsvBean();
			//bank info
			csvBean.setBankId(bankInfo.getId());
			csvBean.setBankIcon(bankInfo.getIcon());
			csvBean.setBankName(bankInfo.getName());
			csvBean.setBankCode(bankInfo.getCode());
			csvBean.setBankCategory(bankInfo.getCategory());
			//card info
			csvBean.setCardId(cardBean.getId());
			csvBean.setCardTitle(cardBean.getTitle());
			csvBean.setCardImg(cardBean.getCoverPic());
			csvBean.setDescription(cardBean.getDescription().stream().collect(Collectors.joining(",")));
			csvBean.setFee(cardBean.getFees().stream().collect(Collectors.joining(",")));
			//card features
			List<Map<String, String>> features = cardBean.getFeatures();
			csvBean.setKbz(getFeatures(features, XykzcConstant.KBZ));
			csvBean.setKdj(getFeatures(features, XykzcConstant.KDJ));
			csvBean.setKzz(getFeatures(features, XykzcConstant.KZZ));
			csvBean.setQxed(getFeatures(features, XykzcConstant.QXED));
			csvBean.setMxq(getFeatures(features, XykzcConstant.MXQ));
			ret.add(csvBean);
		}
		return ret;
	}
	
	private String getFeatures(List<Map<String, String>> features,String title) {
		Optional<String> findFirst = features.stream().filter(e->title.equals(e.getOrDefault("title", ""))).map(e->e.getOrDefault("value", "")).findFirst();
		return findFirst.orElse("");
	}
	
	private List<String[]> formatCsvList(List<CsvBean> list){
		List<String[]> ret =new ArrayList<>(list.size()+1);
		ret.add(new String[] { "银行ID", "银行CODE", "银行类别", "银行名称","银行图标",
				"卡ID","卡名称","卡图片","卡描述","年费","卡币种","卡组织","卡等级","取现额度","免息期" });
		ret.addAll(list.stream().map(e->new String[] {
				e.getBankId(),
				e.getBankCode(),
				e.getBankCategory(),
				e.getBankName(),
				e.getBankIcon(),
				e.getCardId(),
				e.getCardTitle(),
				e.getCardImg(),
				e.getDescription(),
				e.getFee(),
				e.getKbz(),
				e.getKzz(),
				e.getKdj(),
				e.getQxed(),
				e.getMxq()
		}).collect(Collectors.toList()));
		return ret;
	}
	
	private void saveCsv(List<CsvBean> list) {
		List<String[]> csvList = formatCsvList(list);
		CsvWriter writer = CsvUtil.getWriter("d:\\zrfan\\xykzc.csv", Charset.forName("GBK"));
		writer.write(csvList);
		writer.close();
	}
	
	private String bankCardJson="[{\"bankInfo\":{\"category\":\"国有银行\",\"code\":\"7500\",\"icon\":\"https://s1.wacdn.com/wis/481/e3f39767b9663cbd_102x102.png\",\"id\":\"boc\",\"name\":\"中国银行\"},\"cardList\":[{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://s1.wacdn.com/wis/506/f182e6dce40aabba_1014x639.jpg\",\"description\":[\"★ 多种取现途径\",\"★ 全球紧急支援服务\",\"★ 签账或取现即享1.5倍积分优惠\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币,美元\"},{\"title\":\"卡组织\",\"value\":\"VISA\"},{\"title\":\"卡等级\",\"value\":\"白金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:首年免年费，累计消费或取现5次（含）以上免次年年费\",\"主、副卡年费:最高人民币3600元/卡\"],\"id\":\"22361\",\"title\":\"中行长城国际卓隽卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://creditcard-imgs.wacdn.com/uploads_4908a7f182c293f7bf484351d1a53dc9.JPG\",\"description\":[\"★ VIP贵宾服务通道礼遇\",\"★ 高额授信额度\",\"★ 存款可获得利息\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币\"},{\"title\":\"卡组织\",\"value\":\"中国银联\"},{\"title\":\"卡等级\",\"value\":\"白金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:首年免年费，第二年开始收取年费，刷卡消费满16万积分可以兑换800元的年费，刷卡消费积累60万积分可以兑换3600元的年费\",\"主、副卡年费:最高人民币3600元/卡\"],\"id\":\"22143\",\"title\":\"中行长城环球通信用卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://s1.wacdn.com/wis/506/a43900c580420f3e_1012x637.jpg\",\"description\":[\"★ 畅游世界一卡无忧\",\"★ 航空里程、自由兑换\",\"★ 白金品质彰显尊贵\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币,美元,欧元,日元,港币\"},{\"title\":\"卡组织\",\"value\":\"中国银联,VISA\"},{\"title\":\"卡等级\",\"value\":\"白金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:2013年12月31日前办理全币种国际芯片卡终身免除年费\",\"主、副卡年费:最高人民币3600元/卡\"],\"id\":\"22093\",\"title\":\"中行全币种国际芯片信用卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://s1.wacdn.com/wis/506/35f03762a69745f1_1012x637.jpg\",\"description\":[\"★ 畅游世界一卡无忧\",\"★ 航空里程、自由兑换\",\"★ 中行服务全球畅享\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币,美元,欧元,日元,港币\"},{\"title\":\"卡组织\",\"value\":\"中国银联,VISA\"},{\"title\":\"卡等级\",\"value\":\"金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:2013年12月31日前办理全币种国际芯片卡终身免除年费\",\"主、副卡年费:最高人民币360元/卡\"],\"id\":\"22100\",\"title\":\"中行全币种国际芯片信用卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://creditcard-imgs.wacdn.com/uploads_d129cea0135f81c7c4d1fca978aa77ba.JPG\",\"description\":[\"★ 港澳台地区消费可享现金返还\",\"★ 商户常年优惠\",\"★ 白金卡专享礼遇\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币\"},{\"title\":\"卡组织\",\"value\":\"中国银联\"},{\"title\":\"卡等级\",\"value\":\"白金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:首年免年费，累计消费或取现5次（含）以上免次年年费\",\"主、副卡年费:最高人民币3600元/卡\"],\"id\":\"22106\",\"title\":\"中行长城环球通港澳台自由行信用卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://creditcard-imgs.wacdn.com/uploads_f955f5ff4553a27d6688962bb02b0b39.JPG\",\"description\":[\"★ 独一无二加印自定义中文昵称\",\"★ 消费积分即可换取礼品或航程\",\"★ 短信随身行，挂失零风险\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币\"},{\"title\":\"卡组织\",\"value\":\"中国银联\"},{\"title\":\"卡等级\",\"value\":\"普卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:首年免年费，累计消费或取现5次（含）以上免次年年费\",\"主、副卡年费:最高人民币100元/卡\"],\"id\":\"22230\",\"title\":\"中行都市信用卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://creditcard-imgs.wacdn.com/uploads_1533cb38f70b77cefb07f78b737cddb3.JPG\",\"description\":[\"★ 独一无二加印自定义中文昵称\",\"★ 消费积分即可换取礼品或航程\",\"★ 短信随身行，挂失零风险\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币\"},{\"title\":\"卡组织\",\"value\":\"中国银联\"},{\"title\":\"卡等级\",\"value\":\"普卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:首年免年费，累计消费或取现5次（含）以上免次年年费\",\"主、副卡年费:最高人民币100元/卡\"],\"id\":\"22231\",\"title\":\"中行都市信用卡\"},{\"bankIcon\":\"/uploads/center/1601121127324163.png\",\"bankName\":\"中国银行\",\"coverPic\":\"https://creditcard-imgs.wacdn.com/uploads_9ac3fc97bddd4ac06998f18b5c5723f7.JPG\",\"description\":[\"★ 专属女性保险，会员专属网站\",\"★ 特殊节日温馨祝福短信\",\"★ 尽享“积分365”回馈计划\"],\"enrollNum\":\"10001\",\"features\":[{\"title\":\"卡币种\",\"value\":\"人民币,美元\"},{\"title\":\"卡组织\",\"value\":\"中国银联,万事达\"},{\"title\":\"卡等级\",\"value\":\"钛金卡\"},{\"title\":\"取现额度\",\"value\":\"30%\"},{\"title\":\"免息期\",\"value\":\"20-50天\"}],\"fees\":[\"免年费政策:首年免年费，累计消费或取现5次（含）以上免次年年费\",\"主、副卡年费:最高人民币360元/卡\"],\"id\":\"22234\",\"title\":\"中行钛金女士信用卡\"}]}]";
	
	private String bankCodeString="{\r\n" + 
			"    \"title\": \"选银行\",\r\n" + 
			"    \"name\": \"bank\",\r\n" + 
			"    \"list\": [\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"boc\",\r\n" + 
			"            \"code\": \"7500\",\r\n" + 
			"            \"name\": \"中国银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/e3f39767b9663cbd_102x102.png\",\r\n" + 
			"            \"category\": \"国有银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"bocm\",\r\n" + 
			"            \"code\": \"6000\",\r\n" + 
			"            \"name\": \"交通银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/564561f3314e0123_102x102.png\",\r\n" + 
			"            \"category\": \"国有银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"icbc\",\r\n" + 
			"            \"code\": \"7000\",\r\n" + 
			"            \"name\": \"工商银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/cc59fc36ca1d553f_102x102.png\",\r\n" + 
			"            \"category\": \"国有银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"ccb\",\r\n" + 
			"            \"code\": \"3500\",\r\n" + 
			"            \"name\": \"建设银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/da42de7fee7406d6_102x102.png\",\r\n" + 
			"            \"category\": \"国有银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"aboc\",\r\n" + 
			"            \"code\": \"8000\",\r\n" + 
			"            \"name\": \"农业银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/68e07b3b96bf4ca2_102x102.png\",\r\n" + 
			"            \"category\": \"国有银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"psbc\",\r\n" + 
			"            \"code\": \"9500\",\r\n" + 
			"            \"name\": \"储蓄银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/d696dcc6a31fad60_102x102.png\",\r\n" + 
			"            \"category\": \"国有银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"citic\",\r\n" + 
			"            \"code\": \"1000\",\r\n" + 
			"            \"name\": \"中信银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/d1efed5328ea3591_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"pingan\",\r\n" + 
			"            \"code\": \"2500\",\r\n" + 
			"            \"name\": \"平安银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/6ee29e9d8c31953c_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"cgb\",\r\n" + 
			"            \"code\": \"500\",\r\n" + 
			"            \"name\": \"广发银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/9a943a85f192a316_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"spdb\",\r\n" + 
			"            \"code\": \"6500\",\r\n" + 
			"            \"name\": \"浦发银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/a025efa4c09fd3ff_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"hxb\",\r\n" + 
			"            \"code\": \"5500\",\r\n" + 
			"            \"name\": \"华夏银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/4dc35bf061555be8_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"czb\",\r\n" + 
			"            \"code\": \"17000\",\r\n" + 
			"            \"name\": \"浙商银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/486/c343c382f532584a_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"cmb\",\r\n" + 
			"            \"code\": \"2000\",\r\n" + 
			"            \"name\": \"招商银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/b4161c203c786b53_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"ceb\",\r\n" + 
			"            \"code\": \"4000\",\r\n" + 
			"            \"name\": \"光大银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/f1f99f7833f48c9f_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"cib\",\r\n" + 
			"            \"code\": \"1500\",\r\n" + 
			"            \"name\": \"兴业银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/b0bd1cd264e19ab0_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"cmbc\",\r\n" + 
			"            \"code\": \"5000\",\r\n" + 
			"            \"name\": \"民生银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/d869d661d1b57789_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"shanghai\",\r\n" + 
			"            \"code\": \"3000\",\r\n" + 
			"            \"name\": \"上海银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/c08b4207895e7b8f_102x102.png\",\r\n" + 
			"            \"category\": \"商业银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"scb\",\r\n" + 
			"            \"code\": \"15000\",\r\n" + 
			"            \"name\": \"渣打银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/ec465a94fe444997_102x102.png\",\r\n" + 
			"            \"category\": \"外资银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"hsbc\",\r\n" + 
			"            \"code\": \"16000\",\r\n" + 
			"            \"name\": \"汇丰银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/6446fd526029c682_102x102.png\",\r\n" + 
			"            \"category\": \"外资银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"citi\",\r\n" + 
			"            \"code\": \"10000\",\r\n" + 
			"            \"name\": \"花旗银行\",\r\n" + 
			"            \"icon\": \"https://s1.wacdn.com/wis/481/5b17233835a1ff07_102x102.png\",\r\n" + 
			"            \"category\": \"外资银行\"\r\n" + 
			"        },\r\n" + 
			"        {\r\n" + 
			"            \"id\": \"bother\",\r\n" + 
			"            \"code\": \"14000\",\r\n" + 
			"            \"name\": \"其他\",\r\n" + 
			"            \"category\": \"外资银行\"\r\n" + 
			"        }\r\n" + 
			"    ]\r\n" + 
			"}";
}
