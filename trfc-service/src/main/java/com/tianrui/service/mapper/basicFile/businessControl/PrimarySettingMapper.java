package com.tianrui.service.mapper.basicFile.businessControl;

import java.util.List;

import com.tianrui.api.req.basicFile.businessControl.PrimarySettingQuery;
import com.tianrui.api.resp.basicFile.businessControl.PrimarySettingResp;
import com.tianrui.service.bean.basicFile.businessControl.PrimarySetting;

public interface PrimarySettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(PrimarySetting record);

    int insertSelective(PrimarySetting record);

    PrimarySetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrimarySetting record);

    int updateByPrimaryKey(PrimarySetting record);
    
    List<PrimarySetting> selectSelective(PrimarySetting record);
    /**
     * @Description 原发设置分页查询
     * @author zhanggaohao
     * @version 2017年5月12日 下午2:26:46
     * @param query
     * @return
     */
    List<PrimarySettingResp> selectPrimaryPage(PrimarySettingQuery query);
    /**
     * @Description 原发设置分页查询总条数
     * @author zhanggaohao
     * @version 2017年5月12日 下午2:26:59
     * @param query
     * @return
     */
    long selectPrimaryPageCount(PrimarySettingQuery query);
}