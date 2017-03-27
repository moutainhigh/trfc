package com.tianrui.service.impl.access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.access.IAccessRecordService;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.service.bean.access.AccessRecord;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.access.AccessRecordMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.CommonUtils;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class AccessRecordService implements IAccessRecordService {

	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired 
	private RFIDMapper rfidMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	
	/**
	 * 新增数据
	 */
	@Transactional
	@Override
	public Result add(ApiDoorSystemSave api) {
		Result result = Result.getParamErrorResult();
		if (api!=null && StringUtils.isNotBlank(api.getNotionformcode()) && StringUtils.isNotBlank(api.getIccode())
				&& StringUtils.isNotBlank(api.getType()) && StringUtils.isNotBlank(api.getTime())) {
			Card card = new Card();
			card.setState("1");
			card.setCardstatus("1");
			card.setCardno(api.getIccode());
			List<Card> listCard = cardMapper.selectSelective(card);
			if (listCard != null && listCard.size() == 1) {
				SalesArrive sa = new SalesArrive();
				sa.setCode(api.getNotionformcode());
				List<SalesArrive> list = salesArriveMapper.selectSelective(sa);
				if (list != null && list.size() == 1) {
					//入厂
					if (StringUtils.equals(api.getType(), "1")) {
						SalesArriveQuery query = new SalesArriveQuery();
						query.setIcardid(listCard.get(0).getId());
						query.setState("1");
						//检测IC卡有没有被使用
						int count = salesArriveMapper.checkICUse(query);
						if (count > 0) {
							result.setErrorCode(ErrorCode.CARD_IN_USE);
							return result;
						}
					} else {
						//出厂
						AccessRecord access = new AccessRecord();
						access.setSalesarrivecode(api.getNotionformcode());
						access.setIcardcode(api.getIccode());
						access.setAccesstype("1");
						List<AccessRecord> listAccess = accessRecordMapper.selectSelective(access);
						if (listAccess == null || listAccess.size() == 0) {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ENTER);
							return result;
						}
					}
					AccessRecord record = new AccessRecord();
					record.setId(UUIDUtil.getId());
					record.setSalesarrivecode(api.getNotionformcode());
					record.setIcardcode(api.getIccode());
					record.setAccesstype(api.getType());
					record.setIntotime(api.getTime());
					//此处添加 创建者 创建时间 修改者 修改时间
					record.setCreator(api.getCurrUid());
					record.setCreatetime(System.currentTimeMillis());
					record.setModifier(api.getCurrUid());
					record.setModifytime(System.currentTimeMillis());
					int index = accessRecordMapper.insertSelective(record);
					if (index == 1) {
						//操作成功
						//修改通知单状态
						sa.setId(list.get(0).getId());
						sa.setCode(null);
						if (StringUtils.equals(api.getType(), "1")) {
							sa.setStatus("6");
							sa.setIcardid(listCard.get(0).getId());
							sa.setIcardno(listCard.get(0).getCardno());
						} else {
							sa.setStatus("5");
						}
						if (salesArriveMapper.updateByPrimaryKeySelective(sa) > 0) {
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							result.setData(true);
						} else {
							result.setErrorCode(ErrorCode.OPERATE_ERROR);
						}
					} else {
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				} else {
					result.setErrorCode(ErrorCode.SALESARRIVE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
			}
		}
		return result;
	}

	@Override
	public Result leaveFactoryCheckApi(VehicleCheckApi checkApi) {
		Result result = Result.getParamErrorResult();
		if (checkApi != null 
				&& StringUtils.isNotBlank(checkApi.getVehicleNo()) 
				&& StringUtils.isNotBlank(checkApi.getRfid())) {
			if (validateRFID(checkApi.getRfid())) {
				if (validateVehicle(checkApi.getVehicleNo(), checkApi.getRfid())) {
					Map<String, Object> map = validateHasBill(checkApi.getVehicleNo());
					if (map != null && map.size() > 0) {
						Object object = map.get("data");
						if (!StringUtils.equals(CommonUtils.method(object, "status").toString(), "2")) {
							result.setData(map);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_TWO_WEIGHT);
						}
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					}
				} else {
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}
		return result;
	}
	/**
	 * @Description 入厂验证
	 * @author zhanggaohao
	 * @version 2017年3月2日 下午3:49:33
	 * @param checkApi
	 * @return
	 */
	@Override
	public Result enterFactoryCheckApi(VehicleCheckApi checkApi) {
		Result result = Result.getParamErrorResult();
		if (checkApi != null 
				&& StringUtils.isNotBlank(checkApi.getVehicleNo()) 
				&& StringUtils.isNotBlank(checkApi.getRfid())) {
			if (validateRFID(checkApi.getRfid())) {
				if (validateVehicle(checkApi.getVehicleNo(), checkApi.getRfid())) {
					Map<String, Object> map = validateHasBill(checkApi.getVehicleNo());
					if (map != null && map.size() > 0) {
						Object object = map.get("data");
						if (!StringUtils.equals(CommonUtils.method(object, "status").toString(), "0")) {
							result.setData(map);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							result.setErrorCode(ErrorCode.VEHICLE_NOTICE_ALREADY_ENTER);
						}
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					}
				} else {
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}
		return result;
	}
	/**
	 * @Description 验证RFID是否已注册
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:36:57
	 * @param rfid
	 * @return
	 */
	private boolean validateRFID(String rfid) {
		boolean flag = false;
		if (StringUtils.isNotBlank(rfid)) {
			RFID bean = rfidMapper.validateRFID(rfid);
			if (bean != null) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * @Description 验证车牌号是否与RFID已绑定
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:48:26
	 * @param vehicleno
	 * @param rfid
	 * @return
	 */
	private boolean validateVehicle(String vehicleno, String rfid) {
		boolean flag = false;
		if (StringUtils.isNotBlank(vehicleno) && StringUtils.isNotBlank(rfid)) {
			VehicleManage bean = vehicleManageMapper.validateVehicle(vehicleno, rfid);
			if (bean != null) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * @Description 验证是否有通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:52:35
	 * @param vehicleNo
	 * @return
	 */
	private Map<String, Object> validateHasBill(String vehicleno) {
		Map<String, Object> map = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			map = new HashMap<String, Object>();
			PurchaseArrive purchaseArrive = hasPurchaseArrive(vehicleno);
			//判断是否有采购通知单
			if (purchaseArrive == null) {
				SalesArrive salesArrive = hasSalesArrive(vehicleno);
				//判断是否有采购通知单
				if (salesArrive == null) {
					//
				} else {
					map.put("type", 3);
					map.put("data", salesArrive);
				}
			} else {
				map.put("type", 1);
				map.put("data", purchaseArrive);
			}
		}
		return map;
	}
	/**
	 * @Description 验证是否有采购到货通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:57:02
	 * @param vehicleno
	 * @return
	 */
	private PurchaseArrive hasPurchaseArrive(String vehicleno) {
		PurchaseArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			bean = purchaseArriveMapper.hasPurchaseArrive(vehicleno);
		}
		return bean;
	}
	/**
	 * @Description 验证是否有销售提货通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午10:31:40
	 * @param vehicleno
	 * @return
	 */
	private SalesArrive hasSalesArrive(String vehicleno) {
		SalesArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			bean = salesArriveMapper.hasPurchaseArrive(vehicleno);
		}
		return bean;
	}
}
