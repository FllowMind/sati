package com.jyu.sati.entity;

import java.util.Date;

public class Permission {

	public final static int PERMISSION_STATUS_LIMIT = -1;// 禁用
	public final static int PERMISSION_STATUS_NORMAL = 1;// 正常使用
	
	public final static int PERMISSION_MARK_MENU = 1;// 标记 菜单权限
	public final static int PERMISSION_MARK_GENERAL = 2;// 标记 普通权限

	private Integer permissionId;//权限id

	private Integer fatherPermissionId;//父权限id

	private Integer permissionMark;//权限类型标记

	private Integer permissionLevel;//权限级别

	private String permissionName;//权限名称

	private String description;//权限描述

	private Integer status;//权限状态

	private Date createTime;//权限创建时间

	private Date operateTime;//权限操作时间

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getFatherPermissionId() {
		return fatherPermissionId;
	}

	public void setFatherPermissionId(Integer fatherPermissionId) {
		this.fatherPermissionId = fatherPermissionId;
	}

	public Integer getPermissionMark() {
		return permissionMark;
	}

	public void setPermissionMark(Integer permissionMark) {
		this.permissionMark = permissionMark;
	}

	public Integer getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(Integer permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", fatherPermissionId=" + fatherPermissionId
				+ ", permissionMark=" + permissionMark + ", permissionLevel=" + permissionLevel + ", permissionName="
				+ permissionName + ", description=" + description + ", status=" + status + ", createTime=" + createTime
				+ ", operateTime=" + operateTime + "]";
	}

}