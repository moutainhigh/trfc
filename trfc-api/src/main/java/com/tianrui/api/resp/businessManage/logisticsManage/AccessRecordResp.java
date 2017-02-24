package com.tianrui.api.resp.businessManage.logisticsManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

/**
 * @Description 门禁记录resp
 * @author zhanggaohao
 * @version 2017年2月20日 下午4:03:32
 */
public class AccessRecordResp extends BaseResp {
	/** serialVersionUID */
	private static final long serialVersionUID = 3378768743187921904L;
	//主键id
    private String id;
    //门禁单号
    private String code;
    //业务类型（1：采购，2：销售，3：其他入库，4：其他出库）
    private String businesstype;
    //门禁类型（1：入厂，2：出厂）
    private String accesstype;
    //通知单id
    private String noticeid;
    //通知单号
    private String noticecode;
    //入厂硬件来源
    private String entersource;
    //入厂时间
    private Long entertime;
    //入厂时间字符串
    private String entertimeStr;
    //出厂硬件来源
    private String outsource;
    //出场时间
    private Long outtime;
    //出场时间字符串
    private String outtimeStr;
    //状态：（0：删除，1：显示）
    private String state;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //备注
    private String remark;
    //车牌号
    private String vehicleno;
    //物料名称
    private String materielname;
    //RFID
    private String rfid;
    //单位
    private String otherparty;
    //IC卡序号
    private String icardno;
    //IC卡面编号
    private String icardcode;
    
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
	public String getBusinesstype() {
		return businesstype;
	}
	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	public String getAccesstype() {
		return accesstype;
	}
	public void setAccesstype(String accesstype) {
		this.accesstype = accesstype;
	}
	public String getNoticeid() {
		return noticeid;
	}
	public void setNoticeid(String noticeid) {
		this.noticeid = noticeid;
	}
	public String getNoticecode() {
		return noticecode;
	}
	public void setNoticecode(String noticecode) {
		this.noticecode = noticecode;
	}
	public String getEntersource() {
		return entersource;
	}
	public void setEntersource(String entersource) {
		this.entersource = entersource;
	}
	public Long getEntertime() {
		return entertime;
	}
	public void setEntertime(Long entertime) {
		this.entertime = entertime;
		this.entertimeStr = DateUtil.parse(entertime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getEntertimeStr() {
		return entertimeStr;
	}
	public void setEntertimeStr(String entertimeStr) {
		this.entertimeStr = entertimeStr;
	}
	public String getOutsource() {
		return outsource;
	}
	public void setOutsource(String outsource) {
		this.outsource = outsource;
	}
	public Long getOuttime() {
		return outtime;
	}
	public void setOuttime(Long outtime) {
		this.outtime = outtime;
		this.outtimeStr = DateUtil.parse(outtime, "yyyy-MM-dd HH:mm:ss");
	}
	public String getOuttimeStr() {
		return outtimeStr;
	}
	public void setOuttimeStr(String outtimeStr) {
		this.outtimeStr = outtimeStr;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getVehicleno() {
		return vehicleno;
	}
	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}
	public String getMaterielname() {
		return materielname;
	}
	public void setMaterielname(String materielname) {
		this.materielname = materielname;
	}
	public String getRfid() {
		return rfid;
	}
	public void setRfid(String rfid) {
		this.rfid = rfid;
	}
	public String getOtherparty() {
		return otherparty;
	}
	public void setOtherparty(String otherparty) {
		this.otherparty = otherparty;
	}
	public String getIcardno() {
		return icardno;
	}
	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}
	public String getIcardcode() {
		return icardcode;
	}
	public void setIcardcode(String icardcode) {
		this.icardcode = icardcode;
	}
}
