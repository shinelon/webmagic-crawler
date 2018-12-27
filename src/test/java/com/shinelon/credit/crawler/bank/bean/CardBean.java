/**
 *CardInfo.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank.bean;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * CardInfo.java
 *
 * @author syq
 *
 */
public class CardBean {
	private String id;
	private String title;
	private String coverPic;
	private String enrollNum;
	private String bankName;
	private String bankIcon;
	private List<String> description;
	private List<Map<String, String>> features;
	private List<String> fees;

	
	public List<String> getFees() {
		return fees;
	}

	public void setFees(List<String> fees) {
		this.fees = fees;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoverPic() {
		return coverPic;
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getEnrollNum() {
		return enrollNum;
	}

	public void setEnrollNum(String enrollNum) {
		this.enrollNum = enrollNum;
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

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public List<Map<String, String>> getFeatures() {
		return features;
	}

	public void setFeatures(List<Map<String, String>> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
