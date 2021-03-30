package com.site.mountain.controller.simulation;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.entity.SseCollectionDatas;
import com.site.mountain.entity.SseSceneType;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SseSceneTypeService;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "sceneType")
public class SseSceneTypeController {
    @Autowired
    SseSceneTypeService sseSceneTypeService;

    @RequestMapping("getTree")
    @ResponseBody
    public Map<String, Object> getTree(@RequestBody SseSceneType sseSceneType) {
        Map<String, Object> map = new HashMap<String, Object>();
        SseSceneType tree = new SseSceneType();
        tree = sseSceneTypeService.getSseSceneType(sseSceneType);
        map.put("code", 20000);
        map.put("data", tree);
        return map;
    }

    @RequestMapping("getSseSceneTypeTreeById")
    @ResponseBody
    public Map<String, Object> getTreeById(@RequestBody SseSceneType sseSceneType) {
        Map<String, Object> map = new HashMap<String, Object>();
        SseSceneType tree = new SseSceneType();
        tree = sseSceneTypeService.getSseSceneTypeTreeById(sseSceneType);
        map.put("code", 20000);
        map.put("data", tree);
        return map;
    }

    @RequestMapping(value = "addSceneType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addSceneType(@RequestBody SseSceneType sseSceneType, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        String stId = sseSceneType.getStId();
        if (null == stId || ("").equals(stId)) {
            map.put("status", 50001);
            map.put("message", "ID 不能为空");
        } else {
            SseSceneType tempObj = sseSceneTypeService.selectByid(sseSceneType.getStId());

            if (tempObj != null) {
                map.put("status", 50002);
                map.put("message", "ID 已经存在");
            } else {
                map = addOne(sseSceneType, map);
            }
        }
        return map;
    }

    private Map<String, Object> addOne(SseSceneType sseSceneType, Map<String, Object> map) {
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sseSceneType.setOptTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            sseSceneType.setOptPerson(sysUser.getUserId());
        }
        String stId = sseSceneType.getStId();
        if(stId == null || stId == ""){
            String uuid = UUIDUtil.create32UUID();
            sseSceneType.setStId(uuid);
        }
        int flag = sseSceneTypeService.insert(sseSceneType);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "editSceneType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editSceneType(@RequestBody SseSceneType sseSceneType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sseSceneType.setOptTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            sseSceneType.setOptPerson(sysUser.getUserId());
        }
        int flag = sseSceneTypeService.updateOne(sseSceneType);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "deleteSceneType", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteSceneType(@RequestBody SseSceneType sseSceneType) {
        Map<String, Object> map = new HashMap<String, Object>();
        SseSceneType deleteObj = new SseSceneType();
        deleteObj.setStId(sseSceneType.getStId());
        map = sseSceneTypeService.deleteObj(deleteObj);
        map.put("code", 20000);
        return map;
    }
}
