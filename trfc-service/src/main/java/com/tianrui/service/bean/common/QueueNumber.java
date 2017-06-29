package com.tianrui.service.bean.common;

public class QueueNumber {
	
	//通知单号
	private String noticecode;
    //车牌号
    private String vehicleno;
    //RFID
    private String rfid;
    //小票号
    private String queuenumber;
    //等待人数
    private Integer waitingnumber;

    public String getNoticecode() {
        return noticecode;
    }

    public void setNoticecode(String noticecode) {
        this.noticecode = noticecode == null ? null : noticecode.trim();
    }
    
    public String getVehicleno() {
        return vehicleno;
    }

    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno == null ? null : vehicleno.trim();
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid == null ? null : rfid.trim();
    }

    public String getQueuenumber() {
        return queuenumber;
    }

    public void setQueuenumber(String queuenumber) {
        this.queuenumber = queuenumber == null ? null : queuenumber.trim();
    }

    public Integer getWaitingnumber() {
        return waitingnumber;
    }

    public void setWaitingnumber(Integer waitingnumber) {
        this.waitingnumber = waitingnumber;
    }
}