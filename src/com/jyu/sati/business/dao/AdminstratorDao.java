package com.jyu.sati.business.dao;

import com.jyu.sati.entity.Adminstrator;

public interface AdminstratorDao {
    int deleteByPrimaryKey(Integer administratorId);

    int insert(Adminstrator record);

    int insertSelective(Adminstrator record);

    Adminstrator selectByPrimaryKey(Integer administratorId);

    int updateByPrimaryKeySelective(Adminstrator record);

    int updateByPrimaryKey(Adminstrator record);
}