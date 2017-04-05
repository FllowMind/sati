package com.jyu.sati.business.dao;

import com.jyu.sati.entity.SystemLog;

public interface SystemLogDao {
    int deleteByPrimaryKey(Integer systemLogId);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(Integer systemLogId);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
}