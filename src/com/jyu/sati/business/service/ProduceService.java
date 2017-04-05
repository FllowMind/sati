package com.jyu.sati.business.service;

import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import com.jyu.sati.common.exception.BusinessException;
import com.jyu.sati.entity.Produce;
import com.jyu.sati.entity.ProduceImage;
import com.jyu.sati.vo.ProduceInfoVo;
import com.jyu.sati.vo.ProducePageVo;

/**
 * @author 淋雨又调皮
 *
 */
/**
 * @author 淋雨又调皮
 *
 */
/**
 * @author 淋雨又调皮
 *
 */
public interface ProduceService {

	/**
	 * 创建新的产品成果
	 * 
	 * @return
	 * @throws BusinessException
	 */
	ProduceInfoVo createNewProduce() throws BusinessException;

	/**
	 * 提交产品成果
	 * 
	 * @param produceInfo
	 * @throws BusinessException
	 */
	void submitProduce(ProduceInfoVo produceInfo) throws BusinessException;

	/**
	 * 保存产品成果
	 * 
	 * @param produceInfo
	 * @throws BusinessException
	 */
	void saveProduce(ProduceInfoVo produceInfo) throws BusinessException;

	/**
	 * 通过id获取产品成果
	 * 
	 * @param produceId
	 * @return
	 * @throws BusinessException
	 */
	ProduceInfoVo getProduceInfoById(Integer produceId) throws BusinessException;

	/**
	 * 通过id获取产品成果（管理员使用）
	 * 
	 * @param produceId
	 * @return
	 * @throws BusinessException
	 */
	ProduceInfoVo getAuditProduceById(Integer produceId) throws BusinessException;

	/**
	 * 通过产品id获取所有的产品成果图片
	 * 
	 * @param pdocuceId
	 * @return
	 * @throws BusinessException
	 */
	List<ProduceImage> getAllProduceImagesByProduceId(Integer produceId) throws BusinessException;

	/**
	 * 通过图片类型获取所有的产品成果图片
	 * 
	 * @param pdocuceId
	 * @return
	 * @throws BusinessException
	 */
	List<ProduceImage> getAllProduceImagesByImageType(Integer imageType) throws BusinessException;

	/**
	 * 通过产品id和图片类型获取产品成果图片
	 * 
	 * @param pdocuceId
	 * @param produceType
	 * @return
	 * @throws BusinessException
	 */
	ProduceImage getProduceImagesByProduceIdAndImageType(Integer produceId, Integer imageType) throws BusinessException;

	/**
	 * 删除指定的产品成果
	 * 
	 * @param produceId
	 * @throws BusinessException
	 */
	void removeProduce(Integer produceId) throws BusinessException;

	/**
	 * 更新指定的产品信息状态
	 * 
	 * @param produceId
	 * @param produceStatus
	 * @throws BusinessException
	 */
	void updateProduceStatus(Integer produceId, Integer produceStatus) throws BusinessException;

	/**
	 * 指定取消在首页显示的产品成果
	 * 
	 * @param produceId
	 * @throws BusinessException
	 */
	void updateIsRecommenedStatus(Integer produceId) throws BusinessException;

	/**
	 * 根据查询条件获取一页产品成果数据
	 * 
	 * @param condition
	 * @throws BusinessException
	 */
	public List<Produce> getProducePageByCondition(ProducePageVo condition) throws BusinessException;

	/**
	 * 根据审核状态和发布人id 获取产品数量
	 * 
	 * @param auditStatus
	 * @param publisherId
	 * @return
	 * @throws BusinessException
	 */
	public Integer getProduceCountByAuditStatusAndPublisher(Integer auditStatus, String publisherId)
			throws BusinessException;

	/**
	 * 根据查询条件获取产品数目
	 * 
	 * @param condition
	 * @return
	 * @throws BusinessException
	 */
	public Integer getProdcueCountByCondition(ProducePageVo condition) throws BusinessException;
}
