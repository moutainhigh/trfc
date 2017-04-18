package com.tianrui.service.mapper.businessManage.otherManage;

import java.util.List;

import com.tianrui.api.req.businessManage.otherManage.OtherRKArriveReq;
import com.tianrui.service.bean.businessManage.otherManage.OtherRKArrive;

public interface OtherRKArriveMapper {

    int deleteByPrimaryKey(String id);

	int insert(OtherRKArrive record);

	int insertSelective(OtherRKArrive record);

	OtherRKArrive selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(OtherRKArrive record);

	int updateByPrimaryKey(OtherRKArrive record);
	/**
	 * 分页查询
	 */
	List<OtherRKArrive> page(OtherRKArriveReq req);
	/**
	 * 查询总数
	 */
	Long count(OtherRKArriveReq req);
	/**
	 * 查询一条数据
	 */
	OtherRKArrive selectOneByReq(OtherRKArriveReq req);
}