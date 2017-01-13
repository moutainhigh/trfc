package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
/**
 * 仓库管理Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月10日 上午10:30:09
 * @classname WarehouseManageMapper.java
 */
public interface WarehouseManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(WarehouseManage record);

    int insertSelective(WarehouseManage record);

    WarehouseManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarehouseManage record);

    int updateByPrimaryKey(WarehouseManage record);

    long findWarehouseManagePageCount(WarehouseManageQuery query);

	List<WarehouseManage> findWarehouseManagePage(WarehouseManageQuery query);

	List<WarehouseManage> selectSelective(WarehouseManage record);
}