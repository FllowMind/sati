package com.jyu.sati.business.action.permission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.action.BaseController;
import com.jyu.sati.business.service.PermissionService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.entity.Menu;
import com.jyu.sati.entity.PermissionMenu;
import com.jyu.sati.vo.MenuVo;

/**
 * 菜单控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/menu")
@Controller
public class MenuController extends BaseController {

	@Autowired
	private PermissionService permService;

	/**
	 * 获取登录用户的菜单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/menuList", method = { RequestMethod.GET })
	public AjaxResult getMenusByLoginUser() {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		Map<String, List<Menu>> menus = new HashMap<String, List<Menu>>();
		Subject currentUser = SecurityUtils.getSubject();
		// 获取用户id
		String userId = (String) currentUser.getSession().getAttribute("currentUser");
		try {
			menus = permService.getMenusByUserId(userId);// 获取用户对应的菜单
			result.setSuccess(true);
			result.setMsg("获取菜单成功！");
			result.setResultList(menus);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("获取菜单失败！");
		}
		return result;
	}

	/**
	 * 获取所有菜单
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listAllMenus", method = { RequestMethod.GET })
	public AjaxResult getAllMenus() {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:read_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}

		try {
			result.setResultList(permService.getAllMenu());
			result.setSuccess(true);
			result.setMsg("获取菜单成功！");
		} catch (Exception e) {
			logger.error("获取菜单失败！原因：" + e.getMessage());
			result.setSuccess(false);
			result.setMsg("无法获取菜单！");
		}
		return result;
	}

	/**
	 * 获取指定级别的所有菜单
	 * 
	 * @param menuLevel
	 *            指定菜单级别
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAllMenusByLevel", method = { RequestMethod.POST })
	public AjaxResult getAllMenusByLevel(@RequestBody Menu menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:read_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}

		try {
			result = getSuccessListRusult(result, permService.getMenuByLevel(menu.getMenuLevel()));
		} catch (Exception e) {
			logger.error("获取菜单失败！原因：" + e.getMessage());
			result.setSuccess(false);
			result.setMsg("无法获取菜单！");
		}
		return result;
	}

	/**
	 * 功能描述：获取指定菜单的子菜单 需要传入的参数： menuId 指定菜单的Id
	 * 
	 * @param level
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildMenus", method = { RequestMethod.POST })
	public AjaxResult getChildMenus(@RequestBody Menu menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:read_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		try {
			result.setResultList(permService.getAllChildMenus(menu.getMenuId()));
			result.setSuccess(true);
			result.setMsg("获取子菜单成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("无法获取子菜单！");
		}
		return result;
	}

	/**
	 * 添加菜单
	 * 
	 * @param fatherMenuId
	 *            上级菜单id @param menuName;// 菜单名称
	 * 
	 * @param menuLevel
	 *            菜单级别
	 * 
	 * @param menuOrder
	 *            菜单排序
	 * 
	 * @param linkUrl
	 *            关联的url
	 * 
	 * @param status
	 *            菜单状态
	 * 
	 * @param bindPerminssionId
	 *            绑定的权限id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addMenu", method = { RequestMethod.POST })
	public AjaxResult addMenu(@RequestBody MenuVo menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:add_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		if (menu.getLinkUrl() != null && !menu.getLinkUrl().equals("") && menu.getMenuLevel() != null
				&& menu.getMenuOrder() != null && menu.getMenuName() != null && !menu.getMenuName().equals("")) {
			if (menu.getBindPerminssionId() == null) {
				result.setSuccess(false);
				result.setMsg("添加菜单失败！没有绑定权限！");
				return result;
			}
			try {
				permService.addMenu(menu);
				result.setSuccess(true);
				result.setMsg("添加菜单成功！");
			} catch (Exception e) {
				result.setSuccess(false);
				result.setMsg(e.getMessage());
			}
		} else {
			result.setSuccess(false);
			result.setMsg("添加菜单失败！菜单必要字段不能为空！");
		}
		return result;
	}

	/**
	 * 更新菜单
	 * 
	 * @param fatherMenuId
	 *            上级菜单id @param menuName;// 菜单名称
	 * 
	 * @param menuLevel
	 *            菜单级别
	 * 
	 * @param menuOrder
	 *            菜单排序
	 * 
	 * @param linkUrl
	 *            关联的url
	 * 
	 * @param status
	 *            菜单状态
	 * 
	 * @param bindPerminssionId
	 *            绑定的权限id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateMenu", method = { RequestMethod.PUT })
	public AjaxResult updateMenu(@RequestBody MenuVo menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:update_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		if (menu.getLinkUrl() != null && !menu.getLinkUrl().equals("") && menu.getMenuLevel() != null
				&& menu.getMenuOrder() != null && menu.getMenuName() != null && !menu.getMenuName().equals("")) {
			try {
				permService.updateMenu(menu);
				result.setSuccess(true);
				result.setMsg("成功更新菜单！");
			} catch (Exception e) {
				// TODO: handle exception
				result.setSuccess(false);
				result.setMsg(e.getMessage());
			}
		} else {
			result.setSuccess(false);
			result.setMsg("更新菜单失败！菜单不能为空");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/updateMenuStatus", method = { RequestMethod.PUT })
	public AjaxResult updateMenuStatus(@RequestBody Menu menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		// 检查权限
		if (!hasPermission("menu:update_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		try {
			permService.updateMenuStatus(menu.getMenuId());
			result.setSuccess(true);
			result.setMsg("更新状态成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteMenu", method = { RequestMethod.DELETE })
	public AjaxResult deleteMenu(@RequestBody Menu menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:delete_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		try {
			permService.deleteMenu(menu.getMenuId());
			result.setSuccess(true);
			result.setMsg("删除菜单成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	/**
	 * 获取指定菜单
	 * 
	 * @param menuId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMenu", method = { RequestMethod.POST })
	public AjaxResult getMenu(@RequestBody Menu menu) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		if (!hasPermission("menu:read_menu")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			logger.info("缺少权限：【menu:read_menu】");
			return result;
		}
		try {
			result.setResult(permService.getMenuById(menu.getMenuId()));
			result.setSuccess(true);
			result.setMsg("获取菜单成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取菜单失败！");
		}

		return result;
	}

	/**
	 * 功能描述： 绑定菜单权限。 需要传入参数： menuId ;菜单ID permissionId;权限ID
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/bindPermissionMenu", method = { RequestMethod.POST })
	public AjaxResult bindPermissionMenu(@RequestBody PermissionMenu bindInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		try {
			permService.bindPermissionMenu(bindInfo);
			result.setSuccess(true);
			result.setMsg("绑定权限菜单成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}

		return result;
	}

	/**
	 * 功能描述： 解除菜单权限绑定。 需要传入参数： menuId菜单ID 和 permissionId;权限ID ,必须同时传入两个
	 * 
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/unBindPermissionMenu", method = { RequestMethod.POST })
	public AjaxResult unBindPermissionMenu(@RequestBody PermissionMenu bindInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			result.setSuccess(false);
			result.setMsg("请登录！");
			return result;
		}
		// 检查是否有权限
		if (!hasRole("super_admin")) {
			result.setSuccess(false);
			result.setMsg("当前用户权限不足！");
			return result;
		}
		try {
			permService.unBindPermissionMenu(bindInfo.getMenuId(), bindInfo.getPermissionId());
			result.setSuccess(true);
			result.setMsg("解除权限菜单绑定成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}

		return result;
	}
}
