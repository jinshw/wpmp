package com.site.mountain.controller.simulation;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.SseKeyword;
import com.site.mountain.entity.SseSceneDatas;
import com.site.mountain.service.SseKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "keyword")
public class SseKeywordController {
    @Autowired
    SseKeywordService sseKeywordService;

    @RequestMapping(value = "pageList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageList(@RequestBody SseKeyword sseKeyword) {
        PageInfo<SseKeyword> pageInfo = sseKeywordService.findListPage(sseKeyword);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestBody SseKeyword sseKeyword) {
        List<SseKeyword> list = sseKeywordService.findList(sseKeyword);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("code", 20000);
        return map;
    }
}
