package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Produce;
import com.jyu.sati.vo.ProducePageVo;

public interface ProduceDao {

	int deleteByPrimaryKey(Integer produceId);

	int insert(Produce record);

	int insertSelective(Produce record);

	Produce selectByPrimaryKey(Integer produceId);

	int updateByPrimaryKeySelective(Produce record);

	int updateByPrimaryKeyWithBLOBs(Produce record);

	int updateByPrimaryKey(Produce record);

	/**
	 * 更改是否推荐状态
	 * 
	 * @param produceId
	 * @return
	 */
	int updateIsRecommendStatus(@Param("produceId") Integer produceId);

	/**
	 * 更改产品成果状态
	 * 
	 * @param produceId
	 * @param produceStatus
	 * @return
	 */
	int updateProduceStatus(Produce produce);

	/**
	 * 获取一页的产品成果
	 * 
	 * @param produceId
	 * @return
	 */
	List<Produce> getProducePageByCondition(ProducePageVo condition);

	/**
	 * 获取一页的产品成果总数
	 * 
	 * @param produceId
	 * @return
	 */
	Integer getTotalNoByCondition(ProducePageVo condition);

	/**
	 * 获取指定审核状态和分布人的产品数目
	 * 
	 * @param auditStatus
	 *            审核状态 （可为空）
	 * @param publisherId
	 *            发布人id （可为空）
	 * @return
	 */
	Integer getProduceCountByAuditStatusAndPublisher(@Param("auditStatus") Integer auditStatus,
			@Param("publisherId") String publisherId);

	/**
	 * 根据产品成果id获取发布人
	 * 
	 * @param produceId
	 * @return
	 */
	String getPublisherIdByProduceId(@Param("produceId") Integer produceId);
}