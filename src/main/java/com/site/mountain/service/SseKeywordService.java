package com.site.mountain.service;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseKeyword;

import java.util.List;

public interface SseKeywordService {
    PageInfo<SseKeyword> findListPage(SseKeyword pojo);

    List<SseKeyword> findList(SseKeyword pojo);
}
