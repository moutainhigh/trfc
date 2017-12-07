package com.tianrui.service.impl.quality.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.quality.file.IQualitySchemeService;
import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.api.resp.quality.file.QualitySchemeResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class QualitySchemeService implements IQualitySchemeService {
	@Resource
	private QualitySchemeMapper qualitySchemeMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private	SystemDataDictItemMapper systemDataDictItemMapper;
	@Override
	@Transactional
	public Result delete(QualitySchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//将数据的state更新为"0"(删除状态)
			QualityScheme scheme = new QualityScheme();
			scheme.setId(req.getId());
			scheme.setState("0");
			//更新数据
			int index = qualitySchemeMapper.updateByPrimaryKeySelective(scheme);
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
	public Result add(QualitySchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			QualityScheme qs = new QualityScheme();
			PropertyUtils.copyProperties(qs, req);
			//设置id
			qs.setId(UUIDUtil.getId());
			//设置创建者和修改者
			qs.setCreator(req.getUser());
			qs.setCreatetime(System.currentTimeMillis());
			qs.setModifier(req.getUser());
			qs.setModifytime(System.currentTimeMillis());
			qs.setUtc(System.currentTimeMillis());
			//新增数据默认为1(正常状态)
			qs.setState("1");
			//保存数据
			int index = qualitySchemeMapper.insertSelective(qs);
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
	public Result update(QualitySchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//转换类型
			QualityScheme qs = new QualityScheme();
			PropertyUtils.copyProperties(qs, req);
			//设置创建者和修改者
			qs.setModifier(req.getUser());
			qs.setModifytime(System.currentTimeMillis());
			qs.setUtc(System.currentTimeMillis());
			//更新数据
			int index = qualitySchemeMapper.updateByPrimaryKeySelective(qs);
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
	public Result page(QualitySchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			//设置为1(正常状态)
			req.setState("1");
			PaginationVO<QualitySchemeResp> page = new PaginationVO<QualitySchemeResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			//设置开始位置,和每页数据量
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			//获取数据总数
			int total = qualitySchemeMapper.count(req);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			page.setTotal(total);
			System.out.println(total);
			//创建一个出参集合
			List<QualitySchemeResp> resps = new ArrayList<QualitySchemeResp>();
			//判断是否有数据可查询
			if(total>0){
				List<QualityScheme> list = qualitySchemeMapper.page(req);
				if(list!=null && !list.isEmpty()){
					//遍历集合,并转换类型
					for(QualityScheme c : list){
						QualitySchemeResp resp = new QualitySchemeResp();
						PropertyUtils.copyProperties(resp, c);
						//通过物料id 查询物料信息 获取物料名称
						MaterielManage m = materielManageMapper.selectByPrimaryKey(c.getMaterialid());
						if(m!=null){
							resp.setMaterialname(m.getName());
						}
						//通过单据id 查询单据信息,获取单据名称
						SystemDataDictItem sd = systemDataDictItemMapper.selectByPrimaryKey(c.getBills());
						if(sd!=null){
							resp.setBillsname(sd.getName());
						}
						//将对象放入集合
						resps.add(resp);
					}
				}
			}
			page.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}
	public Result billsData() throws Exception{
		Result rs = Result.getSuccessResult();
		//设置单据类型id
		String dictId = "dbca68d35daa485fa07a813504511d03";
		//查询所有单据类型
		List<SystemDataDictItem> list = systemDataDictItemMapper.selectByDictId(dictId);
		rs.setData(list);
		return rs;
	}

	@Override
	public Result findById(QualitySchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//查询数据
			QualityScheme qs = qualitySchemeMapper.selectOne(req.getId());
			QualitySchemeResp resp = new QualitySchemeResp();
			if(qs!=null){
				PropertyUtils.copyProperties(resp, qs);
				//通过物料id 查询物料信息 获取物料名称
				MaterielManage m = materielManageMapper.selectByPrimaryKey(qs.getMaterialid());
				if(m!=null){
					resp.setMaterialname(m.getName());
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(resp);
		}
		return rs;
	}

	@Override
	public Result autoCompleteSearch(String likeName ,String type) throws Exception {
		Result rs = Result.getSuccessResult();
		List<QualityScheme> list = qualitySchemeMapper.autoCompleteSearch(likeName,type);
		List<QualitySchemeResp> resps = new ArrayList<QualitySchemeResp>();
		if(list!=null && list.size()>0){
			for(QualityScheme ms : list){
				QualitySchemeResp resp = new QualitySchemeResp();
				PropertyUtils.copyProperties(resp, ms);
				if(StringUtils.isNotBlank(ms.getMaterialid())){
					MaterielManage mm = materielManageMapper.selectByPrimaryKey(ms.getMaterialid());
					if(mm!=null){
						resp.setMaterialname(mm.getName());
					}
				}
				resps.add(resp);
			}
		}
		rs.setData(resps);
		return rs;
	}
	
	/**
	 * 根据项目类型查找物料
	 */
	@Override
	public Result selectaddType(QualitySchemeReq req) throws Exception {
		Result rs = Result.getSuccessResult();
		MaterielManage record = new MaterielManage();
		record.setBusinesstype(req.getType());
		List<MaterielManage> list =materielManageMapper.selectSelective(record);
		if(list!=null && !list.isEmpty()){
			rs.setData(list);
		}else{
			rs.setCode("111111");
			rs.setError("该类型没有对应的物料！");
		}
		return rs;
	}

}
