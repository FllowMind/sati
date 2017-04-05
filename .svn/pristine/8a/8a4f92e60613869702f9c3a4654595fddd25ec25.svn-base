package com.jyu.sati.vo;

import java.util.Date;

import com.jyu.sati.common.pageUtil.Page;
import com.jyu.sati.entity.TechnologyBaseInfo;

/**
 * 
 * 需求、供给技术查询工具类
 * 
 * @author 淋雨又调皮
 *
 */
public class TechnologyPageVo extends Page<TechOutlineVo> {

	private final static Integer pageSize = 10;// 每页大小

	private String key;// 关键字
	private Integer industryId;// 所属行业
	private Integer htfId;// 高新技术领域
	private Date startDate;// 最早发布时间
	private Date endDate;// 最迟发布时间
	private String publisherId;// 发布人id
	private Integer techStatus;// 技术状态
	private Integer publishTimeDescOrAsc;// 发布时间的顺倒序或顺序：1顺序，2倒序
	private Integer auditStatus;// 审核状态
	private Integer limitStatus;// 是否被禁用

	public TechnologyPageVo() {
		super(0, 0, pageSize);
	}

	public TechnologyPageVo(Integer totalNo, Integer pageNo, String key, Date createTime) {
		super(totalNo, pageNo, pageSize);
	}

	public Integer getLimitStatus() {
		return limitStatus;
	}

	public void setLimitStatus(Integer limitStatus) {
		this.limitStatus = limitStatus;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
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

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getTechStatus() {
		return techStatus;
	}

	public void setTechStatus(Integer techStatus) {
		this.techStatus = techStatus;
	}

	public Integer getPublishTimeDescOrAsc() {
		return publishTimeDescOrAsc;
	}

	public void setPublishTimeDescOrAsc(Integer publishTimeDescOrAsc) {
		this.publishTimeDescOrAsc = publishTimeDescOrAsc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "TechnologyPageVo [key=" + key + ", industryId=" + industryId + ", htfId=" + htfId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", publisherId=" + publisherId + ", techStatus=" + techStatus
				+ ", publishTimeDescOrAsc=" + publishTimeDescOrAsc + ", auditStatus=" + auditStatus + ", limitStatus="
				+ limitStatus + "]";
	}

}
