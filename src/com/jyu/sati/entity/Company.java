package com.jyu.sati.entity;

/**
 * 企业用户实体类
 * @author 淋雨又调皮
 *
 */
public class Company {
	
	private Integer companyId;//企业信息id
	private Integer companyNatureId;//企业性质
	private Float registeredCapital;//注册资金（万元）
	private String registeredAddress;//注册地址
	private Integer industryId;//所属行业
	private Integer circOrNot;//是否国家创新驿站
	private Integer cttOrNot;//是否国家技术转移
	private String businessField;//业务领域、经营范围
	private String remark;//备注
	//用来单位从业人员情况
	private Integer soeId;//单位从业人员情况Id
	//
	private Integer baseInfoId;//单位基本id

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

	@Override
	public String toString() {
		return "Company [companyId=" + companyId + ", companyNatureId=" + companyNatureId + ", registeredCapital="
				+ registeredCapital + ", registeredAddress=" + registeredAddress + ", industryId=" + industryId
				+ ", circOrNot=" + circOrNot + ", cttOrNot=" + cttOrNot + ", businessField=" + businessField
				+ ", remark=" + remark + ", soeId=" + soeId + ", baseInfoId=" + baseInfoId + "]";
	}

}