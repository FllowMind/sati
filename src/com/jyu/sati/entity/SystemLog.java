package com.jyu.sati.entity;

import java.util.Date;

/**
 * 系统日志实体类
 * 
 * @author 淋雨又调皮
 *
 */
public class SystemLog {

	public final static int LOG_TYPE_USER_AUDIT_LOG = 1;// 用户审核日志
	public final static int LOG_TYPE_TECH_SUPPLY_AUDIT_LOG = 2;// 技术供给审核日志
	public final static int LOG_TYPE_TECH_REQUIRE_AUDIT_LOG = 3;// 技术需求审核日志
	public final static int LOG_TYPE_PERMISSION_LOG = 4;//权限变动日志
	public final static int LOG_TYPE_MENU_LOG = 5;//菜单变动日志
	public final static int LOG_TYPE_USER_MANAGMENT_LOG = 6;//用户管理日志

	private Integer systemLogId;
	private String systemLogTitle;// 日志标题
	private String systemLogContent;// 日志内容
	private Date submitTime;// 时间发生时间
	private Integer logType;// 日志类型

	public Integer getSystemLogId() {
		return systemLogId;
	}

	public void setSystemLogId(Integer systemLogId) {
		this.systemLogId = systemLogId;
	}

	public String getSystemLogTitle() {
		return systemLogTitle;
	}

	public void setSystemLogTitle(String systemLogTitle) {
		this.systemLogTitle = systemLogTitle;
	}

	public String getSystemLogContent() {
		return systemLogContent;
	}

	public void setSystemLogContent(String systemLogContent) {
		this.systemLogContent = systemLogContent;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}
}