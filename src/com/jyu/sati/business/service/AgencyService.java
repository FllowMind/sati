package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Agency;
import com.jyu.sati.vo.AgencyInfoVo;

/**
 * 中介机构服务
 * 
 * @author 淋雨又调皮
 *
 */
public interface AgencyService {

	int deleteByAgencyId(Integer agencyId);

	int insert(Agency agency);

	int insertSelective(Agency agency);

	Agency selectByAgencyId(Integer agencyId);

	/**
	 * 更新非空字段
	 * 
	 * @param agency
	 * @return
	 */
	int updateSelective(Agency agency);

	int update(Agency agency);

	/**
	 * 通过用户id获取中介结构信息
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	AgencyInfoVo getAgencyInfoByUserId(String userId) throws BusinessException;

	/**
	 * 更新中介机构
	 * 
	 * @param agencyInfo
	 * @throws BusinessException
	 */
	void updateAgencyInfo(AgencyInfoVo agencyInfo) throws BusinessException;

	/**
	 * 通过用户id获取中介结构的审核信息（管理员使用）
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	AgencyInfoVo getAuditAgencyInfoByUserId(String userId) throws BusinessException;

}
