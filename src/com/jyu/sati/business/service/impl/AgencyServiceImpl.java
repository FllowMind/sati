package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AgencyDao;
import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.EmployeeSituationDao;
import com.jyu.sati.business.dao.UnitBaseInfoDao;
import com.jyu.sati.business.service.AgencyService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Agency;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.EmployeeSituation;
import com.jyu.sati.entity.UnitBaseInfo;
import com.jyu.sati.vo.AgencyInfoVo;

@Service
public class AgencyServiceImpl extends BaseServiceImpl implements AgencyService {

	@Autowired
	private AgencyDao agencyDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private EmployeeSituationDao empDao;
	@Autowired
	private UnitBaseInfoDao ubiDao;
	@Autowired
	private AuditInfoDao auditDao;

	@Transactional
	@Override
	public int deleteByAgencyId(Integer agencyId) {
		// TODO Auto-generated method stub
		return agencyDao.deleteByPrimaryKey(agencyId);
	}

	@Transactional
	@Override
	public int insert(Agency agency) {
		// TODO Auto-generated method stub
		return agencyDao.insert(agency);
	}

	@Transactional
	@Override
	public int insertSelective(Agency agency) {
		// TODO Auto-generated method stub
		return agencyDao.insertSelective(agency);
	}

	@Transactional(readOnly = true)
	@Override
	public Agency selectByAgencyId(Integer agencyId) {
		// TODO Auto-generated method stub
		return agencyDao.selectByPrimaryKey(agencyId);
	}

	@Transactional
	@Override
	public int updateSelective(Agency agency) {
		// TODO Auto-generated method stub
		return agencyDao.updateByPrimaryKeySelective(agency);
	}

	@Transactional
	@Override
	public int update(Agency agency) {
		// TODO Auto-generated method stub
		return agencyDao.updateByPrimaryKey(agency);
	}

	// ********************获取中介机构信息*************************

	@Override
	public AgencyInfoVo getAgencyInfoByUserId(String userId) throws BusinessException {
		AgencyInfoVo agencyInfo = new AgencyInfoVo();
		try {
			Agency agency = agencyDao.getAgencyByUserId(userId);
			UnitBaseInfo baseInfo = ubiDao.selectByPrimaryKey(agency.getBaseInfoId());
			ContactInfo contact = contactDao.getContactInfoByUserId(userId);
			EmployeeSituation emp = empDao.selectByPrimaryKey(agency.getSoeId());
			AuditInfo auditInfo = auditDao.getAuditInfoByUserId(userId);

			agencyInfo = bindAgency(agencyInfo, agency);
			agencyInfo = bindBaseInfo(agencyInfo, baseInfo);
			agencyInfo = bindContactInfo(agencyInfo, contact);
			agencyInfo = bindEmployeeSituation(agencyInfo, emp);
			agencyInfo = bindAuditInfo(agencyInfo, auditInfo);

		} catch (Exception e) {
			errorLog("获取中介机构信息失败！");
			throw new BusinessException(getMsg());
		}
		return agencyInfo;
	}

	/**
	 * 绑定中介机构信息
	 * 
	 * @param agencyInfo
	 * @param agency
	 * @return
	 * @throws BusinessException
	 */
	private AgencyInfoVo bindAgency(AgencyInfoVo agencyInfo, Agency agency) throws BusinessException {
		try {
			agencyInfo.setAgencyId(agency.getAgencyId());
			agencyInfo.setUnitNature(agency.getUnitNature());
			agencyInfo.setClassicCase(agency.getClassicCase());

		} catch (Exception e) {
			throw e;
		}
		return agencyInfo;
	}

