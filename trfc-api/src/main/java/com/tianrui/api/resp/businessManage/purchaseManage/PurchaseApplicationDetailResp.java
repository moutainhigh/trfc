package com.tianrui.api.resp.businessManage.purchaseManage;

import com.tianrui.api.resp.BaseResp;

/**
 * @Description 采购申请单详情Resp
 * @author zhanggaohao
 * @version 2017年2月13日 上午9:31:20
 */
public class PurchaseApplicationDetailResp extends BaseResp {
	/** serialVersionUID */
	private static final long serialVersionUID = -4708671085489826954L;
	//主键id
    private String id;
	//采购申请单id
    private String purchaseid;
    //组织id
    private String orgid;
    //组织名称
    private String orgname;
	//物料id
    private String materielid;
	//物料名称
    private String materielname;
	//物料规格
    private String materielspec;
	//物料类型
    private String materieltype;
	//数量
    private Double purchasesum;
    //余量
    private Double margin;
    //出库占用量
    private Double storagequantity;
    //未出库占用量
    private Double unstoragequantity;
    //预提占用
    private Double pretendingtake;
    //包装类型
    private String packagetype;
    //单价
    private Double price;
	//单位 default='吨'
    private String unit;
	//备注
    private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public String getOrgname() {
		return orgname;
	}
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	public String getMaterielid() {
		return materielid;
	}
	public void setMaterielid(String materielid) {
		this.materielid = materielid;
	}
	public String getMaterielname() {
		return materielname;
	}
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	public String getMaterielspec() {
		return materielspec;
	}
	public void setMaterielspec(String materielspec) {
		this.materielspec = materielspec;
	}
	public String getMaterieltype() {
		return materieltype;
	}
	public void setMaterieltype(String materieltype) {
		this.materieltype = materieltype;
	}
	public Double getPurchasesum() {
		return purchasesum;
	}
	public void setPurchasesum(Double purchasesum) {
		this.purchasesum = purchasesum;
	}
	/**
	 * @return the margin
	 */
	public Double getMargin() {
		return margin;
	}
	/**
	 * @return the storagequantity
	 */
	public Double getStoragequantity() {
		return storagequantity;
	}
	/**
	 * @return the unstoragequantity
	 */
	public Double getUnstoragequantity() {
		return unstoragequantity;
	}
	/**
	 * @return the pretendingtake
	 */
	public Double getPretendingtake() {
		return pretendingtake;
	}
	/**
	 * @param margin the margin to set
	 */
	public void setMargin(Double margin) {
		this.margin = margin;
	}
	/**
	 * @param storagequantity the storagequantity to set
	 */
	public void setStoragequantity(Double storagequantity) {
		this.storagequantity = storagequantity;
	}
	/**
	 * @param unstoragequantity the unstoragequantity to set
	 */
	public void setUnstoragequantity(Double unstoragequantity) {
		this.unstoragequantity = unstoragequantity;
	}
	/**
	 * @param pretendingtake the pretendingtake to set
	 */
	public void setPretendingtake(Double pretendingtake) {
		this.pretendingtake = pretendingtake;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPackagetype() {
		return packagetype;
	}
	public void setPackagetype(String packagetype) {
		this.packagetype = packagetype;
	}
    
}