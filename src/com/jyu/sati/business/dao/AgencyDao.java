package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Agency;

/**
 * @author 淋雨又调皮
 *
 */
public interface AgencyDao {
	int deleteByPrimaryKey(Integer agencyId);

	int insert(Agency record);

	int insertSelective(Agency record);

	Agency selectByPrimaryKey(Integer agencyId);

	int updateByPrimaryKeySelective(Agency record);

	int updateByPrimaryKey(Agency record);

	/**
	 * 通过用户id获取中介机构信息
	 * 
	 * @param userId
	 * @return
	 */
	Agency getAgencyByUserId(@Param("userId") String userId);
}