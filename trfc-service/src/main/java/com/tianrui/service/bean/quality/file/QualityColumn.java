package com.tianrui.service.bean.quality.file;

public class QualityColumn {
	/**
	 * id
	 */
    private String id;
    /**
     * 名称
     */
    private String val;
    /**
     * 内容
     */
    private String txt;
    /**
     * 类型(0:采购,1:销售)
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt == null ? null : txt.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}