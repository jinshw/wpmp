package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseTask;

import java.util.List;

public interface SseTaskService {

    int delete(SseTask pojo);

    List<SseTask> findList(SseTask pojo);

    List<SseTask> findListById(SseTask pojo);

    int insert(SseTask pojo);

    PageInfo<SseTask> findListPage(SseTask sseTask);

    PageInfo<SseTask> findDistributionList(SseTask pojo);


    int updateOne(SseTask pojo);

    public int addTaskUser(List<SseTask> list);

}
