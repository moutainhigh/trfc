package com.tianrui.service.impl.quality.sales;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.quality.file.IQualitySchemeItemService;
import com.tianrui.api.intf.quality.sales.IAssayReportService;
import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.api.resp.quality.file.QualitySchemeItemResp;
import com.tianrui.api.resp.quality.sales.AssayReportDetailResp;
import com.tianrui.api.resp.quality.sales.AssayReportResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.file.MaterialScheme;
import com.tianrui.service.bean.quality.file.QualityColumn;
import com.tianrui.service.bean.quality.file.QualityItem;
import com.tianrui.service.bean.quality.file.QualityScheme;
import com.tianrui.service.bean.quality.file.QualitySchemeItem;
import com.tianrui.service.bean.quality.sales.AssayReport;
import com.tianrui.service.bean.quality.sales.AssayReportItem;
import com.tianrui.service.bean.quality.sales.AssayReportMsg;
import com.tianrui.service.bean.quality.sales.SalesBatchnum;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.file.MaterialSchemeMapper;
import com.tianrui.service.mapper.quality.file.QualityColumnMapper;
import com.tianrui.service.mapper.quality.file.QualityItemMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeItemMapper;
import com.tianrui.service.mapper.quality.file.QualitySchemeMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportItemMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportMapper;
import com.tianrui.service.mapper.quality.sales.AssayReportMsgMapper;
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
	private SystemUserMapper systemUserMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private AssayReportItemMapper assayReportItemMapper;
	@Resource
	private AssayReportMsgMapper assayReportMsgMapper;
	@Resource
	private IQualitySchemeItemService qualitySchemeItemService;
	@Resource
	private QualitySchemeItemMapper qualitySchemeItemMapper;
	@Resource
	private QualityColumnMapper qualityColumnMapper;
	@Resource
	private QualityItemMapper qualityItemMapper;
	@Resource
	private MaterialSchemeMapper materialSchemeMapper;
	@Override
	public Result delete(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())) {
			AssayReport report = new AssayReport();
			report.setId(req.getId());
			// 将状态改为删除状态
			report.setState("0");
			// 更新数据到数据库
			int index = assayReportMapper.updateByPrimaryKeySelective(report);
			// 判断是否成功
			if (index > 0) {
				rs = Result.getSuccessResult();
			} else {
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result add(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if (req != null) {
			AssayReport report = new AssayReport();
			PropertyUtils.copyProperties(report, req);
			String id = UUIDUtil.getId();
			req.setId(id);
			report.setId(id);
			// 设置 创建信息和修改信息
			report.setCreator(req.getUser());
			report.setModifier(req.getUser());
			report.setModifytime(System.currentTimeMillis());
			report.setUtc(System.currentTimeMillis());
			// 设置为 正常状态
			report.setState("1");
			report.setReporttype(req.getReporttype());
			// 校验批号报告唯一
			AssayReportReq r = new AssayReportReq();
			r.setBatchnumid(req.getBatchnumid());
			r.setReporttype(req.getReporttype());
			int a = assayReportMapper.count(r);
			if (a > 0) {
				rs.setCode("111111");
				rs.setError("请确认批号或者报告是否已存在！");
			} else {
				// 保存数据到数据库
				int index = assayReportMapper.insertSelective(report);
				// 判断操作是否成功
				if (index > 0 && saveTestval(req) > 0) {
					rs = Result.getSuccessResult();
				} else {
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return rs;
	}

	@Override
	public Result update(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())) {
			// 转换类型
			AssayReport report = new AssayReport();
			PropertyUtils.copyProperties(report, req);
			// 设置 修改者和时间
			report.setModifier(req.getUser());
			report.setModifytime(System.currentTimeMillis());
			report.setUtc(System.currentTimeMillis());
			if (req.getAuditstate() != null) {

				if (req.getAuditstate() != "0") {
					report.setAuditer(req.getUser());
					report.setAudittime(System.currentTimeMillis());
					saveReportMsg(req);
				}
			}

			// 更新数据到数据库
			int index = assayReportMapper.updateByPrimaryKeySelective(report);
			// 判断操作是否成功
			if (index > 0 && saveTestval(req) > 0) {
				rs = Result.getSuccessResult();
			} else {
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if (req != null) {
			req.setState("1");
			// 创建一个分页封装类对象
			PaginationVO<AssayReportResp> page = new PaginationVO<AssayReportResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			// 设置分页查询开始位置,和数据量
			req.setStart(pageSize * (pageNo - 1));
			req.setLimit(pageSize);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			// 通过批号查询批号id
			if (StringUtils.isNotBlank(req.getBatchcode())) {
				SalesBatchnum batch = salesBatchnumMapper.selectByFactoryCode(req.getBatchcode());
				if (batch != null) {
					req.setBatchnumid(batch.getId());
				}
			}
			// 获取数据总数
			int total = assayReportMapper.count(req);
			page.setTotal(total);
			List<AssayReportResp> resps = new ArrayList<AssayReportResp>();
			// 判断是否可以查出数据
			if (total > 0) {
				// 遍历集合,转换为Resp类型
				List<AssayReport> list = assayReportMapper.page(req);
				for (AssayReport report : list) {
					AssayReportResp resp = new AssayReportResp();
					PropertyUtils.copyProperties(resp, report);
					// 获取批号
					if (resp.getBatchnumid() != null) {
						SalesBatchnum batch = salesBatchnumMapper.selectByPrimaryKey(resp.getBatchnumid());
						if (batch != null) {
							resp.setBatchcode(batch.getFactorycode());
						}
					}
					// 获取物料品种及水泥名称
					if (resp.getMschemeid() != null) {
						MaterialScheme mscheme = materialSchemeMapper.selectOne(resp.getMschemeid());
						if (mscheme != null) {
							resp.setMaterialtype(mscheme.getMaterialtype());
							MaterielManage mm = materielManageMapper.selectByPrimaryKey(mscheme.getMaterialid());
							if (mm != null) {
								resp.setMaterialname(mm.getName());
							}
						}
					}
					// 获取制单者
					if (resp.getCreator() != null) {
						SystemUser sc = systemUserMapper.selectByPrimaryKey(resp.getCreator());
						if (sc != null) {
							resp.setCreator(sc.getName());
						}
					}
					// 获取审核者
					if (resp.getAuditer() != null) {
						SystemUser sc = systemUserMapper.selectByPrimaryKey(resp.getAuditer());
						if (sc != null) {
							resp.setAuditer(sc.getName());
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
		if (req != null && StringUtils.isNotBlank(req.getId())) {
			AssayReport report = assayReportMapper.selectOne(req.getId());
			AssayReportResp resp = new AssayReportResp();
			PropertyUtils.copyProperties(resp, report);
			// 获取批号
			if (resp.getBatchnumid() != null) {
				SalesBatchnum batch = salesBatchnumMapper.selectByPrimaryKey(resp.getBatchnumid());
				if (batch != null) {
					resp.setBatchcode(batch.getFactorycode());
				}
			}
			// 获取物料品种及水泥名称
			if (resp.getMschemeid() != null) {
				MaterialScheme mscheme = materialSchemeMapper.selectOne(resp.getMschemeid());
				if (mscheme != null) {
					resp.setMaterialtype(mscheme.getMaterialtype());
					MaterielManage mm = materielManageMapper.selectByPrimaryKey(mscheme.getMaterialid());
					if (mm != null) {
						resp.setMaterialname(mm.getName());
					}
				}
			}
			// 获取质检项目名称
			if (StringUtils.isNotBlank(resp.getQschemeid())) {
				QualityScheme scheme = qualitySchemeMapper.selectOne(resp.getQschemeid());
				if (scheme != null) {
					resp.setQschemename(scheme.getName());
				}
			}
			// 获取制单者
			if (resp.getCreator() != null) {
				SystemUser sc = systemUserMapper.selectByPrimaryKey(resp.getCreator());
				if (sc != null) {
					resp.setCreator(sc.getName());
				}
			}
			// 获取审核者
			if (resp.getAuditer() != null) {
				SystemUser sc = systemUserMapper.selectByPrimaryKey(resp.getAuditer());
				if (sc != null) {
					resp.setAuditer(sc.getName());
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(resp);
		}
		return rs;
	}

	// @Override
	// public Result mschemeData() throws Exception {
	// MaterialSchemeReq req = new MaterialSchemeReq();
	// req.setState("1");
	// req.setLimit(0);
	// List<MaterialScheme> list = materialSchemeMapper.page(req);
	// List<MaterialSchemeResp> resps = new ArrayList<MaterialSchemeResp>();
	// if(list!=null){
	// for(MaterialScheme scheme : list){
	// MaterialSchemeResp resp = new MaterialSchemeResp();
	// resp.setId(scheme.getId());
	// resp.setMaterialtype(scheme.getMaterialtype());
	// if(StringUtils.isNotBlank(scheme.getMaterialid())){
	// MaterielManage mm =
	// materielManageMapper.selectByPrimaryKey(scheme.getMaterialid());
	// if(mm!=null){
	// resp.setMaterialname(mm.getName());
	// }
	// }
	// resps.add(resp);
	// }
	// }
	// Result rs = Result.getSuccessResult();
	// rs.setData(resps);
	// return rs;
	// }
	@Transactional
	private int saveTestval(AssayReportReq req) {

		int index = 1;
		if (StringUtils.isNotBlank(req.getArrStr())) {
			// 将字符串转换为json数组
			JSONArray json = JSONArray.parseArray(req.getArrStr());
			for (int i = 0; i < json.size(); i++) {
				// json对象转换为java对象
				AssayReportItem ari = JSONArray.toJavaObject(json.getJSONObject(i), AssayReportItem.class);
				ari.setAssayid(req.getId());
				ari.setModifier(req.getUser());
				ari.setModifytime(System.currentTimeMillis());
				ari.setUtc(System.currentTimeMillis());
				AssayReportItem item = assayReportItemMapper.findOne(ari);

				if (item != null) {
					ari.setId(item.getId());
					index = assayReportItemMapper.updateByPrimaryKeySelective(ari);
				} else {
					ari.setId(UUIDUtil.getId());
					ari.setCreator(req.getUser());
					ari.setCreatetime(System.currentTimeMillis());
					index = assayReportItemMapper.insertSelective(ari);
				}
				if (index <= 0) {
					return -1;
				}
			}
		}
		return index;
	}

	@Override
	public Result findReportMsg(AssayReportReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if (req != null) {
			List<AssayReportMsg> list = assayReportMsgMapper.selectByReportid(req.getId());
			if (list != null) {
				for (AssayReportMsg msg : list) {
					if (StringUtils.isNotBlank(msg.getCreator())) {
						SystemUser su = systemUserMapper.selectByPrimaryKey(msg.getCreator());
						if (su != null) {
							msg.setCreator(su.getName());
						}
					}
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(list);
		}
		return rs;
	}

	private int saveReportMsg(AssayReportReq req) throws Exception {
		AssayReportMsg msg = new AssayReportMsg();
		msg.setId(UUIDUtil.getId());
		msg.setReportid(req.getId());
		msg.setAudit(req.getAudit());
		msg.setRemark(req.getRecord());
		msg.setCreator(req.getUser());
		msg.setCreatetime(System.currentTimeMillis());
		msg.setModifier(req.getUser());
		msg.setModifytime(System.currentTimeMillis());
		msg.setUtc(System.currentTimeMillis());
		int index = assayReportMsgMapper.insertSelective(msg);
		return index;
	}

	/**
	 * 根据批号查询批号详情
	 */
	@Override
	public Result findSelectDetail(String factorycode) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		// 创建一个详情返回对象
		AssayReportDetailResp detailResp = new AssayReportDetailResp();
		// 根据批号查询销售批号对应的数据
		SalesBatchnum salesBatchnum = salesBatchnumMapper.selectByFactoryCode(factorycode);
		detailResp.setFactoryCode(salesBatchnum.getFactorycode());// 出厂编码
		MaterialScheme materialScheme =materialSchemeMapper.selectMaterial(salesBatchnum.getId());
		if(materialScheme!=null){
			detailResp.setGradeintensity(materialScheme.getStrength());
		}
		// 创建销售化验报告req对象
		AssayReportReq req = new AssayReportReq();
		// 将批号id放到req对象
		req.setBatchnumid(salesBatchnum.getId());
		// 根据批号id查询数据
		List<AssayReport> list = assayReportMapper.selectBatchnumid(req);
		//质检项目的检测值
		Map<Object, Object> map = new HashMap<Object, Object>();
		List liSist3 = new ArrayList<>();//3天抗压集合
		List sist28 = new ArrayList<>();//28天抗压集合
		List flexural3 = new ArrayList<>();//3天抗折集合
		List flexural28 = new ArrayList<>();//28天抗折集合
		if (list != null && !list.isEmpty()) {
			for (AssayReport assay : list) {// 遍历list
				
				List<QualitySchemeItemResp>  qualityList= getAssayItem(assay.getQschemeid(),assay.getId());
					for(QualitySchemeItemResp itemResp:qualityList){
						if(assay.getReporttype().equals("0")){
							map.put(itemResp.getEname(), itemResp.getTestval());
							if(itemResp.getEname().equals("compress")||itemResp.getEname().equals("compress1")||itemResp.getEname().equals("compress2")
									||itemResp.getEname().equals("compress3")||itemResp.getEname().equals("compress4")||itemResp.getEname().equals("compress5")){
								liSist3.add(itemResp.getTestval());
							}
							if(itemResp.getEname().equals("bending")||itemResp.getEname().equals("bending1")||itemResp.getEname().equals("bending2")){
								flexural3.add(itemResp.getTestval());
							}
						}else{
							if(itemResp.getEname().equals("compress")||itemResp.getEname().equals("compress1")||itemResp.getEname().equals("compress2")
									||itemResp.getEname().equals("compress3")||itemResp.getEname().equals("compress4")||itemResp.getEname().equals("compress5")){
								sist28.add(itemResp.getTestval());
							}
							if(itemResp.getEname().equals("bending")||itemResp.getEname().equals("bending1")||itemResp.getEname().equals("bending2")){
								flexural28.add(itemResp.getTestval());
							}
						}
					}
			}
		}
		detailResp.setMaterName("石灰石");
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Long time=new Long(System.currentTimeMillis());  
	    String d = format.format(time); 
		detailResp.setCreateTime(d);
		detailResp.setCustomer("什么名字啊");
		detailResp.setFactoryTime("2017-12-22");
		detailResp.setVehicleNo("京A12345");
		detailResp.setNumber("40");
		detailResp.setQualityRs((HashMap<Object, Object>) map);// 检测值集合
		detailResp.setSist3(liSist3);
		detailResp.setSist28(sist28);
		detailResp.setFlexural3(flexural3);
		detailResp.setFlexural28(flexural28);
		if(flexural3!=null&&!flexural3.isEmpty()){
			detailResp.setAvgFlexural3(String.valueOf(getAvg(flexural3)));
		}
		if(flexural28!=null&&!flexural28.isEmpty()){
			detailResp.setAvgFlexural28(String.valueOf(getAvg(flexural28)));
		}
		if(liSist3!=null&&!liSist3.isEmpty()){
			detailResp.setAvgSist3(String.valueOf(getAvg(liSist3)));
		}
		if(sist28!=null&&!sist28.isEmpty()){
			detailResp.setAvgSist28(String.valueOf(getAvg(sist28)));
		}
		rs.setData(detailResp);
		return rs;
	}

	/**
	 * 根据批号id校验报告天数
	 */
	@Override
	public Result selectBatchnumid(AssayReportReq req) throws Exception {
		// TODO Auto-generated method stub
		Result rs = Result.getSuccessResult();
		int a = assayReportMapper.count(req);
		if (a >= 2) {
			rs.setCode("111111");
			rs.setError("该批号已有其他报告，请选择其他批号！");
		} else if (a < 2 && a >= 0) {
			List<AssayReport> list = assayReportMapper.selectBatchnumid(req);
			// if(list!=null&&!list.isEmpty()){
			rs.setData(list);
			// }
		}
		return rs;
	}
	
	//传入物料id   查询检测值
	private List<QualitySchemeItemResp> getAssayItem(String qschemeid,String id )  throws Exception {
		QualitySchemeItemReq req =new QualitySchemeItemReq();
		req.setState("1");
		req.setSchemeid(qschemeid);
		req.setAssayid(id);
		List<QualitySchemeItem> list = qualitySchemeItemMapper.findBySchemeId(req);
		//转换为resp集合
		List<QualitySchemeItemResp> resps = new ArrayList<QualitySchemeItemResp>();
		if(list!=null && !list.isEmpty()){
			for(QualitySchemeItem q : list){
				QualitySchemeItemResp resp = new QualitySchemeItemResp();
				PropertyUtils.copyProperties(resp,q);
				//获取物料名称
				QualityScheme qs = qualitySchemeMapper.selectOne(q.getSchemeid());
				//在该对象不会空的情况下,通过id获取物料的名称
				if(qs!=null){
					MaterielManage manage = materielManageMapper.selectByPrimaryKey(qs.getMaterialid());
					if(manage!=null){
						resp.setMaterialname(manage.getName());
					}
				}
				//获取item对象
				QualityItem qi = qualityItemMapper.selectOne(resp.getItemid());
				if(qi!=null){
					resp.setItemcode(qi.getCode());
					resp.setItemname(qi.getName());
					resp.setUnits(qi.getUnits());
					resp.setLine(qi.getLine());
					resp.setEname(qi.getEname());
					if(StringUtils.isNotBlank(req.getAssayid())){
						AssayReportItem itemReq = new AssayReportItem();
						itemReq.setAssayid(req.getAssayid());
						itemReq.setItemid(resp.getItemid());
						AssayReportItem reportItem = assayReportItemMapper.findOne(itemReq);
						if(reportItem!=null){
							resp.setTestval(reportItem.getTestval());
						}
					}
				}
				resps.add(resp);
			}
		}
		return resps;
	}
	
	/**
	 * 求平均值方法
	 * @Title: getAvg 
	 * @Description: TODO
	 * @param @return   
	 * @return double    
	 * @throws
	 */
	private double getAvg (List list){
		int sum = 0;
		double e = 0;
		for (int i = 0; i < list.size(); i++) {
		String a =(String) list.get(i);
		 int val = Integer.valueOf(a).intValue();
		 sum += val;
		}
		e=sum/list.size();
		return e;
	}
}
