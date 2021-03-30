package com.site.mountain.entity;
import java.lang.String;
import java.math.BigInteger;


public class SseReviewUser {
    private String ruId;
    private String sid;
    private BigInteger userId;

    public String getRuId() {
        return ruId;
    }
    public String getSid() {
        return sid;
    }
    public BigInteger getUserId() {
        return userId;
    }

    public void setRuId(String ruId) {
        this.ruId = ruId;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

}
