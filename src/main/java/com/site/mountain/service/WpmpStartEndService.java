package com.site.mountain.service;

import com.site.mountain.entity.WpmpStartend;

import java.util.List;

public interface WpmpStartEndService {
    int delete(WpmpStartend pojo);

    List<WpmpStartend> findList(WpmpStartend pojo);

    int insert(WpmpStartend pojo);

    int insertSelective(List<WpmpStartend> pojo);

    int updateOne(WpmpStartend pojo);

}
