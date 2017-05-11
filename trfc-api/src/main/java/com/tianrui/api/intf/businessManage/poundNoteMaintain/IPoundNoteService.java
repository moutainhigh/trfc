	package com.tianrui.api.intf.businessManage.poundNoteMaintain;

	import java.util.List;

import com.tianrui.api.req.businessManage.app.AppPoundOrderReq;
import com.tianrui.api.req.businessManage.app.AppPoundOrderResp;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.ApiPoundNoteValidation;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
	import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteSave;
	import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.common.UploadImageResp;
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
		/**
		 * @Description 磅单上传接口
		 * @author zhanggaohao
		 * @version 2017年3月25日 上午10:00:44
		 * @param query
		 * @return
		 * @throws Exception 
		 */
		Result savePoundNoteWeight(ApiPoundNoteQuery query) throws Exception;
		/**
		 * @Description 磅房验证
		 * @author zhanggaohao
		 * @version 2017年3月27日 上午9:09:55
		 * @param valid
		 * @return
		 * @备注 一次过磅验证入厂门禁，二次过磅：采购验证一次过磅，销售验证装货
		 */
		Result validation(ApiPoundNoteValidation valid);
		/**
		 * @Description 历史皮重查询
		 * @author zhanggaohao
		 * @version 2017年3月27日 下午3:34:59
		 * @param valid
		 * @return
		 */
		Result tareWeight(ApiPoundNoteQuery valid);
		/**
		 * @Description app磅单分页接口
		 * @author zhanggaohao
		 * @version 2017年4月17日 下午5:07:21
		 * @param body
		 * @return
		 */
		PaginationVO<AppPoundOrderResp> appToPage(AppPoundOrderReq req);
		/**
		 * @Description app磅单详情接口
		 * @author zhanggaohao
		 * @version 2017年4月18日 上午10:02:34
		 * @param req
		 * @return
		 */
		Result appToDetail(AppPoundOrderReq req);
		/**
		 * 根据通知单名称 通知单类型获取榜单号
		 * @param req
		 * @return
		 */
		Result detail(ApiPoundNoteQuery req);
		/**
		 * 获取图片信息
		 */
		List<UploadImageResp> getPoundImages(String billcode) throws Exception;
	}

