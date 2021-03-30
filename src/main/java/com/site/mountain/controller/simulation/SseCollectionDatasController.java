package com.site.mountain.controller.simulation;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseCollectionDatas;
import com.site.mountain.entity.SseTask;
import com.site.mountain.entity.SysUser;
import com.site.mountain.service.SseCollectionDatasService;
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

/**
 * 仿真平台采集数据控制器
 */
@Controller
@RequestMapping("sseCDDatas")
public class SseCollectionDatasController {
    @Autowired
    SseCollectionDatasService sseCollectionDatasService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestBody SseCollectionDatas sseCollectionDatas) {
        PageInfo<SseCollectionDatas> pageInfo = sseCollectionDatasService.findList(sseCollectionDatas);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }
    @RequestMapping(value = "myCollectionList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> myCollectionList(@RequestBody SseCollectionDatas sseCollectionDatas) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        sseCollectionDatas.setPerson(sysUser.getUserId());
        PageInfo<SseCollectionDatas> pageInfo = sseCollectionDatasService.findList(sseCollectionDatas);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "findDistributionList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findDistributionList(@RequestBody SseCollectionDatas sseCollectionDatas) {
        PageInfo<SseCollectionDatas> pageInfo = sseCollectionDatasService.findDistributionList(sseCollectionDatas);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "getListNotPage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getListNotPage(@RequestBody SseCollectionDatas sseCollectionDatas) {
        List<SseCollectionDatas> list = sseCollectionDatasService.getListNotPage(sseCollectionDatas);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("number", list.size());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "addData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addData(@RequestBody SseCollectionDatas sseCollectionDatas) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        // 判断数据编号是否已经存在
        SseCollectionDatas temp = new SseCollectionDatas();
        temp.setCdId(sseCollectionDatas.getCdId());
        List tempList = sseCollectionDatasService.findListByObj(temp);
        if (tempList.size() == 0) {
            Timestamp createtime = new Timestamp(System.currentTimeMillis());
            sseCollectionDatas.setOptTime(createtime);
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            if (sysUser != null) {
                sseCollectionDatas.setPerson(sysUser.getUserId());
            }

//            int flag = sseCollectionDatasService.addData(sseCollectionDatas);
            int flag = sseCollectionDatasService.addCollectionDatas(sseCollectionDatas);
            if (flag == 1) {
                map.put("status", 20000);
                map.put("message", "执行成功");
            } else {
                map.put("status", 50000);
                map.put("message", "执行失败");
            }
        } else {
            map.put("status", 50001);
            map.put("message", "数据ID已经存在");
        }
        return map;
    }

    @RequestMapping(value = "editData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editData(@RequestBody SseCollectionDatas sseCollectionDatas) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sseCollectionDatas.setOptTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            sseCollectionDatas.setPerson(sysUser.getUserId());
        }
        int flag = sseCollectionDatasService.updateOne(sseCollectionDatas);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "deleteData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteData(@RequestBody SseCollectionDatas sseCollectionDatas) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        SseCollectionDatas deleteObj = new SseCollectionDatas();
        deleteObj.setCdId(sseCollectionDatas.getCdId());
        int flag = sseCollectionDatasService.delete(deleteObj);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "addCollectionUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addCollectionUser(@RequestBody List<SseCollectionDatas> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        int flag = sseCollectionDatasService.addCollectionUser(list);
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
