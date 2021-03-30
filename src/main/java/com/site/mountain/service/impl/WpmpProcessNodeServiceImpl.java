package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SysFilesDao;
import com.site.mountain.dao.mysql.WpmpNodeFilesDao;
import com.site.mountain.dao.mysql.WpmpProcessNodeDao;
import com.site.mountain.entity.SysFiles;
import com.site.mountain.entity.WpmpNodeFiles;
import com.site.mountain.entity.WpmpProcessNode;
import com.site.mountain.service.WpmpProcessNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WpmpProcessNodeServiceImpl implements WpmpProcessNodeService {

    @Autowired
    WpmpProcessNodeDao wpmpProcessNodeDao;
    @Autowired
    WpmpNodeFilesDao wpmpNodeFilesDao;
    @Autowired
    SysFilesDao sysFilesDao;


    @Override
    public int delete(WpmpNodeFiles wpmpNodeFiles) {
        SysFiles sysFiles = new SysFiles();
        sysFiles.setFid(wpmpNodeFiles.getFid());
        int flag = wpmpNodeFilesDao.delete(wpmpNodeFiles);
        flag = sysFilesDao.delete(sysFiles);
        return flag;
    }

    @Override
    public List<WpmpProcessNode> findList(WpmpProcessNode pojo) {
        return wpmpProcessNodeDao.findList(pojo);
    }

    @Override
    public List<WpmpProcessNode> findCurrentNodeList(WpmpProcessNode pojo) {
        //查询当前项目的最大节点数
        int maxStep = wpmpProcessNodeDao.selectMaxStep();
        pojo.setStepActive(maxStep);
        List<WpmpProcessNode> list = wpmpProcessNodeDao.findList(pojo);
        return list;
    }

    @Override
    public int insert(WpmpProcessNode pojo) {
        return wpmpProcessNodeDao.insert(pojo);
    }


}
