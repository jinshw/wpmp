package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SseCollectionDatasDao;
import com.site.mountain.dao.mysql.SseCollectionUserDao;
import com.site.mountain.dao.mysql.SseTaskCollectionDao;
import com.site.mountain.dao.mysql.SseTaskDao;
import com.site.mountain.entity.*;
import com.site.mountain.service.SseCollectionDatasService;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SseCollectionDatasServiceImpl implements SseCollectionDatasService {

    @Resource
    private SseCollectionDatasDao sseCollectionDatasDao;
    @Resource
    private SseTaskCollectionDao sseTaskCollectionDao;
    @Resource
    private SseTaskDao sseTaskDao;
    @Resource
    private SseCollectionUserDao sseCollectionUserDao;

    @Override
    public PageInfo<SseCollectionDatas> findList(SseCollectionDatas pojo) {
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        List<SseCollectionDatas> list = sseCollectionDatasDao.findList(pojo);
        SseCollectionDatas sseCollectionDatas = null;
        SseTask sseTask = null;
        SysUser sysUser = null;
        // 查询相关联的任务对象
        for (int i = 0; i < list.size(); i++) {
            sseCollectionDatas = list.get(i);
            sseTask = sseTaskDao.findTaskByCdId(sseCollectionDatas.getCdId());
            sysUser = sseCollectionDatasDao.findUserByCdId(sseCollectionDatas);
            if (sseTask == null) {
                sseTask = new SseTask();
            }
            sseCollectionDatas.setSseTask(sseTask);
            sseCollectionDatas.setSysUser(sysUser);
            list.set(i, sseCollectionDatas);
        }

        PageInfo<SseCollectionDatas> page = new PageInfo<SseCollectionDatas>(list);
        return page;
    }

    @Override
    public PageInfo<SseCollectionDatas> findDistributionList(SseCollectionDatas pojo) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        pojo.setSysUser(currentSysUser);
        int size = currentSysUser.getAuthUsers().size();
        List<SseCollectionDatas> list = null;
        //如果没有数据赋权，查询自己
        if (true) {
//        if (size == 0) {
            BigInteger tempId = currentSysUser.getUserId();
            List<BigInteger> tempList = new ArrayList();
            tempList.add(tempId);
            currentSysUser.setAuthUsers(tempList);
            pojo.setSysUser(currentSysUser);
        }
        list = sseCollectionDatasDao.findDistributionList(pojo);

        PageInfo<SseCollectionDatas> page = new PageInfo<SseCollectionDatas>(list);
        return page;
    }

    @Override
    public List<SseCollectionDatas> getListNotPage(SseCollectionDatas pojo) {
        List<SseCollectionDatas> list = sseCollectionDatasDao.findList(pojo);
        return list;
    }

    @Override
    public int addData(SseCollectionDatas sseCollectionDatas) {
        return sseCollectionDatasDao.insert(sseCollectionDatas);
    }

    @Transactional
    @Override
    public int addCollectionDatas(SseCollectionDatas sseCollectionDatas) {
        int flag = 0;
        flag = sseCollectionDatasDao.insert(sseCollectionDatas);
        SseTaskCollection sseTaskCollection = new SseTaskCollection();
        sseTaskCollection.setCdId(sseCollectionDatas.getCdId());
        sseTaskCollection.setTaskId(sseCollectionDatas.getSseTask().getTaskId());
        sseTaskCollection.setTcId(UUIDUtil.create32UUID());
        flag = sseTaskCollectionDao.insert(sseTaskCollection);
        return flag;
    }

    @Override
    public List<SseCollectionDatas> findListByObj(SseCollectionDatas pojo) {
        return sseCollectionDatasDao.findList(pojo);
    }

    @Override
    public int updateOne(SseCollectionDatas pojo) {
        return sseCollectionDatasDao.updateOne(pojo);
    }

    @Override
    public int delete(SseCollectionDatas pojo) {
        return sseCollectionDatasDao.delete(pojo);
    }


    @Transactional
    @Override
    public int addCollectionUser(List<SseCollectionDatas> list) {
        int result = 0;
        SseCollectionDatas sseCollectionDatas = null;
        SseCollectionUser sseCollectionUser = null;
        SysUser sysUser = null;
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        List<SseCollectionUser> tempList = null;
        for (int i = 0; i < list.size(); i++) {
            sseCollectionDatas = list.get(i);
            sseCollectionDatas.setDataStep(1);
            Timestamp optTime = new Timestamp(System.currentTimeMillis());
            sseCollectionDatas.setOptTime(optTime);
            sysUser = sseCollectionDatas.getSysUser();
            sseCollectionUser = new SseCollectionUser();
            sseCollectionUser.setCdId(sseCollectionDatas.getCdId());
            tempList = sseCollectionUserDao.findList(sseCollectionUser);
            if (tempList.size() == 0) {// 避免重复分发数据
                sseCollectionUser.setUserId(sysUser.getUserId());
                sseCollectionUser.setCuId(UUIDUtil.create32UUID());
                result = sseCollectionUserDao.insert(sseCollectionUser);
            }

            // 设置操作人员
            if (currentSysUser != null) {
                sseCollectionDatas.setPerson(currentSysUser.getUserId());
            }
            result = sseCollectionDatasDao.updateOne(sseCollectionDatas);
        }
        return result;
    }
}
