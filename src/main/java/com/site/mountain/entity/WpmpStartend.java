package com.site.mountain.entity;
import java.lang.String;
import java.math.BigInteger;
import java.lang.Integer;
import java.sql.Timestamp;


public class WpmpStartend {
    private String seId;
    private BigInteger userId;
    private String bussnessname;
    private String pname;
    private Integer type;
    private Timestamp createTime;

    public String getSeId() {
        return seId;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public String getBussnessname() {
        return bussnessname;
    }
    public String getPname() {
        return pname;
    }
    public Integer getType() {
        return type;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setSeId(String seId) {
        this.seId = seId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public void setBussnessname(String bussnessname) {
        this.bussnessname = bussnessname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

}
