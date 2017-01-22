package com.tianrui.service.impl.basicFile.other;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.other.IOtherBdSupplierService;
import com.tianrui.api.req.basicFile.other.OtherBdSupplierReq;
import com.tianrui.api.resp.basicFile.other.OtherBdSupplierResp;
import com.tianrui.service.bean.basicFile.other.OtherBdSupplier;
import com.tianrui.service.mapper.basicFile.other.OtherBdSupplierMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class OtherBdSupplierService implements IOtherBdSupplierService {

	@Resource
	private OtherBdSupplierMapper otherBdSupplierMapper;

	@Override
	public Result page(OtherBdSupplierReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			//获取当前页数
			int pageNo = req.getPageNo();
			//获取显示 数量
			int pageSize = req.getPageSize();
			//设置开始位置
			req.setStart((pageNo-1)*pageSize);
			PaginationVO<OtherBdSupplierResp> vo = new PaginationVO<OtherBdSupplierResp>();
			List<OtherBdSupplierResp> resps = new ArrayList<OtherBdSupplierResp>();
			//获取数据总数
			int count = otherBdSupplierMapper.count(req);
			//数据总数大于0时,查询数据
			if(count>0){
				List<OtherBdSupplier> list = otherBdSupplierMapper.page(req);
				resps = Beans2Resps(list);
			}
			
			vo.setTotal(count);
			vo.setPageNo(pageNo);
			vo.setPageSize(pageSize);
			vo.setList(resps);
			//操作成功
			rs = Result.getSuccessResult();
			rs.setData(vo);
		}
		return rs;
	}
	@Transactional
	@Override
	public Result add(OtherBdSupplierReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			OtherBdSupplier o = new OtherBdSupplier();
			//类型转换
			PropertyUtils.copyProperties(o, req);
			o.setId(UUIDUtil.getId());
			o.setCreator(req.getUserName());
			o.setCreatetime(System.currentTimeMillis());
			o.setModifier(req.getUserName());
			o.setModifytime(System.currentTimeMillis());
			
			int num = otherBdSupplierMapper.insertSelective(o);
			//通过返回值判断操作是否成功
			if(num==1){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	@Transactional
	@Override
	public Result update(OtherBdSupplierReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			OtherBdSupplier o = new OtherBdSupplier();
			//类型转换
			PropertyUtils.copyProperties(o, req);
			o.setModifier(req.getUserName());
			o.setModifytime(System.currentTimeMillis());
			int num = otherBdSupplierMapper.updateByPrimaryKeySelective(o);
			//通过返回值判断操作是否成功
			if(num==1){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	@Transactional
	@Override
	public Result delete(OtherBdSupplierReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		//req的id不能为空
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//删除数据
			int num = otherBdSupplierMapper.deleteByPrimaryKey(req.getId());
			//通过返回值判断操作是否成功
			if(num==1){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	//集合 类型转换
	private List<OtherBdSupplierResp> Beans2Resps(List<OtherBdSupplier> list) throws Exception{
		List<OtherBdSupplierResp> resps = new ArrayList<OtherBdSupplierResp>();
		if(CollectionUtils.isNotEmpty(list)){
			for(OtherBdSupplier o : list){
				OtherBdSupplierResp resp = new OtherBdSupplierResp();
				PropertyUtils.copyProperties(resp,o);
				resps.add(resp);
			}
		}
		return resps;

	}
}
