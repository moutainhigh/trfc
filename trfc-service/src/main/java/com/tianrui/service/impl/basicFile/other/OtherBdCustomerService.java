package com.tianrui.service.impl.basicFile.other;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.other.IOtherBdCustomerService;
import com.tianrui.api.req.basicFile.other.OtherBdCustomerReq;
import com.tianrui.api.resp.basicFile.other.OtherBdCustomerResp;
import com.tianrui.service.bean.basicFile.other.OtherBdCustomer;
import com.tianrui.service.mapper.basicFile.other.OtherBdCustomerMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class OtherBdCustomerService implements IOtherBdCustomerService {


	@Resource
	private OtherBdCustomerMapper otherBdCustomerMapper;
	/**
	 * 获取列表数据
	 */
	@Override
	public Result findCustomerByPage(OtherBdCustomerReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<OtherBdCustomerResp> pageVO = new PaginationVO<OtherBdCustomerResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			req.setOrdering("createtime");
			req.setSorting("desc");
			int count = otherBdCustomerMapper.count(req);
			pageVO.setTotal(count);
			//判断数据总数为>=0时
			if(count>=0){
				List<OtherBdCustomer> list = new ArrayList<OtherBdCustomer>();
				//判断数据总数为大于0时,则通过page()获取数据,等于0是不获取 
				if(count>0){
					list = otherBdCustomerMapper.page(req);
				}
				try {
					pageVO.setList(copyBeanList2RespList(list));
				} catch (Exception e) {
					e.printStackTrace();
					result.setErrorCode(ErrorCode.PARAM_ERROR);
					return result;
				}
				pageVO.setPageNo(req.getPageNo());
				pageVO.setPageSize(req.getPageSize());
				//成功后  重新赋值
				result = Result.getSuccessResult();
				result.setData(pageVO);
			}
		}
		return result;
	}
	/**
	 * 集合转换
	 * @param List<OtherBdCustomer>
	 * @return List<OtherBdCustomerResp>
	 * @throws Exception
	 */
	private List<OtherBdCustomerResp> copyBeanList2RespList(List<OtherBdCustomer> list) throws Exception{
		List<OtherBdCustomerResp> resp = new ArrayList<OtherBdCustomerResp>();
		if(list!=null){
			for(OtherBdCustomer o:list){
				OtherBdCustomerResp r = copyBean(o);
				resp.add(r);
			}
		}
		return resp;
	}
	/**
	 * 类型转换
	 */
	private OtherBdCustomerResp copyBean(OtherBdCustomer o) throws Exception{

		OtherBdCustomerResp resp = new OtherBdCustomerResp();
		PropertyUtils.copyProperties(resp, o);
		return resp;
	}
	//新增数据
	@Transactional
	@Override
	public Result insertCustomer(OtherBdCustomerReq req){
		Result result = Result.getParamErrorResult();
		if(req!=null){
			//添加创建时间
			OtherBdCustomer tomer = new OtherBdCustomer();
			try {
				PropertyUtils.copyProperties(tomer, req);
			} catch (Exception e) {
				e.printStackTrace();
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			tomer.setCreatetime(System.currentTimeMillis());
			tomer.setId(getCustomerId());
			//假设一个creator
			tomer.setCreator("LXY");
			int index = otherBdCustomerMapper.insertSelective(tomer);
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}
	@Transactional
	@Override
	public Result updateCustomer(OtherBdCustomerReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null){
			OtherBdCustomer tomer = new OtherBdCustomer();
			//添加修改时间
			try {
				PropertyUtils.copyProperties(tomer, req);
			} catch (Exception e) {
				e.printStackTrace();
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}

			tomer.setModifytime(System.currentTimeMillis());
			int index = otherBdCustomerMapper.updateByPrimaryKeySelective(tomer);
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}
	@Transactional
	//删除一条数据
	@Override
	public Result deleteCustomer(OtherBdCustomerReq req){
		Result result = Result.getParamErrorResult();
		if(req!=null){
			String id = req.getId();
			OtherBdCustomer o = otherBdCustomerMapper.selectByPrimaryKey(id);
			if(o==null){
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			int index = otherBdCustomerMapper.deleteByPrimaryKey(id);
			if(index<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}
	//获得id
	public String getCustomerId(){
		return UUIDUtil.getId();
	}
	//获得code
	public String getCustomerCode(){
		return "CD"+(int)(Math.random()*10000);
	}

	//获得innercode
	public String getCustomerInnercode(){
		return "ICD"+(int)(Math.random()*10000);
	}
	//通过主键(id)查找数据
	public Result findByPrimaryKey(OtherBdCustomerReq req){
		Result result = Result.getParamErrorResult();
		if(req!=null){
			String id = req.getId();
			if(id==null||id.isEmpty()){
				return result;
			}
			OtherBdCustomer o = otherBdCustomerMapper.selectByPrimaryKey(id);
			if(o==null){
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			//类型 转换
			OtherBdCustomerResp resp = new OtherBdCustomerResp();
			try {
				PropertyUtils.copyProperties(resp, o);
			} catch (Exception e) {
				e.printStackTrace();
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			result = Result.getSuccessResult();
			result.setData(resp);
		}
		return result;
	}
	//检测名字
	@Override
	public Result checkName(OtherBdCustomerReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null){
			int count = otherBdCustomerMapper.count(req);
			if(count<0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			result = Result.getSuccessResult();
			result.setData(count==0);
		}
		return result;
	}


}
