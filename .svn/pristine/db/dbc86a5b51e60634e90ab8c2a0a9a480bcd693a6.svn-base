package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Person;
import com.jyu.sati.vo.PersonInfoVo;

public interface PersonService {

	int delete(Integer personId) throws BusinessException;

	int insert(Person record) throws BusinessException;

	int insertSelective(Person record) throws BusinessException;

	Person getPersonById(Integer personId) throws BusinessException;

	/**
	 * 更新非空字段
	 * 
	 * @param record
	 * @return
	 */
	int updateSelective(Person record) throws BusinessException;

	int update(Person record) throws BusinessException;

	/**
	 * 根据用户Id获取个人用户信息
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfoVo getPersonInfo(String userId) throws BusinessException;

	/**
	 * 根据用户Id获取个人用户信息（管理员审核使用）
	 * 
	 * @param userId
	 * @return
	 */
	PersonInfoVo getAuditPersonInfo(String userId) throws BusinessException;

	/**
	 * 更新个人用户信息
	 * 
	 * @param personInfo
	 * @throws BusinessException
	 */
	void updatePersonInfo(PersonInfoVo personInfo) throws BusinessException;

}
