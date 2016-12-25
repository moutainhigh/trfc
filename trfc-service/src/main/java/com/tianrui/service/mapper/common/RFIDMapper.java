package com.tianrui.service.mapper.common;

import java.util.List;

import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.service.bean.common.RFID;

public interface RFIDMapper {
    int deleteByPrimaryKey(String rfid);

    int insert(RFID record);

    int insertSelective(RFID record);

    RFID selectByPrimaryKey(String rfid);

    int updateByPrimaryKeySelective(RFID record);

    int updateByPrimaryKey(RFID record);

	int saveList(List<RFIDReq> list);
}