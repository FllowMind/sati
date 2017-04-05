package com.jyu.sati.business.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jyu.sati.business.service.InfoService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.common.util.FileUtil;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.entity.Info;
import com.jyu.sati.entity.Role;
import com.jyu.sati.vo.InfoPageVo;
import com.jyu.sati.vo.UploadFileVo;

/**
 * 政策法规和通知公告控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/info")
@Controller
public class InfoController extends BaseController {

	@Autowired
	private InfoService infoService;

	/**
	 * 创建新的产品成果
	 * 
	 * @param infoType
	 *            创建的信息类型（政策或通知）详情看Info实体类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/createNewInfo", method = { RequestMethod.POST })
	public AjaxResult createNewInfo(@RequestBody Info info) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			result = getSuccessRusult(result, "创建成功！", infoService.createNewInfo(info.getInfoType()));
		} catch (Exception e) {
			result = getFailedRusult(result, "创建失败！");
		}
		return result;
	}

	/**
	 * 发布政策或通知
	 * 
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishInfo", method = { RequestMethod.PUT })
	public AjaxResult publishInfo(@RequestBody Info info) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			infoService.publishInfo(info);
			result = getSuccessRusult(result, "发布成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "发布失败！");
		}
		return result;
	}

	/**
	 * 保存 政策或通知
	 * 
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveInfo", method = { RequestMethod.PUT })
	public AjaxResult saveInfo(@RequestBody Info info) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			infoService.saveInfo(info);
			result = getSuccessRusult(result, "保存成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "保存失败！");
		}
		return result;
	}

	/**
	 * 获取 政策或通知的详细信息
	 * 
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfoById", method = { RequestMethod.POST })
	public AjaxResult getInfoById(@RequestBody Info info) {
		AjaxResult result = getAjaxResult();
		try {
			result = getSuccessRusult(result, "获取成功！", infoService.getInfoByInfoId(info.getInfoId()));
		} catch (Exception e) {
			result = getFailedRusult(result, "获取失败！");
		}
		return result;
	}

	/**
	 * 删除 政策或通知
	 * 
	 * @param infoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/removeInfoById", method = { RequestMethod.DELETE })
	public AjaxResult removeInfoById(@RequestBody Info info) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			infoService.removeInfoById(info.getInfoId());
			result = getSuccessRusult(result, "删除成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "删除失败！");
		}
		return result;
	}

	/**
	 * 更改政策或通知发布状态
	 * 
	 * @param infoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateInfoStatusById", method = { RequestMethod.PUT })
	public AjaxResult updateInfoStatusById(@RequestBody Info info) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		try {
			infoService.updateInfoStatusById(info.getInfoId());
			result = getSuccessRusult(result, "更改发布状态成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "更改发布状态失败！");
		}
		return result;
	}

	/**
	 * 获取一页政策或通知发布数据
	 * 
	 * @param infoId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getInfoPageByCondition", method = { RequestMethod.POST })
	public AjaxResult getInfoPageByCondition(@RequestBody InfoPageVo condition) {
		AjaxResult result = getAjaxResult();
		try {
			condition.setResultList(infoService.getInfoPageByCondition(condition));
			result = getSuccessRusult(result, "获取数据失败！", condition);
		} catch (Exception e) {
			result = getFailedRusult(result, "获取数据失败！");
		}
		return result;
	}

	/**
	 * 上传信息
	 * 
	 * @param file
	 * @param bindId
	 *            技术供给信息id
	 * @param desc
	 *            附件描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadWordFile", method = { RequestMethod.POST })
	public AjaxResult uploadWordFile(MultipartFile file, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		// 判断是否管理员权限
		if (hasNotRole(Role.ROLE_NAME_PLATFORM) && hasNotRole(Role.ROLE_NAME_SUPER_ADMIN)) {
			return getNoRoleResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是视频类型
		if (FileUtil.isNotWordFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是doc或docx类型的文件！");
		}
		try {
			UploadFileVo fileVo = new UploadFileVo();
			String desc = request.getParameter("desc");// 获取描述
			Integer bindId = Integer.valueOf(request.getParameter("bindId"));// 获取绑定的技术供给id

			fileVo.setFile(file);
			fileVo.setDesc(desc);
			fileVo.setBindId(bindId);
			// 保存文件
			infoService.uploadFile(fileVo);
			result = getSuccessRusult(result, "上传文件成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "上传文件失败！");
		}
		return result;
	}

}
