package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.service.bean.basicFile.measure.BlacklistManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.smartfactory.common.vo.Result;
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

	Result page(BlacklistManageQuery query);

	long findBlacklistPageCount(BlacklistManageQuery query);

	List<BlacklistManage> findBlacklistPage(BlacklistManageQuery query);

	List<BlacklistManage> autoCompleteSearch(@Param("term")String term);
	
	
}