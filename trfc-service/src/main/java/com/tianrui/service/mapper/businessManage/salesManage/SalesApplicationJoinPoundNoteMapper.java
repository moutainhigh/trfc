package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinPoundNote;

public interface SalesApplicationJoinPoundNoteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplicationJoinPoundNote record);

    int insertSelective(SalesApplicationJoinPoundNote record);

    SalesApplicationJoinPoundNote selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplicationJoinPoundNote record);

    int updateByPrimaryKey(SalesApplicationJoinPoundNote record);

	int insertBatch(List<SalesApplicationJoinPoundNote> list);
	/**
	 * @Description 根据磅单id查询
	 * @author zhanggaohao
	 * @version 2017年3月21日 上午10:10:38
	 * @param poundNoteId
	 * @return
	 */
	List<SalesApplicationJoinPoundNote> selectByPoundNoteId(String poundNoteId);
}