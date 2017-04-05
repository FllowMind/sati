package com.jyu.sati.business.action.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.action.BaseController;
import com.jyu.sati.business.service.PermissionService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.entity.Menu;
import com.jyu.sati.entity.Permission;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.RolePermission;

/**
 * 权限控制器
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/perm")
@Controller
public class PermissonController extends BaseController {

	@Autowired
	private PermissionService permService;

	/**
	 * 功能描述：添加权限 。 需要传入参数： permissionId;权限ID fatherPermissionId;父权限Id
	 * permissionMark;权限标记 permissionLevel;权限级别 permissionName;权限名称
	 * description;权限描述 status;权限状态 createTime;创建时间(不显示)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addPermission", method = { RequestMethod.POST })
	public AjaxResult addPerminssion(@RequestBody Permission permission) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		if (StringUtil.isEmpty(permission.getDescription()) || permission.getPermissionLevel() == null
				|| permission.getPermissionName() == null || permission.getStatus() == null) {
			return getFailedRusult(result, "必要字段为空,添加权限失败！");
		}
		try {
			permService.addPermission(permission);
			result.setSuccess(true);
			result.setMsg("添加权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("添加权限失败！");
		}
		return result;
	}

	/**
	 * 功能描述：更新权限 需要传入参数： permissionId;权限ID fatherPermissionId;父权限Id
	 * permissionMark;权限标记 permissionLevel;权限级别 permissionName;权限名称
	 * description;权限描述 status;权限状态 createTime;创建时间(不显示)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePermission", method = { RequestMethod.PUT })
	public AjaxResult updatePermission(@RequestBody Permission permission) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}

		try {
			if (permService.updatePermission(permission) > 0) {
				result.setSuccess(true);
				result.setMsg("更新权限成功！");
			} else {
				result.setSuccess(false);
				result.setMsg("更新权限失败！");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("更新权限失败！");
		}

		return result;
	}

	/**
	 * 功能描述：更新指定权限状态。 需要传入参数： permissionId;
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePermissionStatus", method = { RequestMethod.PUT })
	public AjaxResult updatePermissionStatus(@RequestBody Permission permission) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}

		try {
			if (permService.updatePermissionStatus(permission.getPermissionId()) > 0) {
				result.setSuccess(true);
				result.setMsg("更新权限状态成功！");
			} else {
				result.setSuccess(false);
				result.setMsg("更新权限状态失败！");
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("更新权限状态失败！");
		}

		return result;
	}

	/**
	 * 功能描述： 绑定角色权限。 需要传入参数： roleId ;角色ID permissionId;权限ID
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bindRolePermission", method = { RequestMethod.POST })
	public AjaxResult bindRolePermission(@RequestBody RolePermission bindInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}

		try {
			permService.bindRolePermission(bindInfo);
			result.setSuccess(true);
			result.setMsg("角色绑定权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}

		return result;
	}

	/**
	 * 功能描述：获取所有角色。
	 * 
	 * @return resultList
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllRoles", method = { RequestMethod.GET })
	public AjaxResult getAllRoles() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResult(permService.getAllRole());
			result.setSuccess(true);
			result.setMsg("获取所有角色成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}

		return result;
	}

	/**
	 * 功能描述：获取某个权限。 需要传入参数：permissionId
	 * 
	 * @return resultList
	 */
	@ResponseBody
	@RequestMapping(value = "/getPermission", method = { RequestMethod.POST })
	public AjaxResult getPermission(@RequestBody Permission permission) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResult(permService.getPermissionById(permission.getPermissionId()));
			result.setSuccess(true);
			result.setMsg("获取权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取权限失败");
		}

		return result;
	}

	/**
	 * 功能描述：获取某个权限。 需要传入参数：permissionId
	 * 
	 * @return resultList
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllPermissions", method = { RequestMethod.GET })
	public AjaxResult getAllPermissions() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getAllPermissions());
			result.setSuccess(true);
			result.setMsg("获取所有权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取所有权限失败");
		}

		return result;
	}

	/**
	 * 功能描述：删除权限 。 需要传入参数： permissionId
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePermission", method = { RequestMethod.DELETE })
	public AjaxResult deletePerminssion(@RequestBody Permission permission) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			permService.deletePermissionById(permission.getPermissionId());
			result.setSuccess(true);
			result.setMsg("删除权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("删除权限失败！");
		}
		return result;
	}

	/**
	 * 功能描述：解除角色权限绑定 。 需要传入参数： permissionId 和 roleId 必须传两个
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unBindRolePerminssion", method = { RequestMethod.DELETE })
	public AjaxResult unBindRolePerminssion(@RequestBody RolePermission rolePermission) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			permService.unBindRolePermission(rolePermission.getRoleId(), rolePermission.getPermissionId());
			result.setSuccess(true);
			result.setMsg("解除权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("解除权限失败！");
		}
		return result;
	}

	/**
	 * 获取该角色绑定的权限
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllPermissionsByRole", method = { RequestMethod.POST })
	public AjaxResult getAllPermissionsByRole(@RequestBody Role role) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getAllPermissionsByRole(role.getRoleId()));
			result.setSuccess(true);
			result.setMsg("获取该角色所有权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取该角色所有权限失败！");
		}

		return result;
	}

	/**
	 * 获取该角色未绑定的权限
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllUnBindPermissionsByRole")
	public AjaxResult getAllUnBindPermissionsByRole(@RequestBody Role role) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getAllUnBindPermissionsByRoleId(role.getRoleId()));
			result.setSuccess(true);
			result.setMsg("获取角色所有未绑定权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取角色所有未绑定权限成功！");
		}

		return result;
	}

	/**
	 * 获取该角色绑定的权限
	 * 
	 * @param roleId
	 *            角色Id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllBindPermissionsByRole")
	public AjaxResult getAllBindPermissionsByRole(@RequestBody Role role) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getAllBindPermissionsByRoleId(role.getRoleId()));
			result.setSuccess(true);
			result.setMsg("获取角色所有未绑定权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取角色所有未绑定权限成功！");
		}

		return result;
	}

	/**
	 * 获取所有没有绑定菜单的权限
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUnBindPermissions", method = { RequestMethod.GET })
	public AjaxResult getUnBindPermissions() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getUnBindPermissions());
			result.setSuccess(true);
			result.setMsg("获取所有未绑定权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取所有未绑定权限失败！");
		}

		return result;
	}

	/**
	 * 获取未绑定的权限和綁定了指定菜单的权限
	 * 
	 * @param menuId
	 *            修改菜单的Id 为空时不指定菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUnBindPermissionsByMenuId", method = { RequestMethod.POST })
	public AjaxResult getUnBindPermissionsByMenuId(@RequestBody Menu menu) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getUnBindPermissionsByMenuId(menu.getMenuId()));
			result.setSuccess(true);
			result.setMsg("获取权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取权限失败！");
		}

		return result;
	}

	/**
	 * 获取指定级别的所有权限
	 * 
	 * @param permissionLevel
	 *            权限级别
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPermissionByLevel", method = { RequestMethod.POST })
	public AjaxResult getPermissionByLevel(@RequestBody Permission perm) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result = getUnLoginResult(result);
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result = getNoRoleResult(result);
			return result;
		}
		try {
			result.setResultList(permService.getPermissionsByLevel(perm.getPermissionLevel()));
			result.setSuccess(true);
			result.setMsg("获取权限成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取权限失败！");
		}

		return result;
	}

}
