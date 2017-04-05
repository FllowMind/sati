package com.jyu.sati.entity;

import java.util.Date;

/**
 * 审核信息
 * 
 * @author 淋雨又调皮
 *
 */
public class AuditInfo {

	public final static int AUDIT_STATUS_NO_AUDIT = -1;// 未审核状态
	public final static int AUDIT_STATUS_WAITING = 1;// 待审核状态
	public final static int AUDIT_STATUS_AUDITING = 2;// 正在审核状态
	public final static int AUDIT_STATUS_NO_PASS = 3;// 审核未通过状态
	public final static int AUDIT_STATUS_PASSED = 4;// 审核通过状态

	public final static int AUDIT_TYPE_USER_INFO = 1;// 用户信息审核类型
	public final static int AUDIT_TYPE_TECH_SUPPLY = 2;// 技术供给信息审核类型
	public final static int AUDIT_TYPE_TECH_REQUIRE = 3;// 技术需求信息审核类型
	public final static int AUDIT_TYPE_PRODUCE = 4;// 产品信息审核类型

	private Integer auditInfoId;// 审核信息ID
	private Date submitTime;// 提交时间
	private Date auditTime;// 审核时间
	private Integer auditStatus;// 审核状态
	private String auditResult;// 审核结果
	private String auditorId;// 审核人用户账号
	private Integer auditType;// 审核信息类型

	private String userId;// 被审核用户账户
	private String userName;// 被审核用户名
	private Integer userType;// 被审核用户类型

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

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public Integer getAuditType() {
		return auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	@Override
	public String toString() {
		return "AuditInfo [auditInfoId=" + auditInfoId + ", submitTime=" + submitTime + ", auditTime=" + auditTime
				+ ", auditStatus=" + auditStatus + ", auditResult=" + auditResult + ", auditorId=" + auditorId
				+ ", auditType=" + auditType + ", userId=" + userId + ", userName=" + userName + ", userType="
				+ userType + "]";
	}

}