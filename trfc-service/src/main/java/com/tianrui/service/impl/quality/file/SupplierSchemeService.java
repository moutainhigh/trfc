package com.tianrui.service.impl.quality.file;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.quality.file.ISupplierSchemeService;
import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.api.req.quality.file.SupplierSchemeReq;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.api.resp.quality.file.QualitySchemeResp;
import com.tianrui.api.resp.quality.file.SupplierSchemeResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.quality.file.SupplierScheme;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.file.SupplierSchemeMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SupplierSchemeService implements ISupplierSchemeService{
	@Resource
	private SupplierSchemeMapper supplierSchemeMapper;
	@Resource
	private SupplierManageMapper supplierManageMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private QualitySchemeMapper qualitySchemeMapper;
	@Resource
	private SystemUserMapper systemUserMapper;

	@Override
	@Transactional
	public Result delete(SupplierSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		//判断参数和id不能为空
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//删除数据库中对应的数据
			int index = supplierSchemeMapper.deleteByPrimaryKey(req.getId());
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
	public Result update(SupplierSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		//判断参数和id不能为空
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			SupplierScheme ss = new SupplierScheme();
			PropertyUtils.copyProperties(ss, req);
			ss.setModifier(req.getUser());
			ss.setModifytime(System.currentTimeMillis());
			ss.setUtc(System.currentTimeMillis());
			int index = supplierSchemeMapper.updateByPrimaryKeySelective(ss);
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	//事务控制
	@Transactional
	public Result add(SupplierSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		//判断参数不能为空
		if(req!=null){
			//数据转换
			SupplierScheme ss = new SupplierScheme();
			PropertyUtils.copyProperties(ss, req);
			//获取id
			ss.setId(UUIDUtil.getId());
			//获取用户
			ss.setCreator(req.getUser());
			//创建时间已获取,所以不用重新获得
			ss.setModifier(req.getUser());
			ss.setModifytime(System.currentTimeMillis());
			//设置数据同步时间戳
			ss.setUtc(System.currentTimeMillis());
			//保存数据到数据库
			int index = supplierSchemeMapper.insertSelective(ss);
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
	@Transactional(readOnly=true)
	public Result page(SupplierSchemeReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		//判断参数不能为空
		if(req!=null){
			PaginationVO<SupplierSchemeResp> page = new PaginationVO<SupplierSchemeResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			//设置开始位置,和每页数据量
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			//获取数据总数
			int total = supplierSchemeMapper.count(req);
			page.setTotal(total);
			//创建一个出参集合
			List<SupplierSchemeResp> resps = new ArrayList<SupplierSchemeResp>();
			//判断是否有数据可查询
			if(total>0){
				List<SupplierScheme> list = supplierSchemeMapper.page(req);
				if(list!=null && !list.isEmpty()){
					//遍历集合,并转换类型
					for(SupplierScheme c : list){
						SupplierSchemeResp resp = new SupplierSchemeResp();
						PropertyUtils.copyProperties(resp, c);
						//获取供应商名称
						if(StringUtils.isNotBlank(c.getSupplierid())){
							SupplierManage sm = supplierManageMapper.selectByPrimaryKey(c.getSupplierid());
							if(sm!=null){
								resp.setSuppliername(sm.getName());
							}
						}
						//获取物料名称
						if(StringUtils.isNotBlank(c.getMaterialid())){
							MaterielManage mm = materielManageMapper.selectByPrimaryKey(c.getMaterialid());
							if(mm!=null){
								resp.setMaterialname(mm.getName());
							}
						
						}
						//获取质检方案名称
						if(StringUtils.isNotBlank(c.getSchemeid())){
							QualityScheme qs = qualitySchemeMapper.selectByPrimaryKey(c.getSchemeid());
							if(qs!=null){
								resp.setSchemename(qs.getName());
							}
						}
						//获取用户名
						if(StringUtils.isNotBlank(c.getCreator())){
							SystemUser su = systemUserMapper.selectByPrimaryKey(c.getCreator());
							if(su!=null){
								resp.setCreator(su.getName());
							}
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
		@Override
		@Transactional(readOnly=true)
		public Result getSchemeData(SupplierSchemeReq req) throws Exception {
			Result rs = Result.getParamErrorResult();
			//判断参数不能为空
			if(req!=null){
				QualitySchemeReq r = new QualitySchemeReq();
				//设置物料id
				r.setMaterialid(req.getMaterialid());
				r.setInvalid(req.getInvalid());
				//根据物料id查询数据 并转换类型
				List<QualityScheme> list = qualitySchemeMapper.page(r);
				List<QualitySchemeResp> resps = new ArrayList<QualitySchemeResp>();
				if(list!=null && !list.isEmpty()){
					for(QualityScheme qs : list){
						QualitySchemeResp resp = new QualitySchemeResp();
						PropertyUtils.copyProperties(resp, qs);
						resps.add(resp);
					}
				}
				rs = Result.getSuccessResult();
				rs.setData(resps);
				}
			return rs;
		}

		@Override
		public Result getSupplierData(SupplierSchemeReq req) throws Exception {
			Result rs = Result.getParamErrorResult();
			//判断参数不能为空
			if(req!=null){
				SupplierManageQuery query = new SupplierManageQuery();
				//设置 开始位置为-1,查询所有数据
				query.setStart(-1);
				//查询数据 并转换类型
				List<SupplierManage> list = supplierManageMapper.findSupplierPage(query);
				List<SupplierManageResp> resps = new ArrayList<SupplierManageResp>();
				if(list!=null && !list.isEmpty()){
					for(SupplierManage qs : list){
						SupplierManageResp resp = new SupplierManageResp();
						PropertyUtils.copyProperties(resp, qs);
						resps.add(resp);
					}
				}
				rs = Result.getSuccessResult();
				rs.setData(resps);
			}
			return rs;
		}

	}
