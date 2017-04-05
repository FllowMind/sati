package com.jyu.sati.vo;

import java.util.Date;

/**
 * 技术需求信息vo
 * 
 * @author 淋雨又调皮
 *
 */
public class TechRequireInfoVo {

	// 从technology_requirement_infos 表查数据
	private Integer triId; // 技术需求信息ID
	private Float investmentFunds;// 欲投资资金（万元）
	private Integer timeLimit;// 解决时限
	private String problemDescription;// 难题描述

	// technology_base_infos
	private Integer tbiId;// 技术基本信息ID
	private String infoTitle;// 信息名称
	private String infoKey;// 关键字
	private Integer industryId;// 所属行业
	private Integer htfId;// 高新技术领域
	private Integer locationId;// 所在地
	private Integer sciId;// 学科分类
	private Integer seIndustryId;// 战略新兴产业
	private Integer sourceTypeId;// 来源类型
	private Integer cooperationModeId;// 合作方式
	private Integer status;// 信息状态
	private Date createTime;// 发布时间
	private String publisherId;// 发布人
	private Integer viewTimes = 0;// 浏览次数
	private Integer limitStatus;// 信息是否被限制（管理员使用）

	// 从contact_infos 表查询数据
	private Integer contactInfoId;// 联系信息id
	private String contactName;// 联系人名字
	private String contactBusiness;// 联系人职务
	private String contactNumber;// 联系电话
	private String contactAddress;// 联系地址
	private String postcode;// 邮编
	private String email;// 联系人邮箱
	private String contactUrl;// 网址
	private String faxNumber;// 传真
	private String qqormsnNumer;// 联系人的QQ或MSN号码
	private String phoneNumber;// 手机号码

	// 从audit_infos 表查查数据
	private Integer auditInfoId;// 审核id
	private Date submitTime;// 提交时间
	private Date auditTime;// 审核时间
	private Integer auditStatus;// 审核状态
	private String auditResult;// 审核结果
	private String userId;// 被审核人
	private String userName;// 审核

	public Integer getLimitStatus() {
		return limitStatus;
	}

	public void setLimitStatus(Integer limitStatus) {
		this.limitStatus = limitStatus;
	}

	public Integer getTriId() {
		return triId;
	}

	public void setTriId(Integer triId) {
		this.triId = triId;
	}

	public Float getInvestmentFunds() {
		return investmentFunds;
	}

	public void setInvestmentFunds(Float investmentFunds) {
		this.investmentFunds = investmentFunds;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public Integer getTbiId() {
		return tbiId;
	}

	public void setTbiId(Integer tbiId) {
		this.tbiId = tbiId;
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

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getHtfId() {
		return htfId;
	}

	public void setHtfId(Integer htfId) {
		this.htfId = htfId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getSciId() {
		return sciId;
	}

	public void setSciId(Integer sciId) {
		this.sciId = sciId;
	}

	public Integer getSeIndustryId() {
		return seIndustryId;
	}

	public void setSeIndustryId(Integer seIndustryId) {
		this.seIndustryId = seIndustryId;
	}

	public Integer getSourceTypeId() {
		return sourceTypeId;
	}

	public void setSourceTypeId(Integer sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}

	public Integer getCooperationModeId() {
		return cooperationModeId;
	}

	public void setCooperationModeId(Integer cooperationModeId) {
		this.cooperationModeId = cooperationModeId;
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

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(Integer viewTimes) {
		this.viewTimes = viewTimes;
	}

	public Integer getContactInfoId() {
		return contactInfoId;
	}

	public void setContactInfoId(Integer contactInfoId) {
		this.contactInfoId = contactInfoId;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactBusiness() {
		return contactBusiness;
	}

	public void setContactBusiness(String contactBusiness) {
		this.contactBusiness = contactBusiness;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactUrl() {
		return contactUrl;
	}

	public void setContactUrl(String contactUrl) {
		this.contactUrl = contactUrl;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getQqormsnNumer() {
		return qqormsnNumer;
	}

	public void setQqormsnNumer(String qqormsnNumer) {
		this.qqormsnNumer = qqormsnNumer;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getAuditInfoId() {
		return auditInfoId;
	}

	public void setAuditInfoId(Integer auditInfoId) {
		this.auditInfoId = auditInfoId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "TechRequireInfoVo [triId=" + triId + ", investmentFunds=" + investmentFunds + ", timeLimit=" + timeLimit
				+ ", problemDescription=" + problemDescription + ", tbiId=" + tbiId + ", infoTitle=" + infoTitle
				+ ", infoKey=" + infoKey + ", industryId=" + industryId + ", htfId=" + htfId + ", locationId="
				+ locationId + ", sciId=" + sciId + ", seIndustryId=" + seIndustryId + ", sourceTypeId=" + sourceTypeId
				+ ", cooperationModeId=" + cooperationModeId + ", status=" + status + ", createTime=" + createTime
				+ ", publisherId=" + publisherId + ", viewTimes=" + viewTimes + ", limitStatus=" + limitStatus
				+ ", contactInfoId=" + contactInfoId + ", contactName=" + contactName + ", contactBusiness="
				+ contactBusiness + ", contactNumber=" + contactNumber + ", contactAddress=" + contactAddress
				+ ", postcode=" + postcode + ", email=" + email + ", contactUrl=" + contactUrl + ", faxNumber="
				+ faxNumber + ", qqormsnNumer=" + qqormsnNumer + ", phoneNumber=" + phoneNumber + ", auditInfoId="
				+ auditInfoId + ", submitTime=" + submitTime + ", auditTime=" + auditTime + ", auditStatus="
				+ auditStatus + ", auditResult=" + auditResult + ", userId=" + userId + ", userName=" + userName + "]";
	}

}
