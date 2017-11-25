package com.tianrui.api.resp.businessManage.purchaseManage;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

public class PushSingleResp extends BaseResp {

	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 8057881803241952298L;
	//主键id
		private String id;
		//申请单号
		private String requisitionNum;
		//通知单号
		private String noticeNum;
		//推送状态（1、推单中 2、成功 3、失败）
		private String pushStatus;
		//申请单类型（1、入库单2、申请单 3、提货单 4、通知单）
		private String requisitionType;
		//失败原因
		private String reasonFailure;
		//轻车时间
		private Long lightCarTime;
		private String  lightCarTimeStr;
		//重车时间
		private Long heavyCarTime;
		private String  heavyCarTimeStr;
		//净重
		private String netWeight;
		//创建人
		private  String creator;
		//创建时间（推送时间）
		private Long createtime;
		private String  createtimeStr;
		//修改人
		private String modifier;
		//修改时间（NC回写时间）
		private Long modifytime;
		private String  modifytimeStr;
		private String desc1;
		private String desc2;
		private String desc3;
		private String desc4;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getRequisitionNum() {
			return requisitionNum;
		}
		public void setRequisitionNum(String requisitionNum) {
			this.requisitionNum = requisitionNum;
		}
		public String getNoticeNum() {
			return noticeNum;
		}
		public void setNoticeNum(String noticeNum) {
			this.noticeNum = noticeNum;
		}
		public String getPushStatus() {
			return pushStatus;
		}
		public void setPushStatus(String pushStatus) {
			this.pushStatus = pushStatus;
		}
		public String getRequisitionType() {
			return requisitionType;
		}
		public void setRequisitionType(String requisitionType) {
			this.requisitionType = requisitionType;
		}
		public String getReasonFailure() {
			return reasonFailure;
		}
		public void setReasonFailure(String reasonFailure) {
			this.reasonFailure = reasonFailure;
		}
		public Long getLightCarTime() {
			return lightCarTime;
		}
		public void setLightCarTime(Long lightCarTime) {
			this.lightCarTime = lightCarTime;
			this.lightCarTimeStr = DateUtil.parse(lightCarTime, "yyyy-MM-dd HH:mm:ss");
		}
		public Long getHeavyCarTime() {
			return heavyCarTime;
		}
		public void setHeavyCarTime(Long heavyCarTime) {
			this.heavyCarTime = heavyCarTime;
			this.heavyCarTimeStr = DateUtil.parse(heavyCarTime, "yyyy-MM-dd HH:mm:ss");
		}
		public String getNetWeight() {
			return netWeight;
		}
		public void setNetWeight(String netWeight) {
			this.netWeight = netWeight;
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
		public String getDesc1() {
			return desc1;
		}
		public void setDesc1(String desc1) {
			this.desc1 = desc1;
		}
		public String getDesc2() {
			return desc2;
		}
		public void setDesc2(String desc2) {
			this.desc2 = desc2;
		}
		public String getDesc3() {
			return desc3;
		}
		public void setDesc3(String desc3) {
			this.desc3 = desc3;
		}
		public String getDesc4() {
			return desc4;
		}
		public void setDesc4(String desc4) {
			this.desc4 = desc4;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getLightCarTimeStr() {
			return lightCarTimeStr;
		}
		public void setLightCarTimeStr(String lightCarTimeStr) {
			this.lightCarTimeStr = lightCarTimeStr;
		}
		public String getHeavyCarTimeStr() {
			return heavyCarTimeStr;
		}
		public void setHeavyCarTimeStr(String heavyCarTimeStr) {
			this.heavyCarTimeStr = heavyCarTimeStr;
		}
		public String getCreatetimeStr() {
			return createtimeStr;
		}
		public void setCreatetimeStr(String createtimeStr) {
			this.createtimeStr = createtimeStr;
		}
		public String getModifytimeStr() {
			return modifytimeStr;
		}
		public void setModifytimeStr(String modifytimeStr) {
			this.modifytimeStr = modifytimeStr;
		}
		
		

}
