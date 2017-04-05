package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Document;

public interface DocumentDao {
	int deleteByPrimaryKey(Integer documentId);

	int insert(Document record);

	int insertSelective(Document record);

	Document selectByPrimaryKey(Integer documentId);

	int updateByPrimaryKeySelective(Document record);

	int updateByPrimaryKey(Document record);

	/**
	 * 获取身份证照片信息
	 * @param userId
	 * @return
	 */
	Document getIdCardImageByUserId(@Param("userId") String userId);
	
	/**
	 * 获取身份证照片信息
	 * @param userId
	 * @return
	 */
	Document getPersonImageByUserId(@Param("userId") String userId);
	
	/**
	 * 获取附件信息
	 * @param userId
	 * @return
	 */
	Document getEnclosureByUserId(@Param("userId")String userId);
}