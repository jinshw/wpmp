package com.site.mountain.entity;

import java.lang.String;
import java.lang.Integer;
import java.math.BigInteger;
import java.sql.Timestamp;


public class SseCollectionDatas {
    private String cdId;
    private String cdName;
    private Integer dataSource;
    private Integer dataType;
    private Integer dataStep;
    private Integer dataProgress;
    private String fileUrl;
    private BigInteger person;
    private Timestamp optTime;

    // 扩展字段
    private Integer pageNum;
    private Integer pageSize;
    private SysUser sysUser;
    private SseTask sseTask;

    public SseTask getSseTask() {
        return sseTask;
    }

    public void setSseTask(SseTask sseTask) {
        this.sseTask = sseTask;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

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

    public String getCdId() {
        return cdId;
    }

    public String getCdName() {
        return cdName;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public Integer getDataType() {
        return dataType;
    }

    public Integer getDataStep() {
        return dataStep;
    }

    public Integer getDataProgress() {
        return dataProgress;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public BigInteger getPerson() {
        return person;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public void setCdId(String cdId) {
        this.cdId = cdId;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public void setDataStep(Integer dataStep) {
        this.dataStep = dataStep;
    }

    public void setDataProgress(Integer dataProgress) {
        this.dataProgress = dataProgress;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setPerson(BigInteger person) {
        this.person = person;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }

}
