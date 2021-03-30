package com.site.mountain.dao.mysql;

import java.util.List;

import com.site.mountain.entity.SseSceneDatas;
import com.site.mountain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseSceneDatasDao {

    int delete(SseSceneDatas pojo);

    List<SseSceneDatas> findList(SseSceneDatas pojo);
    
    List<SseSceneDatas> findWebList(SseSceneDatas pojo);

    List<SseSceneDatas> findDistributionList(SseSceneDatas pojo);

    List<SseSceneDatas> findListByKeyword(SseSceneDatas pojo);

    SysUser findUserById(SseSceneDatas pojo);

    int insert(SseSceneDatas pojo);

    int insertSelective(List<SseSceneDatas> pojo);

    int updateOne(SseSceneDatas pojo);

}