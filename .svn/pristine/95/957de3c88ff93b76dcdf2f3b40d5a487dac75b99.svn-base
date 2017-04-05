package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Document;

/**
 * @author 淋雨又调皮
 *
 */
public interface DocumentService {

	int delete(Integer documentId) throws BusinessException;

	int insert(Document record) throws BusinessException;

	/**
	 * 只插入非空字段
	 * 
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	int insertSelective(Document record) throws BusinessException;

	Document select(Integer documentId) throws BusinessException;

	/**
	 * 
	 * 只更新非空字段
	 * 
	 * @param record
	 * @return
	 * @throws BusinessException
	 */
	int updateSelective(Document record) throws BusinessException;

	int update(Document record) throws BusinessException;

	/**
	 * 获取身份证照片信息
	 * 
	 * @param userId
	 * @return
	 */
	Document getIdCardImageByUserId(String userId) throws BusinessException;

	/**
	 * 获取身份证照片信息
	 * 
	 * @param userId
	 * @return
	 */
	Document getPersonImageByUserId(String userId) throws BusinessException;

	/**
	 *获取个人附件信息 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	Document getEnclosureByUserId(String userId) throws BusinessException;
}
