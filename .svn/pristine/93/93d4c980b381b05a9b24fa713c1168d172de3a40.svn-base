package com.jyu.sati.business.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.service.TechRequireService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.TechRequireInfo;
import com.jyu.sati.entity.TechSupplyInfo;
import com.jyu.sati.vo.TechRequireInfoVo;
import com.jyu.sati.vo.TechSupplyInfoVo;
import com.jyu.sati.vo.TechnologyPageVo;

/**
 * 技术需求控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/requirement")
@Controller
public class TechRequireController extends BaseController {

	@Autowired
	private TechRequireService requireService;

	/**
	 * 创建新的技术供给信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createNewTechRequireInfo", method = { RequestMethod.GET })
	public AjaxResult createNewTechRequireInfo() {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			result = getSuccessRusult(result, "创建技术需求成功！", requireService.createNewTechReqInfo());
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 获取一页技术需求信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTechPageByCondition", method = { RequestMethod.POST })
	public AjaxResult getTechPageByCondition(@RequestBody TechnologyPageVo condition) {
		AjaxResult result = getAjaxResult();
		try {
			condition.setResultList(requireService.getTechReqPageByCondition(condition));
			result = getSuccessRusult(result, condition);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取数据失败！");
		}
		return result;
	}

	/**
	 * 提交技术需求信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitTechRequireInfo", method = { RequestMethod.PUT })
	public AjaxResult submitTechRequireInfo(@RequestBody TechRequireInfoVo requireVo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			requireService.submitTechSupplyInfo(requireVo);
			result = getSuccessRusult(result, "提交成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交失败！");
		}
		return result;
	}

	/**
	 * 保存技术供给信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveTechRequireInfo", method = { RequestMethod.PUT })
	public AjaxResult saveTechRequireInfo(@RequestBody TechRequireInfoVo requireVo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			requireService.saveTechReqInfo(requireVo);
			result = getSuccessRusult(result, "保存成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "保存失败！");
		}
		return result;
	}

	/**
	 * 删除技术需求信息
	 * 
	 * @param triId
	 *            技术需求信息id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeTechRequireInfo", method = { RequestMethod.DELETE })
	public AjaxResult removeTechRequireInfo(@RequestBody TechRequireInfo techInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			requireService.removeTechReqInfo(techInfo.getTriId());
			result = getSuccessRusult(result, "删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			result = getFailedRusult(result, "删除失败！");
		}
		return result;
	}

	/**
	 * 更新技术需求信息解决状态
	 * 
	 * @param triId
	 *            技术需求id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTechRequireStatus", method = { RequestMethod.PUT })
	public AjaxResult updateTechRequireStatus(@RequestBody TechRequireInfo techInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			requireService.updateTechReqStatus(techInfo.getTriId());
			result = getSuccessRusult(result, "更新技术需求信息解决状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更新技术需求信息解决状态失败！");
		}
		return result;
	}

	/**
	 * 更新技术需求信息可见状态
	 * 
	 * @param triId
	 *            技术需求id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTechRequireLimitStatus", method = { RequestMethod.PUT })
	public AjaxResult updateTechRequireLimitStatus(@RequestBody TechRequireInfo techInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			requireService.updateTechReqLimitStatus(techInfo.getTriId());
			result = getSuccessRusult(result, "更新技术需求信息可见状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更新技术需求信息可见状态失败！");
		}
		return result;
	}

	/**
	 * 浏览指定的技术需求信息
	 * 
	 * @param triId
	 *            技术需求信息id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTechRequireInfoById", method = { RequestMethod.POST })
	public AjaxResult getTechRequireInfoById(@RequestBody TechRequireInfo techRep, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取技术需求信息成功！", requireService.getTechReqInfoById(techRep.getTriId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取技术需求信息失败！");
		}
		return result;
	}

	/**
	 * 浏览指定的技术需求信息(管理员)
	 * 
	 * @param triId
	 *            技术需求信息id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAuditTechRequireInfoById", method = { RequestMethod.POST })
	public AjaxResult getAuditTechRequireInfoById(@RequestBody TechRequireInfo techReq) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			result = getSuccessRusult(result, "获取技术需求信息成功！",
					requireService.getAuditTechReqInfoById(techReq.getTriId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取技术需求信息失败！");
		}
		return result;
	}

	/**
	 * 根据查询条件获取技术需求的数目
	 * 
	 * @param condition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTechRequireCountByCondition", method = { RequestMethod.POST })
	public AjaxResult getTechRequireCountByCondition(@RequestBody TechnologyPageVo condition) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取数据成功！", requireService.getTechReqCountByCondition(condition));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取数据失败！");
		}
		return result;
	}
}
