package com.jyu.sati.vo;

public class MenuVo {

	private Integer menuId;// 菜单Id

	private Integer fatherMenuId;// 上级菜单id

	private String menuName;// 菜单名称

	private Integer menuLevel;// 菜单级别

	private Integer menuOrder;// 菜单排序

	private String linkUrl;// 关联的url

	private Integer status;// 菜单状态

	private Integer bindPerminssionId;// 绑定的权限id

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getFatherMenuId() {
		return fatherMenuId;
	}

	public void setFatherMenuId(Integer fatherMenuId) {
		this.fatherMenuId = fatherMenuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBindPerminssionId() {
		return bindPerminssionId;
	}

	public void setBindPerminssionId(Integer bindPerminssionId) {
		this.bindPerminssionId = bindPerminssionId;
	}

	@Override
	public String toString() {
		return "MenuVo [menuId=" + menuId + ", fatherMenuId=" + fatherMenuId + ", menuName=" + menuName + ", menuLevel="
				+ menuLevel + ", menuOrder=" + menuOrder + ", linkUrl=" + linkUrl + ", status=" + status
				+ ", bindPerminssionId=" + bindPerminssionId + "]";
	}

}
