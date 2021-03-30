package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseChart;
import com.site.mountain.entity.SseSceneDatas;
import com.site.mountain.entity.Todo;

import java.util.List;

public interface SseChartService {
    /**
     * 查询采集LineChart数据
     *
     * @param type
     * @return
     */
    List<SseChart> findCollectionChart(String type);

    List<SseChart> findPie(String type);

    PageInfo<Todo> findToDoList(Todo pojo);
}
