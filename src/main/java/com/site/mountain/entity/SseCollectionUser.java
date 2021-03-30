package com.site.mountain.entity;
import java.lang.String;
import java.math.BigInteger;


public class SseCollectionUser {
    private String cuId;
    private BigInteger userId;
    private String cdId;

    public String getCuId() {
        return cuId;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public String getCdId() {
        return cdId;
    }

    public void setCuId(String cuId) {
        this.cuId = cuId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

}
