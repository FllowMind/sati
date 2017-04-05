package com.jyu.sati.business.dao;

import com.jyu.sati.entity.CategoryInfo;

public interface CategoryInfoDao {
    int deleteByPrimaryKey(Integer categoryInfoId);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    CategoryInfo selectByPrimaryKey(Integer categoryInfoId);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
}