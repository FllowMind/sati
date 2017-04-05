package com.jyu.sati.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.service.AgencyService;
import com.jyu.sati.business.service.CompanyService;
import com.jyu.sati.business.service.PersonService;
import com.jyu.sati.business.service.UnitInfoService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.User;
import com.jyu.sati.vo.AgencyInfoVo;
import com.jyu.sati.vo.CompanyInfoVo;
import com.jyu.sati.vo.PersonInfoVo;
import com.jyu.sati.vo.UnitInfoVo;

/**
 * 用户信息控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/userInfo")
@Controller
public class UserInfoController extends BaseController {
	@Autowired
	private PersonService personService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UnitInfoService unitService;
	@Autowired
	private AgencyService agencyService;

	/**
	 * 获取个人信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPersonInfo", method = { RequestMethod.GET })
	public AjaxResult getPersonInfo() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有权限
		if (hasNotRole(Role.ROLE_NAME_PERSON)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取个人信息
			PersonInfoVo personInfo = personService.getPersonInfo(getCurrentUserId());
			result = getSuccessRusult(result, "获取个人信息成功！", personInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取个人信息失败！");
		}
		return result;
	}

	/**
	 * 获取个人用户审核信息（管理员审核使用）
	 * 
	 * @param userId
	 *            个人用户id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getPersonAuditInfoByUserId", method = { RequestMethod.POST })
	public AjaxResult getPersonAuditInfoByUserId(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取个人用户审核信息
			PersonInfoVo personInfo = personService.getAuditPersonInfo(user.getUserId());
			result = getSuccessRusult(result, "获取个人用户审核信息成功！", personInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取个人用户审核信息失败！");
		}
		return result;
	}

	/**
	 * 提交个人信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updatePersonInfo", method = { RequestMethod.PUT })
	public AjaxResult updatePersonInfo(@RequestBody PersonInfoVo personInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_PERSON)) {
			return getNoRoleResult(result);
		}
		try {
			personService.updatePersonInfo(personInfo);
			result = getSuccessRusult(result, "提交个人信息成功！ ");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交个人信息失败！");
		}
		return result;
	}

	/**
	 * 获取企业用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompanyInfo", method = { RequestMethod.GET })
	public AjaxResult getCompanyInfo() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_COMPANY)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取企业信息
			CompanyInfoVo companyInfo = companyService.getCompanyInfoByUserId(getCurrentUserId());
			result = getSuccessRusult(result, "获取企业信息成功！", companyInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取企业信息失败！");
		}
		return result;
	}

	/**
	 * 获取企业用户审核信息（管理员审核使用）
	 * 
	 * @param userId
	 *            企业用户id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCompanyAuditInfo", method = { RequestMethod.POST })
	public AjaxResult getCompanyAuditInfo(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取企业信息
			CompanyInfoVo companyInfo = companyService.getAuditCompanyInfoByUserId(user.getUserId());
			result = getSuccessRusult(result, "获取企业信息成功！", companyInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取企业信息失败！");
		}
		return result;
	}

	/**
	 * 提交企业用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCompanyInfo", method = { RequestMethod.PUT })
	public AjaxResult updateCompanyInfo(@RequestBody CompanyInfoVo companyInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_COMPANY)) {
			return getNoRoleResult(result);
		}
		try {
			companyService.updateCompanyInfo(companyInfo);
			result = getSuccessRusult(result, "提交企业信息成功！ ");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交企业信息失败！");
		}
		return result;
	}

	/**
	 * 获取高校用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCollegeInfo", method = { RequestMethod.GET })
	public AjaxResult getCollegeInfo() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_COLLEAGE)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取高校信息
			UnitInfoVo collegeInfo = unitService.getCollegeInfo(getCurrentUserId());
			result = getSuccessRusult(result, "获取高校信息成功！", collegeInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取高校信息失败！");
		}
		return result;
	}

	/**
	 * 获取高校用户审核信息（管理员审核使用）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getCollegeAuditInfo", method = { RequestMethod.POST })
	public AjaxResult getCollegeAuditInfo(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取高校信息
			UnitInfoVo collegeInfo = unitService.getAuditCollegeInfo(user.getUserId());
			result = getSuccessRusult(result, "获取高校用户审核信息成功！", collegeInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取高校用户审核信息失败！");
		}
		return result;
	}

	/**
	 * 提交高校用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateCollegeInfo", method = { RequestMethod.PUT })
	public AjaxResult updateCollegeInfo(@RequestBody UnitInfoVo collegeInfoVo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_COLLEAGE)) {
			return getNoRoleResult(result);
		}
		try {
			unitService.updateUnitInfo(collegeInfoVo);
			result = getSuccessRusult(result, "提交高校信息成功！ ");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交高校信息失败！");
		}
		return result;
	}

	/**
	 * 获取科研单位信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getScientifyInfo", method = { RequestMethod.GET })
	public AjaxResult getScientifyInfo() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_SCIENTIFIC)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取科研单位信息信息
			UnitInfoVo scientifyinfo = unitService.getScientifyInfo(getCurrentUserId());
			result = getSuccessRusult(result, "获取科研单位信息成功！", scientifyinfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取科研单位信息失败！");
		}
		return result;
	}

	/**
	 * 获取科研单位用户审核信息（管理员审核使用）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getScientifyAuditInfo", method = { RequestMethod.POST })
	public AjaxResult getScientifyAuditInfo(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取科研单位信息信息
			UnitInfoVo scientifyinfo = unitService.getScientifyInfo(user.getUserId());
			result = getSuccessRusult(result, "获取科研单位用户审核信息成功！", scientifyinfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取科研单位用户审核信息失败！");
		}
		return result;
	}

	/**
	 * 提交科研单位用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateScientifyInfo", method = { RequestMethod.PUT })
	public AjaxResult updateScientifyInfo(@RequestBody UnitInfoVo scientifyInfoVo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_SCIENTIFIC)) {
			return getNoRoleResult(result);
		}
		try {
			unitService.updateUnitInfo(scientifyInfoVo);
			result = getSuccessRusult(result, "提交科研单位信息成功！ ");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交科研单位信息失败！");
		}
		return result;
	}

	/**
	 * 获取中介机构用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAgencyInfo", method = { RequestMethod.GET })
	public AjaxResult getAgencyInfo() {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_AGENCY)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取个人信息
			AgencyInfoVo agencyInfo = agencyService.getAgencyInfoByUserId(getCurrentUserId());
			result = getSuccessRusult(result, "获取中介机构信息成功！", agencyInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取中介机构信息失败！");
		}
		return result;
	}

	/**
	 * 获取中介机构用户审核信息（管理员审核使用）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAgencyAuditInfo", method = { RequestMethod.POST })
	public AjaxResult getAgencyAuditInfo(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取个人信息
			AgencyInfoVo agencyInfo = agencyService.getAuditAgencyInfoByUserId(user.getUserId());
			result = getSuccessRusult(result, "获取中介机构用户审核信息成功！", agencyInfo);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取中介机构用户审核信息失败！");
		}
		return result;
	}

	/**
	 * 提交中介机构用户信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateAgencyInfo", method = { RequestMethod.PUT })
	public AjaxResult updateAgencyInfo(@RequestBody AgencyInfoVo agencyInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否登录
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (!hasRole(Role.ROLE_NAME_AGENCY)) {
			return getNoRoleResult(result);
		}
		try {
			agencyService.updateAgencyInfo(agencyInfo);
			result = getSuccessRusult(result, "提交我的中介机构信息成功！ ");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交我的中介机构信息失败！");
		}
		return result;
	}

}
