package com.tianrui.service.bean.access;

public class AccessRecord {
    /**
     * �Ž��¼id
     */
	private String id;
	/**
	 * ͨ�浥��
	 */
    private String salesarrivecode;
    /**
     * ic����
     */
    private String icardcode;
    /**
     * ��¼ʱ��
     */
    private String intotime;
    /**
     * RFID
     */
    private String rfid;
    /**
     * �Ž�����(1:�볧,2:����)
     */
    private String accesstype;
    /**
     * ҵ������(1:�ɹ�,2:����)
     */
    private String businesstype;
    /**
     * ��Դ
     */
    private String source;
    /**
     * ״̬(0:ɾ��,1:��ʾ)
     */
    private String state;
    /**
     * ������
     */
    private String creator;
    /**
     * ����ʱ��
     */
    private Long createtime;
    /**
     * �޸���
     */
    private String modifier;
    /**
     * �޸�ʱ��
     */
    private Long modifytime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSalesarrivecode() {
        return salesarrivecode;
    }

    public void setSalesarrivecode(String salesarrivecode) {
        this.salesarrivecode = salesarrivecode == null ? null : salesarrivecode.trim();
    }

    public String getIcardcode() {
        return icardcode;
    }

    public void setIcardcode(String icardcode) {
        this.icardcode = icardcode == null ? null : icardcode.trim();
    }

    public String getIntotime() {
		return intotime;
	}

	public void setIntotime(String intotime) {
		this.intotime = intotime;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getAccesstype() {
        return accesstype;
    }

    public void setAccesstype(String accesstype) {
        this.accesstype = accesstype;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
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
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Long getModifytime() {
        return modifytime;
    }

    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }
}