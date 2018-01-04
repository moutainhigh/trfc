package com.tianrui.service.mapper.businessManage.report;

import java.util.List;

import com.tianrui.api.resp.businessManage.report.ReportPurchaseMaterResp;
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
    //采购物料（根据条件查找数据）
    List<ReportPurchaseMaterResp> selectByConditionForMater(ReportPurchase reportPurchase);
    //采购物料（根据条件查找条数）
    Long countByConditionForMater(ReportPurchase reportPurchase);
    //采购单位（根据条件查找数据）
    List<ReportPurchaseMaterResp> selectByConditionForMatercg(ReportPurchase reportPurchase);
    //采购单位（根据条件查找条数）
    Long countByConditionForMatercg(ReportPurchase reportPurchase);
    //采购收料收货人（根据条件查找数据）
    List<ReportPurchase> selectByConditionForMaterSignPersonName(ReportPurchase reportPurchase);
    //采购收料收货人（根据条件查找条数）
    Long countByConditionForMaterSignPersonName(ReportPurchase reportPurchase);
    /**
     * 榜单补增（根据条件查找数据）
     * @param reportPurchase
     * @return
     */
    List<ReportPurchase> selectByConditionForBuZeng(ReportPurchase reportPurchase);
    
    /**
     *  榜单补增（根据条件查找条数）
     * @param Long
     * @return
     */
    Long countByConditionForBuZeng(ReportPurchase reportPurchase);
    /**
     * 矿口汇总表（矿口不为空）（根据条件查找数据）
     * @param reportPurchase
     * @return
     */
    List<ReportPurchaseMaterResp> selectByConditionForMinemouthname(ReportPurchase reportPurchase);
    
    /**
     *  矿口汇总表（矿口不为空）（根据条件查找条数）
     * @param Long
     * @return
     */
    Long countByConditionForMinemouthname(ReportPurchase reportPurchase);
    /**
     * 供应商物料时间汇总（根据条件查找数据）
     * @param reportPurchase
     * @return
     */
    List<ReportPurchaseMaterResp> selectByConditionForDateStr(ReportPurchase reportPurchase);
    
    /**
     *  供应商物料时间汇总（根据条件查找条数）
     * @param Long
     * @return
     */
    Long countByConditionForDateStr(ReportPurchase reportPurchase);
    /**
     * 采矿点汇总（根据条件查找数据）
     * @param reportPurchase
     * @return
     */
    List<ReportPurchaseMaterResp> selectByConditionForMiningpointname(ReportPurchase reportPurchase);
    
    /**
     * 采矿点汇总（根据条件查找条数）
     * @param Long
     * @return
     */
    Long countByConditionForMiningpointname(ReportPurchase reportPurchase);
}