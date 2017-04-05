package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.ProduceImage;

public interface ProduceImageDao {
	int deleteByPrimaryKey(Integer produceImageId);

	int insert(ProduceImage record);

	int insertSelective(ProduceImage record);

	ProduceImage selectByPrimaryKey(Integer produceImageId);

	int updateByPrimaryKeySelective(ProduceImage record);

	int updateByPrimaryKey(ProduceImage record);

	/**
	 * 根据产品成果id获取绑定的图片
	 * 
	 * @param produceId
	 * @return
	 */
	List<ProduceImage> getProduceImagesByProduceId(@Param("produceId") Integer produceId);

	/**
	 * 获取指定produceid和图片类型的图片
	 * 
	 * @param produceId
	 *            不能为空
	 * @param imagetype
	 *            不能为空
	 * @return
	 */
	ProduceImage getProduceImagesByProduceIdAndImageType(@Param("produceId") Integer produceId,
			@Param("imageType") Integer imagetype);

	/**
	 * 获取指定图片类型的产品成果图片
	 * 
	 * @param imagetype
	 *            不能为空
	 * @return
	 */
	List<ProduceImage> getProduceImagesByImageType(@Param("imageType") Integer imagetype);

	/**
	 * 更新图片的类型
	 * 
	 * @param image
	 * @return
	 */
	int updateImageTypeByImageIdAndType(ProduceImage image);

	/**
	 * 删除指定产品的所有图片
	 * 
	 * @param produceId
	 * @return
	 */
	int deleteAllProduceImagesByProduceId(Integer produceId);

}