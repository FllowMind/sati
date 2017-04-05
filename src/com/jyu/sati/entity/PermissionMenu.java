package com.jyu.sati.entity;

import java.util.Date;

public class PermissionMenu {
    private Integer permissionMunuId;

    private Integer permissionId;

    private Integer menuId;

    private Date createTime;

    public Integer getPermissionMunuId() {
        return permissionMunuId;
    }

    public void setPermissionMunuId(Integer permissionMunuId) {
        this.permissionMunuId = permissionMunuId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "PermissionMenu [permissionMunuId=" + permissionMunuId + ", permissionId=" + permissionId + ", menuId="
				+ menuId + ", createTime=" + createTime + "]";
	}
}