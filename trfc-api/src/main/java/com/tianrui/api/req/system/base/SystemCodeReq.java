package com.tianrui.api.req.system.base;


import com.tianrui.api.req.BaseReq;

public class SystemCodeReq extends BaseReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 765821020271879498L;
	/**
	 * 自定义编号ID
	 */
	private String id;
	 /**
     *	单据代号
     */
    private String code;

    /**
     *	单据名称
     */
    private String name;

    /**
     *	单据类型
     */
    private String key;

    /**
     *	说明
     */
    private String example;

    /**
     *	编号方式
     */
    private Byte codeType;

    /**
     *	分隔符
     */
    private Byte splitType;

    /**
     *	起始编号
     */
    private Byte codeBegin;

    /**
     *	起始内码
     */
    private Byte innerCodeBegin;

    /**
     * 用户Id
     */
    private String userName;
    /**
     * 分页查询开始位置
     */
    private Integer start;
    /**
     * 分页查询 查询数目
     */
    private Integer limit;
    /**
     * 排序规则(例如:order by xxx desc)
     */
    private String ordering;
    /**
     * 排序顺序
     */
    private String sorting;
    /**
     * 编号方式(item)
     */
    private String typeValue;
    /**
     * true:编号  fasle:内码
     */
    private Boolean itemType;
    
    
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public Boolean getItemType() {
		return itemType;
	}

	public void setItemType(Boolean itemType) {
		this.itemType = itemType;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getOrdering() {
		return ordering;
	}

	public void setOrdering(String ordering) {
		this.ordering = ordering;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public Byte getCodeType() {
		return codeType;
	}

	public void setCodeType(Byte codeType) {
		this.codeType = codeType;
	}

	public Byte getSplitType() {
		return splitType;
	}

	public void setSplitType(Byte splitType) {
		this.splitType = splitType;
	}

	public Byte getCodeBegin() {
		return codeBegin;
	}

	public void setCodeBegin(Byte codeBegin) {
		this.codeBegin = codeBegin;
	}

	public Byte getInnerCodeBegin() {
		return innerCodeBegin;
	}

	public void setInnerCodeBegin(Byte innerCodeBegin) {
		this.innerCodeBegin = innerCodeBegin;
	}

	


    
}
