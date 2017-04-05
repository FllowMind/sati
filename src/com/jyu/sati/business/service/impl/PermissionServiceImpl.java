package com.jyu.sati.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jyu.sati.business.dao.AdminstratorDao;
import com.jyu.sati.business.dao.AgencyDao;
import com.jyu.sati.business.dao.AuditInfoDao;
import com.jyu.sati.business.dao.CompanyDao;
import com.jyu.sati.business.dao.ContactInfoDao;
import com.jyu.sati.business.dao.EmployeeSituationDao;
import com.jyu.sati.business.dao.MenuDao;
import com.jyu.sati.business.dao.PermissionDao;
import com.jyu.sati.business.dao.PermissionMenuDao;
import com.jyu.sati.business.dao.PersonDao;
import com.jyu.sati.business.dao.PersonInfoDao;
import com.jyu.sati.business.dao.RoleDao;
import com.jyu.sati.business.dao.RolePermissionDao;
import com.jyu.sati.business.dao.SystemLogDao;
import com.jyu.sati.business.dao.UnitBaseInfoDao;
import com.jyu.sati.business.dao.UnitInfoDao;
import com.jyu.sati.business.dao.UserDao;
import com.jyu.sati.business.dao.UserRoleDao;
import com.jyu.sati.business.service.PermissionService;
import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.common.util.MenuUtil;
import com.jyu.sati.common.util.PasswordUtil;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.common.util.SystemLogUtil;
import com.jyu.sati.entity.Adminstrator;
import com.jyu.sati.entity.Agency;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.Company;
import com.jyu.sati.entity.ContactInfo;
import com.jyu.sati.entity.EmployeeSituation;
import com.jyu.sati.entity.Menu;
import com.jyu.sati.entity.Permission;
import com.jyu.sati.entity.PermissionMenu;
import com.jyu.sati.entity.Person;
import com.jyu.sati.entity.PersonInfo;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.RolePermission;
import com.jyu.sati.entity.SystemLog;
import com.jyu.sati.entity.UnitBaseInfo;
import com.jyu.sati.entity.UnitInfo;
import com.jyu.sati.entity.User;
import com.jyu.sati.entity.UserRole;
import com.jyu.sati.vo.MenuVo;
import com.jyu.sati.vo.RegisterVo;

