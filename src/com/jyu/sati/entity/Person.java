package com.jyu.sati.entity;

public class Person {

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
	private Integer enclosureId;// 附件Id
	private Integer baseInfoId;// 个人基本信息Id
	private Integer academicTitleId;// 职称;
	private Integer locationId;// 所在地
	private Integer highTechFieldId;// 高新技术领域

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

	public Integer getBaseInfoId() {
		return baseInfoId;
	}

	public void setBaseInfoId(Integer baseInfoId) {
		this.baseInfoId = baseInfoId;
	}

	public Integer getAcademicTitleId() {
		return academicTitleId;
	}

	public void setAcademicTiTleId(Integer academicTitleId) {
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

	@Override
	public String toString() {
		return "Person [personId=" + personId + ", politicalLandscape=" + politicalLandscape + ", learnMajorId="
				+ learnMajorId + ", degreeId=" + degreeId + ", university=" + university + ", workMajorId="
				+ workMajorId + ", industryId=" + industryId + ", researchDirection=" + researchDirection + ", key="
				+ key + ", achievement=" + achievement + ", enclosureId=" + enclosureId + ", baseInfoId=" + baseInfoId
				+ ", academicTitleId=" + academicTitleId + ", locationId=" + locationId + ", highTechFieldId="
				+ highTechFieldId + "]";
	}
}