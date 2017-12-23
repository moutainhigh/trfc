package com.tianrui.service.mapper.businessManage.purchaseManage;
import java.util.List;

import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.service.bean.businessManage.purchaseManage.PushSingle;
/**
 * 
  * <p>Title:PushSingleMapper </p>
  * <p>Description:TODO </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年11月16日 上午9:23:42
 */
public interface PushSingleMapper {
	
		PushSingle selectByPrimaryKey(String id);
		
		int deleteByPrimaryKey(String id);

	    int insert(PushSingleReq req);

	    int insertSelective(PushSingleReq req);

	    int updateByPrimaryKeySelective(PushSingle req);

	    int updateByPrimaryKey(PushSingleReq req);
	    
	    List<PushSingle> findReasonFailure(PushSingleReq req );
	    
	    long findPushSinglePageCount(PushSingleReq req);
	    
	    List<PushSingle> findPushSinglePage(PushSingleReq req);
		
}
