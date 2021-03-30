package com.site.mountain.dao.mysql;

import java.util.List;

import com.site.mountain.entity.SseTask;
import com.site.mountain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseTaskDao {

    int delete(SseTask pojo);

    List<SseTask> findList(SseTask pojo);

    List<SseTask> findDistributionList(SseTask pojo);

    List<SseTask> findListById(SseTask pojo);

    SseTask findTaskByCdId(String id);

    SysUser findUserByTaskId(SseTask sseTask);

    int insert(SseTask pojo);

    int insertSelective(List<SseTask> pojo);

    int updateOne(SseTask pojo);

}