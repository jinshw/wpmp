package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.WpmpStartend;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface WpmpStartendDao {

    int delete(WpmpStartend pojo);

    List<WpmpStartend> findList(WpmpStartend pojo);

    int insert(WpmpStartend pojo);

    int insertSelective(List<WpmpStartend> pojo);

    int updateOne(WpmpStartend pojo);

}