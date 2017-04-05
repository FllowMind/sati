package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Permission;
import com.jyu.sati.entity.RolePermission;

/**
 * @author 淋雨又调皮
 *
 */
public interface PermissionDao {
	int deleteByPrimaryKey(Integer permissionId);

	int insert(Permission record);

	int insertSelective(Permission record);

	Permission selectByPrimaryKey(Integer permissionId);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	/**
	 * 根据规则id获取所有权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getPermissionByRoleId(@Param("roleId") Integer roleId);

	/**
	 * 更新指定权限状态
	 * 
	 * @return
	 */
	int updatePermissionStatus(@Param("permissionId") Integer permissionId);

	/**
	 * 当level 为空时获取所有权限
	 * 
	 * @param level
	 * @return
	 */
	List<Permission> getAllPermissions(@Param("level") Integer level);

	/**
	 * 获取子权限
	 * 
	 * @param fatherId
	 * @return
	 */
	List<Permission> getAllChildPermissions(@Param("fatherId") Integer fatherId);

	/**
	 * 获取权限状态
	 * 
	 * @param permId
	 * @return
	 */
	Integer getPermissionStatus(@Param("permId") Integer permId);

	/**
	 * 更新子权限状态
	 * 
	 * @param fatherId
	 * @param permStatus
	 * @return
	 */
	int updateChildStatus(@Param("fatherId") Integer fatherId, @Param("permStatus") Integer permStatus);

	/**
	 * 获取角色已绑定的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getAllPermissionsByRoleId(@Param("roleId") Integer roleId);

	/**
	 * 获取角色未绑定的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getAllUnBindPermissionsByRoleId(@Param("roleId") Integer roleId);

	/**
	 * 获取所有未绑定菜单的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getUnBindPermissions();

	/**
	 * 获取所有没有绑定菜单和綁定了指定菜单的菜单权限,可以不指定菜单
	 * 
	 * @param menuId
	 *            指定菜单id
	 * @param permMark
	 *            权限标记，1为菜单权限，2 为普通权限
	 * @return
	 */
	List<Permission> getUnBindPermissionsByMenuIdAndMark(@Param("menuId") Integer menuId,
			@Param("permMark") Integer permMark);

	/**
	 * 获取角色绑定的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getAllBindPermissionsByRoleId(@Param("roleId") Integer roleId);

	/**
	 * <!-- 获取未绑定指定角色的子权限 -->
	 * 
	 * @param roleId
	 *            要绑定角色Id
	 * @param permissionId
	 *            父权限Id
	 * @return
	 */
	List<Permission> getAllUnBindRoleChildsByFatherId(RolePermission rp);

}