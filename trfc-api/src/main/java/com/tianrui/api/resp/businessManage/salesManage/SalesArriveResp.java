package com.tianrui.api.resp.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.resp.BaseResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.smartfactory.common.utils.DateUtil;
/**
 * @Description 销售提货通知单Resp
 * @author zhanggaohao
 * @version 2017年2月4日 上午9:27:07
 */
public class SalesArriveResp extends BaseResp {
	
	private static final long serialVersionUID = 4625515863613381387L;
	//主键id
	private String id="";
	//提货单号
    private String code="";
    //审核状态
    private String auditstatus="";
    //来源
    private String source="";
    //状态：（0：未入厂，1：一次过磅，2：二次过磅，3：作废，4：发卡，5：出厂，6：入厂，7：装车）
    private String status="";
    //车辆id
    private String vehicleid="";
    //车牌号
    private String vehicleno="";
    //RFID
    private String vehiclerfid="";
    //司机id
    private String driverid="";
    //司机名称
    private String drivername="";
    //司机身份证号
    private String driveridentityno="";
    //销售订单id
    private String billid="";
    //销售订单编号
    private String billcode="";
    //销售订单详情id
    private String billdetailid="";
    //作废/强制出厂人
    private String abnormalperson="";
    //作废/强制出厂人名称
    private String abnormalpersonname="";
    //作废/强制出厂时间
    private Long abnormaltime=0l;
    //作废/强制出厂时间Str
	private String abnormaltimeStr="";
	//单位
    private String unit="";
    //提货量
    private Double takeamount=0d;
    //实际提货量
    private Double actualtakeamount=0d;
    //喷码
    private String spraycode="";
    //出厂编号
    private String serialnumber="";
    //IC卡id
    private String icardid="";
    //卡序号
    private String icardno="";
    //数据状态：（0：删除，1：正常）
    private String state="";
    //主单扣量（0：否，1：是）
    private String maindeduction="";
    //制单人id
    private String makerid="";
    //制单人名称
    private String makebillname="";
    //制单时间
    private Long makebilltime=0l;
    //制单时间Str
    private String makebilltimeStr="";
    //备注
    private String remarks="";
    //创建人
    private String creator="";
    //创建人名称
    private String creatorname="";
    //创建时间
    private Long createtime=0l;
    //创建时间Str
    private String createtimeStr="";
    //最后修改人
    private String modifier="";
    //最后修改人名称
    private String modifiername="";
    //最后修改时间
    private Long modifytime=0l;
    //最后修改时间Str
    private String modifytimeStr="";
    //开始装车
    private Long startloadingtime=0l;
    //结束装车
    private Long endloadingtime=0l;
    //铅封时间
    private Long sealtime=0l;
    //销售订单列表
    private List<SalesApplicationResp> listApplication = new ArrayList<SalesApplicationResp>();
    
    //磅单信息
    private PoundNoteResp poundNoteResp;
    //入厂时间
    private Long enterTime;
    //出厂时间
    private Long outTime;
    //强制出厂标识（0：否，1：是）
    private Integer forceOutFactory;
    //强制出厂操作人
    private String forceOutFactoryPerson;
    //强制出厂操作时间
    private Long forceOutFactoryTime;
    //作废状态（0：未作废，1：作废中，2：已作废）
    private String validStatus;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	public String getVehiclerfid() {
		return vehiclerfid;
	}

