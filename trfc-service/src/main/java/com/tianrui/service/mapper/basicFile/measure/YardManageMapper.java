package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.service.bean.basicFile.measure.YardManage;

public interface YardManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(YardManage record);

    int insertSelective(YardManage record);

    YardManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YardManage record);

    int updateByPrimaryKey(YardManage record);
    
    long findYardPageCount(YardManageQuery query);
    
    List<YardManage> findYardPage(YardManageQuery query);
    
    List<YardManage> selectSelective(YardManage record);
    /**
     * @Description 通过名称模糊查询
     * @author zhanggaohao
     * @version 2017年3月8日 下午2:08:43
     * @param likeName
     * @return
     */
    List<YardManage> autoCompleteSearch(@Param("likeName")String likeName);
    /**
     * @Description 手持机获取堆场集合接口
     * @author zhanggaohao
     * @version 2017年4月21日 下午2:59:29
     * @param req
     * @return
     */
	List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req);
}