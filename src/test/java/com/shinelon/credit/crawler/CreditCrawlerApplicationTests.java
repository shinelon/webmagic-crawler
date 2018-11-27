package com.shinelon.credit.crawler;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/***
 * 
 * CreditCrawlerApplicationTests.java
 *
 * @author syq
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCrawlerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreditCrawlerApplicationTests {

	protected static final Logger logger = LoggerFactory.getLogger(CreditCrawlerApplicationTests.class);
}
