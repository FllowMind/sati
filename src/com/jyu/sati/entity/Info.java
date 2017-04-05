package com.jyu.sati.entity;

import java.util.Date;

/**
 * 政策法规和通知公告的实体类
 * 
 * @author 淋雨又调皮
 *
 */
public class Info {

	public final static int UPLOAD_INFO_TYPE_POLICY = 1111;// 上传类型为政策法规
	public final static int UPLOAD_INFO_TYPE_NOTICE = 2222;// 上传类型为通知公告

	public final static int INFO_TYPE_POLICY = 1;// 政策法规类型
	public final static int INFO_TYPE_NOTICE = 2;// 通知公告类型

	public final static int INFO_STATUS_PUBLISH = 1;// 已发布状态
	public final static int INFO_STATUS_UNPUBLISH = -1;// 未发布状态

	private Integer infoId;
	private String infoTitle;// 标题
	private String infoKey;// 关键词
	private String infoContent;// 内容
	private Integer infoType;// 信息类型
	private Integer infoStatus;// 信息状态
	private String publisherId;// 发布人
	private Date publishTime;// 发布时间
	private String fileUrl;// 文件上传路径
	private String fileDesc;// 文件描述

	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public String getInfoKey() {
		return infoKey;
	}

	public void setInfoKey(String infoKey) {
		this.infoKey = infoKey;
	}

	public Integer getInfoType() {
		return infoType;
	}

	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
	}

	public Integer getInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(Integer infoStatus) {
		this.infoStatus = infoStatus;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getInfoContent() {
		return infoContent;
	}

	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
}