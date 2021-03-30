package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SseReviewUserDao;
import com.site.mountain.dao.mysql.SseSceneDatasDao;
import com.site.mountain.dao.mysql.SseSceneKeywordDao;
import com.site.mountain.dao.mysql.SseSceneScenetypeDao;
import com.site.mountain.entity.*;
import com.site.mountain.service.SseSceneDatasService;
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
import java.util.stream.Collectors;

@Service
public class SseSceneDatasServiceImpl implements SseSceneDatasService {
    @Resource
    private SseSceneDatasDao sseSceneDatasDao;
    @Resource
    private SseSceneKeywordDao sseSceneKeywordDao;
    @Resource
    private SseReviewUserDao sseReviewUserDao;
    @Resource
    private SseSceneScenetypeDao sseSceneScenetypeDao;

    @Override
    public PageInfo<SseSceneDatas> findList(SseSceneDatas pojo) {
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        List<SseSceneDatas> list = sseSceneDatasDao.findList(pojo);
//        list.stream().filter(s -> {
//            List<SseSceneType> tempList = s.getScenetypeList();
//            SseSceneType sseSceneType = null;
//            for (int i = 0; i < tempList.size(); i++) {
//                sseSceneType = tempList.get(i);
//                if sseSceneType.getStId()
//            }
//
//
//        }).collect(Collectors.toList());
        PageInfo<SseSceneDatas> page = new PageInfo<SseSceneDatas>(list);
        return page;
    }

    @Override
    public PageInfo<SseSceneDatas> findWebList(SseSceneDatas pojo) {
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        List<SseSceneDatas> list = sseSceneDatasDao.findWebList(pojo);
        List<SseSceneType> tempList = pojo.getScenetypeList();
        int size = tempList.size();
        List<SseSceneDatas> resultList = list.stream().filter(s -> {
            int innum = s.getInnum();
            int mynum = s.getMynum();
            boolean flag = false;
//            if (innum == mynum && innum >= size) {
            if ((innum == size && innum <= mynum) || size == 0) {
                flag = true;
            }
            return flag;
        }).collect(Collectors.toList());
        PageInfo<SseSceneDatas> page = new PageInfo<SseSceneDatas>(resultList);
        return page;
    }

    @Override
    public PageInfo<SseSceneDatas> findDistributionList(SseSceneDatas pojo) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        pojo.setSysUser(currentSysUser);
        int size = currentSysUser.getAuthUsers().size();
        List<SseSceneDatas> list = null;
        //如果没有数据赋权，查询自己
        BigInteger tempId = currentSysUser.getUserId();
        List<BigInteger> tempList = new ArrayList();
        tempList.add(tempId);
        currentSysUser.setAuthUsers(tempList);
        pojo.setSysUser(currentSysUser);

