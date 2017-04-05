package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.TechEnclosure;

public interface TechEnclosureDao {
	int deleteByPrimaryKey(Integer enclosureId);

	int insert(TechEnclosure record);

	int insertSelective(TechEnclosure record);

	TechEnclosure selectByPrimaryKey(Integer enclosureId);

	int updateByPrimaryKeySelective(TechEnclosure record);

	int updateByPrimaryKey(TechEnclosure record);

	/**
	 * 获取技术供给图片附件
	 * 
	 * @param tsiId
	 * @return
	 */
	TechEnclosure getImageEnclosureByTsiId(@Param("tsiId") Integer tsiId);

	/**
	 * 获取技术供给文本附件
	 * 
	 * @param tsiId
	 * @return
	 */
	TechEnclosure getTextEnclosureByTsiId(@Param("tsiId") Integer tsiId);

	/**
	 * 获取技术供给视频附件
	 * 
	 * @param tsiId
	 * @return
	 */
	TechEnclosure getVideoEnclosureByTsiId(@Param("tsiId") Integer tsiId);
}