package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.PersonInfo;

public interface PersonInfoDao {
	int deleteByPrimaryKey(Integer pbiId);

	int insert(PersonInfo record);

	int insertSelective(PersonInfo record);

	PersonInfo selectByPrimaryKey(Integer pbiId);

	int updateByPrimaryKeySelective(PersonInfo record);

	int updateByPrimaryKey(PersonInfo record);

	/**
	 * 绑定身份证图片或个人照片
	 * 
	 * @param userId
	 * @param idcardImageId
	 *            为空绑定个人照片
	 * @param personImageId
	 *            为空绑定身份证图片
	 */
	int bindImage(@Param("userId") String userId, @Param("idcardImageId") Integer idcardImageId,
			@Param("personImageId") Integer personImageId);

}