        list = sseSceneDatasDao.findDistributionList(pojo);
        PageInfo<SseSceneDatas> page = new PageInfo<SseSceneDatas>(list);
        return page;
    }

    @Override
    public PageInfo<SseSceneDatas> findListByKeyword(SseSceneDatas pojo) {
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        List<SseSceneDatas> list = sseSceneDatasDao.findListByKeyword(pojo);
        PageInfo<SseSceneDatas> page = new PageInfo<SseSceneDatas>(list);
        return page;
    }

    @Override
    public List<SseSceneDatas> findListByObj(SseSceneDatas pojo) {
        return sseSceneDatasDao.findList(pojo);
    }

    @Override
    public int insert(SseSceneDatas sseSceneDatas) {
        int flag = sseSceneDatasDao.insert(sseSceneDatas);

        // 处理场景类型关联表
        SseSceneScenetype deleteSseSceneScenetype = new SseSceneScenetype();
        deleteSseSceneScenetype.setStId(sseSceneDatas.getSid());
        sseSceneScenetypeDao.delete(deleteSseSceneScenetype);
        List<SseSceneType> sseSceneScenetypeList = sseSceneDatas.getScenetypeList();
        SseSceneScenetype sseSceneScenetype = null;
        SseSceneType sseSceneType = null;
        for (int i = 0; i < sseSceneScenetypeList.size(); i++) {
            sseSceneType = sseSceneScenetypeList.get(i);
            sseSceneScenetype = new SseSceneScenetype();
            sseSceneScenetype.setStId(sseSceneType.getStId());
            sseSceneScenetype.setSid(sseSceneDatas.getSid());
            sseSceneScenetype.setSsid(UUIDUtil.create32UUID());
            flag = sseSceneScenetypeDao.insert(sseSceneScenetype);
        }

        // 处理场景关键字表
        // 1.删除关系表中的sid相关数据
        SseSceneKeyword deleteSceneKeyword = new SseSceneKeyword();
        deleteSceneKeyword.setSid(sseSceneDatas.getSid());
        sseSceneKeywordDao.delete(deleteSceneKeyword);

        List<SseKeyword> sseKeywordList = sseSceneDatas.getKeywordList();
        SseSceneKeyword sseSceneKeyword = null;
        SseKeyword sseKeyword = null;
        for (int i = 0; i < sseKeywordList.size(); i++) {
            sseKeyword = sseKeywordList.get(i);
            sseSceneKeyword = new SseSceneKeyword();
            sseSceneKeyword.setSid(sseSceneDatas.getSid());
            sseSceneKeyword.setKwId(sseKeyword.getKwId());
            flag = sseSceneKeywordDao.insert(sseSceneKeyword);
        }
        return flag;
    }

    @Override
    @Transactional
    public int updateOne(SseSceneDatas sseSceneDatas) {
        int flag = sseSceneDatasDao.updateOne(sseSceneDatas);
        // 场景类别处理
        SseSceneScenetype deleteSceneScenetype = new SseSceneScenetype();
        deleteSceneScenetype.setSid(sseSceneDatas.getSid());
        sseSceneScenetypeDao.delete(deleteSceneScenetype);
        List<SseSceneType> scenetypeList = sseSceneDatas.getScenetypeList();
        SseSceneScenetype sseSceneScenetype = null;
        SseSceneType sseSceneType = null;
        for (int index = 0; index < scenetypeList.size(); index++) {
            sseSceneType = scenetypeList.get(index);
            sseSceneScenetype = new SseSceneScenetype();
            sseSceneScenetype.setSsid(UUIDUtil.create32UUID());
            sseSceneScenetype.setSid(sseSceneDatas.getSid());
            sseSceneScenetype.setStId(sseSceneType.getStId());
            flag = sseSceneScenetypeDao.insert(sseSceneScenetype);
        }

        // 处理场景关键字表
        // 1.删除关系表中的sid相关数据
        SseSceneKeyword deleteSceneKeyword = new SseSceneKeyword();
        deleteSceneKeyword.setSid(sseSceneDatas.getSid());
        flag = sseSceneKeywordDao.delete(deleteSceneKeyword);

        List<SseKeyword> sseKeywordList = sseSceneDatas.getKeywordList();
        SseSceneKeyword sseSceneKeyword = null;
        SseKeyword sseKeyword = null;
        for (int i = 0; i < sseKeywordList.size(); i++) {
            sseKeyword = sseKeywordList.get(i);
            sseSceneKeyword = new SseSceneKeyword();
            sseSceneKeyword.setSid(sseSceneDatas.getSid());
            sseSceneKeyword.setKwId(sseKeyword.getKwId());
            flag = sseSceneKeywordDao.insert(sseSceneKeyword);
        }
        if (sseKeywordList.size() == 0) {
            flag = 1;
        }
        return flag;
    }

    @Override
    public int updateScene(SseSceneDatas sseSceneDatas) {
        return sseSceneDatasDao.updateOne(sseSceneDatas);
    }

    @Override
    public int delete(SseSceneDatas pojo) {
        return sseSceneDatasDao.delete(pojo);
    }

    @Transactional
    @Override
    public int addSceneUser(List<SseSceneDatas> list) {
        int result = 0;
        SseSceneDatas sseSceneDatas = null;
        SseReviewUser sseReviewUser = null;
        SysUser sysUser = null;
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        List<SseReviewUser> tempList = null;
        for (int i = 0; i < list.size(); i++) {
            sseSceneDatas = list.get(i);
            sseSceneDatas.setReviewStatus(1);//待审核
            Timestamp optTime = new Timestamp(System.currentTimeMillis());
            sseSceneDatas.setUpdateTime(optTime);
            sysUser = sseSceneDatas.getSysUser();
            sseReviewUser = new SseReviewUser();
            sseReviewUser.setSid(sseSceneDatas.getSid());
            tempList = sseReviewUserDao.findList(sseReviewUser);
            if (tempList.size() == 0) {// 避免重复分发数据
                sseReviewUser.setUserId(sysUser.getUserId());
                sseReviewUser.setRuId(UUIDUtil.create32UUID());
                result = sseReviewUserDao.insert(sseReviewUser);
            }

            // 设置操作人员
//            if (currentSysUser != null) {
//                sseCollectionDatas.setPerson(currentSysUser.getUserId());
//            }
            result = sseSceneDatasDao.updateOne(sseSceneDatas);
        }
        return result;
    }

}
