package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseFiles;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseFilesDao {

    int delete(SseFiles pojo);

    List<SseFiles> findList(SseFiles pojo);

    int insert(SseFiles pojo);

    int insertSelective(List<SseFiles> pojo);

    int updateOne(SseFiles pojo);

}