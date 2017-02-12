package com.tianrui.service.impl.quality.sales.batchnum;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.quality.sales.batchnum.ISalesBatchnumService;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.quality.sales.batchnum.SalesBatchnumReq;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.resp.basicFile.nc.MaterielManageVO;
import com.tianrui.api.resp.quality.sales.batchnum.SalesBatchnumResp;
import com.tianrui.api.resp.system.auth.SystemRoleResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.sales.batchnum.SalesBatchnum;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.sales.batchnum.SalesBatchnumMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class SalesBatchnumService implements ISalesBatchnumService {

	@Resource
	private SalesBatchnumMapper salesBatchnumMapper;
	@Resource
	private MaterielManageMapper materielManageMapper;
	@Resource
	private SystemUserMapper systemUserMapper;



	/**
	 * 删除数据
	 */
	@Transactional
	public Result delete(SalesBatchnumReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//调用 删除方法 
			int index = salesBatchnumMapper.deleteByPrimaryKey(req.getId());
			if(index>0){
				//操作成功
				rs = Result.getSuccessResult();
			}else{
				//操作失败
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	/**
	 * 新增数据
	 */
	@Transactional
	public Result insertBatch(SalesBatchnumReq salesReq) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(salesReq!=null){
			List<SalesBatchnumReq> list = getListReq(salesReq);
			List<SalesBatchnum> batchnums = new ArrayList<SalesBatchnum>();
			//集合类型转换
			for(SalesBatchnumReq req : list){
				SalesBatchnum batchnum = new SalesBatchnum();
				//类型转换
				PropertyUtils.copyProperties(batchnum, req);
				//获取ID
				batchnum.setId(UUIDUtil.getId());
				//获取单据编号
				batchnum.setCode("HH"+(int)(Math.random()*10000));
				batchnum.setCreator(req.getUser());
				batchnum.setModifier(req.getUser());
				batchnum.setModifytime(System.currentTimeMillis());
				//对状态进行默认复制
				batchnum.setTeststate("0");
				batchnum.setBillsstate("1");
				batchnum.setAuditstate("0");
				batchnums.add(batchnum);
			}
			//调用批量添加
			int index = salesBatchnumMapper.insertBatch(batchnums);
			if(index>0){
				//操作成功
				rs = Result.getSuccessResult();
			}else{
				//操作失败
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	/**
	 * 通过req获取List集合
	 */
	public List<SalesBatchnumReq> getListReq(SalesBatchnumReq req){
		List<SalesBatchnumReq> list = new ArrayList<SalesBatchnumReq>();
		//将字符串转换为json数组
		JSONArray json = JSONArray.parseArray(req.getArrStr());
		//通过循环把数组中的数据添加到list集合中
		for(int i=0;i<json.size();i++){
			//json对象转换为java对象
			SalesBatchnumReq s = JSONArray.toJavaObject(json.getJSONObject(i), SalesBatchnumReq.class);
			s.setAssaytime(req.getAssaytime());
			s.setAssayer(req.getAssayer());
			s.setAssayorg(req.getAssayorg());
			s.setStarttime(req.getStarttime());
			s.setEndtime(req.getEndtime());
			s.setCreatetime(req.getCreatetime());
			s.setUser(req.getUser());
			list.add(s);
		}
		return list;
	}
	/**
	 * 更新数据
	 */
	@Transactional
	public Result update(SalesBatchnumReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			SalesBatchnum batchnum  = new SalesBatchnum();
			//类型转换
			PropertyUtils.copyProperties(batchnum, req);
			//判断是否审核
			if("1".equals(req.getAuditstate())){
				//添加审核人和时间
				batchnum.setAuditer(req.getUser());
				batchnum.setAudittime(System.currentTimeMillis());
			}
			//获取修改者和时间
			batchnum.setModifier(req.getUser());
			batchnum.setModifytime(System.currentTimeMillis());
			//更新数据到数据库
			int index = salesBatchnumMapper.updateByPrimaryKeySelective(batchnum);
			if(index>0){
				//操作成功
				rs = Result.getSuccessResult();
			}else{
				//操作失败
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	/**
	 * 分页查询
	 */
	public Result page(SalesBatchnumReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<SalesBatchnumResp> page = new PaginationVO<SalesBatchnumResp>();
			//获取数据总数
			int total = salesBatchnumMapper.count(req);
			//当前页
			int pageNo = req.getPageNo();
			//每页显示数据数
			int pageSize = req.getPageSize();
			//获取数据总数
			page.setTotal(total);
			//获取当前页
			page.setPageNo(pageNo);
			//获取每页显示的数据数
			page.setPageSize(pageSize);
			//设置分页查询开始位置 与 数量
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			List<SalesBatchnumResp> resps = new ArrayList<SalesBatchnumResp>();
			//判断是否有数据
			if(total>0){
				//获取分页数据
				List<SalesBatchnum> list = salesBatchnumMapper.page(req);
				//进行类型转换
				for(SalesBatchnum batchnum : list){
					SalesBatchnumResp resp = new SalesBatchnumResp();
					PropertyUtils.copyProperties(resp, batchnum);
					//获取审核人的名称
					if(StringUtils.isNotBlank(resp.getAuditer())){
						SystemUser auditer = systemUserMapper.selectByPrimaryKey(resp.getAuditer());
						if(auditer!=null){
							resp.setAuditer(auditer.getName());
						}
					}
					//获取物料名称
					if(StringUtils.isNotBlank(resp.getMaterial())){
						MaterielManage material = materielManageMapper.selectByPrimaryKey(resp.getMaterial());
						if(material!=null){
							resp.setMaterial(material.getName());
						}else{
							resp.setMaterial("");
						}
					}
					//获取化验人名称
					if(StringUtils.isNotBlank(resp.getAssayer())){
						SystemUser assayer = systemUserMapper.selectByPrimaryKey(resp.getAssayer());
						if(assayer!=null){
							resp.setAssayer(assayer.getName());
						}
					}
					resps.add(resp);
				}
			}
			page.setList(resps);
			//操作成功
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}
	@Override
	public Result materialData() throws Exception{
		//创建参数
		MaterielManageQuery req = new MaterielManageQuery();
		//设置限定条件,查询业务类型为 销售的信息
		//req.setBusinesstype("1");
		//查询
		List<MaterielManage> list1 = materielManageMapper.findMaterielManagePage(req);
		//结果集转化为vo类型的集合
		List<MaterielManageVO> list2 = new ArrayList<MaterielManageVO>();
		if(list1!=null && !list1.isEmpty()){
			for(MaterielManage manage : list1){
				MaterielManageVO vo = new MaterielManageVO();
				PropertyUtils.copyProperties(vo, manage);
				list2.add(vo);
			}
		}
		//操作成功
		Result rs = Result.getSuccessResult();
		rs.setData(list2);
		return rs;
	}

	@Override
	public Result selectById(SalesBatchnumReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			//查询数据
			SalesBatchnum b = salesBatchnumMapper.selectByPrimaryKey(req.getId());
			if(b!=null){
				//转化为出参类型
				SalesBatchnumResp resp = new SalesBatchnumResp();
				PropertyUtils.copyProperties(resp, b);
				//获取创建人的名称
				SystemUser creator = systemUserMapper.selectByPrimaryKey(resp.getCreator());
				resp.setCreator(creator.getName());

				//操作成功
				rs = Result.getSuccessResult();
				rs.setData(resp);
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	@Override
	public Result checkFactoryCode(SalesBatchnumReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getFactorycode())){
			//通过批号查询数据
			int index = salesBatchnumMapper.count(req);

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
	@Override
	public Result assayerData() throws Exception {
		Result rs = new Result();
		//创建参数
		SystemUserQueryReq req = new SystemUserQueryReq();

		//查询
		List<SystemUser> list1 = systemUserMapper.selectByCondition(req);
		//结果集转化为vo类型的集合
		List<SystemRoleResp> list2 = new ArrayList<SystemRoleResp>();
		if(list1!=null && !list1.isEmpty()){
			for(SystemUser user : list1){
				SystemRoleResp vo = new SystemRoleResp();
				PropertyUtils.copyProperties(vo, user);
				list2.add(vo);
			}
		}
		rs = Result.getSuccessResult();
		rs.setData(list2);
		return rs;
	}	

}
