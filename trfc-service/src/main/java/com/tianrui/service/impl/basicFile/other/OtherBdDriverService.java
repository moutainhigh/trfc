package com.tianrui.service.impl.basicFile.other;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.other.IOtherBdDriverService;
import com.tianrui.api.req.basicFile.other.OtherBdDriverReq;
import com.tianrui.api.resp.basicFile.other.OtherBdDriverResp;
import com.tianrui.service.bean.basicFile.other.OtherBdDriver;
import com.tianrui.service.mapper.basicFile.other.OtherBdDriverMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 其他司机Service
 * @author Yangzhenfu
 * @createtime 2017年1月20日 下午2:54:52
 * @classname OtherBdDriverService.java
 */
@Service
public class OtherBdDriverService implements IOtherBdDriverService{
	
	@Autowired
	private OtherBdDriverMapper otherBdDriverMapper;

	@Override
	public Result page(OtherBdDriverReq req) throws Exception {
		Result result=Result.getSuccessResult();
		if(req!=null){
			PaginationVO<OtherBdDriverResp> page=new PaginationVO<OtherBdDriverResp>();
			int pageNo=req.getPageNo();
			int pageSize=req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			long count=otherBdDriverMapper.findDriverPageCount(req);
			if(count>0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<OtherBdDriver> list = this.otherBdDriverMapper.findDriverPage(req);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(req.getPageNo());
				page.setPageSize(req.getPageSize());
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(req.getPageNo());
				page.setPageSize(req.getPageSize());
				result.setData(page);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result addDriver(OtherBdDriverReq req) throws Exception {
		Result result=Result.getSuccessResult();
		if(req!=null){
			OtherBdDriver driver=new OtherBdDriver();
			PropertyUtils.copyProperties(driver, req);
			driver.setId(UUIDUtil.getId());
			driver.setCreator("YZF");
			driver.setCreatetime(System.currentTimeMillis());
			driver.setAddr("墨西哥");
			driver.setModifytime(System.currentTimeMillis());
			int n=this.otherBdDriverMapper.insert(driver);
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result editDriver(OtherBdDriverReq req) throws Exception {
		Result result=Result.getSuccessResult();
		if(req != null){
			OtherBdDriver driver = new OtherBdDriver();
			PropertyUtils.copyProperties(driver, req);
			driver.setModifier("YZF");
			driver.setModifytime(System.currentTimeMillis());
			int n=this.otherBdDriverMapper.updateByPrimaryKeySelective(driver);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result deleteDriver(String id) {
		Result result=Result.getSuccessResult();
		if(id!=null && !id.trim().isEmpty()){
			int n=this.otherBdDriverMapper.deleteByPrimaryKey(id);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public String getDriverCode() {
		
		return "ZR"+(int)(Math.random()*100000);
	}

	@Override
	public String getDriverInnercode() {
	
		return "IZR"+(int)(Math.random()*10000);
	}

	@Override
	public Result checkName(String name) {
		Result result=Result.getSuccessResult();
		if(name!=null && !name.trim().isEmpty()){
			if(this.otherBdDriverMapper.findDriverByName(name)==0){
				result.setData(true);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;	
	}
	
	
	/**
	 * 集合转换
	 * @param List<OtherBdDriver> list
	 * @return List<OtherBdDriverResp> 
	 * @throws Exception
	 */
	private List<OtherBdDriverResp> copyBeanList2RespList(List<OtherBdDriver> list) throws Exception {
		List<OtherBdDriverResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<OtherBdDriverResp>();
			for(OtherBdDriver driver : list){
				listResp.add(copyBean2Resp(driver));
			}
		}
		return listResp;
	}
	
	/**
	 * 实体bean类型转换
	 * @param OtherBdDriver bean
	 * @return OtherBdDriverResp
	 * @throws Exception
	 */
	private OtherBdDriverResp copyBean2Resp(OtherBdDriver bean) throws Exception {
		OtherBdDriverResp resp = null;
		if(bean != null){
			resp = new OtherBdDriverResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}
