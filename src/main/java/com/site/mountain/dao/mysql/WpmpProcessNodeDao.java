package com.site.mountain.dao.mysql;

import java.util.List;
import com.site.mountain.entity.WpmpProcessNode;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface WpmpProcessNodeDao {

    int delete(WpmpProcessNode pojo);

    List<WpmpProcessNode> findList(WpmpProcessNode pojo);

    int insert(WpmpProcessNode pojo);

    int insertSelective(List<WpmpProcessNode> pojo);

    int updateOne(WpmpProcessNode pojo);

    int selectMaxStep(WpmpProcessNode pojo);

}