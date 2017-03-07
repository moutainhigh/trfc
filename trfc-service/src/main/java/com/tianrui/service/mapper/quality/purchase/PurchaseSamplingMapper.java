package com.tianrui.service.mapper.quality.purchase;

import java.util.List;

import com.tianrui.api.req.quality.purchase.PurchaseSamplingReq;
import com.tianrui.service.bean.quality.purchase.PurchaseSampling;

public interface PurchaseSamplingMapper {
	/**
	 * 删除
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 新增
     */
    int insert(PurchaseSampling record);
    /**
     * 新增(动态)
     */
    int insertSelective(PurchaseSampling record);
    /**
     * 查询(id)
     */
    PurchaseSampling selectByPrimaryKey(String id);
    /**
     * 更新(动态)
     */
    int updateByPrimaryKeySelective(PurchaseSampling record);
    /**
     * 更新
     */
    int updateByPrimaryKey(PurchaseSampling record);
    /**
     * 分页查询
     */
    List<PurchaseSampling> page(PurchaseSamplingReq record);
    /**
     * 查询数据总数
     */
    int count(PurchaseSamplingReq req);

}