package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseCollectionDatas;

import java.util.List;

public interface SseCollectionDatasService {
    PageInfo<SseCollectionDatas> findList(SseCollectionDatas pojo);

    PageInfo<SseCollectionDatas> findDistributionList(SseCollectionDatas pojo);

    int addData(SseCollectionDatas sseCollectionDatas);

    int addCollectionDatas(SseCollectionDatas sseCollectionDatas);

    List<SseCollectionDatas> findListByObj(SseCollectionDatas pojo);

    int updateOne(SseCollectionDatas pojo);

    int delete(SseCollectionDatas pojo);

    List<SseCollectionDatas> getListNotPage(SseCollectionDatas pojo);

    int addCollectionUser(List<SseCollectionDatas> list);
}
