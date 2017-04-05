package com.jyu.sati.vo;

import java.util.Date;

import com.jyu.sati.common.pageUtil.Page;
import com.jyu.sati.entity.Info;

/**
 * 政策法规和通知公告的分页查询条件类
 * 
 * @author 淋雨又调皮
 *
 */
public class InfoPageVo extends Page<Info> {

	private final static Integer pageSize = 30;
	private String key;// 关键词
	private Integer infoStatus;// 信息状态
	private String publisherId;// 发布人
	private Integer infoType;// 信息类型
	private Date startDate;// 最早发布时间
	private Date endDate;// 最迟发布时间
	private Integer publishTimeDescOrAsc;// 发布时间的顺倒序或顺序：1顺序，2倒序

	public InfoPageVo() {
		super(0, 0, pageSize);
	}

	public InfoPageVo(Integer totalNo, Integer pageNo, Integer pageSize) {
		super(totalNo, pageNo, pageSize);
		// TODO Auto-generated constructor stub
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getInfoStatus() {
		return infoStatus;
	}

	public void setInfoStatus(Integer infoStatus) {
		this.infoStatus = infoStatus;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public Integer getInfoType() {
		return infoType;
	}

	public void setInfoType(Integer infoType) {
		this.infoType = infoType;
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

	public Integer getPublishTimeDescOrAsc() {
		return publishTimeDescOrAsc;
	}

	public void setPublishTimeDescOrAsc(Integer publishTimeDescOrAsc) {
		this.publishTimeDescOrAsc = publishTimeDescOrAsc;
	}

	public static Integer getPagesize() {
		return pageSize;
	}

	@Override
	public String toString() {
		return "InfoPageVo [key=" + key + ", infoStatus=" + infoStatus + ", publisherId=" + publisherId + ", infoType="
				+ infoType + ", startDate=" + startDate + ", endDate=" + endDate + ", publishTimeDescOrAsc="
				+ publishTimeDescOrAsc + "]";
	}

}
