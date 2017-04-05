package com.jyu.sati.business.dao;

import java.util.List;

import com.jyu.sati.entity.TechnologyBaseInfo;
import com.jyu.sati.vo.TechnologyPageVo;

public interface TechnologyBaseInfoDao {
    int deleteByPrimaryKey(Integer tbiId);

    int insert(TechnologyBaseInfo record);

    int insertSelective(TechnologyBaseInfo record);

    TechnologyBaseInfo selectByPrimaryKey(Integer tbiId);

    int updateByPrimaryKeySelective(TechnologyBaseInfo record);

    int updateByPrimaryKey(TechnologyBaseInfo record);
    
    /**
     * 分页查询
     * @param condition
     * @return
     */
    List<TechnologyBaseInfo> getTechInfo(TechnologyPageVo condition);
    
    /**
     * 获取查询数据总条数
     * @param condition
     * @return
     */
    Integer getTotalNo(TechnologyPageVo condition);
}