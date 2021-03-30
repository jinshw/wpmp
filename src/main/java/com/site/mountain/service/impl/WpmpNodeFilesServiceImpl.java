package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.WpmpNodeFilesDao;
import com.site.mountain.entity.WpmpNodeFiles;
import com.site.mountain.service.WpmpNodeFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpmpNodeFilesServiceImpl implements WpmpNodeFilesService {

    @Autowired
    WpmpNodeFilesDao wpmpNodeFilesDao;

    @Override
    public int delete(WpmpNodeFiles pojo) {
        return wpmpNodeFilesDao.delete(pojo);
    }

    @Override
    public List<WpmpNodeFiles> findList(WpmpNodeFiles pojo) {
        return null;
    }

    @Override
    public int insert(WpmpNodeFiles pojo) {
        return wpmpNodeFilesDao.insert(pojo);
    }

    @Override
    public int insertSelective(List<WpmpNodeFiles> pojo) {
        return 0;
    }

    @Override
    public int updateOne(WpmpNodeFiles pojo) {
        return 0;
    }
}
