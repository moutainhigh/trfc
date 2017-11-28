package com.tianrui.service.impl.businessManage.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.report.IPurchaseReportService;
import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseMaterResp;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseResp;
import com.tianrui.service.bean.businessManage.report.ReportPurchase;
import com.tianrui.service.mapper.businessManage.report.ReportPurchaseMapper;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class PurchaseReportService implements IPurchaseReportService {

	@Autowired
	private ReportPurchaseMapper reportPurchaseMapper;

	@Override
	public PaginationVO<ReportPurchaseResp> page(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		PaginationVO<ReportPurchaseResp> page = null;
		if(reportPurchaseQuery != null){
			//参数转换
			ReportPurchase query= queryParam(reportPurchaseQuery,true);
			if( query !=null ){
				page = new PaginationVO<ReportPurchaseResp>();
				//查询总数
				long count = reportPurchaseMapper.countByCondition(query);
				if(count > 0){
					//查询结果转换
					page.setList(copyBeanList2RespList( reportPurchaseMapper.selectByCondition(query)));
				}
				//返回结果参数补全
				page.setPageNo(reportPurchaseQuery.getPageNo());
				page.setPageSize(reportPurchaseQuery.getPageSize());
				page.setTotal(count);
			}
		}
		return page;
	}
	
	@Override
	public List<ReportPurchaseResp> list(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		// TODO Auto-generated method stub
		List<ReportPurchaseResp> rs = null;
		if(reportPurchaseQuery != null){
			//参数转换
			ReportPurchase query= queryParam(reportPurchaseQuery,false);
			
			if( query !=null ){
				//查询结果转换
				rs=copyBeanList2RespList( reportPurchaseMapper.selectByCondition(query));
			}
		}
		if( CollectionUtils.isNotEmpty(rs) ){
			rs =new ArrayList<ReportPurchaseResp>();
		}
		return rs;
	}
	//拼装查询参数   
	/**
	 * 
	 * @param query
	 * @param bb 当bb=true时调用分页，bb=false时，不调用分页
	 * @return
	 */
	private ReportPurchase queryParam(ReportPurchaseQuery query,boolean bb){
		ReportPurchase bean =null;
		if(query!=null){
			bean =new ReportPurchase();
			//开始时间
			//StringUtils.isNotBlank(query.getBeginTime())空字符串检查
			if( StringUtils.isNotBlank(query.getBeginTime()) ){
				bean.setBeginTimeLong(DateUtil.parse(query.getBeginTime()+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
			}
			//结束时间
			if( StringUtils.isNotBlank(query.getEndTime()) ){
				bean.setEndTimeLong(DateUtil.parse(query.getEndTime()+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
			}
			//供应商
			if( StringUtils.isNotBlank(query.getSuppliername()) ){
				bean.setSupplierNameLike(query.getSuppliername());
			}
			//物料
			if( StringUtils.isNotBlank(query.getCargo()) ){
				bean.setCargoNameLike(query.getCargo());
			}
			//司机
			if( StringUtils.isNotBlank(query.getDrivername()) ){
				bean.setDriverNameLike(query.getDrivername());
			}
			//车牌
			if( StringUtils.isNotBlank(query.getVehicleno()) ){
				bean.setVehicleNoLike(query.getVehicleno());
			}
			//矿口
			if( StringUtils.isNotBlank(query.getMinemouthname()) ){
				bean.setMinemouthNameLike(query.getMinemouthname());
			}
			if (bb=true) {
				//分页参数
				bean.setStart((query.getPageNo()-1)*query.getPageSize());
				bean.setLimit(query.getPageSize());
			}
			
		}
		return bean;
		
	}
	
	//返回对象拼装
	private List<ReportPurchaseResp> copyBeanList2RespList(List<ReportPurchase> list){
		List<ReportPurchaseResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs=new ArrayList<ReportPurchaseResp>();
			for(ReportPurchase item:list){
				if( item !=null ){
					ReportPurchaseResp itemResp = new ReportPurchaseResp();
					try {
						PropertyUtils.copyProperties(itemResp, item);
						rs.add(itemResp);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return rs;
	}
	//返回对象拼装
	private List<ReportPurchaseMaterResp> copyBeanList2RespList1(List<ReportPurchase> list){
		List<ReportPurchaseMaterResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs=new ArrayList<ReportPurchaseMaterResp>();
			for(ReportPurchase item:list){
				if( item !=null ){
					ReportPurchaseMaterResp itemResp = new ReportPurchaseMaterResp();
					try {
						PropertyUtils.copyProperties(itemResp, item);
						rs.add(itemResp);
					}  catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return rs;
	}


	@Override
	public PaginationVO<ReportPurchaseMaterResp> page1(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		 PaginationVO<ReportPurchaseMaterResp> page = null;
		 if (reportPurchaseQuery !=null ) {
			ReportPurchase query = queryParam(reportPurchaseQuery,true);
			if (query !=null) {
				page = new PaginationVO<ReportPurchaseMaterResp>();
				//查询总数
				Long count = reportPurchaseMapper.countByConditionForMater(query);
				if (count >0) {
					page.setList(reportPurchaseMapper.selectByConditionForMater(query));
				}
				//返回结果参数补全
				page.setPageNo(reportPurchaseQuery.getPageNo());
				page.setPageSize(reportPurchaseQuery.getPageSize());
				page.setTotal(count);
			}
		}
		return page;
	}

	@Override
	public List<ReportPurchaseMaterResp> list1(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		// TODO Auto-generated method stub
		List<ReportPurchaseMaterResp> rs = null;
		if (reportPurchaseQuery !=null) {
			ReportPurchase query = queryParam(reportPurchaseQuery,false);
			if (query !=null) {
				rs = reportPurchaseMapper.selectByConditionForMater(query);
			}
			
		}
		if( CollectionUtils.isNotEmpty(rs) ){
			rs =new ArrayList<ReportPurchaseMaterResp>();
		}
		return rs;
	}


	@Override
	public PaginationVO<ReportPurchaseMaterResp> page2(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		 PaginationVO<ReportPurchaseMaterResp> page = null;
		 if (reportPurchaseQuery !=null ) {
			ReportPurchase query = queryParam(reportPurchaseQuery,true);
			if (query !=null) {
				page = new PaginationVO<ReportPurchaseMaterResp>();
				//查询总数
				Long count = reportPurchaseMapper.countByConditionForMatercg(query);
				if (count >0) {
					page.setList(reportPurchaseMapper.selectByConditionForMatercg(query));
				}
				//返回结果参数补全
				page.setPageNo(reportPurchaseQuery.getPageNo());
				page.setPageSize(reportPurchaseQuery.getPageSize());
				page.setTotal(count);
			}
		}
		return page;
	}

	@Override
	public List<ReportPurchaseMaterResp> list2(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		// TODO Auto-generated method stub
				List<ReportPurchaseMaterResp> rs = null;
				if (reportPurchaseQuery !=null) {
					ReportPurchase query = queryParam(reportPurchaseQuery,false);
					if (query !=null) {
						rs = reportPurchaseMapper.selectByConditionForMatercg(query);
					}
					
				}
				if( CollectionUtils.isNotEmpty(rs) ){
					rs =new ArrayList<ReportPurchaseMaterResp>();
				}
				return rs;
	}
	@Override
	public PaginationVO<ReportPurchaseResp> page3(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		PaginationVO<ReportPurchaseResp> page = null;
		if (reportPurchaseQuery !=null) {
			ReportPurchase query = queryParam(reportPurchaseQuery,true);
			if (query !=null) {
				page  = new PaginationVO<ReportPurchaseResp>();
				Long count = reportPurchaseMapper.countByConditionForMaterSignPersonName(query);
				if (count >0) {
					page.setList(copyBeanList2RespList(reportPurchaseMapper.selectByConditionForMaterSignPersonName(query)));
				}
				//返回结果参数补全
				page.setPageNo(reportPurchaseQuery.getPageNo());
				page.setPageSize(reportPurchaseQuery.getPageSize());
				page.setTotal(count);
			}
		}
		return page;
	}

	@Override
	public List<ReportPurchaseResp> list3(ReportPurchaseQuery reportPurchaseQuery) throws Exception {
		// TODO Auto-generated method stub
		List<ReportPurchaseResp> rs = null;
		if(reportPurchaseQuery != null){
			//参数转换
			ReportPurchase query= queryParam(reportPurchaseQuery,false);
			
			if( query !=null ){
				//查询结果转换
				rs=copyBeanList2RespList( reportPurchaseMapper.selectByConditionForMaterSignPersonName(query));
			}
		}
		if( CollectionUtils.isNotEmpty(rs) ){
			rs =new ArrayList<ReportPurchaseResp>();
		}
		return rs;
	}




	



}