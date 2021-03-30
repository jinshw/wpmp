package com.site.mountain.entity;
import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;
import java.math.BigInteger;


public class SseKeyword {
    private String kwId;
    private String kwName;
    private String kwNameEnglish;
    private Integer type;
    private Timestamp optTime;
    private BigInteger optPerson;

    // 扩展字段
    private Integer pageNum;
    private Integer pageSize;
    private SysUser sysUser;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getKwId() {
        return kwId;
    }
    public String getKwName() {
        return kwName;
    }
    public String getKwNameEnglish() {
        return kwNameEnglish;
    }
    public Integer getType() {
        return type;
    }
    public Timestamp getOptTime() {
        return optTime;
    }
    public BigInteger getOptPerson() {
        return optPerson;
    }

    public void setKwId(String kwId) {
        this.kwId = kwId;
    }
    public void setKwName(String kwName) {
        this.kwName = kwName;
    }
    public void setKwNameEnglish(String kwNameEnglish) {
        this.kwNameEnglish = kwNameEnglish;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }
    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
    }

}
