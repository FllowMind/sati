package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Menu;

/**
 * @author 淋雨又调皮
 *
 */
public interface MenuDao {
	int deleteByPrimaryKey(Integer menuId);

	int insert(Menu record);

	int insertSelective(Menu record);

	Menu selectByPrimaryKey(Integer menuId);

	int updateByPrimaryKeySelective(Menu record);

	int updateByPrimaryKey(Menu record);

	/**
	 * 通过权限id获取菜单
	 * 
	 * @param permissionId
	 * @return
	 */
	List<Menu> getMenuByPermissionId(@Param("permissionId") Integer permissionId);

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 */
	List<Menu> getAllMenuByLevel(@Param("level") Integer level);

	/**
	 * 更新指定菜单状态 1或 -1
	 * 
	 * @return
	 */
	int updateMenuStatus(@Param("menuId") Integer menuId);

	/**
	 * 获取所有的子菜单
	 * 
	 * @param fatherId
	 * @return
	 */
	List<Menu> getAllChildMenus(@Param("fatherId") Integer fatherId);

	/**
	 * 获取指定菜单的状态
	 * @param menuId
	 * @return
	 */
	Integer getMenuStatus(@Param("menuId") Integer menuId);

	/**
	 * 更新所有子菜单的状态为父菜单的状态
	 * @param fatherId
	 * @return
	 */
	int updateChildStatus(@Param("fatherId") Integer fatherId,@Param("menuStatus")Integer menuStatus);
	
	/**
	 * 解除父菜单和子菜单的绑定
	 * @param fathterId
	 * @return
	 */
	int unBindWithFatherMenu(@Param("fatherId") Integer fathterId);
	
	/**
	 * 删除指定父菜单下的所有子菜单
	 * @param fatherId
	 * @return
	 */
	int deleteAllChildMenus(@Param("fatherId" )Integer fatherId);
	
}