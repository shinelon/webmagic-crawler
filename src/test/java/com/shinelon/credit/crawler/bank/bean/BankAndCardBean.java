/**
 *TotalRetBean.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank.bean;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TotalRetBean.java
 *
 * @author syq
 *
 */
public class BankAndCardBean {
	private BankBean bankInfo;
	private List<CardBean> cardList;

	public BankBean getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(BankBean bankInfo) {
		this.bankInfo = bankInfo;
	}

	public List<CardBean> getCardList() {
		return cardList;
	}

	public void setCardList(List<CardBean> cardList) {
		this.cardList = cardList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
