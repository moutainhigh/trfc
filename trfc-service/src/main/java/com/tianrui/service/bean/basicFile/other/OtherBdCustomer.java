package com.tianrui.service.bean.basicFile.other;

import java.io.Serializable;

public class OtherBdCustomer implements Serializable{
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 9083011762217382896L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.id
     *	客户ID
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.code
     *	客户编码
     * @mbggenerated
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.innercode
     *	客户内码
     * @mbggenerated
     */
    private String innercode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.name
     *	客户名称
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.info
     *	客户信息
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.isvalid
     *	有效性 0:无效,1:有效
     * @mbggenerated
     */
    private Byte isvalid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.orgid
     *	组织ID
     * @mbggenerated
     */
    private String orgid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.orgname
     *	组织名称
     * @mbggenerated
     */
    private String orgname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.remark
     *	备注
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.creator
     *	创建者
     * @mbggenerated
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.createtime
     *	创建时间
     * @mbggenerated
     */
    private Long createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.modifier
     *	修改者
     * @mbggenerated
     */
    private String modifier;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tr_other_bd_customer.modifytime
     *	修改时间
     * @mbggenerated
     */
    private Long modifytime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.id
     *
     * @return the value of tr_other_bd_customer.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.id
     *
     * @param id the value for tr_other_bd_customer.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.code
     *
     * @return the value of tr_other_bd_customer.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.code
     *
     * @param code the value for tr_other_bd_customer.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.innercode
     *
     * @return the value of tr_other_bd_customer.innercode
     *
     * @mbggenerated
     */
    public String getInnercode() {
        return innercode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.innercode
     *
     * @param innercode the value for tr_other_bd_customer.innercode
     *
     * @mbggenerated
     */
    public void setInnercode(String innercode) {
        this.innercode = innercode == null ? null : innercode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.name
     *
     * @return the value of tr_other_bd_customer.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.name
     *
     * @param name the value for tr_other_bd_customer.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.info
     *
     * @return the value of tr_other_bd_customer.info
     *
     * @mbggenerated
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.info
     *
     * @param info the value for tr_other_bd_customer.info
     *
     * @mbggenerated
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.isvalid
     *
     * @return the value of tr_other_bd_customer.isvalid
     *
     * @mbggenerated
     */
    public Byte getIsvalid() {
        return isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.isvalid
     *
     * @param isvalid the value for tr_other_bd_customer.isvalid
     *
     * @mbggenerated
     */
    public void setIsvalid(Byte isvalid) {
        this.isvalid = isvalid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.orgid
     *
     * @return the value of tr_other_bd_customer.orgid
     *
     * @mbggenerated
     */
    public String getOrgid() {
        return orgid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.orgid
     *
     * @param orgid the value for tr_other_bd_customer.orgid
     *
     * @mbggenerated
     */
    public void setOrgid(String orgid) {
        this.orgid = orgid == null ? null : orgid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.orgname
     *
     * @return the value of tr_other_bd_customer.orgname
     *
     * @mbggenerated
     */
    public String getOrgname() {
        return orgname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.orgname
     *
     * @param orgname the value for tr_other_bd_customer.orgname
     *
     * @mbggenerated
     */
    public void setOrgname(String orgname) {
        this.orgname = orgname == null ? null : orgname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.remark
     *
     * @return the value of tr_other_bd_customer.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.remark
     *
     * @param remark the value for tr_other_bd_customer.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.creator
     *
     * @return the value of tr_other_bd_customer.creator
     *
     * @mbggenerated
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.creator
     *
     * @param creator the value for tr_other_bd_customer.creator
     *
     * @mbggenerated
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.createtime
     *
     * @return the value of tr_other_bd_customer.createtime
     *
     * @mbggenerated
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.createtime
     *
     * @param createtime the value for tr_other_bd_customer.createtime
     *
     * @mbggenerated
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.modifier
     *
     * @return the value of tr_other_bd_customer.modifier
     *
     * @mbggenerated
     */
    public String getModifier() {
        return modifier;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.modifier
     *
     * @param modifier the value for tr_other_bd_customer.modifier
     *
     * @mbggenerated
     */
    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tr_other_bd_customer.modifytime
     *
     * @return the value of tr_other_bd_customer.modifytime
     *
     * @mbggenerated
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tr_other_bd_customer.modifytime
     *
     * @param modifytime the value for tr_other_bd_customer.modifytime
     *
     * @mbggenerated
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
}