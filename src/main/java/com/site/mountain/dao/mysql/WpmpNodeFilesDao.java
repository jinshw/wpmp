package com.site.mountain.dao.mysql;

import java.util.List;

import com.site.mountain.entity.SysFiles;
import com.site.mountain.entity.WpmpNodeFiles;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface WpmpNodeFilesDao {

    int delete(WpmpNodeFiles pojo);

    List<WpmpNodeFiles> findList(WpmpNodeFiles pojo);

    int insert(WpmpNodeFiles pojo);

    int insertSelective(List<WpmpNodeFiles> pojo);

    int updateOne(WpmpNodeFiles pojo);

}