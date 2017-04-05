package com.jyu.sati.business.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyu.sati.business.service.ProduceService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.entity.AuditInfo;
import com.jyu.sati.entity.Produce;
import com.jyu.sati.entity.Role;
import com.jyu.sati.vo.ProduceInfoVo;
import com.jyu.sati.vo.ProducePageVo;

/**
 * 产品成果控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/produce")
@Controller
public class ProduceController extends BaseController {

	@Autowired
	private ProduceService produceService;

	/**
	 * 创建新的产品成果
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createNewProduce", method = { RequestMethod.GET })
	public AjaxResult createNewProduce() {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			result = getSuccessRusult(result, "创建产品成果成功！", produceService.createNewProduce());
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 提交产品成果信息审核
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/submitNewProduce", method = { RequestMethod.PUT })
	public AjaxResult submitNewProduce(@RequestBody ProduceInfoVo produceInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			produceService.submitProduce(produceInfo);
			result = getSuccessRusult(result, "提交成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "提交失败！");
		}
		return result;
	}

	/**
	 * 保存产品成果信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveProduce", method = { RequestMethod.PUT })
	public AjaxResult saveProduce(@RequestBody ProduceInfoVo produceInfo) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			produceService.saveProduce(produceInfo);
			result = getSuccessRusult(result, "保存成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "保存失败！");
		}
		return result;
	}

	/**
	 * 删除指定产品成果信息
	 * 
	 * @param produceId
	 *            产品id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeProduce", method = { RequestMethod.DELETE })
	public AjaxResult removeProduce(@RequestBody Produce produce) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		try {
			produceService.removeProduce(produce.getProduceId());
			result = getSuccessRusult(result, "删除成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "删除失败！");
		}
		return result;
	}

	/**
	 * 更新指定产品成果信息
	 * 
	 * @param produceId
	 *            产品成果id
	 * @param produceStatus
	 *            产品成果状 态 ：1为可用，2为禁用
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateProduceStatus", method = { RequestMethod.PUT })
	public AjaxResult updateProduceStatus(@RequestBody Produce produce) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否是管理员身份
		if (hasNotRole(Role.ROLE_NAME_SUPER_ADMIN) && hasNotRole(Role.ROLE_NAME_PLATFORM)) {
			return getNoRoleResult(result);
		}
		try {
			produceService.updateProduceStatus(produce.getProduceId(), produce.getProduceStatus());
			result = getSuccessRusult(result, "更新产品成果状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更新产品成果状态失败！");
		}
		return result;
	}

	/**
	 * 更新推荐状态
	 * 
	 * @param produceId
	 *            产品id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateIsRecommenedStatus", method = { RequestMethod.PUT })
	public AjaxResult updateIsRecommenedStatus(@RequestBody Produce produce) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否有权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			produceService.updateIsRecommenedStatus(produce.getProduceId());
			result = getSuccessRusult(result, "更新推荐状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更新推荐状态失败！");
		}
		return result;
	}

	/**
	 * 根据条件获取一页的产品成果数据
	 * 
	 * @param produceKey
	 *            被搜索到的关键字
	 * @param produceTypeId
	 *            产品类别
	 * @param startDate
	 *            最早发布时间
	 * @param endDate
	 *            最迟发布时间
	 * @param publisherId
	 *            发布人id
	 * @param produceStatus
	 *            产品状态
	 * @param isRecommend
	 *            是否推荐到首页
	 * @param auditorId
	 *            审核人id
	 * @param pageViewDescOrAsc
	 *            浏览次数倒序或顺序：1顺序，2倒序
	 * @param publishTimeDescOrAsc
	 *            发布时间的顺倒序或顺序：1顺序，2倒序
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            一页数据条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getProducesPageByCondition", method = { RequestMethod.POST })
	public AjaxResult getProducesPageByCondition(@RequestBody ProducePageVo condition) {
		AjaxResult result = getAjaxResult();
		// 判断是否是管理员
		if (hasRole(Role.ROLE_NAME_PLATFORM) && hasRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			// 是管理员且审核状态为正在审核，则获取对应管理员正在审核的数据
			if (condition.getAuditStatus() == AuditInfo.AUDIT_STATUS_AUDITING) {
				// 设置查询审核人为当前管理员的数据
				condition.setAuditorId(getCurrentUserId());
			}
		} else {
			condition.setPublisherId(getCurrentUserId());
		}
		try {
			condition.setResultList(produceService.getProducePageByCondition(condition));
			result = getSuccessRusult(result, "获取产品成果数据成功！", condition);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取产品成果数据失败！");
		}
		return result;
	}

	/**
	 * 浏览指定的产品成果信息
	 * 
	 * @param produceId
	 *            产品id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getProduceInfoById", method = { RequestMethod.POST })
	public AjaxResult getProduceInfoById(@RequestBody Produce produce) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取产品成果成功！", produceService.getProduceInfoById(produce.getProduceId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取产品成果失败！");
		}
		return result;
	}

	/**
	 * 浏览指定的产品成果信息（管理员）
	 * 
	 * @param produceId
	 *            产品id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getAuditProduceInfoById", method = { RequestMethod.POST })
	public AjaxResult getAuditProduceInfoById(@RequestBody Produce produce) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取产品成果成功！", produceService.getAuditProduceById(produce.getProduceId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取产品成果失败！");
		}
		return result;
	}

	/**
	 * 根据条件获取产品成果总数
	 * 
	 * @param produceKey
	 *            被搜索到的关键字
	 * @param produceTypeId
	 *            产品类别
	 * @param startDate
	 *            最早发布时间
	 * @param endDate
	 *            最迟发布时间
	 * @param publisherId
	 *            发布人id
	 * @param produceStatus
	 *            产品状态
	 * @param isRecommend
	 *            是否推荐到首页
	 * @param pageViewDescOrAsc
	 *            浏览次数倒序或顺序：1顺序，2倒序
	 * @param publishTimeDescOrAsc
	 *            发布时间的顺倒序或顺序：1顺序，2倒序
	 * @param pageNo
	 *            当前页
	 * @param pageSize
	 *            一页数据条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getProduceCountByCondition", method = { RequestMethod.POST })
	public AjaxResult getProduceCountByCondition(@RequestBody ProducePageVo condition) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取数据成功！", produceService.getProdcueCountByCondition(condition));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取数据失败！");
		}
		return result;
	}

}
