package com.jyu.sati.business.service;

import java.util.List;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.vo.TechOutlineVo;
import com.jyu.sati.vo.TechRequireInfoVo;
import com.jyu.sati.vo.TechnologyPageVo;

/**
 * 技术需求服务
 * 
 * @author 淋雨又调皮
 *
 */
public interface TechRequireService {
	/**
	 * 创建新的技术需求信息
	 * 
	 * @return
	 * @throws BusinessException
	 */
	TechRequireInfoVo createNewTechReqInfo() throws BusinessException;

	/**
	 * 提交技术需求信息
	 * 
	 * @param techReqInfo
	 * @throws BusinessException
	 */
	void submitTechSupplyInfo(TechRequireInfoVo techReqInfo) throws BusinessException;

	/**
	 * 保存未提交的技术需求信息
	 * 
	 * @param techReqInfo
	 * @throws BusinessException
	 */
	void saveTechReqInfo(TechRequireInfoVo techReqInfo) throws BusinessException;

	/**
	 * 通过id获取技术需求信息(用户使用)
	 * 
	 * @param techReqId
	 * @return
	 * @throws BusinessException
	 */
	TechRequireInfoVo getTechReqInfoById(Integer techReqId) throws BusinessException;

	/**
	 * 通过id获取技术需求信息（管理员使用）
	 * 
	 * @param techReqId
	 * @return
	 * @throws BusinessException
	 */
	TechRequireInfoVo getAuditTechReqInfoById(Integer techReqId) throws BusinessException;

	/**
	 * 删除指定的技术需求信息
	 * 
	 * @param techReqId
	 * @throws BusinessException
	 */
	void removeTechReqInfo(Integer techReqId) throws BusinessException;

	/**
	 * 更新指定的技术需求信息状态
	 * 
	 * @param techReqId
	 * @throws BusinessException
	 */
	void updateTechReqStatus(Integer techReqId) throws BusinessException;

	/**
	 * 更新指定的技术需求信息可见状态
	 * 
	 * @param techReqId
	 * @throws BusinessException
	 */
	void updateTechReqLimitStatus(Integer techReqId) throws BusinessException;

	/**
	 * 根据查询条件获取一页技术需求数据
	 *
	 * @param condition
	 * @throws BusinessException
	 */
	public List<TechOutlineVo> getTechReqPageByCondition(TechnologyPageVo condition) throws BusinessException;

	/**
	 * 根据查询条件获取技术需求数量
	 * 
	 * @param condition
	 * @return
	 * @throws BusinessException
	 */
	public Integer getTechReqCountByCondition(TechnologyPageVo condition) throws BusinessException;
}
