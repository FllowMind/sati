package com.jyu.sati.business.service;

import java.util.List;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.vo.TechOutlineVo;
import com.jyu.sati.vo.TechSupplyInfoVo;
import com.jyu.sati.vo.TechnologyPageVo;

/**
 * 
 * 技术供给服务
 * 
 * @author 淋雨又调皮
 *
 */
public interface TechSupplyService {

	/**
	 * 创建新的技术供给信息
	 * 
	 * @return
	 * @throws BusinessException
	 */
	TechSupplyInfoVo createTechSupplyInfo() throws BusinessException;

	/**
	 * 提交技术供给信息
	 * 
	 * @param techSupInfo
	 * @throws BusinessException
	 */
	void submitTechSupplyInfo(TechSupplyInfoVo techSupInfo) throws BusinessException;

	/**
	 * 保存技术供给信息
	 * 
	 * @param techSupInfo
	 * @throws BusinessException
	 */
	void saveTechSupplyInfo(TechSupplyInfoVo techSupInfo) throws BusinessException;

	/**
	 * 通过id获取技术供给信息(用户使用)
	 * 
	 * @param techSupId
	 * @return
	 * @throws BusinessException
	 */
	TechSupplyInfoVo getTechSupById(Integer techSupId) throws BusinessException;

	/**
	 * 通过id获取技术供给信息（管理员使用）
	 * 
	 * @param techSupId
	 * @return
	 * @throws BusinessException
	 */
	TechSupplyInfoVo getAuditTechSupById(Integer techSupId) throws BusinessException;

	/**
	 * 删除指定的技术供给信息
	 * 
	 * @param techSupId
	 * @throws BusinessException
	 */
	void removeTechSupInfo(Integer techSupId) throws BusinessException;

	/**
	 * 更新指定的技术信息状态
	 * 
	 * @param techSupId
	 * @throws BusinessException
	 */
	void updateTechSupStatus(Integer techSupId) throws BusinessException;

	/**
	 * 更新指定的技术信息是否被禁用状态
	 * 
	 * @param techSupId
	 * @throws BusinessException
	 */
	void updateTechSupLimitStatus(Integer tsiId) throws BusinessException;

	/**
	 * 根据查询条件获取一页技术供给数据
	 *
	 * @param condition
	 * @throws BusinessException
	 */
	public List<TechOutlineVo> getTechSupPageByCondition(TechnologyPageVo condition) throws BusinessException;

	/**
	 * 根据查询条件技术供给数量
	 * 
	 * @param condition
	 * @return
	 * @throws BusinessException
	 */
	public Integer getTechSupCountByCondition(TechnologyPageVo condition) throws BusinessException;
}
