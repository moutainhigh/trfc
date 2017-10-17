package com.tianrui.service.impl.quality.purchase;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.quality.purchase.IPurchaseMixedService;
import com.tianrui.api.req.quality.purchase.PurchaseMixedReq;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.api.resp.quality.purchase.PurchaseMixedResp;
import com.tianrui.api.resp.quality.purchase.PurchaseSamplingResp;
import com.tianrui.service.bean.quality.purchase.PurchaseSampling;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingItemMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PurchaseMixedService implements IPurchaseMixedService {

    @Autowired
    private PurchaseSamplingMapper purchaseSamplingMapper;
    @Autowired
    private PurchaseSamplingItemMapper purchaseSamplingItemMapper;
    @Autowired
    private SystemDataDictItemMapper systemDataDictItemMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    
    @Override
    public List<HandSetReturnResp> supplierAutoComplate(String date) {
        List<HandSetReturnResp> list = null ;
        if (StringUtils.isNotBlank(date)) {
            list = purchaseSamplingItemMapper.listAutoComplateByDay(DateUtil.parse(date, DateUtil.Y_M_D), "supplierid","suppliername");
        }
        return list;
    }
    
    @Override
    public List<HandSetReturnResp> minemouthAutoComplate(String date) {
        List<HandSetReturnResp> list = null ;
        if (StringUtils.isNotBlank(date)) {
            list = purchaseSamplingItemMapper.listAutoComplateByDay(DateUtil.parse(date, DateUtil.Y_M_D), "minemouthid","minemouthname");
        }
        return list;
    }
    
    @Override
    public List<HandSetReturnResp> materialAutoComplate(String date) {
        List<HandSetReturnResp> list = null ;
        if (StringUtils.isNotBlank(date)) {
            list = purchaseSamplingItemMapper.listAutoComplateByDay(DateUtil.parse(date, DateUtil.Y_M_D), "materielid","materielname");
        }
        return list;
    }

    @Override
    public Result page(PurchaseMixedReq req) throws Exception {
        Result result = Result.getSuccessResult();
        if (req != null) {
            if (StringUtils.isNotBlank(req.getDate())) {
                req.setDay(DateUtil.parse(req.getDate(), DateUtil.Y_M_D));
            } else {
                req.setDay(DateUtil.getYesterday().getTime());
            }
            List<PurchaseSampling> list = purchaseSamplingMapper.listSampling(req);
            if (CollectionUtils.isNotEmpty(list)) {
                List<PurchaseSamplingResp> resps = new ArrayList<PurchaseSamplingResp>();
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
                result.setData(resps);
            }
        }
        return result;
    }
    
    @Override
    public Result mixedPage(PurchaseMixedReq req) throws Exception {
        Result result = Result.getParamErrorResult();
        if (req != null) {
            List<PurchaseMixedResp> list = purchaseSamplingMapper.applistSampling(req);
            result.setData(list);
            result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
        }
        return result;
    }

}
