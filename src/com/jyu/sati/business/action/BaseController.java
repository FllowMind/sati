package com.jyu.sati.business.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.jyu.sati.common.util.AjaxResult;

/**
 * @author 淋雨又调皮
 *
 */
public class BaseController {
	public final static String HOMEPAGE = "homepage";// 主页
	public final static String LOGIN = "login";// 登录
	public final static String LOGOUT = "logout";// 注销
	public final static String REDIRECT_HOME = "redirect:/home/homepage";// 重定向到主页
	public final static String FORWORD_LOGIN = "forward:/login/login";// 跳转到登录界面

	public static Logger logger = Logger.getLogger("common");

	public Map<String, Object> session = new HashMap<>();

	public AjaxResult ajaxResult = null;

	public AjaxResult getAjaxResult() {
		ajaxResult = new AjaxResult();
		return ajaxResult;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	/**
	 * 检查是否有权限
	 * 
	 * @param permissionName
	 * @return 有权限时回true
	 */
	public boolean hasPermission(String permissionName) {
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.checkPermission(permissionName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 检查是否沒有有权限
	 * 
	 * @param permissionName
	 * @return 无权限时回true
	 */
	public boolean hasNotPermission(String permissionName) {
		return !hasPermission(permissionName);
	}

	/**
	 * 检查是否有角色
	 * 
	 * @param roleName
	 * @return 有角色时回true
	 */
	public boolean hasRole(String roleName) {
		Subject subject = SecurityUtils.getSubject();
		try {
			return subject.hasRole(roleName);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 检查是否没有有角色
	 * 
	 * @param roleName
	 *            角色名稱
	 * @return 无角色时回true
	 */
	public boolean hasNotRole(String roleName) {
		return !hasRole(roleName);
	}

	/**
	 * 检查是否登录
	 * 
	 * @return 已登录返回true
	 */
	public boolean checkIsLogin() {
		// String userId = getCurrentUserId();
		// if (userId == null || userId.equals("")) {
		// return false;
		// }
		// return true;
		return SecurityUtils.getSubject().isAuthenticated();
	}

	/**
	 * 检查是否未登陆，未登录放回true
	 * 
	 * @return
	 */
	public boolean checkIsNotLogin() {
		return !checkIsLogin();
	}

	/**
	 * 获取当前用户Id
	 * 
	 * @return
	 */
	public String getCurrentUserId() {
		Subject currentUser = SecurityUtils.getSubject();
		return (String) currentUser.getSession().getAttribute("currentUser");
	}

	/**
	 * 当前用户未登陆是返回的结果
	 * 
	 * @param result
	 * @return
	 */
	public AjaxResult getUnLoginResult(AjaxResult result) {
		result.setSuccess(false);
		result.setMsg("请登录！");
		return result;
	}

	/**
	 * 无权限时返回的结果
	 * 
	 * @param result
	 * @return
	 */
	public AjaxResult getNoPermissionResult(AjaxResult result) {
		result.setSuccess(false);
		result.setMsg("当前用户权限不足！");
		return result;
	}

	/**
	 * 校验角色失败时放回的结果
	 * 
	 * @param result
	 * @return
	 */
	public AjaxResult getNoRoleResult(AjaxResult result) {
		result.setSuccess(false);
		result.setMsg("当前用户角色无法进行该操作！");
		return result;
	}

	/**
	 * 获取错误结果
	 * 
	 * @param result
	 * @param msg
	 *            消息
	 * @return
	 */
	public AjaxResult getFailedRusult(AjaxResult result, String msg) {
		result.setSuccess(false);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 获取错误结果 提示为（操作失败！）
	 * 
	 * @param result
	 * @return
	 */
	public AjaxResult getFailedRusult(AjaxResult result) {
		result.setSuccess(false);
		result.setMsg("操作失败！");
		return result;
	}

	/**
	 * 获取成功结果 提示为（操作成功！）
	 * 
	 * @param result
	 * @return
	 */
	public AjaxResult getSuccessRusult(AjaxResult result) {
		result.setSuccess(true);
		result.setMsg("操作成功！");
		return result;
	}

	/**
	 * 获取成功结果
	 * 
	 * @param result
	 * @param msg
	 *            消息
	 * @return
	 */
	public AjaxResult getSuccessRusult(AjaxResult result, String msg) {
		result.setSuccess(true);
		result.setMsg(msg);
		return result;
	}

	/**
	 * 获取成功结果（单个数据） 提示为（获取数据成功！）
	 * 
	 * @param result
	 * @param rs
	 *            需要返回的结果
	 * @return
	 */
	public AjaxResult getSuccessRusult(AjaxResult result, Object res) {
		result.setSuccess(true);
		result.setMsg("获取数据成功！");
		result.setResult(res);
		return result;
	}

	/**
	 * 获取成功结果（单个数据）
	 * 
	 * @param result
	 * @param msg
	 *            消息
	 * @param rs
	 *            需要返回的结果
	 * @return
	 */
	public AjaxResult getSuccessRusult(AjaxResult result, String msg, Object res) {
		result.setSuccess(true);
		result.setMsg(msg);
		result.setResult(res);
		return result;
	}

	/**
	 * 获取成功结果（返回集合） * 提示为（获取数据成功！）
	 * 
	 * @param result
	 * @param resList
	 *            需要返回的结果集
	 * @return
	 */
	public AjaxResult getSuccessListRusult(AjaxResult result, Object resList) {
		result.setSuccess(true);
		result.setMsg("获取数据成功！");
		result.setResultList(resList);
		return result;
	}

	/**
	 * 获取成功结果（返回集合）
	 * 
	 * @param result
	 * @param msg
	 *            消息
	 * @param resList
	 *            需要返回的结果集
	 * @return
	 */
	public AjaxResult getSuccessListRusult(AjaxResult result, String msg, Object resList) {
		result.setSuccess(true);
		result.setMsg(msg);
		result.setResultList(resList);
		return result;
	}

}
