package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseTaskCollection;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseTaskCollectionDao {

    int delete(SseTaskCollection pojo);

    List<SseTaskCollection> findList(SseTaskCollection pojo);

    int insert(SseTaskCollection pojo);

    int insertSelective(List<SseTaskCollection> pojo);

    int updateOne(SseTaskCollection pojo);

}