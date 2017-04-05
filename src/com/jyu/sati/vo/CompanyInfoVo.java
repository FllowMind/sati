package com.jyu.sati.vo;

import java.util.Date;

/**
 * 企业信息Vo
 * 
 * @author 淋雨又调皮
 *
 */
public class CompanyInfoVo {

	private Integer companyId;// 企业信息id
	private Integer companyNatureId;// 企业性质
	private Float registeredCapital;// 注册资金（万元）
	private String registeredAddress;// 注册地址
	private Integer industryId;// 所属行业
	private Integer circOrNot;// 是否国家创新驿站
	private Integer cttOrNot;// 是否国家技术转移
	// 单位基本情况
	private Integer baseInfoId;// 单位基本id
	private String unitName;// 单位名称
	private String unitAbbreviation;// 单位简称
	private Date establishmentDate;// 成立日期
	private String legalRepresentative;// 法人代表
	private String unitAddress;// 单位地址
	private Integer locationId;// 所在地Id
	private String key;// 被搜索的关键字
	private Integer unitCodeType;// 单位机构代码类型
	private String unitCode;// 组织机构代码或 统一社会信用代码
	private String unitCodeImageUrl;// 组织机构代码证图片或统一社会信用存放路径
	private String unitProfile;// 单位简介
	private String businessField;// 业务领域
	private String remark;// 备注

	// 用来单位从业人员情况
	private Integer soeId;// 单位从业人员情况Id
	private Integer totalNumber;// 人员总数
	private Integer developerNumber;// 研发人员数
	private Integer juniorNumber;// 初级人员人数
	private Integer intermediateNumber;// 中级职称人员人数
	private Integer seniorNumber;// 高级职称人员人数
	private Integer juniorCollegeNumber;// 大专学历人数
	private Integer undergraduateNumber;// 本科学历人数
	private Integer masterNumber;// 硕士学历人数
	private Integer doctorNumber;// 博士学历人数
	private Integer overseasNumber;// 海归人数
	private Integer academicianNunber;// 院士人数

	private Integer contactId;// 联系人信息id
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

	private Integer auditInfoId;// 审核信息ID
	private Date submitTime;// 提交时间
	private Date auditTime;// 审核时间
	private Integer auditStatus;// 审核状态
	private String auditResult;// 审核结果
	private String userId;// 被审核人
	private String userName;// 审核

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getCompanyNatureId() {
		return companyNatureId;
	}

	public void setCompanyNatureId(Integer companyNatureId) {
		this.companyNatureId = companyNatureId;
	}

