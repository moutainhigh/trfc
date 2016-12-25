package com.tianrui.service.impl.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.demo.IDemoService;
import com.tianrui.api.req.demo.DemoReq;
import com.tianrui.api.resp.demo.DemoResp;
import com.tianrui.service.bean.demo.Demo;
import com.tianrui.service.mapper.DemoMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class DemoService implements IDemoService {

	@Autowired
	private DemoMapper demoMapper;

	@Override
	public PaginationVO<DemoResp> page(DemoReq req) throws Exception {
		PaginationVO<DemoResp> page = null;
		if (req != null && req.getPageNo() > 0) {
			page =new PaginationVO<DemoResp>();
			
			Demo query =new Demo();
			long total = demoMapper.countByCondition(query);
			if (total > 0) {
				query.setStart((req.getPageNo() - 1) * req.getPageSize());
				query.setLimit(req.getPageSize());
				page.setList(conver2DemosResp(demoMapper.selectByCondition(query)));
			}
			page.setTotal(total);
			page.setPageNo(req.getPageNo());
		}
		return page;
	}
	
	
	
	@Override
	public Result saveDemo(DemoReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurrId())){
			Demo demo =new Demo();
			demo.setId(UUIDUtil.getId());
			demo.setCreatetime(System.currentTimeMillis());
			demo.setCreator(req.getCurrId());
			demo.setModifier(req.getCurrId());
			demo.setModifytime(System.currentTimeMillis());
			demoMapper.insert(demo);
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}




	@Override
	public Result delDemo(DemoReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		if( req !=null && StringUtils.isNotBlank(req.getCurrId())&& StringUtils.isNotBlank(req.getId()) ){
			
			demoMapper.deleteByPrimaryKey(req.getId());
		}else{
			rs.setErrorCode(ErrorCode.PARAM_ERROR);
		}
		return rs;
	}




	private List<DemoResp> conver2DemosResp(List<Demo> list) throws Exception{
		 List<DemoResp> rs =null;
		if( CollectionUtils.isNotEmpty(list) ){
			rs =new ArrayList<DemoResp>();
			for(Demo item:list){
				DemoResp resp =conver2DemoResp(item);
				if( resp !=null ){
					rs.add(resp);
				}
			}
		}
		return rs;
	}
	
	private DemoResp conver2DemoResp(Demo demo) throws Exception{
		DemoResp resp =null;
		if( demo!=null ){
			resp=new  DemoResp();
			PropertyUtils.copyProperties(resp, demo);
		}
		return resp;
	}
}
