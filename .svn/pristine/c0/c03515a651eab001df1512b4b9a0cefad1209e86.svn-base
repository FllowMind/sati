package com.jyu.sati.entity;

import java.util.Date;
import java.util.List;

/**
 * 产品成果实体类
 * 
 * @author 淋雨又调皮
 *
 */
public class Produce {

	public final static int PRODUCE_STATUS_UN_SUBMIT = -1;// 未提交状态
	public final static int PRODUCE_STATUS_NORMAL = 1;// 正常状态
	public final static int PRODUCE_STATUS_LIMIT = 2;// 禁止状态

	public final static int IS_RECOMMENT = 1;// 推荐到首页显示
	public final static int IS_NOT_RECOMMENT = -1;// 不推荐到首页显示

	private Integer produceId;
	private String produceName;// 产品名称
	private Integer produceTypeId;// 产品类别
	private Integer produceStatus;// 产品状态
	private String publisherId;// 发布人id
	private Integer isRecommend;// 是否推荐到首页展示
	private Integer auditInfoId;// 审核信息id
	private String produceKey;// 被搜索到的关键字
	private Integer contactInfoId;// 联系信息id
	private Integer pageView;// 浏览次数
	private String produceDesc;// 产品描述
	private Integer homePageImageId;// 在首页展示的图片id
	private Date publishTime;// 发布时间

	private List<ProduceImage> images;// 产品成果图片

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getProduceKey() {
		return produceKey;
	}

	public void setProduceKey(String produceKey) {
		this.produceKey = produceKey;
	}

	public Integer getProduceId() {
		return produceId;
	}

	public void setProduceId(Integer produceId) {
		this.produceId = produceId;
	}

	public String getProduceName() {
		return produceName;
	}

	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}

	public Integer getProduceTypeId() {
		return produceTypeId;
	}

	public void setProduceTypeId(Integer produceTypeId) {
		this.produceTypeId = produceTypeId;
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

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public Integer getAuditInfoId() {
		return auditInfoId;
	}

	public void setAuditInfoId(Integer auditInfoId) {
		this.auditInfoId = auditInfoId;
	}

	public Integer getContactInfoId() {
		return contactInfoId;
	}

	public void setContactInfoId(Integer contactInfoId) {
		this.contactInfoId = contactInfoId;
	}

	public Integer getPageView() {
		return pageView;
	}

	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}

	public String getProduceDesc() {
		return produceDesc;
	}

	public void setProduceDesc(String produceDesc) {
		this.produceDesc = produceDesc;
	}

	public List<ProduceImage> getImages() {
		return images;
	}

	public void setImages(List<ProduceImage> images) {
		this.images = images;
	}

	public Integer getHomePageImageId() {
		return homePageImageId;
	}

	public void setHomePageImageId(Integer homePageImageId) {
		this.homePageImageId = homePageImageId;
	}

	@Override
	public String toString() {
		return "Produce [produceId=" + produceId + ", produceName=" + produceName + ", produceTypeId=" + produceTypeId
				+ ", produceStatus=" + produceStatus + ", publisherId=" + publisherId + ", isRecommend=" + isRecommend
				+ ", auditInfoId=" + auditInfoId + ", produceKey=" + produceKey + ", contactInfoId=" + contactInfoId
				+ ", pageView=" + pageView + ", produceDesc=" + produceDesc + ", homePageImageId=" + homePageImageId
				+ ", publishTime=" + publishTime + ", images=" + images + "]";
	}

}