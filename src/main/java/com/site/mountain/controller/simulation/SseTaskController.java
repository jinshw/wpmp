package com.site.mountain.controller.simulation;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.*;
import com.site.mountain.service.SseTaskService;
import com.site.mountain.utils.MD5Util;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "sseTask")
public class SseTaskController {

    @Autowired
    private SseTaskService sseTaskService;

    @RequestMapping(value = "taskList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> taskList(@RequestBody SseTask sseTask) {
        PageInfo<SseTask> pageInfo = sseTaskService.findListPage(sseTask);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "findDistributionList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findDistributionList(@RequestBody SseTask sseTask) {
        PageInfo<SseTask> pageInfo = sseTaskService.findDistributionList(sseTask);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "addData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addData(@RequestBody SseTask sseTask) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        // 判断数据编号是否已经存在
        SseTask temp = new SseTask();
        temp.setTaskId(sseTask.getTaskId());
        List tempList = sseTaskService.findListById(temp);
        if (tempList.size() == 0) {
            Timestamp createtime = new Timestamp(System.currentTimeMillis());
            sseTask.setOptTime(createtime);
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            if (sysUser != null) {
                sseTask.setOptPerson(sysUser.getUserId());
            }
            sseTask.setTaskId(UUIDUtil.create32UUID());
            int flag = sseTaskService.insert(sseTask);
            if (flag == 1) {
                map.put("status", 20000);
                map.put("message", "执行成功");
            } else {
                map.put("status", 50000);
                map.put("message", "执行失败");
            }
        } else {
            map.put("status", 50001);
            map.put("message", "ID已经存在");
        }
        return map;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody SseTask sseTask) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        SseTask deleteObj = new SseTask();
        deleteObj.setTaskId(sseTask.getTaskId());
        int flag = sseTaskService.delete(deleteObj);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editData(@RequestBody SseTask sseTask) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sseTask.setOptTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            sseTask.setOptPerson(sysUser.getUserId());
        }
        int flag = sseTaskService.updateOne(sseTask);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }


    @RequestMapping(value = "addTaskUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addTaskUser(@RequestBody List<SseTask> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        int flag = sseTaskService.addTaskUser(list);
        if (flag > 0) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

}
