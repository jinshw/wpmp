package com.site.mountain.controller.wpmp;

import com.alibaba.fastjson.JSONObject;
import com.site.mountain.constant.ConstantProperties;
import com.site.mountain.controller.sys.SysFilesController;
import com.site.mountain.entity.*;
import com.site.mountain.service.SysFilesService;
import com.site.mountain.service.WpmpNodeFilesService;
import com.site.mountain.service.WpmpProcessNodeService;
import com.site.mountain.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "processnode")
public class ProcessNodeController {
    private final static Logger logger = LoggerFactory.getLogger(ProcessNodeController.class);
    @Autowired
    WpmpProcessNodeService wpmpProcessNodeService;
    @Autowired
    WpmpNodeFilesService wpmpNodeFilesService;


    @Autowired
    SysFilesService sysFilesService;
    @Autowired
    ConstantProperties constantProperties;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(@RequestBody WpmpProcessNode wpmpProcessNode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        List<WpmpProcessNode> list = wpmpProcessNodeService.findList(wpmpProcessNode);
        if(list.size() > 0){
            map.put("currentNode", list.get(0));
        }else {
            map.put("currentNode", null);
        }
        return map;
    }

    @RequestMapping(value = "currentlist", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getCurrentList(@RequestBody WpmpProcessNode wpmpProcessNode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        List<WpmpProcessNode> list = wpmpProcessNodeService.findCurrentNodeList(wpmpProcessNode);
        if(list.size() > 0){
            map.put("currentNode", list.get(0));
        }else {
            map.put("currentNode", null);
        }

        return map;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> add(@RequestBody WpmpProcessNode wpmpProcessNode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        wpmpProcessNode.setPnId(UUIDUtil.create32UUID());
        Timestamp createtime = new Timestamp(System.currentTimeMillis());
        wpmpProcessNode.setCreateTime(createtime);
        Subject currentUser = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) currentUser.getPrincipal();
        if (sysUser != null) {
            wpmpProcessNode.setUserId(sysUser.getUserId());
        }
        int result = wpmpProcessNodeService.insert(wpmpProcessNode);
        if (result == 0) {
            map.put("status", 50000);
            map.put("message", "执行失败");
        } else {
            map.put("status", 20000);
            map.put("message", "执行成功");
        }
        return map;
    }


    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestBody WpmpNodeFiles wpmpNodeFiles) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 20000);
        int flag = wpmpProcessNodeService.delete(wpmpNodeFiles);
        if (flag == 1) {
            map.put("status", 20000);
            map.put("message", "执行成功");
        } else {
            map.put("status", 50000);
            map.put("message", "执行失败");
        }
        return map;
    }


    @RequestMapping(value = "fileUpload", method = RequestMethod.POST)
    @Transactional
    public void fileUpload(MultipartFile file, String pnId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        SysFiles sysFiles = getSysFiles(file);
        //获取文件名
        String filePathName = UUIDUtil.create32UUID() + sysFiles.getSuffixName();
//        String fileName = file.getOriginalFilename();
        String path = constantProperties.getFileUploadPath();
        String filePath = path + filePathName;
        File saveFile = new File(filePath);
        boolean isCreateSuccess = saveFile.createNewFile();
        if (isCreateSuccess) {
            //写入文件
            file.transferTo(saveFile);
            jsonObject.put("status", 200);
            String coverUrl = constantProperties.getImgUrl() + "/" + filePathName;
            jsonObject.put("name", filePathName);
            jsonObject.put("url", coverUrl);
            sysFiles.setPath(filePathName);
            Subject currentUser = SecurityUtils.getSubject();
            SysUser sysUser = (SysUser) currentUser.getPrincipal();
            sysFiles.setCreatePerson(sysUser.getUserId());
            int flag = sysFilesService.insert(sysFiles);

            WpmpNodeFiles wpmpNodeFiles = new WpmpNodeFiles();
            wpmpNodeFiles.setPnId(pnId);
            wpmpNodeFiles.setFid(sysFiles.getFid());
            flag = wpmpNodeFilesService.insert(wpmpNodeFiles);

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

    public SysFiles getSysFiles(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        long size = file.getSize();
        SysFiles sysFiles = new SysFiles();
        sysFiles.setFname(fileName);
        sysFiles.setSuffixName(suffix);
        sysFiles.setSize(size);
        return sysFiles;
    }

    @RequestMapping(value = "fileDownLoad", method = RequestMethod.POST)
    public void fileDownLoad(@RequestBody SysFiles sysFiles, HttpServletRequest request, HttpServletResponse response) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 20000);
        String fileName = sysFiles.getFname();
        String filePath = sysFiles.getPath();
        String path = constantProperties.getFileUploadPath() + filePath;
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
