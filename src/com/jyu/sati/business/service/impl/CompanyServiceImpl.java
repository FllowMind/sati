package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.CompanyDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.EmployeeSituationDao;
import com.jyu.sati.business.dao.UnitBaseInfoDao;
import com.jyu.sati.business.service.CompanyService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.Company;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.EmployeeSituation;
import com.jyu.sati.entity.UnitBaseInfo;
import com.jyu.sati.vo.CompanyInfoVo;

/**
 * 企业单位服务
 * 
 * @author 淋雨又调皮
 *
 */
/**
 * @author 淋雨又调皮
 *
 */
@Service
public class CompanyServiceImpl extends BaseServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private EmployeeSituationDao esDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private UnitBaseInfoDao ubiDao;

	@Transactional
	@Override
	public int delete(Integer companyId) throws BusinessException {
		// TODO Auto-generated method stub
		return companyDao.deleteByPrimaryKey(companyId);
	}

	@Transactional
	@Override
	public int insert(Company record) throws BusinessException {
		// TODO Auto-generated method stub
		return companyDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(Company record) throws BusinessException {
		// TODO Auto-generated method stub
		return companyDao.insertSelective(record);
	}

	@Transactional(readOnly = true)
	@Override
	public Company getCompanyById(Integer companyId) throws BusinessException {
		// TODO Auto-generated method stub
		return companyDao.selectByPrimaryKey(companyId);
	}

	@Transactional
	@Override
	public int updateSelective(Company record) throws BusinessException {
		// TODO Auto-generated method stub
		return companyDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int update(Company record) throws BusinessException {
		// TODO Auto-generated method stub
		return companyDao.updateByPrimaryKey(record);
	}

	@Transactional(readOnly = true)
	@Override
	public CompanyInfoVo getCompanyInfoByUserId(String userId) throws BusinessException {
		CompanyInfoVo companyInfo = new CompanyInfoVo();
		try {
			Company company = companyDao.getCompanyByUserId(userId);
			UnitBaseInfo baseInfo = ubiDao.selectByPrimaryKey(company.getBaseInfoId());
			ContactInfo contact = contactDao.getContactInfoByUserId(userId);
			AuditInfo auditInfo = auditDao.getAuditInfoByUserId(userId);
			EmployeeSituation emp = esDao.selectByPrimaryKey(company.getSoeId());

			companyInfo = bindCompany(companyInfo, company);
			companyInfo = bindBaseInfo(companyInfo, baseInfo);
			companyInfo = bindEmployeeSuituation(companyInfo, emp);
			companyInfo = bindAuditInfo(companyInfo, auditInfo);
			companyInfo = bindContactInfo(companyInfo, contact);

		} catch (Exception e) {
			errorLog("获取企业信息失败！", e);
			throw new BusinessException(getMsg());
		}
		return companyInfo;
	}

	/**
	 * 绑定企业信息
	 * 
	 * @param companyInfo
	 * @param company
	 * @return
	 * @throws BusinessException
	 */
	private CompanyInfoVo bindCompany(CompanyInfoVo companyInfo, Company company) throws BusinessException {
		try {
			companyInfo.setCompanyId(company.getCompanyId());
			companyInfo.setCompanyNatureId(company.getCompanyNatureId());
			companyInfo.setRegisteredCapital(company.getRegisteredCapital());
			companyInfo.setRegisteredAddress(company.getRegisteredAddress());
			companyInfo.setIndustryId(company.getIndustryId());
			companyInfo.setCircOrNot(company.getCircOrNot());
			companyInfo.setCttOrNot(company.getCttOrNot());
		} catch (Exception e) {
			throw e;
		}
		return companyInfo;
	}

	/**
	 * 绑定审核情况
	 * 
	 * @param companyInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private CompanyInfoVo bindAuditInfo(CompanyInfoVo companyInfo, AuditInfo auditInfo) throws BusinessException {
		try {
			companyInfo.setAuditInfoId(auditInfo.getAuditInfoId());
			companyInfo.setSubmitTime(auditInfo.getSubmitTime());
			companyInfo.setAuditTime(auditInfo.getAuditTime());
			companyInfo.setAuditStatus(auditInfo.getAuditStatus());
			companyInfo.setAuditResult(auditInfo.getAuditResult());
			companyInfo.setUserId(auditInfo.getUserId());
			companyInfo.setUserName(auditInfo.getUserName());
		} catch (Exception e) {
			throw e;
		}
		return companyInfo;
	}

	/**
	 * 绑定单位从业人员情况
	 * 
	 * @param companyInfo
	 * @param emp
	 * @return
	 * @throws BusinessException
	 */
	private CompanyInfoVo bindEmployeeSuituation(CompanyInfoVo companyInfo, EmployeeSituation emp)
			throws BusinessException {
		try {
			companyInfo.setSoeId(emp.getSoeId());
			companyInfo.setTotalNumber(emp.getTotalNumber());
			companyInfo.setDeveloperNumber(emp.getDeveloperNumber());
			companyInfo.setJuniorNumber(emp.getJuniorNumber());
			companyInfo.setIntermediateNumber(emp.getIntermediateNunber());
			companyInfo.setSeniorNumber(emp.getSeniorNumber());
			companyInfo.setJuniorCollegeNumber(emp.getJuniorCollegeNumber());
			companyInfo.setUndergraduateNumber(emp.getUndergraduateNumber());
			companyInfo.setMasterNumber(emp.getMasterNumber());
			companyInfo.setDoctorNumber(emp.getDoctorNumber());
			companyInfo.setOverseasNumber(emp.getOverseasNumber());
			companyInfo.setAcademicianNunber(emp.getAcademicianNunber());
		} catch (Exception e) {
			throw e;
		}
		return companyInfo;
	}

	/**
	 * 绑定单位基本信息
	 * 
	 * @param companyInfo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private CompanyInfoVo bindBaseInfo(CompanyInfoVo companyInfo, UnitBaseInfo baseInfo) throws BusinessException {
		try {
			companyInfo.setBaseInfoId(baseInfo.getUnitBaseinfoId());
			companyInfo.setUnitName(baseInfo.getUnitName());
			companyInfo.setUnitAbbreviation(baseInfo.getUnitAbbreviation());
			companyInfo.setEstablishmentDate(baseInfo.getEstablishmentDate());
			companyInfo.setLegalRepresentative(baseInfo.getLegalRepresentative());
			companyInfo.setUnitAddress(baseInfo.getUnitAddress());
			companyInfo.setLocationId(baseInfo.getLocationId());
			companyInfo.setKey(baseInfo.getKey());
			companyInfo.setUnitCode(baseInfo.getUnitCode());
			companyInfo.setUnitCodeType(baseInfo.getUnitCodeType());
			companyInfo.setUnitCodeImageUrl(baseInfo.getUnitCodeImageUrl());
			companyInfo.setUnitProfile(baseInfo.getUnitProfile());
			companyInfo.setBusinessField(baseInfo.getBusinessField());
			companyInfo.setRemark(baseInfo.getRemark());
		} catch (Exception e) {
			throw e;
		}
		return companyInfo;
	}

	/**
	 * 绑定单位基本信息
	 * 
	 * @param companyInfo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private CompanyInfoVo bindContactInfo(CompanyInfoVo companyInfo, ContactInfo contact) throws BusinessException {
		try {
			companyInfo.setContactId(contact.getContactId());
			companyInfo.setContactName(contact.getContactName());
			companyInfo.setContactBusiness(contact.getContactBusiness());
			companyInfo.setContactAddress(contact.getContactAddress());
			companyInfo.setPostcode(contact.getPostcode());
			companyInfo.setEmail(contact.getEmail());
			companyInfo.setContactUrl(contact.getContactUrl());
			companyInfo.setFaxNumber(contact.getFaxNumber());
			companyInfo.setQqormsnNumer(contact.getQqormsnNumer());
			companyInfo.setPhoneNumber(contact.getPhoneNumber());
			companyInfo.setContactNumber(contact.getContactNumber());
		} catch (Exception e) {
			throw e;
		}
		return companyInfo;
	}

	// *********************提交***********************
	@Transactional
	@Override
	public void updateCompanyInfo(CompanyInfoVo companyInfo) throws BusinessException {
		try {
			updateAuditInfo(companyInfo);
			updateBaseInfo(companyInfo);
			updateCompany(companyInfo);
			updateContactInfo(companyInfo);
			undateEmployeeSuituation(companyInfo);
			// 更新用户信息审核状态
			auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_WAITING, null);
		} catch (Exception e) {
			errorLog("提交企业信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新企业信息
	 * 
	 * @param companyInfo
	 * @param company
	 * @return
	 * @throws BusinessException
	 */
	private void updateCompany(CompanyInfoVo companyInfo) throws BusinessException {
		try {
			Company company = new Company();
			company.setCompanyId(companyInfo.getCompanyId());
			company.setCompanyNatureId(companyInfo.getCompanyNatureId());
			company.setRegisteredCapital(companyInfo.getRegisteredCapital());
			company.setRegisteredAddress(companyInfo.getRegisteredAddress());
			company.setIndustryId(companyInfo.getIndustryId());
			company.setCircOrNot(companyInfo.getCircOrNot());
			company.setCttOrNot(companyInfo.getCttOrNot());

			companyDao.updateByPrimaryKeySelective(company);

		} catch (Exception e) {
			errorLog("更新Company表失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新审核情况
	 * 
	 * @param companyInfo
	 * @param auditInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateAuditInfo(CompanyInfoVo companyInfo) throws BusinessException {
		try {
			AuditInfo auditInfo = new AuditInfo();
			auditInfo.setAuditInfoId(companyInfo.getAuditInfoId());
			auditInfo.setSubmitTime(companyInfo.getSubmitTime());
			auditInfo.setAuditTime(companyInfo.getAuditTime());
			auditInfo.setAuditStatus(companyInfo.getAuditStatus());
			auditInfo.setAuditResult(companyInfo.getAuditResult());
			auditInfo.setUserId(companyInfo.getUserId());
			auditInfo.setUserName(companyInfo.getUserName());

			auditDao.updateByPrimaryKeySelective(auditInfo);

		} catch (Exception e) {
			errorLog("更新AuditInfo表失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新单位从业人员情况
	 * 
	 * @param companyInfo
	 * @param emp
	 * @return
	 * @throws BusinessException
	 */
	private void undateEmployeeSuituation(CompanyInfoVo companyInfo) throws BusinessException {
		try {
			EmployeeSituation emp = new EmployeeSituation();
			emp.setSoeId(companyInfo.getSoeId());
			emp.setTotalNumber(companyInfo.getTotalNumber());
			emp.setDeveloperNumber(companyInfo.getDeveloperNumber());
			emp.setJuniorNumber(companyInfo.getJuniorNumber());
			emp.setIntermediateNunber(companyInfo.getIntermediateNumber());
			emp.setSeniorNumber(companyInfo.getSeniorNumber());
			emp.setJuniorCollegeNumber(companyInfo.getJuniorCollegeNumber());
			emp.setUndergraduateNumber(companyInfo.getUndergraduateNumber());
			emp.setMasterNumber(companyInfo.getMasterNumber());
			emp.setDoctorNumber(companyInfo.getDoctorNumber());
			emp.setOverseasNumber(companyInfo.getOverseasNumber());
			emp.setAcademicianNunber(companyInfo.getAcademicianNunber());

			esDao.updateByPrimaryKeySelective(emp);

		} catch (Exception e) {
			errorLog("更新EmployeeSituation表失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新单位基本信息
	 * 
	 * @param companyInfo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateBaseInfo(CompanyInfoVo companyInfo) throws BusinessException {
		try {
			UnitBaseInfo baseInfo = new UnitBaseInfo();
			baseInfo.setUnitBaseinfoId(companyInfo.getBaseInfoId());
			baseInfo.setUnitName(companyInfo.getUnitName());
			baseInfo.setUnitAbbreviation(companyInfo.getUnitAbbreviation());
			baseInfo.setEstablishmentDate(companyInfo.getEstablishmentDate());
			baseInfo.setLegalRepresentative(companyInfo.getLegalRepresentative());
			baseInfo.setUnitAddress(companyInfo.getUnitAddress());
			baseInfo.setLocationId(companyInfo.getLocationId());
			baseInfo.setKey(companyInfo.getKey());
			baseInfo.setUnitCode(companyInfo.getUnitCode());
			baseInfo.setUnitCodeType(companyInfo.getUnitCodeType());
			baseInfo.setUnitCodeImageUrl(companyInfo.getUnitCodeImageUrl());
			baseInfo.setUnitProfile(companyInfo.getUnitProfile());
			baseInfo.setBusinessField(companyInfo.getBusinessField());
			baseInfo.setRemark(companyInfo.getRemark());

			ubiDao.updateByPrimaryKeySelective(baseInfo);

		} catch (Exception e) {
			errorLog("更新UnitBaseInfo表失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新单位基本信息
	 * 
	 * @param companyInfo
	 * @param baseInfo
	 * @return
	 * @throws BusinessException
	 */
	private void updateContactInfo(CompanyInfoVo companyInfo) throws BusinessException {
		try {
			ContactInfo contact = new ContactInfo();
			contact.setContactId(companyInfo.getContactId());
			contact.setContactName(companyInfo.getContactName());
			contact.setContactBusiness(companyInfo.getContactBusiness());
			contact.setContactAddress(companyInfo.getContactAddress());
			contact.setPostcode(companyInfo.getPostcode());
			contact.setEmail(companyInfo.getEmail());
			contact.setContactUrl(companyInfo.getContactUrl());
			contact.setFaxNumber(companyInfo.getFaxNumber());
			contact.setQqormsnNumer(companyInfo.getQqormsnNumer());
			contact.setPhoneNumber(companyInfo.getPhoneNumber());
			contact.setContactNumber(companyInfo.getContactNumber());
			contactDao.updateByPrimaryKeySelective(contact);

		} catch (Exception e) {
			errorLog("更新ContactInfo表失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Override
	public CompanyInfoVo getAuditCompanyInfoByUserId(String userId) throws BusinessException {
		CompanyInfoVo companyInfo = null;
		try {
			companyInfo = getCompanyInfoByUserId(userId);
			try {
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
		return companyInfo;
	}

}
