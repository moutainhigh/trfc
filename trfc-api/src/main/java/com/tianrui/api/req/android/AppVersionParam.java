package com.tianrui.api.req.android;

public class AppVersionParam extends AppBase {

	private static final long serialVersionUID = 4631504344714344550L;
	//手机系统型号 android / ios
	private String phoneType;
	//版本号
	private String version;
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AppVersionParam [phoneType=" + phoneType + ", version=" + version + "]";
	}
}