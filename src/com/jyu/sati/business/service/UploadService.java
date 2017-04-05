package com.jyu.sati.business.service;

import org.springframework.web.multipart.MultipartFile;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.vo.UploadFileVo;

public interface UploadService {

	/**
	 * 上传照片
	 * 
	 * @param file
	 * @param userId
	 * @param uploadType
	 *            为1时上传个人照片，为2时上传身份证照片
	 * @throws BusinessException
	 */
	void uploadPersonImage(MultipartFile file, String userId, int uploadType) throws BusinessException;

	/**
	 * 上传附件
	 * 
	 * @param file
	 * @param userId
	 * @throws BusinessException
	 */
	void uploadEnclosure(MultipartFile file, String userId) throws BusinessException;

	/**
	 * 上传单位机构代码图片或统一社会信用代码图片
	 * 
	 * @param file
	 * @param userId
	 * @throws BusinessException
	 */
	void uploadUnitCodeImage(MultipartFile file, String userId) throws BusinessException;

	/**
	 * 上传首页宣传图片
	 * 
	 * @param file
	 * @throws BusinessException
	 */
	void uploadHomePageImage(MultipartFile file) throws BusinessException;

	/**
	 * 上传产品成果图片
	 * 
	 * @param file
	 * @throws BusinessException
	 */
	void uploadProduceImage(UploadFileVo fileVo) throws BusinessException;

	/**
	 * 上传技术供给图片附件
	 * 
	 * @param file
	 * @throws BusinessException
	 */
	void uploadTechSupplyImageEnclosure(UploadFileVo fileVo) throws BusinessException;

	/**
	 * 上传技术供给文本附件
	 * 
	 * @param file
	 * @throws BusinessException
	 */
	void uploadTechSupplyTextEnclosure(UploadFileVo fileVo) throws BusinessException;

	/**
	 * 上传技术供给视频附件
	 * 
	 * @param file
	 * @throws BusinessException
	 */
	void uploadTechSupplyVideoEnclosure(UploadFileVo fileVo) throws BusinessException;

}
