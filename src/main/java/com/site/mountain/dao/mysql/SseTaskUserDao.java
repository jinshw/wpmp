package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseTaskUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseTaskUserDao {

    int delete(SseTaskUser pojo);

    List<SseTaskUser> findList(SseTaskUser pojo);

    int insert(SseTaskUser pojo);

    int insertSelective(List<SseTaskUser> pojo);

    int updateOne(SseTaskUser pojo);

}