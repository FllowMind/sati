package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.TechSupplyInfo;
import com.jyu.sati.vo.TechOutlineVo;
import com.jyu.sati.vo.TechnologyPageVo;

public interface TechSupplyInfoDao {
	int deleteByPrimaryKey(Integer tsiId);

	int insert(TechSupplyInfo record);

	int insertSelective(TechSupplyInfo record);

	TechSupplyInfo selectByPrimaryKey(Integer tsiId);

	int updateByPrimaryKeySelective(TechSupplyInfo record);

	int updateByPrimaryKey(TechSupplyInfo record);

	/**
	 * 更新技术供给状态
	 * 
	 * @param tsiId
	 * @return
	 */
	int updateTechSupStatus(@Param("tsiId") Integer tsiId);

	/**
	 * 更新技术供给是否被禁用状态
	 * 
	 * @param tsiId
	 * @return
	 */
	int updateTechSupLimitStatus(@Param("tsiId") Integer tsiId);

	/**
	 * 获取技术供给的一页数据
	 * 
	 * @param condition
	 *            查询条件
	 * @return
	 */
	List<TechOutlineVo> getTechSuppplyPageByCondition(TechnologyPageVo condition);

	/**
	 * 根据查询条件获取数据总数
	 * 
	 * @param condition
	 * @return
	 */
	Integer getTotalNoByCondition(TechnologyPageVo condition);

	/**
	 * 根据技术供给id获取发布人账号
	 * 
	 * @param tsiId
	 * @return
	 */
	String getPublisherIdByTechSupInfoId(@Param("tsiId") Integer tsiId);

	/**
	 * 更新技术供给图片附件id
	 * 
	 * @param tsiId
	 * @param enclosureId
	 *            附件id
	 * @return
	 */
	Integer updateSupplyImageEnclosure(@Param("tsiId") Integer tsiId, @Param("enclosureId") Integer enclosureId);

	/**
	 * 更新技术供给图片附件id
	 * 
	 * @param tsiId
	 * @param enclosureId
	 *            附件id
	 * @return
	 */
	Integer updateSupplyTextEnclosure(@Param("tsiId") Integer tsiId, @Param("enclosureId") Integer enclosureId);

	/**
	 * 更新技术供给图片附件id
	 * 
	 * @param tsiId
	 * @param enclosureId
	 *            附件id
	 * @return
	 */
	Integer updateSupplyVideoEnclosure(@Param("tsiId") Integer tsiId, @Param("enclosureId") Integer enclosureId);

}