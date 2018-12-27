/**
 *CreditCardService.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank.service;

import java.util.List;

import com.shinelon.credit.crawler.bank.bean.BankAndCardBean;
import com.shinelon.credit.crawler.bank.bean.BankBean;

/**
 * CreditCardService.java
 *
 * @author syq
 *
 */
public interface CreditCardService {
	/***
	 * getCardMainInfo
	 * @param list
	 * @return
	 */
	List<BankAndCardBean> getCardMainInfo(List<BankBean> list) ;
}
