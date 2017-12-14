package com.tianrui.service.impl.handSetStatic;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.handSetStatic.IHandSetStaticService;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.businessManage.handset.HandSetReadICardResp;
import com.tianrui.service.bean.basicFile.businessControl.PrimarySetting;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.examine.ExceptionAudit;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.businessControl.PrimarySettingMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.examine.ExceptionAuditMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class HandSetStaticService implements IHandSetStaticService {
    
    @Autowired
    private VehicleManageMapper vehicleManageMapper;
    @Autowired
    private PurchaseArriveMapper purchaseArriveMapper;
    @Autowired
    private PurchaseApplicationMapper purchaseApplicationMapper;
    @Autowired
    private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
    @Autowired
    private SalesArriveMapper salesArriveMapper;
    @Autowired
    private SalesApplicationMapper salesApplicationMapper;
    @Autowired
    private SalesApplicationDetailMapper salesApplicationDetailMapper;
    @Autowired
    private PoundNoteMapper poundNoteMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private WarehouseManageMapper warehouseManageMapper;
    @Autowired
    private YardManageMapper yardManageMapper;
    @Autowired
    private PrimarySettingMapper primarySettingMapper;
    @Autowired
    private ExceptionAuditMapper exceptionAuditMapper;
    
    @Override
    public Result readICard(HandSetRequestParam params) {
        Result result = Result.getParamErrorResult();
        if (StringUtils.isNotBlank(params.getVehicleNo())) {
            //判断车辆是否存在
            VehicleManage vehicle = vehicleManageMapper.getVehicleByNo(params.getVehicleNo());
            if (vehicle != null) {
                HandSetReadICardResp resp = readICardByVehicle(vehicle.getId());
                if (resp != null) {
                    result.setData(resp);
                    result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
                }
            } else {
                result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
            }
        } 
        return result;
    }
    
    //根据车辆ID读卡
    private HandSetReadICardResp readICardByVehicle(String vehicleId) {
        HandSetReadICardResp resp = new HandSetReadICardResp();
        PurchaseArrive purchaseArrive = purchaseArriveMapper.getByVehicleId(vehicleId);
        if (purchaseArrive == null) {
            SalesArrive salesArrive = salesArriveMapper.getByVehicleId(vehicleId);
            if (salesArrive == null) {
                //其他通知单
                resp = null;
            } else {
                //销售读卡
                sDo2Dto(resp, salesArrive);
            }
        } else {
            //采购读卡
            pDo2Dto(resp, purchaseArrive);
        }
        return resp;
    }

    //销售通知单读到卡里
    private void sDo2Dto(HandSetReadICardResp resp, SalesArrive salesArrive) {
        resp.setNoticeCode(salesArrive.getCode());
        resp.setNoticeStatus(salesArrive.getStatus());
        SalesApplication application = salesApplicationMapper.selectByPrimaryKey(salesArrive.getBillid());
        if (application != null) {
            resp.setDeptId(application.getCustomerid());
            resp.setDeptName(application.getCustomername());
        }
        SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(salesArrive.getBilldetailid());
        if (applicationDetail != null) {
            resp.setMaterial(applicationDetail.getMaterielname());
        }
        PoundNote pn = poundNoteMapper.selectByNoticeId(salesArrive.getId());
        if (pn != null) {
            resp.setPoundNoteCode(pn.getCode());
        }
    }
    
    //采购通知单读到卡里
    private void pDo2Dto(HandSetReadICardResp resp, PurchaseArrive purchaseArrive) {
        resp.setNoticeCode(purchaseArrive.getCode());
        resp.setNoticeStatus(purchaseArrive.getStatus());
        PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(purchaseArrive.getBillid());
        if (application != null) {
            resp.setDeptId(application.getSupplierid());
            resp.setDeptName(application.getSuppliername());
            resp.setDeptRemark(application.getSupplierremark());
            resp.setMinemouth(application.getMinemouthname());
        }
        PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(purchaseArrive.getBilldetailid());
        if (applicationDetail != null) {
            resp.setMaterial(applicationDetail.getMaterielname());
        }
        if (application != null && applicationDetail != null) {
            PrimarySetting record = new PrimarySetting();
            record.setMaterialid(applicationDetail.getMaterielid());
            record.setSupplierid(application.getSupplierid());
            record.setIsvalid(Constant.ONE_STRING);
            List<PrimarySetting> list = primarySettingMapper.selectSelective(record);
            if(CollectionUtils.isNotEmpty(list)) {
                resp.setIsPrimary(Constant.ONE_STRING);
            } else {
                resp.setIsPrimary(Constant.ZERO_STRING);
            }
        }
        PoundNote pn = poundNoteMapper.selectByNoticeId(purchaseArrive.getId());
        if (pn != null) {
            resp.setPoundNoteCode(pn.getCode());
        }
        
    }

    @Transactional
    @Override
    public Result receive(HandSetRequestParam param) {
        Result result = Result.getParamErrorResult();
        if (StringUtils.isNotBlank(param.getUserId())
                &&StringUtils.isNotBlank(param.getNoticeCode())
                && StringUtils.isNotBlank(param.getWarehouseId())
                && StringUtils.isNotBlank(param.getYardId())
                && param.getDeductionweight() != null
                && param.getDeductionother() != null
                && param.getOriginalnetweight() != null
                && StringUtils.isNotBlank(param.getSignID())) {
            PurchaseArrive pa = purchaseArriveMapper.selectByCode(param.getNoticeCode());
            if (pa != null) {
                if (!StringUtils.equals(pa.getStatus(), Constant.SEVEN_STRING)) {
                    if (StringUtils.equals(pa.getStatus(), Constant.ONE_STRING)) {
                        PoundNote poundNote = new PoundNote();
                        poundNote.setNoticecode(param.getNoticeCode());
                        List<PoundNote> list = poundNoteMapper.selectSelective(poundNote);
                        if (CollectionUtils.isNotEmpty(list)) {
                            poundNote = list.get(0);
                            PoundNote updateItem = copyReceive(param, poundNote);
                            poundNoteMapper.updateByPrimaryKeySelective(updateItem);
                            result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                            //修改通知单签收状态
                            PurchaseArrive update = new PurchaseArrive();
                            update.setStatus(Constant.SEVEN_STRING);
                            update.setId(pa.getId());
                            update.setSignStatus(Constant.ONE_NUMBER);
                            update.setSignPerson(param.getUserId());
                            update.setSignPersonName(updateItem.getReceiverpersonname());
                            update.setSignTime(System.currentTimeMillis());
                            update.setSignID(param.getSignID());
                            update.setModifier(param.getUserId());
                            update.setModifytime(System.currentTimeMillis());
                            purchaseArriveMapper.updateByPrimaryKeySelective(update);
                        }
                    } else {
                        result.setErrorCode(ErrorCode.NOTICE_NOT_ONE_POUNDNOTE);
                    }
                } else {
                    result.setErrorCode(ErrorCode.NOTICE_ON_SIGN);
                }
            } else {
                result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return result;
    }

    private PoundNote copyReceive(HandSetRequestParam param, PoundNote poundNote) {
        PoundNote updateItem = new PoundNote();
        updateItem.setId(poundNote.getId());
        SystemUser user = systemUserMapper.selectByPrimaryKey(param.getUserId());
        if (user != null) {
            updateItem.setReceiverpersonid(user.getId());
            updateItem.setReceiverpersonname(user.getName());
        }
        WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(param.getWarehouseId());
        if (warehouse != null) {
            updateItem.setWarehouseid(warehouse.getId());
            updateItem.setWarehousename(warehouse.getName());
        }
        YardManage yard = yardManageMapper.selectByPrimaryKey(param.getYardId());
        if (yard != null) {
            updateItem.setYardid(yard.getId());
            updateItem.setYardname(yard.getName());
        }
        /**
         * 这里扣重扣重和原发跟二次过磅有冲突，在此记录，待解决
         */
        updateItem.setDeductionweight(param.getDeductionweight());
        updateItem.setDeductionother(param.getDeductionother());
        updateItem.setOriginalnetweight(param.getOriginalnetweight());
        return updateItem;
    }

    @Transactional
    @Override
    public Result allReturnOfGoods(HandSetRequestParam param) {
        Result result = Result.getParamErrorResult();
        if (StringUtils.isNotBlank(param.getUserId())
                &&StringUtils.isNotBlank(param.getNoticeCode())) {
            PurchaseArrive pa = purchaseArriveMapper.selectByCode(param.getNoticeCode());
            if (pa != null) {
                if (!StringUtils.equals(pa.getStatus(), Constant.SEVEN_STRING)) {
                    if (StringUtils.equals(pa.getStatus(), Constant.ONE_STRING)) {
                        PurchaseArrive update = new PurchaseArrive();
                        update.setId(pa.getId());
                        update.setStatus(Constant.SEVEN_STRING);
                        update.setSignStatus(Constant.ZERO_NUMBER);
                        update.setModifier(param.getUserId());
                        update.setModifytime(System.currentTimeMillis());
                        purchaseArriveMapper.updateByPrimaryKeySelective(update);
                        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                    } else {
                        result.setErrorCode(ErrorCode.NOTICE_NOT_ONE_POUNDNOTE);
                    }
                } else {
                    result.setErrorCode(ErrorCode.NOTICE_ON_SIGN);
                }
            } else {
                result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return result;
    }

    @Transactional
    @Override
    public Result returnOfGoods(HandSetRequestParam param) {
        Result result = Result.getParamErrorResult();
        if (StringUtils.isNotBlank(param.getUserId())
                &&StringUtils.isNotBlank(param.getNoticeCode())) {
            PurchaseArrive pa = purchaseArriveMapper.selectByCode(param.getNoticeCode());
            if (pa != null) {
                if (!StringUtils.equals(pa.getStatus(), Constant.SEVEN_STRING)) {
                    if (StringUtils.equals(pa.getStatus(), Constant.ONE_STRING)) {
                        PurchaseArrive update = new PurchaseArrive();
                        update.setId(pa.getId());
                        update.setStatus(Constant.SEVEN_STRING);
                        update.setSignStatus(Constant.ZERO_NUMBER);
                        update.setModifier(param.getUserId());
                        update.setModifytime(System.currentTimeMillis());
                        purchaseArriveMapper.updateByPrimaryKeySelective(update);
                        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                    } else {
                        result.setErrorCode(ErrorCode.NOTICE_NOT_ONE_POUNDNOTE);
                    }
                } else {
                    result.setErrorCode(ErrorCode.NOTICE_ON_SIGN);
                }
            } else {
                result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return result;
    }

    @Override
    public Result confirmationOfShipment(HandSetRequestParam param) {
        Result rs = Result.getParamErrorResult();
        if (param != null && StringUtils.isNotBlank(param.getVehicleNo())) {
        	SystemUser user = systemUserMapper.selectByPrimaryKey(param.getUserId());
        	if (user != null) {
        		SalesArrive sa = salesArriveMapper.getByVehicleNo(param.getVehicleNo());
        		if (sa != null) {
        			if (!StringUtils.equals(sa.getStatus(), Constant.SEVEN_STRING)) {
        				if (StringUtils.equals(sa.getStatus(), Constant.ONE_STRING)) {
        					PoundNote pn = poundNoteMapper.selectByNoticeId(sa.getId());
        					List<ExceptionAudit> list = exceptionAuditMapper.listByPnId(Constant.ONE_STRING, pn.getId());
        					if (CollectionUtils.isEmpty(list)) {
        						SalesArrive bean = new SalesArrive();
        						bean.setId(sa.getId());
        						bean.setStatus(Constant.SEVEN_STRING);
        						bean.setSendPerson(user.getId());
        						bean.setSendPersonName(user.getName());
        						bean.setSendTime(System.currentTimeMillis());
        						bean.setSendID(param.getSignID());
        						bean.setModifier(param.getUserId());
        						bean.setModifytime(System.currentTimeMillis());
        						salesArriveMapper.updateByPrimaryKeySelective(bean);
        						rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
        					} else {
        						rs.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR2);
        					}
        				} else {
        					rs.setErrorCode(ErrorCode.NOTICE_NOT_ONE_POUNDNOTE);
        				}
        			} else {
        				rs.setErrorCode(ErrorCode.NOTICE_ON_SIGN);
        			}
        		} else {
        			rs.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
        		}
        	} else {
        		rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR18);
        	}
        }
        return rs;
    }

    @Override
    public Result emptyCarLeavingFactory(HandSetRequestParam param) {
        Result rs = Result.getParamErrorResult();
        if (param != null && StringUtils.isNotBlank(param.getVehicleNo())) {
            SalesArrive sa = salesArriveMapper.getByVehicleNo(param.getVehicleNo());
            if (sa != null) {
                if (StringUtils.equals(sa.getStatus(), Constant.ONE_STRING)) {
                    PoundNote pn = poundNoteMapper.selectByNoticeId(sa.getId());
                    List<ExceptionAudit> list = exceptionAuditMapper.listByPnId(Constant.ONE_STRING, pn.getId());
                    if (CollectionUtils.isEmpty(list)) {
                        ExceptionAudit ea = new ExceptionAudit();
                        ea.setId(UUIDUtil.getId());
                        ea.setPnId(pn.getId());
                        ea.setType(Constant.ONE_NUMBER);
                        ea.setAuditStatus(false);
                        ea.setState(true);
                        ea.setCreator(param.getUserId());
                        ea.setCreatetime(System.currentTimeMillis());
                        exceptionAuditMapper.insertSelective(ea);
                        rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                    } else {
                        rs.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR2);
                    }
                } else {
                    rs.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_ONE_WEIGHT);
                }
            } else {
                rs.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return rs;
    }

    @Override
    public Result noNeedToFillTheBag(HandSetRequestParam param) {
        Result rs = Result.getParamErrorResult();
        if (param != null && StringUtils.isNotBlank(param.getVehicleNo())) {
            SalesArrive sa = salesArriveMapper.getByVehicleNo(param.getVehicleNo());
            if (sa != null) {
                if (StringUtils.equals(sa.getStatus(), Constant.SEVEN_STRING)) {
                    PoundNote pn = poundNoteMapper.selectByNoticeId(sa.getId());
                    List<ExceptionAudit> list = exceptionAuditMapper.listByPnId(Constant.FOUR_STRING, pn.getId());
                    if (CollectionUtils.isEmpty(list)) {
                        ExceptionAudit ea = new ExceptionAudit();
                        ea.setId(UUIDUtil.getId());
                        ea.setPnId(pn.getId());
                        ea.setType(Constant.FOUR_NUMBER);
                        ea.setAuditStatus(false);
                        ea.setState(true);
                        ea.setCreator(param.getUserId());
                        ea.setCreatetime(System.currentTimeMillis());
                        exceptionAuditMapper.insertSelective(ea);
                        rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                    } else {
                        rs.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR3);
                    }
                } else {
                    rs.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD);
                }
            } else {
                rs.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return rs;
    }
    
    @Override
    public Result confirmationOfFillTheBag(HandSetRequestParam param) {
        Result rs = Result.getParamErrorResult();
        if (param != null && StringUtils.isNotBlank(param.getVehicleNo())) {
            SalesArrive sa = salesArriveMapper.getByVehicleNo(param.getVehicleNo());
            if (sa != null) {
                if (StringUtils.equals(sa.getStatus(), Constant.SEVEN_STRING)) {
                    PoundNote pn = poundNoteMapper.selectByNoticeId(sa.getId());
                    ExceptionAudit ea = new ExceptionAudit();
                    ea.setId(UUIDUtil.getId());
                    ea.setPnId(pn.getId());
                    ea.setType(Constant.TWO_NUMBER);
                    ea.setNumber(Integer.parseInt(param.getNumber()));
                    ea.setAuditStatus(false);
                    ea.setState(true);
                    ea.setCreator(param.getUserId());
                    ea.setCreatetime(System.currentTimeMillis());
                    exceptionAuditMapper.insertSelective(ea);
                    rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    rs.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD);
                }
            } else {
                rs.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return rs;
    }
    
    @Override
    public Result confirmationOfReturnPacket(HandSetRequestParam param) {
        Result rs = Result.getParamErrorResult();
        if (param != null && StringUtils.isNotBlank(param.getVehicleNo())) {
            SalesArrive sa = salesArriveMapper.getByVehicleNo(param.getVehicleNo());
            if (sa != null) {
                if (StringUtils.equals(sa.getStatus(), Constant.SEVEN_STRING)) {
                    PoundNote pn = poundNoteMapper.selectByNoticeId(sa.getId());
                    ExceptionAudit ea = new ExceptionAudit();
                    ea.setId(UUIDUtil.getId());
                    ea.setPnId(pn.getId());
                    ea.setType(Constant.THREE_NUMBER);
                    ea.setNumber(Integer.parseInt(param.getNumber()));
                    ea.setAuditStatus(false);
                    ea.setState(true);
                    ea.setCreator(param.getUserId());
                    ea.setCreatetime(System.currentTimeMillis());
                    exceptionAuditMapper.insertSelective(ea);
                    rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    rs.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_LOAD);
                }
            } else {
                rs.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
            }
        }
        return rs;
    }
    
}
