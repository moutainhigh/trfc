package com.tianrui.service.mapper.businessManage.report;

import java.util.List;

import com.tianrui.api.resp.businessManage.report.InOutDaoPoundMaterResp;
import com.tianrui.service.bean.businessManage.report.InOutDaoPound;

public interface InOutDaoPoundMapper {

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
	 /**
		 * 调拨物料调入堆场统计（查找数据）
		 * @param inOutDaoPound
		 * @return
		 */
	 List<InOutDaoPoundMaterResp> selectByConditionForMater(InOutDaoPound inOutDaoPound);
	 /**
	  * 调拨物料调入堆场统计（查找条数）
	  * @param inOutDaoPound
	  * @return
	  */
	 Long countByConditionForMater(InOutDaoPound inOutDaoPound);
	 /**
		 * 调拨物料调出堆场统计（查找数据）
		 * @param inOutDaoPound
		 * @return
		 */
	 List<InOutDaoPoundMaterResp> selectByConditionForMaterDC(InOutDaoPound inOutDaoPound);
	 /**
	  * 调拨物料调出堆场统计（查找条数）
	  * @param inOutDaoPound
	  * @return
	  */
	 Long countByConditionForMaterDC(InOutDaoPound inOutDaoPound);
	 /**
		 * 调拨物料车号统计（查找数据）
		 * @param inOutDaoPound
		 * @return
		 */
	 List<InOutDaoPoundMaterResp> selectByConditionForMaterVehicleno(InOutDaoPound inOutDaoPound);
	 /**
	  * 调拨物料车号统计（查找条数）
	  * @param inOutDaoPound
	  * @return
	  */
	 Long countByConditionForMaterVehicleno(InOutDaoPound inOutDaoPound);
	 /**
		 * 调拨物料调入调出堆场统计（查找数据）
		 * @param inOutDaoPound
		 * @return
		 */
	 List<InOutDaoPoundMaterResp> selectByConditionForMaterDrDc(InOutDaoPound inOutDaoPound);
	 /**
	  * 调拨物料调入调出堆场统计（查找条数）
	  * @param inOutDaoPound
	  * @return
	  */
	 Long countByConditionForMaterDrDc(InOutDaoPound inOutDaoPound);
}
