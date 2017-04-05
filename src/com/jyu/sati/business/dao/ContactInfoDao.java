package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.ContactInfo;

public interface ContactInfoDao {
	int deleteByPrimaryKey(Integer contactId);

	int insert(ContactInfo record);

	int insertSelective(ContactInfo record);

	ContactInfo selectByPrimaryKey(Integer contactId);

	int updateByPrimaryKeySelective(ContactInfo record);

	int updateByPrimaryKey(ContactInfo record);

	/**
	 * 通过用户id获取联系人信息
	 * 
	 * @param userId
	 * @return
	 */
	ContactInfo getContactInfoByUserId(@Param("userId") String userId);
}