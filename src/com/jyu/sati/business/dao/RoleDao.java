package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Role;

public interface RoleDao {
	int deleteByPrimaryKey(Integer roleId);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(Integer roleId);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	List<Role> getRoleByUserId(@Param("userId")String userId);
	
	/**
	 * 获取所有的角色
	 * @return
	 */
	List<Role> getAllRoles();

}