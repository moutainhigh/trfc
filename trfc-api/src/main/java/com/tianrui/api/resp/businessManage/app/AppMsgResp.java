package com.tianrui.api.resp.businessManage.app;

import com.tianrui.api.req.BaseReq;
/**
 * 订单详情
 * @author lixp
 *
 */
public class AppMsgResp extends BaseReq{

	private static final long serialVersionUID = 280565475561660076L;

    private String id;
    //时间
    private String createTime;
    //标题
    private String title;
    //内容
    private String content;
    //发送人
    private String fromUser;
    //状态
    private String status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

    
    
  

}
