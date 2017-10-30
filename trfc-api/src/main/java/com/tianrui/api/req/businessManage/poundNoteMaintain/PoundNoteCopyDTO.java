package com.tianrui.api.req.businessManage.poundNoteMaintain;

import com.tianrui.api.req.BaseReq;

/**
 * @annotation 榜单参照参数DTO
 * @author zhanggaohao
 * @date 2017年7月20日 下午4:40:49
 */
public class PoundNoteCopyDTO extends BaseReq {

    private static final long serialVersionUID = -6593675392588564443L;
    //榜单ID
    private String poundNoteId;
    //订单ID
    private String billId;
    //订单子表ID
    private String billDetailId;
    //当前登陆用户ID
    private String currId;
    public String getPoundNoteId() {
        return poundNoteId;
    }
    public void setPoundNoteId(String poundNoteId) {
        this.poundNoteId = poundNoteId;
    }
    public String getBillId() {
        return billId;
    }
    public void setBillId(String billId) {
        this.billId = billId;
    }
    public String getBillDetailId() {
        return billDetailId;
    }
    public void setBillDetailId(String billDetailId) {
        this.billDetailId = billDetailId;
    }
    public String getCurrId() {
        return currId;
    }
    public void setCurrId(String currId) {
        this.currId = currId;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        return "PoundNoteCopyDTO [poundNoteId=" + poundNoteId + ", billId=" + billId + ", billDetailId=" + billDetailId
                + ", currId=" + currId + "]";
    }
   
}
