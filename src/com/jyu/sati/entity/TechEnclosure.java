package com.jyu.sati.entity;

public class TechEnclosure {

	public final static int ENCLOSURE_TYPE_IMAGE = 11;// 图片附件
	public final static int ENCLOSURE_TYPE_VIDEO = 12;// 视频附件
	public final static int ENCLOSURE_TYPE_TEXT = 13;// 文本附件

	private Integer enclosureId;// 获取附件id
	private String enclosureUrl;// 附件url
	private String enclosureDesc;// 附件描述
	private Integer enclosureType;// 附件类型
	private Integer bindId;// 绑定的id

	public Integer getEnclosureId() {
		return enclosureId;
	}

	public void setEnclosureId(Integer enclosureId) {
		this.enclosureId = enclosureId;
	}

	public String getEnclosureUrl() {
		return enclosureUrl;
	}

	public void setEnclosureUrl(String enclosureUrl) {
		this.enclosureUrl = enclosureUrl;
	}

	public String getEnclosureDesc() {
		return enclosureDesc;
	}

	public void setEnclosureDesc(String enclosureDesc) {
		this.enclosureDesc = enclosureDesc;
	}

	public Integer getEnclosureType() {
		return enclosureType;
	}

	public void setEnclosureType(Integer enclosureType) {
		this.enclosureType = enclosureType;
	}

	public Integer getBindId() {
		return bindId;
	}

	public void setBindId(Integer bindId) {
		this.bindId = bindId;
	}

	@Override
	public String toString() {
		return "TechEnclosure [enclosureId=" + enclosureId + ", enclosureUrl=" + enclosureUrl + ", enclosureDesc="
				+ enclosureDesc + ", enclosureType=" + enclosureType + ", bindId=" + bindId + "]";
	}

}