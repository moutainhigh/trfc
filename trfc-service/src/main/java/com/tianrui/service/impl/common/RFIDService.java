package com.tianrui.service.impl.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.common.IRFIDService;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 卡务管理Service
 * @author zhanggaohao
 * @createtime 2016年12月25日 上午9:41:24
 * @classname CardService.java
 */
@Service
public class RFIDService implements IRFIDService {
	
	@Autowired
	private RFIDMapper rfidMapper;
	
	@Transactional
	@Override
	public Result save(RFIDReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getRfid())){
			RFID db =rfidMapper.selectByPrimaryKey(req.getRfid());
			if(db ==null){
				RFID save = new RFID();
				save.setRfid(req.getRfid());
				save.setState(true);
				save.setCreatetime(System.currentTimeMillis());
				save.setModifytime(System.currentTimeMillis());
				save.setModifier(req.getCurrUid());
				save.setCreator(req.getCurrUid());
				rfidMapper.insert(save);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}
		}
		return rs;
	}
	
}
