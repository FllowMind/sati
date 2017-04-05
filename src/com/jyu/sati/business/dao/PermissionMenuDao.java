package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.PermissionMenu;

public interface PermissionMenuDao {
	int deleteByPrimaryKey(Integer permissionMunuId);

	int insert(PermissionMenu record);

	int insertSelective(PermissionMenu record);

	PermissionMenu selectByPrimaryKey(Integer permissionMunuId);

	int updateByPrimaryKeySelective(PermissionMenu record);

	int updateByPrimaryKey(PermissionMenu record);

	/**
	 * 解除权限菜单的绑定
	 * 
	 * @param permissionId
	 * @param menuId
	 * @return
	 */
	int unBindPermissionMenu(@Param("permissionId") Integer permissionId, @Param("menuId") Integer menuId);

	/**
	 * 获取指定菜单绑定的权限
	 * @param menuId
	 * @return
	 */
	PermissionMenu getPermissionMenuByMenuId(@Param("menuId") Integer menuId);
}