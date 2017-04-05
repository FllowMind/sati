package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.EmployeeSituation;

public interface EmployeeSituationService {

	int delete(Integer soeId) throws BusinessException;

	int insert(EmployeeSituation record) throws BusinessException;

	int insertSelective(EmployeeSituation record) throws BusinessException;

	EmployeeSituation getEmpSituationById(Integer soeId) throws BusinessException;

	int updateSelective(EmployeeSituation record) throws BusinessException;

	int update(EmployeeSituation record) throws BusinessException;
}
