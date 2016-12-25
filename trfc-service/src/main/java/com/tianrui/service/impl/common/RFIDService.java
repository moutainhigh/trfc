package com.tianrui.service.impl.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.common.IRFIDService;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.common.RFIDMapper;

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
	public int save(RFIDReq req) throws Exception {
		if(req != null){
			RFID rfid = new RFID();
			PropertyUtils.copyProperties(rfid, req);
			rfid.setState("1");
			return rfidMapper.insert(rfid);
		}
		return 0;
	}
	
	@Transactional
	@Override
	public int saveList(List<RFIDReq> list) throws Exception {
		if(list != null && list.size() > 0){
			for(RFIDReq req : list){
				req.setState("1");
			}
			return rfidMapper.saveList(list);
		}
		return 0;
	}

//	private List<CardResp> copyBeanList2RespList(List<Card> list) throws Exception {
//		List<CardResp> listResp = null;
//		if(list != null && list.size() > 0){
//			listResp = new ArrayList<CardResp>();
//			for(Card card : list){
//				listResp.add(copyBean2Resp(card));
//			}
//		}
//		return listResp;
//	}
//	
//	private CardResp copyBean2Resp(Card bean) throws Exception {
//		CardResp resp = null;
//		if(bean != null){
//			resp = new CardResp();
//			PropertyUtils.copyProperties(resp, bean);
//		}
//		return resp;
//	}
}
