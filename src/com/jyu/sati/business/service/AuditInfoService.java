package com.jyu.sati.business.service;

import java.util.List;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.vo.AuditInfoVo;

public interface AuditInfoService {

	/**
	 * 通过审核信息id获取审核信息
	 * 
	 * @param auditInfoId
	 * @return
	 */
	AuditInfo getAuditInfoById(Integer auditInfoId) throws BusinessException;

	/**
	 * 插入新的审核信息
	 * 
	 * @param audiInfo
	 * @return
	 */
	int insert(AuditInfo audiInfo) throws BusinessException;

	/**
	 * 更新
	 * 
	 * @param audiInfo
	 * @return
	 * @throws BusinessException
	 */
	int update(AuditInfo audiInfo) throws BusinessException;


	/**
	 * 根据分页条件获取审核信息分页数据
	 * 
	 * @param condition
	 * @return
	 * @throws BusinessException
	 */
	List<AuditInfo> getAuditInfoPageByCondition(AuditInfoVo condition) throws BusinessException;

	/**
	 * 根据用户id获取用户信息的审核状态-
	 * 
	 * @param userId
	 * @return
	 */
	Integer getUserInfoAuditStatusByUserId(String userId);

	/**
	 * 获取对应用户类型的 ，未审核的，用户审核信息，总数
	 * 
	 * @param userType
	 *            审核的用户类型
	 * @return
	 */
	Integer getUnAuditUserInfosCountByUserType(Integer userType);

	/**
	 * 管理员提交对信息的审核结果
	 * 
	 * @param auditInfo
	 *            审核结果
	 * @param auditorId
	 *            审核人
	 * @throws BusinessException
	 */
	void auditInfo(AuditInfo auditInfo, String auditorId) throws BusinessException;

	/**
	 * 更新用户信息审核状态
	 * 
	 * @param userId
	 * @param auditStatus
	 * @param auditorId
	 */
	void updateUserInfoAuditStatus(String userId, Integer auditStatus, String auditorI0d) throws BusinessException;

}
