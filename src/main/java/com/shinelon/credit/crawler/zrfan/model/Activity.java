/**
 *Activity.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Activity.java
 *
 * @author syq
 *
 */
public class Activity {
	
	private String keywords;
    private String bankCode;
    private String bankName;
    private String title;
    private String content;
    private String tag;
    private String url;
     	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
    	
    public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this);
    }
	
}
