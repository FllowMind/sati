package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.TechRequireInfo;
import com.jyu.sati.vo.TechOutlineVo;
import com.jyu.sati.vo.TechnologyPageVo;

public interface TechRequireInfoDao {
	int deleteByPrimaryKey(Integer triId);

	int insert(TechRequireInfo record);

	int insertSelective(TechRequireInfo record);

	TechRequireInfo selectByPrimaryKey(Integer triId);

	int updateByPrimaryKeySelective(TechRequireInfo record);

	int updateByPrimaryKey(TechRequireInfo record);

	/**
	 * 获取指定技术需求的发布人信息
	 * 
	 * @param triId
	 * @return
	 */
	String getPublisherIdByTriId(@Param("triId") Integer triId);

	/**
	 * 更新指定的技术需求状态
	 * 
	 * @param tirId
	 * @return
	 */
	int updateTechReqStatus(@Param("triId") Integer tirId);

	/**
	 * 更新指定的技术需求是否被禁用状态
	 * 
	 * @param tirId
	 * @return
	 */
	int updateTechReqLimitStatus(@Param("triId") Integer tirId);

	/**
	 * 更新审核状态和审核人
	 * 
	 * @param triId
	 *            需求信息id
	 * @param auditStatus
	 *            审核状态
	 * @param auditorId
	 *            审核人
	 * @return
	 */
	int updateAuditStatusAndAuditorId(@Param("triId") Integer triId, @Param("auditStatus") Integer auditStatus,
			@Param("auditorId") String auditorId);

	/**
	 * 根据条件获取一页技术需求数据
	 * 
	 * @param condition
	 * @return
	 */
	List<TechOutlineVo> getTechPageByCondition(TechnologyPageVo condition);

	/**
	 * 根据条件获取技术需求总数
	 * 
	 * @param condition
	 * @return
	 */
	Integer getTotalNoByCondition(TechnologyPageVo condition);
}