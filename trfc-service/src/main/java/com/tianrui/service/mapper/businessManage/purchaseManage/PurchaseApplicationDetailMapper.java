package com.tianrui.service.mapper.businessManage.purchaseManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;

public interface PurchaseApplicationDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseApplicationDetail record);

    int insertSelective(PurchaseApplicationDetail record);

    PurchaseApplicationDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PurchaseApplicationDetail record);

    int updateByPrimaryKey(PurchaseApplicationDetail record);

	List<PurchaseApplicationDetail> selectSelective(PurchaseApplicationDetail record);

	List<PurchaseApplicationDetail> selectByPurchaseId(String purchaseId);
	
	List<PurchaseApplicationDetail> selectByIds(List<String> list);
	
	int insertBatch(@Param("list")List<PurchaseApplicationDetail> list);
}