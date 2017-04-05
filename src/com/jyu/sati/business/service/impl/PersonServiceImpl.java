package com.jyu.sati.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.DocumentDao;
import com.jyu.sati.business.dao.PersonDao;
import com.jyu.sati.business.dao.PersonInfoDao;
import com.jyu.sati.business.dao.UserDao;
import com.jyu.sati.business.service.PersonService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.Document;
import com.jyu.sati.entity.Person;
import com.jyu.sati.entity.PersonInfo;
import com.jyu.sati.vo.PersonInfoVo;

/**
 * 个人用户服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl implements PersonService {

	@Autowired
	private PersonDao personDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private DocumentDao documentDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public int delete(Integer personId) throws BusinessException {
		// TODO Auto-generated method stub
		return personDao.deleteByPrimaryKey(personId);
	}

	@Transactional
	@Override
	public int insert(Person record) throws BusinessException {
		// TODO Auto-generated method stub
		return personDao.insert(record);
	}

	@Transactional
	@Override
	public int insertSelective(Person record) throws BusinessException {
		// TODO Auto-generated method stub
		return personDao.insertSelective(record);
	}

	@Transactional(readOnly = true)
	@Override
	public Person getPersonById(Integer personId) throws BusinessException {
		// TODO Auto-generated method stub
		return personDao.selectByPrimaryKey(personId);
	}

	@Override
	@Transactional
	public int updateSelective(Person record) throws BusinessException {
		// TODO Auto-generated method stub
		return personDao.updateByPrimaryKeySelective(record);
	}

	@Transactional
	@Override
	public int update(Person record) throws BusinessException {
		// TODO Auto-generated method stub
		return personDao.updateByPrimaryKey(record);
	}

	@Override
	@Transactional
	public PersonInfoVo getPersonInfo(String userId) throws BusinessException {
		// TODO Auto-generated method stub
		PersonInfoVo personInfo = new PersonInfoVo();
		try {
			Person person = personDao.getPersonByUserId(userId);// 获取个人用户信息
			PersonInfo baseInfo = personInfoDao.selectByPrimaryKey(person.getBaseInfoId());// 个人基本信息
			ContactInfo contact = contactDao.getContactInfoByUserId(userId);// 联系人信息
			AuditInfo auditInfo = auditDao.getAuditInfoByUserId(userId);// 审核信息
			Document personImage = documentDao.selectByPrimaryKey(baseInfo.getPersonImageId());// 个人照片
			Document idCardImage = documentDao.selectByPrimaryKey(baseInfo.getIdcardImageId());// 身份证照片
			Document enclosure = documentDao.selectByPrimaryKey(person.getEnclosureId());// 附件

			personInfo = setPersonToVo(personInfo, person);
			personInfo = setAuditInfoToVo(personInfo, auditInfo);
			personInfo = setBaseInfoToVo(personInfo, baseInfo);
			personInfo = setContactInfoToVo(personInfo, contact);
			personInfo = setPeronImageToVo(personInfo, personImage);
			personInfo = setIdCardToVo(personInfo, idCardImage);
			personInfo = setEnclosureToVo(personInfo, enclosure);

		} catch (Exception e) {
			errorLog("获取个人信息失败", e);
			throw new BusinessException(getMsg());
		}

		return personInfo;
	}

	/**
	 * 
	 * @param personInfo
	 * @param person
	 */
	private PersonInfoVo setPersonToVo(PersonInfoVo personInfo, Person person) {
		if (person != null) {
			personInfo.setPersonId(person.getPersonId());
			personInfo.setPoliticalLandscape(person.getPoliticalLandscape());
			personInfo.setLearnMajorId(person.getLearnMajorId());
			personInfo.setDegreeId(person.getDegreeId());
			personInfo.setUniversity(person.getUniversity());
			personInfo.setWorkMajorId(person.getWorkMajorId());
			personInfo.setIndustryId(person.getIndustryId());
			personInfo.setResearchDirection(person.getResearchDirection());
			personInfo.setKey(person.getKey());
			personInfo.setAchievement(person.getAchievement());
			personInfo.setAcademicTitleId(person.getAcademicTitleId());
			personInfo.setHighTechFieldId(person.getHighTechFieldId());
			personInfo.setLocationId(person.getLocationId());
		}
		return personInfo;
	}

	/**
	 * 设置附件
	 * 
	 * @param personInfo
	 * @param enclosure
	 * @return
	 */
	private PersonInfoVo setEnclosureToVo(PersonInfoVo personInfo, Document enclosure) {
		if (enclosure != null) {
			personInfo.setEnclosureId(enclosure.getDocumentId());
			personInfo.setEnclosureUrl(enclosure.getDocumentUrl());
			personInfo.setEnclosureDesc(enclosure.getDocumentDesc());
		}
		return personInfo;
	}

	/**
	 * 绑定基本信息
	 * 
	 * @param personInfo
	 * @param baseInfo
	 * @return
	 */
	private PersonInfoVo setBaseInfoToVo(PersonInfoVo personInfo, PersonInfo baseInfo) {

		if (baseInfo != null) {
			personInfo.setBaseInfoId(baseInfo.getPbiId());
			personInfo.setPersonName(baseInfo.getPersonName());
			personInfo.setSex(baseInfo.getSex());
			personInfo.setNation(baseInfo.getNation());
			personInfo.setBirth(baseInfo.getBirth());
			personInfo.setEducationId(baseInfo.getEducationId());
			personInfo.setWorkplace(baseInfo.getWorkplace());
			personInfo.setBusiness(baseInfo.getBusiness());
			personInfo.setPersonalProfile(baseInfo.getPersonalProfile());
			personInfo.setRemark(baseInfo.getRemark());
			personInfo.setIdcardNumber(baseInfo.getIdcardNumber());
		}
		return personInfo;
	}

	/**
	 * 绑定个人图片
	 * 
	 * @param personInfo
	 * @param personImage
	 * @return
	 */
	private PersonInfoVo setPeronImageToVo(PersonInfoVo personInfo, Document personImage) {

		if (personImage != null) {
			personInfo.setPersonImageId(personImage.getDocumentId());
			personInfo.setPersonImageUrl(personImage.getDocumentUrl());
		}
		return personInfo;
	}

	/**
	 * 绑定身份证信息
	 * 
	 * @param personInfo
	 * @param idCardImage
	 * @return
	 */
	private PersonInfoVo setIdCardToVo(PersonInfoVo personInfo, Document idCardImage) {
		if (idCardImage != null) {
			personInfo.setIdcardImageId(idCardImage.getDocumentId());
			personInfo.setImageUrl(idCardImage.getDocumentUrl());
		}
		return personInfo;
	}

	/**
	 * 绑定审核信息到个人信息
	 * 
	 * @param personInfo
	 * @param auditInfo
	 * @return
	 */
	private PersonInfoVo setAuditInfoToVo(PersonInfoVo personInfo, AuditInfo auditInfo) {
		if (auditInfo != null) {
			personInfo.setAuditInfoId(auditInfo.getAuditInfoId());
			personInfo.setSubmitTime(auditInfo.getSubmitTime());
			personInfo.setAuditTime(auditInfo.getAuditTime());
			personInfo.setAuditStatus(auditInfo.getAuditStatus());
			personInfo.setAuditResult(auditInfo.getAuditResult());
			personInfo.setUserId(auditInfo.getUserId());
			if (auditInfo.getUserId() != null && auditInfo.getUserId().equals("")) {
				personInfo.setUserName(userDao.getUserNameByUserId(auditInfo.getUserId()));
			}
		}
		return personInfo;
	}

	/**
	 * @param personInfo
	 * @param catactInfo
	 * @return
	 */
	private PersonInfoVo setContactInfoToVo(PersonInfoVo personInfo, ContactInfo contactInfo) {
		if (contactInfo != null) {
			personInfo.setContactId(contactInfo.getContactId());
			personInfo.setContactAddress(contactInfo.getContactAddress());
			personInfo.setContactBusiness(contactInfo.getContactBusiness());
			personInfo.setContactName(contactInfo.getContactName());
			personInfo.setPostcode(contactInfo.getPostcode());
			personInfo.setEmail(contactInfo.getEmail());
			personInfo.setContactUrl(contactInfo.getContactUrl());
			personInfo.setFaxNumber(contactInfo.getFaxNumber());
			personInfo.setQqormsnNumer(contactInfo.getQqormsnNumer());
			personInfo.setPhoneNumber(contactInfo.getPhoneNumber());
			personInfo.setContactNumber(contactInfo.getContactNumber());
		}

		return personInfo;
	}

	@Transactional
	@Override
	public void updatePersonInfo(PersonInfoVo personInfo) throws BusinessException {
		if (personInfo == null) {
			log.error("个人用户信息为空！");
			throw new BusinessException("个人用户信息为空！");
		}
		try {
			updateBaseInfo(personInfo);
			updateContact(personInfo);
			updatePerson(personInfo);
			// 设置审核状态为待审核状态
			auditDao.updateUserInfoAuditStatus(getCurrentUserId(), AuditInfo.AUDIT_STATUS_WAITING, "");
		} catch (Exception e) {
			errorLog("更新个人信息失败", personInfo.toString(), e);
			throw new BusinessException(getMsg());
		}
	}

	/**
	 * 更新用户
	 * 
	 * @param personInfo
	 * @param person
	 */
	private void updatePerson(PersonInfoVo personInfo) throws BusinessException {
		Person person = new Person();
		person.setPersonId(personInfo.getPersonId());
		person.setPoliticalLandscape(personInfo.getPoliticalLandscape());
		person.setLearnMajorId(personInfo.getLearnMajorId());
		person.setDegreeId(personInfo.getDegreeId());
		person.setUniversity(personInfo.getUniversity());
		person.setWorkMajorId(personInfo.getWorkMajorId());
		person.setIndustryId(personInfo.getIndustryId());
		person.setResearchDirection(personInfo.getResearchDirection());
		person.setKey(personInfo.getKey());
		person.setAchievement(personInfo.getAchievement());
		person.setAcademicTiTleId(personInfo.getAcademicTitleId());
		person.setHighTechFieldId(personInfo.getHighTechFieldId());
		person.setLocationId(personInfo.getLocationId());
		try {
			personDao.updateByPrimaryKeySelective(person);
		} catch (Exception e) {
			errorLog("更新person表失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	/**
	 * 更新基本信息
	 * 
	 * @param personInfo
	 * @param baseInfo
	 * @return
	 */
	private void updateBaseInfo(PersonInfoVo personInfo) throws BusinessException {

		PersonInfo baseInfo = new PersonInfo();
		baseInfo.setPbiId(personInfo.getBaseInfoId());
		baseInfo.setPersonName(personInfo.getPersonName());
		baseInfo.setSex(personInfo.getSex());
		baseInfo.setNation(personInfo.getNation());
		baseInfo.setBirth(personInfo.getBirth());
		baseInfo.setEducationId(personInfo.getEducationId());
		baseInfo.setWorkplace(personInfo.getWorkplace());
		baseInfo.setBusiness(personInfo.getBusiness());
		baseInfo.setPersonalProfile(personInfo.getPersonalProfile());
		baseInfo.setRemark(personInfo.getRemark());
		baseInfo.setIdcardNumber(personInfo.getIdcardNumber());

		try {
			personInfoDao.updateByPrimaryKeySelective(baseInfo);
		} catch (Exception e) {
			errorLog("更新person表失败", e);
			throw new BusinessException(getMsg());
		}

	}

	/**
	 * 更新联系人
	 * 
	 * @param personInfo
	 * @param catactInfo
	 * @return
	 */
	private void updateContact(PersonInfoVo personInfo) {

		ContactInfo contact = new ContactInfo();
		contact.setContactId(personInfo.getContactId());
		contact.setContactAddress(personInfo.getContactAddress());
		contact.setContactBusiness(personInfo.getContactBusiness());
		contact.setContactName(personInfo.getContactName());
		contact.setPostcode(personInfo.getPostcode());
		contact.setEmail(personInfo.getEmail());
		contact.setContactUrl(personInfo.getContactUrl());
		contact.setFaxNumber(personInfo.getFaxNumber());
		contact.setQqormsnNumer(personInfo.getQqormsnNumer());
		contact.setPhoneNumber(personInfo.getPhoneNumber());
		contact.setContactNumber(personInfo.getContactNumber());

		try {
			contactDao.updateByPrimaryKeySelective(contact);
		} catch (Exception e) {
			errorLog("更新联系人信息失败！", e);
			throw new BusinessException(getMsg());
		}
	}

	@Override
	public PersonInfoVo getAuditPersonInfo(String userId) throws BusinessException {
		PersonInfoVo personInfo = null;
		try {
			try {
				personInfo = getPersonInfo(userId);
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
		return personInfo;
	}

}
