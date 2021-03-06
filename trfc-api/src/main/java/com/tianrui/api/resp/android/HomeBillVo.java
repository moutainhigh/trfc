package com.tianrui.api.resp.android;

import com.tianrui.api.resp.BaseResp;

public class HomeBillVo extends BaseResp {

	private static final long serialVersionUID = -8334906301337503006L;
	//订单ID
	private String id = "";
	//子表ID
	private String detailId = "";
	//订单编号
	private String code = "";
	//物料
	private String material = "";
	//订单日期
	private String billTime = "";
	//订单来源
	private String billSource = "";
	//供应商备注
	private String supRemark = "";
	//矿口
	private String minemouth = "";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getBillTime() {
		return billTime;
	}
	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}
	public String getBillSource() {
		return billSource;
	}
	public void setBillSource(String billSource) {
		this.billSource = billSource;
	}
	public String getSupRemark() {
		return supRemark;
	}
	public void setSupRemark(String supRemark) {
		this.supRemark = supRemark;
	}
	public String getMinemouth() {
		return minemouth;
	}
	public void setMinemouth(String minemouth) {
		this.minemouth = minemouth;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "HomeBillVo [id=" + id + ", detailId=" + detailId + ", code=" + code + ", material=" + material
				+ ", billTime=" + billTime + ", billSource=" + billSource + ", supRemark=" + supRemark + ", minemouth="
				+ minemouth + "]";
	}
}
