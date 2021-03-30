package com.site.mountain.entity;
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;
import java.sql.Timestamp;


public class SseFiles {
    private BigInteger fid;
    private String fname;
    private Integer fclass;
    private Integer ftype;
    private String suffixName;
    private BigInteger size;
    private String path;
    private Timestamp createTime;
    private BigInteger optPerson;
    private Integer isDelete;

    public BigInteger getFid() {
        return fid;
    }
    public String getFname() {
        return fname;
    }
    public Integer getFclass() {
        return fclass;
    }
    public Integer getFtype() {
        return ftype;
    }
    public String getSuffixName() {
        return suffixName;
    }
    public BigInteger getSize() {
        return size;
    }
    public String getPath() {
        return path;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public BigInteger getOptPerson() {
        return optPerson;
    }
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setFid(BigInteger fid) {
        this.fid = fid;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setFclass(Integer fclass) {
        this.fclass = fclass;
    }
    public void setFtype(Integer ftype) {
        this.ftype = ftype;
    }
    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }
    public void setSize(BigInteger size) {
        this.size = size;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
    }
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

}
