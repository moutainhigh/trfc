package com.tianrui.service.mapper.businessManage.report;

import java.util.List;

import com.tianrui.service.bean.businessManage.report.ReportPurchase;

public interface ReportPurchaseMapper {

    ReportPurchase selectByPrimaryKey(String id);
    
    /**
     * 根据条件查找数据
     * @param reportPurchase
     * @return
     */
    List<ReportPurchase> selectByCondition(ReportPurchase reportPurchase);
    
    /**
     * 根据条件查找条数
     * @param Long
     * @return
     */
    Long countByCondition(ReportPurchase reportPurchase);

}