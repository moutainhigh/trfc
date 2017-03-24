package com.tianrui.api.resp.businessManage.poundNoteMaintain;

import com.tianrui.api.resp.BaseResp;
import com.tianrui.smartfactory.common.utils.DateUtil;

/**
 * @Description 推送结果信息Resp
 * @author zhanggaohao
 * @version 2017年3月23日 下午1:49:48
 */
public class PoundNoteMsgResp extends BaseResp {
	private static final long serialVersionUID = -4624022496274532908L;
	//主键id
    private String id;
    //磅单id
    private String poundnoteid;
    //磅单编号
    private String poundnotecode;
    //状态（1：上传）
    private String status;
    //推送时间
    private Long returntime;
    //推送时间字符串
    private String returntimeStr;
    //推送结果
    private String returnmsg;
    //状态：（0：删除，1：显示）
    private String state;
    //创建人
    private String creator;
    //创建时间
    private Long createtime;
    //创建时间字符串
    private String createtimeStr;
    //最后修改人
    private String modifier;
    //最后修改时间
    private Long modifytime;
    //最后修改时间字符串
    private String modifytimeStr;
    //备注
    private String remark;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the poundnoteid
	 */
	public String getPoundnoteid() {
		return poundnoteid;
	}
	/**
	 * @return the poundnotecode
	 */
	public String getPoundnotecode() {
		return poundnotecode;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @return the returntime
	 */
	public Long getReturntime() {
		return returntime;
	}
	/**
	 * @return the returntimeStr
	 */
	public String getReturntimeStr() {
		return returntimeStr;
	}
	/**
	 * @return the returnmsg
	 */
	public String getReturnmsg() {
		return returnmsg;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}
	/**
	 * @return the createtime
	 */
	public Long getCreatetime() {
		return createtime;
	}
	/**
	 * @return the createtimeStr
	 */
	public String getCreatetimeStr() {
		return createtimeStr;
	}
	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}
	/**
	 * @return the modifytime
	 */
	public Long getModifytime() {
		return modifytime;
	}
	/**
	 * @return the modifytimeStr
	 */
	public String getModifytimeStr() {
		return modifytimeStr;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param poundnoteid the poundnoteid to set
	 */
	public void setPoundnoteid(String poundnoteid) {
		this.poundnoteid = poundnoteid;
	}
	/**
	 * @param poundnotecode the poundnotecode to set
	 */
	public void setPoundnotecode(String poundnotecode) {
		this.poundnotecode = poundnotecode;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param returntime the returntime to set
	 */
	public void setReturntime(Long returntime) {
		this.returntime = returntime;
		this.returntimeStr = DateUtil.parse(returntime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @param returntimeStr the returntimeStr to set
	 */
	public void setReturntimeStr(String returntimeStr) {
		this.returntimeStr = returntimeStr;
	}
	/**
	 * @param returnmsg the returnmsg to set
	 */
	public void setReturnmsg(String returnmsg) {
		this.returnmsg = returnmsg;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
		this.createtimeStr = DateUtil.parse(createtime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @param createtimeStr the createtimeStr to set
	 */
	public void setCreatetimeStr(String createtimeStr) {
		this.createtimeStr = createtimeStr;
	}
	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	/**
	 * @param modifytime the modifytime to set
	 */
	public void setModifytime(Long modifytime) {
		this.modifytime = modifytime;
		this.modifytimeStr = DateUtil.parse(modifytime, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * @param modifytimeStr the modifytimeStr to set
	 */
	public void setModifytimeStr(String modifytimeStr) {
		this.modifytimeStr = modifytimeStr;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}