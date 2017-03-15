package com.tianrui.service.impl.quality.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.quality.purchase.IPurchaseAssayService;
import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.api.resp.quality.purchase.PurchaseAssayResp;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.quality.purchase.PurchaseAssay;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseAssayMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseAssayService implements IPurchaseAssayService {
	@Resource
	private PurchaseAssayMapper purchaseAssayMapper;
	@Resource
	private SystemUserMapper systemUserMapper;
	@Resource
	private QualitySchemeMapper qualitySchemeMapper;
	

	@Override
	public Result delete(PurchaseAssayReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			PurchaseAssay assay = new PurchaseAssay();
			PropertyUtils.copyProperties(assay, req);
			assay.setState("0");
			assay.setModifier(req.getUser());
			assay.setModifytime(System.currentTimeMillis());
			assay.setUtc(System.currentTimeMillis());
			int index = purchaseAssayMapper.updateByPrimaryKeySelective(assay);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result add(PurchaseAssayReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PurchaseAssay assay = new PurchaseAssay();
			PropertyUtils.copyProperties(assay, req);
			assay.setId(UUIDUtil.getId());
			assay.setState("1");
			assay.setCreator(req.getUser());
			assay.setModifier(req.getUser());
			assay.setModifytime(System.currentTimeMillis());
			assay.setUtc(System.currentTimeMillis());
			int index = purchaseAssayMapper.insertSelective(assay);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result update(PurchaseAssayReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			PurchaseAssay assay = new PurchaseAssay();
			PropertyUtils.copyProperties(assay, req);
			assay.setModifier(req.getUser());
			assay.setModifytime(System.currentTimeMillis());
			assay.setUtc(System.currentTimeMillis());
			int index = purchaseAssayMapper.updateByPrimaryKeySelective(assay);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(PurchaseAssayReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<PurchaseAssayResp> pagevo = new PaginationVO<PurchaseAssayResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			int count = purchaseAssayMapper.count(req);
			pagevo.setPageNo(pageNo);pagevo.setPageSize(pageSize);pagevo.setTotal(count);
			List<PurchaseAssayResp> resps = new ArrayList<PurchaseAssayResp>();
			if(count>0){
				List<PurchaseAssay> list = purchaseAssayMapper.page(req);
				for(PurchaseAssay assay :list){
					PurchaseAssayResp resp = new PurchaseAssayResp();
					PropertyUtils.copyProperties(resp, assay);
					if(StringUtils.isNotBlank(assay.getCreator())){
						SystemUser user = systemUserMapper.selectByPrimaryKey(assay.getCreator());
						resp.setCreator(user.getName());
					}
					if(StringUtils.isNotBlank(assay.getQschemeid())){
						QualityScheme qs = qualitySchemeMapper.selectByPrimaryKey(assay.getQschemeid());
						resp.setQschemename(qs.getName());
					}
					resps.add(resp);
				}
				
			}
			pagevo.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(pagevo);
		}
		return rs;
	}

}
