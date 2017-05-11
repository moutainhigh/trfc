package com.tianrui.api.resp.common;

import com.tianrui.api.resp.BaseResp;

public class UploadImageResp extends BaseResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3431903555534946442L;
	
	//主键id
    private String id;
    //1：入厂，2：一次过磅，3：二次过磅，4：出厂，5：袋装，6：散装
    private String source;
    //单据编码
    private String billcode;
    //单据类型   0采购到货 ,1采购退货,2销售提货,3销售退货,4厂内倒运,5其它入库,6其它入库退货,7其它出库,8其它出库退货,9工程车辆
    private String billtype;
    //图片地址
    private String imgurl;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //备注
    private String remarks;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBillcode() {
		return billcode;
	}
	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}
	public String getBilltype() {
		return billtype;
	}
	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Long getModifytime() {
		return modifytime;
	}
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
