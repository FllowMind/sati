package com.jyu.sati.business.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.service.TechSupplyService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.entity.Role;
import com.jyu.sati.entity.TechSupplyInfo;
import com.jyu.sati.vo.TechSupplyInfoVo;
import com.jyu.sati.vo.TechnologyPageVo;

/**
 * 技术供给服务
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/supply")
@Controller
public class TechSupplyController extends BaseController {
	@Autowired
	private TechSupplyService supplyService;

	/**
	 * 创建新的技术供给信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createNewTechSupplyInfo", method = { RequestMethod.GET })
	public AjaxResult createNewTechSupplyInfo() {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			result = getSuccessRusult(result, "创建技术供给成功！", supplyService.createTechSupplyInfo());
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 创建新的技术供给信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTechPageByCondition", method = { RequestMethod.POST })
	public AjaxResult getTechPageByCondition(@RequestBody TechnologyPageVo condition) {
		AjaxResult result = getAjaxResult();
		try {
			condition.setResultList(supplyService.getTechSupPageByCondition(condition));
			result = getSuccessRusult(result, condition);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取数据失败！");
		}
		return result;
	}

	/**
	 * 提交技术供给信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitTechSupplyInfo", method = { RequestMethod.PUT })
	public AjaxResult submitTechSupplyInfo(@RequestBody TechSupplyInfoVo supplyInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			supplyService.submitTechSupplyInfo(supplyInfo);
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
	@RequestMapping(value = "/saveTechSupplyInfo", method = { RequestMethod.PUT })
	public AjaxResult saveTechSupplyInfo(@RequestBody TechSupplyInfoVo supplyInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			supplyService.saveTechSupplyInfo(supplyInfo);
			result = getSuccessRusult(result, "保存成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "保存失败！");
		}
		return result;
	}

	/**
	 * 删除指定产品成果信息
	 * 
	 * @param tbiId
	 *            技术供给信息id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeTechSupply", method = { RequestMethod.DELETE })
	public AjaxResult removeTechSupply(@RequestBody TechSupplyInfo techInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			supplyService.removeTechSupInfo(techInfo.getTsiId());
			result = getSuccessRusult(result, "删除成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "删除失败！");
		}
		return result;
	}

	/**
	 * 更新技术供给信息交易状态
	 * 
	 * @param tbiId
	 *            技术供给id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTechSupplyStatus", method = { RequestMethod.PUT })
	public AjaxResult updateTechSupplyStatus(@RequestBody TechSupplyInfo techInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			supplyService.updateTechSupStatus(techInfo.getTsiId());
			result = getSuccessRusult(result, "更新技术供给信息交易状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更新技术供给信息交易状态失败！");
		}
		return result;
	}

	/**
	 * 更新技术供给信息可见状态
	 * 
	 * @param tbiId
	 *            技术供给id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTechSupplyLimitStatus", method = { RequestMethod.PUT })
	public AjaxResult updateTechSupplyLimitStatus(@RequestBody TechSupplyInfo techInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			supplyService.updateTechSupLimitStatus(techInfo.getTsiId());
			result = getSuccessRusult(result, "更新技术供给信息可见状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更新技术供给信息可见状态失败！");
		}
		return result;
	}

	/**
	 * 浏览指定的技术供给信息
	 * 
	 * @param tsiId
	 *            产品id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTechSupplyInfoById", method = { RequestMethod.POST })
	public AjaxResult getTechSupplyInfoById(@RequestBody TechSupplyInfo techSup, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取技术供给信息成功！", supplyService.getTechSupById(techSup.getTsiId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取技术供给信息失败！");
		}
		return result;
	}

	/**
	 * 浏览指定的技术供给信息(管理员)
	 * 
	 * @param tsiId
	 *            产品id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAuditTechSupplyInfoById", method = { RequestMethod.POST })
	public AjaxResult getAuditTechSupplyInfoById(@RequestBody TechSupplyInfo techSup) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			result = getSuccessRusult(result, "获取技术供给信息成功！", supplyService.getAuditTechSupById(techSup.getTsiId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取技术供给信息失败！");
		}
		return result;
	}

	/**
	 * 根据查询条件获取技术供给的数目
	 * 
	 * @param condition
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getProduceCountByCondition", method = { RequestMethod.POST })
	public AjaxResult getTechSupplyCountByCondition(@RequestBody TechnologyPageVo condition) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取数据成功！", supplyService.getTechSupCountByCondition(condition));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取数据失败！");
		}
		return result;
	}
}
