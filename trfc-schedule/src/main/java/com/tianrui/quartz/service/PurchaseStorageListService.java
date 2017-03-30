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

import com.tianrui.quartz.common.ApiParamUtils;
import com.tianrui.quartz.common.HttpUtils;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageList;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseStorageListItem;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListItemMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseStorageListMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
/**
 * 厂区到数据中心采购入库单数据同步接口
 * @author YangZhenFu
 *
 */
@Service
public class PurchaseStorageListService implements IPurchaseStorageListService{

	private Logger Logger=LoggerFactory.getLogger(PurchaseStorageListService.class);
	
	@Autowired
	private PurchaseStorageListMapper purchaseStorageListMapper;
	@Autowired
	private PurchaseStorageListItemMapper PurchaseStorageListItemMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	
	/**
	 * 获取采购入库单数据
	 */
	@Override
	public List<PurchaseStorageList> getPurchaseStorageList(String orgId, Date minDate) {
		List<PurchaseStorageList> list=null;
		if(StringUtils.isNotBlank(orgId)){
			PurchaseStorageList query=new PurchaseStorageList();
			query.setPkOrg(orgId);
			if(minDate!=null){
				query.setTs(String.valueOf(minDate.getTime()));
			}
			//获取增量数据
			list=purchaseStorageListMapper.selectIncrementalData(query);
			if(CollectionUtils.isNotEmpty(list)){
				List<String> ids=new ArrayList<String>();
				Map<String,PurchaseStorageList> map=new HashMap<String,PurchaseStorageList>();
				for (PurchaseStorageList item : list) {
					ids.add(item.getId());
					map.put(item.getId(), item);
				}
				//获取所有的子表数据
				List<PurchaseStorageListItem> itemList=PurchaseStorageListItemMapper.selectByOrderIds(ids);
				//遍历并赋值到主表
				if(CollectionUtils.isNotEmpty(itemList)){
					for (PurchaseStorageListItem listItem : itemList) {
						PurchaseStorageList order=map.get(listItem.getPurchaseStorageListId());
						if(order.getList()!=null){
							order.getList().add(listItem);
						}else{
							List<PurchaseStorageListItem> itemList2=new ArrayList<PurchaseStorageListItem>();
							itemList2.add(listItem);
							order.setList(itemList2);
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 数据回传
	 */
	@Transactional
	@Override
	public void returnPurchaseStorageList() throws Exception {
		String orgId=Constant.ORG_ID;
		//获取采购入库单数据
		List<PurchaseStorageList> list=getPurchaseStorageList(orgId,null);
		if(CollectionUtils.isNotEmpty(list)){
			List<PurchaseStorageList> subList=new ArrayList<PurchaseStorageList>();
			for(int i=0;i<list.size();i++){
				PurchaseStorageList order=list.get(i);
				subList.add(order);
				ApiResult apiResult=HttpUtils.post(ApiParamUtils.getApiParam(subList), Constant.URL_RETURN_PURCHASESTORAGEATION);
				if(apiResult!=null && StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)){
					order.setStatus("1");
					if(purchaseStorageListMapper.updateByPrimaryKeySelective(order)>0){
						PoundNote pn = new PoundNote();
						pn.setPutinwarehousecode(order.getCode());
						pn.setReturnstatus("2");
						poundNoteMapper.updateByOrderCode(pn);
						Logger.info("操作成功!");
					}else{
						Logger.info(ErrorCode.OPERATE_ERROR.getMsg());
					}
				}else{
					Logger.error(apiResult.getError());
				}
				subList.clear();
			}
			Logger.info("同步完成!");
		}
		
	}

}
