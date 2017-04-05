package com.jyu.sati.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.TechEnclosureDao;
import com.jyu.sati.business.dao.TechSupplyInfoDao;
import com.jyu.sati.business.dao.TechnologyBaseInfoDao;
import com.jyu.sati.business.service.TechSupplyService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.common.util.UploadUtil;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.TechEnclosure;
import com.jyu.sati.entity.TechSupplyInfo;
import com.jyu.sati.entity.TechnologyBaseInfo;
import com.jyu.sati.vo.TechOutlineVo;
import com.jyu.sati.vo.TechSupplyInfoVo;
import com.jyu.sati.vo.TechnologyPageVo;

/**
 * 技术供给服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class TechSupplyServiceImpl extends BaseServiceImpl implements TechSupplyService {

	@Autowired
	private TechnologyBaseInfoDao tbiDao;
	@Autowired
	private TechSupplyInfoDao tsDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private TechEnclosureDao enclosureDao;

	@Transactional
	@Override
	public TechSupplyInfoVo createTechSupplyInfo() throws BusinessException {
		TechSupplyInfoVo techInfoVo = new TechSupplyInfoVo();
		try {
			ContactInfo contact = null;
			TechnologyBaseInfo baseInfo = null;
			TechSupplyInfo techSup = null;
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
				errorLog("创建技术供给信息失败！");
				throw e;
			}
			try {
				techSup = new TechSupplyInfo();
				techSup.setTbiId(baseInfo.getTbiId());
				tsDao.insertSelective(techSup);
			} catch (Exception e) {
				errorLog("创建技术供给信息失败！");
				throw e;
			}

			techInfoVo = bindAuditInfo(techInfoVo, auditInfo);
			techInfoVo = bindContactInfo(techInfoVo, contact);
			techInfoVo = bindTechBaseInfo(techInfoVo, baseInfo);
			techInfoVo = bindTechSupplyInfo(techInfoVo, techSup);

		} catch (Exception e) {
			errorLog("创建技术供给信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return techInfoVo;
	}

	/**
	 * 绑定技术供给图片信息
	 * 
	 * @param techInfoVo
	 * @param image
	 * @return
	 * @throws BusinessException
	 */
	private TechSupplyInfoVo bindImageEclosure(TechSupplyInfoVo techInfoVo, TechEnclosure image)
			throws BusinessException {
		if (image == null) {
			image = new TechEnclosure();
		}
		techInfoVo.setImageId(image.getEnclosureId());
		techInfoVo.setImageDesc(image.getEnclosureDesc());
		techInfoVo.setImageUrl(image.getEnclosureUrl());

		return techInfoVo;
	}

	/**
	 * 绑定技术供给图片信息
	 * 
	 * @param techInfoVo
	 * @param image
	 * @return
	 * @throws BusinessException
	 */
	private TechSupplyInfoVo bindVideoEclosure(TechSupplyInfoVo techInfoVo, TechEnclosure video)
			throws BusinessException {
		if (video == null) {
			video = new TechEnclosure();
		}
		techInfoVo.setVideoId(video.getEnclosureId());
		techInfoVo.setVideoDesc(video.getEnclosureDesc());
		techInfoVo.setVideoUrl(video.getEnclosureUrl());

		return techInfoVo;
	}

	/**
	 * 绑定技术供给图片信息
	 * 
	 * @param techInfoVo
	 * @param image
	 * @return
	 * @throws BusinessException
	 */
	private TechSupplyInfoVo bindTextEclosure(TechSupplyInfoVo techInfoVo, TechEnclosure text)
			throws BusinessException {
		if (text == null) {
			text = new TechEnclosure();
		}
		techInfoVo.setTextId(text.getEnclosureId());
		techInfoVo.setTextDesc(text.getEnclosureDesc());
		techInfoVo.setTextUrl(text.getEnclosureUrl());

		return techInfoVo;
	}

	/**
	 * 绑定技术供给信息
	 * 
	 * @param techInfoVo
	 * @param techSup
	 * @return
	 * @throws BusinessException
	 */
	private TechSupplyInfoVo bindTechSupplyInfo(TechSupplyInfoVo techInfoVo, TechSupplyInfo techSup)
			throws BusinessException {
		if (techSup == null) {
			throw new BusinessException("技术供给信息为空！");
		}
		techInfoVo.setTsiId(techSup.getTsiId());
		techInfoVo.setIntellectualTypeId(techSup.getIntellectualTypeId());
		techInfoVo.setIntellectualCode(techSup.getIntellectualCode());
		techInfoVo.setResultFormId(techSup.getResultFormId());
		techInfoVo.setResultPropertyId(techSup.getResultPropertyId());
		techInfoVo.setResultStageId(techSup.getResultStageId());
		techInfoVo.setResultLevelId(techSup.getResultLevelId());
		techInfoVo.setReserachFormId(techSup.getReserachFormId());
		techInfoVo.setTopicSourceId(techSup.getTopicSourceId());
		techInfoVo.setTechnologyMaturityId(techSup.getTechnologyMaturityId());
		techInfoVo.setSourceTypeId(techSup.getSourceTypeId());
		techInfoVo.setPrice(techSup.getPrice());
		techInfoVo.setResultIntroduction(techSup.getResultIntroduction());
		techInfoVo.setApplicationRange(techSup.getApplicationRange());
		techInfoVo.setProspectAnalysis(techSup.getProspectAnalysis());
		techInfoVo.setFirstUnit(techSup.getFirstUnit());

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
	private TechSupplyInfoVo bindTechBaseInfo(TechSupplyInfoVo techInfoVo, TechnologyBaseInfo baseInfo)
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
		techInfoVo.setImageId(baseInfo.getImageId());
		techInfoVo.setVideoId(baseInfo.getVideoId());
		techInfoVo.setTextId(baseInfo.getTextId());
		techInfoVo.setStatus(baseInfo.getStatus());
		techInfoVo.setCreateTime(baseInfo.getCreateTime());
		techInfoVo.setPublisherId(baseInfo.getPublisherId());
		techInfoVo.setViewTimes(baseInfo.getViewTimes());
		techInfoVo.setLimitStatus(baseInfo.getLimitStatus());

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
	private TechSupplyInfoVo bindContactInfo(TechSupplyInfoVo techInfoVo, ContactInfo contact)
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
	private TechSupplyInfoVo bindAuditInfo(TechSupplyInfoVo techInfoVo, AuditInfo auditInfo) throws BusinessException {
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
	public void submitTechSupplyInfo(TechSupplyInfoVo techSupInfo) throws BusinessException {
		if (techSupInfo == null) {
			throw new BusinessException("传入参数为空！");
		}
		try {
			techSupInfo.setSubmitTime(new Date());
			techSupInfo.setCreateTime(new Date());
			techSupInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_WAITING);

			updateAuditInfo(techSupInfo);// 更新审核信息
			updateContactInfo(techSupInfo);// 更新联系人
			updateTechBaseInfo(techSupInfo);// 更新基本信息
			updateTechSupplyInfo(techSupInfo);// 更新技术供给
		} catch (Exception e) {
			errorLog("提交供给信息失败！", e);
			throw e;
		}
	}

	@Transactional
	@Override
	public void saveTechSupplyInfo(TechSupplyInfoVo techSupInfo) throws BusinessException {
		if (techSupInfo == null) {
			throw new BusinessException("传入参数为空！");
		}
		try {
			techSupInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_NO_AUDIT);
			updateAuditInfo(techSupInfo);// 更新审核信息
			updateContactInfo(techSupInfo);// 更新联系人
			updateTechBaseInfo(techSupInfo);// 更新基本信息
			updateTechSupplyInfo(techSupInfo);// 更新技术供给
		} catch (Exception e) {
			errorLog("保存供给信息失败！", e);
			throw e;
		}
	}

	/**
	 * 绑定技术供给信息
	 * 
	 * @param techInfoVo
	 * @param techSup
	 * @return
	 * @throws BusinessException
	 */
	private void updateTechSupplyInfo(TechSupplyInfoVo techInfoVo) throws BusinessException {
		try {
			TechSupplyInfo techSup = new TechSupplyInfo();
			techSup.setTsiId(techInfoVo.getTsiId());
			techSup.setIntellectualTypeId(techInfoVo.getIntellectualTypeId());
			techSup.setIntellectualCode(techInfoVo.getIntellectualCode());
			techSup.setResultFormId(techInfoVo.getResultFormId());
			techSup.setResultPropertyId(techInfoVo.getResultPropertyId());
			techSup.setResultStageId(techInfoVo.getResultStageId());
			techSup.setResultLevelId(techInfoVo.getResultLevelId());
			techSup.setReserachFormId(techInfoVo.getReserachFormId());
			techSup.setTopicSourceId(techInfoVo.getTopicSourceId());
			techSup.setTechnologyMaturityId(techInfoVo.getTechnologyMaturityId());
			techSup.setSourceTypeId(techInfoVo.getSourceTypeId());
			techSup.setPrice(techInfoVo.getPrice());
			techSup.setResultIntroduction(techInfoVo.getResultIntroduction());
			techSup.setApplicationRange(techInfoVo.getApplicationRange());
			techSup.setProspectAnalysis(techInfoVo.getProspectAnalysis());
			techSup.setFirstUnit(techInfoVo.getFirstUnit());

			tsDao.updateByPrimaryKeySelective(techSup);
		} catch (Exception e) {
			errorLog("更新供给信息失败！", e);
			throw e;
		}
	}

	/**
	 * 更新基本信息
	 * 
	 * @param techInfoVo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateTechBaseInfo(TechSupplyInfoVo techInfoVo) throws BusinessException {

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
			baseInfo.setImageId(techInfoVo.getImageId());
			baseInfo.setVideoId(techInfoVo.getVideoId());
			baseInfo.setTextId(techInfoVo.getTextId());
			baseInfo.setStatus(techInfoVo.getStatus());
			baseInfo.setCreateTime(techInfoVo.getCreateTime());
			baseInfo.setPublisherId(techInfoVo.getPublisherId());
			baseInfo.setViewTimes(techInfoVo.getViewTimes());
			baseInfo.setLimitStatus(techInfoVo.getLimitStatus());

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
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	private void updateContactInfo(TechSupplyInfoVo techInfoVo) throws BusinessException {
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
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateAuditInfo(TechSupplyInfoVo techInfoVo) throws BusinessException {
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
	public TechSupplyInfoVo getTechSupById(Integer techSupId) throws BusinessException {
		if (techSupId == null) {
			throw new BusinessException("技术供给id为空！");
		}
		TechSupplyInfoVo techInfoVo = new TechSupplyInfoVo();
		try {
			TechSupplyInfo techSup = tsDao.selectByPrimaryKey(techSupId);
			TechnologyBaseInfo baseInfo = tbiDao.selectByPrimaryKey(techSup.getTbiId());
			ContactInfo contact = contactDao.selectByPrimaryKey(baseInfo.getContactInfoId());
			AuditInfo auditInfo = auditDao.selectByPrimaryKey(baseInfo.getAuditInfoId());
			TechEnclosure image = enclosureDao.selectByPrimaryKey(baseInfo.getImageId());
			TechEnclosure video = enclosureDao.selectByPrimaryKey(baseInfo.getVideoId());
			TechEnclosure text = enclosureDao.selectByPrimaryKey(baseInfo.getTextId());

			techInfoVo = bindAuditInfo(techInfoVo, auditInfo);
			techInfoVo = bindContactInfo(techInfoVo, contact);
			techInfoVo = bindTechBaseInfo(techInfoVo, baseInfo);
			techInfoVo = bindTechSupplyInfo(techInfoVo, techSup);
			techInfoVo = bindTextEclosure(techInfoVo, text);
			techInfoVo = bindVideoEclosure(techInfoVo, video);
			techInfoVo = bindImageEclosure(techInfoVo, image);

		} catch (Exception e) {
			errorLog("获取技术供给信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return techInfoVo;
	}

	@Transactional
	@Override
	public TechSupplyInfoVo getAuditTechSupById(Integer techSupId) throws BusinessException {
		if (techSupId == null) {
			throw new BusinessException("技术供给id为空！");
		}
		TechSupplyInfoVo techSupVo = new TechSupplyInfoVo();
		try {
			try {
				// 更新审核信息状态和加入审核人
				if (auditDao.updateTechSupAuditStatus(techSupId, AuditInfo.AUDIT_STATUS_AUDITING,
						getCurrentUserId()) < 1) {
					throw new BusinessException("更新审核信息失败！");
				}
			} catch (Exception e) {
				errorLog("更新审核状态失败！", e);
				throw new BusinessException(getMsg());
			}
			techSupVo = getTechSupById(techSupId);
		} catch (Exception e) {
			errorLog("获取技术供给信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return techSupVo;
	}

	@Transactional
	@Override
	public void removeTechSupInfo(Integer techSupId) throws BusinessException {
		if (techSupId == null) {
			throw new BusinessException("传入参数为空！");
		}
		String publisher = tsDao.getPublisherIdByTechSupInfoId(techSupId);
		if (StringUtil.isEmpty(publisher)) {
			throw new BusinessException("技术供给信息异常！");
		}
		if (!publisher.equals(getCurrentUserId())) {
			throw new BusinessException("不是该技术供给信息的发布人！");
		}
		try {
			TechSupplyInfo supply = tsDao.selectByPrimaryKey(techSupId);
			TechnologyBaseInfo baseInfo = tbiDao.selectByPrimaryKey(supply.getTbiId());
			try {
				tsDao.deleteByPrimaryKey(techSupId);
			} catch (Exception e) {
				errorLog("删除技术供给信息失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				tbiDao.deleteByPrimaryKey(baseInfo.getTbiId());
			} catch (Exception e) {
				errorLog("删除技术基本信息失败！", e);
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
			try {
				try {
					Integer imageId = baseInfo.getImageId();
					if (imageId != null) {
						TechEnclosure image = enclosureDao.selectByPrimaryKey(imageId);
						enclosureDao.deleteByPrimaryKey(imageId);
						// 删除文件
						UploadUtil.deleteFile(image.getEnclosureUrl());
					}
				} catch (Exception e) {
					errorLog("删除图片附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
				try {
					Integer textId = baseInfo.getTextId();
					if (textId != null) {
						TechEnclosure text = enclosureDao.selectByPrimaryKey(textId);
						enclosureDao.deleteByPrimaryKey(textId);
						// 删除文件
						UploadUtil.deleteFile(text.getEnclosureUrl());
					}
				} catch (Exception e) {
					errorLog("删除文本附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
				try {
					Integer videoId = baseInfo.getTextId();
					if (videoId != null) {
						TechEnclosure video = enclosureDao.selectByPrimaryKey(videoId);
						enclosureDao.deleteByPrimaryKey(videoId);
						// 删除文件
						UploadUtil.deleteFile(video.getEnclosureUrl());
					}
				} catch (Exception e) {
					errorLog("删除视频附件信息失败！", e);
					throw new BusinessException(getMsg());
				}
			} catch (Exception e) {
				errorLog("删除附件信息失败！", e);
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("删除技术供给信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void updateTechSupStatus(Integer techSupId) throws BusinessException {
		try {
			tsDao.updateTechSupStatus(techSupId);
		} catch (Exception e) {
			errorLog("更新技术供给信息状态失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public Integer getTechSupCountByCondition(TechnologyPageVo condition) throws BusinessException {
		if (condition == null) {
			throw new BusinessException("查询条件为空！");
		}
		Integer result = null;
		try {
			result = tsDao.getTotalNoByCondition(condition);
		} catch (Exception e) {
			errorLog("获取技术供给信息数目失败！", e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Transactional
	@Override
	public List<TechOutlineVo> getTechSupPageByCondition(TechnologyPageVo condition) throws BusinessException {
		if (condition == null) {
			throw new BusinessException("查询条件为空！");
		}
		List<TechOutlineVo> result = new ArrayList<>();
		try {
			condition.setTotalNo(tsDao.getTotalNoByCondition(condition));
			result = tsDao.getTechSuppplyPageByCondition(condition);
		} catch (Exception e) {
			errorLog("获取技术供给数据失败！");
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Override
	public void updateTechSupLimitStatus(Integer tsiId) throws BusinessException {
		try {
			tsDao.updateTechSupLimitStatus(tsiId);
		} catch (Exception e) {
			errorLog("更新技术供给信息可见状态失败！", e);
			throw new BusinessException(getMsg());
		}
	}
}
