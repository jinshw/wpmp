package com.site.mountain.service;

import com.site.mountain.entity.WpmpNodeFiles;

import java.util.List;

public interface WpmpNodeFilesService {
    int delete(WpmpNodeFiles pojo);

    List<WpmpNodeFiles> findList(WpmpNodeFiles pojo);

    int insert(WpmpNodeFiles pojo);

    int insertSelective(List<WpmpNodeFiles> pojo);

    int updateOne(WpmpNodeFiles pojo);
}