	/**
	 * 绑定基本信息
	 * 
	 * @param agencyInfo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private AgencyInfoVo bindBaseInfo(AgencyInfoVo agencyInfo, UnitBaseInfo baseInfo) throws BusinessException {
		try {

			agencyInfo.setUnitBaseinfoId(baseInfo.getUnitBaseinfoId());
			agencyInfo.setUnitName(baseInfo.getUnitName());
			agencyInfo.setUnitAbbreviation(baseInfo.getUnitAbbreviation());
			agencyInfo.setEstablishmentDate(baseInfo.getEstablishmentDate());
			agencyInfo.setLegalRepresentative(baseInfo.getLegalRepresentative());
			agencyInfo.setUnitAddress(baseInfo.getUnitAddress());
			agencyInfo.setLocationId(baseInfo.getLocationId());
			agencyInfo.setKey(baseInfo.getKey());
			agencyInfo.setUnitCodeType(baseInfo.getUnitCodeType());
			agencyInfo.setUnitCode(baseInfo.getUnitCode());
			agencyInfo.setUnitCodeImageUrl(baseInfo.getUnitCodeImageUrl());
			agencyInfo.setUnitProfile(baseInfo.getUnitProfile());
			agencyInfo.setBusinessField(baseInfo.getBusinessField());
			agencyInfo.setRemark(baseInfo.getRemark());

		} catch (Exception e) {
			throw e;
		}
		return agencyInfo;
	}

	/**
	 * 绑定联系人信息
	 * 
	 * @param agencyInfo
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	private AgencyInfoVo bindContactInfo(AgencyInfoVo agencyInfo, ContactInfo contact) throws BusinessException {
		try {
			agencyInfo.setContactId(contact.getContactId());
			agencyInfo.setContactName(contact.getContactName());
			agencyInfo.setContactBusiness(contact.getContactBusiness());
			agencyInfo.setContactNumber(contact.getContactNumber());
			agencyInfo.setContactAddress(contact.getContactAddress());
			agencyInfo.setPostcode(contact.getPostcode());
			agencyInfo.setEmail(contact.getEmail());
			agencyInfo.setContactUrl(contact.getContactUrl());
			agencyInfo.setFaxNumber(contact.getFaxNumber());
			agencyInfo.setQqormsnNumer(contact.getQqormsnNumer());
			agencyInfo.setPhoneNumber(contact.getPhoneNumber());
		} catch (Exception e) {
			throw e;
		}
		return agencyInfo;
	}

	/**
	 * 绑定审核信息
	 * 
	 * @param agencyInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private AgencyInfoVo bindAuditInfo(AgencyInfoVo agencyInfo, AuditInfo auditInfo) throws BusinessException {
		try {
			agencyInfo.setAuditInfoId(auditInfo.getAuditInfoId());
			agencyInfo.setSubmitTime(auditInfo.getSubmitTime());
			agencyInfo.setAuditTime(auditInfo.getAuditTime());
			agencyInfo.setAuditStatus(auditInfo.getAuditStatus());
			agencyInfo.setAuditResult(auditInfo.getAuditResult());
			agencyInfo.setAuditorId(auditInfo.getAuditorId());
		} catch (Exception e) {
			throw e;
		}
		return agencyInfo;
	}

	/**
	 * 绑定单位从业人员情况
	 * 
	 * @param agencyInfo
	 * @param emp
	 * @return
	 * @throws BusinessException
	 */
	private AgencyInfoVo bindEmployeeSituation(AgencyInfoVo agencyInfo, EmployeeSituation emp)
			throws BusinessException {
		try {
			agencyInfo.setSoeId(emp.getSoeId());
			agencyInfo.setTotalNumber(emp.getTotalNumber());
			agencyInfo.setDeveloperNumber(emp.getDeveloperNumber());
			agencyInfo.setJuniorNumber(emp.getJuniorNumber());
			agencyInfo.setIntermediateNunber(emp.getIntermediateNunber());
			agencyInfo.setSeniorNumber(emp.getSeniorNumber());
			agencyInfo.setJuniorCollegeNumber(emp.getJuniorCollegeNumber());
			agencyInfo.setUndergraduateNumber(emp.getUndergraduateNumber());
			agencyInfo.setMasterNumber(emp.getMasterNumber());
			agencyInfo.setDoctorNumber(emp.getDoctorNumber());
			agencyInfo.setOverseasNumber(emp.getOverseasNumber());
			agencyInfo.setAcademicianNunber(emp.getAcademicianNunber());
		} catch (Exception e) {
			throw e;
		}
		return agencyInfo;
	}

