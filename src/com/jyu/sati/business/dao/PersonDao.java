package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Person;

public interface PersonDao {
	int deleteByPrimaryKey(Integer personId);

	int insert(Person record);

	int insertSelective(Person record);

	Person selectByPrimaryKey(Integer personId);

	int updateByPrimaryKeySelective(Person record);

	int updateByPrimaryKey(Person record);

	Person getPersonByUserId(@Param("userId") String userId);

	/**
	 * 
	 * 绑定个人用户的附件
	 * @param userId
	 * @param enclosureId
	 * @return
	 */
	int bindEnclosureForPerson(@Param("userId") String userId, @Param("enclosureId") Integer enclosureId);
}