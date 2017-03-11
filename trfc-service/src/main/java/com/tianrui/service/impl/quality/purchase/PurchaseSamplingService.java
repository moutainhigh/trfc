package com.tianrui.service.impl.quality.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.quality.purchase.IPurchaseSamplingService;
import com.tianrui.api.req.quality.purchase.PurchaseSamplingReq;
import com.tianrui.api.resp.quality.purchase.PurchaseSamplingItemResp;
import com.tianrui.api.resp.quality.purchase.PurchaseSamplingResp;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.quality.purchase.PurchaseSampling;
import com.tianrui.service.bean.quality.purchase.PurchaseSamplingItem;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.bean.system.base.SystemDataDictItem;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingItemMapper;
import com.tianrui.service.mapper.quality.purchase.PurchaseSamplingMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.service.mapper.system.base.SystemDataDictItemMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class PurchaseSamplingService implements IPurchaseSamplingService {

	@Resource
	private PurchaseSamplingMapper purchaseSamplingMapper;
	@Resource
	private SystemUserMapper systemUserMapper;
	@Resource
	private SystemDataDictItemMapper systemDataDictItemMapper;
	@Resource
	private PurchaseSamplingItemMapper purchaseSamplingItemMapper;
	@Resource
	private PurchaseArriveMapper purchaseArriveMapper;
	@Resource
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Resource
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;

	@Override
	public Result delete(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			PurchaseSampling ps = new PurchaseSampling();
			PropertyUtils.copyProperties(ps, req);
			//设置为删除状态
			ps.setState("0");
			ps.setModifier(req.getUser());
			ps.setModifytime(System.currentTimeMillis());
			ps.setUtc(System.currentTimeMillis());
			//更新到数据库
			int index = purchaseSamplingMapper.updateByPrimaryKeySelective(ps);
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
	public Result add(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PurchaseSampling ps = new PurchaseSampling();
			req.setId(UUIDUtil.getId());
			PropertyUtils.copyProperties(ps, req);
			ps.setCreator(req.getUser());
			ps.setModifier(req.getUser());
			ps.setModifytime(System.currentTimeMillis());
			ps.setUtc(System.currentTimeMillis());
			ps.setState("1");
			int index = purchaseSamplingMapper.insertSelective(ps);
			if(index>0 && saveItem(req)>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	/**
	 * 通过req获取List集合
	 */
	public List<PurchaseSamplingItem> getListReq(PurchaseSamplingReq req){
		List<PurchaseSamplingItem> list = new ArrayList<PurchaseSamplingItem>();
		//将字符串转换为json数组
		JSONArray json = JSONArray.parseArray(req.getArrstr());
		//通过循环把数组中的数据添加到list集合中
		for(int i=0;i<json.size();i++){
			//json对象转换为java对象
			PurchaseSamplingItem s = JSONArray.toJavaObject(json.getJSONObject(i), PurchaseSamplingItem.class);
			s.setId(UUIDUtil.getId());
			s.setSamplingid(req.getId());
			s.setState("1");
			s.setCreator(req.getUser());
			s.setModifier(req.getUser());
			s.setCreatetime(System.currentTimeMillis());
			s.setModifytime(System.currentTimeMillis());
			s.setUtc(System.currentTimeMillis());
			list.add(s);
		}
		return list;
	}
	//通过id删除子表信息
	public int deleteItem(PurchaseSamplingReq req){
		int index = 1;
		if(StringUtils.isNotBlank(req.getIdToDelete())){
			//把JSON字符串转换为JSON数组
			JSONArray json = JSONArray.parseArray(req.getIdToDelete());
			for(int i=0;i<json.size();i++){
				//获取id
				String id = json.get(i).toString();
				if(StringUtils.isNotBlank(id)){
					PurchaseSamplingItem item = new PurchaseSamplingItem();
					item.setId(id);
					//设置状态为删除状态
					item.setState("0");
					item.setModifier(req.getUser());
					item.setModifytime(System.currentTimeMillis());
					item.setUtc(System.currentTimeMillis());
					//更新数据到数据库
					int num = purchaseSamplingItemMapper.updateByPrimaryKeySelective(item);
					if(num<=0){
						index = -1;
					}
				}
			}
		}
		return index;
	}
	//保存子表信息
	public int saveItem(PurchaseSamplingReq req){
		int index = 1;
		if(StringUtils.isNotBlank(req.getArrstr())){
			List<PurchaseSamplingItem> list = getListReq(req);
			for(PurchaseSamplingItem item : list){
				//保存数据到数据库
				int num = purchaseSamplingItemMapper.insertSelective(item);
				if(num<1){
					index = -1;
				}
			}
		}
		return index;

	}
	@Override
	public Result update(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			PurchaseSampling ps = new PurchaseSampling();
			PropertyUtils.copyProperties(ps, req);
			ps.setModifier(req.getUser());
			ps.setModifytime(System.currentTimeMillis());
			ps.setUtc(System.currentTimeMillis());
			System.out.println(req.getIdToDelete());
			//更新到数据库
			int index = purchaseSamplingMapper.updateByPrimaryKeySelective(ps);
			if(index>0 && deleteItem(req)>0 && saveItem(req)>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<PurchaseSamplingResp> page = new PaginationVO<PurchaseSamplingResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			int total = purchaseSamplingMapper.count(req);
			page.setTotal(total);
			List<PurchaseSamplingResp> resps = new ArrayList<PurchaseSamplingResp>();
			if(total>0){
				List<PurchaseSampling> list = purchaseSamplingMapper.page(req);
				for(PurchaseSampling ps : list){
					PurchaseSamplingResp resp = new PurchaseSamplingResp();
					PropertyUtils.copyProperties(resp, ps);
					//获取采样类型
					if(StringUtils.isNotBlank(resp.getAssaytype())){
						SystemDataDictItem item = systemDataDictItemMapper.selectByPrimaryKey(resp.getAssaytype());
						if(item!=null){
							resp.setAssayname(item.getName());
						}
					}
					//获取用户名
					if(StringUtils.isNotBlank(resp.getCreator())){
						SystemUser su = systemUserMapper.selectByPrimaryKey(resp.getCreator());
						if(su!=null){
							resp.setCreator(su.getName());
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
	public Result getDetailData(PurchaseSamplingReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			List<PurchaseSamplingItem> list = purchaseSamplingItemMapper.findBySamplingid(req.getId());
			List<PurchaseSamplingItemResp> resps = new ArrayList<PurchaseSamplingItemResp>();
			if(list!=null && list.size()>0){
				for(PurchaseSamplingItem item : list){
					PurchaseSamplingItemResp resp = new PurchaseSamplingItemResp();
					PropertyUtils.copyProperties(resp, item);
					//假设 可以获取下列数据
					if(StringUtils.isNotBlank(resp.getSamplingcar())){
						PurchaseArrive arrive = purchaseArriveMapper.selectByCode(resp.getSamplingcar());
						if(arrive!=null){

							resp.setVehicle(arrive.getVehicleno());
							PurchaseApplication appl = purchaseApplicationMapper.selectByPrimaryKey(arrive.getBillid());
							if(appl!=null){
								resp.setMine(appl.getMinemouthname());
								resp.setSupplier(appl.getSuppliername());
								resp.setRemark(appl.getSupplierremark());
							}
							PurchaseApplicationDetail detail = purchaseApplicationDetailMapper.selectByPrimaryKey(arrive.getBilldetailid());
							if(detail!=null){
								resp.setMaterial(detail.getMaterielname());
							}
						}
					}
					resps.add(resp);
				}
			}
			rs = Result.getSuccessResult();
			rs.setData(resps);
		}
		return rs;
	}

}
