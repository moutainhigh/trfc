package com.tianrui.service.impl.quality.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.quality.purchase.IPurchaseSamplingService;
import com.tianrui.api.req.quality.purchase.PurchaseSamplingReq;
import com.tianrui.api.resp.quality.purchase.PurchaseSamplingResp;
import com.tianrui.service.bean.quality.purchase.PurchaseSampling;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class PurchaseSamplingService implements IPurchaseSamplingService {
	
	@Resource
	private PurchaseSamplingMapper purchaseSamplingMapper;
	@Resource
	private SystemUserMapper systemUserMapper;
	@Resource
	private SystemDataDictItemMapper systemDataDictItemMapper;
	
	@Override
	public Result delete(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			PurchaseSampling ps = new PurchaseSampling();
			PropertyUtils.copyProperties(ps, req);
			//设置为删除状态
			ps.setState("0");
			ps.setModifier(req.getUser());
			ps.setModifytime(System.currentTimeMillis());
			ps.setUtc(System.currentTimeMillis());
			//更新到数据库
			int index = purchaseSamplingMapper.updateByPrimaryKeySelective(ps);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result add(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PurchaseSampling ps = new PurchaseSampling();
			PropertyUtils.copyProperties(ps, req);
			ps.setId(UUIDUtil.getId());
			ps.setCreator(req.getUser());
			ps.setModifier(req.getUser());
			ps.setModifytime(System.currentTimeMillis());
			ps.setUtc(System.currentTimeMillis());
			ps.setState("1");
			int index = purchaseSamplingMapper.insertSelective(ps);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result update(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			PurchaseSampling ps = new PurchaseSampling();
			PropertyUtils.copyProperties(ps, req);
			ps.setModifier(req.getUser());
			ps.setModifytime(System.currentTimeMillis());
			ps.setUtc(System.currentTimeMillis());
			//更新到数据库
			int index = purchaseSamplingMapper.updateByPrimaryKeySelective(ps);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<PurchaseSamplingResp> page = new PaginationVO<PurchaseSamplingResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			int total = purchaseSamplingMapper.count(req);
			page.setTotal(total);
			List<PurchaseSamplingResp> resps = new ArrayList<PurchaseSamplingResp>();
			if(total>0){
				List<PurchaseSampling> list = purchaseSamplingMapper.page(req);
				for(PurchaseSampling ps : list){
					PurchaseSamplingResp resp = new PurchaseSamplingResp();
					PropertyUtils.copyProperties(resp, ps);
					//获取采样类型
					if(StringUtils.isNotBlank(resp.getAssaytype())){
						SystemDataDictItem item = systemDataDictItemMapper.selectByPrimaryKey(resp.getAssaytype());
						if(item!=null){
							resp.setAssayname(item.getName());
						}
					}
					//获取用户名
					if(StringUtils.isNotBlank(resp.getCreator())){
						SystemUser su = systemUserMapper.selectByPrimaryKey(resp.getCreator());
						if(su!=null){
							resp.setCreator(su.getName());
						}
					}
					resps.add(resp);
				}
			}
			page.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}

}
