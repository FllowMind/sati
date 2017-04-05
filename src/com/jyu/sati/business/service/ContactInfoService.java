package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.ContactInfo;

public interface ContactInfoService {

	int delete(Integer contactId) throws BusinessException;

	int insert(ContactInfo record) throws BusinessException;

	int insertSelective(ContactInfo record) throws BusinessException;

	ContactInfo getContactInfoById(Integer contactId) throws BusinessException;

	int updateSelective(ContactInfo record) throws BusinessException;

	int update(ContactInfo record) throws BusinessException;
}
