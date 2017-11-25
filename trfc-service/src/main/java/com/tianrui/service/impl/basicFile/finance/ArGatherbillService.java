package com.tianrui.service.impl.basicFile.finance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.finance.IArGatherbillService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.api.req.basicFile.finance.ArGatherbillQuery;
import com.tianrui.api.req.basicFile.finance.ArGatherbillSave;
import com.tianrui.api.resp.basicFile.finance.ArGatherbillResp;
import com.tianrui.service.bean.basicFile.finance.ArGatherbill;
import com.tianrui.service.mapper.basicFile.finance.ArGatherbillMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class ArGatherbillService implements IArGatherbillService {
	
	@Resource
	ArGatherbillMapper  arGatherbillMapper;
	
	
	@Override
	public Result findMaxUtc(BaseReq query) throws Exception {
		Result rs =Result.getParamErrorResult();
		String max =arGatherbillMapper.findMaxUtc();
		rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		rs.setData(max);
		return rs;
	}

	@Override
	public Result updateDataWithDC(List<JSONObject> list) {
		Result rs =Result.getParamErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> idSet = getAllDb();
			// 区分新增还是修改
			List<ArGatherbill>  toSave = new ArrayList<ArGatherbill>();
			List<ArGatherbill>  toUpdate = new ArrayList<ArGatherbill>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				arGatherbillMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( ArGatherbill item:toUpdate ){
					arGatherbillMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<String>();
		List<String> ids =arGatherbillMapper.selectIds();
		if( CollectionUtils.isNotEmpty(ids) ){
			for(String id:ids){
				set.add(id);
			}
		}
		return set;
	}
	
	
	private ArGatherbill converJson2Bean(JSONObject jsonItem){
		ArGatherbill item  =new ArGatherbill();
		
		item.setId(jsonItem.getString("ncid2"));
		item.setNcid2(jsonItem.getString("ncid2"));
		item.setNcid1(jsonItem.getString("ncid1"));
		item.setCustomerId(jsonItem.getString("customerId"));
		item.setCustomerCode(jsonItem.getString("customerCode"));
		
		item.setCustomerName(jsonItem.getString("customerName"));
		item.setBillno(jsonItem.getString("billno"));
		item.setBilldate(jsonItem.getString("billdate"));
		item.setBillstatus(jsonItem.getString("billstatus"));
		item.setLocalMoney(jsonItem.getString("localMoney"));
		
		
		item.setMoney(jsonItem.getString("money"));
		item.setApprover(jsonItem.getString("approver"));
		item.setApprovestatus(jsonItem.getString("approvestatus"));
		item.setApprovedated(jsonItem.getString("approvedated"));
		item.setSaleOrgId(jsonItem.getString("saleOrgId"));
		
		
		item.setSaleOrgName(jsonItem.getString("saleOrgName"));
		item.setDeliveryOrgId(jsonItem.getString("deliveryOrgId"));
		item.setMaterialId(jsonItem.getString("materialId"));
		item.setMaterialCode(jsonItem.getString("materialCode"));
		item.setBillmaker(jsonItem.getString("billmaker"));
		
		item.setTs(jsonItem.getString("ts"));
		item.setDesc1(jsonItem.getString("desc1"));
		item.setDesc2(jsonItem.getString("desc2"));
		item.setDesc3(jsonItem.getString("desc3"));
		item.setDesc4(jsonItem.getString("desc4"));
		
		item.setCreateTime(System.currentTimeMillis());

		return item;
	}

	@Override
	public Result page(ArGatherbillQuery query) throws Exception {
		// TODO Auto-generated method stub
		//返回错误信息
		Result result = Result.getParamErrorResult();
		if (query!=null) {
			PaginationVO<ArGatherbillResp> page = new PaginationVO<ArGatherbillResp>();
			Long count = arGatherbillMapper.findpagecountArGatherbill(query);
//			if (count>0) {
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<ArGatherbill> list = arGatherbillMapper.findpageArGatherbill(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				//保存返回的数据信息
				result.setData(page);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
 			/*}else{
 				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
				//result.setErrorCode(ErrorCode.DATA_ERROR);
			}*/
		}
		return result;
	}
	/**
	 * 集合转换
	 * @param list
	 * @return
	 * @throws Exception 
	 */
	private List<ArGatherbillResp> copyBeanList2RespList(List<ArGatherbill> list) throws Exception {
		// TODO Auto-generated method stub
		List<ArGatherbillResp> listResp = null;
		if (list != null && list.size() >0) {
			listResp = new ArrayList<ArGatherbillResp>();
			for (ArGatherbill begin : list) {
				listResp.add(copyBeanList2RespList(begin));
			}
		}
		return listResp;
	}
	
	/**
	 * 实体bean类型转换
	 * @param ArGatherbill bean
	 * @return ArGatherbillResp
	 * @throws Exception
	 */
	private ArGatherbillResp copyBeanList2RespList(ArGatherbill bean) throws Exception {
		ArGatherbillResp resp = null;
		if(bean != null){
			resp = new ArGatherbillResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result save(ArGatherbillSave save) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}