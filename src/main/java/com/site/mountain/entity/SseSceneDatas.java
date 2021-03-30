package com.site.mountain.entity;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class SseSceneDatas {
    private String sid;
    private String scId;
    private String sceneName;
    private String sceneDescription;
    private String roadType;
    private String sceneType;
    private Integer sceneSource;
    private Integer status;
    private String previewFile;
    private Integer reviewStatus;
    private String reviewComment;
    private Timestamp updateTime;
    private BigInteger optPerson;
    private String filePath;

    // 扩展字段
    private Integer pageNum;
    private Integer pageSize;
    private SysUser sysUser;
    private SseSceneType roadTypeObj;
    private SseSceneType sceneTypeObj;
    private List<SseKeyword> keywordList = new ArrayList<SseKeyword>();
    private List<String> keywords = null;
    private List<SseSceneType> scenetypeList = new ArrayList<>();

    private Integer innum;//web端查询用：in 个数
    private Integer mynum;//web端查询用：各条场景自己的场景类型


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getInnum() {
        return innum;
    }

    public void setInnum(Integer innum) {
        this.innum = innum;
    }

    public Integer getMynum() {
        return mynum;
    }

    public void setMynum(Integer mynum) {
        this.mynum = mynum;
    }

    public List<SseSceneType> getScenetypeList() {
        return scenetypeList;
    }

    public void setScenetypeList(List<SseSceneType> scenetypeList) {
        this.scenetypeList = scenetypeList;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<SseKeyword> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<SseKeyword> keywordList) {
        this.keywordList = keywordList;
    }

    public SseSceneType getRoadTypeObj() {
        return roadTypeObj;
    }

    public void setRoadTypeObj(SseSceneType roadTypeObj) {
        this.roadTypeObj = roadTypeObj;
    }

    public SseSceneType getSceneTypeObj() {
        return sceneTypeObj;
    }

    public void setSceneTypeObj(SseSceneType sceneTypeObj) {
        this.sceneTypeObj = sceneTypeObj;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getSceneDescription() {
        return sceneDescription;
    }

    public void setSceneDescription(String sceneDescription) {
        this.sceneDescription = sceneDescription;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getSceneType() {
        return sceneType;
    }

    public void setSceneType(String sceneType) {
        this.sceneType = sceneType;
    }

    public Integer getSceneSource() {
        return sceneSource;
    }

    public void setSceneSource(Integer sceneSource) {
        this.sceneSource = sceneSource;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPreviewFile() {
        return previewFile;
    }

    public void setPreviewFile(String previewFile) {
        this.previewFile = previewFile;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public BigInteger getOptPerson() {
        return optPerson;
    }

    public void setOptPerson(BigInteger optPerson) {
        this.optPerson = optPerson;
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

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }
}
