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
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
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
    
    @Override
    public Result readICard(HandSetRequestParam params) {
        Result result = Result.getParamErrorResult();
        if (StringUtils.isNotBlank(params.getVehicleNo())) {
            //判断车辆是否存在
            VehicleManage vehicle = vehicleManageMapper.getVehicleByNo(params.getVehicleNo());
            if (vehicle != null) {
                result.setData(readICardByVehicle(vehicle.getId()));
                result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
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
        SalesApplication application = salesApplicationMapper.selectByPrimaryKey(salesArrive.getBillid());
        if (application != null) {
            resp.setDeptId(application.getCustomerid());
            resp.setDeptName(application.getCustomername());
        }
        SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(salesArrive.getBilldetailid());
        if (applicationDetail != null) {
            resp.setMaterial(applicationDetail.getMaterielname());
        }
    }
    
    //采购通知单读到卡里
    private void pDo2Dto(HandSetReadICardResp resp, PurchaseArrive purchaseArrive) {
        resp.setNoticeCode(purchaseArrive.getCode());
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
                        pa.setStatus(Constant.SEVEN_STRING);
                        update.setSignStatus(Constant.ZERO_NUMBER);
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
                        pa.setStatus(Constant.SEVEN_STRING);
                        update.setSignStatus(Constant.ZERO_NUMBER);
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
    
}