/**
 * 用户、权限服务
 * 
 * @author 淋雨又调皮
 *
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private AuditInfoDao auditDao;
	@Autowired
	private AdminstratorDao adminDao;
	@Autowired
	private AgencyDao agencyDao;
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private EmployeeSituationDao empDao;
	@Autowired
	private PersonDao personDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	@Autowired
	private UnitBaseInfoDao ubiDao;
	@Autowired
	private ContactInfoDao contactDao;
	@Autowired
	private UnitInfoDao uiDao;
	@Autowired
	private RolePermissionDao rolePermDao;
	@Autowired
	private PermissionMenuDao permMenuDao;
	@Autowired
	private UserRoleDao urDao;
	@Autowired
	private SystemLogDao logDao;

	/*
	 * 通过用户名和密码获取用户
	 */
	@Transactional(readOnly = true)
	@Override
	public User getUserByUserNameAndPassword(String userName, String password) throws BusinessException {
		// TODO Auto-generated method stub
		return userDao.getUserByUserNameAndPassword(userName, password);
	}

	@Transactional
	@Override
	public User getUserByUserName(String userName) throws BusinessException {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName);
	}

	@Override
	public int saveUser(User user) throws BusinessException {
		// TODO Auto-generated method stub
		return userDao.updateByPrimaryKey(user);
	}

	@Transactional
	@Override
	public int insertUser(RegisterVo regInfo) throws BusinessException {
		// TODO Auto-generated method stub
		String msg = "";

		if (!StringUtil.isNotEmpty(regInfo.getUserId())) {
			msg = "用户账号为空!";
			log.error(msg);
			throw new BusinessException(msg);
		} else if (!StringUtil.isNotEmpty(regInfo.getPassword())) {
			msg = "用户密码为空";
			log.error(msg);
			throw new BusinessException(msg);
		} else if (!StringUtil.isNotEmpty(regInfo.getEmail())) {
			msg = "邮箱为空！";
			log.error(msg);
			throw new BusinessException(msg);
		} else if (regInfo.getUserType() == null) {
			msg = "用户类型为空！";
			log.error(msg);
			throw new BusinessException(msg);
		} else if (regInfo.getPhoneNumber() == null || regInfo.getPhoneNumber().equals("")) {
			msg = "电话号码为空！";
			log.error(msg);
			throw new BusinessException(msg);
		} else if (userDao.isPhoneNumberInUsing(regInfo.getPhoneNumber()) > 0) {
			msg = "该电话号码已被使用！";
			log.error(msg);
			throw new BusinessException(msg);
		}
		User user = new User();
		ContactInfo contact = new ContactInfo();
		contact.setEmail(regInfo.getEmail());
		contact.setPhoneNumber(regInfo.getPhoneNumber());
		try {
			contactDao.insert(contact);// 插入联系信息

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("绑定联系信息失败！");
		}
		user.setUserId(regInfo.getUserId());// 账号
		user.setPassword(regInfo.getPassword());// 密码
		user.setUserType(regInfo.getUserType());// 用户类型
		user.setUserName(regInfo.getUserId());// 用户名
		user.setContactInfoId(contact.getContactId());// 绑定联系人

		// 对密码进行加密
		String password = PasswordUtil.getMD5Password(user.getUserId(), user.getPassword());
		user.setPassword(password);

		user.setPreupdateTime(new Date());// 设置最新更新 时间
		user.setRegisterTime(new Date());// 设置注册时间
		user.setStatus(User.USER_STATUS_NORMAL);// 设置为正常使用状态

		int userType = user.getUserType();// 根据用户类型创建相应的用户

		// 绑定审核信息
		AuditInfo audit = new AuditInfo();
		// 如果不是管理员，需要创建审核数据
		if (userType != User.USER_TYPE_PLATFORM || userType != User.USER_TYPE_SUPER) {

			audit.setAuditStatus(AuditInfo.AUDIT_STATUS_NO_AUDIT);
			try {
				auditDao.insert(audit);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new BusinessException("绑定审核信息失败！");
			}
			user.setAuditId(audit.getAuditInfoId());
		}

		int roleType = -1;

		// 绑定用户基本信息
		switch (userType) {
		case User.USER_TYPE_PERSON:
			user = bindPerson(user);
			roleType = Role.PERSON;
			break;
		case User.USER_TYPE_AGENCY:
			user = bindAgency(user);
			roleType = Role.AGENCY;
			break;
		case User.USER_TYPE_COLLEGE:
			roleType = Role.COLLEAGE;
		case User.USER_TYPE_SCIENTIFIC:
			user = bindUnit(user);
			if (roleType != Role.COLLEAGE) {
				roleType = Role.SCIENTIFIC;
			}
			break;
		case User.USER_TYPE_COMPANY:
			user = bindCompany(user);
			roleType = Role.COMPANY;
			break;
		case User.USER_TYPE_PLATFORM:
			roleType = Role.ROLE_PLATFORM;
		case User.USER_TYPE_SUPER:
			user = bindAdmin(user);
			if (roleType != Role.ROLE_PLATFORM) {
				roleType = Role.ROLE_SUPER_ADMIN;
			}
			break;
		}
		int result = -1;
		try {
			result = userDao.insertSelective(user);
			// 将用户id放入审核信息，更新审核信息
			audit.setUserId(user.getUserId());
			auditDao.updateByPrimaryKeySelective(audit);
			// 绑定角色
			UserRole bindInfo = new UserRole();
			bindInfo.setRoleId(roleType);
			bindInfo.setUserId(user.getUserId());
			bindUserRole(bindInfo);// 绑定用户和角色
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("【" + user.getUserId() + "】" + "注册失败！");
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Permission> getPermissionsByRoleId(Integer roleId) throws BusinessException {
		// TODO Auto-generated method stub
		return permissionDao.getPermissionByRoleId(roleId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> getRolesByUserId(String userId) throws BusinessException {
		List<Role> roles = new ArrayList<>();
		try {
			roles = roleDao.getRoleByUserId(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException(e);
		}

		return roles;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Menu> getMenusByPermissionId(Integer permissionId) throws BusinessException {
		// TODO Auto-generated method stub
		List<Menu> menus = new ArrayList<>();
		try {
			menus = menuDao.getMenuByPermissionId(permissionId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException(e);
		}

		return menus;
	}

	/**
	 * 绑定个人用户
	 * 
	 * @param user
	 */
	private User bindPerson(User user) throws BusinessException {
		PersonInfo personInfo = new PersonInfo();
		try {
			personInfoDao.insert(personInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定联系人！");
		}
		Person person = new Person();
		person.setBaseInfoId(personInfo.getPbiId());// 绑定个人基本信息
		try {
			personDao.insert(person);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定个人基本信息！");
		}
		user.setPersonId(person.getPersonId());// 绑定个人用户信息
		return user;
	}

	/**
	 * 绑定企业信息
	 * 
	 * @param user
	 */
	private User bindCompany(User user) throws BusinessException {
		EmployeeSituation emp = new EmployeeSituation();
		try {
			empDao.insert(emp);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定单位人员信息！");
		}
		UnitBaseInfo baseInfo = new UnitBaseInfo();
		try {
			ubiDao.insert(baseInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定单位基本信息！"); // TODO: handle
														// exception
		}
		Company company = new Company();
		company.setSoeId(emp.getSoeId());// 绑定单位人员信息
		company.setBaseInfoId(baseInfo.getUnitBaseinfoId());// 绑定单位基本信息
		try {
			companyDao.insert(company);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定企业单位信息！"); // TODO: handle
														// exception
		}
		user.setCompanyId(company.getCompanyId());// 绑定公司
		return user;
	}

	/**
	 * 绑定科研单位或高校信息
	 * 
	 * @param user
	 * @return
	 */
	private User bindUnit(User user) throws BusinessException {
		EmployeeSituation emp = new EmployeeSituation();
		try {
			empDao.insert(emp);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定单位人员信息！");
		}
		UnitBaseInfo baseInfo = new UnitBaseInfo();
		try {
			ubiDao.insert(baseInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定单位基本信息！");
		}
		UnitInfo unit = new UnitInfo();
		unit.setSoeId(emp.getSoeId());// 绑定从业人员信息
		unit.setBaseInfoId(baseInfo.getUnitBaseinfoId());// 绑定单位基本信息
		// 设置单位类型
		if (user.getUserType() == User.USER_TYPE_COLLEGE) {
			unit.setUnitType(UnitInfo.UNIT_TYPE_COLLEAGE);
			try {
				uiDao.insert(unit);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new BusinessException("无法绑定高校信息！");
			}
			user.setUniversityId(unit.getUnitInfoId());// 绑定高校信息
		} else {
			unit.setUnitType(UnitInfo.UNIT_TYPE_SCIENTIFIC);
			try {
				uiDao.insert(unit);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new BusinessException("无法绑定科研单位信息！"); // TODO: handle
			}
			user.setSruId(unit.getUnitInfoId());// 绑定科研单位
		}

		return user;
	}

	/**
	 * 绑定中介机构信息
	 * 
	 * @param user
	 * @return
	 */
	private User bindAgency(User user) throws BusinessException {
		EmployeeSituation emp = new EmployeeSituation();
		empDao.insert(emp);
		UnitBaseInfo baseInfo = new UnitBaseInfo();
		try {
			ubiDao.insert(baseInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定单位基本信息！");
		}
		Agency agency = new Agency();
		agency.setSoeId(emp.getSoeId());// 绑定单位从业人员信息
		agency.setBaseInfoId(baseInfo.getUnitBaseinfoId());// 绑定基本信息
		try {
			agencyDao.insert(agency);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定中介机构信息！");
		}
		user.setAgencyId(agency.getAgencyId());// 绑定中介机构信息
		return user;
	}

	/**
	 * 绑定管理员信息
	 * 
	 * @param user
	 * @param contact
	 * @return
	 */
	private User bindAdmin(User user) throws BusinessException {
		PersonInfo info = new PersonInfo();
		try {
			personInfoDao.insert(info);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("无法绑定联系人信息！");
		}
		Adminstrator admin = new Adminstrator();
		admin.setPbiId(info.getPbiId());// 绑定用户个人信息
		if (user.getUserType() == User.USER_TYPE_PLATFORM) {
			admin.setAdminType(Adminstrator.ADMIN_TYPE_PLATFORM);
			try {
				adminDao.insert(admin);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new BusinessException("无法绑定平台管理员信息！");
			}
			user.setAdministratorId(admin.getAdministratorId());// 绑定平台管理员
		} else {
			admin.setAdminType(Adminstrator.ADMIN_TYPE_SUPER);
			try {
				adminDao.insert(admin);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
				throw new BusinessException("无法绑定超级管理员信息！");
			}
			user.setSuperAdministratorId(admin.getAdministratorId());// 绑定超级管理员
		}
		return user;
	}

	@Transactional
	@Override
	public int removeUser(String userId) throws BusinessException {
		// TODO Auto-generated method stub
		User user = userDao.getUserByUserName(userId);
		int userType = user.getUserType();
		switch (userType) {
		case User.USER_TYPE_AGENCY:
			try {
				deleteAgency(user);
			} catch (Exception e) {
				throw e;
			}
			break;
		case User.USER_TYPE_COMPANY:
			try {
				deleteCompany(user);
			} catch (Exception e) {
				throw e;
			}
			break;
		case User.USER_TYPE_PERSON:
			try {
				deletePerson(user);
			} catch (Exception e) {
				throw e;
			}
			break;
		case User.USER_TYPE_PLATFORM:
		case User.USER_TYPE_SUPER:
			try {
				deleteAdmin(user);
			} catch (Exception e) {
				throw e;
			}
			break;
		case User.USER_TYPE_SCIENTIFIC:
		case User.USER_TYPE_COLLEGE:
			try {
				deleteUnit(user);
			} catch (Exception e) {
				throw e;
			}
			break;
		}

		try {
			auditDao.deleteByPrimaryKey(user.getAuditId());// 删除用户
			urDao.deleteByUserId(user.getUserId());// 删除用户角色绑定
			// 写入系统日志
			logDao.insertSelective(
					SystemLogUtil.getUserManagmentLog(getCurrentUserId(), "【删除】【用户" + user.getUserId() + "】"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除审核信息失败！ID为" + "【" + user.getAuditId() + "】");
		}
		int result = -1;
		try {
			result = userDao.deleteByPrimaryKey(userId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除用户信息失败！ID为" + "【" + userId + "】");
		}
		return result;
	}

	/**
	 * 删除中介机构相关信息
	 * 
	 * @param user
	 */
	private void deleteAgency(User user) throws BusinessException {
		Agency agency = agencyDao.selectByPrimaryKey(user.getAgencyId());
		UnitBaseInfo unitBaseInfo = ubiDao.selectByPrimaryKey(agency.getBaseInfoId());
		EmployeeSituation emp = empDao.selectByPrimaryKey(agency.getSoeId());
		try {
			contactDao.deleteByPrimaryKey(user.getContactInfoId());// 删除联系人
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除联系人信息失败！ID为" + "【" + unitBaseInfo.getContactId() + "】");
		}
		try {
			empDao.deleteByPrimaryKey(emp.getSoeId());// 删除单位从业人员
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位人员信息失败！ID为" + "【" + emp.getSoeId() + "】");
		}
		try {
			ubiDao.deleteByPrimaryKey(unitBaseInfo.getUnitBaseinfoId());// 删除单位基本信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位基本信息失败！ID为" + "【" + unitBaseInfo.getUnitBaseinfoId() + "】");
		}
		try {
			agencyDao.deleteByPrimaryKey(agency.getAgencyId());// 删除中介机构
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除中介机构信息失败！ID为" + "【" + agency.getAgencyId() + "】");
		}

	}

	private void deleteAdmin(User user) throws BusinessException {
		Adminstrator admin = null;
		if (user.getUserType() == User.USER_TYPE_PLATFORM) {
			admin = adminDao.selectByPrimaryKey(user.getAdministratorId());
		} else {
			admin = adminDao.selectByPrimaryKey(user.getSuperAdministratorId());
		}
		PersonInfo info = personInfoDao.selectByPrimaryKey(admin.getPbiId());
		try {
			contactDao.deleteByPrimaryKey(user.getContactInfoId());// 删除联系人
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除联系人信息失败！ID为" + "【" + info.getContactId() + "】");
		}
		try {
			personInfoDao.deleteByPrimaryKey(info.getPbiId());// 删除个人基本信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除个人基本信息失败！ID为" + "【" + info.getPbiId() + "】");
		}
		try {
			adminDao.deleteByPrimaryKey(admin.getAdministratorId());// 删除管理员信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除管理员信息失败！ID为" + "【" + admin.getAdministratorId() + "】");
		}
	}

	private void deleteUnit(User user) throws BusinessException {
		UnitInfo unit = null;
		if (user.getUserType() == User.USER_TYPE_COLLEGE) {
			unit = uiDao.selectByPrimaryKey(user.getUniversityId());
		} else {
			unit = uiDao.selectByPrimaryKey(user.getSruId());
		}
		UnitBaseInfo baseInfo = ubiDao.selectByPrimaryKey(unit.getBaseInfoId());
		try {
			contactDao.deleteByPrimaryKey(user.getContactInfoId());// 删除联系人
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除联系人信息失败！ID为" + "【" + baseInfo.getContactId() + "】");
		}
		try {
			empDao.deleteByPrimaryKey(unit.getSoeId());// 删除单位从业人员信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位人员信息失败！ID为" + "【" + unit.getSoeId() + "】");
		}

		try {
			ubiDao.deleteByPrimaryKey(baseInfo.getUnitBaseinfoId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位基本信息失败！ID为" + "【" + baseInfo.getUnitBaseinfoId() + "】");
		}
		try {
			uiDao.deleteByPrimaryKey(unit.getUnitInfoId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位信息失败！ID为" + "【" + unit.getUnitInfoId() + "】");
		}

	}

	private void deleteCompany(User user) throws BusinessException {
		Company company = companyDao.selectByPrimaryKey(user.getCompanyId());
		UnitBaseInfo baseInfo = ubiDao.selectByPrimaryKey(company.getBaseInfoId());
		try {
			contactDao.deleteByPrimaryKey(user.getContactInfoId());// 删除联系人
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除联系人信息失败！ID为" + "【" + baseInfo.getContactId() + "】");
		}
		try {
			empDao.deleteByPrimaryKey(company.getSoeId());// 删除单位从业人员信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位人员信息失败！ID为" + "【" + company.getSoeId() + "】");
		}
		try {
			ubiDao.deleteByPrimaryKey(baseInfo.getUnitBaseinfoId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除单位基本信息失败！ID为" + "【" + baseInfo.getUnitBaseinfoId() + "】");
		}
		try {
			companyDao.deleteByPrimaryKey(company.getCompanyId());
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除企业失败！ID为" + "【" + company.getCompanyId() + "】");
		}
	}

	private void deletePerson(User user) throws BusinessException {
		Person person = personDao.selectByPrimaryKey(user.getPersonId());
		PersonInfo info = personInfoDao.selectByPrimaryKey(person.getBaseInfoId());
		try {
			contactDao.deleteByPrimaryKey(user.getContactInfoId());// 删除联系人
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除联系人信息失败！ID为" + "【" + info.getContactId() + "】");
		}
		try {
			personInfoDao.deleteByPrimaryKey(info.getPbiId());// 删除个人基本信息
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除个人基本信息失败！ID为" + "【" + info.getPbiId() + "】");
		}
		try {
			personDao.deleteByPrimaryKey(person.getPersonId());// 删除个人用户
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			throw new BusinessException("删除个人信息失败！ID为" + "【" + person.getPersonId() + "】");
		}
	}

	// ***********************菜单************************

	@Transactional(readOnly = true)
	@Override
	public Map<String, List<Menu>> getMenusByUserId(String userId) throws BusinessException {
		// TODO Auto-generated method stub
		List<Menu> firstMenus = new ArrayList<Menu>();// 一级菜单
		List<Menu> secondsMenus = new ArrayList<Menu>();// 二级菜单
		List<Menu> thirdMenus = new ArrayList<Menu>();// 三级菜单
		Map<String, List<Menu>> menus = new HashMap<>();
		try {
			List<Role> roles = getRolesByUserId(userId);
			for (Role role : roles) {
				List<Permission> permissions = permissionDao.getPermissionByRoleId(role.getRoleId());
				for (Permission perm : permissions) {
					List<Menu> allMenus = menuDao.getMenuByPermissionId(perm.getPermissionId());
					for (Menu menu : allMenus) {
						if (menu.getMenuLevel() == 1) {
							firstMenus.add(menu);
						} else if (menu.getMenuLevel() == 2) {
							secondsMenus.add(menu);
						}
						if (menu.getMenuLevel() == 3) {
							thirdMenus.add(menu);
						}
					}
				}
			}

			MenuUtil.menuSort(firstMenus);
			MenuUtil.menuSort(secondsMenus);
			MenuUtil.menuSort(thirdMenus);
			menus.put("firstMenus", firstMenus);
			menus.put("secondMenus", secondsMenus);
			menus.put("thirdMenus", thirdMenus);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("无法加载菜单 ：" + e.getMessage());
			throw new BusinessException(e);
		}
		return menus;
	}

	@Transactional(readOnly = true)
	@Override
	public Map<String, List<Menu>> getAllMenu() throws BusinessException {
		// TODO Auto-generated method stub
		List<Menu> firstMenus = new ArrayList<Menu>();// 一级菜单
		List<Menu> secondsMenus = new ArrayList<Menu>();// 二级菜单
		List<Menu> thirdMenus = new ArrayList<Menu>();// 三级菜单
		Map<String, List<Menu>> menus = new HashMap<>();
		try {
			List<Menu> allMenus = menuDao.getAllMenuByLevel(null);
			for (Menu menu : allMenus) {
				if (menu.getMenuLevel() == 1) {
					firstMenus.add(menu);
				} else if (menu.getMenuLevel() == 2) {
					secondsMenus.add(menu);
				}
				if (menu.getMenuLevel() == 3) {
					thirdMenus.add(menu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取所有菜单出现异常：" + e.getMessage());
			throw new BusinessException(e);
		}
		MenuUtil.menuSort(firstMenus);
		MenuUtil.menuSort(secondsMenus);
		MenuUtil.menuSort(thirdMenus);
		menus.put("firstMenus", firstMenus);
		menus.put("secondMenus", secondsMenus);
		menus.put("thirdMenus", thirdMenus);
		return menus;
	}

	@Transactional
	@Override
	public int updateMenuStatus(Integer menuId) throws BusinessException {
		int result = -1;
		if (menuId == null) {
			throw new BusinessException("菜单ID为空！");
		}
		try {
			result = menuDao.updateMenuStatus(menuId);
			if (result < 1) {
				throw new BusinessException("没有菜单被更新！");
			}
			int status = menuDao.getMenuStatus(menuId);
			updateChildMenusStatus(menuId, status);// 更新子菜单状态
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "更新了【菜单】ID为【" + menuId + "】的状态"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("更新菜单状态失败！菜单ID:【" + menuId + "】" + e.getMessage());
			throw new BusinessException(e);
		}
		return result;
	}

	/**
	 * 递归更新子菜单状态
	 * 
	 * @param menuId
	 * @throws BusinessException
	 */
	private void updateChildMenusStatus(Integer menuId, int status) throws BusinessException {
		try {
			menuDao.updateChildStatus(menuId, status);// 更新子菜单状态
			List<Menu> childs = menuDao.getAllChildMenus(menuId);
			for (Menu child : childs) {
				updateChildMenusStatus(child.getMenuId(), status);// 递归更新
				// 写入系统日志
				addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "更新了【菜单】ID为【" + child.getMenuId() + "】的状态"));
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}

	}

	@Transactional
	@Override
	public int addMenu(MenuVo menuVo) throws BusinessException {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			Menu menu = new Menu();
			menu.setFatherMenuId(menuVo.getFatherMenuId());
			menu.setLinkUrl(menuVo.getLinkUrl());
			menu.setMenuLevel(menuVo.getMenuLevel());
			menu.setMenuName(menuVo.getMenuName());
			menu.setMenuOrder(menuVo.getMenuOrder());
			menu.setCreateTime(new Date());// 设置菜单时间
			menu.setStatus(Menu.MENU_STATUS_NORMAL);// 设置菜单状态
			try {
				result = menuDao.insertSelective(menu);
			} catch (Exception e) {
				errorLog("插入菜单失败！", e);
				throw new BusinessException(getMsg());
			}
			try {
				PermissionMenu pm = new PermissionMenu();
				pm.setPermissionId(menuVo.getBindPerminssionId());
				pm.setMenuId(menu.getMenuId());
				pm.setCreateTime(new Date());
				permMenuDao.insertSelective(pm);
			} catch (Exception e) {
				errorLog("绑定权限失败！", e);
				throw new BusinessException(getMsg());
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【添加】新菜单【" + menu.getMenuName() + "】"));
		} catch (Exception e) {
			errorLog("添加菜单失败！", e);
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Transactional
	@Override
	public int updateMenu(MenuVo menuVo) throws BusinessException {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			Menu menu = new Menu();
			menu.setMenuId(menuVo.getMenuId());
			menu.setFatherMenuId(menuVo.getFatherMenuId());
			menu.setLinkUrl(menuVo.getLinkUrl());
			menu.setMenuLevel(menuVo.getMenuLevel());
			menu.setMenuName(menuVo.getMenuName());
			menu.setMenuOrder(menuVo.getMenuOrder());
			menu.setCreateTime(new Date());// 设置菜单时间
			try {
				result = menuDao.updateByPrimaryKeySelective(menu);
			} catch (Exception e) {
				errorLog("更新菜单失败！", e);
				throw new BusinessException(getMsg());
			}
			if (menuVo.getBindPerminssionId() != null && menuVo.getBindPerminssionId() != -1) {
				try {
					PermissionMenu pm = permMenuDao.getPermissionMenuByMenuId(menuVo.getMenuId());
					pm.setPermissionId(menuVo.getBindPerminssionId());
					permMenuDao.updateByPrimaryKeySelective(pm);
				} catch (Exception e) {
					errorLog("更新绑定权限失败！", e);
					throw new BusinessException(getMsg());
				}
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【更新】了菜单【" + menu.getMenuId() + "】"));

		} catch (Exception e) {
			throw new BusinessException(getMsg());
		}
		return result;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Menu> getMenuByLevel(Integer level) throws BusinessException {
		// TODO Auto-generated method stub
		List<Menu> allMenus = new ArrayList<>();
		if (level == null) {
			throw new BusinessException("菜单级别为空！");
		}
		try {
			allMenus = menuDao.getAllMenuByLevel(level);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取所有菜单出现异常：" + e.getMessage());
			throw new BusinessException(e);
		}
		return allMenus;
	}

	@Transactional(readOnly = true)
	@Override
	public List<Permission> getAllChildPermissions(Integer fatherId) throws BusinessException {
		// TODO Auto-generated method stub
		if (fatherId == null) {
			throw new BusinessException("父权限ID为空！");
		}
		try {
			return permissionDao.getAllChildPermissions(fatherId);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("获取子权限失败！父权限ID:【" + fatherId + "】", e);
			throw new BusinessException(e);
		}
	}

	// ***************************权限****************************

	@Transactional(readOnly = true)
	@Override
	public boolean isFatherPermissionNormal(Integer permissionId) throws BusinessException {
		boolean isNormal = false;
		try {
			Permission perm = permissionDao.selectByPrimaryKey(permissionId);
			if (perm != null) {
				if (perm.getStatus() != -1) {
					isNormal = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(e);
		}
		return isNormal;
	}

	@Transactional
	@Override
	public int addPermission(Permission permission) throws BusinessException {
		// TODO Auto-generated method stub
		int result = -1;
		try {
			try {
				permission.setCreateTime(new Date());// 设置创建时间
				permission.setOperateTime(new Date());// 设置操作时间
				// permission.setStatus(Permission.PERMISSION_STATUS_NORMAL);
				permission.setCreateTime(new Date());
				result = permissionDao.insertSelective(permission);
			} catch (Exception e) {
				errorLog("创建权限失败！", permission.toString(), e);
				throw new BusinessException(getMsg());
			}
			try {
				RolePermission rp = new RolePermission();
				rp.setCreateTime(new Date());
				rp.setRoleId(Role.ROLE_SUPER_ADMIN);// 创建时默认绑定超级管理员
				if (permission.getPermissionId() == null) {
					errorLog("权限Id为空！");
				}
				rp.setPermissionId(permission.getPermissionId());
				rolePermDao.insertSelective(rp);// 绑定角色权限
			} catch (Exception e) {
				errorLog("绑定角色权限失败！", permission.toString(), e);
				throw new BusinessException(getMsg());
			}
			// 写入系统日志
			addSystemLog(
					SystemLogUtil.getMenuLog(getCurrentUserId(), "【添加】新权限【" + permission.getPermissionName() + "】"));
		} catch (Exception e) {
			throw new BusinessException(getMsg());
		}

		return result;
	}

	@Transactional
	@Override
	public int updatePermissionStatus(Integer permissionId) throws BusinessException {
		int result = -1;
		try {
			result = permissionDao.updatePermissionStatus(permissionId);
			if (result < 1) {
				throw new BusinessException("没有权限被更新！");
			}
			Integer status = permissionDao.getPermissionStatus(permissionId);
			updateChildStatus(permissionId, status);// 更新所有子权限的状态
			// 写入系统日志
			addSystemLog(SystemLogUtil.getPermissionLog(getCurrentUserId(), "【更新】【权限" + permissionId + "】的状态"));

		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新权限失败！权限ID:【" + permissionId + "】" + e.getMessage());
			throw new BusinessException(e);
		}
		return result;
	}

	/**
	 * 递归更新该权限下的所有子权限
	 * 
	 * @param fatherId
	 */
	@Transactional
	private void updateChildStatus(Integer permissionId, int status) throws BusinessException {
		try {
			permissionDao.updateChildStatus(permissionId, status);// 更新所有子权限的状态
			List<Permission> childs = permissionDao.getAllChildPermissions(permissionId);
			for (Permission child : childs) {
				updateChildStatus(child.getPermissionId(), status);// 更新子权限的子权限状态
				// 写入系统日志
				addSystemLog(SystemLogUtil.getPermissionLog(getCurrentUserId(),
						"【更新】【权限" + child.getPermissionId() + "】的状态"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException(e);
		}
	}

	@Transactional
	@Override
	public List<Permission> getPermissionsByLevel(Integer level) throws BusinessException {
		List<Permission> permissions = new ArrayList<>();
		try {
			permissions = permissionDao.getAllPermissions(level);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("获取权限失败：" + e.getMessage());
			throw new BusinessException(e);
		}
		return permissions;
	}

	@Transactional
	@Override
	public int updatePermission(Permission permission) {
		int result = -1;
		try {
			permission.setOperateTime(new Date());// 设置最新操作时间
			result = permissionDao.updateByPrimaryKey(permission);
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【更新】了【权限" + permission.getPermissionId() + "】"));

		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新权限失败！权限ID:【" + permission.getPermissionId() + "】" + e.getMessage());
			throw new BusinessException(e);
		}
		return result;
	}

	@Transactional
	@Override
	public Map<String, List<Permission>> getAllPermissions() throws BusinessException {
		// TODO Auto-generated method stub
		List<Permission> firstPermissons = new ArrayList<>();
		List<Permission> secondPermissons = new ArrayList<>();
		List<Permission> thirdPermissons = new ArrayList<>();

		Map<String, List<Permission>> permissions = new HashMap<>();
		try {
			List<Permission> allPermission = permissionDao.getAllPermissions(null);
			for (Permission perm : allPermission) {
				if (perm.getPermissionLevel() == 1) {
					firstPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 2) {
					secondPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 3) {
					thirdPermissons.add(perm);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("获取权限失败！原因:" + e.getMessage());
		}

		permissions.put("firstPermissions", firstPermissons);
		permissions.put("secondPermissions", secondPermissons);
		permissions.put("thirdPermissions", thirdPermissons);
		return permissions;
	}

	@Transactional
	@Override
	public List<Menu> getAllChildMenus(Integer fatherId) throws BusinessException {
		if (fatherId == null) {
			throw new BusinessException("父菜单ID为空！");
		}
		try {
			return menuDao.getAllChildMenus(fatherId);
		} catch (Exception e) {
			log.error("获取子菜单失败！父菜单ID:【" + fatherId + "】", e);
			throw new BusinessException(e);
		}
	}

	@Transactional
	@Override
	public void removeMenu(Integer menuId) throws BusinessException {
		if (menuId == null) {
			throw new BusinessException("菜单ID为空！");
		}
		try {
			menuDao.unBindWithFatherMenu(menuId);// 解除与子菜单的绑定
			permMenuDao.unBindPermissionMenu(null, menuId);// 解除权限与菜单
			if (menuDao.deleteByPrimaryKey(menuId) < 1) {
				throw new BusinessException("删除菜单失败！菜单ID:【" + menuId + "】");
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【删除】了【菜单" + menuId + "】"));

		} catch (Exception e) {
			log.error("删除菜单失败！菜单ID:【" + menuId + "】", e);
			throw new BusinessException(e);
		}
	}

	@Transactional
	@Override
	public void removePermission(Integer permissionId) throws BusinessException {
		// TODO Auto-generated method stub
		if (permissionId == null) {
			throw new BusinessException("权限ID为空！");
		}
		try {
			RolePermission rp = new RolePermission();
			rp.setPermissionId(permissionId);
			permMenuDao.unBindPermissionMenu(permissionId, null);// 解除绑定菜单和权限
			rolePermDao.unBindRolePermission(rp);// 解除角色和菜单的绑定
			if (permissionDao.deleteByPrimaryKey(permissionId) < 1) {
				throw new BusinessException("删除权限失败！权限ID:【" + permissionId + "】");
			}
			// 递归删除该权限下的所有子权限
			List<Permission> childs = permissionDao.getAllChildPermissions(permissionId);
			for (Permission child : childs) {
				removePermission(child.getPermissionId());
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【删除】了【权限" + permissionId + "】"));

		} catch (Exception e) {
			log.error("删除权限失败！权限ID:【" + permissionId + "】", e);
			throw new BusinessException(e);
		}
	}

	@Transactional
	@Override
	public void bindRolePermission(RolePermission bindInfo) throws BusinessException {
		try {
			bindInfo.setCreateTime(new Date());
			if (rolePermDao.insertSelective(bindInfo) < 1) {
				log.error("没有角色权限被绑定！");
				throw new BusinessException("没有角色权限被绑定！！");
			}
			try {
				// 获取该被绑定权限的所有子权限
				List<Permission> childs = permissionDao.getAllUnBindRoleChildsByFatherId(bindInfo);
				for (Permission child : childs) {
					// 递归绑定所有的子权限
					bindInfo.setPermissionId(child.getPermissionId());
					bindRolePermission(bindInfo);
				}
			} catch (Exception e) {
				errorLog("绑定角色权限的子权限时失败！", e);
				throw new BusinessException(getMsg());
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(),
					"【绑定】了【权限" + bindInfo.getPermissionId() + "】" + "和【角色" + bindInfo.getRoleId() + "】"));

		} catch (Exception e) {
			log.error("绑定角色权限失败！", e);
			throw new BusinessException("绑定角色权限失败！");
		}
	}

	@Transactional
	@Override
	public void bindPermissionMenu(PermissionMenu bindInfo) throws BusinessException {
		try {
			bindInfo.setCreateTime(new Date());
			if (permMenuDao.insertSelective(bindInfo) < 1) {
				log.error("没有权限菜单被绑定！");
				throw new BusinessException("没有权限菜单被绑定！！");
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(),
					"【绑定】了【权限" + bindInfo.getPermissionId() + "】" + "和【菜单" + bindInfo.getMenuId() + "】"));

		} catch (Exception e) {
			log.error("绑定角色权限失败！", e);
			throw new BusinessException("绑定权限菜单失败！");
		}

	}

	@Transactional
	@Override
	public void bindUserRole(UserRole bindInfo) throws BusinessException {
		try {
			bindInfo.setCreateTime(new Date());
			if (urDao.insertSelective(bindInfo) < 1) {
				log.error("没有用户角色被绑定！");
				throw new BusinessException("没有用户角色被绑定！！");
			}

		} catch (Exception e) {
			log.error("绑定用户角色失败！", e);
			throw new BusinessException("绑定用户角色失败！");
		}

	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> getAllRole() throws BusinessException {
		List<Role> roles = new ArrayList<>();
		try {
			roles = roleDao.getAllRoles();
		} catch (Exception e) {
			log.error("获取所有角色失败！", e);
			throw new BusinessException("获取所有角色失败！", e);
		}
		return roles;
	}

	@Transactional(readOnly = true)
	@Override
	public MenuVo getMenuById(Integer menuId) throws BusinessException {

		MenuVo menuVo = new MenuVo();
		try {
			Menu menu = menuDao.selectByPrimaryKey(menuId);
			if (menu == null) {
				errorLog("获取菜单失败！");
				throw new BusinessException(getMsg());
			}
			menuVo.setFatherMenuId(menu.getFatherMenuId());
			menuVo.setLinkUrl(menu.getLinkUrl());
			menuVo.setMenuId(menu.getMenuId());
			menuVo.setMenuLevel(menu.getMenuLevel());
			menuVo.setMenuName(menu.getMenuName());
			menuVo.setMenuOrder(menu.getMenuOrder());
			menuVo.setStatus(menu.getStatus());

			PermissionMenu pm = permMenuDao.getPermissionMenuByMenuId(menuId);
			if (pm == null) {
				errorLog("无法获取菜单绑定的权限！");
				throw new BusinessException(getMsg());
			}
			menuVo.setBindPerminssionId(pm.getPermissionId());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new BusinessException("获取菜单失败！ID:" + menuId);
		}
		return menuVo;
	}

	@Transactional(readOnly = true)
	@Override
	public Permission getPermissionById(Integer permissionId) throws BusinessException {
		// TODO Auto-generated method stub
		Permission permission = null;
		try {
			permission = permissionDao.selectByPrimaryKey(permissionId);
		} catch (Exception e) {
			errorLog("获取权限失败！", permissionId + "", e);
			throw new BusinessException(getMsg());
		}
		return permission;
	}

	@Transactional
	@Override
	public void deletePermissionById(Integer permissionId) throws BusinessException {
		try {
			try {
				RolePermission rp = new RolePermission();
				rp.setPermissionId(permissionId);
				rolePermDao.unBindRolePermission(rp);
			} catch (Exception e) {
				errorLog("解除角色权限绑定失败！", permissionId.toString());
				throw new BusinessException(getMsg());
			}
			try {
				permMenuDao.unBindPermissionMenu(permissionId, null);
			} catch (Exception e) {
				errorLog("解除权限菜单绑定失败！", permissionId.toString());
				throw new BusinessException(getMsg());
			}
			int result = -1;
			result = permissionDao.deleteByPrimaryKey(permissionId);
			if (result < 1) {
				throw new BusinessException("无法删除权限!");
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【删除】了【权限" + permissionId + "】"));

		} catch (Exception e) {
			errorLog("删除权限失败！", permissionId.toString(), e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void unBindRolePermission(Integer roleId, Integer permissionId) throws BusinessException {
		try {
			if (roleId != null && permissionId != null) {
				RolePermission rp = new RolePermission();
				rp.setPermissionId(permissionId);
				rp.setRoleId(roleId);
				rolePermDao.unBindRolePermission(rp);
				try {
					List<Permission> childs = permissionDao.getAllChildPermissions(permissionId);
					for (Permission child : childs) {
						unBindRolePermission(roleId, child.getPermissionId());
						// 写入系统日志
						addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(),
								"【解除】【权限" + permissionId + "】和" + "【角色" + child.getPermissionId() + "】的【绑定】"));
					}
				} catch (Exception e) {
					errorLog("解除子权限角色权限绑定时失败！");
					throw new BusinessException(getMsg());
				}
				// 写入系统日志
				addSystemLog(SystemLogUtil.getPermissionLog(getCurrentUserId(),
						"【解除】【权限" + permissionId + "】和" + "【角色" + roleId + "】的【绑定】"));

			} else {
				errorLog("角色id和权限id不能为空！");
				throw new BusinessException(getMsg());
			}
		} catch (Exception e) {
			errorLog("解除角色权限失败", e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public void unBindPermissionMenu(Integer permissionId, Integer menuId) throws BusinessException {
		try {
			if (permissionId != null && menuId != null) {
				if (permMenuDao.unBindPermissionMenu(permissionId, menuId) < 1) {
					throw new BusinessException("解除权限菜单绑定失败！");
				}
			} else {
				throw new BusinessException("权限Id和菜单Id不能为空！");
			}
			// 写入系统日志
			addSystemLog(SystemLogUtil.getPermissionLog(getCurrentUserId(),
					"【解除】【权限" + permissionId + "】和" + "【菜单" + menuId + "】的【绑定】"));
		} catch (Exception e) {
			errorLog("解除权限菜单绑定失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	@Transactional
	@Override
	public void deleteMenu(Integer menuId) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			if (menuDao.deleteByPrimaryKey(menuId) < 1) {
				throw new BusinessException("删除菜单失败！");
			}
			permMenuDao.unBindPermissionMenu(null, menuId);// 解除权限菜单权限绑定
			deleteAllChildMenus(menuId);// 删除所有子菜单
			// 写入系统日志
			addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【删除】" + "【菜单" + menuId + "】"));

		} catch (Exception e) {
			errorLog("删除菜单失败！", e);
			throw new BusinessException(getMsg());
		}

	}

	/**
	 * 递归删除子菜单
	 * 
	 * @param fatherId
	 * @throws BusinessException
	 */
	@Transactional
	private void deleteAllChildMenus(Integer fatherId) throws BusinessException {
		try {
			List<Menu> childs = menuDao.getAllChildMenus(fatherId);
			menuDao.deleteAllChildMenus(fatherId);// 删除所有子菜单
			for (Menu child : childs) {
				permMenuDao.unBindPermissionMenu(null, child.getMenuId());// 解除菜单的权限绑定
				// 写入系统日志
				addSystemLog(SystemLogUtil.getMenuLog(getCurrentUserId(), "【删除】" + "【菜单" + child.getMenuId() + "】"));

				deleteAllChildMenus(child.getMenuId());// 递归删除子菜单
			}
		} catch (Exception e) {
			errorLog("删除相应子菜单失败", fatherId.toString(), e);
			throw new BusinessException(getMsg());
		}
	}

	@Transactional
	@Override
	public Map<String, List<Permission>> getAllPermissionsByRole(Integer roleId) throws BusinessException {
		// TODO Auto-generated method stub
		List<Permission> firstPermissons = new ArrayList<>();
		List<Permission> secondPermissons = new ArrayList<>();
		List<Permission> thirdPermissons = new ArrayList<>();

		Map<String, List<Permission>> permissions = new HashMap<>();
		try {
			List<Permission> allPermission = permissionDao.getAllPermissionsByRoleId(roleId);
			for (Permission perm : allPermission) {
				if (perm.getPermissionLevel() == 1) {
					firstPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 2) {
					secondPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 3) {
					thirdPermissons.add(perm);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("获取权限失败！原因:" + e.getMessage());
		}

		permissions.put("firstPermissions", firstPermissons);
		permissions.put("secondPermissions", secondPermissons);
		permissions.put("thirdPermissions", thirdPermissons);
		return permissions;
	}

	@Transactional
	@Override
	public List<Permission> getUnBindPermissions() {
		List<Permission> permissions = new ArrayList<>();
		try {
			permissions = permissionDao.getUnBindPermissions();
		} catch (Exception e) {
			errorLog("获取权限失败", e);
			throw new BusinessException(getMsg());
		}
		return permissions;
	}

	@Transactional
	@Override
	public List<Permission> getUnBindPermissionsByMenuId(Integer menuId) {
		List<Permission> permissions = new ArrayList<>();
		try {
			permissions = permissionDao.getUnBindPermissionsByMenuIdAndMark(menuId, Permission.PERMISSION_MARK_MENU);
		} catch (Exception e) {
			errorLog("获取权限失败", e);
			throw new BusinessException(getMsg());
		}
		return permissions;
	}

	@Transactional
	@Override
	public Map<String, List<Permission>> getAllBindPermissionsByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		List<Permission> firstPermissons = new ArrayList<>();
		List<Permission> secondPermissons = new ArrayList<>();
		List<Permission> thirdPermissons = new ArrayList<>();

		Map<String, List<Permission>> permissions = new HashMap<>();
		try {
			List<Permission> allPermission = permissionDao.getAllPermissionsByRoleId(roleId);
			for (Permission perm : allPermission) {
				if (perm.getPermissionLevel() == 1) {
					firstPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 2) {
					secondPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 3) {
					thirdPermissons.add(perm);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("获取权限失败！原因:" + e.getMessage());
		}

		permissions.put("firstPermissions", firstPermissons);
		permissions.put("secondPermissions", secondPermissons);
		permissions.put("thirdPermissions", thirdPermissons);

		return permissions;
	}

	@Transactional
	@Override
	public Map<String, List<Permission>> getAllUnBindPermissionsByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		List<Permission> firstPermissons = new ArrayList<>();
		List<Permission> secondPermissons = new ArrayList<>();
		List<Permission> thirdPermissons = new ArrayList<>();

		Map<String, List<Permission>> permissions = new HashMap<>();
		try {
			List<Permission> allPermission = permissionDao.getAllUnBindPermissionsByRoleId(roleId);
			for (Permission perm : allPermission) {
				if (perm.getPermissionLevel() == 1) {
					firstPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 2) {
					secondPermissons.add(perm);
				} else if (perm.getPermissionLevel() == 3) {
					thirdPermissons.add(perm);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			throw new BusinessException("获取权限失败！原因:" + e.getMessage());
		}

		permissions.put("firstPermissions", firstPermissons);
		permissions.put("secondPermissions", secondPermissons);
		permissions.put("thirdPermissions", thirdPermissons);

		return permissions;
	}

	@Transactional
	@Override
	public Integer getUserTypeByUserId(String userId) throws BusinessException {
		Integer result = -1;
		try {
			if (StringUtil.isEmpty(userId)) {
				errorLog("userId为空！");
				throw new BusinessException(getMsg());
			}
			result = userDao.getUserTypeByUserId(userId);
		} catch (Exception e) {
			errorLog("获取用户类型失败！", e);
			throw new BusinessException(getMsg());
		}
		return result;
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