	public Float getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(Float registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public Integer getCircOrNot() {
		return circOrNot;
	}

	public void setCircOrNot(Integer circOrNot) {
		this.circOrNot = circOrNot;
	}

	public Integer getCttOrNot() {
		return cttOrNot;
	}

	public void setCttOrNot(Integer cttOrNot) {
		this.cttOrNot = cttOrNot;
	}

	public Integer getSoeId() {
		return soeId;
	}

	public void setSoeId(Integer soeId) {
		this.soeId = soeId;
	}

	public Integer getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(Integer baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitAbbreviation() {
		return unitAbbreviation;
	}

	public void setUnitAbbreviation(String unitAbbreviation) {
		this.unitAbbreviation = unitAbbreviation;
	}

	public Date getEstablishmentDate() {
		return establishmentDate;
	}

	public void setEstablishmentDate(Date establishmentDate) {
		this.establishmentDate = establishmentDate;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getUnitAddress() {
		return unitAddress;
	}

	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getUnitCodeType() {
		return unitCodeType;
	}

	public void setUnitCodeType(Integer unitCodeType) {
		this.unitCodeType = unitCodeType;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getUnitCodeImageUrl() {
		return unitCodeImageUrl;
	}

	public void setUnitCodeImageUrl(String unitCodeImageUrl) {
		this.unitCodeImageUrl = unitCodeImageUrl;
	}

	public String getUnitProfile() {
		return unitProfile;
	}

	public void setUnitProfile(String unitProfile) {
		this.unitProfile = unitProfile;
	}

	public String getBusinessField() {
		return businessField;
	}

	public void setBusinessField(String businessField) {
		this.businessField = businessField;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getContactId() {
		return contactId;
	}

	public void setContactId(Integer contactId) {
		this.contactId = contactId;
	}

	public Integer getAuditInfoId() {
		return auditInfoId;
	}

	public void setAuditInfoId(Integer auditInfoId) {
		this.auditInfoId = auditInfoId;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public Integer getDeveloperNumber() {
		return developerNumber;
	}

	public void setDeveloperNumber(Integer developerNumber) {
		this.developerNumber = developerNumber;
	}

	public Integer getJuniorNumber() {
		return juniorNumber;
	}

	public void setJuniorNumber(Integer juniorNumber) {
		this.juniorNumber = juniorNumber;
	}

	public Integer getIntermediateNumber() {
		return intermediateNumber;
	}

	public void setIntermediateNumber(Integer intermediateNumber) {
		this.intermediateNumber = intermediateNumber;
	}

	public Integer getSeniorNumber() {
		return seniorNumber;
	}

	public void setSeniorNumber(Integer seniorNumber) {
		this.seniorNumber = seniorNumber;
	}

	public Integer getJuniorCollegeNumber() {
		return juniorCollegeNumber;
	}

	public void setJuniorCollegeNumber(Integer juniorCollegeNumber) {
		this.juniorCollegeNumber = juniorCollegeNumber;
	}

	public Integer getUndergraduateNumber() {
		return undergraduateNumber;
	}

	public void setUndergraduateNumber(Integer undergraduateNumber) {
		this.undergraduateNumber = undergraduateNumber;
	}

	public Integer getMasterNumber() {
		return masterNumber;
	}

	public void setMasterNumber(Integer masterNumber) {
		this.masterNumber = masterNumber;
	}

	public Integer getDoctorNumber() {
		return doctorNumber;
	}

	public void setDoctorNumber(Integer doctorNumber) {
		this.doctorNumber = doctorNumber;
	}

	public Integer getOverseasNumber() {
		return overseasNumber;
	}

	public void setOverseasNumber(Integer overseasNumber) {
		this.overseasNumber = overseasNumber;
	}

	public Integer getAcademicianNunber() {
		return academicianNunber;
	}

	public void setAcademicianNunber(Integer academicianNunber) {
		this.academicianNunber = academicianNunber;
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
		return "CompanyInfoVo [companyId=" + companyId + ", companyNatureId=" + companyNatureId + ", registeredCapital="
				+ registeredCapital + ", registeredAddress=" + registeredAddress + ", industryId=" + industryId
				+ ", circOrNot=" + circOrNot + ", cttOrNot=" + cttOrNot + ", baseInfoId=" + baseInfoId + ", unitName="
				+ unitName + ", unitAbbreviation=" + unitAbbreviation + ", establishmentDate=" + establishmentDate
				+ ", legalRepresentative=" + legalRepresentative + ", unitAddress=" + unitAddress + ", locationId="
				+ locationId + ", key=" + key + ", unitCodeType=" + unitCodeType + ", unitCode=" + unitCode
				+ ", unitCodeImageUrl=" + unitCodeImageUrl + ", unitProfile=" + unitProfile + ", businessField="
				+ businessField + ", remark=" + remark + ", soeId=" + soeId + ", totalNumber=" + totalNumber
				+ ", developerNumber=" + developerNumber + ", juniorNumber=" + juniorNumber + ", intermediateNunber="
				+ intermediateNumber + ", seniorNumber=" + seniorNumber + ", juniorCollegeNumber=" + juniorCollegeNumber
				+ ", undergraduateNumber=" + undergraduateNumber + ", masterNumber=" + masterNumber + ", doctorNumber="
				+ doctorNumber + ", overseasNumber=" + overseasNumber + ", academicianNunber=" + academicianNunber
				+ ", contactId=" + contactId + ", contactName=" + contactName + ", contactBusiness=" + contactBusiness
				+ ", contactNumber=" + contactNumber + ", contactAddress=" + contactAddress + ", postcode=" + postcode
				+ ", email=" + email + ", contactUrl=" + contactUrl + ", faxNumber=" + faxNumber + ", qqormsnNumer="
				+ qqormsnNumer + ", phoneNumber=" + phoneNumber + ", auditInfoId=" + auditInfoId + ", submitTime="
				+ submitTime + ", auditTime=" + auditTime + ", auditStatus=" + auditStatus + ", auditResult="
				+ auditResult + ", userId=" + userId + ", userName=" + userName + "]";
	}

}
