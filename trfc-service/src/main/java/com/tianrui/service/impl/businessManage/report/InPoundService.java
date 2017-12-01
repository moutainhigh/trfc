package com.tianrui.service.impl.businessManage.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.report.IInPoundService;
import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.service.bean.businessManage.report.InOutDaoPound;
import com.tianrui.service.mapper.businessManage.report.InOutDaoPoundMapper;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
@Service
public class InPoundService implements IInPoundService{
	@Resource
	private InOutDaoPoundMapper inOutDaoPoundMapper;
	/**
	 * 分页查询
	 */
	
	@Override
	public  PaginationVO<InOutDaoPoundResp> page(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		PaginationVO<InOutDaoPoundResp> page = null;
		if (inOutDaoPoundQuery !=null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query != null) {
				page = new PaginationVO<InOutDaoPoundResp>();
				//5 表示其他入库
				query.setBilltype("5");
				//查询总条数
				Long count = inOutDaoPoundMapper.countByCondition(query);
				if (count>0) {
					page.setList(copyBeanList2RespList(inOutDaoPoundMapper.selectByCondition(query)));
				}
				//返回结果参数补全
				page.setPageNo(inOutDaoPoundQuery.getPageNo());
				page.setPageSize(inOutDaoPoundQuery.getPageSize());
				page.setTotal(count);
			}
		}
		return page;
	}
	/**
	 * 返回全部结果
	 */
	@Override
	public List<InOutDaoPoundResp> list(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		List<InOutDaoPoundResp> rs = null;
		if(inOutDaoPoundQuery != null){
			//参数转换
			InOutDaoPound query= queryParam(inOutDaoPoundQuery,false);
			//5 表示其他入库
			query.setBilltype("5");
			if( query !=null ){
				//查询结果转换
				rs=copyBeanList2RespList( inOutDaoPoundMapper.selectByCondition(query));
			}
		}
		/*if( CollectionUtils.isNotEmpty(rs) ){
			rs =new ArrayList<InOutDaoPoundResp>();
		}*/
		return rs;
	}

	/**
	 * 查询参数拼接
	 * @param query
	 * @param bb 当bb=true时调用分页，bb=false时，不调用分页
	 * @return
	 */
	private InOutDaoPound queryParam(InOutDaoPoundQuery query,boolean bb){
		InOutDaoPound bean =null;
		if(query!=null){
			bean =new InOutDaoPound();
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
			//客户
			if( StringUtils.isNotBlank(query.getCustomername()) ){
				bean.setCustomerNameLike(query.getCustomername());
			}		
			if (bb==true) {
				//分页参数
				bean.setStart((query.getPageNo()-1)*query.getPageSize());
				bean.setLimit(query.getPageSize());
			}
			
		}
		return bean;
		
	}

	//返回对象拼装
	private List<InOutDaoPoundResp> copyBeanList2RespList(List<InOutDaoPound> list){
		List<InOutDaoPoundResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs=new ArrayList<InOutDaoPoundResp>();
			for(InOutDaoPound item:list){
				if( item !=null ){
					InOutDaoPoundResp itemResp = new InOutDaoPoundResp();
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
}
