package com.site.mountain.entity;
import java.lang.String;
import java.math.BigInteger;
import java.lang.Integer;
import java.sql.Timestamp;
import java.util.List;


public class WpmpProcessNode {
    private String pnId;
    private String seId;
    private BigInteger userId;
    private Integer stepActive;
    private Timestamp createTime;

    // 扩展字段
    private List<SysFiles> files;

    public String getPnId() {
        return pnId;
    }
    public String getSeId() {
        return seId;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public Integer getStepActive() {
        return stepActive;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setPnId(String pnId) {
        this.pnId = pnId;
    }
    public void setSeId(String seId) {
        this.seId = seId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public void setStepActive(Integer stepActive) {
        this.stepActive = stepActive;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public List<SysFiles> getFiles() {
        return files;
    }

    public void setFiles(List<SysFiles> files) {
        this.files = files;
    }
}
