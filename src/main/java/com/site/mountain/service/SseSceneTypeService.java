package com.site.mountain.service;

import com.site.mountain.entity.SseSceneType;

import java.util.Map;

public interface SseSceneTypeService {

    SseSceneType getSseSceneType(SseSceneType sseSceneType);

    SseSceneType getSseSceneTypeTreeById(SseSceneType sseSceneType);

    int insert(SseSceneType pojo);

    int updateOne(SseSceneType pojo);

    int delete(SseSceneType pojo);

    Map<String, Object> deleteObj(SseSceneType pojo);

    SseSceneType selectByid(String id);
}
