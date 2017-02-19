package com.tianrui.api.intf.businessManage.purchaseManage;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationQuery;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationJoinDetailResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
/**
 * @Description 采购通知单Service接口
 * @author zhanggaohao
 * @version 2017年2月14日 下午4:07:09
 */
public interface IPurchaseApplicationService {

	/**
	 * @Description 分页查询采购到货通知单列表
	 * @author zhanggaohao
	 * @version 2017年2月14日 下午4:06:16
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<PurchaseApplicationResp> page(PurchaseApplicationQuery query) throws Exception;
	
	/**
	 * @Description 查询分页根据物料分组
	 * @author zhanggaohao
	 * @version 2017年2月16日 下午2:34:50
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<PurchaseApplicationJoinDetailResp> pageGroupMateriel(PurchaseApplicationQuery query) throws Exception;

	/**
	 * @Description 根据id查询采购通知单
	 * @author zhanggaohao
	 * @version 2017年2月14日 下午4:06:50
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PurchaseApplicationResp findOne(String id) throws Exception;
}