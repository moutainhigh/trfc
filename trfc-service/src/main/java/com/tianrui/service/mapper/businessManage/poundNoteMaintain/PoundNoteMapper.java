package com.tianrui.service.mapper.businessManage.poundNoteMaintain;

import java.util.List;

import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;

public interface PoundNoteMapper {
    int deleteByPrimaryKey(String id);

    int insert(PoundNote record);

    int insertSelective(PoundNote record);

    PoundNote selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PoundNote record);

    int updateByPrimaryKey(PoundNote record);
    /**
     * @Description 查询采购磅单总条数
     * @author zhanggaohao
     * @version 2017年3月6日 下午2:23:47
     * @param query
     * @return
     */
	long purchasePageCount(PoundNoteQuery query);
	/**
	 * @Description 查询采购磅单
	 * @author zhanggaohao
	 * @version 2017年3月6日 下午2:24:15
	 * @param query
	 * @return
	 */
	List<PoundNote> purchasePage(PoundNoteQuery query);
}