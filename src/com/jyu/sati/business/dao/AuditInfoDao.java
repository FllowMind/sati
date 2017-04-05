package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.vo.AuditInfoVo;

/**
 * @author 淋雨又调皮
 *
 */
/**
 * @author 淋雨又调皮
 *
 */
public interface AuditInfoDao {
	int deleteByPrimaryKey(Integer auditInfoId);

	int insert(AuditInfo record);

	int insertSelective(AuditInfo record);

	AuditInfo selectByPrimaryKey(Integer auditInfoId);

	int updateByPrimaryKeySelective(AuditInfo record);

	int updateByPrimaryKey(AuditInfo record);

	/**
	 * 功能描述
	 * 
	 * @param userId
	 * @return
	 */
	AuditInfo getAuditInfoByUserId(@Param("userId") String userId);

	/**
	 * 更新用户审核状态
	 * 
	 * @param userId
	 * @param status
	 * @param auditorId
	 * @return
	 */
	int updateUserInfoAuditStatus(@Param("userId") String userId, @Param("status") Integer status,
			@Param("auditorId") String auditorId);

	/**
	 * 获取分页审核信息
	 * 
	 * @param condition
	 * @return
	 */
	List<AuditInfo> getAuditOutlineByCondition(AuditInfoVo condition);

	/**
	 * 根据条件获取数据总条数
	 * 
	 * @param condition
	 * @return
	 */
	int getTotalNo(AuditInfoVo condition);

	/**
	 * 根据用户id获取用户信息的审核状态-
	 * 
	 * @param userId
	 * @return
	 */
	Integer getUserInfoAuditStatusByUserId(@Param("userId") String userId);

	/**
	 * 获取对应用户类型的 ，未审核的，用户审核信息，总数
	 * 
	 * @param userType
	 *            审核的用户类型
	 * @return
	 */
	Integer getUnAuditUserInfosCountByUserType(@Param("userType") Integer userType);

	/**
	 * 根据产品成果id获取产品成果审核信息
	 * 
	 * @param produceId
	 * @return
	 */
	AuditInfo getAuditInfoByProduceId(@Param("produceId") Integer produceId);

	/**
	 * 更新产品的审核状态
	 * 
	 * @param produceId
	 * @param auditStatus
	 * @return
	 */
	Integer updateProduceAuditStatus(@Param("produceId") Integer produceId, @Param("auditStatus") Integer auditStatus,
			@Param("auditorId") String auditorId);

	/**
	 * 更新技术供给的审核状态
	 * 
	 * @param techId
	 *            技术需求id
	 * @param auditStatus
	 * @return
	 */
	Integer updateTechSupAuditStatus(@Param("techId") Integer techId, @Param("auditStatus") Integer auditStatus,
			@Param("auditorId") String aditorId);

	/**
	 * 更新技术需求的审核状态
	 * 
	 * @param techId
	 *            技术供给id
	 * @param auditStatus
	 * @return
	 */
	Integer updateTechReqAuditStatus(@Param("techId") Integer techId, @Param("auditStatus") Integer auditStatus,
			@Param("auditorId") String aditorId);

}