	public void setVehiclerfid(String vehiclerfid) {
		this.vehiclerfid = vehiclerfid;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDriveridentityno() {
		return driveridentityno;
	}

	public void setDriveridentityno(String driveridentityno) {
		this.driveridentityno = driveridentityno;
	}

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getBilldetailid() {
		return billdetailid;
	}

	public void setBilldetailid(String billdetailid) {
		this.billdetailid = billdetailid;
	}

	public String getAbnormalperson() {
		return abnormalperson;
	}

	public void setAbnormalperson(String abnormalperson) {
		this.abnormalperson = abnormalperson;
	}

	public String getAbnormalpersonname() {
		return abnormalpersonname;
	}

	public void setAbnormalpersonname(String abnormalpersonname) {
		this.abnormalpersonname = abnormalpersonname;
	}

	public Long getAbnormaltime() {
		return abnormaltime;
	}

	public void setAbnormaltime(Long abnormaltime) {
		this.abnormaltime = abnormaltime;
		this.abnormaltimeStr = DateUtil.parse(abnormaltime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getAbnormaltimeStr() {
		return abnormaltimeStr;
	}

	public void setAbnormaltimeStr(String abnormaltimeStr) {
		this.abnormaltimeStr = abnormaltimeStr;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getTakeamount() {
		return takeamount;
	}

	public void setTakeamount(Double takeamount) {
		this.takeamount = takeamount;
	}

	/**
	 * @return the actualtakeamount
	 */
	public Double getActualtakeamount() {
		return actualtakeamount;
	}

	/**
	 * @param actualtakeamount the actualtakeamount to set
	 */
	public void setActualtakeamount(Double actualtakeamount) {
		this.actualtakeamount = actualtakeamount;
	}

	public String getSpraycode() {
		return spraycode;
	}

	public void setSpraycode(String spraycode) {
		this.spraycode = spraycode;
	}

	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getIcardid() {
		return icardid;
	}

	public void setIcardid(String icardid) {
		this.icardid = icardid;
	}

	public String getIcardno() {
		return icardno;
	}

	public void setIcardno(String icardno) {
		this.icardno = icardno;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMaindeduction() {
		return maindeduction;
	}

	public void setMaindeduction(String maindeduction) {
		this.maindeduction = maindeduction;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
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

	public String getModifiername() {
		return modifiername;
	}

	public void setModifiername(String modifiername) {
		this.modifiername = modifiername;
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

	public Long getStartloadingtime() {
		return startloadingtime;
	}

	public void setStartloadingtime(Long startloadingtime) {
		this.startloadingtime = startloadingtime;
	}

	public Long getEndloadingtime() {
		return endloadingtime;
	}

	public void setEndloadingtime(Long endloadingtime) {
		this.endloadingtime = endloadingtime;
	}

	public Long getSealtime() {
		return sealtime;
	}

	public void setSealtime(Long sealtime) {
		this.sealtime = sealtime;
	}

	/**
	 * @return the listApplication
	 */
	public List<SalesApplicationResp> getListApplication() {
		return listApplication;
	}

	/**
	 * @param listApplication the listApplication to set
	 */
	public void setListApplication(List<SalesApplicationResp> listApplication) {
		this.listApplication = listApplication;
	}
	
	public SalesApplicationResp getMainApplication(){
		if (this.listApplication != null && this.listApplication.size() > 0) {
			for (SalesApplicationResp resp : this.listApplication) {
				if(StringUtils.equals(resp.getId(), this.billid)){
					return resp;
				}
			}
		}
		return null;
	}
	
	public SalesApplicationDetailResp getMainApplicationDetail(){
		if (this.listApplication != null && this.listApplication.size() > 0) {
			for (SalesApplicationResp resp : this.listApplication) {
				if(resp.getList() != null && StringUtils.equals(resp.getList().get(0).getId(), this.billdetailid)){
					return resp.getList().get(0);
				}
			}
		}
		return null;
	}
	
	public Double getBillSum() {
		if (StringUtils.equals(this.maindeduction, "0")) {
			Double billSum = 0.0;
			if (this.listApplication != null && this.listApplication.size() > 0) {
				for (SalesApplicationResp resp : this.listApplication) {
					billSum += resp.getList().get(0).getSalessum();
				}
			}
			return billSum;
		}else{
			return getMainApplicationDetail().getSalessum();
		}
	}
	
	public Double getYlSum() {
		Double billSum = 0.0;
		if (this.listApplication != null && this.listApplication.size() > 0) {
			for (SalesApplicationResp resp : this.listApplication) {
				billSum += resp.getList().get(0).getMargin();
			}
		}
		return billSum;
	}
	
	public String getIds() {
		if (CollectionUtils.isNotEmpty(listApplication)) {
			List<List<String>> list = new ArrayList<List<String>>();
			for (SalesApplicationResp bill : listApplication) {
				List<String> item = new ArrayList<String>();
				item.add(bill.getId());
				item.add(bill.getList().get(0).getId());
				list.add(item);
			}
			return JSON.toJSONString(list);
		}
		return null;
	}

	public PoundNoteResp getPoundNoteResp() {
		return poundNoteResp;
	}

	public void setPoundNoteResp(PoundNoteResp poundNoteResp) {
		this.poundNoteResp = poundNoteResp;
	}

	public Long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Long enterTime) {
		this.enterTime = enterTime;
	}

	public Long getOutTime() {
		return outTime;
	}

	public void setOutTime(Long outTime) {
		this.outTime = outTime;
	}

    public Integer getForceOutFactory() {
        return forceOutFactory;
    }

    public void setForceOutFactory(Integer forceOutFactory) {
        this.forceOutFactory = forceOutFactory;
    }

    public String getForceOutFactoryPerson() {
        return forceOutFactoryPerson;
    }

    public void setForceOutFactoryPerson(String forceOutFactoryPerson) {
        this.forceOutFactoryPerson = forceOutFactoryPerson;
    }

    public Long getForceOutFactoryTime() {
        return forceOutFactoryTime;
    }

    public void setForceOutFactoryTime(Long forceOutFactoryTime) {
        this.forceOutFactoryTime = forceOutFactoryTime;
    }

	public String getValidStatus() {
		return validStatus;
	}

	public void setValidStatus(String validStatus) {
		this.validStatus = validStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
