package com.tianrui.service.mapper.businessManage.poundNoteMaintain;

import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNoteMsg;

public interface PoundNoteMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(PoundNoteMsg record);

    int insertSelective(PoundNoteMsg record);

    PoundNoteMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PoundNoteMsg record);

    int updateByPrimaryKey(PoundNoteMsg record);
}