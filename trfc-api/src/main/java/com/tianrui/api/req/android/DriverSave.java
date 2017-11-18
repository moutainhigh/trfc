package com.tianrui.api.req.android;

/**
 * @annotation 客商APP添加司机
 * @author zhanggaohao
 *
 */
public class DriverSave extends AppBase {

	private static final long serialVersionUID = 5882329856858968389L;
	//名称
	private String name;
	//简称
	private String abbrName;
	//手机号
	private String telephone;
	//身份证号
	private String idNo;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbrName() {
		return abbrName;
	}
	public void setAbbrName(String abbrName) {
		this.abbrName = abbrName;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "DriverSave [name=" + name + ", abbrName=" + abbrName + ", telephone=" + telephone + ", idNo=" + idNo
				+ "]";
	}
}