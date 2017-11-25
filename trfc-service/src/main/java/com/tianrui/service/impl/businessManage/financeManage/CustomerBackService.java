package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.financeManage.ICustomerBackService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.financeManage.CustomerBackQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBackSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.financeManage.CustomerBackResp;
import com.tianrui.service.bean.businessManage.financeManage.CustomerBack;
import com.tianrui.service.mapper.businessManage.financeManage.CustomerBackMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 客户退补Service
 * @author YangZhenFu
 * @createtime 2017年3月11日 上午10:28:52
 * @classname CustomerBackService.java
 */
@Service
public class CustomerBackService implements ICustomerBackService {

	@Autowired
	private CustomerBackMapper customerBackMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	/**
	 * 分页查询
	 */
	@Override
	public Result page(CustomerBackQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		if(query!=null){
			PaginationVO<CustomerBackResp> page=new PaginationVO<CustomerBackResp>();
			query.setState("1");
			Long count=customerBackMapper.findCustomerBackPageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<CustomerBack> list=customerBackMapper.findCustomerBackPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result add(CustomerBackSave save) throws Exception {
		Result result=Result.getSuccessResult();
		if(save!=null){
			GetCodeReq code=new GetCodeReq();
			code.setCode("XSTB");
			code.setCodeType(true);
			code.setUserid(save.getUser());
			CustomerBack bean=new CustomerBack();
			bean.setCode(String.valueOf(systemCodeService.getCode(code).getData()));
			List<CustomerBack> _list=customerBackMapper.selectSelective(bean);
			if(_list!=null && _list.size()>0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			bean=new CustomerBack();
			bean.setState("1");
			bean.setCustomername(save.getCustomername());
			List<CustomerBack> list=customerBackMapper.selectSelective(bean);
			if(list!=null && list.size()>0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			bean.setAuditstatus("0");
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setMakeid(save.getUser());
			bean.setMakebilltime(System.currentTimeMillis());
			bean.setState("1");
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			bean.setUtc(System.currentTimeMillis());
			if(customerBackMapper.insertSelective(bean)>0){
				result.setData(bean);
				//使编号自增
				if(!StringUtils.equals(systemCodeService.updateCodeItem(code).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result audit(CustomerBackQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			CustomerBack bean=new CustomerBack();
			bean.setId(query.getId());
			//已审核
			bean.setAuditstatus("1");
			bean.setAuditid(query.getAuditid());
			bean.setAuditname(query.getAuditname());
			bean.setAudittime(System.currentTimeMillis());
			bean.setModifier(query.getAuditid());
			bean.setModifytime(System.currentTimeMillis());
			if(customerBackMapper.updateByPrimaryKeySelective(bean)>0){
				result.setData("操作成功!");
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
	public Result delete(CustomerBackQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			CustomerBack bean=new CustomerBack();
			bean.setId(query.getId());
			bean.setState("0");
			bean.setModifier(query.getUser());
			bean.setModifytime(System.currentTimeMillis());
			if(customerBackMapper.updateByPrimaryKeySelective(bean)>0){
				result.setData("操作成功!");
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
	 * @param List<CustomerBack> list
	 * @return List<CustomerBackResp> 
	 * @throws Exception
	 */
	private List<CustomerBackResp> copyBeanList2RespList(List<CustomerBack> list) throws Exception {
		List<CustomerBackResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<CustomerBackResp>();
			for(CustomerBack Back : list){
				listResp.add(copyBeanList2RespList(Back));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param CustomerBack bean
	 * @return CustomerBackResp
	 * @throws Exception
	 */
	private CustomerBackResp copyBeanList2RespList(CustomerBack bean) throws Exception {
		CustomerBackResp resp = null;
		if(bean != null){
			resp = new CustomerBackResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
