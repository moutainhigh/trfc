package com.tianrui.service.impl.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.common.IBillTypeService;
import com.tianrui.api.req.common.BillTypeQuery;
import com.tianrui.api.resp.common.BillTypeResp;
import com.tianrui.service.bean.common.BillType;
import com.tianrui.service.mapper.common.BillTypeMapper;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class BillTypeService implements IBillTypeService {
	
	@Autowired
	private BillTypeMapper billTypeMapper;
	
	@Override
	public Result findListByParmas(BillTypeQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		BillType type = new BillType();
		if (query != null) {
			PropertyUtils.copyProperties(type, query);
		}
		type.setState("1");
		List<BillType> list = billTypeMapper.selectSelective(type);
		result.setData(copyBeanList2RespList(list));
		return result;
	}

	@Override
	public List<BillTypeResp> autoCompleteSearch(String trim) throws Exception {
		return copyBeanList2RespList(billTypeMapper.autoCompleteSearch(trim, "1"));
	}

	private List<BillTypeResp> copyBeanList2RespList(List<BillType> list) throws Exception {
		List<BillTypeResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<BillTypeResp>();
			for(BillType bean : list){
				listResp.add(copyBean2Resp(bean));
			}
		}
		return listResp;
	}
	
	private BillTypeResp copyBean2Resp(BillType bean) throws Exception {
		BillTypeResp resp = null;
		if(bean != null){
			resp = new BillTypeResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
