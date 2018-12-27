/**
 *BankBean.java 
 *
 * 2018年12月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.bank.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * BankBean.java
 *
 * @author syq
 *
 */
public class BankBean {
	private String id;
	private String code;
	private String name;
	private String icon;
	private String category;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
