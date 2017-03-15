package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.service.bean.basicFile.measure.MinemouthManage;

public interface MinemouthManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(MinemouthManage record);

    int insertSelective(MinemouthManage record);

    MinemouthManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MinemouthManage record);

    int updateByPrimaryKey(MinemouthManage record);
    /**
     * 根据字段查新数据
     * @param record
     * @return
     */
    List<MinemouthManage> selectSelective(MinemouthManage record);
    /**
     * 根据条件查询数据总数
     * @param query
     * @return
     */
    long findMinemouthPageCount(MinemouthManageQuery query);
    /**
     * 根据条件进行分页查询
     * @param query
     * @return
     */
	List<MinemouthManage> findMinemouthPage(MinemouthManageQuery query);
	/**
	 * @Description 根据名称模糊查询
	 * @author zhanggaohao
	 * @version 2017年3月8日 下午12:01:20
	 * @param likeName
	 * @return
	 */
	List<MinemouthManage> autoCompleteSearch(@Param("likeName")String likeName);
}