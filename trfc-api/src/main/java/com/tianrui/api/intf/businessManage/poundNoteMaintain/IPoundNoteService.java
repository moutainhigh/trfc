package com.tianrui.api.intf.businessManage.poundNoteMaintain;

import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

public interface IPoundNoteService {
	/**
	 * @Description 查询采购磅单
	 * @author zhanggaohao
	 * @version 2017年3月6日 下午2:20:53
	 * @param query
	 * @return
	 * @throws Exception 
	 */
	PaginationVO<PoundNoteResp> purchasePage(PoundNoteQuery query) throws Exception;
	
}
