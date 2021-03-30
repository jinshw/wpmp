package com.site.mountain.entity;
import java.lang.String;


public class SseTaskCollection {
    private String tcId;
    private String cdId;
    private String taskId;

    public String getTcId() {
        return tcId;
    }
    public String getCdId() {
        return cdId;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }
    public void setCdId(String cdId) {
        this.cdId = cdId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
