package com.jyu.sati.business.service;

import java.util.List;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Info;
import com.jyu.sati.vo.InfoPageVo;
import com.jyu.sati.vo.UploadFileVo;

public interface InfoService {

	/**
	 * 根据信息类型创建政策法规或通知公告
	 * 
	 * @param infoType
	 *            信息类型，详情看Info实体类
	 * @return
	 * @throws BusinessException
	 */
	Info createNewInfo(Integer infoType) throws BusinessException;

	/**
	 * 根据查询条件获取一页数据
	 * 
	 * @param condition
	 * @return
	 * @throws BusinessException
	 */
	List<Info> getInfoPageByCondition(InfoPageVo condition) throws BusinessException;

	/**
	 * 根据id获取信息
	 * 
	 * @param infoId
	 * @return
	 * @throws BusinessException
	 */
	Info getInfoByInfoId(Integer infoId) throws BusinessException;

	/**
	 * 更新信息状态（禁用或正常使用）
	 * 
	 * @param infoId
	 * @throws BusinessException
	 */
	void updateInfoStatusById(Integer infoId) throws BusinessException;

	/**
	 * 发布信息
	 * 
	 * @param info
	 * @throws BusinessException
	 */
	void publishInfo(Info info) throws BusinessException;

	/**
	 * 保存信息不发布
	 * 
	 * @param info
	 * @throws BusinessException
	 */
	void saveInfo(Info info) throws BusinessException;

	/**
	 * 上传信息文件
	 * 
	 * @param fileVo
	 * @throws BusinessException
	 */
	void uploadFile(UploadFileVo fileVo) throws BusinessException;

	/**
	 * 删除指定信息
	 * 
	 * @param infoId
	 * @throws BusinessException
	 */
	void removeInfoById(Integer infoId) throws BusinessException;
}
