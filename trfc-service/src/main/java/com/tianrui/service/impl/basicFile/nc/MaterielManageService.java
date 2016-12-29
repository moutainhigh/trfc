package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.req.basicFile.nc.MaterielManageReq;
import com.tianrui.api.resp.basicFile.nc.MaterielManageResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;

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
	public PaginationVO<MaterielManageResp> page(MaterielManageReq req) throws Exception {
		PaginationVO<MaterielManageResp> page = null;
		if(req != null){
			page = new PaginationVO<MaterielManageResp>();
			req.setStart((req.getPageNo()-1)*req.getPageSize());
			req.setLimit(req.getPageSize());
			long count = materielManageMapper.findMaterielManagePageCount(req);
			if(count > 0){
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
	public MaterielManageResp findOne(String id) throws Exception {
		MaterielManageResp resp = null;
		if(StringUtils.isNotBlank(id)){
			resp = new MaterielManageResp();
			MaterielManage mater = materielManageMapper.selectByPrimaryKey(id);
			resp = copyBean2Resp(mater);
		}
		return resp;
	}

	@Override
	@Transactional
	public int deleteMateriel(String id) {
		int n = 0;
		if(StringUtils.isNotBlank(id)){
			n = materielManageMapper.deleteByPrimaryKey(id);
		}
		return n;
	}

	@Override
	@Transactional
	public int updateMateriel(MaterielManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			MaterielManage mater = new MaterielManage();
			PropertyUtils.copyProperties(mater, req);
//			mater.setModifier("");
			mater.setModifytime(System.currentTimeMillis());
			n = materielManageMapper.updateByPrimaryKeySelective(mater);
		}
		return n;
	}
	
	@Override
	@Transactional
	public int addMateriel(MaterielManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			MaterielManage mater = new MaterielManage();
			PropertyUtils.copyProperties(mater, req);
			mater.setId(UUIDUtil.getId());
			mater.setCreatetime(System.currentTimeMillis());
			n = materielManageMapper.insert(mater);
		}
		return n;
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
	
}
