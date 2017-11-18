package com.tianrui.api.req.android;

/**
 * 用户登录
 * @author lixp
 *
 */
public class LoginUserParam extends AppBase {

	private static final long serialVersionUID = 7754051527490768321L;
	//用户id
	private String id;
	//登录账户
	private String account;
	//登录密码
	private String pwd;
	//待修改密码
	private String newPwd;
	//手机号
	private String mobilePhone;
	//待切换用户NCID
	private String cutoverUserNCId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCutoverUserNCId() {
		return cutoverUserNCId;
	}
	public void setCutoverUserNCId(String cutoverUserNCId) {
		this.cutoverUserNCId = cutoverUserNCId;
	}
	@Override
	public String toString() {
		return "LoginUserParam [id=" + id + ", account=" + account + ", pwd=" + pwd + ", newPwd=" + newPwd
				+ ", mobilePhone=" + mobilePhone + ", cutoverUserNCId=" + cutoverUserNCId + "]";
	}
	
}