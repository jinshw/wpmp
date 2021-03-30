package com.site.mountain.entity;
import java.lang.String;
import java.math.BigInteger;


public class SseTaskUser {
    private String tuId;
    private BigInteger userId;
    private String taskId;

    public String getTuId() {
        return tuId;
    }
    public BigInteger getUserId() {
        return userId;
    }
    public String getTaskId() {
        return taskId;
    }

    public void setTuId(String tuId) {
        this.tuId = tuId;
    }
    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

}
