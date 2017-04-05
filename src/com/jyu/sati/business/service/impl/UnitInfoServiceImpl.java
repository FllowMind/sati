package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.EmployeeSituationDao;
import com.jyu.sati.business.dao.UnitBaseInfoDao;
import com.jyu.sati.business.dao.UnitInfoDao;
import com.jyu.sati.business.service.UnitInfoService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.EmployeeSituation;
import com.jyu.sati.entity.UnitBaseInfo;
import com.jyu.sati.entity.UnitInfo;
import com.jyu.sati.vo.UnitInfoVo;

/**
 * 科研单位或高校服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class UnitInfoServiceImpl extends BaseServiceImpl implements UnitInfoService {

	@Autowired
	private UnitInfoDao uiDao;
	@Autowired
	private UnitBaseInfoDao ubiDao;
	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	EmployeeSituationDao empDao;

	@Transactional
	@Override
	public int delete(Integer unitInfoId) throws BusinessException {
		// TODO Auto-generated method stub
		return uiDao.deleteByPrimaryKey(unitInfoId);
	}

	@Transactional
	@Override
	public int insert(UnitInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return uiDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(UnitInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return uiDao.insertSelective(record);
	}

	@Transactional(readOnly = true)
	@Override
	public UnitInfo getUnitInfoById(Integer unitInfoId) throws BusinessException {
		// TODO Auto-generated method stub
		return uiDao.selectByPrimaryKey(unitInfoId);
	}

	@Transactional
	@Override
	public int updateSelective(UnitInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return uiDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int update(UnitInfo record) throws BusinessException {
		// TODO Auto-generated method stub
		return uiDao.updateByPrimaryKey(record);
	}

	// ********************* 高校和科研单位信息获取*******************

	@Transactional
	@Override
	public UnitInfoVo getCollegeInfo(String userId) throws BusinessException {
		UnitInfoVo unitInfo = new UnitInfoVo();
		try {
			// 获取高校单位信息
			UnitInfo unit = uiDao.getCollegeByUserId(userId);
			unitInfo = getBind(unitInfo, unit, userId);
		} catch (Exception e) {
			errorLog("获取高校信息失败！", e);
			throw new BusinessException(getMsg());
		}

		return unitInfo;
	}

	@Transactional
	@Override
	public UnitInfoVo getScientifyInfo(String userId) throws BusinessException {
		UnitInfoVo unitInfo = new UnitInfoVo();
		try {
			// 获取科研单位信息
			UnitInfo unit = uiDao.getScientificByUserId(userId);
			unitInfo = getBind(unitInfo, unit, userId);
		} catch (Exception e) {
			errorLog("获取科研单位信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return unitInfo;
	}

	private UnitInfoVo getBind(UnitInfoVo unitInfo, UnitInfo unit, String userId) {
		UnitBaseInfo baseInfo = ubiDao.selectByPrimaryKey(unit.getBaseInfoId());
		EmployeeSituation emp = empDao.selectByPrimaryKey(unit.getSoeId());
		AuditInfo auditInfo = auditDao.getAuditInfoByUserId(userId);
		ContactInfo contact = contactDao.getContactInfoByUserId(userId);

		unitInfo = bindAuditInfo(unitInfo, auditInfo);
		unitInfo = bindBaseInfo(unitInfo, baseInfo);
		unitInfo = bindContactInfo(unitInfo, contact);
		unitInfo = bindEmployeeSituation(unitInfo, emp);

		return unitInfo;
	}

	/**
	 * 绑定基本信息
	 * 
	 * @param unitInfo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private UnitInfoVo bindBaseInfo(UnitInfoVo unitInfo, UnitBaseInfo baseInfo) throws BusinessException {
		try {

			unitInfo.setBaseInfoId(baseInfo.getUnitBaseinfoId());
			unitInfo.setUnitName(baseInfo.getUnitName());
			unitInfo.setUnitAbbreviation(baseInfo.getUnitAbbreviation());
			unitInfo.setEstablishmentDate(baseInfo.getEstablishmentDate());
			unitInfo.setLegalRepresentative(baseInfo.getLegalRepresentative());
			unitInfo.setUnitAddress(baseInfo.getUnitAddress());
			unitInfo.setLocationId(baseInfo.getLocationId());
			unitInfo.setKey(baseInfo.getKey());
			unitInfo.setUnitCodeType(baseInfo.getUnitCodeType());
			unitInfo.setUnitCode(baseInfo.getUnitCode());
			unitInfo.setUnitCodeImageUrl(baseInfo.getUnitCodeImageUrl());
			unitInfo.setUnitProfile(baseInfo.getUnitProfile());
			unitInfo.setBusinessField(baseInfo.getBusinessField());
			unitInfo.setRemark(baseInfo.getRemark());

		} catch (Exception e) {
			throw e;
		}
		return unitInfo;
	}

	/**
	 * 绑定联系人信息
	 * 
	 * @param unitInfo
	 * @param contact
	 * @return
	 * @throws BusinessException
	 */
	private UnitInfoVo bindContactInfo(UnitInfoVo unitInfo, ContactInfo contact) throws BusinessException {
		try {
			unitInfo.setContactId(contact.getContactId());
			unitInfo.setContactName(contact.getContactName());
			unitInfo.setContactBusiness(contact.getContactBusiness());
			unitInfo.setContactNumber(contact.getContactNumber());
			unitInfo.setContactAddress(contact.getContactAddress());
			unitInfo.setPostcode(contact.getPostcode());
			unitInfo.setEmail(contact.getEmail());
			unitInfo.setContactUrl(contact.getContactUrl());
			unitInfo.setFaxNumber(contact.getFaxNumber());
			unitInfo.setQqormsnNumer(contact.getQqormsnNumer());
			unitInfo.setPhoneNumber(contact.getPhoneNumber());
		} catch (Exception e) {
			throw e;
		}
		return unitInfo;
	}

	/**
	 * 绑定审核信息
	 * 
	 * @param unitInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private UnitInfoVo bindAuditInfo(UnitInfoVo unitInfo, AuditInfo auditInfo) throws BusinessException {
		try {
			unitInfo.setAuditInfoId(auditInfo.getAuditInfoId());
			unitInfo.setSubmitTime(auditInfo.getSubmitTime());
			unitInfo.setAuditTime(auditInfo.getAuditTime());
			unitInfo.setAuditStatus(auditInfo.getAuditStatus());
			unitInfo.setAuditResult(auditInfo.getAuditResult());
		} catch (Exception e) {
			throw e;
		}
		return unitInfo;
	}

	/**
	 * 绑定单位从业人员情况
	 * 
	 * @param unitInfo
	 * @param emp
	 * @return
	 * @throws BusinessException
	 */
	private UnitInfoVo bindEmployeeSituation(UnitInfoVo unitInfo, EmployeeSituation emp) throws BusinessException {
		try {
			unitInfo.setSoeId(emp.getSoeId());
			unitInfo.setTotalNumber(emp.getTotalNumber());
			unitInfo.setDeveloperNumber(emp.getDeveloperNumber());
			unitInfo.setJuniorNumber(emp.getJuniorNumber());
			unitInfo.setIntermediateNumber(emp.getIntermediateNunber());
			unitInfo.setSeniorNumber(emp.getSeniorNumber());
			unitInfo.setJuniorCollegeNumber(emp.getJuniorCollegeNumber());
			unitInfo.setUndergraduateNumber(emp.getUndergraduateNumber());
			unitInfo.setMasterNumber(emp.getMasterNumber());
			unitInfo.setDoctorNumber(emp.getDoctorNumber());
			unitInfo.setOverseasNumber(emp.getOverseasNumber());
			unitInfo.setAcademicianNunber(emp.getAcademicianNunber());
		} catch (Exception e) {
			throw e;
		}
		return unitInfo;
	}

	// ****************** 高校和科研单位信息更改**********************
	@Transactional
	@Override
	public void updateUnitInfo(UnitInfoVo unitInfo) throws BusinessException {
		try {
			updateBaseInfo(unitInfo);
			updateContactInfo(unitInfo);
			updateEmployeeSituation(unitInfo);
			// 更新用户审信息为等待审核状态
			auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_WAITING, "");

		} catch (Exception e) {
			errorLog("更新科研单位信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新基本信息
	 * 
	 * @param unitInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateBaseInfo(UnitInfoVo unitInfo) throws BusinessException {
		try {
			UnitBaseInfo baseInfo = new UnitBaseInfo();
			baseInfo.setUnitBaseinfoId(unitInfo.getBaseInfoId());
			baseInfo.setUnitName(unitInfo.getUnitName());
			baseInfo.setUnitAbbreviation(unitInfo.getUnitAbbreviation());
			baseInfo.setEstablishmentDate(unitInfo.getEstablishmentDate());
			baseInfo.setLegalRepresentative(unitInfo.getLegalRepresentative());
			baseInfo.setUnitAddress(unitInfo.getUnitAddress());
			baseInfo.setLocationId(unitInfo.getLocationId());
			baseInfo.setKey(unitInfo.getKey());
			baseInfo.setUnitCodeType(unitInfo.getUnitCodeType());
			baseInfo.setUnitCode(unitInfo.getUnitCode());
			baseInfo.setUnitCodeImageUrl(unitInfo.getUnitCodeImageUrl());
			baseInfo.setUnitProfile(unitInfo.getUnitProfile());
			baseInfo.setBusinessField(unitInfo.getBusinessField());
			baseInfo.setRemark(unitInfo.getRemark());

			ubiDao.updateByPrimaryKeySelective(baseInfo);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新联系人信息
	 * 
	 * @param unitInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateContactInfo(UnitInfoVo unitInfo) throws BusinessException {
		try {
			ContactInfo contact = new ContactInfo();
			contact.setContactId(unitInfo.getContactId());
			contact.setContactName(unitInfo.getContactName());
			contact.setContactBusiness(unitInfo.getContactBusiness());
			contact.setContactNumber(unitInfo.getContactNumber());
			contact.setContactAddress(unitInfo.getContactAddress());
			contact.setPostcode(unitInfo.getPostcode());
			contact.setEmail(unitInfo.getEmail());
			contact.setContactUrl(unitInfo.getContactUrl());
			contact.setFaxNumber(unitInfo.getFaxNumber());
			contact.setQqormsnNumer(unitInfo.getQqormsnNumer());
			contact.setPhoneNumber(unitInfo.getPhoneNumber());

			contactDao.updateByPrimaryKeySelective(contact);

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 更新单位从业人员情况
	 * 
	 * @param unitInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateEmployeeSituation(UnitInfoVo unitInfo) throws BusinessException {
		try {
			EmployeeSituation emp = new EmployeeSituation();
			emp.setSoeId(unitInfo.getSoeId());
			emp.setTotalNumber(unitInfo.getTotalNumber());
			emp.setDeveloperNumber(unitInfo.getDeveloperNumber());
			emp.setJuniorNumber(unitInfo.getJuniorNumber());
			emp.setIntermediateNunber(unitInfo.getIntermediateNumber());
			emp.setSeniorNumber(unitInfo.getSeniorNumber());
			emp.setJuniorCollegeNumber(unitInfo.getJuniorCollegeNumber());
			emp.setUndergraduateNumber(unitInfo.getUndergraduateNumber());
			emp.setMasterNumber(unitInfo.getMasterNumber());
			emp.setDoctorNumber(unitInfo.getDoctorNumber());
			emp.setOverseasNumber(unitInfo.getOverseasNumber());
			emp.setAcademicianNunber(unitInfo.getAcademicianNunber());

			empDao.updateByPrimaryKeySelective(emp);

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public UnitInfoVo getAuditScientifyInfo(String userId) throws BusinessException {
		UnitInfoVo unitInfo = null;
		try {
			try {
				unitInfo = getScientifyInfo(userId);
				AuditInfo auditInfo = auditDao.getAuditInfoByUserId(userId);
				auditInfo.setAuditorId(getCurrentUserId());
				auditInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_AUDITING);
				auditDao.updateByPrimaryKeySelective(auditInfo);
			} catch (Exception e) {
				errorLog("更新审核状态失败！");
				throw new BusinessException(getMsg());
			}

		} catch (Exception e) {
			throw e;
		}
		return unitInfo;
	}

	@Override
	public UnitInfoVo getAuditCollegeInfo(String userId) throws BusinessException {
		UnitInfoVo unitInfo = null;
		try {
			try {
				unitInfo = getCollegeInfo(userId);
				AuditInfo auditInfo = auditDao.getAuditInfoByUserId(userId);
				auditInfo.setAuditorId(getCurrentUserId());
				auditInfo.setAuditStatus(AuditInfo.AUDIT_STATUS_AUDITING);
				auditDao.updateByPrimaryKeySelective(auditInfo);
			} catch (Exception e) {
				errorLog("更新审核状态失败！");
				throw new BusinessException(getMsg());
			}

		} catch (Exception e) {
			throw e;
		}
		return unitInfo;
	}
}
