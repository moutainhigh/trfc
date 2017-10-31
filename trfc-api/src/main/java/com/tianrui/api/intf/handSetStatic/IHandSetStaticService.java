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
    /**
     * @annotation 整车退货
     * @param param
     * @return
     */
    Result allReturnOfGoods(HandSetRequestParam param);
    /**
     * @annotation 退货确认
     * @param param
     * @return
     */
    Result returnOfGoods(HandSetRequestParam param);
    /**
     * @annotation 装货确认
     * @param param
     * @return
     */
    Result confirmationOfShipment(HandSetRequestParam param);
    /**
     * @annotation 空车出厂
     * @param param
     * @return
     */
    Result emptyCarLeavingFactory(HandSetRequestParam param);
    /**
     * @annotation 无需补包
     * @param param
     * @return
     */
    Result noNeedToFillTheBag(HandSetRequestParam param);
    /**
     * @annotation 补包确认
     * @param param
     * @return
     */
    Result confirmationOfFillTheBag(HandSetRequestParam param);
    /**
     * @annotation 回包确认
     * @param param
     * @return
     */
    Result confirmationOfReturnPacket(HandSetRequestParam param);

}
