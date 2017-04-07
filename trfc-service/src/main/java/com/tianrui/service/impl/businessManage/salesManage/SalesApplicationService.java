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
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationJoinDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.common.BillType;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.common.BillTypeMapper;
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
	@Autowired
	private BillTypeMapper billTypeMapper;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	
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
				List<SalesApplicationResp> listResp = copyBeanList2RespList(list, true);
				listRespSetListDetailResp(listResp);
				page.setList(listResp);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Override
	public PaginationVO<SalesApplicationJoinDetailResp> pageGroupMateriel(SalesApplicationQuery query) throws Exception{
		PaginationVO<SalesApplicationJoinDetailResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesApplicationJoinDetailResp>();
			query.setState("1");
			long count = salesApplicationMapper.findPageGroupMaterielCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesApplicationJoinDetailResp> list = salesApplicationMapper.findPageGroupMateriel(query);
				//获取水泥包装类型
				if(CollectionUtils.isNotEmpty(list)){
					for(SalesApplicationJoinDetailResp resp : list){
						MaterielManage materiel = materielManageMapper.selectByPrimaryKey(resp.getMaterielid());
						if(materiel!=null){
							resp.setPackagetype(materiel.getPackagetype());
						}
					}
				}
				page.setList(list);
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
			codeReq.setUserid(save.getMakerid());
			SalesApplication sa = new SalesApplication();
			sa.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			List<SalesApplication> list = salesApplicationMapper.selectSelective(sa);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			save.setCode(sa.getCode());
			PropertyUtils.copyProperties(sa, save);
			sa.setId(UUIDUtil.getId());
			if(StringUtils.isNotBlank(save.getBilltypeid())){
				BillType billType = billTypeMapper.selectByPrimaryKey(save.getBilltypeid());
				if(billType != null){
					sa.setBilltypename(billType.getName());
				}
			}
			if(StringUtils.isNotBlank(save.getCustomerid())){
				CustomerManage customer = customerManageMapper.selectByPrimaryKey(save.getCustomerid());
				if(customer != null){
					sa.setCustomername(customer.getName());
					sa.setChannelcode(customer.getChannelcode());
					sa.setSalesmanid(customer.getSalesmanid());
					sa.setSalesmanname(customer.getSalesmanname());
					sa.setTransportcompanyid(customer.getTransportcompanyid());
					sa.setTransportcompanyname(customer.getTransportcompanyname());
					sa.setDepartmentid(customer.getDepartmentid());
					sa.setDepartmentname(customer.getDepartmentname());
				}
			}
			sa.setOrgid(Constant.ORG_ID);
			sa.setOrgname(Constant.ORG_NAME);
			sa.setStatus("0");
			sa.setSource("1");
			sa.setState("1");
			sa.setMakebilltime(System.currentTimeMillis());
			sa.setCreator(save.getMakerid());
			sa.setCreatetime(System.currentTimeMillis());
			sa.setModifier(save.getMakerid());
			sa.setModifytime(System.currentTimeMillis());
			if(salesApplicationMapper.insertSelective(sa) > 0){
				SalesApplicationDetailSave sd = new SalesApplicationDetailSave();
				sd.setId(UUIDUtil.getId());
				sd.setSalesid(sa.getId());
				sd.setMaterielid(save.getMaterielid());
				if(StringUtils.isNotBlank(save.getMaterielid())){
					MaterielManage mater = materielManageMapper.selectByPrimaryKey(save.getMaterielid());
					if(mater != null){
						sd.setMaterielname(mater.getName());
					}
				}
				sd.setWarehouseid(save.getWarehouseid());
				if(StringUtils.isNotBlank(save.getWarehouseid())){
					WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(save.getWarehouseid());
					sd.setWarehousename(warehouse.getName());
				}
				sd.setUnit(save.getUnit());
				sd.setSalessum(save.getSalessum());
				sd.setTaxprice(save.getTaxprice());
				sd.setTaxrate(save.getTaxrate());
				sd.setUntaxprice(save.getUntaxprice());
				sa.setCreator(save.getMakerid());
				sa.setCreatetime(System.currentTimeMillis());
				sa.setModifier(save.getMakerid());
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
					returnQueue.setCreator(sa.getMakerid());
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
			if(StringUtils.isNotBlank(save.getId())){
				SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(save.getId());
				PropertyUtils.copyProperties(sa, save);
				if(StringUtils.isNotBlank(save.getBilltypeid())){
					BillType billType = billTypeMapper.selectByPrimaryKey(save.getBilltypeid());
					if(billType != null){
						sa.setBilltypename(billType.getName());
					}
				}
				if(StringUtils.isNotBlank(save.getCustomerid())){
					CustomerManage customer = customerManageMapper.selectByPrimaryKey(save.getCustomerid());
					if(customer != null){
						sa.setCustomername(customer.getName());
						sa.setChannelcode(customer.getChannelcode());
						sa.setSalesmanid(customer.getSalesmanid());
						sa.setSalesmanname(customer.getSalesmanname());
						sa.setTransportcompanyid(customer.getTransportcompanyid());
						sa.setTransportcompanyname(customer.getTransportcompanyname());
						sa.setDepartmentid(customer.getDepartmentid());
						sa.setDepartmentname(customer.getDepartmentname());
					}
				}
				sa.setModifier(save.getCurrid());
				sa.setModifytime(System.currentTimeMillis());
				if(salesApplicationMapper.updateByPrimaryKeySelective(sa) > 0){
					SalesApplicationDetailResp applicationDetailResp = salesApplicationDetailService.findOne(save.getDetailid());
					if(StringUtils.isNotBlank(save.getMaterielid())){
						MaterielManage mater = materielManageMapper.selectByPrimaryKey(save.getMaterielid());
						if(mater != null){
							applicationDetailResp.setMaterielname(mater.getName());
						}
					}
					if(StringUtils.isNotBlank(save.getWarehouseid())){
						WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(save.getWarehouseid());
						applicationDetailResp.setWarehousename(warehouse.getName());
					}
					applicationDetailResp.setSalessum(save.getSalessum());
					applicationDetailResp.setTaxprice(save.getTaxprice());
					applicationDetailResp.setTaxrate(save.getTaxrate());
					applicationDetailResp.setUntaxprice(save.getUntaxprice());
					SalesApplicationDetailSave applicationDetailSave = new SalesApplicationDetailSave();
					PropertyUtils.copyProperties(applicationDetailSave, applicationDetailResp);
					result = salesApplicationDetailService.update(applicationDetailSave);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
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
	public SalesApplicationResp findOne(String id, boolean setDetail) throws Exception {
		if(StringUtils.isNotBlank(id)){
			return copyBean2Resp(salesApplicationMapper.selectByPrimaryKey(id), setDetail);
		}
		return null;
	}
	
	@Override
	public List<SalesApplicationResp> selectByIds(List<String> ids, boolean isSetDetail) throws Exception{
		List<SalesApplicationResp> listResp = null;
		if(CollectionUtils.isNotEmpty(ids)){
			listResp = copyBeanList2RespList(salesApplicationMapper.selectByIds(ids), false);
			if(isSetDetail){
				listRespSetListDetailResp(listResp);
			}
		}
		return listResp;
	}
	
	private void listRespSetListDetailResp(List<SalesApplicationResp> listResp) throws Exception{
		if(CollectionUtils.isNotEmpty(listResp)){
			List<String> ids = new ArrayList<String>();
			for(SalesApplicationResp resp : listResp){
				ids.add(resp.getId());
			}
			List<SalesApplicationDetailResp> listDetailResp = salesApplicationDetailService.selectBySalesIds(ids);
			if(CollectionUtils.isNotEmpty(listDetailResp)){
				for(SalesApplicationResp resp : listResp){
					List<SalesApplicationDetailResp> list = new ArrayList<SalesApplicationDetailResp>();
					for(SalesApplicationDetailResp detailResp : listDetailResp){
						if(StringUtils.equals(resp.getId(), detailResp.getSalesid())){
							list.add(detailResp);
						}
					}
					resp.setList(list);
				}
			}
		}
	}
	
	private List<SalesApplicationResp> copyBeanList2RespList(List<SalesApplication> list, boolean setDetail) throws Exception {
		List<SalesApplicationResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesApplicationResp>();
			for(SalesApplication sales : list){
				listResp.add(copyBean2Resp(sales, setDetail));
			}
		}
		return listResp;
	}
	
	private SalesApplicationResp copyBean2Resp(SalesApplication bean, boolean setDetail) throws Exception {
		SalesApplicationResp resp = null;
		if(bean != null){
			resp = new SalesApplicationResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setDetail){
				SalesApplicationDetailQuery query = new SalesApplicationDetailQuery();
				query.setSalesid(bean.getId());
				resp.setList(salesApplicationDetailService.findListBySalesApplicationId(query));
			}
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
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
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
		//类型
		String billtypeid = jsonItem.getString("type");
		if(StringUtils.isNotBlank(billtypeid)){
			item.setBilltypeid(billtypeid);
			BillType bt = billTypeMapper.selectByPrimaryKey(billtypeid);
			if(bt != null && StringUtils.isNotBlank(bt.getName())){
				item.setBilltypename(bt.getName());
			}
		}
		//客户
		String customerid = jsonItem.getString("customerId");
		if(StringUtils.isNotBlank(customerid)){
			item.setCustomerid(customerid);
			CustomerManage cm = customerManageMapper.selectByPrimaryKey(customerid);
			if(cm != null && StringUtils.isNotBlank(cm.getName())){
				item.setCustomername(cm.getName());
			}
		}
		//区域码
		item.setChannelcode(jsonItem.getString("channelTypeCode"));
		//业务员
		item.setSalesmanid(jsonItem.getString("salesPsnId"));
		item.setSalesmanname(jsonItem.getString("salesPsn"));
		//订单日期
		item.setBilltime(DateUtil.parse(jsonItem.getString("orderData"), "yyyy-MM-dd HH:mm:ss"));
		//销售组织
		item.setOrgid(Constant.ORG_ID);
		item.setOrgname(Constant.ORG_NAME);
		//运输公司
		item.setTransportcompanyid(jsonItem.getString("transComp"));
		item.setTransportcompanyname(jsonItem.getString("transport"));
		//部门
		item.setDepartmentid(jsonItem.getString("deptId"));
		item.setDepartmentname(jsonItem.getString("dept"));
		//审核人
		item.setAuditid(jsonItem.getString("auditPerson"));
		item.setAuditname(jsonItem.getString("auditName"));
		//审核日期
		item.setAudittime(DateUtil.parse(jsonItem.getString("auditData"), "yyyy-MM-dd HH:mm:ss"));
		//制单人
		item.setMakerid(jsonItem.getString("singleId"));
		item.setMakebillname(jsonItem.getString("billMaker"));
		//制单日期
		item.setMakebilltime(DateUtil.parse(jsonItem.getString("singleData"), "yyyy-MM-dd HH:mm:ss"));
		item.setRemarks(jsonItem.getString("remark"));
		item.setCreatetime(System.currentTimeMillis());
		item.setModifytime(System.currentTimeMillis());
		//TS
		if(StringUtils.isNotBlank(jsonItem.getString("ts"))){
			item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		}
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
					//物料
					String materielid = itemJon.getString("materialId");
					if(StringUtils.isNotBlank(materielid)){
						saleItem.setMaterielid(materielid);
						MaterielManage m = materielManageMapper.selectByPrimaryKey(materielid);
						if(m != null && StringUtils.isNotBlank(m.getName())){
							saleItem.setMaterielname(m.getName());
						}
					}
					saleItem.setWarehouseid(itemJon.getString("csendstordocId"));
					saleItem.setWarehousename(itemJon.getString("csendstordocName"));
					saleItem.setUnit("吨");
					saleItem.setSalessum(Double.valueOf(itemJon.getString("number")));
					saleItem.setMargin(saleItem.getSalessum());
					saleItem.setStoragequantity(0D);
					saleItem.setUnstoragequantity(0D);
					saleItem.setPretendingtake(0D);
					saleItem.setTaxprice(Double.valueOf(itemJon.getString("nqtorigtaxprice")));
					saleItem.setUntaxprice(Double.valueOf(itemJon.getString("nqtorigprice")));
					saleItem.setTaxrate(Double.valueOf(itemJon.getString("ntaxrate")));
					saleItem.setRemarks(itemJon.getString("remark"));
					itemList.add(saleItem);
				}
			}
		}
		return itemList;
	}
}