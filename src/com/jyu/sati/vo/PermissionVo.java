package com.jyu.sati.vo;

public class PermissionVo {

	private Integer fatherPermissionId;//父权限Id 

	private String permissionMark;//权限标记

	private Integer permissionLevel;//权限级别

	private String permissionName;//权限名称

	private String description;//描述
	
	private Integer roleId ;//所属角色id

	
	
	public Integer getFatherPermissionId() {
		return fatherPermissionId;
	}



	public void setFatherPermissionId(Integer fatherPermissionId) {
		this.fatherPermissionId = fatherPermissionId;
	}



	public String getPermissionMark() {
		return permissionMark;
	}



	public void setPermissionMark(String permissionMark) {
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



	public Integer getRoleId() {
		return roleId;
	}



	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}



	@Override
	public String toString() {
		return "PermissionVo [fatherPermissionId=" + fatherPermissionId + ", permissionMark=" + permissionMark
				+ ", permissionLevel=" + permissionLevel + ", permissionName=" + permissionName + ", description="
				+ description + ", roleId=" + roleId + "]";
	}
	
	
}
