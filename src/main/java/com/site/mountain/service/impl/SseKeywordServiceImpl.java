package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SseKeywordDao;
import com.site.mountain.entity.SseKeyword;
import com.site.mountain.service.SseKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SseKeywordServiceImpl implements SseKeywordService {
    @Autowired
    private SseKeywordDao sseKeywordDao;

    @Override
    public PageInfo<SseKeyword> findListPage(SseKeyword pojo) {
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        List<SseKeyword> list = sseKeywordDao.findList(pojo);
        PageInfo<SseKeyword> page = new PageInfo<SseKeyword>(list);
        return page;
    }

    @Override
    public List<SseKeyword> findList(SseKeyword pojo) {
        return sseKeywordDao.findList(pojo);
    }
}
