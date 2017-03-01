package com.tianrui.service.impl.quality.sales;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.quality.sales.IAssayReportService;
import com.tianrui.api.req.quality.file.MaterialSchemeReq;
import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.api.resp.quality.file.MaterialSchemeResp;
import com.tianrui.api.resp.quality.sales.AssayReportResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.MaterialScheme;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.quality.sales.AssayReport;
import com.tianrui.service.bean.quality.sales.AssayReportItem;
import com.tianrui.service.bean.quality.sales.SalesBatchnum;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.MaterialSchemeMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportItemMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportMapper;
import com.tianrui.service.mapper.quality.sales.SalesBatchnumMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class AssayReportService implements IAssayReportService {
	@Resource
	private AssayReportMapper assayReportMapper;
	@Resource
	private SalesBatchnumMapper salesBatchnumMapper;
	@Resource
	private QualitySchemeMapper qualitySchemeMapper;
	@Resource
	private MaterialSchemeMapper materialSchemeMapper;
	@Resource
	private SystemUserMapper systemUserMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private AssayReportItemMapper assayReportItemMapper;

	@Override
	public Result delete(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			AssayReport report = new AssayReport();
			report.setId(req.getId());
			//将状态改为删除状态
			report.setState("0");
			//更新数据到数据库
			int index = assayReportMapper.updateByPrimaryKeySelective(report);
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
	public Result add(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			AssayReport report = new AssayReport();
			PropertyUtils.copyProperties(report, req);
			String id = UUIDUtil.getId();
			req.setId(id);
			report.setId(id);
			//设置 创建信息和修改信息
			report.setCreator(req.getUser());
			report.setModifier(req.getUser());
			report.setModifytime(System.currentTimeMillis());
			report.setUtc(System.currentTimeMillis());
			//设置为 正常状态
			report.setState("1");
			//设置为 3天报告
			report.setReporttype("0");
			//保存数据到数据库
			int index = assayReportMapper.insertSelective(report);
			//判断操作是否成功
			if(index>0 && saveTestval(req)>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result update(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//转换类型
			AssayReport report = new AssayReport();
			PropertyUtils.copyProperties(report, req);
			//设置 修改者和时间
			report.setModifier(req.getUser());
			report.setModifytime(System.currentTimeMillis());
			report.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = assayReportMapper.updateByPrimaryKeySelective(report);
			//判断操作是否成功
			if(index>0 && saveTestval(req)>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			req.setState("1");
			//创建一个分页封装类对象
			PaginationVO<AssayReportResp> page = new PaginationVO<AssayReportResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			//设置分页查询开始位置,和数据量
			req.setStart(pageSize*(pageNo-1));
			req.setLimit(pageSize);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			//获取数据总数
			int total = assayReportMapper.count(req);
			page.setTotal(total);
			List<AssayReportResp> resps = new ArrayList<AssayReportResp>();
			//判断是否可以查出数据
			if(total>0){
				//遍历集合,转换为Resp类型
				List<AssayReport> list = assayReportMapper.page(req);
				for(AssayReport report : list){
					AssayReportResp resp = new AssayReportResp();
					PropertyUtils.copyProperties(resp, report);
					//获取批号
					if(resp.getBatchnumid()!=null){
						SalesBatchnum batch = salesBatchnumMapper.selectByPrimaryKey(resp.getBatchnumid());
						if(batch!=null){
							resp.setBatchcode(batch.getFactorycode());
						}
					}
					//获取物料品种及水泥名称
					if(resp.getMschemeid()!=null){
						MaterialScheme mscheme = materialSchemeMapper.selectOne(resp.getId());
						if(mscheme!=null){
							resp.setMaterialtype(mscheme.getMaterialtype());
							MaterielManage mm = 
									materielManageMapper.selectByPrimaryKey(mscheme.getMaterialid());
							if(mm!=null){
								resp.setMaterialname(mm.getName());
							}
						}
					}
					//获取制单者
					if(resp.getCreator()!=null){
						SystemUser sc = systemUserMapper.selectByPrimaryKey(resp.getCreator());
						if(sc!=null){
							resp.setCreator(sc.getName());
						}
					}

					resps.add(resp);
				}
			}
			page.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}

	@Override
	public Result findOne(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			AssayReport report = assayReportMapper.selectOne(req.getId());
			AssayReportResp resp = new AssayReportResp();
			PropertyUtils.copyProperties(resp, report);
			//获取批号
			if(resp.getBatchnumid()!=null){
				SalesBatchnum batch = salesBatchnumMapper.selectByPrimaryKey(resp.getBatchnumid());
				if(batch!=null){
					resp.setBatchcode(batch.getFactorycode());
				}
			}
			//获取物料品种及水泥名称
			if(resp.getMschemeid()!=null){
				MaterialScheme mscheme = materialSchemeMapper.selectOne(resp.getMschemeid());
				if(mscheme!=null){
					resp.setMaterialtype(mscheme.getMaterialtype());
					MaterielManage mm = 
							materielManageMapper.selectByPrimaryKey(mscheme.getMaterialid());
					if(mm!=null){
						resp.setMaterialname(mm.getName());
					}
				}
			}
			//获取质检项目名称
			if(StringUtils.isNotBlank(resp.getQschemeid())){
				QualityScheme scheme = qualitySchemeMapper.selectOne(resp.getQschemeid());
				if(scheme!=null){
					resp.setQschemename(scheme.getName());
				}
			}
			//获取制单者
			if(resp.getCreator()!=null){
				SystemUser sc = systemUserMapper.selectByPrimaryKey(resp.getCreator());
				if(sc!=null){
					resp.setCreator(sc.getName());
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(resp);
		}
		return rs;
	}

	@Override
	public Result mschemeData() throws Exception {
		MaterialSchemeReq req = new MaterialSchemeReq();
		req.setState("1");
		req.setLimit(0);
		List<MaterialScheme> list = materialSchemeMapper.page(req);
		List<MaterialSchemeResp> resps = new ArrayList<MaterialSchemeResp>();
		if(list!=null){
			for(MaterialScheme scheme : list){
				MaterialSchemeResp resp = new MaterialSchemeResp();
				resp.setId(scheme.getId());
				resp.setMaterialtype(scheme.getMaterialtype());
				if(StringUtils.isNotBlank(scheme.getMaterialid())){
					MaterielManage mm = materielManageMapper.selectByPrimaryKey(scheme.getMaterialid());
					if(mm!=null){
						resp.setMaterialname(mm.getName());
					}
				}
				resps.add(resp);
			}
		}
		Result rs = Result.getSuccessResult();
		rs.setData(resps);
		return rs;
	}
	@Transactional
	private int saveTestval(AssayReportReq req){
		//将字符串转换为json数组
		JSONArray json = JSONArray.parseArray(req.getArrStr());
		int index = 0;
		for(int i=0;i<json.size();i++){
			//json对象转换为java对象
			AssayReportItem ari = JSONArray.toJavaObject(json.getJSONObject(i), AssayReportItem.class);
			ari.setAssayid(req.getId());
			ari.setModifier(req.getUser());
			ari.setModifytime(System.currentTimeMillis());
			ari.setUtc(System.currentTimeMillis());
			AssayReportItem item = assayReportItemMapper.findOne(ari);
			
			if(item!=null){
				index = assayReportItemMapper.updateByPrimaryKeySelective(ari);
			}else{
				ari.setId(UUIDUtil.getId());
				ari.setCreator(req.getUser());
				ari.setCreatetime(System.currentTimeMillis());
				index = assayReportItemMapper.insertSelective(ari);
			}
			if(index<=0){
				return -1;
			}
		}
		return index;
	}
}
