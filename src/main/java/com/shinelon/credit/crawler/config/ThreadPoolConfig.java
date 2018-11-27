package com.shinelon.credit.crawler.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 
 * ThreadPoolConfig.java
 *
 * @author syq
 *
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name = "spiderTaskExecutor")
    public ExecutorService configPool() {
        ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("spider-task-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(64, 128, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

}
