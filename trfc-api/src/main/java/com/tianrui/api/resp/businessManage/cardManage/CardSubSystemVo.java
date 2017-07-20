package com.tianrui.api.resp.businessManage.cardManage;

import com.tianrui.api.resp.BaseResp;

public class CardSubSystemVo extends BaseResp {

    private static final long serialVersionUID = -2532161399595115732L;
    //卡面编号
    private String cardCode;
    //卡类型（0：IC采样卡，1：IC过磅卡）
    private String cardType;
    //是否有效（0：无效，1有效）
    private String isValid;
    public String getCardCode() {
        return cardCode;
    }
    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }
    public String getCardType() {
        return cardType;
    }
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    public String getIsValid() {
        return isValid;
    }
    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "CardSubSystemVo [cardCode=" + cardCode + ", cardType=" + cardType + ", isValid=" + isValid + "]";
    }
    
}