	// *****************************更新企业信息***************************
	@Transactional
	@Override
	public void updateAgencyInfo(AgencyInfoVo agencyInfo) throws BusinessException {
		try {
			updateAgency(agencyInfo);
			updateAuditInfo(agencyInfo);
			updateBaseInfo(agencyInfo);
			updateContactInfo(agencyInfo);
			updateEmployeeSituation(agencyInfo);
			// 更新用户审信息为等待审核状态
			auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_WAITING, null);
		} catch (Exception e) {
			errorLog("更新中介机构信息失败！");
			throw new BusinessException(getMsg());
		}

	}

	/**
	 * 绑定中介机构信息
	 * 
	 * @param agencyInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateAgency(AgencyInfoVo agencyInfo) throws BusinessException {
		try {
			Agency agency = new Agency();
			agency.setAgencyId(agencyInfo.getAgencyId());
			agency.setUnitNature(agencyInfo.getUnitNature());
			agency.setClassicCase(agencyInfo.getClassicCase());
			agencyDao.updateByPrimaryKeySelective(agency);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新基本信息
	 * 
	 * @param agencyInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateBaseInfo(AgencyInfoVo agencyInfo) throws BusinessException {
		try {
			UnitBaseInfo baseInfo = new UnitBaseInfo();
			baseInfo.setUnitBaseinfoId(agencyInfo.getUnitBaseinfoId());
			baseInfo.setUnitName(agencyInfo.getUnitName());
			baseInfo.setUnitAbbreviation(agencyInfo.getUnitAbbreviation());
			baseInfo.setEstablishmentDate(agencyInfo.getEstablishmentDate());
			baseInfo.setLegalRepresentative(agencyInfo.getLegalRepresentative());
			baseInfo.setUnitAddress(agencyInfo.getUnitAddress());
			baseInfo.setLocationId(agencyInfo.getLocationId());
			baseInfo.setKey(agencyInfo.getKey());
			baseInfo.setUnitCodeType(agencyInfo.getUnitCodeType());
			baseInfo.setUnitCode(agencyInfo.getUnitCode());
			baseInfo.setUnitCodeImageUrl(agencyInfo.getUnitCodeImageUrl());
			baseInfo.setUnitProfile(agencyInfo.getUnitProfile());
			baseInfo.setBusinessField(agencyInfo.getBusinessField());
			baseInfo.setRemark(agencyInfo.getRemark());

			ubiDao.updateByPrimaryKeySelective(baseInfo);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新联系人信息
	 * 
	 * @param agencyInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateContactInfo(AgencyInfoVo agencyInfo) throws BusinessException {
		try {
			ContactInfo contact = new ContactInfo();
			contact.setContactId(agencyInfo.getContactId());
			contact.setContactName(agencyInfo.getContactName());
			contact.setContactBusiness(agencyInfo.getContactBusiness());
			contact.setContactNumber(agencyInfo.getContactNumber());
			contact.setContactAddress(agencyInfo.getContactAddress());
			contact.setPostcode(agencyInfo.getPostcode());
			contact.setEmail(agencyInfo.getEmail());
			contact.setContactUrl(agencyInfo.getContactUrl());
			contact.setFaxNumber(agencyInfo.getFaxNumber());
			contact.setQqormsnNumer(agencyInfo.getQqormsnNumer());
			contact.setPhoneNumber(agencyInfo.getPhoneNumber());

			contactDao.updateByPrimaryKeySelective(contact);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新审核信息
	 * 
	 * @param agencyInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateAuditInfo(AgencyInfoVo agencyInfo) throws BusinessException {
		try {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setAuditInfoId(agencyInfo.getAuditInfoId());
			auditInfo.setSubmitTime(agencyInfo.getSubmitTime());
			auditInfo.setAuditTime(agencyInfo.getAuditTime());
			auditInfo.setAuditStatus(agencyInfo.getAuditStatus());
			auditInfo.setAuditResult(agencyInfo.getAuditResult());
			auditInfo.setAuditorId(agencyInfo.getAuditorId());

			auditDao.updateByPrimaryKeySelective(auditInfo);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新单位从业人员情况
	 * 
	 * @param agencyInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateEmployeeSituation(AgencyInfoVo agencyInfo) throws BusinessException {
		try {
			EmployeeSituation emp = new EmployeeSituation();
			emp.setSoeId(agencyInfo.getSoeId());
			emp.setTotalNumber(agencyInfo.getTotalNumber());
			emp.setDeveloperNumber(agencyInfo.getDeveloperNumber());
			emp.setJuniorNumber(agencyInfo.getJuniorNumber());
			emp.setIntermediateNunber(agencyInfo.getIntermediateNunber());
			emp.setSeniorNumber(agencyInfo.getSeniorNumber());
			emp.setJuniorCollegeNumber(agencyInfo.getJuniorCollegeNumber());
			emp.setUndergraduateNumber(agencyInfo.getUndergraduateNumber());
			emp.setMasterNumber(agencyInfo.getMasterNumber());
			emp.setDoctorNumber(agencyInfo.getDoctorNumber());
			emp.setOverseasNumber(agencyInfo.getOverseasNumber());
			emp.setAcademicianNunber(agencyInfo.getAcademicianNunber());

			empDao.updateByPrimaryKeySelective(emp);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public AgencyInfoVo getAuditAgencyInfoByUserId(String userId) throws BusinessException {
		AgencyInfoVo agency = null;
		try {
			agency = getAgencyInfoByUserId(userId);
			try {
				// 更新审核状态为正在审核状态，并且设置审核人
				AuditInfo audit = auditDao.getAuditInfoByUserId(userId);
				audit.setAuditStatus(AuditInfo.AUDIT_STATUS_AUDITING);
				audit.setAuditorId(getCurrentUserId());
				auditDao.updateByPrimaryKeySelective(audit);
			} catch (Exception e) {
				errorLog("更新审核状态失败！", e);
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			throw e;
		}
		return agency;
	}

}
