package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AdminstratorDao;
import com.jyu.sati.business.service.AdministratorService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Adminstrator;

/**
 * 
 * 管理员服务
 * @author 淋雨又调皮
 *
 */
@Service
public class AdministratorServiceImpl implements AdministratorService {

	@Autowired
	private AdminstratorDao adminDao;
	
	@Transactional
	@Override
	public int deleteByAdminId(Integer administratorId) throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.deleteByPrimaryKey(administratorId);
	}

	@Transactional
	@Override
	public int insert(Adminstrator adminstrator) throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.insert(adminstrator);
	}

	@Transactional
	@Override
	public int insertSelective(Adminstrator adminstrator) throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.insert(adminstrator);
	}

	@Transactional(readOnly = true)
	@Override
	public Adminstrator getAdminByAdminId(Integer administratorId) throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.selectByPrimaryKey(administratorId);
	}

	@Transactional
	@Override
	public int updateSelective(Adminstrator adminstrator) throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.updateByPrimaryKeySelective(adminstrator);
	}

	@Transactional
	@Override
	public int update(Adminstrator adminstrator) throws BusinessException {
		// TODO Auto-generated method stub
		return adminDao.updateByPrimaryKey(adminstrator);
	}

}
