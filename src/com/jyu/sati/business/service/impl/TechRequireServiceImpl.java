package com.jyu.sati.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.TechRequireInfoDao;
import com.jyu.sati.business.dao.TechnologyBaseInfoDao;
import com.jyu.sati.business.service.TechRequireService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.TechRequireInfo;
import com.jyu.sati.entity.TechnologyBaseInfo;
import com.jyu.sati.vo.TechOutlineVo;
import com.jyu.sati.vo.TechRequireInfoVo;
import com.jyu.sati.vo.TechnologyPageVo;

/**
 * 技术需求服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class TechRequireServiceImpl extends BaseServiceImpl implements TechRequireService {
	@Autowired
	private TechnologyBaseInfoDao tbiDao;
	@Autowired
	private TechRequireInfoDao trDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private AuditInfoDao auditDao;

	@Transactional
	@Override
	public TechRequireInfoVo createNewTechReqInfo() throws BusinessException {
		TechRequireInfoVo techInfoVo = new TechRequireInfoVo();
		try {
			ContactInfo contact = null;
			TechnologyBaseInfo baseInfo = null;
			TechRequireInfo techRep = null;
			AuditInfo auditInfo = null;

			// 获取用户的联系人信息
			try {
				contact = contactDao.getContactInfoByUserId(getCurrentUserId());
				contact.setContactId(null);
				contactDao.insertSelective(contact);
			} catch (Exception e) {
				errorLog("获取联系人失败！");
				throw e;
			}
			// 创建审核信息
			try {
				auditInfo = new AuditInfo();
				auditInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_NO_AUDIT);
				auditDao.insertSelective(auditInfo);
			} catch (Exception e) {
				errorLog("创建审核信息失败！");
				throw e;
			}
			// 创建技术基本信息
			try {
				baseInfo = new TechnologyBaseInfo();
				baseInfo.setAuditInfoId(auditInfo.getAuditInfoId());
				baseInfo.setContactInfoId(contact.getContactId());
				baseInfo.setPublisherId(getCurrentUserId());
				baseInfo.setStatus(TechnologyBaseInfo.INFO_STATUS_NOT_DEAL);
				tbiDao.insertSelective(baseInfo);
			} catch (Exception e) {
				errorLog("创建技术需求信息失败！");
				throw e;
			}
			try {
				techRep = new TechRequireInfo();
				techRep.setTbiId(baseInfo.getTbiId());
				trDao.insertSelective(techRep);
			} catch (Exception e) {
				errorLog("创建技术需求信息失败！");
				throw e;
			}

			techInfoVo = bindAuditInfo(techInfoVo, auditInfo);
			techInfoVo = bindContactInfo(techInfoVo, contact);
			techInfoVo = bindTechBaseInfo(techInfoVo, baseInfo);
			techInfoVo = bindTechRequireInfo(techInfoVo, techRep);

		} catch (Exception e) {
			errorLog("创建技术需求信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return techInfoVo;
	}

	/**
	 * 绑定技术技术需求信息
	 * 
	 * @param techInfoVo
	 * @param techReq
	 * @return
	 */
	private TechRequireInfoVo bindTechRequireInfo(TechRequireInfoVo techInfoVo, TechRequireInfo techReq)
			throws BusinessException {
		if (techReq == null) {
			throw new BusinessException("技术需求信息为空！");
		}
		techInfoVo.setTriId(techReq.getTriId());
		techInfoVo.setInvestmentFunds(techReq.getInvestmentFunds());
		techInfoVo.setTimeLimit(techReq.getTimeLimit());
		techInfoVo.setProblemDescription(techReq.getProblemDescription());

		return techInfoVo;
	}

	/**
	 * 绑定基本信息
	 * 
	 * @param techInfoVo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private TechRequireInfoVo bindTechBaseInfo(TechRequireInfoVo techInfoVo, TechnologyBaseInfo baseInfo)
			throws BusinessException {
		if (baseInfo == null) {
			throw new BusinessException("技术基本信息为空！");
		}
		techInfoVo.setTbiId(baseInfo.getTbiId());
		techInfoVo.setInfoTitle(baseInfo.getInfoTitle());
		techInfoVo.setInfoKey(baseInfo.getInfoKey());
		techInfoVo.setIndustryId(baseInfo.getIndustryId());
		techInfoVo.setHtfId(baseInfo.getHtfId());
		techInfoVo.setLocationId(baseInfo.getLocationId());
		techInfoVo.setSciId(baseInfo.getSciId());
		techInfoVo.setSeIndustryId(baseInfo.getSeIndustryId());
		techInfoVo.setCooperationModeId(baseInfo.getCooperationModeId());
		techInfoVo.setStatus(baseInfo.getStatus());
		techInfoVo.setCreateTime(baseInfo.getCreateTime());
		techInfoVo.setPublisherId(baseInfo.getPublisherId());
		techInfoVo.setViewTimes(baseInfo.getViewTimes());
		techInfoVo.setLimitStatus(baseInfo.getLimitStatus());
		techInfoVo.setSourceTypeId(baseInfo.getSourceTypeId());
		return techInfoVo;
	}

	/**
	 * 绑定联系人
	 * 
	 * @param techInfoVo
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	private TechRequireInfoVo bindContactInfo(TechRequireInfoVo techInfoVo, ContactInfo contact)
			throws BusinessException {
		if (contact != null) {
			techInfoVo.setContactInfoId(contact.getContactId());
			techInfoVo.setContactAddress(contact.getContactAddress());
			techInfoVo.setContactBusiness(contact.getContactBusiness());
			techInfoVo.setContactName(contact.getContactName());
			techInfoVo.setPostcode(contact.getPostcode());
			techInfoVo.setEmail(contact.getEmail());
			techInfoVo.setContactUrl(contact.getContactUrl());
			techInfoVo.setFaxNumber(contact.getFaxNumber());
			techInfoVo.setQqormsnNumer(contact.getQqormsnNumer());
			techInfoVo.setPhoneNumber(contact.getPhoneNumber());
			techInfoVo.setContactNumber(contact.getContactNumber());
		}
		return techInfoVo;
	}

	/**
	 * 绑定审核信息
	 * 
	 * @param techInfoVo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private TechRequireInfoVo bindAuditInfo(TechRequireInfoVo techInfoVo, AuditInfo auditInfo)
			throws BusinessException {
		if (auditInfo == null) {
			throw new BusinessException("审核信息为空！");
		}
		techInfoVo.setAuditInfoId(auditInfo.getAuditInfoId());
		techInfoVo.setSubmitTime(auditInfo.getSubmitTime());
		techInfoVo.setAuditTime(auditInfo.getAuditTime());
		techInfoVo.setAuditStatus(auditInfo.getAuditStatus());
		techInfoVo.setAuditResult(auditInfo.getAuditResult());
		techInfoVo.setUserId(auditInfo.getUserId());
		return techInfoVo;
	}

	@Transactional
	@Override
	public void submitTechSupplyInfo(TechRequireInfoVo techReqInfo) throws BusinessException {
		if (techReqInfo == null) {
			throw new BusinessException("传入参数为空！");
		}
		try {
			techReqInfo.setSubmitTime(new Date());
			techReqInfo.setCreateTime(new Date());// 设置发布时间
			techReqInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_WAITING);
			updateAuditInfo(techReqInfo);// 更新审核信息
			updateContactInfo(techReqInfo);// 更新联系人
			updateTechBaseInfo(techReqInfo);// 更新基本信息
			updateTechRequireInfo(techReqInfo);// 更新技术需求信息

		} catch (Exception e) {
			errorLog("保存技术需求信息失败！", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public void saveTechReqInfo(TechRequireInfoVo techReqInfo) throws BusinessException {
		if (techReqInfo == null) {
			throw new BusinessException("传入参数为空！");
		}
		try {
			techReqInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_NO_AUDIT);
			updateAuditInfo(techReqInfo);// 更新审核信息
			updateContactInfo(techReqInfo);// 更新联系人
			updateTechBaseInfo(techReqInfo);// 更新基本信息
			updateTechRequireInfo(techReqInfo);// 更新技术需求信息

		} catch (Exception e) {
			errorLog("保存技术需求信息失败！", e);
			throw e;
		}
	}

	/**
	 * 更新技术需求信息
	 * 
	 * @param techInfoVo
	 * @param techReq
	 * @return
	 */
	private void updateTechRequireInfo(TechRequireInfoVo techInfoVo) throws BusinessException {
		if (techInfoVo == null) {
			throw new BusinessException("技术需求vo信息为空！");
		}
		try {
			TechRequireInfo techReq = new TechRequireInfo();
			techReq.setTriId(techInfoVo.getTriId());
			techReq.setInvestmentFunds(techInfoVo.getInvestmentFunds());
			techReq.setTimeLimit(techInfoVo.getTimeLimit());
			techReq.setProblemDescription(techInfoVo.getProblemDescription());
			trDao.updateByPrimaryKeySelective(techReq);

		} catch (Exception e) {
			errorLog("更新技术需求信息失败！", e);
			throw e;
		}
	}

	/**
	 * 更新基本信息
	 * 
	 * @param techInfoVo
	 * @return
	 * @throws BusinessException
	 */
	private void updateTechBaseInfo(TechRequireInfoVo techInfoVo) throws BusinessException {

		try {
			TechnologyBaseInfo baseInfo = new TechnologyBaseInfo();
			baseInfo.setTbiId(techInfoVo.getTbiId());
			baseInfo.setInfoTitle(techInfoVo.getInfoTitle());
			baseInfo.setInfoKey(techInfoVo.getInfoKey());
			baseInfo.setIndustryId(techInfoVo.getIndustryId());
			baseInfo.setHtfId(techInfoVo.getHtfId());
			baseInfo.setLocationId(techInfoVo.getLocationId());
			baseInfo.setSciId(techInfoVo.getSciId());
			baseInfo.setSeIndustryId(techInfoVo.getSeIndustryId());
			baseInfo.setCooperationModeId(techInfoVo.getCooperationModeId());
			baseInfo.setStatus(techInfoVo.getStatus());
			baseInfo.setCreateTime(techInfoVo.getCreateTime());
			baseInfo.setPublisherId(techInfoVo.getPublisherId());
			baseInfo.setViewTimes(techInfoVo.getViewTimes());
			baseInfo.setLimitStatus(techInfoVo.getLimitStatus());
			baseInfo.setSourceTypeId(techInfoVo.getSourceTypeId());

			tbiDao.updateByPrimaryKeySelective(baseInfo);
		} catch (Exception e) {
			errorLog("更新基本信息失败！", e);
			throw e;
		}
	}

	/**
	 * 更新联系人
	 * 
	 * @param techInfoVo
	 * @return
	 * @throws BusinessException
	 */
	private void updateContactInfo(TechRequireInfoVo techInfoVo) throws BusinessException {
		try {
			ContactInfo contact = new ContactInfo();
			contact.setContactId(techInfoVo.getContactInfoId());
			contact.setContactAddress(techInfoVo.getContactAddress());
			contact.setContactBusiness(techInfoVo.getContactBusiness());
			contact.setContactName(techInfoVo.getContactName());
			contact.setPostcode(techInfoVo.getPostcode());
			contact.setEmail(techInfoVo.getEmail());
			contact.setContactUrl(techInfoVo.getContactUrl());
			contact.setFaxNumber(techInfoVo.getFaxNumber());
			contact.setQqormsnNumer(techInfoVo.getQqormsnNumer());
			contact.setPhoneNumber(techInfoVo.getPhoneNumber());
			contact.setContactNumber(techInfoVo.getContactNumber());
			contactDao.updateByPrimaryKeySelective(contact);
		} catch (Exception e) {
			errorLog("更新联系人信息失败！", e);
			throw e;
		}
	}

	/**
	 * 更新审核信息
	 * 
	 * @param techInfoVo
	 * @return
	 * @throws BusinessException
	 */
	private void updateAuditInfo(TechRequireInfoVo techInfoVo) throws BusinessException {
		try {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setAuditInfoId(techInfoVo.getAuditInfoId());
			auditInfo.setSubmitTime(techInfoVo.getSubmitTime());
			auditInfo.setAuditTime(techInfoVo.getAuditTime());
			auditInfo.setAuditStatus(techInfoVo.getAuditStatus());
			auditInfo.setAuditResult(techInfoVo.getAuditResult());
			auditInfo.setUserId(techInfoVo.getUserId());
			auditDao.updateByPrimaryKeySelective(auditInfo);
		} catch (Exception e) {
			errorLog("更新审核信息失败！", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public TechRequireInfoVo getTechReqInfoById(Integer techReqId) throws BusinessException {
		if (techReqId == null) {
			throw new BusinessException("技术供给id为空！");
		}
		TechRequireInfoVo techInfoVo = new TechRequireInfoVo();
		try {
			TechRequireInfo techReq = trDao.selectByPrimaryKey(techReqId);
			TechnologyBaseInfo baseInfo = tbiDao.selectByPrimaryKey(techReq.getTbiId());
			ContactInfo contact = contactDao.selectByPrimaryKey(baseInfo.getContactInfoId());
			AuditInfo auditInfo = auditDao.selectByPrimaryKey(baseInfo.getAuditInfoId());

			techInfoVo = bindAuditInfo(techInfoVo, auditInfo);
			techInfoVo = bindContactInfo(techInfoVo, contact);
			techInfoVo = bindTechBaseInfo(techInfoVo, baseInfo);
			techInfoVo = bindTechRequireInfo(techInfoVo, techReq);

		} catch (Exception e) {
			errorLog("获取技术需求信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return techInfoVo;
	}

	@Transactional
	@Override
	public TechRequireInfoVo getAuditTechReqInfoById(Integer techReqId) throws BusinessException {
		if (techReqId == null) {
			errorLog("传入需求信息id为空！");
			throw new BusinessException(getMsg());
		}
		TechRequireInfoVo techReq = new TechRequireInfoVo();
		try {

			try {
				trDao.updateAuditStatusAndAuditorId(techReqId, AuditInfo.AUDIT_STATUS_AUDITING, getCurrentUserId());
			} catch (Exception e) {
				errorLog("更新技术需求信息审核状态出错！", e);
				throw new BusinessException(getMsg());
			}
			techReq = getTechReqInfoById(techReqId);
		} catch (Exception e) {
			errorLog("获取技术需求信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return techReq;
	}

	@Transactional
	@Override
	public void removeTechReqInfo(Integer techReqId) throws BusinessException {
		if (techReqId == null) {
			errorLog("传入参数为空！");
			throw new BusinessException(getMsg());
		}
		String publisher = null;
		try {
			publisher = trDao.getPublisherIdByTriId(techReqId);
		} catch (Exception e) {
			errorLog("获取发布人失败！", e);
			throw new BusinessException(getMsg());
		}
		if (StringUtil.isEmpty(publisher)) {
			errorLog("技术需求信息异常！");
			throw new BusinessException(getMsg());
		}
		if (!publisher.equals(getCurrentUserId())) {
			errorLog("不是该技术需求信息的发布人！");
			throw new BusinessException(getMsg());
		}
		try {
			TechRequireInfo techReq = trDao.selectByPrimaryKey(techReqId);
			TechnologyBaseInfo baseInfo = tbiDao.selectByPrimaryKey(techReq.getTbiId());
			try {
				trDao.deleteByPrimaryKey(techReqId);
			} catch (Exception e) {
				errorLog("删除技术需求信息失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				tbiDao.deleteByPrimaryKey(baseInfo.getTbiId());
			} catch (Exception e) {
				errorLog("删除技术需求信息失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				contactDao.deleteByPrimaryKey(baseInfo.getContactInfoId());
			} catch (Exception e) {
				errorLog("删除联系人信息失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				auditDao.deleteByPrimaryKey(baseInfo.getAuditInfoId());
			} catch (Exception e) {
				errorLog("删除审核信息失败！", e);
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("删除技术需求信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void updateTechReqStatus(Integer techReqId) throws BusinessException {
		if (techReqId == null) {
			throw new BusinessException("技术需求id为空！");
		}
		try {
			trDao.updateTechReqStatus(techReqId);
		} catch (Exception e) {
			errorLog("更新技术需求信息状态失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public Integer getTechReqCountByCondition(TechnologyPageVo condition) throws BusinessException {
		if (condition == null) {
			throw new BusinessException("传入参数为空！");
		}
		Integer result = 0;
		try {
			result = trDao.getTotalNoByCondition(condition);
		} catch (Exception e) {
			errorLog("获取技术需求信息数目失败！", e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Transactional
	@Override
	public List<TechOutlineVo> getTechReqPageByCondition(TechnologyPageVo condition) throws BusinessException {
		if (condition == null) {
			throw new BusinessException("传入参数为空！");
		}
		List<TechOutlineVo> result = null;
		try {
			condition.setTotalNo(trDao.getTotalNoByCondition(condition));
			result = trDao.getTechPageByCondition(condition);
		} catch (Exception e) {
			errorLog("获取技术需求信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public void updateTechReqLimitStatus(Integer techReqId) throws BusinessException {
		if (techReqId == null) {
			throw new BusinessException("技术需求id为空！");
		}
		try {
			trDao.updateTechReqLimitStatus(techReqId);
		} catch (Exception e) {
			errorLog("更新技术需求信息可见状态失败！", e);
			throw new BusinessException(getMsg());
		}
	}

}
