package com.tianrui.service.impl.quality.sales;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.quality.sales.IAssayReportService;
import com.tianrui.api.req.quality.file.MaterialSchemeReq;
import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.api.resp.quality.sales.AssayReportResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.MaterialScheme;
import com.tianrui.service.bean.quality.sales.AssayReport;
import com.tianrui.service.bean.quality.sales.SalesBatchnum;
import com.tianrui.service.bean.system.base.SystemCode;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.MaterialSchemeMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportMapper;
import com.tianrui.service.mapper.quality.sales.SalesBatchnumMapper;
import com.tianrui.service.mapper.system.base.SystemCodeMapper;
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
	private SystemCodeMapper systemCodeMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	
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
			report.setId(UUIDUtil.getId());
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
			if(index>0){
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
			if(index>0){
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
						MaterialSchemeReq msReq = new MaterialSchemeReq();
						msReq.setId(resp.getMschemeid());
						msReq.setState("1");
						MaterialScheme mscheme = materialSchemeMapper.selectOne(msReq);
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
						SystemCode sc = systemCodeMapper.selectByPrimaryKey(resp.getCreator());
						if(sc!=null){
							resp.setCreator(sc.getName());
						}
					}
					
					resps.add(resp);
				}
			}
		}
		return rs;
	}

	@Override
	public Result findOne(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			req.setState("1");
			AssayReport report = assayReportMapper.selectOne(req);
			rs = Result.getSuccessResult();
			rs.setData(report);
		}
		return rs;
	}

}
