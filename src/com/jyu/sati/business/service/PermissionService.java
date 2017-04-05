package com.jyu.sati.business.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Menu;
import com.jyu.sati.entity.Permission;
import com.jyu.sati.entity.PermissionMenu;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.RolePermission;
import com.jyu.sati.entity.User;
import com.jyu.sati.entity.UserRole;
import com.jyu.sati.vo.MenuVo;
import com.jyu.sati.vo.RegisterVo;

public interface PermissionService {

	/**
	 * 根据用户名和密码获取用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	User getUserByUserNameAndPassword(String userName, String password) throws BusinessException;

	/**
	 * 根据用户名获取用户
	 * 
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName) throws BusinessException;

	/**
	 * 更新用户
	 * 
	 * @param user
	 * @return
	 */
	int saveUser(User user) throws BusinessException;

	/**
	 * 插入用户
	 * 
	 * @param user
	 * @return
	 */
	int insertUser(RegisterVo regInfo) throws BusinessException;

	/**
	 * 根据规则id获取权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getPermissionsByRoleId(Integer roleId) throws BusinessException;

	/**
	 * 
	 * 根据用户id获取规则
	 * 
	 * @param userId
	 * @return
	 */
	List<Role> getRolesByUserId(String userId) throws BusinessException;

	/**
	 * 根据权限获取菜单
	 * 
	 * @param permissionId
	 * @return
	 */
	List<Menu> getMenusByPermissionId(Integer permissionId) throws BusinessException;

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	int removeUser(String userId);

	/**
	 * 判断父权限是否可用
	 * 
	 * @param perminssionId
	 * @return
	 */
	boolean isFatherPermissionNormal(Integer perminssionId) throws BusinessException;

	/**
	 * 根据用户id获取三级以内的所有菜单
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	Map<String, List<Menu>> getMenusByUserId(String userId) throws BusinessException;

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 */
	Map<String, List<Menu>> getAllMenu() throws BusinessException;

	/**
	 * 获取指定级别菜单
	 * 
	 * @param level
	 * @return
	 * @throws BusinessException
	 */
	List<Menu> getMenuByLevel(Integer level) throws BusinessException;

	/**
	 * 更新指定菜单状态 1或 -1
	 * 
	 * @return
	 */
	int updateMenuStatus(@Param("menuId") Integer menuId) throws BusinessException;

	/**
	 * 添加菜单项
	 * 
	 * @param menu
	 * @return
	 * @throws BusinessException
	 */
	int addMenu(MenuVo menuVo) throws BusinessException;

	/**
	 * 更新菜单
	 * 
	 * @param menu
	 * @return
	 * @throws BusinessException
	 */
	int updateMenu(MenuVo menu) throws BusinessException;

	/**
	 * 添加权限
	 * 
	 * @param permission
	 * @return
	 * @throws BusinessException
	 */
	int addPermission(Permission permission) throws BusinessException;

	/**
	 * 更新指定权限状态
	 * 
	 * @param permissionId
	 * @return
	 */
	int updatePermissionStatus(Integer permissionId) throws BusinessException;

	/**
	 * 
	 * 获取指定级别权限
	 * 
	 * @return
	 * @throws BusinessException
	 */
	List<Permission> getPermissionsByLevel(Integer level) throws BusinessException;

	/**
	 * 获取所有权限
	 * 
	 * @return
	 * @throws BusinessException
	 */
	Map<String, List<Permission>> getAllPermissions() throws BusinessException;

	/**
	 * 获取相应角色的所有权限
	 * 
	 * @return
	 * @throws BusinessException
	 */
	Map<String, List<Permission>> getAllPermissionsByRole(Integer roleId) throws BusinessException;

	/**
	 * 更新权限
	 * 
	 * @param permission
	 * @return
	 */
	int updatePermission(Permission permission);

	/**
	 * 获取所有子菜单
	 * 
	 * @param fatherId
	 * @return
	 */
	List<Menu> getAllChildMenus(Integer fatherId);

	/**
	 * 获取所有子权限
	 * 
	 * @param fatherId
	 * @return
	 * @throws BusinessException
	 */
	List<Permission> getAllChildPermissions(Integer fatherId) throws BusinessException;

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 */
	void removeMenu(Integer menuId) throws BusinessException;

	/**
	 * 删除权限
	 * 
	 * @param permissionId
	 */
	void removePermission(Integer permissionId) throws BusinessException;

	/**
	 * 绑定角色和权限
	 * 
	 * @param bindInfo
	 * @throws BusinessException
	 */
	void bindRolePermission(RolePermission bindInfo) throws BusinessException;

	/**
	 * 绑定权限和菜单
	 * 
	 * @param bindInfo
	 * @throws BusinessException
	 */
	void bindPermissionMenu(PermissionMenu bindInfo) throws BusinessException;

	/**
	 * 绑定用户和角色
	 * 
	 * @param bindInfo
	 * @throws BusinessException
	 */
	void bindUserRole(UserRole bindInfo) throws BusinessException;

	/**
	 * 获取所有角色
	 * 
	 * @return
	 */
	List<Role> getAllRole();

	/**
	 * 获取菜单
	 * 
	 * @param menuId
	 * @return
	 * @throws BusinessException
	 */
	MenuVo getMenuById(Integer menuId) throws BusinessException;

	/**
	 * 
	 * 获取权限
	 * 
	 * @param permissionId
	 * @return
	 * @throws BusinessException
	 */
	Permission getPermissionById(Integer permissionId) throws BusinessException;

	/**
	 * @param permissionId
	 * @throws BusinessException
	 */
	void deletePermissionById(Integer permissionId) throws BusinessException;

	/**
	 * 
	 * 解除角色和权限的绑定
	 * 
	 * @param roleId
	 *            为空时，根据permissioId删除
	 * @param permissionId
	 *            为空时，根据roleId删除
	 * @throws BusinessException
	 */
	void unBindRolePermission(Integer roleId, Integer permissionId) throws BusinessException;

	/**
	 * 解除权限角色的绑定
	 * 
	 * @param menuId
	 * @param permissionId
	 * @throws BusinessException
	 */
	void unBindPermissionMenu(Integer permissionId, Integer menuId) throws BusinessException;

	/**
	 * 删除菜单以及该菜单的子菜单
	 * 
	 * @param menuId
	 * @throws BusinessException
	 */
	void deleteMenu(Integer menuId) throws BusinessException;

	/**
	 * 获取角色未绑定的权限
	 * 
	 * @param roleId
	 * @return
	 */
	Map<String, List<Permission>> getAllUnBindPermissionsByRoleId(Integer roleId);

	/**
	 * 获取角色未绑定的权限
	 * 
	 * @param roleId
	 * @return
	 */
	Map<String, List<Permission>> getAllBindPermissionsByRoleId(Integer roleId);

	/**
	 * 获取所有未绑定菜单的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getUnBindPermissions();

	/**
	 * 获取所有没有绑定菜单和綁定了指定菜单的菜单权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> getUnBindPermissionsByMenuId(Integer menuId);

	/**
	 * 根据用户id获取用户类型
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	Integer getUserTypeByUserId(String userId) throws BusinessException;
	

}
