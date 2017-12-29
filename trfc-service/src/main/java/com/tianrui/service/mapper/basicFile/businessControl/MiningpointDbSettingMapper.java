package com.tianrui.service.mapper.basicFile.businessControl;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.businessControl.MiningpointDbSettingQuery;
import com.tianrui.api.resp.basicFile.businessControl.MiningpointDbSettingResp;
import com.tianrui.service.bean.basicFile.businessControl.MiningpointDbSetting;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;

public interface MiningpointDbSettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MiningpointDbSetting record);

    int insertSelective(MiningpointDbSetting record);

    MiningpointDbSetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MiningpointDbSetting record);

    int updateByPrimaryKey(MiningpointDbSetting record);
    
    List<MiningpointDbSetting> selectSelective(MiningpointDbSetting record);
    /**
     * @Description 原发设置分页查询
     * @author q
     * @param query
     * @return
     */
    List<MiningpointDbSettingResp> selectPrimaryPage(MiningpointDbSettingQuery query);
    /**
     * @Description 原发设置分页查询总条数
     * @author q
     * @param query
     * @return
     */
    long selectPrimaryPageCount(MiningpointDbSettingQuery query);
   

	List<MiningpointDbSettingResp> selectByMaterialid(String materialid, String userId);
	
	 List<MiningpointDbSettingResp> autoCompleteSearch(@Param("likeName")String likeName,@Param("materid")String materid,@Param("suppid")String suppid);
}