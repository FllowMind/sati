package com.jyu.sati.business.service;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Company;
import com.jyu.sati.vo.CompanyInfoVo;

public interface CompanyService {

	int delete(Integer companyId) throws BusinessException;

	int insert(Company record) throws BusinessException;

	int insertSelective(Company record) throws BusinessException;

	Company getCompanyById(Integer companyId) throws BusinessException;

	/**
	 * 更新非空字段
	 * 
	 * @param record
	 * @return
	 */
	int updateSelective(Company record) throws BusinessException;

	int update(Company record) throws BusinessException;

	/**
	 * 获取企业信息
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	CompanyInfoVo getCompanyInfoByUserId(String userId) throws BusinessException;

	/**
	 * 更新企业信息
	 * 
	 * @param companyInfo
	 * @throws BusinessException
	 */
	void updateCompanyInfo(CompanyInfoVo companyInfo) throws BusinessException;

	/**
	 * 获取企业的审核信息（管理员使用）
	 * 
	 * @param userId
	 * @return
	 * @throws BusinessException
	 */
	CompanyInfoVo getAuditCompanyInfoByUserId(String userId) throws BusinessException;
}
