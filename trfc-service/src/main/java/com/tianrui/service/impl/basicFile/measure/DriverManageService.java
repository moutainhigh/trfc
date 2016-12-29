package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.req.basicFile.measure.DriverManageReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class DriverManageService implements IDriverManageService {
	
	@Autowired
	private DriverManageMapper driverManageMapper;

	@Override
	public PaginationVO<DriverManageResp> page(DriverManageReq req) throws Exception {
		PaginationVO<DriverManageResp> page = null;
		if(req != null){
			page = new PaginationVO<DriverManageResp>();
			long count = driverManageMapper.findDriverPageCount(req);
			if(count > 0){
				req.setState("1");
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<DriverManage> list = driverManageMapper.findDriverPage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public int addDriver(DriverManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			DriverManage driver = new DriverManage();
			driver.setIdentityno(req.getIdentityno());
			List<DriverManage> list = driverManageMapper.selectSelective(driver);
			if(list != null && list.size() > 0){
				return -1;
			}
			PropertyUtils.copyProperties(driver, req);
			driver.setId(UUIDUtil.getId());
//			driver.setCreator("");
			driver.setCreatetime(System.currentTimeMillis());
//			driver.setModifier("");
			driver.setModifytime(System.currentTimeMillis());
			n = driverManageMapper.insertSelective(driver);
		}
		return n;
	}

	@Override
	public int updateDriver(DriverManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			DriverManage driver = new DriverManage();
			PropertyUtils.copyProperties(driver, req);
//			req.setModifier("");
			req.setModifytime(System.currentTimeMillis());
			n = driverManageMapper.updateByPrimaryKeySelective(driver);
		}
		return n;
	}

	@Override
	public int deleteDriver(String id) {
		if(StringUtils.isNotBlank(id)){
			return driverManageMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}
	
	@Override
	public int delDriver(DriverManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			DriverManage driver = new DriverManage();
			PropertyUtils.copyProperties(driver, req);
			driver.setState("0");
//			req.setModifier("");
			req.setModifytime(System.currentTimeMillis());
			n = driverManageMapper.updateByPrimaryKeySelective(driver);
		}
		return n;
	}

	private List<DriverManageResp> copyBeanList2RespList(List<DriverManage> list) throws Exception {
		List<DriverManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<DriverManageResp>();
			for(DriverManage driver : list){
				listResp.add(copyBean2Resp(driver));
			}
		}
		return listResp;
	}
	
	private DriverManageResp copyBean2Resp(DriverManage bean) throws Exception {
		DriverManageResp resp = null;
		if(bean != null){
			resp = new DriverManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}