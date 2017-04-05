package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.UnitInfo;
import com.jyu.sati.vo.UnitInfoVo;

public interface UnitInfoService {
	int delete(Integer unitInfoId) throws BusinessException;

	int insert(UnitInfo record) throws BusinessException;

	int insertSelective(UnitInfo record) throws BusinessException;

	UnitInfo getUnitInfoById(Integer unitInfoId) throws BusinessException;

	int updateSelective(UnitInfo record) throws BusinessException;

	int update(UnitInfo record) throws BusinessException;

	/**
	 * 获取高校单位信息
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	UnitInfoVo getCollegeInfo(String userId) throws BusinessException;

	/**
	 * 获取高校单位审核信息（管理员使用）
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	UnitInfoVo getAuditCollegeInfo(String userId) throws BusinessException;

	/**
	 * 获取科研单位信息
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	UnitInfoVo getScientifyInfo(String userId) throws BusinessException;

	/**
	 * 获取科研单位审核信息（管理员使用）
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	UnitInfoVo getAuditScientifyInfo(String userId) throws BusinessException;

	/**
	 * 更新单位信息
	 * 
	 * @param unitInfo
	 * @throws BusinessException
	 */
	void updateUnitInfo(UnitInfoVo unitInfo) throws BusinessException;

	}
