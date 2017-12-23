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
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 推单管理
 * <p>
 * Title:PushSingleService
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author yangbobo
 * @date 2017年11月16日 下午12:54:14
 */
@Service
public class PushSingleService implements IPushSingleService {
	@Autowired
	private PushSingleMapper pushSingleMapper;

	/**
	 * 推单管理分页查询
	 */
	@Override
	public PaginationVO<PushSingleResp> page(PushSingleReq req) throws Exception {
		PaginationVO<PushSingleResp> page = null;
		if (req != null) {
			page = new PaginationVO<PushSingleResp>();
			req.setStart(1);
			long count = pushSingleMapper.findPushSinglePageCount(req);
			if (count > 0) {
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
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
		if (list != null && list.size() > 0) {
			listResp = new ArrayList<PushSingleResp>();
			for (PushSingle pushSingle : list) {
				listResp.add(copyBean2Resp(pushSingle));
			}
		}
		return listResp;
	}

	private PushSingleResp copyBean2Resp(PushSingle bean) throws Exception {
		PushSingleResp resp = null;
		if (bean != null) {
			resp = new PushSingleResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result savePushSingle(PushSingleReq pushSingle) throws Exception {
		Result rs = Result.getParamErrorResult();
		// TODO Auto-generated method stub
		PushSingleReq pr = new PushSingleReq();
		pr.setRequisitionNum(pushSingle.getRequisitionNum());// 申请单号
		pr.setNoticeNum(pushSingle.getNoticeNum());// 通知单号
		pr.setRequisitionType(pushSingle.getRequisitionType());// 申请单类型
		pr.setDesc2(pushSingle.getDesc2());// 业务类型
		pr.setDesc3(pushSingle.getDesc3());//入库单号or出库单号(单据类型所对应的单据号码)
		List<PushSingle> pushList = pushSingleMapper.findReasonFailure(pr);// 查询该日志
		if (pushList != null && !pushList.isEmpty()) {// 判断该日志是否存在
			// 该日志已存在 修改该条日志
			for (PushSingle push : pushList) {
				push.setRequisitionNum(pushSingle.getRequisitionNum());
				push.setNoticeNum(pushSingle.getNoticeNum());
				push.setPushStatus(pushSingle.getPushStatus());
				push.setRequisitionType(pushSingle.getRequisitionType());
				push.setReasonFailure(pushSingle.getReasonFailure());
				push.setLightCarTime(pushSingle.getLightCarTime());
				push.setHeavyCarTime(pushSingle.getHeavyCarTime());
				push.setNetWeight(pushSingle.getNetWeight());
				push.setCreatetime(pushSingle.getCreatetime());
				push.setModifytime(pushSingle.getModifytime());
				push.setDesc2(pushSingle.getDesc2());
				push.setDesc1(pushSingle.getDesc1());
				push.setDesc3(pushSingle.getDesc3());
				push.setCreator(pushSingle.getCreator());
				push.setModifier(pushSingle.getModifier());
				int a = pushSingleMapper.updateByPrimaryKeySelective(push);
				if (a != 1) {
					rs.setErrorCode(ErrorCode.lOGE_UPDATE_ERROR);
				}
			}

		} else {
			// 该日志不存在，新增一条日志
			int a = pushSingleMapper.insertSelective(pushSingle);
			if (a != 1) {
				rs.setErrorCode(ErrorCode.lOGE_SAVE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result updatePushSingle(PushSingleReq pushSingle) throws Exception {
		Result rs = Result.getSuccessResult();
		PushSingleReq p = new PushSingleReq();
		p.setRequisitionNum(pushSingle.getRequisitionNum());
		p.setNoticeNum(pushSingle.getNoticeNum());
		List<PushSingle> pushList = pushSingleMapper.findReasonFailure(p);
		if (pushList != null && !pushList.isEmpty()) {
			for (PushSingle push : pushList) {
				push.setRequisitionNum(pushSingle.getRequisitionNum());
				push.setNoticeNum(pushSingle.getNoticeNum());
				push.setPushStatus(pushSingle.getPushStatus());
				push.setRequisitionType(pushSingle.getRequisitionType());
				push.setReasonFailure(pushSingle.getReasonFailure());
				push.setLightCarTime(pushSingle.getLightCarTime());
				push.setHeavyCarTime(pushSingle.getHeavyCarTime());
				push.setNetWeight(pushSingle.getNetWeight());
				push.setCreatetime(pushSingle.getCreatetime());
				push.setModifytime(pushSingle.getModifytime());
				push.setDesc3(pushSingle.getDesc3());
				push.setDesc2(pushSingle.getDesc2());
				push.setDesc1(pushSingle.getDesc1());
				push.setCreator(pushSingle.getCreator());
				push.setModifier(pushSingle.getModifier());
				int a = pushSingleMapper.updateByPrimaryKeySelective(push);
				if (a != 1) {
					rs.setErrorCode(ErrorCode.lOGE_UPDATE_ERROR);
				}
			}
		} else {
			rs.setErrorCode(ErrorCode.lOGE_SELECT_ERROR);
		}
		return rs;
	}

	@Override
	public Result findPushSingle(PushSingleReq pushSingle) throws Exception {
		Result rs = Result.getSuccessResult();
		List<PushSingle> push = pushSingleMapper.findReasonFailure(pushSingle);
		if (push != null && !push.isEmpty()) {
			rs.setData(push);
		} else {
			rs.setErrorCode(ErrorCode.lOGE_SELECT_ERROR);
		}
		return rs;
	}

}
