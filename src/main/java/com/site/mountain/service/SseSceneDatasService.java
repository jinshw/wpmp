package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseSceneDatas;
import com.site.mountain.entity.SseSceneKeyword;

import java.util.List;

public interface SseSceneDatasService {
    PageInfo<SseSceneDatas> findList(SseSceneDatas pojo);
    
    PageInfo<SseSceneDatas> findWebList(SseSceneDatas pojo);

    PageInfo<SseSceneDatas> findDistributionList(SseSceneDatas pojo);

    PageInfo<SseSceneDatas> findListByKeyword(SseSceneDatas pojo);

    List<SseSceneDatas> findListByObj(SseSceneDatas pojo);

    int insert(SseSceneDatas pojo);

    int updateOne(SseSceneDatas pojo);

    int updateScene(SseSceneDatas sseSceneDatas);

    int delete(SseSceneDatas pojo);

    int addSceneUser(List<SseSceneDatas> list);
}
