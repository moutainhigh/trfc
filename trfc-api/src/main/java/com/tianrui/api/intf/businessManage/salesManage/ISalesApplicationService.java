package com.tianrui.api.intf.businessManage.salesManage;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.resp.businessManage.app.AppOrderDetailResp;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationJoinDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface ISalesApplicationService {

	PaginationVO<SalesApplicationResp> page(SalesApplicationQuery query) throws Exception;

	Result add(SalesApplicationSave save) throws Exception;

	Result update(SalesApplicationSave save) throws Exception;

	Result audit(SalesApplicationQuery query);

	Result unaudit(SalesApplicationQuery query);

	Result delete(SalesApplicationQuery query);

	SalesApplicationResp findOne(String id, boolean setDetail) throws Exception;
	
	//查询最大时间戳
	Result findMaxUtc(SalesApplicationQuery req) throws Exception;
	//同步修改订单数据
	Result updateDataWithDC(List<JSONObject> list) throws Exception;

	List<SalesApplicationResp> selectByIds(List<String> ids, boolean isSetDetail) throws Exception;

	PaginationVO<SalesApplicationJoinDetailResp> pageGroupMateriel(SalesApplicationQuery query) throws Exception;
	/**
	 * @Description app销售订单接口
	 * @author zhanggaohao
	 * @version 2017年4月14日 下午3:08:47
	 * @param req
	 * @return
	 */
	PaginationVO<AppOrderResp> appToPage(AppOrderReq req);
	/**
	 * @Description app销售订单详情接口
	 * @author zhanggaohao
	 * @version 2017年4月14日 下午4:08:51
	 * @param req
	 * @return
	 */
	AppOrderDetailResp appToDetail(AppOrderReq req);




}