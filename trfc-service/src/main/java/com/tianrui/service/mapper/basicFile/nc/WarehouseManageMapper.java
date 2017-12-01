package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.android.SearchKeyParam;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.android.SearchWarehouseListVo;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
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
    
    int insertBatch(List<WarehouseManage> list);

    int insertSelective(WarehouseManage record);

    WarehouseManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WarehouseManage record);

    int updateByPrimaryKey(WarehouseManage record);

    long findWarehouseManagePageCount(WarehouseManageQuery query);

	List<WarehouseManage> findWarehouseManagePage(WarehouseManageQuery query);

	List<WarehouseManage> selectSelective(WarehouseManage record);
	
	Long findMaxUtc();

	List<WarehouseManage> autoCompleteSearch(@Param("likeName")String trim);
	/**
	 * @Description 手持机获取仓库集合接口
	 * @author zhanggaohao
	 * @version 2017年4月21日 下午2:53:47
	 * @param req
	 * @return
	 */
	List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req);

	/**
	 * 查询仓库的id和名称
	 * @param param
	 * @return
	 */
	List<SearchWarehouseListVo> queryWarehouse(SearchKeyParam param);
}