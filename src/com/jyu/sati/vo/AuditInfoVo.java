package com.jyu.sati.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jyu.sati.common.pageUtil.Page;
import com.jyu.sati.entity.AuditInfo;

public class AuditInfoVo extends Page<AuditInfo> {

	private Integer auditStatus;// 审核状态
	private Integer userType;// 被审核用户类型
	private Integer auditType;// 审核类型
	private Date startDate;// 最早提交时间
	private Date endDate;// 最迟提交时间
	private String auditorId;// 审核人ID

	public List<AuditInfo> resultList = new ArrayList<>();// 返回数据

	public AuditInfoVo() {
		super(0, 0, 20);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public AuditInfoVo(Integer totalNo, Integer pageNo, int pageSize) {
		super(totalNo, pageNo, pageSize);
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	@Override
	public String toString() {
		return "AuditInfoVo [auditStatus=" + auditStatus + ", userType=" + userType + ", auditType=" + auditType
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", auditorId=" + auditorId + ", resultList="
				+ resultList + "]";
	}

}
