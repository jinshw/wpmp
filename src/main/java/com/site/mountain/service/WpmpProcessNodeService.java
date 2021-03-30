package com.site.mountain.service;

import com.site.mountain.entity.WpmpNodeFiles;
import com.site.mountain.entity.WpmpProcessNode;

import java.util.List;
import java.util.Map;

public interface WpmpProcessNodeService {

    int delete(WpmpNodeFiles wpmpNodeFiles);

    List<WpmpProcessNode> findList(WpmpProcessNode pojo);

    List<WpmpProcessNode> findCurrentNodeList(WpmpProcessNode pojo);

    int insert(WpmpProcessNode pojo);

}
