package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Adminstrator;

public interface AdministratorService {

	int deleteByAdminId(Integer administratorId) throws BusinessException;

	int insert(Adminstrator record) throws BusinessException;

	int insertSelective(Adminstrator record) throws BusinessException;

	Adminstrator getAdminByAdminId(Integer administratorId) throws BusinessException;

	/**
	 * 更新非空字段
	 * 
	 * @param record
	 * @return
	 */
	int updateSelective(Adminstrator record) throws BusinessException;

	int update(Adminstrator record) throws BusinessException;
}
