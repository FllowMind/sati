package com.jyu.sati.business.dao;

import com.jyu.sati.entity.EmployeeSituation;

public interface EmployeeSituationDao {
    int deleteByPrimaryKey(Integer soeId);

    int insert(EmployeeSituation record);

    int insertSelective(EmployeeSituation record);

    EmployeeSituation selectByPrimaryKey(Integer soeId);

    int updateByPrimaryKeySelective(EmployeeSituation record);

    int updateByPrimaryKey(EmployeeSituation record);
}