package com.tianrui.service.mapper.businessManage.report;

import java.util.List;

import com.tianrui.api.resp.businessManage.report.InOutDaoPoundMaterResp;
import com.tianrui.service.bean.businessManage.report.InOutDaoPound;

public interface OutSalesPoundMapper {

	/**
	 * 根据条件查找数据
	 * @param inOutDaoPound
	 * @return
	 */
	 List<InOutDaoPound> selectByCondition(InOutDaoPound inOutDaoPound);
	 /**
	  * 根据条件查找条数
	  * @param inOutDaoPound
	  * @return
	  */
	 Long countByCondition(InOutDaoPound inOutDaoPound);
	 
}
