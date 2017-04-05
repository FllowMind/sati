package com.jyu.sati.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.SystemLogDao;
import com.jyu.sati.business.service.AuditInfoService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.SystemLogUtil;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.SystemLog;
import com.jyu.sati.vo.AuditInfoVo;

/**
 * 审核信息服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class AuditInfoServiceImpl extends BaseServiceImpl implements AuditInfoService {

	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private SystemLogDao logDao;

	@Transactional(readOnly = true)
	@Override
	public AuditInfo getAuditInfoById(Integer auditInfoId) throws BusinessException {
		// TODO Auto-generated method stub
		return auditDao.selectByPrimaryKey(auditInfoId);
	}

	@Transactional
	@Override
	public int insert(AuditInfo audiInfo) throws BusinessException {
		// TODO Auto-generated method stub
		return auditDao.insertSelective(audiInfo);
	}

	@Transactional
	@Override
	public int update(AuditInfo audiInfo) throws BusinessException {
		// TODO Auto-generated method stub
		return auditDao.updateByPrimaryKeySelective(audiInfo);
	}

	@Transactional
	@Override
	public void updateUserInfoAuditStatus(String userId, Integer status, String auditorId) throws BusinessException {
		try {
			if (auditDao.updateUserInfoAuditStatus(userId, status, auditorId) < 1) {
				errorLog("更新审核状态失败！");
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("更新审核状态失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public List<AuditInfo> getAuditInfoPageByCondition(AuditInfoVo condition) throws BusinessException {
		// TODO Auto-generated method stub
		List<AuditInfo> auditInfos = null;
		try {
			condition.setTotalNo(auditDao.getTotalNo(condition));
			auditInfos = auditDao.getAuditOutlineByCondition(condition);
		} catch (Exception e) {
			errorLog("获取审核信息失败！", condition.toString(), e);
			throw new BusinessException(getMsg());
		}
		return auditInfos;
	}

	@Transactional(readOnly = true)
	@Override
	public Integer getUserInfoAuditStatusByUserId(String userId) {
		Integer result = null;
		try {
			result = auditDao.getUserInfoAuditStatusByUserId(userId);
			if (result == null) {
				errorLog("获取用户信息的审核状态失败！");
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("获取用户信息的审核状态失败！", e);
			throw new BusinessException(getMsg());
		}

		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public Integer getUnAuditUserInfosCountByUserType(Integer userType) {
		Integer result = 0;
		try {
			result = auditDao.getUnAuditUserInfosCountByUserType(userType);
		} catch (Exception e) {
			errorLog("获取指定用户类型未审核用户信息数目失败！", userType.toString(), e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Transactional
	@Override
	public void auditInfo(AuditInfo auditInfo, String auditorId) throws BusinessException {
		try {
			auditInfo.setAuditorId(auditorId);
			auditInfo.setAuditTime(new Date());
			// 更新数据库
			auditDao.updateByPrimaryKeySelective(auditInfo);
			// 写入系统日志
			switch (auditInfo.getAuditType()) {
			case AuditInfo.AUDIT_TYPE_USER_INFO:
				addSystemLog(
						SystemLogUtil.getUserAuditLog(getCurrentUserId(), "【审核】了【" + auditInfo.getAuditorId() + "】"));
				break;
			case AuditInfo.AUDIT_TYPE_TECH_SUPPLY:
				addSystemLog(
						SystemLogUtil.getTechSupplyLog(getCurrentUserId(), "【审核】了【" + auditInfo.getAuditorId() + "】"));
				break;
			case AuditInfo.AUDIT_TYPE_TECH_REQUIRE:
				addSystemLog(
						SystemLogUtil.getTechRequireLog(getCurrentUserId(), "【审核】了【" + auditInfo.getAuditorId() + "】"));
				break;
			default:
				break;
			}
		} catch (Exception e) {
			errorLog("提交审核结果失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 添加系统日志
	 * 
	 * @param log
	 * @throws BusinessException
	 */
	private void addSystemLog(SystemLog log) throws BusinessException {
		try {
			logDao.insertSelective(log);
		} catch (Exception e) {
			errorLog("添加系统日志失败！", e);
			throw new BusinessException(getMsg());
		}
	}
}
