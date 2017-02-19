package com.tianrui.service.impl.quality.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.quality.file.IMaterialSchemeService;
import com.tianrui.api.req.quality.file.MaterialSchemeReq;
import com.tianrui.api.resp.quality.file.MaterialSchemeResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.MaterialScheme;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.MaterialSchemeMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class MaterialSchemeServcie implements IMaterialSchemeService {

	@Resource
	private MaterialSchemeMapper materialSchemeMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;

	@Override
	@Transactional
	public Result delete(MaterialSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		//参数不能为空,id不能为空
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//删除数据
			int index = materialSchemeMapper.deleteByPrimaryKey(req.getId());
			//判断是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	@Transactional
	public Result add(MaterialSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			MaterialScheme ms = new MaterialScheme();
			PropertyUtils.copyProperties(ms, req);
			//获取id
			ms.setId(UUIDUtil.getId());
			//获取创建者和修改者
			ms.setCreator(req.getUser());
			ms.setCreatetime(System.currentTimeMillis());
			ms.setModifier(req.getUser());
			ms.setModifytime(System.currentTimeMillis());
			//设置同步时间戳
			ms.setUtc(System.currentTimeMillis());
			//更新数据
			int index = materialSchemeMapper.insertSelective(ms);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	@Transactional
	public Result update(MaterialSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			MaterialScheme ms = new MaterialScheme();
			PropertyUtils.copyProperties(ms, req);
			//设置修改者
			ms.setModifier(req.getUser());
			ms.setModifytime(System.currentTimeMillis());
			ms.setUtc(System.currentTimeMillis());
			//更新数据
			int index = materialSchemeMapper.updateByPrimaryKeySelective(ms);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(MaterialSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<MaterialSchemeResp> page = new PaginationVO<MaterialSchemeResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			//设置开始位置,和每页数据量
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			//获取数据总数
			int total = materialSchemeMapper.count(req);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotal(total);
			List<MaterialSchemeResp> resps = new ArrayList<MaterialSchemeResp>();
			//通过数据总数判断是否需要进行查询数据
			if(total>0){
				//查询数据,并进行类型转换
				List<MaterialScheme> list = materialSchemeMapper.page(req);
				if(list!=null && !list.isEmpty())
					for(MaterialScheme ms : list){
						MaterialSchemeResp resp = new MaterialSchemeResp();
						PropertyUtils.copyProperties(resp, ms);
						//通过id获取名称,并赋值
						MaterielManage mm = materielManageMapper.selectByPrimaryKey(ms.getMaterialid());
						if(mm!=null){
							resp.setMaterialname(mm.getName());
						}
						//添加到集合
						resps.add(resp);
					}
			}
			//添加到page对象中
			page.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}

	

	@Override
	public Result checkMaterialType(MaterialSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			//查询数据总数(通过物料品种)
			int index = materialSchemeMapper.count(req);
			//通过index 判断操作是否成功
			if(index<0){
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}else{
				//操作成功   大于0返回false 等于0返回true
				rs = Result.getSuccessResult();
				rs.setData(index==0);
			}
		}
		return rs;
	}

}
