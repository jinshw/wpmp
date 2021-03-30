package com.site.mountain.dao.mysql;

import java.util.List;

import com.site.mountain.entity.SseKeyword;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseKeywordDao {

    int delete(SseKeyword pojo);

    List<SseKeyword> findList(SseKeyword pojo);

    List<SseKeyword> selectBySid(String sid);

    int insert(SseKeyword pojo);

    int insertSelective(List<SseKeyword> pojo);

    int updateOne(SseKeyword pojo);

}