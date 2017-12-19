package com.tianrui.service.impl.businessManage.report;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.report.ISalesPoundService;
import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.service.bean.businessManage.report.InOutDaoPound;
import com.tianrui.service.mapper.businessManage.report.OutSalesPoundMapper;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;



/**
 * 销售逐车明细Service
 * @author lenovo
 *
 */
@Service
public class SalesPoundService implements ISalesPoundService{
	@Resource
	private OutSalesPoundMapper outSalesPoundMapper;
	
	
	@Override
	public PaginationVO<InOutDaoPoundResp> page(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<InOutDaoPoundResp> page= null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, true);
			if (query !=null) {
				page = new PaginationVO<InOutDaoPoundResp>();
			}
			//2表示销售提货通知单
			query.setBilltype("2");
			//查询总条数
			Long count = outSalesPoundMapper.countByCondition(query);
			if (count > 0) {
				page.setList(copyBeanList2RespList(outSalesPoundMapper.selectByCondition(query)));
			}
			//返回结果参数补全
			page.setPageNo(inOutDaoPoundQuery.getPageNo());
			page.setPageSize(inOutDaoPoundQuery.getPageSize());
			page.setTotal(count);
		}
		
		return page;
	}

	@Override
	public List<InOutDaoPoundResp> list(InOutDaoPoundQuery inOutDaoPoundQuery) throws Exception {
		// TODO Auto-generated method stub
		List<InOutDaoPoundResp> rs = null;
		if (inOutDaoPoundQuery != null) {
			InOutDaoPound query = queryParam(inOutDaoPoundQuery, false);
			//2表示销售提货通知单
			query.setBilltype("2");
			if (query !=null) {
				rs = copyBeanList2RespList(outSalesPoundMapper.selectByCondition(query));
			}

		}
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
			//订单号
			if (StringUtils.isNotBlank(query.getBillcode())) {
				bean.setBillcodeLike(query.getBillcode());
			}
			//榜单号
			if (StringUtils.isNotBlank(query.getCode())) {
				bean.setCodeLike(query.getCode());
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
