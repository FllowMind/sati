package com.jyu.sati.entity;

public class TechRequireInfo {

	private Integer triId; // 技术需求信息ID
	private Float investmentFunds;// 欲投资资金（万元）
	private Integer timeLimit;// 解决时限
	private String problemDescription;// 难题描述
	private Integer tbiId;// 技术基本信息

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

	@Override
	public String toString() {
		return "TechRequireInfo [triId=" + triId + ", investmentFunds=" + investmentFunds + ", timeLimit=" + timeLimit
				+ ", problemDescription=" + problemDescription + ", tbiId=" + tbiId + "]";
	}
	
}