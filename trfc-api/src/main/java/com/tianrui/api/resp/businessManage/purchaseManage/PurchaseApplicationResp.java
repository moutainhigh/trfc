package com.tianrui.api.resp.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

/**
 * @Description 采购申请单Resp
 * @author zhanggaohao
 * @version 2017年2月13日 上午9:31:43
 */
public class PurchaseApplicationResp extends BaseResp {
	/** serialVersionUID */
	private static final long serialVersionUID = 4187702554630690799L;
	//主键id
    private String id;
	//订单编号
    private String code;
	//审核状态 0-未审核，1-已审核
    private String auditstatus;
	//来源 0-联机，1-脱机
    private String source;
	//订单类型id
    private String billtypeid;
	//订单类型名称
    private String billtypename;
	//订单日期
    private Long billtime;
    //订单日期字符串
    private String billtimeStr;
	//总数量
    private Double sumnum;
	//组织id
    private String orgid;
	//组织名称
    private String orgname;
	//供应商id
    private String supplierid;
	//供应商名称
    private String suppliername;
	//部门id
    private String departmentid;
	//部门
    private String departmentname;
	//采购员id
    private String buyerid;
	//采购员姓名
    private String buyername;
	//审核人id
    private String auditid;
	//审核人姓名
    private String auditname;
	//审核日期
    private Long audittime;
    //审核日期字符串
    private String audittimeStr;
	//状态：（0：删除，1：正常）
    private String state;
	//制单人id
    private String makerid;
	//制单人名称
    private String makebillname;
	//制单时间
    private Long makebilltime;
    //制单时间字符串
    private String makebilltimeStr;
	//制单人
    private String creator;
	//制单时间
    private Long createtime;
    //制单时间字符串
    private String createtimeStr;
	//修改人
    private String modifier;
	//修改时间
    private Long modifytime;
    //修改时间字符串
    private String modifytimeStr;
	//备注
    private String remark;
    //申请单详情集合
    private List<PurchaseApplicationDetailResp> listdetail;
    
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
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBilltypeid() {
		return billtypeid;
	}
	public void setBilltypeid(String billtypeid) {
		this.billtypeid = billtypeid;
	}
	public String getBilltypename() {
		return billtypename;
	}
	public void setBilltypename(String billtypename) {
		this.billtypename = billtypename;
	}
	public Long getBilltime() {
		return billtime;
	}
	public void setBilltime(Long billtime) {
		this.billtime = billtime;
		this.billtimeStr = DateUtil.parse(billtime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getBilltimeStr() {
		return billtimeStr;
	}
	public void setBilltimeStr(String billtimeStr) {
		this.billtimeStr = billtimeStr;
	}
	public Double getSumnum() {
		return sumnum;
	}
	public void setSumnum(Double sumnum) {
		this.sumnum = sumnum;
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
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public String getBuyername() {
		return buyername;
	}
	public void setBuyername(String buyername) {
		this.buyername = buyername;
	}
	public String getAuditid() {
		return auditid;
	}
	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}
	public String getAuditname() {
		return auditname;
	}
	public void setAuditname(String auditname) {
		this.auditname = auditname;
	}
	public Long getAudittime() {
		return audittime;
	}
	public void setAudittime(Long audittime) {
		this.audittime = audittime;
		this.audittimeStr = DateUtil.parse(audittime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getAudittimeStr() {
		return audittimeStr;
	}
	public void setAudittimeStr(String audittimeStr) {
		this.audittimeStr = audittimeStr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMakerid() {
		return makerid;
	}
	public void setMakerid(String makerid) {
		this.makerid = makerid;
	}
	public String getMakebillname() {
		return makebillname;
	}
	public void setMakebillname(String makebillname) {
		this.makebillname = makebillname;
	}
	public Long getMakebilltime() {
		return makebilltime;
	}
	public void setMakebilltime(Long makebilltime) {
		this.makebilltime = makebilltime;
		this.makebilltimeStr = DateUtil.parse(makebilltime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getMakebilltimeStr() {
		return makebilltimeStr;
	}
	public void setMakebilltimeStr(String makebilltimeStr) {
		this.makebilltimeStr = makebilltimeStr;
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
		this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getCreatetimeStr() {
		return createtimeStr;
	}
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
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
		this.modifytimeStr = DateUtil.parse(modifytime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getModifytimeStr() {
		return modifytimeStr;
	}
	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<PurchaseApplicationDetailResp> getListdetail() {
		return listdetail;
	}
	public void setListdetail(List<PurchaseApplicationDetailResp> listdetail) {
		this.listdetail = listdetail;
	}

}