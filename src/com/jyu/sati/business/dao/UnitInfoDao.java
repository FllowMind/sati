package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.UnitInfo;

public interface UnitInfoDao {
	int deleteByPrimaryKey(Integer unitInfoId);

	int insert(UnitInfo record);

	int insertSelective(UnitInfo record);

	UnitInfo selectByPrimaryKey(Integer unitInfoId);

	int updateByPrimaryKeySelective(UnitInfo record);

	int updateByPrimaryKey(UnitInfo record);

	/**
	 * 通过用户id获取高校信息
	 * 
	 * @param userId
	 * @return
	 */
	UnitInfo getCollegeByUserId(@Param("userId") String userId);

	/**
	 * 通过用户id获取科研单位信息
	 * 
	 * @param userId
	 * @return
	 */
	UnitInfo getScientificByUserId(@Param("userId") String userId);
}