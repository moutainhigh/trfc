package com.tianrui.api.intf.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveSave;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * @Description 采购通知单Service接口
 * @author zhanggaohao
 * @version 2017年2月17日 上午9:00:07
 */
public interface IPurchaseArriveService {
	/**
	 * @Description 分页查询
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:58:51
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<PurchaseArriveResp> page(PurchaseArriveQuery query) throws Exception;
	/**
	 * @Description 更新通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:59:02
	 * @param update
	 * @return
	 * @throws Exception
	 */
	Result updateOperation(PurchaseArriveSave update) throws Exception;
	/**
	 * @Description 根据id查询通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:59:16
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PurchaseArriveResp findOne(String id) throws Exception;
	/**
	 * @Description 新增通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午8:59:54
	 * @param save
	 * @return
	 * @throws Exception
	 */
	Result add(PurchaseArriveSave save) throws Exception;
	/**
	 * @Description 修改通知单
	 * @author zhanggaohao
	 * @version 2017年2月17日 上午9:07:32
	 * @param update
	 * @return
	 * @throws Exception 
	 */
	Result update(PurchaseArriveSave update) throws Exception;
	/**
	 * @Description 根据ids获取采购到货通知单列表
	 * @author zhanggaohao
	 * @version 2017年2月23日 下午3:12:02
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	List<PurchaseArriveResp> selectByIds(List<String> ids) throws Exception;

}
