package com.tianrui.service.mapper.quality.file;

import java.util.List;

import com.tianrui.api.req.quality.file.CertificationReq;
import com.tianrui.service.bean.quality.file.Certification;

public interface CertificationMapper {
	
	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
    /**
	 * 新增数据
	 */
    int insert(Certification record);
    /**
	 * 新增数据(动�??)
	 */
    int insertSelective(Certification record);
    /**
	 * 查询数据(主键id)
	 */
    Certification selectByPrimaryKey(String id);
    /**
	 * 查询数据(主键id,state)
	 */
    Certification selectOne(CertificationReq req);
    /**
	 * 更新数据(动�??)
	 */
    int updateByPrimaryKeySelective(Certification record);
    /**
	 * 更新数据
	 */
    int updateByPrimaryKey(Certification record);
    /**
     * 查询数据总数
     */
    int count(CertificationReq req);
    /**
     * 分页查询
     */
    List<Certification> page(CertificationReq req);
}