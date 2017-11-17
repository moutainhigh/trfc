package com.tianrui.service.impl.businessManage.purchaseManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PushSingleResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PushSingle;
import com.tianrui.service.mapper.businessManage.purchaseManage.PushSingleMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;


/**
 * 推单管理
  * <p>Title:PushSingleService </p>
  * <p>Description:TODO </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年11月16日 下午12:54:14
 */
@Service
public class PushSingleService implements IPushSingleService{
	@Autowired
	private PushSingleMapper pushSingleMapper;
	
	/**
	 * 推单管理分页查询
	 */
	@Override
	public PaginationVO<PushSingleResp> page(PushSingleReq req) throws Exception {
		PaginationVO<PushSingleResp> page = null;
		if(req != null){
			page = new PaginationVO<PushSingleResp>();
			req.setStart(1);
			long count = pushSingleMapper.findPushSinglePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<PushSingle> list = pushSingleMapper.findPushSinglePage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}
	
	
	
	private List<PushSingleResp> copyBeanList2RespList(List<PushSingle> list) throws Exception {
		List<PushSingleResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PushSingleResp>();
			for(PushSingle pushSingle : list){
				listResp.add(copyBean2Resp(pushSingle));
			}
		}
		return listResp;
	}
	private PushSingleResp copyBean2Resp(PushSingle bean) throws Exception {
		PushSingleResp resp = null;
		if(bean != null){
			resp = new PushSingleResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}



	@Override
	public Result savePushSingle(PushSingleReq pushSingle) throws Exception {
		Result rs =Result.getSuccessResult();
		// TODO Auto-generated method stub
		int a =pushSingleMapper.insert(pushSingle);
		if(a!=1){
			rs.setCode("111111");
			rs.setError("推送管理日志保存失败！");
		}
		return rs;
	}



	@Override
	public Result updatePushSingle(PushSingleReq pushSingle) throws Exception {
		Result rs =Result.getSuccessResult();
		PushSingle push =pushSingleMapper.findReasonFailure(pushSingle);
		if(push!=null){
			pushSingle.setId(push.getId());
			int a =pushSingleMapper.updateByPrimaryKeySelective(pushSingle);
			if(a!=1){
				rs.setCode("111111");
				rs.setError("推送管理日志修改失败！");
			}
		}else{
			rs.setCode("222222");
			rs.setError("未查到要修改的数据！");
		}
		return rs;
	}



	@Override
	public Result findPushSingle(PushSingleReq pushSingle) throws Exception {
		Result rs =Result.getSuccessResult();
		PushSingle push =pushSingleMapper.findReasonFailure(pushSingle);
		if(push!=null){
			rs.setData(push);
		}else{
			rs.setCode("111111");
			rs.setError("未查到异常信息！");
		}
		return rs;
	}

}
