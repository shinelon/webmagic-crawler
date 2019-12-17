/**
 *PddLoginServiceImpl.java
 *
 * 2019年12月12日
 *
 * @author shinelon
 */
package com.shinelon.credit.crawler.selenium.pdd;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * PddLoginServiceImpl.java
 *
 * @author syq
 *
 */
public class PddLoginServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(PddLoginServiceImpl.class);

    private static final String COOKIE_STR = "[{\"domain\":\"mobile.yangkeduo.com\",\"expiry\":2440043124000,\"httpOnly\":false,\"name\":\"_nano_fp\",\"path\":\"/\",\"secure\":false,\"value\":\"Xpd8n5gxlpdxn0EYX9_T1eikvqqLjb0roujmJiRW\"},{\"domain\":\".yangkeduo.com\",\"expiry\":2145916555000,\"httpOnly\":false,\"name\":\"api_uid\",\"path\":\"/\",\"secure\":false,\"value\":\"CiU16V3x0/G6NQBFHbG/Ag==\"},{\"domain\":\"mobile.yangkeduo.com\",\"expiry\":1584769524000,\"httpOnly\":false,\"name\":\"webp\",\"path\":\"/\",\"secure\":false,\"value\":\"1\"},{\"domain\":\"mobile.yangkeduo.com\",\"expiry\":1584769524000,\"httpOnly\":false,\"name\":\"ua\",\"path\":\"/\",\"secure\":false,\"value\":\"Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F77.0.3865.120%20Safari%2F537.36\"}]";
    private static final String COOKIE_STR_FORMAT_DOMAIN = "[{\"expiry\":2440043124000,\"httpOnly\":false,\"name\":\"_nano_fp\",\"path\":\"/\",\"secure\":false,\"value\":\"Xpd8n5gxlpdxn0EYX9_T1eikvqqLjb0roujmJiRW\"},{\"expiry\":2145916555000,\"httpOnly\":false,\"name\":\"api_uid\",\"path\":\"/\",\"secure\":false,\"value\":\"CiU16V3x0/G6NQBFHbG/Ag==\"},{\"expiry\":1584769524000,\"httpOnly\":false,\"name\":\"webp\",\"path\":\"/\",\"secure\":false,\"value\":\"1\"},{\"expiry\":1584769524000,\"httpOnly\":false,\"name\":\"ua\",\"path\":\"/\",\"secure\":false,\"value\":\"Mozilla%2F5.0%20(Windows%20NT%2010.0%3B%20Win64%3B%20x64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F77.0.3865.120%20Safari%2F537.36\"}]";

    @Test
    public void testChrome() {
        System.getProperties().setProperty("webdriver.chrome.driver",
                "D:\\eclipse-workspace2018-photon\\chromedriver_win32\\77.3865.10\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-web-security");
        List<String> excludeSwitches = Stream.of("enable-automation").collect(Collectors.toList());
        chromeOptions.setExperimentalOption("excludeSwitches", excludeSwitches);
        chromeOptions
                .addArguments("user-data-dir=D:\\eclipse-workspace2018-photon\\chromedriver_win32\\77.3865.10\\data");
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        List<Cookie> cookieList = JSON.parseArray(COOKIE_STR, Cookie.class);
        webDriver.get("https://mobile.yangkeduo.com/login.html");
//        webDriver.manage().deleteAllCookies();
//        for (Cookie cookie : cookieList) {
//            webDriver.manage().addCookie(cookie);
//        }
        Set<Cookie> cookies = webDriver.manage().getCookies();
//        Set<Cookie> copyCookies = new HashSet<>(cookies);
        logger.info("cookies:{}", JSON.toJSONString(cookies));
//        webDriver.navigate().refresh();
        Actions action = new Actions(webDriver);
        while (todoScanner(webDriver, action)) {

        }
        Set<Cookie> cookies2 = webDriver.manage().getCookies();
        logger.info("cookies2:{}", JSON.toJSONString(cookies2));
        webDriver.close();
        webDriver.quit();

    }

    private void gotoLogin(WebDriver webDriver, Actions action) {
        WebElement phoneLoginDiv = webDriver.findElement(By.className("phone-login"));
        action.click(phoneLoginDiv).perform();
        action = action.release(phoneLoginDiv);
    }

    private Integer scanner() {
        System.out.println("todo:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNumeric(ipt)) {
                return Integer.valueOf(ipt);
            }
        }
        return 0;
    }

    private void sendCode(WebDriver webDriver, Actions action) {
        WebElement mobileInput = webDriver.findElement(By.id("user-mobile"));
        action.sendKeys(mobileInput, "18617816946").perform();
        action = action.release(mobileInput);
        WebElement codeButton = webDriver.findElement(By.id("code-button"));
        action.click(codeButton).perform();
        action = action.release(codeButton);
    }

    private boolean todoScanner(WebDriver webDriver, Actions action) {
        Integer scanner = scanner();
        if (scanner.intValue() == 1) {
            gotoLogin(webDriver, action);
            return true;
        }
        if (scanner.intValue() == 2) {
            sendCode(webDriver, action);
            return true;
        }
        return false;
    }

}
