/**
 *FileController.java 
 *
 * 2018年11月28日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinelon.credit.crawler.api.bean.ActivityFile;
import com.shinelon.credit.crawler.service.FileDownloadService;
import com.shinelon.credit.crawler.utils.JsonResultUtil;

/**
 * FileController.java
 *
 * @author syq
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileDownloadService fileDownloadService;

	@RequestMapping("/list")
	@ResponseBody
	public String listFile(String type) {
		List<ActivityFile> listActivityFile=Collections.emptyList();
		if(StringUtils.isBlank(type)) {
			listActivityFile = fileDownloadService.listActivityFile();
		}else {
			listActivityFile = fileDownloadService.listActivityFile(type);
		}
		return JsonResultUtil.getLayUiSuccessJson(listActivityFile,listActivityFile.size()).toString();
	}

	@RequestMapping("/del")
	@ResponseBody
	public String delFile(String fileFullPath) {
		fileDownloadService.deleteFile(fileFullPath);
		return JsonResultUtil.getSuccessJson().toString();
	}

	@RequestMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response) {
		String fullFile = request.getParameter("fileFullPath");
		String fileName = request.getParameter("fileName");
		response.setContentType("application/octet-stream; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		try (OutputStream os = response.getOutputStream();
				InputStream is = fileDownloadService.downloadFile(fullFile)) {
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) > 0) {
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

}
