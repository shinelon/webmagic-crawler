/**
 *ZrfanActivitySpider.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.spider;

import java.util.List;
import java.util.Optional;

import com.shinelon.credit.crawler.zrfan.pipeline.ZrfanActivityPipeline;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * ZrfanActivitySpider.java
 *
 * @author syq
 *
 */
public class ZrfanActivitySpider extends Spider{

	/**
	 * @param pageProcessor
	 */
	public ZrfanActivitySpider(PageProcessor pageProcessor) {
		super(pageProcessor);
	}
	
	
	public ZrfanActivityPipeline getZrfanActivityPipeline() {
		List<Pipeline> list = super.pipelines;
        Optional<ZrfanActivityPipeline> opt = list.stream().filter(e -> e instanceof ZrfanActivityPipeline)
                .map(e -> (ZrfanActivityPipeline) e).findFirst();
        return opt.orElseThrow(() -> new RuntimeException("ZrfanActivityPipeline is null"));
	}

}
