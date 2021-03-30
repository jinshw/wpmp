package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseSceneScenetype;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseSceneScenetypeDao {

    int delete(SseSceneScenetype pojo);

    List<SseSceneScenetype> findList(SseSceneScenetype pojo);

    int insert(SseSceneScenetype pojo);

    int insertSelective(List<SseSceneScenetype> pojo);

    int updateOne(SseSceneScenetype pojo);

}