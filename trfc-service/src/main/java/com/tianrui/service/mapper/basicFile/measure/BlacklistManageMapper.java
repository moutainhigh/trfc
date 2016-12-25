package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.service.bean.basicFile.measure.BlacklistManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
/**
 * 车辆黑名单Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月20日 下午4:20:22
 * @classname BlacklistManageMapper.java
 */
public interface BlacklistManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(BlacklistManage record);

    int insertSelective(BlacklistManage record);

    BlacklistManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BlacklistManage record);

    int updateByPrimaryKey(BlacklistManage record);
    
    int deleteBlacklistByVid(String vehicleid);

	List<VehicleManage> selectSelective(BlacklistManage record);
}