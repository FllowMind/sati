package com.jyu.sati.vo;

import java.io.Serializable;
import java.util.Date;

import com.jyu.sati.common.pageUtil.Page;
import com.jyu.sati.common.util.DateUtil;
import com.jyu.sati.entity.Produce;

/**
 * 产品成果vo
 * 
 * @author 淋雨又调皮
 *
 */
public class ProducePageVo extends Page<Produce> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 683824056277529383L;
	private final static int pageSize = 20;// 默认20条
	private String produceKey;// 被搜索到的关键字
	private Integer produceTypeId;// 产品类别
	private Date startDate;// 最早发布时间
	private Date endDate;// 最迟发布时间
	private String publisherId;// 发布人id
	private Integer produceStatus;// 产品状态
	private Integer isRecommend;// 是否推荐到首页
	private Integer publishTimeDescOrAsc;// 发布时间的顺倒序或顺序：1顺序，2倒序
	private Integer auditStatus;// 审核状态
	private String auditorId;// 审核人id

	public ProducePageVo() {
		super(0, 0, pageSize);
	}

	public String getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(String auditorId) {
		this.auditorId = auditorId;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getPublishTimeDescOrAsc() {
		return publishTimeDescOrAsc;
	}

	public void setPublishTimeDescOrAsc(Integer publishTimeDescOrAsc) {
		this.publishTimeDescOrAsc = publishTimeDescOrAsc;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public ProducePageVo(Integer totalNo, Integer pageNo) {
		super(totalNo, pageNo, pageSize);
		// TODO Auto-generated constructor stub
	}

	public Integer getProduceStatus() {
		return produceStatus;
	}

	public void setProduceStatus(Integer produceStatus) {
		this.produceStatus = produceStatus;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getProduceKey() {
		return produceKey;
	}

	public void setProduceKey(String produceKey) {
		this.produceKey = produceKey;
	}

	public Integer getProduceTypeId() {
		return produceTypeId;
	}

	public void setProduceTypeId(Integer produceTypeId) {
		this.produceTypeId = produceTypeId;
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

	@Override
	public String toString() {
		return "ProducePageVo [produceKey=" + produceKey + ", produceTypeId=" + produceTypeId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", publisherId=" + publisherId + ", produceStatus="
				+ produceStatus + ", isRecommend=" + isRecommend + ", publishTimeDescOrAsc=" + publishTimeDescOrAsc
				+ ", auditStatus=" + auditStatus + ", auditorId=" + auditorId + "]";
	}

}
