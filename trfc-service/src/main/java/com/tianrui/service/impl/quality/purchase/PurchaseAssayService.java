package com.tianrui.service.impl.quality.purchase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.quality.purchase.IPurchaseAssayService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.quality.purchase.PurchaseAssayResp;
import com.tianrui.api.resp.quality.purchase.PurchaseSamplingItemResp;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.quality.purchase.PurchaseAssay;
import com.tianrui.service.bean.quality.purchase.PurchaseSampling;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseAssayMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingItemMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingMapper;
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
    private PurchaseSamplingMapper purchaseSamplingMapper;
    @Resource
    private PurchaseSamplingItemMapper purchaseSamplingItemMapper;
	@Resource
	private SystemUserMapper systemUserMapper;
	@Resource
	private QualitySchemeMapper qualitySchemeMapper;
	@Resource
	private ISystemCodeService systemCodeService;
	

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

    private String getCode(String code, String userId) throws Exception {
        GetCodeReq codeReq1 = new GetCodeReq();
        codeReq1.setCode(code);
        codeReq1.setCodeType(true);
        codeReq1.setUserid(userId);
        return systemCodeService.getCode(codeReq1).getData().toString();
    }
    private boolean updateCode(String code, String userId) throws Exception {
        boolean flag = false;
        GetCodeReq codeReq = new GetCodeReq();
        codeReq.setCode(code);
        codeReq.setCodeType(true);
        codeReq.setUserid(userId);
        if (StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
            flag = true; 
        }
        return flag;
    }

	@Override
	@Transactional
	public synchronized Result add(PurchaseAssayReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getSamplingid())){
		    if (req.getSamplingid().contains(",") || req.getSamplingid().contains("，")) {
		        if (!validMixed(req.getSamplingid())) {
		            rs.setErrorCode(ErrorCode.PURCHASE_MIXED_ERROR1);
		            return rs;
		        }
		    }
		    String samid = req.getSamplingid().replace("，", ",");
		    req.setSamplingid(samid);
		    String[] samplingids = req.getSamplingid().split(",");
	        List<PurchaseSampling> list =  purchaseSamplingMapper.findYesAssByIds(Arrays.asList(samplingids));
	        if (CollectionUtils.isEmpty(list)) {
	            PurchaseAssay assay = new PurchaseAssay();
	            PropertyUtils.copyProperties(assay, req);
	            assay.setId(UUIDUtil.getId());
	            assay.setCode(getCode("HY", req.getUser()));
	            assay.setState("1");
	            assay.setCreator(req.getUser());
	            assay.setModifier(req.getUser());
	            assay.setModifytime(System.currentTimeMillis());
	            assay.setUtc(System.currentTimeMillis());
	            int index = purchaseAssayMapper.insertSelective(assay);
	            if(index>0 && updateCode("HY", req.getUser())){
	                purchaseSamplingMapper.updateStatusByIds(Arrays.asList(samplingids));
	                rs.setData(assay.getCode());
	                rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
	            }else{
	                rs.setErrorCode(ErrorCode.OPERATE_ERROR);
	            }
	        } else {
	            String codes = "";
	            for (PurchaseSampling ps : list) {
	                codes += ps.getCode() + ",";
                }
	            codes = codes.substring(0, codes.length()-1);
	            rs.setErrorCode(ErrorCode.PURCHASE_MIXED_ERROR2);
	            rs.setError("该采样单" + codes + rs.getError());
	        }
		}
		return rs;
	}

	private boolean validMixed(String samplingid) {
	    boolean flag = false;
	    String[] samplingids = null;
	    if (samplingid.contains(",")) {
	        samplingids = samplingid.split(",");
	    }
	    if (samplingid.contains("，")) {
            samplingids = samplingid.split("，");
        }
	    if (samplingids != null && samplingids.length > 0) {
            List<PurchaseSamplingItemResp> list = purchaseSamplingItemMapper.findRespByPid(Arrays.asList(samplingids));
            if (CollectionUtils.isNotEmpty(list)) {
                String supplier = list.get(0).getSupplier();
                String mine = list.get(0).getMine();
                String material = list.get(0).getMaterial();
                for (int i=1;i<list.size();i++) {
                    if (StringUtils.equals(supplier, list.get(i).getSupplier()) 
                            && StringUtils.equals(mine, list.get(i).getMine()) 
                            && StringUtils.equals(material, list.get(i).getMaterial()) && StringUtils.isNotBlank(list.get(i).getMine())) {
                        flag = true;
                    } else {
                        break;
                    }
                }
            }
	    }
        return flag;
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
						if(user != null){
							resp.setCreator(user.getName());
						}
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
