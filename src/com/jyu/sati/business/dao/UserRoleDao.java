package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.UserRole;

public interface UserRoleDao {
	int deleteByPrimaryKey(Integer userRoleId);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(Integer userRoleId);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);

	/**
	 * 通过userId 删除用户角色绑定
	 * @param userId
	 * @return
	 */
	int deleteByUserId(@Param("userId") String userId);

}