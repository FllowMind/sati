package com.jyu.sati.vo;

import java.util.Date;

/**
 * 技术需求和供给的部分信息（用于分页列表）
 * 
 * @author 淋雨又调皮
 *
 */
public class TechOutlineVo {

	private Integer triId;// 技术需求信息ID
	private Integer tsiId;// 技术供给信息ID
	private String infoTitle;// 信息名称
	private Integer industryId;// 所属行业
	private Integer locationId;// 所在地
	private Integer htfId;// 高新技术领域
	private Date createTime;// 发布时间
	private Integer status;// 信息状态
	private Float price;// 交易价格（万元）
	private String contactName;// 联系人名字
	private String phoneNumber;// 联系电话

	public Integer getTriId() {
		return triId;
	}

	public void setTriId(Integer triId) {
		this.triId = triId;
	}

	public Integer getTsiId() {
		return tsiId;
	}

	public void setTsiId(Integer tsiId) {
		this.tsiId = tsiId;
	}

	public String getInfoTitle() {
		return infoTitle;
	}

	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getHtfId() {
		return htfId;
	}

	public void setHtfId(Integer htfId) {
		this.htfId = htfId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "TechOutlineVo [triId=" + triId + ", tsiId=" + tsiId + ", infoTitle=" + infoTitle + ", industryId="
				+ industryId + ", locationId=" + locationId + ", htfId=" + htfId + ", createTime=" + createTime
				+ ", status=" + status + ", price=" + price + ", contactName=" + contactName + ", phoneNumber="
				+ phoneNumber + "]";
	}

}
