package com.site.mountain.dao.mysql;

import com.site.mountain.entity.SseSceneType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SseSceneTypeDao {

    int delete(SseSceneType pojo);

    List<SseSceneType> findList(SseSceneType pojo);

    List<SseSceneType> findListBySid(String sid);

    SseSceneType selectByid(String id);

    SseSceneType selectOneObj(SseSceneType sseSceneType);

    List<SseSceneType> selectByPid(SseSceneType sseSceneType);

    int insert(SseSceneType pojo);

    int insertSelective(List<SseSceneType> pojo);

    int updateOne(SseSceneType pojo);

}