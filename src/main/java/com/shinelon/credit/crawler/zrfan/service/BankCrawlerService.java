package com.shinelon.credit.crawler.zrfan.service;

import java.util.List;

/***
 * 爬虫入口
 *
 * @author syq
 *
 */
public interface BankCrawlerService {
   /***
    *   银行抓取入口执行
    * @param urls
    */
    void execute(List<String> urls);
}
