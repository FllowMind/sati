package com.jyu.sati.vo;

import java.util.Date;

public class PersonInfoVo {

	// Person表查询
	private Integer personId;// 个人用户ID
	private Integer politicalLandscape;// 政治面貌
	private Integer learnMajorId;// 所学专业
	private Integer degreeId;// 学位
	private String university;// 毕业院校
	private Integer workMajorId;// 从事专业
	private Integer industryId;// 研究行业
	private String researchDirection;// 研究方向
	private String key;// 被搜索的关键字
	private String achievement;// 个人成果成绩简介
	private Integer academicTitleId;// 职称;
	private Integer locationId;// 所在地
	private Integer highTechFieldId;// 高新技术领域
	// documents 表查询
	private Integer enclosureId;// 附件Id
	private String enclosureUrl;
	private String enclosureDesc;

	private Integer baseInfoId;// 个人基本信息
	// person_infos表查询
	private String personName;// 姓名
	private String idcardNumber;// 身份证号码
	// documents 表查询
	private Integer idcardImageId;// 身份证正面图片
	private String imageUrl;

	private Integer sex;// 性别
	private String nation;// 民族
	private Date birth;// 出生日期
	private Integer educationId;// 学历
	private String workplace;// 工作单位
	private String business;// 职务
	// documents 表查询
	private Integer personImageId;// 个人照片
	private String personImageUrl;

	private String personalProfile;// 个人简介
	private String remark;// 备注
	// contact_infos表查询
	private Integer contactId;// 联系人ID
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

	// 从audit_info中查询数据
	private Integer auditInfoId;// 审核信息ID
	private Date submitTime;// 提交时间
	private Date auditTime;// 审核时间
	private Integer auditStatus;// 审核状态
	private String auditResult;// 审核结果
	private String userId;// 审核人
	private String userName;// 审核人姓名

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Integer getPoliticalLandscape() {
		return politicalLandscape;
	}

	public void setPoliticalLandscape(Integer politicalLandscape) {
		this.politicalLandscape = politicalLandscape;
	}

	public Integer getLearnMajorId() {
		return learnMajorId;
	}

	public void setLearnMajorId(Integer learnMajorId) {
		this.learnMajorId = learnMajorId;
	}

	public Integer getDegreeId() {
		return degreeId;
	}

	public void setDegreeId(Integer degreeId) {
		this.degreeId = degreeId;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public Integer getWorkMajorId() {
		return workMajorId;
	}

	public void setWorkMajorId(Integer workMajorId) {
		this.workMajorId = workMajorId;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getResearchDirection() {
		return researchDirection;
	}

	public void setResearchDirection(String researchDirection) {
		this.researchDirection = researchDirection;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

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

	public Integer getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(Integer baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getIdcardNumber() {
		return idcardNumber;
	}

	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}

	public Integer getIdcardImageId() {
		return idcardImageId;
	}

	public void setIdcardImageId(Integer idcardImageId) {
		this.idcardImageId = idcardImageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Integer getEducationId() {
		return educationId;
	}

	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public Integer getPersonImageId() {
		return personImageId;
	}

	public void setPersonImageId(Integer personImageId) {
		this.personImageId = personImageId;
	}

	public String getPersonImageUrl() {
		return personImageUrl;
	}

	public void setPersonImageUrl(String personImageUrl) {
		this.personImageUrl = personImageUrl;
	}

	public String getPersonalProfile() {
		return personalProfile;
	}

	public void setPersonalProfile(String personalProfile) {
		this.personalProfile = personalProfile;
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

	public Integer getAcademicTitleId() {
		return academicTitleId;
	}

	public void setAcademicTitleId(Integer academicTitleId) {
		this.academicTitleId = academicTitleId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getHighTechFieldId() {
		return highTechFieldId;
	}

	public void setHighTechFieldId(Integer highTechFieldId) {
		this.highTechFieldId = highTechFieldId;
	}

	public String getEnclosureDesc() {
		return enclosureDesc;
	}

	public void setEnclosureDesc(String enclosureDesc) {
		this.enclosureDesc = enclosureDesc;
	}

	@Override
	public String toString() {
		return "PersonInfoVo [personId=" + personId + ", politicalLandscape=" + politicalLandscape + ", learnMajorId="
				+ learnMajorId + ", degreeId=" + degreeId + ", university=" + university + ", workMajorId="
				+ workMajorId + ", industryId=" + industryId + ", researchDirection=" + researchDirection + ", key="
				+ key + ", achievement=" + achievement + ", academicTitleId=" + academicTitleId + ", locationId="
				+ locationId + ", highTechFieldId=" + highTechFieldId + ", enclosureId=" + enclosureId
				+ ", enclosureUrl=" + enclosureUrl + ", enclosureDesc=" + enclosureDesc + ", baseInfoId=" + baseInfoId
				+ ", personName=" + personName + ", idcardNumber=" + idcardNumber + ", idcardImageId=" + idcardImageId
				+ ", imageUrl=" + imageUrl + ", sex=" + sex + ", nation=" + nation + ", birth=" + birth
				+ ", educationId=" + educationId + ", workplace=" + workplace + ", business=" + business
				+ ", personImageId=" + personImageId + ", personImageUrl=" + personImageUrl + ", personalProfile="
				+ personalProfile + ", remark=" + remark + ", contactId=" + contactId + ", contactName=" + contactName
				+ ", contactBusiness=" + contactBusiness + ", contactNumber=" + contactNumber + ", contactAddress="
				+ contactAddress + ", postcode=" + postcode + ", email=" + email + ", contactUrl=" + contactUrl
				+ ", faxNumber=" + faxNumber + ", qqormsnNumer=" + qqormsnNumer + ", phoneNumber=" + phoneNumber
				+ ", auditInfoId=" + auditInfoId + ", submitTime=" + submitTime + ", auditTime=" + auditTime
				+ ", auditStatus=" + auditStatus + ", auditResult=" + auditResult + ", userId=" + userId + ", userName="
				+ userName + "]";
	}

}
