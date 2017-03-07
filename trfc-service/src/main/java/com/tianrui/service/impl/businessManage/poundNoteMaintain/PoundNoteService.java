package com.tianrui.service.impl.businessManage.poundNoteMaintain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class PoundNoteService implements IPoundNoteService {

	@Autowired
	private PoundNoteMapper poundNoteMapper;
	
	@Override
	public PaginationVO<PoundNoteResp> purchasePage(PoundNoteQuery query) throws Exception {
		PaginationVO<PoundNoteResp> page = null;
		if(query != null){
			page = new PaginationVO<PoundNoteResp>();
			query.setState("1");
			long count = poundNoteMapper.purchasePageCount(query);
			if(count > 0){
				List<PoundNote> list = poundNoteMapper.purchasePage(query);
				page.setList(copyBeanList2RespList(list, false));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	private List<PoundNoteResp> copyBeanList2RespList(List<PoundNote> list, boolean setApplication) throws Exception {
		List<PoundNoteResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<PoundNoteResp>();
			for(PoundNote sa : list){
				listResp.add(copyBean2Resp(sa, setApplication));
			}
		}
		return listResp;
	}
	
	private PoundNoteResp copyBean2Resp(PoundNote bean, boolean setApplication) throws Exception {
		PoundNoteResp resp = null;
		if(bean != null){
			resp = new PoundNoteResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setApplication){
			}
		}
		return resp;
	}

}
