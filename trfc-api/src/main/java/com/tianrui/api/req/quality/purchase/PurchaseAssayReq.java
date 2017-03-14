package com.tianrui.api.req.quality.purchase;

import java.math.BigDecimal;

import com.tianrui.api.req.BaseReq;

public class PurchaseAssayReq extends BaseReq {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 7285442076077733741L;
	/**
	 * id主键
	 */
	private String id;
	/**
	 * 单据编号
	 */
    private String code;
    /**
     * 质检方案id
     */
    private String qschemeid;
    /**
     * 采样id
     */
    private String samplingid;
    /**
     * 化验日期
     */
    private Long assaytime;
    /**
     * 备注
     */
    private String remark;

    private BigDecimal qc0;

    private BigDecimal qc1;

    private BigDecimal qc2;

    private BigDecimal qc3;

    private BigDecimal qc4;

    private BigDecimal qc5;

    private BigDecimal qc6;

    private BigDecimal qc7;

    private BigDecimal qc8;

    private BigDecimal qc9;

    private BigDecimal qc10;

    private BigDecimal qc11;

    private BigDecimal qc12;

    private BigDecimal qc13;

    private BigDecimal qc14;

    private BigDecimal qc15;

    private BigDecimal qc16;

    private BigDecimal qc17;

    private BigDecimal qc18;

    private BigDecimal qc19;

    private BigDecimal qc20;

    private BigDecimal qc21;

    private BigDecimal qc22;

    private BigDecimal qc23;

    private BigDecimal qc24;

    private BigDecimal qc25;

    private BigDecimal qc26;

    private BigDecimal qc27;

    private BigDecimal qc28;

    private BigDecimal qc29;

    private BigDecimal qc30;

    private BigDecimal qc31;

    private BigDecimal qc32;

    private BigDecimal qc33;

    private BigDecimal qc34;

