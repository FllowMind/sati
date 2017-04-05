package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Company;

public interface CompanyDao {
	int deleteByPrimaryKey(Integer companyId);

	int insert(Company record);

	int insertSelective(Company record);

	Company selectByPrimaryKey(Integer companyId);

	int updateByPrimaryKeySelective(Company record);

	int updateByPrimaryKey(Company record);

	/**
	 * 通过用户id获取企业信息
	 * 
	 * @param userId
	 * @return
	 */
	Company getCompanyByUserId(@Param("userId") String userId);
}