/**
 *CsvBean.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * CsvBean.java
 *
 * @author syq
 *
 */
public class CsvBean {

	private String bankId;
	private String bankCode;
	private String bankCategory;
	private String bankName;
	private String bankIcon;
	private String cardId;
	private String cardImg;
	private String cardTitle;
	private String description;
	/***
	 * @see XykzcConstant
	 */
	private String kbz;
	private String kzz;
	private String kdj;
	private String qxed;
	private String mxq;
	/***
	 * 年费
	 */
	private String fee;
	
	public String getCardTitle() {
		return cardTitle;
	}

	public void setCardTitle(String cardTitle) {
		this.cardTitle = cardTitle;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankCategory() {
		return bankCategory;
	}

	public void setBankCategory(String bankCategory) {
		this.bankCategory = bankCategory;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardImg() {
		return cardImg;
	}

	public void setCardImg(String cardImg) {
		this.cardImg = cardImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKbz() {
		return kbz;
	}

	public void setKbz(String kbz) {
		this.kbz = kbz;
	}

	public String getKzz() {
		return kzz;
	}

	public void setKzz(String kzz) {
		this.kzz = kzz;
	}

	public String getKdj() {
		return kdj;
	}

	public void setKdj(String kdj) {
		this.kdj = kdj;
	}

	public String getQxed() {
		return qxed;
	}

	public void setQxed(String qxed) {
		this.qxed = qxed;
	}

	public String getMxq() {
		return mxq;
	}

	public void setMxq(String mxq) {
		this.mxq = mxq;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
