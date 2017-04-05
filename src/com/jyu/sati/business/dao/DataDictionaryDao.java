package com.jyu.sati.business.dao;

import com.jyu.sati.entity.DataDictionary;

public interface DataDictionaryDao {
    int deleteByPrimaryKey(Integer dataId);

    int insert(DataDictionary record);

    int insertSelective(DataDictionary record);

    DataDictionary selectByPrimaryKey(Integer dataId);

    int updateByPrimaryKeySelective(DataDictionary record);

    int updateByPrimaryKey(DataDictionary record);
}