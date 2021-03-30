package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseSceneKeyword;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseSceneKeywordDao {

    int delete(SseSceneKeyword pojo);

    List<SseSceneKeyword> findList(SseSceneKeyword pojo);

    int insert(SseSceneKeyword pojo);

    int insertSelective(List<SseSceneKeyword> pojo);

    int updateOne(SseSceneKeyword pojo);

}