package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.req.basicFile.nc.WarehouseManageSave;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 仓库管理Service
 * @author zhanggaohao
 * @createtime 2016年12月10日 上午10:21:40
 * @classname WarehouseManageService.java
 */
@Service
public class WarehouseManageService implements IWarehouseManageService {
	
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;

	@Override
	public PaginationVO<WarehouseManageResp> page(WarehouseManageQuery query) throws Exception {
		PaginationVO<WarehouseManageResp> page = null;
		if(query != null){
			page = new PaginationVO<WarehouseManageResp>();
			long count = warehouseManageMapper.findWarehouseManagePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<WarehouseManage> list = warehouseManageMapper.findWarehouseManagePage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Override
	public WarehouseManageResp findOne(WarehouseManageQuery query) throws Exception {
		if(StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(warehouseManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}

	@Override
	public Result addWarehouse(WarehouseManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			WarehouseManage wm = new WarehouseManage();
			wm.setCode(save.getCode());
			List<WarehouseManage> list = warehouseManageMapper.selectSelective(wm);
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				PropertyUtils.copyProperties(wm, save);
				wm.setId(UUIDUtil.getId());
				wm.setCreatetime(System.currentTimeMillis());
				if(warehouseManageMapper.insertSelective(wm) > 0){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result updateWarehouse(WarehouseManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			WarehouseManage wm = new WarehouseManage();
			PropertyUtils.copyProperties(wm, save);
			wm.setModifier(save.getCurrUId());
			wm.setModifytime(System.currentTimeMillis());
			if(warehouseManageMapper.updateByPrimaryKeySelective(wm) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result findListByParmas(WarehouseManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		WarehouseManage w = new WarehouseManage();
		if(query != null){
			PropertyUtils.copyProperties(w, query);
		}
		w.setState("1");
		List<WarehouseManage> list = warehouseManageMapper.selectSelective(w);
		result.setData(copyBeanList2RespList(list));
		return result;
	}

	@Override
	public List<WarehouseManageResp> autoCompleteSearch(String trim) throws Exception {
		return copyBeanList2RespList(warehouseManageMapper.autoCompleteSearch(trim));
	}
	
	private List<WarehouseManageResp> copyBeanList2RespList(List<WarehouseManage> list) throws Exception {
		List<WarehouseManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<WarehouseManageResp>();
			for(WarehouseManage wm : list){
				listResp.add(copyBean2Resp(wm));
			}
		}
		return listResp;
	}
	
	private WarehouseManageResp copyBean2Resp(WarehouseManage bean) throws Exception {
		WarehouseManageResp resp = null;
		if(bean != null){
			resp = new WarehouseManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result findMaxUtc(WarehouseManageQuery query) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(query !=null  ){
			Long max =warehouseManageMapper.findMaxUtc();
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			rs.setData(max);
		}
		return rs;
	}

	@Override
	public Result updateDataWithDC(List<JSONObject> list) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(CollectionUtils.isNotEmpty(list) ){
			Set<String> idSet = getAllDb();
			// 区分新增还是修改
			List<WarehouseManage>  toSave = new ArrayList<>();
			List<WarehouseManage>  toUpdate = new ArrayList<>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				warehouseManageMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( WarehouseManage item:toUpdate ){
					warehouseManageMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}

	
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<>();
		List<WarehouseManage> dbList =warehouseManageMapper.selectSelective(null);
		
		if( CollectionUtils.isNotEmpty(dbList) ){
			for(WarehouseManage manage:dbList){
				set.add(manage.getId());
			}
		}
		return set;
	}
	private WarehouseManage converJson2Bean(JSONObject jsonItem){
		WarehouseManage item  =new WarehouseManage();
		item.setId(jsonItem.getString("id"));
		item.setCode(jsonItem.getString("code"));
		item.setOrgid(jsonItem.getString("orgId"));
		item.setName(jsonItem.getString("name"));
		item.setState("1");
		item.setOrgname(jsonItem.getString("orgName"));
		item.setInternalcode(jsonItem.getString("internalcode"));
		item.setCreatetime(System.currentTimeMillis());
		item.setModifytime(System.currentTimeMillis());	
		item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		item.setRemarks(jsonItem.getString("remark"));
		return item;
	}

	@Override
	public List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req) {
		List<HandSetReturnResp> list = warehouseManageMapper.handSetQueryAll(req);
		return list;
	}
	
}
