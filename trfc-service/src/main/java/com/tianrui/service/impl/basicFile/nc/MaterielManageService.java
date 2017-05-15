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
import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.MaterielManageSave;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
//import com.tianrui.api.resp.basicFile.nc.MaterielManageVO;
import com.tianrui.api.resp.businessManage.app.AppMaterialResp;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 物料管理Service
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:28:46
 * @classname MaterielManageService.java
 */
@Service
public class MaterielManageService implements IMaterielManageService {
	
	@Autowired
	private MaterielManageMapper materielManageMapper;

	@Override
	public PaginationVO<MaterielManageResp> page(MaterielManageQuery req) throws Exception {
		PaginationVO<MaterielManageResp> page = null;
		if(req != null){
			page = new PaginationVO<MaterielManageResp>();
			req.setState("1");
			long count = materielManageMapper.findMaterielManagePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<MaterielManage> list = materielManageMapper.findMaterielManagePage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public Result updateMateriel(MaterielManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			MaterielManage mater = new MaterielManage();
			PropertyUtils.copyProperties(mater, save);
			mater.setModifier(save.getCurrUId());
			mater.setModifytime(System.currentTimeMillis());
			if(materielManageMapper.updateByPrimaryKeySelective(mater) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result findListByParmas(MaterielManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		MaterielManage m = new MaterielManage();
		if(query != null){
			PropertyUtils.copyProperties(m, query);
		}
		m.setState("1");
		List<MaterielManage> list = materielManageMapper.selectSelective(m);
		result.setData(copyBeanList2RespList(list));
		return result;
	}

	@Override
	public MaterielManageResp findOne(MaterielManageQuery query) throws Exception {
		if(query != null && StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(materielManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}

	@Override
	public List<MaterielManageResp> autoCompleteSearch(String likeName) throws Exception {
		return copyBeanList2RespList(materielManageMapper.autoCompleteSearch(likeName));
	}
	
	private List<MaterielManageResp> copyBeanList2RespList(List<MaterielManage> list) throws Exception {
		List<MaterielManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<MaterielManageResp>();
			for(MaterielManage mater : list){
				listResp.add(copyBean2Resp(mater));
			}
		}
		return listResp;
	}
	
	private MaterielManageResp copyBean2Resp(MaterielManage bean) throws Exception {
		MaterielManageResp resp = null;
		if(bean != null){
			resp = new MaterielManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result findMaxUtc(MaterielManageQuery query) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(query !=null  ){
			Long max =materielManageMapper.findMaxUtc();
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
			List<MaterielManage>  toSave = new ArrayList<>();
			List<MaterielManage>  toUpdate = new ArrayList<>();
			for(JSONObject jsonItem :list ){
				if( idSet.contains(jsonItem.get("id"))){
					toUpdate.add(converJson2Bean(jsonItem));
				}else{
					toSave.add(converJson2Bean(jsonItem));
				}
			}
			//新增的调批量保存
			if(CollectionUtils.isNotEmpty(toSave)){
				materielManageMapper.insertBatch(toSave);
			}
			//修改的就一个一个的调用修改
			if( CollectionUtils.isNotEmpty(toUpdate) ){
				for( MaterielManage item:toUpdate ){
					materielManageMapper.updateByPrimaryKeySelective(item);
				}
			}
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return rs;
	}
	
	private Set<String> getAllDb(){
		Set<String> set =new HashSet<>();
		List<MaterielManage> dbList =materielManageMapper.selectSelective(null);
		if( CollectionUtils.isNotEmpty(dbList) ){
			for(MaterielManage manage:dbList){
				set.add(manage.getId());
			}
		}
		return set;
	}
	private MaterielManage converJson2Bean(JSONObject jsonItem){
		MaterielManage item  =new MaterielManage();
		item.setId(jsonItem.getString("id"));
		item.setCode(jsonItem.getString("code"));
		item.setOrgid(Constant.ORG_ID);
		item.setName(jsonItem.getString("name"));
		item.setAbbrname(jsonItem.getString("shortName"));
		item.setState("1");
		item.setOrgname(Constant.ORG_NAME);
		item.setInternalcode(jsonItem.getString("innerCode"));
		item.setCreatetime(Long.valueOf(jsonItem.getString("createTime")));
		item.setModifytime(System.currentTimeMillis());	
		item.setUtc(Long.valueOf(jsonItem.getString("ts")));
		item.setRemarks(jsonItem.getString("remark"));
		item.setSpec(jsonItem.getString("spec"));
		item.setModel(jsonItem.getString("modelNumber"));
		return item;
	}


	@Override
	public PaginationVO<AppMaterialResp> materialList(AppQueryReq req) {
		PaginationVO<AppMaterialResp> page = null;
		if(req != null){
			page = new PaginationVO<AppMaterialResp>();
			long count = materielManageMapper.appQueryPageCount(req);
			if (count > 0) {
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppMaterialResp> list = materielManageMapper.appQueryPage(req);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req) {
		List<HandSetReturnResp> list = materielManageMapper.handSetQueryAll(req);
		return list;
	}
}
