package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.businessManage.app.AppMaterialResp;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;

/**
 * 物料管理Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:30:39
 * @classname MaterielManageMapper.java
 */
public interface MaterielManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(MaterielManage record);
    
    int insertBatch(List<MaterielManage> list);

    int insertSelective(MaterielManage record);

    MaterielManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaterielManage record);

    int updateByPrimaryKey(MaterielManage record);
    
    long findMaterielManagePageCount(MaterielManageQuery req);
    
    List<MaterielManage> findMaterielManagePage(MaterielManageQuery req);
    
    List<MaterielManage> selectSelective(MaterielManage record);
    
    Long findMaxUtc();
    
    List<MaterielManage> autoCompleteSearch(@Param("likeName")String likeName);
    /**
     * @Description app物料列表
     * @author zhanggaohao
     * @version 2017年4月25日 上午9:45:17
     * @param req
     * @return
     */
	long appQueryPageCount(AppQueryReq req);
	/**
     * @Description app物料列表
     * @author zhanggaohao
     * @version 2017年4月25日 上午9:45:17
     * @param req
     * @return
     */
	List<AppMaterialResp> appQueryPage(AppQueryReq req);
	/**
	 * @Description 手持机物料接口
	 * @author zhanggaohao
	 * @version 2017年5月15日 下午2:36:28
	 * @param req
	 * @return
	 */
	List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req);
}