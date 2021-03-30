package com.site.mountain.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.site.mountain.dao.mysql.SseChartDao;
import com.site.mountain.dao.mysql.SseCollectionDatasDao;
import com.site.mountain.dao.mysql.SseSceneDatasDao;
import com.site.mountain.dao.mysql.SseTaskDao;
import com.site.mountain.entity.*;
import com.site.mountain.service.SseChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SseChartServiceImpl implements SseChartService {

    @Autowired
    private SseChartDao sseChartDao;

    @Autowired
    private SseTaskDao sseTaskDao;

    @Autowired
    private SseCollectionDatasDao sseCollectionDatasDao;

    @Autowired
    private SseSceneDatasDao sseSceneDatasDao;

    @Override
    public List<SseChart> findCollectionChart(String type) {
        List<SseChart> list = new ArrayList<>();
        switch (type) {
            case "collection":
                list = sseChartDao.findCollectionChart(0);
                break;
            case "progress":
                list = sseChartDao.findProgressChart(0);
                break;
            case "scene":
                list = sseChartDao.findSceneChart(0);
                break;
            case "audit":
                list = sseChartDao.findAuditChart(0);
                break;
        }
        return list;
    }

    public List<SseChart> findPie(String type) {
        List<SseChart> list = new ArrayList<>();
        switch (type) {
            case "collection":
                list = sseChartDao.findPieCollection(0);
                break;
            case "progress":
                break;
            case "scene":
                list = sseChartDao.findPieScene(0);
                break;
            case "audit":
                break;
        }
        return list;
    }

    @Override
    public PageInfo<Todo> findToDoList(Todo pojo) {
        List<Todo> list = new ArrayList<>();
        Todo todo = null;
        PageHelper.startPage(pojo.getPageNum(), pojo.getPageSize());
        // 任务管理
        SseTask sseTask = new SseTask();
        sseTask.setStatus(0);
        List<SseTask> taskList = sseTaskDao.findList(sseTask);
        SseTask taskObj = null;
        for (int i = 0; i < taskList.size(); i++) {
            taskObj = taskList.get(i);
            todo = new Todo();
            todo.setModuleName("任务管理");
            todo.setTodoName(taskObj.getTaskName());
            todo.setStatusName("新增");
            todo.setRouterURL("simulation/task");
            todo.setTime(taskObj.getCreateTime());
            list.add(todo);
        }


        //采集管理
        SseCollectionDatas sseCollectionDatas = new SseCollectionDatas();
        sseCollectionDatas.setDataStep(100);
        List<SseCollectionDatas> collectionList = sseCollectionDatasDao.findList(sseCollectionDatas);
        SseCollectionDatas collectionObj = null;
        for (int i = 0; i < collectionList.size(); i++) {
            collectionObj = collectionList.get(i);
            todo = new Todo();
            todo.setModuleName("采集管理");
            todo.setTodoName(collectionObj.getCdName());
            todo.setStatusName("未分发");
            todo.setRouterURL("simulation/data");
            todo.setTime(collectionObj.getOptTime());
            list.add(todo);
        }

        //场景管理
        SseSceneDatas sseSceneDatas = new SseSceneDatas();
        sseSceneDatas.setReviewStatus(0);
        List<SseSceneDatas> sceneList = sseSceneDatasDao.findList(sseSceneDatas);
        SseSceneDatas sceneObj = null;
        for (int i = 0; i < sceneList.size(); i++) {
            sceneObj = sceneList.get(i);
            if (sceneObj.getReviewStatus() == 0) {
                todo = new Todo();
                todo.setModuleName("场景管理");
                todo.setTodoName(sceneObj.getSceneName());
                todo.setStatusName("未审核");
                todo.setRouterURL("simulation/scene");
                todo.setTime(sceneObj.getUpdateTime());
                list.add(todo);
            }
        }

        //审核管理
        SseSceneDatas audit = new SseSceneDatas();
        audit.setReviewStatus(1);
        List<SseSceneDatas> auditList = sseSceneDatasDao.findList(audit);
        SseSceneDatas auditObj = null;
        for (int i = 0; i < auditList.size(); i++) {
            auditObj = auditList.get(i);
            if (auditObj.getReviewStatus() == 1) {
                todo = new Todo();
                todo.setModuleName("审核管理");
                todo.setTodoName(auditObj.getSceneName());
                todo.setStatusName("待审核");
                todo.setRouterURL("simulation/audit");
                todo.setTime(auditObj.getUpdateTime());
                list.add(todo);
            }
        }

        Collections.sort(list, new Comparator<Todo>() {
            @Override
            public int compare(Todo t1, Todo t2) {
                if (t1.getTime().compareTo(t2.getTime()) > 0) {
                    return -1;
                } else if (t1.getTime().compareTo(t2.getTime()) < 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        PageInfo<Todo> page = new PageInfo<Todo>(list);
        return page;
    }

}
