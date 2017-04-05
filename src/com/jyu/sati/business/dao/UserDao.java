package com.jyu.sati.business.dao;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.User;

public interface UserDao {
	int deleteByPrimaryKey(String userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	/**
	 * 根据账号和密码获取用户
	 * 
	 * @param userName
	 *            账号
	 * @param password
	 *            密码
	 * @return
	 */
	User getUserByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

	User getUserByUserName(@Param("userName") String userName);

	/**
	 * 判断电话号码是否被使用
	 * 
	 * @param phoneNumber
	 * @return
	 */
	Integer isPhoneNumberInUsing(@Param("phoneNumber") String phoneNumber);

	/**
	 * 获取用户名
	 * 
	 * @param userId
	 * @return
	 */
	String getUserNameByUserId(@Param("userId") String userId);

	/**
	 * 根据用户id获取用户类型
	 * @param userId
	 * @return
	 */
	Integer getUserTypeByUserId(@Param("userId") String userId);

}