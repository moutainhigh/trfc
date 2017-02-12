package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationDetailSave;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SalesApplicationService implements ISalesApplicationService {
	
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	
	@Autowired
	private ISalesApplicationDetailService salesApplicationDetailService;
	
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@Autowired
	private ReturnQueueMapper returnQueueMapper;
	
	@Override
	public PaginationVO<SalesApplicationResp> page(SalesApplicationQuery query) throws Exception{
		PaginationVO<SalesApplicationResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesApplicationResp>();
			query.setState("1");
			long count = salesApplicationMapper.findSalesApplicationPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesApplication> list = salesApplicationMapper.findSalesApplicationPage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public Result add(SalesApplicationSave save) throws Exception {
		Result result = Result.getSuccessResult();
		if(save != null){
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("XXSO");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getCurrid());
			SalesApplication sa = new SalesApplication();
			sa.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			List<SalesApplication> list = salesApplicationMapper.selectSelective(sa);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			PropertyUtils.copyProperties(sa, save);
			sa.setId(UUIDUtil.getId());
			sa.setStatus("0");
			sa.setSource("1");
			sa.setState("1");
			sa.setMakebilltime(System.currentTimeMillis());
			sa.setCreator(save.getCreator());
			sa.setCreatetime(System.currentTimeMillis());
			sa.setModifier(save.getCreator());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.insertSelective(sa) > 0){
				SalesApplicationDetailSave sd = new SalesApplicationDetailSave();
				sd.setId(UUIDUtil.getId());
				sd.setSalesid(sa.getId());
				sd.setMaterielid(save.getMaterielid());
				sd.setMaterielname(save.getMaterielname());
				sd.setWarehouseid(save.getWarehouseid());
				sd.setWarehousename(save.getWarehousename());
				sd.setUnit(save.getUnit());
				sd.setSalessum(save.getSalessum());
				sd.setTaxprice(save.getTaxprice());
				sd.setTaxrate(save.getTaxrate());
				sd.setUntaxprice(save.getUntaxprice());
				sa.setCreator(save.getCreator());
				sa.setCreatetime(System.currentTimeMillis());
				sa.setModifier(save.getCreator());
				sa.setModifytime(System.currentTimeMillis());
				result = salesApplicationDetailService.add(sd);
				if(StringUtils.equals(result.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					if(!StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
					ReturnQueue returnQueue = new ReturnQueue();
					returnQueue.setId(UUIDUtil.getId());
					returnQueue.setDataid(sa.getId());
					returnQueue.setDatatype("0");
					returnQueue.setCreator(sa.getCreator());
					returnQueue.setCreatetime(System.currentTimeMillis());
					returnQueueMapper.insertSelective(returnQueue);
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result update(SalesApplicationSave save) throws Exception {
		Result result = Result.getSuccessResult();
		if(save != null){
			SalesApplication sa = new SalesApplication();
			PropertyUtils.copyProperties(sa, save);
			sa.setModifier(save.getCurrid());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				SalesApplicationDetailSave sd = new SalesApplicationDetailSave();
				sd.setId(save.getDetailid());
				sd.setMaterielid(save.getMaterielid());
				sd.setMaterielname(save.getMaterielname());
				sd.setWarehouseid(save.getWarehouseid());
				sd.setWarehousename(save.getWarehousename());
				sd.setSalessum(save.getSalessum());
				sd.setTaxprice(save.getTaxprice());
				sd.setTaxrate(save.getTaxrate());
				sd.setUntaxprice(save.getUntaxprice());
				result = salesApplicationDetailService.update(sd);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result audit(SalesApplicationQuery query) {
		Result result = Result.getSuccessResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = new SalesApplication();
			sa.setId(query.getId());
			sa.setStatus("1");
			sa.setAuditid(query.getAuditid());
			sa.setAuditname(query.getAuditname());
			sa.setAudittime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result unaudit(SalesApplicationQuery query) {
		Result result = Result.getSuccessResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = new SalesApplication();
			sa.setId(query.getId());
			sa.setStatus("0");
			sa.setAuditid(query.getAuditid());
			sa.setAuditname(query.getAuditname());
			sa.setAudittime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result delete(SalesApplicationQuery query) {
		Result result = Result.getSuccessResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesApplication sa = new SalesApplication();
			sa.setId(query.getId());
			sa.setState("0");
			sa.setModifier(query.getCurrid());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public SalesApplicationResp findOne(SalesApplicationQuery query) throws Exception {
		if(query != null && StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(salesApplicationMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}
	
	private List<SalesApplicationResp> copyBeanList2RespList(List<SalesApplication> list) throws Exception {
		List<SalesApplicationResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesApplicationResp>();
			for(SalesApplication sales : list){
				listResp.add(copyBean2Resp(sales));
			}
		}
		return listResp;
	}
	
	private SalesApplicationResp copyBean2Resp(SalesApplication bean) throws Exception {
		SalesApplicationResp resp = null;
		if(bean != null){
			resp = new SalesApplicationResp();
			PropertyUtils.copyProperties(resp, bean);
			SalesApplicationDetailQuery query = new SalesApplicationDetailQuery();
			query.setSalesid(bean.getId());
			resp.setDetailResp(salesApplicationDetailService.findListBySalesApplicationId(query).get(0));
		}
		return resp;
	}

	@Override
	public Result findMaxUtc(SalesApplicationQuery req) throws Exception {
		Result rs = Result.getErrorResult();
		if(req !=null  ){
			Long utc =salesApplicationMapper.findMaxUtc();
			rs.setData(utc);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}

	@Override
	public Result updateDataWithDC(List<JSONObject> list) throws Exception {
		Result rs = Result.getErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> ids =getAllIds();
			List<SalesApplication> toUpdate =new ArrayList<SalesApplication>();
			List<SalesApplication> toSave =new ArrayList<SalesApplication>();
			List<SalesApplicationDetail> toSaveItem =new ArrayList<SalesApplicationDetail>();
			
			for( JSONObject jsonObject:list ){
				String id =jsonObject.getString("id");
				if( ids.contains(id) ){
					toUpdate.add(converJson2Bean(jsonObject));
				}else{
					toSave.add(converJson2Bean(jsonObject));
					toSaveItem.addAll(converJson2ItemList(jsonObject,id));
				}
			}
			
			if( CollectionUtils.isNotEmpty(toSave) ){
				salesApplicationMapper.insertBatch(toSave);
				salesApplicationDetailMapper.insertBatch(toSaveItem);
			}
			
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( SalesApplication item :toUpdate){
					salesApplicationMapper.updateByPrimaryKeySelective(item);
				}
			}
		}
		return rs;
	}

	
	private Set<String> getAllIds(){
		Set<String> rs = new HashSet<String>();
		List<SalesApplication> list=salesApplicationMapper.selectSelective(null);
		for(SalesApplication item:list){
			rs.add(item.getId());
		}
		return rs;
	}
	private SalesApplication converJson2Bean(JSONObject jsonItem){
		SalesApplication item  =new SalesApplication();
		item.setId(jsonItem.getString("id"));
		//编码
		item.setCode(jsonItem.getString("code"));
		//状态
		item.setStatus("1");
		item.setState("1");
		//来源
		item.setSource("0");
		//类型jsonItem.getString("sourceType")
		item.setBilltypeid("1002P11000000000SEKU");
		//客户
		item.setCustomerid(jsonItem.getString("customerId"));
		//订单日期
		item.setBilltime(DateUtil.parse(jsonItem.getString("orderData"), "yyyy-MM-dd HH:mm:ss"));
		//业务员 TODO
		//销售组织
		item.setOrgid(Constant.ORG_ID);
		item.setOrgname(Constant.ORG_NAME);
		//部门名称jsonItem.getString("deptId")
		item.setDepartmentid("1111");
		//jsonItem.getString("deptName")
		item.setDepartmentname("工程部");
		//运输公司 //TODO transComp
		//制单人 jsonItem.getString("singleId")
		item.setCreator("0001P11000000002AB56");
		//制单日期
		item.setCreatetime(DateUtil.parse(jsonItem.getString("singleData"), "yyyy-MM-dd HH:mm:ss"));
		//审核人
		item.setAuditid(jsonItem.getString("auditPerson"));
		item.setAuditname(jsonItem.getString("auditName"));
		//审核日期
		item.setAudittime(DateUtil.parse(jsonItem.getString("auditData"), "yyyy-MM-dd HH:mm:ss"));
		//区域码 //TODO areaCode
		//TS
		item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		return item;
	}
	private List<SalesApplicationDetail> converJson2ItemList(JSONObject jsonItem,String id){
		List<SalesApplicationDetail> itemList = new ArrayList<SalesApplicationDetail>();
		if( jsonItem.getJSONArray("list") !=null ){
			JSONArray arr =jsonItem.getJSONArray("list");
			if( arr.size()>0){
				for(int i=0;i<arr.size();i++){
					JSONObject itemJon=JSONObject.parseObject(arr.get(0).toString());
					SalesApplicationDetail saleItem = new SalesApplicationDetail();
					
					saleItem.setId(itemJon.getString("id"));
					saleItem.setSalesid(id);
					saleItem.setMaterielid(itemJon.getString("materialId"));
					saleItem.setMaterielname(itemJon.getString("materialCode"));
//					saleItem.setWarehouseid(itemJon.getString(""));
//					saleItem.setWarehousename(itemJon.getString(""));
					saleItem.setUnit("吨");
					saleItem.setSalessum(Double.valueOf(itemJon.getString("number")));
					saleItem.setTaxprice(Double.valueOf(itemJon.getString("nqtorigtaxprice")));
					saleItem.setUntaxprice(Double.valueOf(itemJon.getString("nqtorigprice")));
					saleItem.setTaxrate(Double.valueOf(itemJon.getString("ntaxrate")));
					itemList.add(saleItem);
				}
			}
		}
		return itemList;
	}
}