package com.site.mountain.entity;

import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class SseSceneType {
    private String stId;
    private String pId;
    private String stName;
    private String stNameEnglish;
    private Integer stType;
    private Integer orders;
    private Timestamp optTime;
    private BigInteger optPerson;
    // extend
    private SseSceneType parentSceneType;

    private List<SseSceneType> children = new ArrayList<>();

    public SseSceneType getParentSceneType() {
        return parentSceneType;
    }

    public void setParentSceneType(SseSceneType parentSceneType) {
        this.parentSceneType = parentSceneType;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public String getStNameEnglish() {
        return stNameEnglish;
    }

    public void setStNameEnglish(String stNameEnglish) {
        this.stNameEnglish = stNameEnglish;
    }

    public Integer getStType() {
        return stType;
    }

    public void setStType(Integer stType) {
        this.stType = stType;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }

    public BigInteger getOptPerson() {
        return optPerson;
    }

    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
    }

    public List<SseSceneType> getChildren() {
        return children;
    }

    public void setChildren(List<SseSceneType> children) {
        this.children = children;
    }
}
