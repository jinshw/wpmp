package com.site.mountain.controller.wpmp;

import com.site.mountain.entity.SysUser;
import com.site.mountain.entity.WpmpProcessNode;
import com.site.mountain.entity.WpmpStartend;
import com.site.mountain.service.WpmpProcessNodeService;
import com.site.mountain.service.WpmpStartEndService;
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
@RequestMapping(value = "startend")
public class StartEndController {

    @Autowired
    WpmpStartEndService wpmpStartEndService;
    @Autowired
    WpmpProcessNodeService wpmpProcessNodeService;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(@RequestBody WpmpStartend wpmpStartend) {
        List<WpmpStartend> list = wpmpStartEndService.findList(wpmpStartend);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list",list);
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody WpmpStartend wpmpStartend) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        String seId = UUIDUtil.create32UUID();
        wpmpStartend.setSeId(seId);
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        wpmpStartend.setCreateTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            wpmpStartend.setUserId(sysUser.getUserId());
        }
        int result = wpmpStartEndService.insert(wpmpStartend);

        WpmpProcessNode wpmpProcessNode = new WpmpProcessNode();
        wpmpProcessNode.setSeId(seId);
        wpmpProcessNode.setPnId(UUIDUtil.create32UUID());
        wpmpProcessNode.setStepActive(1);
        if (sysUser != null) {
            wpmpProcessNode.setUserId(sysUser.getUserId());
        }
        wpmpProcessNode.setCreateTime(createtime);
        result = wpmpProcessNodeService.insert(wpmpProcessNode);
        if (result == 0) {
            map.put("status", 50000);
            map.put("message", "执行失败");
        } else {
            map.put("status", 20000);
            map.put("message", "执行成功");
        }
        return map;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> edit(@RequestBody WpmpStartend wpmpStartend) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            wpmpStartend.setUserId(sysUser.getUserId());
        }
        int flag = wpmpStartEndService.updateOne(wpmpStartend);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody WpmpStartend wpmpStartend) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        WpmpStartend deleteObj = new WpmpStartend();
        deleteObj.setSeId(wpmpStartend.getSeId());
        int flag = wpmpStartEndService.delete(deleteObj);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

}