    private BigDecimal qc35;
    /**
     * 用户id
     */
    private String user;
    /**
     * 制单日期
     */
    private Long createtime;
    /**
     * 分页开始位置
     */
    private int start;
    /**
     * 分页 数据量
     */
    private int limit;
    /**
     * 开始日期
     */
    private Long starttime;
    /**
     * 结束日期
     */
	private Long endtime;
	
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
	public String getQschemeid() {
		return qschemeid;
	}
	public void setQschemeid(String qschemeid) {
		this.qschemeid = qschemeid;
	}
	public String getSamplingid() {
		return samplingid;
	}
	public void setSamplingid(String samplingid) {
		this.samplingid = samplingid;
	}
	public Long getAssaytime() {
		return assaytime;
	}
	public void setAssaytime(Long assaytime) {
		this.assaytime = assaytime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getQc0() {
		return qc0;
	}
	public void setQc0(BigDecimal qc0) {
		this.qc0 = qc0;
	}
	public BigDecimal getQc1() {
		return qc1;
	}
	public void setQc1(BigDecimal qc1) {
		this.qc1 = qc1;
	}
	public BigDecimal getQc2() {
		return qc2;
	}
	public void setQc2(BigDecimal qc2) {
		this.qc2 = qc2;
	}
	public BigDecimal getQc3() {
		return qc3;
	}
	public void setQc3(BigDecimal qc3) {
		this.qc3 = qc3;
	}
	public BigDecimal getQc4() {
		return qc4;
	}
	public void setQc4(BigDecimal qc4) {
		this.qc4 = qc4;
	}
	public BigDecimal getQc5() {
		return qc5;
	}
	public void setQc5(BigDecimal qc5) {
		this.qc5 = qc5;
	}
	public BigDecimal getQc6() {
		return qc6;
	}
	public void setQc6(BigDecimal qc6) {
		this.qc6 = qc6;
	}
	public BigDecimal getQc7() {
		return qc7;
	}
	public void setQc7(BigDecimal qc7) {
		this.qc7 = qc7;
	}
	public BigDecimal getQc8() {
		return qc8;
	}
	public void setQc8(BigDecimal qc8) {
		this.qc8 = qc8;
	}
	public BigDecimal getQc9() {
		return qc9;
	}
	public void setQc9(BigDecimal qc9) {
		this.qc9 = qc9;
	}
	public BigDecimal getQc10() {
		return qc10;
	}
	public void setQc10(BigDecimal qc10) {
		this.qc10 = qc10;
	}
	public BigDecimal getQc11() {
		return qc11;
	}
	public void setQc11(BigDecimal qc11) {
		this.qc11 = qc11;
	}
	public BigDecimal getQc12() {
		return qc12;
	}
	public void setQc12(BigDecimal qc12) {
		this.qc12 = qc12;
	}
	public BigDecimal getQc13() {
		return qc13;
	}
	public void setQc13(BigDecimal qc13) {
		this.qc13 = qc13;
	}
	public BigDecimal getQc14() {
		return qc14;
	}
	public void setQc14(BigDecimal qc14) {
		this.qc14 = qc14;
	}
	public BigDecimal getQc15() {
		return qc15;
	}
	public void setQc15(BigDecimal qc15) {
		this.qc15 = qc15;
	}
	public BigDecimal getQc16() {
		return qc16;
	}
	public void setQc16(BigDecimal qc16) {
		this.qc16 = qc16;
	}
	public BigDecimal getQc17() {
		return qc17;
	}
	public void setQc17(BigDecimal qc17) {
		this.qc17 = qc17;
	}
	public BigDecimal getQc18() {
		return qc18;
	}
	public void setQc18(BigDecimal qc18) {
		this.qc18 = qc18;
	}
	public BigDecimal getQc19() {
		return qc19;
	}
	public void setQc19(BigDecimal qc19) {
		this.qc19 = qc19;
	}
	public BigDecimal getQc20() {
		return qc20;
	}
	public void setQc20(BigDecimal qc20) {
		this.qc20 = qc20;
	}
	public BigDecimal getQc21() {
		return qc21;
	}
	public void setQc21(BigDecimal qc21) {
		this.qc21 = qc21;
	}
	public BigDecimal getQc22() {
		return qc22;
	}
	public void setQc22(BigDecimal qc22) {
		this.qc22 = qc22;
	}
	public BigDecimal getQc23() {
		return qc23;
	}
	public void setQc23(BigDecimal qc23) {
		this.qc23 = qc23;
	}
	public BigDecimal getQc24() {
		return qc24;
	}
	public void setQc24(BigDecimal qc24) {
		this.qc24 = qc24;
	}
	public BigDecimal getQc25() {
		return qc25;
	}
	public void setQc25(BigDecimal qc25) {
		this.qc25 = qc25;
	}
	public BigDecimal getQc26() {
		return qc26;
	}
	public void setQc26(BigDecimal qc26) {
		this.qc26 = qc26;
	}
	public BigDecimal getQc27() {
		return qc27;
	}
	public void setQc27(BigDecimal qc27) {
		this.qc27 = qc27;
	}
	public BigDecimal getQc28() {
		return qc28;
	}
	public void setQc28(BigDecimal qc28) {
		this.qc28 = qc28;
	}
	public BigDecimal getQc29() {
		return qc29;
	}
	public void setQc29(BigDecimal qc29) {
		this.qc29 = qc29;
	}
	public BigDecimal getQc30() {
		return qc30;
	}
	public void setQc30(BigDecimal qc30) {
		this.qc30 = qc30;
	}
	public BigDecimal getQc31() {
		return qc31;
	}
	public void setQc31(BigDecimal qc31) {
		this.qc31 = qc31;
	}
	public BigDecimal getQc32() {
		return qc32;
	}
	public void setQc32(BigDecimal qc32) {
		this.qc32 = qc32;
	}
	public BigDecimal getQc33() {
		return qc33;
	}
	public void setQc33(BigDecimal qc33) {
		this.qc33 = qc33;
	}
	public BigDecimal getQc34() {
		return qc34;
	}
	public void setQc34(BigDecimal qc34) {
		this.qc34 = qc34;
	}
	public BigDecimal getQc35() {
		return qc35;
	}
	public void setQc35(BigDecimal qc35) {
		this.qc35 = qc35;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public Long getStarttime() {
		return starttime;
	}
	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}
	public Long getEndtime() {
		return endtime;
	}
	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}
	
}
