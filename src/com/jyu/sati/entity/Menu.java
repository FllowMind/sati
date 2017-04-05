package com.jyu.sati.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单实体类
 * 
 * @author 淋雨又调皮
 *
 */
public class Menu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public final static int MENU_STATUS_LIMIT = -1;//禁用状态
	public final static int MENU_STATUS_NORMAL = 1;//正常使用状态
	

	private Integer menuId;

	private Integer fatherMenuId;//上级菜单id

	private String menuName;//菜单名称

	private Integer menuLevel;//菜单级别

	private Integer menuOrder;//菜单排序

	private String linkUrl;//关联的url

	private Integer status;//菜单状态

	private Date createTime;//创建时间

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", fatherMenuId=" + fatherMenuId + ", menuName=" + menuName + ", menuLevel="
				+ menuLevel + ", menuOrder=" + menuOrder + ", linkUrl=" + linkUrl + ", status=" + status
				+ ", createTime=" + createTime + "]";
	}

}