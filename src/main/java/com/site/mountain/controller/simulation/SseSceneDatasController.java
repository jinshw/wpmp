package com.site.mountain.controller.simulation;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.controller.sys.SysFilesController;
import com.site.mountain.entity.*;
import com.site.mountain.service.SseKeywordService;
import com.site.mountain.service.SseSceneDatasService;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("sseSceneDatas")
public class SseSceneDatasController {
    private final static Logger logger = LoggerFactory.getLogger(SseSceneDatasController.class);
    @Autowired
    private SseSceneDatasService sseSceneDatasService;
    @Autowired
    private SseKeywordService sseKeywordService;
    @Autowired
    private ConstantProperties constantProperties;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list(@RequestBody SseSceneDatas sseSceneDatas) {
        PageInfo<SseSceneDatas> pageInfo = sseSceneDatasService.findList(sseSceneDatas);
        List<SseKeyword> sseKeywordList = sseKeywordService.findList(new SseKeyword());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("keywordlist", sseKeywordList);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "weblist", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> webList(@RequestBody SseSceneDatas sseSceneDatas) {
        PageInfo<SseSceneDatas> pageInfo = sseSceneDatasService.findWebList(sseSceneDatas);
        List<SseKeyword> sseKeywordList = sseKeywordService.findList(new SseKeyword());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("keywordlist", sseKeywordList);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "mySceneList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> mySceneList(@RequestBody SseSceneDatas sseSceneDatas) {
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        sseSceneDatas.setOptPerson(sysUser.getUserId());
        PageInfo<SseSceneDatas> pageInfo = sseSceneDatasService.findList(sseSceneDatas);
        List<SseKeyword> sseKeywordList = sseKeywordService.findList(new SseKeyword());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("keywordlist", sseKeywordList);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "findSceneDistributionList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findSceneDistributionList(@RequestBody SseSceneDatas sseSceneDatas) {
        PageInfo<SseSceneDatas> pageInfo = sseSceneDatasService.findDistributionList(sseSceneDatas);
        List<SseKeyword> sseKeywordList = sseKeywordService.findList(new SseKeyword());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("keywordlist", sseKeywordList);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "listByKeyword", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> listByKeyword(@RequestBody SseSceneDatas sseSceneDatas) {
        PageInfo<SseSceneDatas> pageInfo = sseSceneDatasService.findListByKeyword(sseSceneDatas);
        List<SseKeyword> sseKeywordList = sseKeywordService.findList(new SseKeyword());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", pageInfo);
        map.put("keywordlist", sseKeywordList);
        map.put("number", pageInfo.getTotal());
        map.put("code", 20000);
        return map;
    }

    @RequestMapping(value = "addData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addData(@RequestBody SseSceneDatas sseSceneDatas) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        // 判断数据编号是否已经存在
        SseSceneDatas temp = new SseSceneDatas();
        String sid = sseSceneDatas.getSid();
        temp.setSid(sseSceneDatas.getSid());
        sseSceneDatas.setReviewStatus(0);// 添加时设置为0：质检审核状态:0未审核（未分发）；1：待审核；2审核通过；3：审核未通过；
        List tempList = sseSceneDatasService.findListByObj(temp);
        if (tempList.size() == 0 || sid == null || sid.equals("")) {
            Timestamp createtime = new Timestamp(System.currentTimeMillis());
            sseSceneDatas.setUpdateTime(createtime);
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            if (sysUser != null) {
                sseSceneDatas.setOptPerson(sysUser.getUserId());
            }
            if (sid == null || sid.equals("")) {
                sseSceneDatas.setSid(UUIDUtil.create32UUID());
            }
            int flag = sseSceneDatasService.insert(sseSceneDatas);
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

    @RequestMapping(value = "editData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editData(@RequestBody SseSceneDatas sseSceneDatas) {
        System.out.println("SceneName==" + sseSceneDatas.getSceneName());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        sseSceneDatas.setUpdateTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            sseSceneDatas.setOptPerson(sysUser.getUserId());
        }
        int flag = sseSceneDatasService.updateOne(sseSceneDatas);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "deleteScene", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteScene(@RequestBody SseSceneDatas sseSceneDatas) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        SseSceneDatas deleteObj = new SseSceneDatas();
        deleteObj.setSid(sseSceneDatas.getSid());
        int flag = sseSceneDatasService.delete(deleteObj);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "imgUpload", method = RequestMethod.POST)
    public void imgUpload(@RequestParam(value = "file", required = false) MultipartFile file, String sseSceneDatasStr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        SseSceneDatas sseSceneDatasParam = JSONObject.parseObject(sseSceneDatasStr, SseSceneDatas.class);
        //获取文件名
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String filePathName = sseSceneDatasParam.getSid() + suffix;
        String path = constantProperties.getImgUploadPath();
        String filePath = path + filePathName;
        File saveFile = new File(filePath);
//        boolean isCreateSuccess = saveFile.createNewFile();
        if (true) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            String coverUrl = constantProperties.getImgUrl() + "/" + filePathName;
            jsonObject.put("name", filePathName);
            jsonObject.put("url", coverUrl);
            sseSceneDatasParam.setPreviewFile(filePathName);
//            Subject currentUser = SecurityUtils.getSubject();
//            SysUser sysUser = (SysUser) currentUser.getPrincipal();
//            sseSceneDatasParam.setOptPerson(sysUser.getUserId());
            int flag = sseSceneDatasService.updateOne(sseSceneDatasParam);
            if (flag == 0) {
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            }
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "getPreviewFileUrl", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPreviewFileUrl(@RequestBody SseSceneDatas sseSceneDatas) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        String previewFile = sseSceneDatas.getPreviewFile();
        if (previewFile != null && previewFile != "") {
            String imgUrl = constantProperties.getImgUrl() + "/" + previewFile;
            map.put("status", 20000);
            map.put("imgUrl", imgUrl);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }


    @RequestMapping(value = "addSceneUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addSceneUser(@RequestBody List<SseSceneDatas> list) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        int flag = sseSceneDatasService.addSceneUser(list);
        if (flag > 0) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }

    @RequestMapping(value = "fileDataUpload", method = RequestMethod.POST)
    public void fileDataUpload(@RequestParam(value = "file", required = false) MultipartFile file, String sseSceneDatasStr, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        SseSceneDatas sseSceneDatasParam = JSONObject.parseObject(sseSceneDatasStr, SseSceneDatas.class);
        //获取文件名
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String filePathName = sseSceneDatasParam.getSid() + "-filepath" + suffix;
        String path = constantProperties.getFileUploadPath();
        String filePath = path + filePathName;
        File saveFile = new File(filePath);
//        boolean isCreateSuccess = saveFile.createNewFile();
        if (true) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            String coverUrl = constantProperties.getImgUrl() + "/" + filePathName;
            jsonObject.put("name", filePathName);
            jsonObject.put("url", coverUrl);
            sseSceneDatasParam.setFilePath(filePathName);
//            Subject currentUser = SecurityUtils.getSubject();
//            SysUser sysUser = (SysUser) currentUser.getPrincipal();
//            sseSceneDatasParam.setOptPerson(sysUser.getUserId());
            int flag = sseSceneDatasService.updateScene(sseSceneDatasParam);
            if (flag == 0) {
                jsonObject.put("status", 501);
                jsonObject.put("msg", "文件上传失败");
            }
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("msg", "文件已存在");
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(@RequestBody SseSceneDatas sseSceneDatas, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String fileName = sseSceneDatas.getFilePath();
//        String filePath = sysFiles.getPath();
        String path = constantProperties.getFileUploadPath() + fileName;
        File file = new File(path);

        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        OutputStream outputStream = null;

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        try {
            if (!file.exists()) {
                logger.info("文件不存在");
                jsonObject.put("code", 20000);
                jsonObject.put("status", 500);
                jsonObject.put("data", "");
                jsonObject.put("msg", "文件不存在");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().print(jsonObject.toJSONString());
            }
            response.addHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

            byte[] buffer = new byte[1024];
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = bufferedInputStream.read(buffer);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
