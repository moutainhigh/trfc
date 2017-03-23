package com.tianrui.service.mapper.businessManage.poundNoteMaintain;

import java.util.List;

import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
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
	List<PoundNoteResp> purchasePage(PoundNoteQuery query);
	/**
	 * @Description 根据id查询磅单
	 * @author zhanggaohao
	 * @version 2017年3月9日 上午9:48:01
	 * @param id
	 * @return
	 */
	PoundNoteResp findOne(String id);
	/**
	 * @Description 查询销售磅单
	 * @author zhanggaohao
	 * @version 2017年3月18日 下午3:20:38
	 * @param query
	 * @return
	 */
	List<PoundNoteResp> salesPage(PoundNoteQuery query);
	/**
	 * @Description 查询销售磅单总条数
	 * @author zhanggaohao
	 * @version 2017年3月18日 下午3:20:22
	 * @param query
	 * @return
	 */
	long salesPageCount(PoundNoteQuery query);
	PoundNote findByBillid(String billid);
}