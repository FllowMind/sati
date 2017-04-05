package com.jyu.sati.business.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.service.AgencyService;
import com.jyu.sati.business.service.AuditInfoService;
import com.jyu.sati.business.service.CompanyService;
import com.jyu.sati.business.service.PermissionService;
import com.jyu.sati.business.service.PersonService;
import com.jyu.sati.business.service.UnitInfoService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.User;
import com.jyu.sati.vo.AuditInfoVo;

/**
 * 用户审核控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/audit")
@Controller
public class AuditUserInfoController extends BaseController {

	@Autowired
	private AuditInfoService auditService;
	@Autowired
	private CompanyService companyService;
	@Autowired 
	private UnitInfoService unitService;
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private PersonService personService;
	@Autowired
	private PermissionService permService;

	/**
	 * 获取审核信息的某个分页的数据
	 * 
	 * @param auditStatus
	 *            审核状态 (五种状态，详情查看AuditInfo类)
	 * @param userType
	 *            被审核用户类型
	 * @param startDate
	 *            最早提交时间
	 * @param endDate
	 *            最迟提交时间
	 * 
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            一页数据条数
	 * 
	 * @return resultList 查询到的审核信息集合
	 * @return pageNo // 当前页码
	 * @return totalNo // 总条数
	 * @return pageSize // 每页条数
	 * @return totalPage // 总页数
	 */
	@ResponseBody
	@RequestMapping(value = "/getAuditOutlinePage", method = { RequestMethod.POST })
	private AjaxResult getAuditOutlinePage(@RequestBody AuditInfoVo condition) {
		AjaxResult result = getAjaxResult();
		// 判断是否未登录
		if (checkIsNotLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		// 如果当前审核状态为正在审核，设置审核者
		if (condition.getAuditStatus() == AuditInfo.AUDIT_STATUS_AUDITING) {
			condition.setAuditorId(getCurrentUserId());
		}
		try {
			List<AuditInfo> auditInfos = auditService.getAuditInfoPageByCondition(condition);
			condition.setResultList(auditInfos);
			result.setResult(condition);
			result.setSuccess(true);
			result.setMsg("获取审核信息成功！");
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg("获取审核信息失败！");
		}
		return result;
	}

	/**
	 * 获取指定用户类型的未审核用户信息总数
	 * 
	 * @param userType
	 *            用户类型
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUnAuditUserInfosCountByUserType", method = { RequestMethod.POST })
	public AjaxResult getUnAuditUserInfosCountByUserType(@RequestBody User user) {
		AjaxResult result = getAjaxResult();
		// 判断是否未登录
		if (checkIsNotLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取数据
			Integer res = auditService.getUnAuditUserInfosCountByUserType(user.getUserType());
			result = getSuccessRusult(result, res);
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}

		return result;
	}

	/**
	 * 获取当前用户信息审核状态
	 * 
	 * @return 当前用户信息审核状态
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserInfoAuditStatus", method = { RequestMethod.GET })
	public AjaxResult getUserInfoAuditStatus() {
		AjaxResult result = getAjaxResult();
		// 判断是否未登录
		if (checkIsNotLogin()) {
			return getUnLoginResult(result);
		}
		try {
			// 获取数据
			Integer res = auditService.getUserInfoAuditStatusByUserId(getCurrentUserId());
			result = getSuccessRusult(result, res);
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}

		return result;
	}

	/**
	 * 管理员对信息的审核结果的提交
	 * 
	 * @param auditInfo
	 * @return 当前用户信息审核状态
	 */
	@ResponseBody
	@RequestMapping(value = "/auditInfos", method = { RequestMethod.POST })
	public AjaxResult auditInfos(@RequestBody AuditInfo auditInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否未登录
		if (checkIsNotLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			// 获取数据
			auditService.auditInfo(auditInfo, getCurrentUserId());
			result = getSuccessRusult(result, "提交审核结果成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}

		return result;
	}

	/**
	 * 获取要审核的详细的用户信息
	 * 
	 * @param userId
	 *            要审核的用户id
	 * @return 当前用户信息审核状态
	 */
	@ResponseBody
	@RequestMapping(value = "/getAuditUserInfoDetails", method = { RequestMethod.POST })
	public AjaxResult getAuditUserInfoDetails(@RequestBody AuditInfo auditInfo) {
		AjaxResult result = getAjaxResult();
		// 判断是否未登录
		if (checkIsNotLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		} else {
			try {
				// 更新对应的用户信息审核状态为审核中
				auditService.updateUserInfoAuditStatus(auditInfo.getUserId(), AuditInfo.AUDIT_STATUS_AUDITING,
						getCurrentUserId());
			} catch (Exception e) {
				logger.error("更新审核状态失败！", e);
				return getFailedRusult(result, "获取数据出现异常");
			}
		}
		try {
			String userId = auditInfo.getUserId();
			Integer userType = permService.getUserTypeByUserId(userId);
			switch (userType) {
			case User.USER_TYPE_AGENCY:
				// 中介机构的信息
				result = getSuccessRusult(result, agencyService.getAgencyInfoByUserId(userId));
				break;
			case User.USER_TYPE_COMPANY:
				// 企业用户的信息
				result = getSuccessRusult(result, companyService.getCompanyInfoByUserId(userId));
				break;
			case User.USER_TYPE_COLLEGE:
				// 高校用户的信息
				result = getSuccessRusult(result, unitService.getCollegeInfo(userId));
				break;
			case User.USER_TYPE_SCIENTIFIC:
				// 科研单位的信息
				result = getSuccessRusult(result, unitService.getScientifyInfo(userId));
				break;
			case User.USER_TYPE_PERSON:
				// 个人用户的信息
				result = getSuccessRusult(result, personService.getPersonInfo(userId));
				break;
			default:
				result = getFailedRusult(result, "没有该用户类型的数据！");
				break;
			}
		} catch (Exception e) {
			result = getFailedRusult(result);
		}

		return result;
	}

}
