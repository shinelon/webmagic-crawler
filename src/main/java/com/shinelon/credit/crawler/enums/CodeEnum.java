package com.shinelon.credit.crawler.enums;

import com.alibaba.fastjson.JSONObject;

/***
 * 
 * CodeEnum.java
 *
 * @author syq
 *
 */
public enum CodeEnum {
    // 成功
    SUCCESS("0000", "操作成功"),

    // 失败
    FAIL("0001", "服务器开小差"),

    // 失败
    ERROR_PARMS("0002", "参数错误"),

    // 失败
    ERROR_ID("0003", "不存在的ID");
    /**
     * 消息
     */
    private String msg;
    /**
     * 编码
     */
    private String code;

    private CodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public JSONObject getJsonMsg() {
        String resultMsg = "{\"code\":\"%s\",\"msg\":\"%s\"}";
        return JSONObject.parseObject(String.format(resultMsg, code, msg));
    }

    public String getMessage() {
        String resultMsg = "{\"code\":\"%s\",\"msg\":\"%s\"}";
        return String.format(resultMsg, code, msg);
    }

    public String getMsg() {
        return msg;
    }

}
