package com.tianrui.service.mapper.access;

import java.util.List;

import com.tianrui.service.bean.access.AccessRecord;

public interface AccessRecordMapper {
	/**
	 * ɾ�����(����ID)
	 */
    int deleteByPrimaryKey(String id);
    /**
     * �������
     */
    int insert(AccessRecord record);
    /**
     * �������(��̬)
     */
    int insertSelective(AccessRecord record);
    /**
     * ��ѯ���(����ID)
     */
    AccessRecord selectByPrimaryKey(String id);
    /**
     * �������(��̬)
     */
    int updateByPrimaryKeySelective(AccessRecord record);
    /**
     * �������
     */
    int updateByPrimaryKey(AccessRecord record);
    /**
     * ��ѯ�Ž��¼
     * @param access
     * @return
     */
	List<AccessRecord> selectSelective(AccessRecord access);
}