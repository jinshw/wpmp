package com.site.mountain.dao.mysql;

import java.util.List;

import com.site.mountain.entity.SseCollectionDatas;
import com.site.mountain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SseCollectionDatasDao {

    int delete(SseCollectionDatas pojo);

    List<SseCollectionDatas> findList(SseCollectionDatas pojo);

    List<SseCollectionDatas> findDistributionList(SseCollectionDatas pojo);

    int insert(SseCollectionDatas pojo);

    int insertSelective(List<SseCollectionDatas> pojo);

    int updateOne(SseCollectionDatas pojo);

    SysUser findUserByCdId(SseCollectionDatas pojo);

}