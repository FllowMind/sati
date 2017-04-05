package com.jyu.sati.business.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyu.sati.entity.Info;
import com.jyu.sati.vo.InfoPageVo;

public interface InfoDao {
	int deleteByPrimaryKey(Integer infoId);

	int insert(Info record);

	int insertSelective(Info record);

	Info selectByPrimaryKey(Integer infoId);

	int updateByPrimaryKeySelective(Info record);

	int updateByPrimaryKeyWithBLOBs(Info record);

	int updateByPrimaryKey(Info record);

	int updateInfoStatus(@Param("infoId") Integer infoId);

	/**
	 * 获取一页数据
	 * 
	 * @param condition
	 * @return
	 */
	List<Info> getInfoPageByCondition(InfoPageVo condition);

	/**
	 * 根据查询条件获取信息总数
	 * 
	 * @param conditon
	 * @return
	 */
	Integer getTotalNoByCondition(InfoPageVo condition);

	/**
	 * 获取信息类型
	 * 
	 * @param infoId
	 * @return
	 */
	Integer getInfoTypeByInfoId(@Param("infoId") Integer infoId);

}