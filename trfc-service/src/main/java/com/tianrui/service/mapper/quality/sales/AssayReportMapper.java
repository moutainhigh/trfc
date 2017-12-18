package com.tianrui.service.mapper.quality.sales;

import java.util.List;

import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.service.bean.quality.sales.AssayReport;

public interface AssayReportMapper {
	/**
	 * 删除数据(通过id)
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 新增数据
     */
    int insert(AssayReport record);
    /**
     * 新增数据(动态)
     */
    int insertSelective(AssayReport record);
    /**
     * 查询数据(主键id)
     */
    AssayReport selectByPrimaryKey(String id);
    /**
     * 更新数据(动态)
     */
    int updateByPrimaryKeySelective(AssayReport record);
    /**
     * 更新数据
     */
    int updateByPrimaryKey(AssayReport record);
    /**
     * 分页查询数据
     */
    List<AssayReport> page(AssayReportReq req);
    /**
     * 查询数据总数
     */
    int count(AssayReportReq req);
    /**
     * 查询单条数据
     */
    AssayReport selectOne(String id);
    /**
     * 根据批号id查询报告
     * @Title: selectBatchnumid 
     * @Description: TODO
     * @param @param req
     * @param @return   
     * @return List<AssayReport>    
     * @throws
     */
    List<AssayReport> selectBatchnumid(AssayReportReq req);
}