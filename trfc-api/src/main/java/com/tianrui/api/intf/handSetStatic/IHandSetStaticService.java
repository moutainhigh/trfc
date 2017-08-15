package com.tianrui.api.intf.handSetStatic;

import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.smartfactory.common.vo.Result;

public interface IHandSetStaticService {
    /**
     * @annotation IC卡读卡
     * @param param
     * @return
     */
    Result readICard(HandSetRequestParam param);
    /**
     * @annotation 确认签收
     * @param param
     * @return
     */
    Result receive(HandSetRequestParam param);

}
