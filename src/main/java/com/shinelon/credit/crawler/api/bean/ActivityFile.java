/**
 *ActivityFile.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.api.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ActivityFile.java
 *
 * @author syq
 *
 */
public class ActivityFile {
	
	private String fileName;
	
	private String fileFullPath;
	
	private String dateStr;
	
	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
