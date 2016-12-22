package com.mybatis.mapper;

import com.mybatis.model.LongTime;
import com.mybatis.model.LongTimeExample;
import java.util.List;

public interface LongTimeMapper {
    int countByExample(LongTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LongTime record);

    int insertSelective(LongTime record);

    List<LongTime> selectByExample(LongTimeExample example);

    LongTime selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LongTime record);

    int updateByPrimaryKey(LongTime record);
}