package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.service.ContactInfoService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.ContactInfo;

/**
 * 联系信息服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class ContactInfoServiceImpl implements ContactInfoService {

	@Autowired
	private ContactInfoDao contactDao;

	@Transactional
	@Override
	public int delete(Integer contactId) throws BusinessException {
		// TODO Auto-generated method stub
		return contactDao.deleteByPrimaryKey(contactId);
	}

	@Transactional
	@Override
	public int insert(ContactInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return contactDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(ContactInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return contactDao.insertSelective(record);
	}

	@Transactional(readOnly = true)
	@Override
	public ContactInfo getContactInfoById(Integer contactId) throws BusinessException {
		// TODO Auto-generated method stub
		return contactDao.selectByPrimaryKey(contactId);
	}

	@Transactional
	@Override
	public int updateSelective(ContactInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return contactDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int update(ContactInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return contactDao.updateByPrimaryKey(record);
	}

}
