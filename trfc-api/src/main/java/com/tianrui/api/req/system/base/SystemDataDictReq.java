package com.tianrui.api.req.system.base;

import java.util.Date;

import com.tianrui.api.req.BaseReq;

public class SystemDataDictReq extends BaseReq{

	private static final long serialVersionUID = 8569078493364872297L;
	  
	    private String id;

	    private String code;

	    private String name;

	    private String type;

	    private Integer orderBy;

	    private String info;

	    private String creator;

	    private String modifier;

	    private Date utc;  

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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getOrderBy() {
			return orderBy;
		}

		public void setOrderBy(Integer orderBy) {
			this.orderBy = orderBy;
		}

		public String getInfo() {
			return info;
		}

		public void setInfo(String info) {
			this.info = info;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public String getModifier() {
			return modifier;
		}

		public void setModifier(String modifier) {
			this.modifier = modifier;
		}

		public Date getUtc() {
			return utc;
		}

		public void setUtc(Date utc) {
			this.utc = utc;
		}

		
	    

}
