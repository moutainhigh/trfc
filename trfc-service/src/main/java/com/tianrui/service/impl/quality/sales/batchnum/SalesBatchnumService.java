package com.tianrui.service.impl.quality.sales.batchnum;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.quality.sales.batchnum.ISalesBatchnumService;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.quality.sales.batchnum.SalesBatchnumReq;
import com.tianrui.api.resp.basicFile.nc.MaterielManageVO;
import com.tianrui.api.resp.quality.sales.batchnum.SalesBatchnumResp;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.quality.sales.batchnum.SalesBatchnum;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.quality.sales.batchnum.SalesBatchnumMapper;
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
	public Result insertBatch(List<SalesBatchnumReq> list) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(list!=null && !list.isEmpty()){
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
				//
				batchnum.setCreator(req.getUser());
				batchnum.setModifier(req.getUser());
				batchnum.setModifytime(System.currentTimeMillis());
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
	 * 更新数据
	 */
	@Transactional
	public Result update(SalesBatchnumReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			SalesBatchnum batchnum  = new SalesBatchnum();
			//类型转换
			PropertyUtils.copyProperties(batchnum, req);
			//获取修改者和时间
			batchnum.setModifier(req.getUser());
			batchnum.setModifytime(System.currentTimeMillis());
		
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
			int total = salesBatchnumMapper.count(req);
			//获取数据总数
			page.setTotal(total);
			//获取当前页
			page.setPageNo(req.getPageNo());
			//获取每页显示的数据数
			page.setPageSize(req.getPageSize());
			List<SalesBatchnumResp> resps = new ArrayList<SalesBatchnumResp>();
			//判断是否有数据
			if(total>0){
				//获取分页数据
				List<SalesBatchnum> list = salesBatchnumMapper.page(req);
				//进行类型转换
				for(SalesBatchnum batchnum : list){
					SalesBatchnumResp resp = new SalesBatchnumResp();
					PropertyUtils.copyProperties(resp, batchnum);
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
				System.out.println(vo.getName());
			}
		}
		//操作成功
		Result rs = Result.getSuccessResult();
		rs.setData(list2);
		return rs;
	}

}
