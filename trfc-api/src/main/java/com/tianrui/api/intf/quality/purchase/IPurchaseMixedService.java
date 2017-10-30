package com.tianrui.api.intf.quality.purchase;

import java.util.List;

import com.tianrui.api.req.quality.purchase.PurchaseMixedReq;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.smartfactory.common.vo.Result;

public interface IPurchaseMixedService {

    List<HandSetReturnResp> supplierAutoComplate(String date);
    
    List<HandSetReturnResp> minemouthAutoComplate(String date);
    
    List<HandSetReturnResp> materialAutoComplate(String date);

    Result page(PurchaseMixedReq req) throws Exception;

    Result mixedPage(PurchaseMixedReq req) throws Exception;

}
