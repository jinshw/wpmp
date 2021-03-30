package com.site.mountain.service.impl;

import com.site.mountain.dao.mysql.SseSceneTypeDao;
import com.site.mountain.entity.SseSceneDatas;
import com.site.mountain.entity.SseSceneType;
import com.site.mountain.entity.SysDept;
import com.site.mountain.service.SseSceneDatasService;
import com.site.mountain.service.SseSceneTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SseSceneTypeServiceImpl implements SseSceneTypeService {
    @Autowired
    private SseSceneTypeDao sseSceneTypeDao;
    @Autowired
    private SseSceneDatasService sseSceneDatasService;

    @Override
    public SseSceneType getSseSceneType(SseSceneType sseSceneType) {
        SseSceneType tree = new SseSceneType();
        tree = sseSceneTypeDao.selectByid(sseSceneType.getStId());
        SseSceneType parentSceneType = sseSceneTypeDao.selectByid(tree.getpId());
        tree.setParentSceneType(parentSceneType);
        SseSceneType spn = new SseSceneType();
        spn.setpId(sseSceneType.getStId());
        spn.setStType(sseSceneType.getStType());
        List<SseSceneType> children = sseSceneTypeDao.selectByPid(spn);
        for (SseSceneType sceneType : children) {
            sceneType.setStType(sseSceneType.getStType());// 递归时设置类型为初始传递的值
            SseSceneType s = getSseSceneType(sceneType);
            tree.getChildren().add(s);
        }
        return tree;
    }

    @Override
    public SseSceneType getSseSceneTypeTreeById(SseSceneType sseSceneType) {
        SseSceneType tree = new SseSceneType();
        tree = sseSceneTypeDao.selectByid(sseSceneType.getStId());
        SseSceneType spn = new SseSceneType();
        spn.setpId(sseSceneType.getStId());
        List<SseSceneType> children = sseSceneTypeDao.selectByPid(spn);
        for (SseSceneType sceneType : children) {
            SseSceneType s = getSseSceneTypeTreeById(sceneType);
            tree.getChildren().add(s);
        }
        return tree;
    }

    public int insert(SseSceneType pojo) {
        return sseSceneTypeDao.insert(pojo);
    }

    public int updateOne(SseSceneType pojo) {
        return sseSceneTypeDao.updateOne(pojo);
    }

    public Map<String, Object> deleteObj(SseSceneType pojo) {
        Map<String, Object> map = new HashMap<>();
        SseSceneDatas sseSceneDatas = new SseSceneDatas();
        sseSceneDatas.setRoadType(pojo.getStId());
        List<SseSceneDatas> tempList = sseSceneDatasService.findListByObj(sseSceneDatas);
        if (tempList.size() == 0) {
            map.put("status", 50000);
            map.put("message", "场景道路类型有引用");
            sseSceneDatas.setRoadType(null);
            sseSceneDatas.setSceneType(pojo.getStId());
            tempList = sseSceneDatasService.findListByObj(sseSceneDatas);
        }

        if (tempList.size() > 0) {
            map.put("status", 50000);
            map.put("message", "场景类型有引用");
        } else {
            SseSceneType tree = getSseSceneType(pojo);
            if (tree.getChildren().size() == 0) {//删除的节点是叶子节点
                int flag = sseSceneTypeDao.delete(pojo);
                if (flag > 0) {
                    map.put("status", 20000);
                    map.put("message", "执行成功");
                } else {
                    map.put("status", 50000);
                    map.put("message", "执行失败");
                }
            } else {
                map.put("status", 50000);
                map.put("message", "不是叶子节点");
            }
        }
        return map;
    }

    public int delete(SseSceneType pojo) {
        return sseSceneTypeDao.delete(pojo);
    }

    @Override
    public SseSceneType selectByid(String id) {
        return sseSceneTypeDao.selectByid(id);
    }
}
