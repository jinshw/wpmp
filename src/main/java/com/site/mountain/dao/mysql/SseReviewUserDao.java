package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.SseReviewUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseReviewUserDao {

    int delete(SseReviewUser pojo);

    List<SseReviewUser> findList(SseReviewUser pojo);

    int insert(SseReviewUser pojo);

    int insertSelective(List<SseReviewUser> pojo);

    int updateOne(SseReviewUser pojo);

}