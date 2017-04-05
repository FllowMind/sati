package com.jyu.sati.business.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jyu.sati.business.service.UploadService;
import com.jyu.sati.common.util.AjaxResult;
import com.jyu.sati.common.util.FileUtil;
import com.jyu.sati.common.util.StringUtil;
import com.jyu.sati.vo.UploadFileVo;

/**
 * 文件上传控制器
 * 
 * @author 淋雨又调皮
 *
 */
@RequestMapping("/upload")
@Controller
public class UploadController extends BaseController {

	@Autowired
	private UploadService uploadService;

	/**
	 * 上传身份证图片
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadIDCardImage", method = { RequestMethod.POST })
	public AjaxResult uploadIDCardImage(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getFailedRusult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是图片类型
		if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是图片类型的文件！");
		}
		try {
			if (StringUtil.isEmpty(file.getOriginalFilename())) {
				result.setSuccess(false);
				result.setMsg("无效的附件文件！");
				return result;
			}
			// 判断是否是图片类型
			if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
				result.setMsg("文件名为空或不是图片类型的文件！");
				result.setSuccess(false);
				return result;
			}
			uploadService.uploadPersonImage(file, getCurrentUserId(), 2);
			result.setSuccess(true);
			result.setMsg("文件上传成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传个人照片
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadPersonImage", method = { RequestMethod.POST })
	public AjaxResult uploadPersonImage(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getFailedRusult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是图片类型
		if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是图片类型的文件！");
		}
		try {
			uploadService.uploadPersonImage(file, getCurrentUserId(), 1);
			result.setSuccess(true);
			result.setMsg("个人照片上传成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传个人附件
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadPersonEnclosure", method = { RequestMethod.POST })
	public AjaxResult uploadPersonEnclosure(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		try {
			uploadService.uploadEnclosure(file, getCurrentUserId());
			result.setSuccess(true);
			result.setMsg("附件上传成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result = getFailedRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传单位机构代码图片
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadUnitCodeImage", method = { RequestMethod.POST })
	public AjaxResult uploadUnitCodeImage(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是图片类型
		if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是图片类型的文件！");
		}
		try {
			// 保存文件
			uploadService.uploadUnitCodeImage(file, getCurrentUserId());
			getSuccessRusult(result, "上传文件成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getSuccessRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传主页宣传图片
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadHomePageImage", method = { RequestMethod.POST })
	public AjaxResult uploadHomePageImage(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是图片类型
		if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是图片类型的文件！");
		}
		try {
			// 保存文件
			uploadService.uploadHomePageImage(file);
			getSuccessRusult(result, "上传文件成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getSuccessRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传产品成果图片
	 * 
	 * @param file
	 * @param bindId
	 *            产品成果id
	 * @param desc
	 *            附件描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadProduceImage", method = { RequestMethod.POST })
	public AjaxResult uploadProduceImage(MultipartFile file, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是图片类型
		if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是图片类型的文件！");
		}
		try {
			UploadFileVo fileVo = new UploadFileVo();
			String desc = request.getParameter("desc");
			Integer bindId = Integer.valueOf(request.getParameter("bindId"));

			fileVo.setFile(file);
			fileVo.setDesc(desc);
			fileVo.setBindId(bindId);
			// 保存文件
			uploadService.uploadProduceImage(fileVo);
			getSuccessRusult(result, "上传图片成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			getSuccessRusult(result, e.getMessage());
		}
		return result;
	}

	/**
	 * 上传技术供给信息图片附件
	 * 
	 * @param file
	 * @param bindId
	 *            技术供给信息id
	 * @param desc
	 *            附件描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadTechSupplyImageEnclosure", method = { RequestMethod.POST })
	public AjaxResult uploadTechSupplyImageEnclosure(MultipartFile file, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是图片类型
		if (FileUtil.isNotImageFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是图片类型的文件！");
		}
		try {
			UploadFileVo fileVo = new UploadFileVo();
			String desc = request.getParameter("desc");// 获取附件描述
			Integer bindId = Integer.valueOf(request.getParameter("bindId"));// 获取绑定的技术供给id

			fileVo.setFile(file);
			fileVo.setDesc(desc);
			fileVo.setBindId(bindId);
			// 保存文件
			uploadService.uploadTechSupplyImageEnclosure(fileVo);
			result = getSuccessRusult(result, "上传图片附件成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "上传图片附件失败！");
		}
		return result;
	}

	/**
	 * 上传技术供给信息文本附件
	 * 
	 * @param file
	 * @param bindId
	 *            技术供给信息id
	 * @param desc
	 *            附件描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadTechSupplyTextEnclosure", method = { RequestMethod.POST })
	public AjaxResult uploadTechSupplyTextEnclosure(MultipartFile file, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是文本类型
		if (FileUtil.isNotTextType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是文本类型的文件！");
		}
		try {
			UploadFileVo fileVo = new UploadFileVo();
			String desc = request.getParameter("desc");// 获取附件描述
			Integer bindId = Integer.valueOf(request.getParameter("bindId"));// 获取绑定的技术供给id

			fileVo.setFile(file);
			fileVo.setDesc(desc);
			fileVo.setBindId(bindId);
			// 保存文件
			uploadService.uploadTechSupplyTextEnclosure(fileVo);
			result = getSuccessRusult(result, "上传文本附件成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "上传文本附件失败！");
		}
		return result;
	}

	/**
	 * 上传技术供给信息视频附件
	 * 
	 * @param file
	 * @param bindId
	 *            技术供给信息id
	 * @param desc
	 *            附件描述
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadTechSupplyVideoEnclosure", method = { RequestMethod.POST })
	public AjaxResult uploadTechSupplyVideoEnclosure(MultipartFile file, HttpServletRequest request) {
		AjaxResult result = getAjaxResult();
		if (!checkIsLogin()) {
			return getUnLoginResult(result);
		}
		if (file == null) {
			return getFailedRusult(result, "上传失败！不能传入空文件！");
		}
		if (StringUtil.isEmpty(file.getOriginalFilename())) {
			return getFailedRusult(result, "无效的文件！");
		}
		// 判断是否是视频类型
		if (FileUtil.isNotVideoFileType(file.getOriginalFilename())) {
			return getFailedRusult(result, "文件名为空或不是视频类型的文件！");
		}
		try {
			UploadFileVo fileVo = new UploadFileVo();
			String desc = request.getParameter("desc");// 获取附件描述
			Integer bindId = Integer.valueOf(request.getParameter("bindId"));// 获取绑定的技术供给id

			fileVo.setFile(file);
			fileVo.setDesc(desc);
			fileVo.setBindId(bindId);
			// 保存文件
			uploadService.uploadTechSupplyVideoEnclosure(fileVo);
			result = getSuccessRusult(result, "上传视频附件成功！");
		} catch (Exception e) {
			result = getFailedRusult(result, "上传视频附件失败！");
		}
		return result;
	}

}
