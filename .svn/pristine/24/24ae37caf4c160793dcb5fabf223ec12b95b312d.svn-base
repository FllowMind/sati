package com.jyu.sati.common.util;

import java.util.Date;

import com.jyu.sati.entity.SystemLog;

/**
 * 
 * 系统日志工具类
 * 
 * @author 淋雨又调皮
 *
 */
public class SystemLogUtil {

	/**
	 * 生成新的权限日志
	 * 
	 * @param logTitile
	 * @param logContent
	 * @return
	 */
	public static SystemLog getPermissionLog(String userId, String operation) {
		SystemLog log = new SystemLog();
		log.setSystemLogTitle("权限日志");
		log.setSystemLogContent("【" + userId + "】在" + getCurrentTime() + operation);
		log.setLogType(SystemLog.LOG_TYPE_PERMISSION_LOG);
		log.setSubmitTime(new Date());
		return log;
	}

	/**
	 * 生成新的菜单日志
	 * 
	 * @param userId
	 *            操作用户
	 * @param operation
	 *            操作
	 */
	public static SystemLog getMenuLog(String userId, String operation) {
		SystemLog log = new SystemLog();
		log.setSystemLogTitle("菜单日志");
		log.setSystemLogContent("【" + userId + "】在" + getCurrentTime() + operation);
		log.setLogType(SystemLog.LOG_TYPE_MENU_LOG);
		log.setSubmitTime(new Date());
		return log;
	}

	/**
	 * 生成新的审核用户日志
	 * 
	 * @param userId
	 *            操作用户
	 * @param operation
	 *            操作
	 */
	public static SystemLog getUserAuditLog(String userId, String operation) {
		SystemLog log = new SystemLog();
		log.setSystemLogTitle("审核用户信息日志");
		log.setSystemLogContent("【" + userId + "】在" + getCurrentTime() + operation);
		log.setLogType(SystemLog.LOG_TYPE_USER_AUDIT_LOG);
		log.setSubmitTime(new Date());
		return log;
	}
	/**
	 * 生成新的用户管理日志
	 * 
	 * @param userId
	 *            操作用户
	 * @param operation
	 *            操作
	 */
	public static SystemLog getUserManagmentLog(String userId, String operation) {
		SystemLog log = new SystemLog();
		log.setSystemLogTitle("审核用户信息日志");
		log.setSystemLogContent("【" + userId + "】在" + getCurrentTime() + operation);
		log.setLogType(SystemLog.LOG_TYPE_USER_MANAGMENT_LOG);
		log.setSubmitTime(new Date());
		return log;
	}

	/**
	 * 生成新的技术需求审核日志
	 * 
	 * @param userId
	 *            操作用户
	 * @param operation
	 *            操作
	 */
	public static SystemLog getTechRequireLog(String userId, String operation) {
		SystemLog log = new SystemLog();
		log.setSystemLogTitle("技术需求审核日志");
		log.setSystemLogContent("【" + userId + "】在" + getCurrentTime() + operation);
		log.setLogType(SystemLog.LOG_TYPE_TECH_REQUIRE_AUDIT_LOG);
		log.setSubmitTime(new Date());
		return log;
	}

	/**
	 * 生成新的技术供给审核日志
	 * 
	 * @param userId
	 *            操作用户
	 * @param operation
	 *            操作
	 */
	public static SystemLog getTechSupplyLog(String userId, String operation) {
		SystemLog log = new SystemLog();
		log.setSystemLogTitle("技术供给审核日志");
		log.setSystemLogContent("【" + userId + "】在" + getCurrentTime() + operation);
		log.setLogType(SystemLog.LOG_TYPE_TECH_SUPPLY_AUDIT_LOG);
		log.setSubmitTime(new Date());
		return log;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	private static String getCurrentTime() {
		return DateUtil.datetimeToString(new Date());
	}
}
