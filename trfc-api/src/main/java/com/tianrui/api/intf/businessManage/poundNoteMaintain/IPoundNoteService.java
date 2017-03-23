	package com.tianrui.api.intf.businessManage.poundNoteMaintain;

	import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
	import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteSave;
	import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
	import com.tianrui.smartfactory.common.vo.PaginationVO;
	import com.tianrui.smartfactory.common.vo.Result;

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
		/**
		 * @Description 新增采购计量磅单
		 * @author zhanggaohao
		 * @version 2017年3月8日 下午3:06:52
		 * @param save
		 * @return
		 * @throws Exception 
		 */
		Result addPurchasePoundNote(PoundNoteSave save) throws Exception;
		/**
		 * @Description 根据id查询磅单
		 * @author zhanggaohao
		 * @version 2017年3月9日 上午9:28:35
		 * @param id
		 * @return
		 * @throws Exception 
		 */
		PoundNoteResp findOne(String id) throws Exception;
		/**
		 * @Description 采购退货计量单新增
		 * @author zhanggaohao
		 * @version 2017年3月9日 上午11:40:55
		 * @param save
		 * @return
		 * @throws Exception 
		 */
		Result returnAddPoundNote(PoundNoteSave save) throws Exception;
		/**
		 * @Description 采购红冲
		 * @author zhanggaohao
		 * @version 2017年3月9日 下午2:48:06
		 * @param query
		 * @return
		 */
		Result purchaseRedcollide(PoundNoteQuery query);
		/**
		 * @Description 采购作废
		 * @author zhanggaohao
		 * @version 2017年3月9日 下午2:48:32
		 * @param query
		 * @return
		 */
		Result purchaseInvalid(PoundNoteQuery query);
		/**
		 * @Description 查询销售磅单
		 * @author zhanggaohao
		 * @version 2017年3月17日 上午11:09:14
		 * @param query
		 * @return
		 */
		PaginationVO<PoundNoteResp> salesPage(PoundNoteQuery query);
		Result findByBillid(String billid) throws Exception;
	/**
		 * @Description 销售计量单新增
		 * @author zhanggaohao
		 * @version 2017年3月19日 下午3:11:52
		 * @param save
		 * @param bills 
		 * @return
		 * @throws Exception 
		 */
		Result addSalesPoundNote(PoundNoteSave save, String bills) throws Exception;
		/**
		 * @Description 销售红冲
		 * @author zhanggaohao
		 * @version 2017年3月21日 上午9:50:10
		 * @param query
		 * @return
		 */
		Result salesRedcollide(PoundNoteQuery query);
		/**
		 * @Description 销售作废
		 * @author zhanggaohao
		 * @version 2017年3月21日 上午9:50:45
		 * @param query
		 * @return
		 */
		Result salesInvalid(PoundNoteQuery query);
		/**
		 * @Description 修改销售计量单出厂编号
		 * @author zhanggaohao
		 * @version 2017年3月21日 下午2:41:23
		 * @param query
		 * @return
		 */
		Result updateSerialNumber(PoundNoteQuery query);
	
	}

