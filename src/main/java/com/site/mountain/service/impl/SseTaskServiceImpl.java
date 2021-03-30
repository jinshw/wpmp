package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SseTaskDao;
import com.site.mountain.dao.mysql.SseTaskUserDao;
import com.site.mountain.entity.SseTask;
import com.site.mountain.entity.SseTaskUser;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SseTaskService;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SseTaskServiceImpl implements SseTaskService {

    @Resource
    private SseTaskDao sseTaskDao;
    @Resource
    private SseTaskUserDao sseTaskUserDao;

    @Override
    public int delete(SseTask pojo) {
        return sseTaskDao.delete(pojo);
    }

    @Override
    public List<SseTask> findList(SseTask pojo) {
        return sseTaskDao.findList(pojo);
    }

    @Override
    public List<SseTask> findListById(SseTask pojo) {
        return sseTaskDao.findListById(pojo);
    }

    @Override
    public int insert(SseTask pojo) {
        return sseTaskDao.insert(pojo);
    }

    // 全部查询
    @Override
    public PageInfo<SseTask> findListPage(SseTask pojo) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        pojo.setSysUser(currentSysUser);
        List<SseTask> list = sseTaskDao.findList(pojo);
        SseTask tempTask = null;
        SysUser tempUser = null;
        for(int i =0;i<list.size();i++){
            tempTask = list.get(i);
            tempUser = sseTaskDao.findUserByTaskId(tempTask);
            tempTask.setSysUser(tempUser);
            list.set(i,tempTask);
        }
        PageInfo<SseTask> page = new PageInfo<SseTask>(list);
        return page;
    }

    @Override
    public PageInfo<SseTask> findDistributionList(SseTask pojo) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        pojo.setSysUser(currentSysUser);
        int size = currentSysUser.getAuthUsers().size();
        List<SseTask> list = null;
        //如果没有数据赋权，查询自己
        if (true) {
//        if (size == 0) {
            BigInteger tempId = currentSysUser.getUserId();
            List<BigInteger> tempList = new ArrayList();
            tempList.add(tempId);
            currentSysUser.setAuthUsers(tempList);
            pojo.setSysUser(currentSysUser);
        }
        list = sseTaskDao.findDistributionList(pojo);

        PageInfo<SseTask> page = new PageInfo<SseTask>(list);
        return page;
    }

    @Override
    public int updateOne(SseTask pojo) {
        return sseTaskDao.updateOne(pojo);
    }

    @Transactional
    @Override
    public int addTaskUser(List<SseTask> list) {
        int result = 0;
        SseTask sseTask = null;
        SseTaskUser sseTaskUser = null;
        SysUser sysUser = null;
        Subject currentUser = SecurityUtils.getSubject();
        SysUser currentSysUser = (SysUser) currentUser.getPrincipal();
        List<SseTaskUser> tempList = null;
        for (int i = 0; i < list.size(); i++) {
            sseTask = list.get(i);
            sseTask.setStatus(1);//0任务状态：0 新建；1 已下发 2 完成
            Timestamp optTime = new Timestamp(System.currentTimeMillis());
            sseTask.setOptTime(optTime);
            sysUser = sseTask.getSysUser();
            sseTaskUser = new SseTaskUser();
            sseTaskUser.setTaskId(sseTask.getTaskId());
            tempList = sseTaskUserDao.findList(sseTaskUser);
            if (tempList.size() == 0) {// 避免重复分发数据
                sseTaskUser.setUserId(sysUser.getUserId());
                sseTaskUser.setTuId(UUIDUtil.create32UUID());
                result = sseTaskUserDao.insert(sseTaskUser);
            }

            // 设置操作人员
            if (currentSysUser != null) {
                sseTask.setOptPerson(currentSysUser.getUserId());
            }
            result = sseTaskDao.updateOne(sseTask);
        }
        return result;
    }
}
