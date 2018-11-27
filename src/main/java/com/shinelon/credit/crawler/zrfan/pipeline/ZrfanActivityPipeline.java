/**
 *ZrfanActivityPipeline.java 
 *
 * 2018年11月26日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.zrfan.pipeline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.shinelon.credit.crawler.zrfan.model.Activity;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.CollectorPipeline;

/**
 * ZrfanActivityPipeline.java
 *
 * @author syq
 *
 */
public class ZrfanActivityPipeline implements CollectorPipeline<Activity> {

	private List<Activity> list = Collections.synchronizedList(new ArrayList<>());;

	@Override
	public void process(ResultItems resultItems, Task task) {

		Map<String, Object> map = resultItems.getAll();
		if (map.isEmpty()) {
			return;
		}
		Object obj = resultItems.get("activity");
		if (obj != null) {
			list.add((Activity) obj);
		}
		Object objs = resultItems.get("activityListJson");
		if(objs!=null) {
			List<Activity> activityList = JSON.parseArray((String)objs, Activity.class);
			list.addAll(activityList);
		}

	}

	@Override
	public List<Activity> getCollected() {
		return list;
	}

}
