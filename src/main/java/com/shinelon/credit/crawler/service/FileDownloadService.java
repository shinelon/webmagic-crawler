/**
 *FileDownloadService.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.shinelon.credit.crawler.api.bean.ActivityFile;

/**
 * FileDownloadService.java
 *
 * @author syq
 *
 */
public interface FileDownloadService {
	
	/***
	 *   获取文件列表
	 * @return
	 */
	List<ActivityFile> listActivityFile();
	
	/***
	 *   获取文件列表
	 * @param type
	 * @return
	 */
	List<ActivityFile> listActivityFile(String type);
	
	/***
	 * 下载文件
	 * @param fullFile
	 * @return
	 */
	InputStream downloadFile(String fullFile);
	
	
	/***
	 * 删除文件
	 * @param fullFile
	 */
	void deleteFile(String fullFile);
}
