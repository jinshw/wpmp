package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SseChart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SseChartDao {
    List<SseChart> findCollectionChart(Integer type);

    List<SseChart> findProgressChart(Integer type);

    List<SseChart> findSceneChart(Integer type);

    List<SseChart> findAuditChart(Integer type);

    List<SseChart> findPieCollection(Integer type);

    List<SseChart> findPieScene(Integer type);
}
