package com.site.mountain.controller.simulation;

import com.github.pagehelper.PageInfo;
import com.site.mountain.entity.*;
import com.site.mountain.service.SseChartService;
import com.site.mountain.service.SseCollectionDatasService;
import com.site.mountain.service.SseSceneDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "sseChart")
public class SseChartController {
    @Autowired
    private SseCollectionDatasService sseCollectionDatasService;

    @Autowired
    private SseSceneDatasService sseSceneDatasService;

    @Autowired
    private SseChartService sseChartService;

    @RequestMapping(value = "getTotalDatas", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTotalDatas(@RequestBody SseSceneDatas sseSceneDatas) {
        SseCollectionDatas sseCollectionDatas = new SseCollectionDatas();
//        SseSceneDatas sseSceneDatas = new SseSceneDatas();
        List<SseCollectionDatas> sseCollectionDatasList = sseCollectionDatasService.findListByObj(sseCollectionDatas);
        List<SseSceneDatas> sseSceneDatasList = sseSceneDatasService.findListByObj(sseSceneDatas);
        List<SseCollectionDatas> progressTotalList = sseCollectionDatasList.stream().filter(item -> item.getDataStep() > 1).collect(Collectors.toList());
        List<SseSceneDatas> auditTotalList = sseSceneDatasList.stream().filter(item -> item.getReviewStatus() == 2).collect(Collectors.toList());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("collectionTotal", sseCollectionDatasList.size());
        map.put("progressTotal", progressTotalList.size());
        map.put("sceneTotal", sseSceneDatasList.size());
        map.put("auditTotal", auditTotalList.size());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "findLineChart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findLineChart(@RequestBody SseChart sseChart) {
        List<SseChart> list = sseChartService.findCollectionChart(sseChart.getType());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("collectionLine", list);
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "findPieChart", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findPieChart(@RequestBody SseChart sseChart) {
        List<SseChart> list = sseChartService.findPie(sseChart.getType());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("collection", list);
        map.put("code", 20000);
        return map;
    }


    @RequestMapping(value = "todolist", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestBody Todo todo) {
        PageInfo<Todo> pageInfo = sseChartService.findToDoList(todo);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }
}
