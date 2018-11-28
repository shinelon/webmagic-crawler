/**
 *FileDownloadServiceImpl.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.shinelon.credit.crawler.api.bean.ActivityFile;
import com.shinelon.credit.crawler.service.FileDownloadService;

import cn.hutool.core.io.FileUtil;

/**
 * FileDownloadServiceImpl.java
 *
 * @author syq
 *
 */
@Service
public class FileDownloadServiceImpl implements FileDownloadService {

	private static final Logger logger = LoggerFactory.getLogger(FileDownloadServiceImpl.class);

	@Value("${activity.file.path}")
	private String filePath;

	@Value("${activity.file.suffix}")
	private String fileSuffix;
	
	@Override
	public List<ActivityFile> listActivityFile() {
		List<String> listFileNames = FileUtil.listFileNames(filePath);
		logger.info("listFileNames:{}", listFileNames);
		List<ActivityFile> ret = new ArrayList<>(listFileNames.size());
		for (String fileName : listFileNames) {
			ActivityFile activityFile = new ActivityFile();
			activityFile.setFileName(fileName);
			activityFile.setFileFullPath(filePath + fileName);
			activityFile.setDateStr(getDateStr(fileName));
			ret.add(activityFile);
		}

		List<ActivityFile> collect = ret.stream().sorted(Comparator.comparing(ActivityFile::getDateStr))
				.collect(Collectors.toList());
		Collections.reverse(collect);
		return collect;
	}
	
	@Override
	public List<ActivityFile> listActivityFile(String type){
		List<ActivityFile> list = listActivityFile();
		return list.stream().filter(e->StringUtils.indexOf(e.getFileName(), type)>-1).collect(Collectors.toList());
	}
	
	@Override
	public InputStream downloadFile(String fullFile) {
		return FileUtil.getInputStream(fullFile);
	}
	
	@Override
	public void deleteFile(String fullFile) {
		FileUtil.del(fullFile);
	}
	
	private String getDateStr(String fileName) {
		String[] split = StringUtils.split(fileName, "-");
		return split[2] + split[3] + StringUtils.remove(split[4], fileSuffix);
	}

}
