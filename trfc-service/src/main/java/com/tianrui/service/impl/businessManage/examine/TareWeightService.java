package com.tianrui.service.impl.businessManage.examine;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.examine.ITareWeightService;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditSaveReq;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.service.bean.businessManage.examine.ExceptionAudit;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.businessManage.examine.ExceptionAuditMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class TareWeightService implements ITareWeightService{
	
	   @Autowired
	    private ExceptionAuditMapper exceptionAuditMapper;
	    @Autowired
	    private SystemUserMapper systemUserMapper;
	    
	    @Override
	    public PaginationVO<ExceptionAuditQueryResp> page(ExceptionAuditQuery query) {
	        PaginationVO<ExceptionAuditQueryResp> page = null;
	        if (query != null) {
	            page = new PaginationVO<ExceptionAuditQueryResp>();
	            long count = exceptionAuditMapper.countByPageParams(query);
	            if (count > 0) {
	                query.setStart((query.getPageNo() - 1) * query.getPageSize());
	                query.setLimit(query.getPageSize());
	                List<ExceptionAuditQueryResp> list = exceptionAuditMapper.listByPageParams(query);
	                page.setList(list);
	            }
	            page.setPageNo(query.getPageNo());
	            page.setPageSize(query.getPageSize());
	            page.setTotal(count);
	        }
	        return page;
	    }
	    /**
	     * 皮重异常审批分页
	     */
	    @Override
	    public PaginationVO<ExceptionAuditResp> pageForInfraredBlock(ExceptionAuditQuery query) {
	    	PaginationVO<ExceptionAuditResp> page = null;
	    	if (query != null) {
	    		page = new PaginationVO<ExceptionAuditResp>();
	    		long count = exceptionAuditMapper.countByCondition(query);
	    		if (count > 0) {
	    			query.setStart((query.getPageNo() - 1) * query.getPageSize());
	    			query.setLimit(query.getPageSize());
	    			List<ExceptionAudit> list = exceptionAuditMapper.listByCondition(query);
	    			page.setList(conver2Resps(list));
	    		}
	    		page.setPageNo(query.getPageNo());
	    		page.setPageSize(query.getPageSize());
	    		page.setTotal(count);
	    	}
	    	return page;
	    }

	    @Override
	    public Result audit(ExceptionAuditReq req) {
	        Result result = Result.getParamErrorResult();
	        if (req != null && StringUtils.isNotBlank(req.getId())
	                && StringUtils.isNotBlank(req.getAuditOpinion())) {
	            ExceptionAudit bean = exceptionAuditMapper.selectByPrimaryKey(req.getId());
	            if (bean != null) {
	                if (!bean.getAuditStatus()) {
	                    bean.setAuditOpinion(req.getAuditOpinion());
	                    bean.setAuditPerson(req.getUserId());
	                    bean.setAuditTime(System.currentTimeMillis());
	                    bean.setAuditStatus(true);
	                    bean.setModifier(req.getUserId());
	                    bean.setModifytime(System.currentTimeMillis());
	                    exceptionAuditMapper.updateByPrimaryKeySelective(bean);
	                    result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
	                } else {
	                    result.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR1);
	                }
	            } else {
	                result.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR);
	            }
	        }
	        return result;
	    }
	    
	    @Override
	    public ExceptionAuditResp getById(String id) throws Exception {
	        ExceptionAuditResp resp = null;
	        if (StringUtils.isNotBlank(id)) {
	            resp = new ExceptionAuditResp();
	            ExceptionAudit bean = exceptionAuditMapper.selectByPrimaryKey(id);
	            PropertyUtils.copyProperties(resp, bean);
	            SystemUser user = systemUserMapper.selectByPrimaryKey(resp.getAuditPerson());
	            if (user != null) {
	                resp.setAuditPersonName(user.getName());
	            }
	        }
	        return resp;
	    }

	    /**
	     * 皮重异常审批申请
	     */
		@Override
		public Result apply(ExceptionAuditSaveReq req) {
			Result result = Result.getParamErrorResult();
		    if (req != null && StringUtils.isNotBlank(req.getNoticeNo()) 
		    		&& StringUtils.isNotBlank(req.getNoticeType()) ){	
		    	//查询下不能有该通知单未审批的单据
		    	List<ExceptionAudit> list =exceptionAuditMapper.listByPnId("6",req.getNoticeNo());
		    	boolean flag = true;
		    	if( CollectionUtils.isNotEmpty(list) ){
		    		for(ExceptionAudit bean :list ){
		    			if(bean.getAuditStatus()==false){
		    				flag=false;
		    			}
		    		}
		    	}
		    	if(flag){
		    		ExceptionAudit bean =new ExceptionAudit();
		    		bean.setId(UUIDUtil.getId());
		    		bean.setPnId(req.getNoticeNo());
		    		bean.setType(6);
		    		bean.setAuditStatus(false);
		    		bean.setState(true);
		    		bean.setCreator(req.getCurrUid());
		    		bean.setCreatetime(System.currentTimeMillis());
		    		bean.setRemark(req.getSeqNo());
		    		bean.setModifier(req.getCurrUid());
		    		bean.setModifytime(System.currentTimeMillis());
		    		exceptionAuditMapper.insert(bean);
		    		result.setData(bean.getId());
		    		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		    	}else{
		    		result.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR4);
		    	}
		    }
		    return result;
		}
		/**
		 * 查询皮重异常审批状态
		 */
		@Override
		public Result query(ExceptionAuditReq req) {
			Result result = Result.getParamErrorResult();
			if (req != null && StringUtils.isNotBlank(req.getId()) ) {
				ExceptionAudit bean =exceptionAuditMapper.selectByPrimaryKey(req.getId());
				if( bean !=null){
		    		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					if(bean.getAuditStatus()){
						result.setData(1);
					}else{
						result.setData(2);
						
					}
				}
			}
			return result;
		}
		
		private List<ExceptionAuditResp> conver2Resps(List<ExceptionAudit> list){
			List<ExceptionAuditResp> rs =null;
			if( CollectionUtils.isNotEmpty(list) ){
				rs =new ArrayList<ExceptionAuditResp>();
				for( ExceptionAudit bean :list ){
					if( bean !=null ){
						ExceptionAuditResp item = new ExceptionAuditResp();
						item.setId(bean.getId());
						item.setRemark(bean.getRemark());
						item.setCreatetime(bean.getCreatetime());
						item.setCreator(bean.getCreator());
						item.setAuditPerson(bean.getAuditPerson());
						item.setAuditTime(bean.getAuditTime());
						item.setAuditStatus(bean.getAuditStatus());
						item.setPnId(bean.getPnId());
						rs.add(item);
					}
				}
			}
			return rs;
		}

}
