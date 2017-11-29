package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.businessManage.financeManage.ISalesDetailService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailSave;
import com.tianrui.api.resp.businessManage.financeManage.SalesDetailResp;
import com.tianrui.service.bean.businessManage.financeManage.SalesDetail;
import com.tianrui.service.mapper.businessManage.financeManage.SalesDetailMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 销售明细service  
 * @author YangZhenFu
 * @createtime 2017年3月16日 上午10:26:52
 * @classname SalesDetailService.java
 */
@Service
public class SalesDetailService implements ISalesDetailService{

	@Autowired
	private SalesDetailMapper salesDetailMapper;
	/**
	 * 分页查询
	 */
	@Override
	public Result page(SalesDetailQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		if(query!=null){
			PaginationVO<SalesDetailResp> page=new PaginationVO<SalesDetailResp>();
			query.setState("1");
			Long count=salesDetailMapper.findSalesDetailPageCount(query);
	//		if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesDetail> list=salesDetailMapper.findSalesDetailPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			/*}else{
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);*/
		}
		return result;
	}
	/**
	 * 获取最大时间戳
	 */
	@Override
	public Result findMaxUtc(BaseReq query) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getParamErrorResult();
		Integer max = salesDetailMapper.findMaxUtc();
		rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		rs.setData(max);
		return rs;
	}
	
	@Override
	public Result updateDataWithDC(List<JSONObject> list) {
		// TODO Auto-generated method stub
		Result rs = Result.getParamErrorResult();
		if (CollectionUtils.isNotEmpty(list)) {
			Set<String> idSet = getAllDb();
			//区分添加还是修改
			ArrayList<SalesDetail> toSave = new ArrayList<SalesDetail>();
			
			ArrayList<SalesDetail> toUpdate = new ArrayList<SalesDetail>();
			for (JSONObject jsonItem : list) {
				if (idSet.contains(jsonItem.get("id"))) {
					toUpdate.add(converJson2Bean(jsonItem));
				} else {
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增调批量添加
			if (CollectionUtils.isNotEmpty(toSave)) {
				salesDetailMapper.insertBatch(toSave);
			}
			//修改就一个一个修改
			if (CollectionUtils.isNotEmpty(toUpdate)) {
				for (SalesDetail item : toUpdate) {
					salesDetailMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	} 
	
	@Transactional
	@Override
	public Result add(SalesDetailSave save) throws Exception {
		Result result=Result.getSuccessResult();
		if(save!=null){
			SalesDetail bean=new SalesDetail();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setState("1");
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			bean.setUtc(System.currentTimeMillis());
			if(salesDetailMapper.insertSelective(bean)>0){
				result.setData("操作成功");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	/**
	 * 集合转换
	 * @param List<SalesDetail> list
	 * @return List<SalesDetailResp> 
	 * @throws Exception
	 */
	private List<SalesDetailResp> copyBeanList2RespList(List<SalesDetail> list) throws Exception {
		List<SalesDetailResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesDetailResp>();
			for(SalesDetail begin : list){
				listResp.add(copyBeanList2RespList(begin));
			}
		}
		return listResp;
	}
	/**
	 * 实体bean类型转换
	 * @param SalesDetail bean
	 * @return SalesDetailResp
	 * @throws Exception
	 */
	private SalesDetailResp copyBeanList2RespList(SalesDetail bean) throws Exception {
		SalesDetailResp resp = null;
		if(bean != null){
			resp = new SalesDetailResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}



	
	private Set<String> getAllDb(){
		HashSet<String> set = new HashSet<String>();
		List<String> ids = salesDetailMapper.selectIds();
		if (CollectionUtils.isNotEmpty(ids)) {
			for (String id : ids) {
				set.add(id);
			}
		}
		return set;
	}
	private SalesDetail converJson2Bean(JSONObject jsonItem){
		SalesDetail item = new SalesDetail();
		item.setId(jsonItem.getString("id"));
		item.setCode(jsonItem.getString("code"));
		item.setOrdercode(jsonItem.getString("ordercode"));
		item.setOrgid(jsonItem.getString("orgid"));
		item.setOrgname(jsonItem.getString("orgname"));
		item.setCustomerid(jsonItem.getString("customerid"));
		item.setCustomername(jsonItem.getString("customername"));
		item.setPrice(jsonItem.getDouble("price"));
		item.setNumber(jsonItem.getDouble("number"));
		item.setMoney(jsonItem.getDouble("money"));
		item.setDeliveryunit(jsonItem.getString("deliveryunit"));
		item.setConsumetime(jsonItem.getLong("consumetime"));
		item.setState(jsonItem.getString("state"));
		item.setCreator(jsonItem.getString("creator"));
		item.setCreatetime(jsonItem.getLong("createtime"));
		item.setModifier(jsonItem.getString("modifier"));
		item.setModifytime(jsonItem.getLong("modifytime"));
		item.setUtc(jsonItem.getLong("utc"));
		return item;
	}
}
