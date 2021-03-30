package com.site.mountain.entity;

import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;
import java.math.BigInteger;


public class SseTask {
    private String taskId;
    private String taskName;
    private String scope;
    private String element;
    private String dataUsed;
    private Integer status;
    private String remark;
    private Timestamp createTime;
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

    public String getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getScope() {
        return scope;
    }

    public String getElement() {
        return element;
    }

    public String getDataUsed() {
        return dataUsed;
    }

    public Integer getStatus() {
        return status;
    }

    public String getRemark() {
        return remark;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getOptTime() {
        return optTime;
    }

    public BigInteger getOptPerson() {
        return optPerson;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setDataUsed(String dataUsed) {
        this.dataUsed = dataUsed;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setOptTime(Timestamp optTime) {
        this.optTime = optTime;
    }

    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
    }

}
