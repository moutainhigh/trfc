package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.ITransportunitManageService;
import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.api.req.basicFile.measure.TransportunitManageSave;
import com.tianrui.api.resp.basicFile.measure.TransportunitManageResp;
import com.tianrui.service.bean.basicFile.measure.TransportunitManage;
import com.tianrui.service.mapper.basicFile.measure.TransportunitManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 运输单位Service
 * 
 * @author YangZhenFu
 * @createtime 2017年2月5日 上午10:07:52
 * @classname TransportunitManageService.java
 */
@Service
public class TransportunitManageService implements ITransportunitManageService{
	
	@Autowired
	private TransportunitManageMapper transportunitManageMapper;
	
	/**
	 * 分页查询数据
	 */
	@Override
	public Result page(TransportunitManageQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空校验
		if(query != null){
			PaginationVO<TransportunitManageResp> page = new PaginationVO<TransportunitManageResp>();
			//设置状态为1，查询数据总数
			query.setState("1");
			long count = transportunitManageMapper.findTransportunitPageCount(query);
			//数据总数大于0时执行分页查询
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<TransportunitManage> list = transportunitManageMapper.findTransportunitPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				//成功时保存数据
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}
		}
		return result;
	}
	
	/**
	 * 新增运输单位信息
	 */
	@Transactional
	@Override
	public Result addTransportunit(TransportunitManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		//参数不能为空校验
		if(save != null){
			TransportunitManage transportunit = new TransportunitManage();
			transportunit.setCode(save.getCode());
			transportunit.setName(save.getName());
			transportunit.setState("1");
			//查询数据库里是否有这条数据
			List<TransportunitManage> list = transportunitManageMapper.selectSelective(transportunit);
			//有的话提示错误信息，没有则执行新增
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(transportunit, save);
				transportunit.setId(UUIDUtil.getId());
				transportunit.setCreator("YZF");
				transportunit.setCreatetime(System.currentTimeMillis());
				transportunit.setModifier("YZF");
				transportunit.setModifytime(System.currentTimeMillis());
				//执行新增方法，成功时保存数据，失败时提示错误信息
				if(transportunitManageMapper.insertSelective(transportunit) > 0){
					result.setData(transportunit);
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}
	
	/**
	 * 修改运输单位信息
	 */
	@Transactional
	@Override
	public Result updateTransportunit(TransportunitManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		//参数不能为空校验
		if(save != null){
			TransportunitManage transportunit = new TransportunitManage();
			PropertyUtils.copyProperties(transportunit, save);
			save.setModifier("YZF");
			save.setModifytime(System.currentTimeMillis());
			//执行修改方法，成功时提示信息
			if(transportunitManageMapper.updateByPrimaryKeySelective(transportunit) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 删除运输单位信息
	 */
	@Transactional
	@Override
	public Result deleteTransportunit(TransportunitManageQuery query) {
		Result result=Result.getParamErrorResult();
		//参数不能为空校验
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			TransportunitManage transportunit=new TransportunitManage();
			transportunit.setId(query.getId());
			//修改运输单位状态为0，执行假删除
			transportunit.setState("0");
			//执行修改方法，成功时提示信息
			if(transportunitManageMapper.updateByPrimaryKeySelective(transportunit)>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 集合转换
	 * @param List<TransportunitManage> list
	 * @return List<TransportunitManageResp> 
	 * @throws Exception
	 */
	private List<TransportunitManageResp> copyBeanList2RespList(List<TransportunitManage> list) throws Exception {
		List<TransportunitManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<TransportunitManageResp>();
			for(TransportunitManage Transportunit : list){
				listResp.add(copyBean2Resp(Transportunit));
			}
		}
		return listResp;
	}
	
	/**
	 * 实体bean类型转换
	 * @param TransportunitManage bean
	 * @return TransportunitManageResp
	 * @throws Exception
	 */
	private TransportunitManageResp copyBean2Resp(TransportunitManage bean) throws Exception {
		TransportunitManageResp resp = null;
		if(bean != null){
			resp = new TransportunitManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}
