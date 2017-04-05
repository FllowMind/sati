package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.EmployeeSituationDao;
import com.jyu.sati.business.service.EmployeeSituationService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.EmployeeSituation;

/**
 * 单位人员情况服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class EmployeeSituationServiceImpl implements EmployeeSituationService {

	@Autowired
	private EmployeeSituationDao empDao;

	@Transactional
	@Override
	public int delete(Integer soeId) throws BusinessException {
		// TODO Auto-generated method stub
		return empDao.deleteByPrimaryKey(soeId);
	}

	@Transactional
	@Override
	public int insert(EmployeeSituation record) throws BusinessException {
		// TODO Auto-generated method stub
		return empDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(EmployeeSituation record) throws BusinessException {
		// TODO Auto-generated method stub
		return empDao.insertSelective(record);
	}

	@Transactional(readOnly = true)
	@Override
	public EmployeeSituation getEmpSituationById(Integer soeId) throws BusinessException {
		// TODO Auto-generated method stub
		return empDao.selectByPrimaryKey(soeId);
	}

	@Transactional
	@Override
	public int updateSelective(EmployeeSituation record) throws BusinessException {
		// TODO Auto-generated method stub
		return empDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int update(EmployeeSituation record) throws BusinessException {
		// TODO Auto-generated method stub
		return empDao.updateByPrimaryKey(record);
	}

}
