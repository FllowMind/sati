package com.jyu.sati.business.dao;

import com.jyu.sati.entity.RolePermission;

public interface RolePermissionDao {
	int deleteByPrimaryKey(Integer rolePermissionId);

	int insert(RolePermission record);

	int insertSelective(RolePermission record);

	RolePermission selectByPrimaryKey(Integer rolePermissionId);

	int updateByPrimaryKeySelective(RolePermission record);

	int updateByPrimaryKey(RolePermission record);

	/**
	 * 解除角色和权限的绑定
	 * 
	 * @param roleId
	 * @param permissionId
	 * @return
	 */
	int unBindRolePermission(RolePermission rp);
	
}