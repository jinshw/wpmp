package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.WpmpStartendDao;
import com.site.mountain.entity.WpmpStartend;
import com.site.mountain.service.WpmpStartEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpmpStartEndServiceImpl implements WpmpStartEndService {
    @Autowired
    WpmpStartendDao wpmpStartendDao;


    @Override
    public int delete(WpmpStartend pojo) {
        return wpmpStartendDao.delete(pojo);
    }

    @Override
    public List<WpmpStartend> findList(WpmpStartend pojo) {
        return wpmpStartendDao.findList(pojo);
    }

    @Override
    public int insert(WpmpStartend pojo) {
        return wpmpStartendDao.insert(pojo);
    }

    @Override
    public int insertSelective(List<WpmpStartend> pojo) {
        return 0;
    }

    @Override
    public int updateOne(WpmpStartend pojo) {
        return wpmpStartendDao.updateOne(pojo);
    }
}
