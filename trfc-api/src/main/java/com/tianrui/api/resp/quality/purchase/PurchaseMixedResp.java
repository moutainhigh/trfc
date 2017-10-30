package com.tianrui.api.resp.quality.purchase;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class PurchaseMixedResp extends BaseResp {

    private static final long serialVersionUID = -2873047963624926393L;
    //采样ID
    private String id;
    //采样单号
    private String code;
    //供应商名称
    private String suppliername;
    //矿口名称
    private String minemouthname;
    //物料名称
    private String materielname;
    //采样日期
    private Long samplingtime;
    //采样日期
    private String samplingtimeStr;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getSuppliername() {
        return suppliername;
    }
    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }
    public String getMinemouthname() {
        return minemouthname;
    }
    public void setMinemouthname(String minemouthname) {
        this.minemouthname = minemouthname;
    }
    public String getMaterielname() {
        return materielname;
    }
    public void setMaterielname(String materielname) {
        this.materielname = materielname;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getSamplingtime() {
        return samplingtime;
    }
    public void setSamplingtime(Long samplingtime) {
        this.samplingtime = samplingtime;
        this.samplingtimeStr = DateUtil.parse(samplingtime, DateUtil.Y_M_D);
    }
    public String getSamplingtimeStr() {
        return samplingtimeStr;
    }
    public void setSamplingtimeStr(String samplingtimeStr) {
        this.samplingtimeStr = samplingtimeStr;
    }
    @Override
    public String toString() {
        return "PurchaseMixedResp [id=" + id + ", code=" + code + ", suppliername=" + suppliername + ", minemouthname="
                + minemouthname + ", materielname=" + materielname + ", samplingtime=" + samplingtime
                + ", samplingtimeStr=" + samplingtimeStr + "]";
    }
}