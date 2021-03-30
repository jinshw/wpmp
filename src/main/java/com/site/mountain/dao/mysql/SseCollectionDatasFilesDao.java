package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseCollectionDatasFiles;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseCollectionDatasFilesDao {

    int delete(SseCollectionDatasFiles pojo);

    List<SseCollectionDatasFiles> findList(SseCollectionDatasFiles pojo);

    int insert(SseCollectionDatasFiles pojo);

    int insertSelective(List<SseCollectionDatasFiles> pojo);

    int updateOne(SseCollectionDatasFiles pojo);

}