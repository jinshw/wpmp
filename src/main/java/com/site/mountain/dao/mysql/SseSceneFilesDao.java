package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseSceneFiles;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseSceneFilesDao {

    int delete(SseSceneFiles pojo);

    List<SseSceneFiles> findList(SseSceneFiles pojo);

    int insert(SseSceneFiles pojo);

    int insertSelective(List<SseSceneFiles> pojo);

    int updateOne(SseSceneFiles pojo);

}