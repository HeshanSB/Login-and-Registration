package com.example.demo.bean;

/**
 * Created By Heshan
 * Created Date 6/6/2020
 */
public class ResponseBean {
    private String responseCode;
    private String responseMsg;
    private Object content;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
