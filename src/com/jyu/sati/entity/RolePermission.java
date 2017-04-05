package com.jyu.sati.entity;

import java.util.Date;

public class RolePermission {
    private Integer rolePermissionId;

    private Integer roleId;

    private Integer permissionId;

    private Date createTime;

    public Integer getRolePermissionId() {
        return rolePermissionId;
    }

    public void setRolePermissionId(Integer rolePermissionId) {
        this.rolePermissionId = rolePermissionId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "RolePermission [rolePermissionId=" + rolePermissionId + ", roleId=" + roleId + ", permissionId="
				+ permissionId + ", createTime=" + createTime + "]";
	}
    
    
}