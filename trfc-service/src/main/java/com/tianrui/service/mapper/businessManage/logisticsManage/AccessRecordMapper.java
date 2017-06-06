package com.tianrui.service.mapper.businessManage.logisticsManage;

import java.util.List;

import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;

public interface AccessRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(AccessRecord record);

    int insertSelective(AccessRecord record);

    AccessRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccessRecord record);

    int updateByPrimaryKey(AccessRecord record);

	long findAccessRecordPageCount(AccessRecordQuery query);

	List<AccessRecord> findAccessRecordPage(AccessRecordQuery query);

	AccessRecord selectByNoticeId(String noticeId);
}