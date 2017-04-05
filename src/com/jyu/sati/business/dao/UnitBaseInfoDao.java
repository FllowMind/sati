package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.UnitBaseInfo;

public interface UnitBaseInfoDao {
	int deleteByPrimaryKey(Integer unitBaseinfoId);

	int insert(UnitBaseInfo record);

	int insertSelective(UnitBaseInfo record);

	UnitBaseInfo selectByPrimaryKey(Integer unitBaseinfoId);

	int updateByPrimaryKeySelective(UnitBaseInfo record);

	int updateByPrimaryKey(UnitBaseInfo record);

	/**
	 * 更新企业用户的单位机构代码图片存放路径
	 * 
	 * @param userId
	 *            企业用户Id
	 * @param imageUrl
	 *            存放图片的路径
	 * @return
	 */
	int updateCompanyUnitCodeImage(@Param("userId") String userId, @Param("imageUrl") String imageUrl);
	/**
	 * 更新中介机构用户的单位机构代码图片存放路径
	 * 
	 * @param userId
	 *           中介机构用户Id
	 * @param imageUrl
	 *            存放图片的路径
	 * @return
	 */
	int updateAgencyUnitCodeImage(@Param("userId") String userId, @Param("imageUrl") String imageUrl);
	/**
	 * 更新高校用户的单位机构代码图片存放路径
	 * 
	 * @param userId
	 *            高校用户Id
	 * @param imageUrl
	 *            存放图片的路径
	 * @return
	 */
	int updateCollegeUnitCodeImage(@Param("userId") String userId, @Param("imageUrl") String imageUrl);
	/**
	 * 更新科研单位用户的单位机构代码图片存放路径
	 * 
	 * @param userId
	 *            科研单位用户Id
	 * @param imageUrl
	 *            存放图片的路径
	 * @return
	 */
	int updateScientifyUnitCodeImage(@Param("userId") String userId, @Param("imageUrl") String imageUrl);
}