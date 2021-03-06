package com.tianrui.quartz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.quartz.common.ApiParamUtils;
import com.tianrui.quartz.common.HttpUtils;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrder;
import com.tianrui.service.bean.businessManage.salesManage.SalesOutboundOrderItem;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesOutboundOrderItemMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesOutboundOrderMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;

/**
 * 厂区到数据中心销售出库单同步接口
 * @author YangZhenFu
 *
 */
@Service
public class SalesOutboundOrderService implements ISalesOutboundOrderService{
	
	private Logger log = LoggerFactory.getLogger(SalesOutboundOrderService.class);
	
	@Autowired
	private SalesOutboundOrderMapper salesOutboundOrderMapper;
	@Autowired
	private SalesOutboundOrderItemMapper salesOutboundOrderItemMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SmUserMapper smUserMapper;
	@Autowired
	private IPushSingleService pushSingleService;
	
	
	/**
	 * 获取销售出库单数据
	 */
	@Override
	public List<SalesOutboundOrder> getSalesOutboundOrderList(String orgId,Date minDate) {
		List<SalesOutboundOrder> list =null;
//		if( StringUtils.isNotBlank(orgId) ){
			SalesOutboundOrder query = new SalesOutboundOrder();
			query.setPkOrg(orgId);
			if(minDate!=null){
				query.setTs(String.valueOf(minDate.getTime()));
			}
			//获取增量数据
			list=salesOutboundOrderMapper.selectIncrementalData(query);
			//获取对应的子表数据
			if( CollectionUtils.isNotEmpty(list) ){
				//内存循环
				List<String> ids = new ArrayList<String>();
				Map<String,SalesOutboundOrder> map = new HashMap<String,SalesOutboundOrder>();
				for(SalesOutboundOrder item  :list){
					ids.add(item.getId());
					map.put(item.getId(),item );
				}
				//获取所有的子表数据
				List<SalesOutboundOrderItem> itemList= salesOutboundOrderItemMapper.selectByOrderIds(ids);
				//遍历并赋值到主表
				if( CollectionUtils.isNotEmpty(itemList) ){
					for(SalesOutboundOrderItem orderItem :itemList ){
						SalesOutboundOrder localBoOrder =map.get(orderItem.getSaleOutboundOrderId());
						if( localBoOrder.getList() !=null ){
							localBoOrder.getList().add(orderItem);
						}else{
							List<SalesOutboundOrderItem> list2 =new ArrayList<SalesOutboundOrderItem>();
							list2.add(orderItem);
							localBoOrder.setList(list2);
						}
					}
				}
			}
//		}
		return list;
	}
	
	@Transactional
	@Override
	public void returnSalesOutboundOrder() throws Exception{
//		String orgId=Constant.ORG_ID;
		List<SalesOutboundOrder> list=getSalesOutboundOrderList(null,null);
		log.info("销售出库单FC到DC待推送数据条数：" + (list !=null ? list.size() : 0) + "条");
		List<SmUser> smUserList = null;
		if(CollectionUtils.isNotEmpty(list)){
			List<SalesOutboundOrder> subList=new ArrayList<SalesOutboundOrder>();
			for(int i=0;i<list.size();i++){
				SalesOutboundOrder order=list.get(i);
				smUserList = getSmUser(order.getBillmaker());
				if(CollectionUtils.isNotEmpty(smUserList)){
					order.setBillmaker(smUserList.get(0).getId());
				}
				subList.add(order);
				ApiResult apiResult=HttpUtils.post(ApiParamUtils.getApiParam(subList),Constant.URL_DOMAIN + Constant.URL_RETURN_SALESOUTBOUNDCATION);
				PoundNote pn = poundNoteMapper.selectByPrimaryKey(order.getPoundId());
				PushSingleReq ps = new PushSingleReq();
				ps.setId(UUIDUtil.getId());
				ps.setRequisitionNum(pn.getBillcode());
				ps.setNoticeNum(pn.getNoticecode());
				ps.setRequisitionType(Constant.ONE_STRING);
				ps.setLightCarTime(pn.getLighttime());
				ps.setHeavyCarTime(pn.getWeighttime());
				ps.setNetWeight(pn.getNetweight().toString());
				ps.setCreatetime(System.currentTimeMillis());
				ps.setModifytime(System.currentTimeMillis());
				ps.setDesc2(Constant.ONE_STRING);
				ps.setDesc3(order.getCode());
				if(apiResult!=null && StringUtils.equals(apiResult.getCode(),Constant.SUCCESS )){
					order.setStatus("1");
					if(salesOutboundOrderMapper.updateByPrimaryKeySelective(order)>0){
						pn.setId(order.getPoundId());
						pn.setReturnstatus("2");
						poundNoteMapper.updateByPrimaryKeySelective(pn);
						log.info("操作成功！");
 					}else{
						log.error(ErrorCode.OPERATE_ERROR.getMsg());
						ps.setPushStatus(Constant.THREE_STRING);
					}
					ps.setReasonFailure(apiResult.getError());
					ps.setDesc1(apiResult.getCode());
				}else{
					log.error(apiResult.getError());
					ps.setPushStatus(Constant.THREE_STRING);
					ps.setReasonFailure("FC-DC出库单推单失败，连接超时。");
					ps.setDesc1("-1");
				}
				pushSingleService.savePushSingle(ps);
				subList.clear();
			}
			log.info("同步完成");
		}
	}

	private List<SmUser> getSmUser(String id) throws Exception {
		List<SmUser> smUserList = null;
		SystemUserResp user = systemUserService.getUser(id);
		if(user != null){
			SmUser smUser = new SmUser();
			smUser.setCode(user.getCode());
			smUserList = smUserMapper.selectSelective(smUser);
		}
		return smUserList;
	}
	
	
}
