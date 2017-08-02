package com.tianrui.service.mapper.businessManage.otherManage;

import java.util.List;

import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;

public interface OtherArriveMapper {
    int deleteByPrimaryKey(String id);

    int insert(OtherArrive record);

    int insertSelective(OtherArrive record);

    OtherArrive selectByPrimaryKey(String id);
    /**
     * 通过code 查询数据
     * lixiaoyong
     * @param code
     * @return OtherArrive
     */
    OtherArrive selectByCode(String code);

    int updateByPrimaryKeySelective(OtherArrive record);

    int updateByPrimaryKey(OtherArrive record);
	/**
	 * 分页查询
	 */
	List<OtherArrive> page(OtherArriveReq req);
	/**
	 * 查询总数
	 */
	Long count(OtherArriveReq req);
	/**
	 * 查询�??条数�??
	 */
	OtherArrive selectOneByReq(OtherArriveReq req);
	
	
	List<OtherArrive> checkDriverAndVehicleAndIcardIsUse(OtherArrive record);
	
	/**
	 * 通过车号 判断是否含有通知单 
	 * lixiaoyong
	 */
	OtherArrive hasOtherArrive(String vehicleid);
	
	/**
	 * 通过IC卡ID判断是否正在使用
	 */
	OtherArrive checkICUse(String icardid);
	
    
}